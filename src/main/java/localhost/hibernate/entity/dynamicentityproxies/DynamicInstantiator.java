package localhost.hibernate.entity.dynamicentityproxies;


import org.hibernate.HibernateException;
import org.hibernate.tuple.Instantiator;

import java.io.Serializable;

public class DynamicInstantiator
        implements Instantiator {

    private final Class targetClass;

    public DynamicInstantiator(String targetClassName) {
        try {
            this.targetClass = Class.forName( targetClassName );
        }
        catch (ClassNotFoundException e) {
            throw new HibernateException( e );
        }
    }

    public Object instantiate(Serializable id) {
        return ProxyHelper.newProxy( targetClass, id );
    }

    public Object instantiate() {
        return instantiate( null );
    }

    public boolean isInstance(Object object) {
        try {
            return targetClass.isInstance( object );
        }
        catch( Throwable t ) {
            throw new HibernateException(
                    "could not get handle to entity as interface : " + t
            );
        }
    }
}

