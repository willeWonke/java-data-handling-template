package com.epam.izh.rd.online.service;

import java.util.regex.*;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replaceAll(remove, ""); //TODO
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     * <p>
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        Pattern pattern = Pattern.compile("[?]$");
        Matcher matcher = pattern.matcher(text);
        return matcher.find(); //TODO
    }

    /**
     * Реализовать функционал соединения переданных строк.
     * <p>
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        String arrString = "";
        for (String word : elements) {
            arrString = arrString.concat(word);
        }
        return arrString; //TODO
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        if (text.length() == 0) {
            return "";
        }

        char[] arrChars = text.toCharArray();
        StringBuffer arrStr = new StringBuffer();
        StringBuffer strBuffer = null;

        for (int i = 0; i < arrChars.length; i++) {
            char[] arrChangedChar = new char[arrChars.length];
            if (i % 2 == 0 && arrChars[i] != ' ') {
                arrChangedChar[i] = Character.toLowerCase(arrChars[i]);
            } else if (i % 2 != 0 && arrChars[i] != ' ') {
                arrChangedChar[i] = Character.toUpperCase(arrChars[i]);
            } else if (arrChars[i] == ' ') {
                arrChangedChar[i] = arrChars[i];
            }
            strBuffer = arrStr.insert(i, arrChangedChar[i]);
        }
        return strBuffer.toString(); //TODO
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     * <p>
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     * <p>
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if (string == "") {
            return false;
        }
        string = string.replaceAll("\\p{P}","");//удаляем все ненужное
        string = string.replaceAll("\\s", "");
        StringBuilder strBuilder = new StringBuilder(string);
        strBuilder.reverse();
        String invertedText = strBuilder.toString();

        return string.equalsIgnoreCase(invertedText); //TODO
    }
}
