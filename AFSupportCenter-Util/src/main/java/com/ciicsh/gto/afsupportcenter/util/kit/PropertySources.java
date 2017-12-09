package com.ciicsh.gto.afsupportcenter.util.kit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

/**
 * 属性文件辅助类，可以配合 PropertySourceKit 一起使用
 */
public class PropertySources {

  private static volatile Map<String, PropertySources> cachePropertySources = new HashMap<>();

  private final PropertySource<?> propertySource;

  private PropertySources(PropertySource<?> propertySource) {
    Assert.notNull(propertySource, "PropertySource 不能为 null ！");
    this.propertySource = propertySource;
  }

  public static PropertySources on(PropertySource<?> propertySource) {
    return new PropertySources(propertySource);
  }

  public static PropertySources onForCache(PropertySource<?> propertySource) {
    Assert.notNull(propertySource, "PropertySource 不能为 null ！");
    PropertySources propertySources = cachePropertySources.get(propertySource.getName());
    if (propertySources == null) {
      synchronized (cachePropertySources) {
        if (propertySources == null) {
          propertySources = on(propertySource);
          cachePropertySources.put(propertySource.getName(), propertySources);
        }
      }
    }
    return propertySources;
  }

  public boolean containsProperty(String name) {
    return getProperty(name) != null;
  }

  public Object getProperty(String name) {
    return propertySource.getProperty(name);
  }

  public String getString(String name) {
    return String.valueOf(getProperty(name));
  }

  public String getString(String name, String defaultValue) {
    Object vlaue = getProperty(name);
    return String.valueOf(vlaue == null ? defaultValue : vlaue);
  }

  private String getString(String name, Object defaultValue) {
    Object vlaue = getProperty(name);
    return String.valueOf(vlaue == null ? String.valueOf(defaultValue) : vlaue);
  }

  public int getInt(String name) {
    return Integer.valueOf(getString(name));
  }

  public int getInt(String name, int defaultValue) {
    return Integer.valueOf(getString(name, defaultValue));
  }

  public long getLong(String name) {
    return Long.valueOf(getString(name));
  }

  public long getLong(String name, long defaultValue) {
    return Long.valueOf(getString(name, defaultValue));
  }

  public double getDouble(String name) {
    return Double.valueOf(getString(name));
  }

  public double getDouble(String name, double defaultValue) {
    return Double.valueOf(getString(name, defaultValue));
  }

  public boolean getBoolean(String name) {
    return Boolean.valueOf(getString(name));
  }

  public boolean getBoolean(String name, boolean defaultValue) {
    return Boolean.valueOf(getString(name, defaultValue));
  }

}
