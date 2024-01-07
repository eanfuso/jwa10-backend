package edu.it;

import org.junit.jupiter.api.Test;

class App {
    public String getGreeting() {
        return "app should have a greeting";
    }
}

class AppTest {
    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        org.junit.Assert.assertEquals(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
