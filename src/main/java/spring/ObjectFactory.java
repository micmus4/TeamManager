package spring;

import constants.other.ProjectConstants;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

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
        String pathToConfigFile =
                Objects.requireNonNull( getClass().getResource( ProjectConstants.RELATIVE_PATH_TO_STRING_CONFIG_FILE ) ).toString();
        springContext = new ClassPathXmlApplicationContext( pathToConfigFile );
    }

    public Object getBean( String aBeanID )
    {
        return springContext.getBean( aBeanID );
    }

    public static ObjectFactory getFactory()
    {
        return objectFactoryInstance;
    }
}
