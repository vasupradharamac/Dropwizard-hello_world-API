package com.mycompany.helloworld;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloWorldApplicationTest {

    @Test
    public void testGetApplicationName() {
        HelloWorldApplication app = new HelloWorldApplication();
        String name = app.getName();
        assertThat(name).isEqualTo("helloworld");
    }

    @Test
    public void testApplicationMainMethod() {
        String[] noData = new String[]{};
        try {
            HelloWorldApplication.main(noData);
        }
        catch (Exception e) {
            fail("Exception wasn't expected XD!");
        }
    }
}
