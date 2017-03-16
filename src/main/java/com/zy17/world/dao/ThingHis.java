package com.zy17.world.dao;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 2017/3/16 zy17
 */
@Entity
@Data
public class ThingHis extends BaseEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  /**
   * 调用成功次数
   */
  private long age;
  /**
   * 失败原因
   */
  private String deadException;
  private String className;
  private String randomMethod;
  private String outputClass;
}
