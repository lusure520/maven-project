package com.example;

/**
 * This is a class.
 */
public class Person {

  /**
   * This is a constructor.
   */
  public Person() {

  }

  /**
   * @param someone the name of person.
   * @return string
   */
  public String person(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
