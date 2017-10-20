package com.ciicsh.gto.afsupportcenter.util.tips;

/**
 * 返回给前台的提示（最终转化为json形式）
 */
public class Tip<T> {

  // 状态码
  protected int code;
  // 信息
  protected String message;
  // 数据
  protected T data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
