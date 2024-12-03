package com.example.javamailapitp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaMailApitpApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaMailApitpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "ch.adam1235@gmail.com";
        String adminPassword = "cjis lbzr rwjx iwhc";

        String userEmail = "nvhvul9@gmail.com";
        String userPassword = "nuwb igtx fkft gntl";

        Admin admin = new Admin(adminEmail, adminPassword);
        User user = new User(userEmail, userPassword);

        Task task1 = new Task("Finish Report", "Complete the report by end of day.");
        Task task2 = new Task("Prepare Presentation", "Prepare slides for the team meeting.");

        admin.affectTask(task1, user);
        admin.affectTask(task2, user);

        List<Task> adminTasks = new ArrayList<>();
        adminTasks.add(task1);
        adminTasks.add(task2);
        admin.listAssignedTasks(adminTasks);

        System.out.println("\nUser retrieving tasks from inbox:");
        List<Task> userTasks = user.listTasks();
        for (Task task : userTasks) {
            System.out.println(task);
        }
    }
}
