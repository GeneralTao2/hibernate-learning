package localhost.hibernate.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import org.apache.commons.lang3.StringUtils;

public class AcmeCorpPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final Map<String, String> ABBREVIATIONS;

    static {
        ABBREVIATIONS = new TreeMap<>( String.CASE_INSENSITIVE_ORDER );
        //ABBREVIATIONS.put( "account", "acct" );
        ABBREVIATIONS.put( "number", "num" );
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace( name.getText() );
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                StringUtils.join( parts, '_' ).replaceAll("__", "_").replaceAll("__", "_"),
                name.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace( name.getText() );
        // Acme Corp says all sequences should end with _seq
        if ( !"seq".equals( parts.get( parts.size() - 1 ) ) ) {
            parts.add( "seq" );
        }
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                StringUtils.join( parts, '_' ),
                name.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace( name.getText() );
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                StringUtils.join( parts, '_' ).replaceAll("__", "_").replaceAll("__", "_"),
                name.isQuoted()
        );
    }

    private List<String> splitAndReplace(String name) {
        return Arrays.stream( StringUtils.splitByCharacterTypeCamelCase( name ) )
                .filter( StringUtils::isNotBlank )
                .map( p -> ABBREVIATIONS.getOrDefault( p, p ).toLowerCase( Locale.ROOT ) )
                .collect( Collectors.toList() );
    }
}