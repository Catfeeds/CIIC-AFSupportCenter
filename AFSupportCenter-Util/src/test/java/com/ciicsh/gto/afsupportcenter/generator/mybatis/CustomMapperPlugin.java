package com.ciicsh.gto.afsupportcenter.generator.mybatis;

import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import tk.mybatis.mapper.generator.MapperPlugin;

/**
 * Custom MapperPlugin
 */
public class CustomMapperPlugin extends MapperPlugin {

    public void setContext(Context context) {
        super.setContext(context);
        CommentGeneratorConfiguration commentCfg = context.getCommentGeneratorConfiguration();
        commentCfg.setConfigurationType(DbRemarksCommentGenerator.class.getCanonicalName());
    }
}
