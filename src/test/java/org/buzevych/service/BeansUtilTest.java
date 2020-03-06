package org.buzevych.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeansUtilTest {

  @Mock ApplicationContext context;

  BeansUtil util;

  @BeforeEach
  void init() {
    util = new BeansUtil(context);
    when(context.getBeanDefinitionNames()).thenReturn(new String[] {"bean1", "bean2"});
  }

  @Test
  void allBeanName() {
    assertEquals(List.of("bean1", "bean2"), util.allApplicationBeans());
  }
}
