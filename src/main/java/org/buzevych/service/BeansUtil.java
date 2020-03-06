package org.buzevych.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/** Bean Class which purpose is to output all bean names in to log */
@Component
public class BeansUtil {

  private ApplicationContext context;

  @Autowired
  BeansUtil(ApplicationContext context) {
    this.context = context;
  }

  /**
   * Used to get {@link List} of all Beans in the Spring context. Method is used for for testing
   * purpose.
   *
   * @return {@link List} of String representation of all Application beans.
   */
  public List<String> allApplicationBeans() {
    return List.of(context.getBeanDefinitionNames());
  }
}
