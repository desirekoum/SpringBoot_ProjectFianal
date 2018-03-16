package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading Data ...");

        Message message  = new Message("Hey this is the first message.", "01/20/2018", "desire");
        messageRepository.save(message);
    }
}
