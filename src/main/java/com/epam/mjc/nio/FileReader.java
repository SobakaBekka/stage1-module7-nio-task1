package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try {
            Path path = Paths.get(file.toURI());
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;  // Пропуск строк с неправильным форматом

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "Name":
                        name = value;
                        break;
                    case "Age":
                        age = Integer.parseInt(value);
                        break;
                    case "Email":
                        email = value;
                        break;
                    case "Phone":
                        phone = Long.parseLong(value);
                        break;
                    default:
                        System.out.println("Unknown key: " + key); // Добавление default блока для обработки неизвестных ключей
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Profile(name, age, email, phone);
    }
}
