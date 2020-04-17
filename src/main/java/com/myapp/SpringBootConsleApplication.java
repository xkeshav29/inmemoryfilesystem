package com.myapp;

import com.myapp.service.InMemoryFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootConsleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Autowired
    InMemoryFileSystem fileSystem;

    @Override
    public void run(String... args) {
        fileSystem.create("f1.txt", "this is a small 2020-02-06 13:34:01.777  INFO 35659 --- [  " +
                "     Thread-7] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotati" +
                "on.AnnotationConfigApplicationContext@70e67ede: startup date [Thu Feb 06 13:34:00 IST 2020]; root of context hierarchy sentence in file 1");


        fileSystem.create("f2.txt", "this is a sentence in file 2");
        fileSystem.create("f3.txt", "this is text in file 3");

        System.out.println("f1: " + fileSystem.read("f1.txt"));
        System.out.println("f2: " + fileSystem.read("f2.txt"));
        System.out.println("f3: " + fileSystem.read("f3.txt"));
    }
}