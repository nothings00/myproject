package com.znothings.thread;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author zenghh
 * @date 2020/11/24 4:05 PM
 * @version 1.0
 */
public class DOMUtils {
    private static final Map<ClassLoader, String> DOCUMENT_BUILDERS
            = Collections.synchronizedMap(new WeakHashMap<>());

    public static String create(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        if (loader == null) {
            loader = DOMUtils.class.getClassLoader();
        }
        if (loader == null) {
            return "";
        }
        String factory = DOCUMENT_BUILDERS.get(loader);
        if (factory == null) {
            DOCUMENT_BUILDERS.put(loader, factory);
        }
        return "success";
    }
}
