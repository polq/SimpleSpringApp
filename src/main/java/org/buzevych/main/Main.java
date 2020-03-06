package org.buzevych.main;

import org.buzevych.configuration.Config;
import org.buzevych.service.BeansUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    BeansUtil namesBean = context.getBean(BeansUtil.class);

    namesBean.allApplicationBeans().forEach(System.out::println);
  }
}
