package gr.codehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//gr.codehub.MainApplication

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) throws IOException  {

        SpringApplication.run(MainApplication.class, args);
        openHomePage();
    }


    private static void openHomePage() throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:4000");
    }


}