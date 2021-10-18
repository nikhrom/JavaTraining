package com.github.nikhrom.javatraining.http.practice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@UtilityClass
public class LocalDateFormatter {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate format(String date){
        return LocalDate.parse(date, FORMATTER);
    }

    public static boolean isValid(String date){
        try{
            format(date);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
