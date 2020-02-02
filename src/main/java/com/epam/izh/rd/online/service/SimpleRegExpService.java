package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")){
            BufferedReader bufferedReader = new BufferedReader(reader);
            String textFromFile = bufferedReader.readLine();

            String regExMain = "[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}";
            Pattern pattern = Pattern.compile(regExMain);
            Matcher matcher = pattern.matcher(textFromFile);

            String[] findedSubStrings = new String[2];
            int index = 0;
            while (matcher.find()) { // Заполнение массива найдеными счетами
                findedSubStrings[index] = matcher.group();
                index++;
            }

            String regExForChange = "\\s[\\d]{4}\\s[\\d]{4}\\s";
            String replaceWord = " **** **** ";
            String firstChangedString = findedSubStrings[0].replaceFirst(regExForChange, replaceWord); // Замена в счёте необходимых
            String secondChangedString = findedSubStrings[1].replaceFirst(regExForChange, replaceWord); // символов

            String oneConfidentVer = textFromFile.replaceFirst(regExMain, firstChangedString); // Замена неизменённых счетов изменёнными
            return oneConfidentVer.replaceFirst(regExMain, secondChangedString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount}
     * и ${balance} на заданные числа. Метод должен содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String payments = String.valueOf((int) paymentAmount);
        String balances = String.valueOf((int) balance);

        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String text = bufferedReader.readLine();
            String textMiddle = text.replaceFirst("\\$.[p][\\w]{13}.", payments);
            return textMiddle.replaceFirst("\\$.[b][\\w]{6}.", balances);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
