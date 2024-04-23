package no.strazdins;

/**
 * A class for creating greetings.
 */
public class Greeting {
  /**
   * Not allowed to instantiate the object.
   */
  private Greeting() {
  }

  /**
   * Create a Greeting.
   *
   * @param name name of the person/object to greet, use "World" by default
   * @return Hello ${name}!
   */
  public static String createGreeting(String name) {
    if (name == null || name.isEmpty()) {
      name = "World";
    }
    System.out.println("This is an undesired console log, should be caught by SonarLint...");
    return "Hello " + name + "!";
  }
}
