package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal varA = new BigDecimal(a);
        BigDecimal varB = new BigDecimal(b);

        return varA.divide(varB, range, BigDecimal.ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int[] arr = new int[range + 1];
        arr[0] = 2;
        int number = 3;
        int index = 1;
        while (arr[range] == 0) {
            BigInteger bigInteger = BigInteger.valueOf(number);
            if (bigInteger.isProbablePrime((int) Math.log(number))) {
                arr[index] = number;
                index++;
            }
            number += 2;
        }
        return BigInteger.valueOf(arr[range]);
    }
}
