package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        int day = localDate.getDayOfMonth();
        String strDay = "";
        if(day < 10){
            strDay = "0" + String.valueOf(strDay);
        } else {
            strDay = String.valueOf(day);
        }
        int month = localDate.getMonthValue();
        String strMonth = "";
        if(month < 10){
            strMonth= "0" + String.valueOf(month);
        } else {
            strMonth = String.valueOf(month);
        }
        int year = localDate.getYear();
        String string = strDay + "-" + strMonth + "-" + year;
        return string;
    }


    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        LocalDateTime localDateTime;
        String[] str2 = string.split("\\D");
        int[] str = new int[str2.length];
        for(int i = 0; i < str2.length; i++){
            str[i] = Integer.parseInt(str2[i]);
        }
        localDateTime = LocalDateTime.of(str[0],str[1],str[2],str[3], str[4]);
        return localDateTime;
    }


    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        String string = localDate.format(formatter);
        return string;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDate localDate = LocalDate.now();
        long leapYear = localDate.getYear();
        while ( leapYear % 4 !=0){
            leapYear++;
        }
        return leapYear;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        LocalDateTime start = LocalDateTime.of(year, 1,1,0,0);
        LocalDateTime finish = LocalDateTime.of(year,12,31,23,59,59,59);
        long seconds = start.until(finish, ChronoUnit.SECONDS) + 1;
        return seconds;
    }


}
