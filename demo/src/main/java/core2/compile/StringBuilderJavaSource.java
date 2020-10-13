package core2.compile;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * A Java source that holds the code in a string builder
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 1:57 PM
 * @version 1.0
 */
public class StringBuilderJavaSource  extends SimpleJavaFileObject {
    private StringBuilder code;

    public StringBuilderJavaSource(String name) {
        super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
        code = new StringBuilder();
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors){
        return code;
    }

    public void append(String str){
        code.append(str);
        code.append('\n');
    }

}
