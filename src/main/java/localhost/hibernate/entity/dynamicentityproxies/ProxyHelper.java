package localhost.hibernate.entity.dynamicentityproxies;


import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyHelper {

    public static <T> T newProxy(Class<T> targetClass, Serializable id) {
        return ( T ) Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                new Class[] {
                        targetClass
                },
                new DataProxyHandler(
                        targetClass.getName(),
                        id
                )
        );
    }

    public static String extractEntityName(Object object) {
        if ( Proxy.isProxyClass( object.getClass() ) ) {
            InvocationHandler handler = Proxy.getInvocationHandler(
                    object
            );
            if ( DataProxyHandler.class.isAssignableFrom( handler.getClass() ) ) {
                DataProxyHandler myHandler = (DataProxyHandler) handler;
                return myHandler.getEntityName();
            }
        }
        return null;
    }
}

