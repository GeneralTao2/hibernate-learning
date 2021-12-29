package localhost.hibernate.entity.dynamicentityproxies;


import org.hibernate.mapping.Component;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.component.PojoComponentTuplizer;

public class DynamicEmbeddableTuplizer
        extends PojoComponentTuplizer {

    public DynamicEmbeddableTuplizer(Component embeddable) {
        super( embeddable );
    }

    protected Instantiator buildInstantiator(Component embeddable) {
        return new DynamicInstantiator(
                embeddable.getComponentClassName()
        );
    }
}

