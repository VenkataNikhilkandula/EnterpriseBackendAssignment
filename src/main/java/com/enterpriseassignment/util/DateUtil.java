package com.enterpriseassignment.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private DateUtil() {
    }

    
    public static String formatDate(LocalDate date) {

        if (date == null) {
            return null;
        }

        return date.format(
                DateTimeFormatter.ofPattern(
                        AppConstants.DATE_FORMAT
                )
        );
    }

    
    public static String formatDateTime(
            LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        }

        return dateTime.format(
                DateTimeFormatter.ofPattern(
                        AppConstants.DATE_TIME_FORMAT
                )
        );
    }

    
    public static LocalDate parseDate(String date) {

        return LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern(
                        AppConstants.DATE_FORMAT
                )
        );
    }

    
    public static boolean isOverdue(LocalDate dueDate) {

        return dueDate != null
                && dueDate.isBefore(LocalDate.now());
    }

    
    public static boolean isDueToday(LocalDate dueDate) {

        return dueDate != null
                && dueDate.equals(LocalDate.now());
    }

    
    public static LocalDateTime now() {

        return LocalDateTime.now();
    }
}