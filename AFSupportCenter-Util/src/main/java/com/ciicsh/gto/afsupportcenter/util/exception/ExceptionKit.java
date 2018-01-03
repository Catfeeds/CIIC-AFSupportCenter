package com.ciicsh.gto.afsupportcenter.util.exception;

/**
 * 异常辅助类
 */

import java.lang.reflect.InvocationTargetException;

public final class ExceptionKit {

  public static RuntimeException unchecked(Throwable e) {
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    }
    if (e instanceof InvocationTargetException) {
      return unchecked(((InvocationTargetException) e).getTargetException());
    }
    return new RuntimeException(e);
  }

  /**
   * Throw checked exceptions like runtime exceptions.
   * <p>
   * see: http://blog.jooq.org/2012/09/14/throw-checked-exceptions-like-runtime-exceptions-in-java/
   */
  public static void rethrow(Throwable e) {
    ExceptionKit.<RuntimeException>rethrow0(e);
  }

  @SuppressWarnings("unchecked")
  public static <E extends Throwable> void rethrow0(Throwable e) throws E {
    throw (E) e;
  }
}
