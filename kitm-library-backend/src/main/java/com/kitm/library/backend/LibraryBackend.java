package com.kitm.library.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class LibraryBackend {

  public static void main(String[] args) {
    SpringApplication.run(LibraryBackend.class, args);
  }
}
