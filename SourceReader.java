package DI_demo;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by root on 8/10/17.
 */
public interface SourceReader {

    Map<String, BeanInfo> loadBeans(String filePath) throws IOException, SAXException;
}
