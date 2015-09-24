package com.ss.webutil.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.Writer;

/**
 * Created by LiYm on 2015/6/8.
 */
public class XStreamEx extends XStream {

    public XStreamEx() {
        super(new XppDriver(new XmlFriendlyReplacer("_-", "_")) {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, this.xmlFriendlyReplacer()) {

                    public void startNode(String name, Class clazz) {
                        super.startNode(name);
                    }

                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[");
                        super.writeText(writer, text);
                        writer.write("]]>");
                    }
                };
            }
        });
    }

    @Override
    protected MapperWrapper wrapMapper(MapperWrapper next) {

        MapperWrapper resMapper = new MapperWrapper(next)
        {
            /**
             * @param elementName
             * @return
             */
            @SuppressWarnings("rawtypes")
            @Override
            public Class realClass(String elementName)
            {
                Class res = null;

                try
                {
                    res = super.realClass(elementName);
                }
                catch (CannotResolveClassException e)
                {
                    System.out.printf("Ignore filed(%s)\n", elementName);
                }
                return res;
            }
        };

        return resMapper;
    }
}
