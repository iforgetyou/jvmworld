package com.zy17.world.alive;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import com.zy17.world.energy.StringEnergy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by zy17 on 2016/12/3.
 */
@Data
@Slf4j
@Component
@Scope(SCOPE_PROTOTYPE)
public class AThing implements Runnable {
  private UUID id = UUID.randomUUID();
  private String name;

  public void run() {
    log.info("I'm {},my id is:{}", StringEnergy.getRandomStr(), getId());
  }


}
