package com.lucidity.assesment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hello World!");
        String[] command = {"/bin/bash", "./src/main/java/setup_environment.sh"};

        // Create a ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Merge standard error and output

        // Start the process
        Process process = processBuilder.start();

        // Read and log the output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Environment setup completed successfully.");
        } else {
            System.err.println("Environment setup failed with exit code " + exitCode);
        }
    }
}
