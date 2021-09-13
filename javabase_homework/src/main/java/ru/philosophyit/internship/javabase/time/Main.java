package ru.philosophyit.internship.javabase.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    static Calendar calendar = Calendar.getInstance();

    public static void main(String... args) {
        currentMonthCalendar();
    }

    public static void currentMonthCalendar() {
        Date date = new Date();
        calendar.setTime(date);

        System.out.printf("%22s\n", new SimpleDateFormat("MMMM").format(date));
        System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s %-5s", "пн", "вт", "ср", "чт", "пт", "сб", "вс");
        System.out.println();

        try {
            int[][] array = fillCalendar();
            printCalendar(array);
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
        }
    }

    private static int[][] fillCalendar() {
        int firstDate = 1;
        final int currentMonthDayLim = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, firstDate);
        final int padding = calendar.getTime().getDay();
        calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) + 1);
        final int nextMonthDayLim = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) - 1);
        int prevMonthDayLim = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[][] array = new int[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < padding - 1) {
                    array[i][j] = prevMonthDayLim - padding + 2;
                    prevMonthDayLim++;
                    continue;
                }
                if (firstDate <= currentMonthDayLim) {
                    array[i][j] = firstDate++;
                } else {
                    firstDate = 1;
                    if (firstDate <= nextMonthDayLim) {
                        array[i][j] = firstDate++;
                    }
                }
            }
        }
        return array;
    }

    private static void printCalendar(final int[][] array) {
        if (array == null) throw new RuntimeException();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%-6d", array[i][j]);
            }
            System.out.println();
        }
    }

}
