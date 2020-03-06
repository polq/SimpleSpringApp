package org.buzevych.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Config class file which is defined as {@link Configuration} annotation and {@link ComponentScan}
 * which specifies directory that should be scanned for spring beans.
 */
@Configuration
@ComponentScan("org.buzevych")
public class Config {

}
