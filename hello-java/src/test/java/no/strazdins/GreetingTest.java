package no.strazdins;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingTest {
  @Test
  void defaultGreeting() {
    assertEquals("Hello World!", Greeting.createGreeting(null));
  }

  @Test
  void customGreeting() {
    assertEquals("Hello Chuck!", Greeting.createGreeting("Chuck"));
  }
}
