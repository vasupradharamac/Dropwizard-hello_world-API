package com.mycompany.helloworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.helloworld.api.HelloWorld;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.*;

public class HelloWorldTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void testSerialization() throws Exception {
        final HelloWorld message = new HelloWorld("Hello World!");
        String actual = MAPPER.writeValueAsString(message);
        String expected = fixture("fixtures/helloworld.json");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDeserialization() throws Exception {
        final HelloWorld expected = new HelloWorld("Hello World!");
        HelloWorld actual = MAPPER.readValue(fixture("fixtures/helloworld.json"), HelloWorld.class);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}