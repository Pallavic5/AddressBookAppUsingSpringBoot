package com.bridgelabz.addressbookappbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AddressBookAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookAppApplication.class, args);
        System.out.println("Welcome To AddressBook App...");
        log.info("Hello Logger!!!");        //logger added here
    }

}
