package localhost.hibernate.util;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionMetadataBuilderContributor
        implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        /*metadataBuilder.applySqlFunction(
                "instr", new StandardSQLFunction( "instr", StandardBasicTypes.STRING )
        );*/
        /* TODO: doesnt solve the problem with embeddable types naming */
        metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE);
    }
}

