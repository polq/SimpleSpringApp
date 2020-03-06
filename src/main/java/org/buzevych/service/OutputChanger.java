package org.buzevych.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Class that is used to set standard System output to {@link Logger} info and all standard System
 * error to {@link Logger}.error Class is defined as a Spring {@link Component} and has just one
 * {@link PostConstruct} marked method which will be called by {@link
 * org.springframework.beans.factory.config.BeanPostProcessor} after Spring creates all POJO's and
 * perform all Injections.
 */
@Component
public class OutputChanger {

  /**
   * Method is used to create two custom {@link PrintStream} objects and populate standard
   * System.out and System.err fields with those custom {@link PrintStream}
   */
  @PostConstruct
  private void changeStandardOutput() {
    PrintStream infoPrintStream = new CustomLog4jPrintStream("info");
    PrintStream errorPrintStream = new CustomLog4jPrintStream("error");
    System.setErr(errorPrintStream);
    System.setOut(infoPrintStream);
  }

  private static class CustomLog4jPrintStream extends PrintStream {

    public CustomLog4jPrintStream(String value) {
      super(new CustomLog4jOutputStream(value));
    }

    @Override
    public void print(String s) {
      super.print(s);
      try {
        this.out.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private static class CustomLog4jOutputStream extends OutputStream {

      private static final Logger logger = LogManager.getLogger();
      private static char[] buffer = new char[1024 * 1024];
      String outputType;

      private static int count = 0;

      public CustomLog4jOutputStream(String outputType) {
        this.outputType = outputType;
      }

      @Override
      public void write(int b) throws IOException {
        char character = (char) b;
        if (character == '\n') {
          this.flush();
        } else {
          buffer[count++] = character;
        }
      }

      @Override
      public void flush() throws IOException {
        if (count != 0) {
          Method method = ReflectionUtils.findMethod(logger.getClass(), outputType, String.class);
          ReflectionUtils.invokeMethod(
              Objects.requireNonNull(method), logger, String.valueOf(buffer, 0, count));
          count = 0;
        }
        super.flush();
      }
    }
  }
}
