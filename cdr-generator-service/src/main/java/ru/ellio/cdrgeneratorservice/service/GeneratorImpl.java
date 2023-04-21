package ru.ellio.cdrgeneratorservice.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeneratorImpl implements Generator {
    @Value("${generator.options.directoryPath}")
    private String directoryPath; // Стандартная директория хранения отчетов
    @Value("${generator.options.format}")
    private String reportFormat; // Формат в котором мы сохраняем файл
    @Value("${generator.options.amountPhones}")
    private int amountPhones; // Количество абонентов

    /**
     * Метод, генерирующий cdr файл
     *
     * @return файл
     */
    @Override
    public File generate() {
        final List<String> possibleTypes = List.of("01", "02");
        final List<String> possiblePhoneNumbers = generatePhoneNumbers();
        createDirectory(directoryPath);
        StringBuilder cdr = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                String.format("%s/random_cdr%s", directoryPath, reportFormat)))) {

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); // вормат времени в cdr файле
            Random random = new Random();

            // для каждого абонента генерируем 10 рандомных звонков и записываем в файл
            for (String phone : possiblePhoneNumbers) {
                LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 1, 15, 10);
                LocalDateTime endDateTime = startDateTime
                        .plusMinutes(random.nextInt(30))
                        .plusSeconds(random.nextInt(60));
                String callType = possibleTypes.get(random.nextInt(2));
                cdr.append(callType).append(",").append(phone)
                        .append(",").append(startDateTime.format(dateFormat))
                        .append(",").append(endDateTime.format(dateFormat)).append("\n");

                for (int i = 1; i < 10; i++) {
                    startDateTime = startDateTime.plusDays(random.nextInt(30))
                            .plusMonths(random.nextInt(1))
                            .plusHours(random.nextInt(12))
                            .plusMinutes(random.nextInt(60))
                            .plusSeconds(random.nextInt(60));
                    endDateTime = startDateTime
                            .plusMinutes(random.nextInt(30))
                            .plusSeconds(random.nextInt(60));
                    cdr.append(callType).append(",").append(phone)
                            .append(",").append(startDateTime.format(dateFormat))
                            .append(",").append(endDateTime.format(dateFormat))
                            .append("\n");
                }
            }
            writer.write(cdr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(String.format("%s/random_cdr%s", directoryPath, reportFormat));
    }

    /**
     * Метод, который генерирует телефонные номера.
     *
     * @return список телефонных номеров
     */
    private List<String> generatePhoneNumbers() {
        long phoneNumber = 73734435243L;
        List<String> phones = new ArrayList<>();
        for (int i = 0; i < amountPhones; i++) {
            phones.add(String.valueOf(phoneNumber + (long) i * i));
        }
        return phones;
    }

    /**
     * Метод, который созадет директорию.
     *
     * @param directoryPath путь к директории
     */
    private void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}

