package com.ciicsh.gto.afsupportcenter.generator.mybatis;

import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;
import tk.mybatis.mapper.generator.MapperCommentGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 此插件使用数据库表中列的注释来生成 Java Model中属性的注释
 */
public class DbRemarksCommentGenerator extends MapperCommentGenerator {
    private Properties properties = new Properties();
    private String databaseProductName = "MYSQL";

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.properties.putAll(properties);

        String databaseProductName = properties.getProperty("databaseProductName");
        if (StringUtility.stringHasValue(databaseProductName)) {
            this.databaseProductName = databaseProductName;
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        super.addModelClassComment(topLevelClass, introspectedTable);
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + getTableComment(introspectedTable));
        topLevelClass.addJavaDocLine(" */");
        getTableComment(introspectedTable);
    }


    private String getTableComment(IntrospectedTable introspectedTable) {
        try {
            //添加start
            try (Connection conn = this.getConnection(introspectedTable.getContext())) {
                try (Statement stmt = conn.createStatement()) {
                    StringBuffer sql = new StringBuffer();
                    String columnName = "";
                    String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
                    if ("MYSQL".equalsIgnoreCase(databaseProductName)) {
                        //设置数据库表的备注信息
                        sql = sql.append("SHOW TABLE STATUS LIKE '").append(tableName).append("'");
                        columnName = "COMMENT";
                    } else if ("ORACLE".equalsIgnoreCase(databaseProductName)) {
                        sql.append("select * from user_tab_comments where Table_Name Like '").append(tableName).append("'");
                        columnName = "COMMENTS";
                    } else if ("SQLSERVER".equalsIgnoreCase(databaseProductName)) {

                    }

                    try (ResultSet rs = stmt.executeQuery(sql.toString())) {
                        while (rs.next()) {
                            return rs.getString(columnName);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //添加end
        return null;
    }

    private Connection getConnection(Context context) throws SQLException {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = context.getJdbcConnectionConfiguration();
        Object connectionFactory;
        if (jdbcConnectionConfiguration != null) {
            connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
        } else {
            connectionFactory = ObjectFactory.createConnectionFactory(context);
        }

        return ((ConnectionFactory) connectionFactory).getConnection();
    }
}
