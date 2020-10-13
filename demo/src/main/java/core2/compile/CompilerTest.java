package core2.compile;

import javax.swing.*;
import javax.tools.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 2:52 PM
 * @version 1.0
 */
public class CompilerTest {
    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        final List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();

        DiagnosticCollector<JavaFileObject>  diagnostics = new DiagnosticCollector<>();

        JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics,null, StandardCharsets.UTF_8);

        fileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager){
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                //如果是我们源码生成的类，加入类列表中
                if (className.startsWith("x.")){
                    ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
                    classFileObjects.add(fileObject);
                    return fileObject;
                }else {
                    return super.getJavaFileForOutput(location,className,kind,sibling);
                }
            }
        };


        String frameClassName  = args.length == 0? "core2.compile.x.ButtonFrame" : args[0];
        //生成源码对象
        JavaFileObject source = buildSource(frameClassName);
        //编译源码，逻辑在fileManager.getJavaFileForOutput(...)
        JavaCompiler.CompilationTask task = compiler.getTask(null,fileManager,diagnostics,null,null
                , Arrays.asList(source));
        //调用编译过程
        Boolean result = task.call();
        for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()){
            System.out.println(d.getKind() + ": " + d.getMessage(null));
        }
        fileManager.close();
        if (!result){
            System.out.println("Compilation failed");
            System.exit(1);
        }

        EventQueue.invokeLater(()->{
            try {
                Map<String,byte[]> byteCodeMap = new HashMap<>();
                for (ByteArrayJavaClass cl : classFileObjects){
                    byteCodeMap.put(cl.getName().substring(1),cl.getBytes());
                }
                //类加载
                ClassLoader loader = new MapClassLoader(byteCodeMap);
                //获取自定义的类字节码
                JFrame frame = (JFrame) loader.loadClass("x.Frame").newInstance();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("CompilerTest");
                frame.setVisible(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    /**
     * 源码生成
     * @param superclassName 父类
     * @return 源码
     * @throws ClassNotFoundException
     * @throws IOException
     */
    static JavaFileObject buildSource(String superclassName) throws ClassNotFoundException, IOException {
        //新增源码的类名
        StringBuilderJavaSource source = new StringBuilderJavaSource("x.Frame");
        source.append("package x;\n");
        source.append("public class Frame extends " + superclassName + " {");
        source.append("protected void addEventHandlers(){");
        final Properties props = new Properties();
        //通过配置嵌入代码
        props.load(Class.forName(superclassName).getResourceAsStream("/compile/action.properties"));
        for (Map.Entry<Object,Object> e : props.entrySet()){
            //对象
            String beanName = (String) e.getKey();
            //代码
            String eventCode = (String) e.getValue();
            source.append(beanName + ".addActionListener(event -> {");
            source.append(eventCode);
            source.append("} );");
        }
        source.append("} }");
        return source;
    }
}
