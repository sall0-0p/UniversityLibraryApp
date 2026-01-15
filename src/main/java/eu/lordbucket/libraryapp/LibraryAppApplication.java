package eu.lordbucket.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class LibraryAppApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(LibraryAppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("http://localhost:2811"));
            } catch (Exception e) {
                System.err.println("Failed to open browser: " + e.getMessage());
            }
        }
    }
}
