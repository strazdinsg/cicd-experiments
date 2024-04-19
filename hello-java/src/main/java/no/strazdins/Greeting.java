package no.strazdins;

public class Greeting {
  private Greeting() {
  }

  public static String createGreeting(String name) {
    if (name == null || name.isEmpty()) {
      name = "World";
    }
    return "Hello " + name + "!";
  }
}
