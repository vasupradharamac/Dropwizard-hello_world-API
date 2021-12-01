package com.mycompany.helloworld;

import com.mycompany.helloworld.api.HelloWorld;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import io.dropwizard.testing.ResourceHelpers;
import javax.ws.rs.client.Entity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class HelloWorldIntegrationTest {

    public static DropwizardAppExtension<HelloWorldConfiguration> app = new DropwizardAppExtension<>(
            HelloWorldApplication.class,
            ResourceHelpers.resourceFilePath("config.yml")
    );

    @Test
    public void testGetRequestOnHeyURI() {
        Client client = app.client();
        Response response = client.target(
                String.format("http://localhost:%d/hey", app.getLocalPort()))
                .request().get();
        assertThat(response.getStatus()).isEqualTo(200);
        Map data = response.readEntity(Map.class);
        assertThat((data.get("content"))).isEqualTo("Hello World!");
    }

    @Test
    public void testPostRequestOnHeyURI() {
        Client client = app.client();
        HelloWorld requestPOJO = new HelloWorld("New Content");
        Response response = client.target(
                String.format("http://localhost:%d/hey", app.getLocalPort()))
                .request().post(Entity.json(requestPOJO));
        assertThat(response.getStatus()).isEqualTo(200);
        HelloWorld actual = response.readEntity(HelloWorld.class);
        assertThat(actual.getContent()).isEqualTo(requestPOJO.getContent());
    }

}