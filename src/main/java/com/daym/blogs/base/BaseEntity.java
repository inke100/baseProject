package com.daym.blogs.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mayongchang
 * Date: 2024/4/8
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   *  创建用户
   */
  private String creator;
  /**
   * 更新用户
   */
  private String updater;
  /**
   * 用户注册时间，记录用户加入系统的时间
   */
  private Date createdTime;

  /**
   * 用户信息更新时间，记录用户信息最后更新的时间
   */
  private Date updatedTime;
}
