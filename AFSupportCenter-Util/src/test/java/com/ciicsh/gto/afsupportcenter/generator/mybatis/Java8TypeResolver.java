package com.ciicsh.gto.afsupportcenter.generator.mybatis;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

/**
 * java8
 */
public class Java8TypeResolver extends JavaTypeResolverDefaultImpl {

    private static final String PROP_USE_JAVA_TIME = "useJava8Time";

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);

        if ("true".equalsIgnoreCase(properties.getProperty(PROP_USE_JAVA_TIME))) {
            typeMap.put(Types.DATE, build("DATE", LocalDate.class));
            typeMap.put(Types.TIME, build("TIME", LocalTime.class));
            typeMap.put(Types.TIMESTAMP, build("TIMESTAMP", LocalDateTime.class));
        }

    }

    private JdbcTypeInformation build(String jdbcTypeName, Class<?> type) {
        return new JdbcTypeInformation(jdbcTypeName, new FullyQualifiedJavaType(type.getName()));
    }

}
