package DI_demo;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by root on 8/16/17.
 */
public class Bootstrap {
    public static void main(String args[]) throws IOException, SAXException {
        BeanFactory factory = new XMLContext("beans.xml");
        Speakable s =(Speakable)factory.getBean("Person1");
        s.speak("Lesson one!");
    }
}
