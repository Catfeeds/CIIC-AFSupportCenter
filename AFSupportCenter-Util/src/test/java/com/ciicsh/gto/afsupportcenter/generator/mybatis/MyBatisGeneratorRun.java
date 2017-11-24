package com.ciicsh.gto.afsupportcenter.generator.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGeneratorRun {

    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(getInputStream(Modal.SocialSecurity));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            for (String warning : warnings) {
                System.out.println(warning);
            }
            System.out.println("success:");
        } catch (Exception e) {
            System.out.println("error:");
            e.printStackTrace();
        }
    }

    enum Modal {
        HealtHmedical("config/generatorConfig-gtobusinessdb-healthmedical.xml"), // 健康医疗
        HouseFund("config/generatorConfig-gtobusinessdb-housefund.xml"), // 公积金
        SocialSecurity("config/generatorConfig-gtobusinessdb-socialsecurity.xml"), // 社保
        ;

        public final String config;

        Modal(String config) {
            this.config = config;
        }
    }

    static InputStream getInputStream(Modal modal) throws IOException {
        return new ClassPathResource(modal.config).getInputStream();
    }

}
