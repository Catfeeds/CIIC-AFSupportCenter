package com.ciicsh.gto.afsupportcenter.util.context.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;

/**
 * 自定义 name Condition，此 Condition 只在 method 上有效 1. method --> gt1.names.name1 = true 、gt1.names.name2 = false 2. class
 * --> gt1.names = true
 */
public class CustomByNameCondition implements Condition {
  private static final String PREFIX = "gt1.names";

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
    Environment env = context.getEnvironment();
    if (annotatedTypeMetadata instanceof MethodMetadataReadingVisitor) {// method
      MethodMetadataReadingVisitor visitor = (MethodMetadataReadingVisitor) annotatedTypeMetadata;
      // 默认开启
      return env.getProperty(PREFIX + "." + visitor.getMethodName(), Boolean.class, true);
    } else if (annotatedTypeMetadata instanceof AnnotationMetadataReadingVisitor) {// class
      // 默认开启
      return env.getProperty(PREFIX, Boolean.class, true);
    }
    return true;
  }
}
