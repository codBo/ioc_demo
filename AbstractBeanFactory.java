package DI_demo;


import org.xml.sax.SAXException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by root on 8/10/17.
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    private String filePath;
    private Map<String, BeanInfo> container;
    protected SourceReader reader;

    public AbstractBeanFactory(String filePath) {
        this.filePath = filePath;
    }
    protected abstract void setSourceReader(SourceReader reader);

    public void registerBeans() throws IOException, SAXException {
        this.container = this.reader.loadBeans(filePath);
    }
    @Override
    public Object getBean(String name) {
        BeanInfo beanInfo = this.container.get(name);
        if(beanInfo == null) {
            return null;
        } else {
            return this.parseBean(beanInfo);
        }
    }
    protected Object parseBean(BeanInfo beanInfo) {

        Class clazz;
        try{
            clazz = Class.forName(beanInfo.getType());
            Object bean = clazz.newInstance();
            Method[] methods = clazz.getMethods();

            for(String property: beanInfo.getProperties().keySet()) {
                String setter = "set" + (""+property.charAt(0)).toUpperCase() + property.substring(1);
                for(Method method: methods) {
                    String methodName = method.getName();
                    if(methodName.equals(setter)) {
                        Object value = beanInfo.getProperties().get(property);
                        method.invoke(bean, value);
                        continue;
                    }
                }
            }
            return bean;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
