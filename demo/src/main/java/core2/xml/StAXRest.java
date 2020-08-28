package core2.xml;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 3:23 PM
 * @version 1.0
 */
public class StAXRest {
    public static void main(String[] args) throws XMLStreamException, IOException {
        String urlString;
        if (args.length == 0){
            urlString = "http://www.w3c.org";
            System.out.println("Using " + urlString);
        }else {
            urlString = args[0];
        }

        URL url = new URL(urlString);
        InputStream in = url.openStream();
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader parser = factory.createXMLStreamReader(in);

        while (parser.hasNext()){
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT){
                if (parser.getLocalName().equals("a")){
                    String href = parser.getAttributeValue(null,"href");
                    if (href!=null){
                        System.out.println(href);
                    }
                }
            }
        }
    }
}
