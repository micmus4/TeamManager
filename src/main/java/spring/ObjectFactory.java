package spring;

import constants.other.ProjectConstants;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ObjectFactory
{

    private static final ObjectFactory objectFactoryInstance = new ObjectFactory();

    private ClassPathXmlApplicationContext springContext;

    private ObjectFactory()
    {
        init();
    }

    private void init()
    {
        String pathToConfigFile = getClass().getResource( ProjectConstants.RELATIVE_PATH_TO_STRING_CONFIG_FILE ).toString();
        springContext = new ClassPathXmlApplicationContext( pathToConfigFile );
    }

    public Object getBean( String beanID )
    {
        return springContext.getBean( beanID );
    }

    public static ObjectFactory getFactory()
    {
        return objectFactoryInstance;
    }
}
