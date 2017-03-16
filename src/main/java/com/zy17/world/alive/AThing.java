package com.zy17.world.alive;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import com.zy17.world.energy.Behave;
import com.zy17.world.tools.ReflectionUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zy17 on 2016/12/3.
 */
@Data
@Slf4j
@Component
@Scope(SCOPE_PROTOTYPE)
public class AThing implements Runnable {
  public static final int MAX_AGE = 1000;
  private UUID id = UUID.randomUUID();
  /**
   * 调用成功次数
   */
  private long age;
  /**
   * 失败原因
   */
  private Exception dead_exception;
  /**
   *
   */
  Method randomMethod;

  Class outputClass;

  @Autowired
  ReflectionUtil util;
  @Autowired
  Behave behave;

  public void run() {
    try {
      randomMethod = util.getRandomMethod();
      outputClass = randomMethod.getReturnType();

      while (age < MAX_AGE) {
        behave.act(randomMethod);
        age++;
      }
    } catch (Exception e) {
      dead_exception = e;
      log.warn("{} dead for:{},age:{},method:{}", getId(), dead_exception, age, randomMethod);
    } finally {
      if (age > 0) {
        log.info("{} lived:{}", getId(), age);
      }
    }
  }

}
