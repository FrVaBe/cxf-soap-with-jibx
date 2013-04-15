package de.frvabe.sample.calculator;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(final String[] args) {
    new ClassPathXmlApplicationContext(new String[]{"calculator-app-context.xml"});
  }
}
