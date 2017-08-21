package DI_demo;

import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by root on 8/10/17.
 */
public class XMLSourceReader implements SourceReader {

    private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    //Load and parse XML file into DOM
    public Document parse(String filePath) {
        Document document = null;
        try {
            //DOM parser instance
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //parse an XML file into a DOM tree
            File file = new File(filePath);
            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    @Override
    public Map<String, BeanInfo> loadBeans(String filePath) throws IOException, SAXException {

        Map<String, BeanInfo> beans = new HashMap<>();
        Document document = parse("src/DI_demo/Person.xml");
        Element rootElement = document.getDocumentElement();
        //traverse child elements
        NodeList nodes = rootElement.getChildNodes();
        for (int i=0; i < nodes.getLength(); i++)
        {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node;
                //process child element
            }
        }

        NodeList nodeList = rootElement.getElementsByTagName("bean");
        if(nodeList != null)
        {
            for (int i = 0 ; i < nodeList.getLength(); i++)
            {
                Element element = (Element)nodeList.item(i);
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                BeanInfo beanInfo = new BeanInfo();
                beanInfo.setId(id);
                beanInfo.setType(className);
                NodeList elementNodes = element.getElementsByTagName("property");
                for (int j = 0; j < elementNodes.getLength(); j ++) {
                    Element element1 = (Element)elementNodes.item(j);
                    String name = element1.getAttribute("name");
                    String ref = element1.getAttribute("ref");
                    beanInfo.addProperty(name, ref);
                }
                beans.put(id, beanInfo);
            }
        }
        return beans;
    }

}
