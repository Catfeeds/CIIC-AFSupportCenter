package com.ciicsh.gto.afsupportcenter.util.kit;

import java.io.IOException;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * PropertySource kit
 */
public class PropertySourceKit {

  /**
   * 加载 yml 文件
   * 
   * @param name
   * @param path
   * @return
   */
  public static PropertySource<?> loadYml(String name, Resource path) {
    YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
    if (!path.exists()) {
      throw new IllegalArgumentException("Resource " + path + " does not exist");
    }
    try {
      return loader.load("custom-resource", path, null);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
    }
  }

  /**
   * 加载 properties 文件
   * 
   * @param path
   * @return
   */
  public static PropertySource<?> loadProperties(Resource path) {
    PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
    if (!path.exists()) {
      throw new IllegalArgumentException("Resource " + path + " does not exist");
    }
    try {
      return loader.load("custom-resource", path, null);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to load properties configuration from " + path, ex);
    }
  }
}
