package DI_demo;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by root on 8/16/17.
 */
public class XMLContext extends AbstractBeanFactory{

    public XMLContext(String filePath) throws IOException, SAXException {
        super(filePath);
        this.setSourceReader(new XMLSourceReader());
        this.registerBeans();
    }

    @Override
    protected void setSourceReader(SourceReader reader) {
        this.reader = reader;
    }
}
