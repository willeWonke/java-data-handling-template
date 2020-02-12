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
        BigDecimal result = new BigDecimal(a).divide(new BigDecimal(b),range,1);
        return result;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger a = BigInteger.valueOf(2);
        BigInteger result = a;
        for(int i = 0; i <= range; i++){
            boolean b = true;
            while (b) {
                boolean c = true;
                for (int j = 2; j < a.intValue(); j++) {
                    if (a.intValue() % j == 0) {
                        c = false;
                        break;
                    }
                }
                if(c){
                    b = false;
                    result = a;
                }
                BigInteger d = new BigInteger("1");
                a = new BigInteger(String.valueOf(a.add(d)));
            }
        }
        return result;
    }
}
