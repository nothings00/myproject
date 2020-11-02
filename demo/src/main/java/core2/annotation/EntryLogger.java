package core2.annotation;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 增加进入日志
 * @author zenghh
 * @date 2020/11/2 4:02 PM
 * @version 1.0
 */
public class EntryLogger extends ClassVisitor {
    private String className;

    public EntryLogger(ClassWriter writer,String className) {
        super(Opcodes.ASM5, writer);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,methodName,desc,signature,exceptions);
        return new AdviceAdapter(Opcodes.ASM5,mv,access,methodName,desc) {
            private String loggerName;

            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                return new AnnotationVisitor(Opcodes.ASM5) {
                    @Override
                    public void visit(String name ,Object value) {
                        //noinspection AlibabaUndefineMagicConstant
                        if ("LogEntry;".contains(desc) && "logger".equals(name)){
                          loggerName = value.toString();
                      }
                    }
                };
            }

            @Override
            public void onMethodEnter(){
                if (loggerName!=null){
                    visitLdcInsn(loggerName);
                    visitMethodInsn(INVOKESTATIC,"java/util/logging/Logger",
                            "getLogger","(Ljava/lang/String;)Ljava/util/logging/Logger;",false);
                    visitLdcInsn(className);
                    visitLdcInsn(methodName);
                    visitMethodInsn(INVOKEVIRTUAL,"java/util/logging/Logger","entering",
                            "(Ljava/lang/String;Ljava/lang/String;)V",false);
                    loggerName = null;
                }
            }
        };
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(EntryLogger.class.getResource("/").toURI());
        ClassReader reader = new ClassReader(Files.newInputStream(path));
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        EntryLogger entryLogger = new EntryLogger(writer,path.toString().
                replaceAll(".class","").replaceAll("[/\\\\]]","."));
        reader.accept(entryLogger,ClassReader.EXPAND_FRAMES);
        Files.write(path,writer.toByteArray());

    }

}
