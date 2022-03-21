
import java.math.BigInteger;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Converter {

    String convert(String input, int baseIn, int baseOut) {
        String result = "";
        switch (baseIn) {
            case 2:
                switch (baseOut) {
                    case 10:
                        result = convertBinToDec(input);
                        break;
                    case 16:
                        result = convertBinToHex(input);
                        break;
                }
                break;
            case 10:
                switch (baseOut) {
                    case 2:
                        result = convertDecToBin(input);
                        break;
                    case 16:
                        result = convertDecToHex(input);
                        break;
                }
                break;
            case 16:
                switch (baseOut) {
                    case 2:
                        result = convertHexToBin(input);
                        break;
                    case 10:
                        result = convertHexToDec(input);
                        break;
                }
                break;
        }
        return result;
    }

    private String convertBinToDec(String input) {
        BigInteger valueInput = new BigInteger(input);
        BigInteger valueOutput = new BigInteger("0");
        BigInteger binBase = new BigInteger("2");
        BigInteger decBase = new BigInteger("10");
        //for each digit: output += digit*2^(reverse index)
        for (int i = 0; i < input.length(); i++) {
            BigInteger digit = new BigInteger(Character.toString(input.charAt(i)));
            valueOutput = valueOutput.add(digit.multiply(binBase.pow(input.length() - 1 - i)));
        }
        return valueOutput.toString();
    }



    private String convertBinToHex(String input) {
        HashMap<String, String> valueMap = new HashMap<>();
        valueMap.put("0000", "0");
        valueMap.put("0001", "1");
        valueMap.put("0010", "2");
        valueMap.put("0011", "3");
        valueMap.put("0100", "4");
        valueMap.put("0101", "5");
        valueMap.put("0110", "6");
        valueMap.put("0111", "7");
        valueMap.put("1000", "8");
        valueMap.put("1001", "9");
        valueMap.put("1010", "A");
        valueMap.put("1011", "B");
        valueMap.put("1100", "C");
        valueMap.put("1101", "D");
        valueMap.put("1110", "E");
        valueMap.put("1111", "F");
        //add 0 digit to the left to make the number of digits divisible by 16
        while (input.length() % 4 != 0) {
            input = "0" + input;
        }
        String result = "";
        //group each 4 digits and map them with the value in the map
        for (int i = 0; i < input.length(); i += 4) {
            result += valueMap.get(input.substring(i, i + 4));
        }
        return result;
    }

    private String convertDecToBin(String input) {
        BigInteger valueInput = new BigInteger(input);
        BigInteger binBase = new BigInteger("2");
        BigInteger zero = new BigInteger("0");
        String binDigits = "01";
        StringBuilder result = new StringBuilder();
        //add remainder to the result until the division yield 0
        do {
            BigInteger remain = valueInput.remainder(binBase);
            char binDigit = binDigits.charAt(remain.intValue());
            //if the division has result different than 0, add the remainder to the right of result and divide the input
            if (!valueInput.equals(zero)) {
                result.append(binDigit);
                valueInput = valueInput.divide(binBase);
            } else {
                break;
            }

        } while (true);
        return result.reverse().toString();
    }

    private String convertDecToHex(String input) {
        BigInteger valueInput = new BigInteger(input);
        BigInteger hexBase = new BigInteger("16");
        BigInteger zero = new BigInteger("0");
        String hexDigits = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        //add remainder to the result until the division yield 0
        do {
            BigInteger remain = valueInput.remainder(hexBase);
            char hexDigit = hexDigits.charAt(remain.intValue());
            //if the division has result different than 0, add the remainder to the right of result and divide the input
            if (!valueInput.equals(zero)) {
                result.append(hexDigit);
                valueInput = valueInput.divide(hexBase);
            } else {
                break;
            }
        } while (true);
        return result.reverse().toString();
    }

    private String convertHexToBin(String input) {
        HashMap<String, String> valueMap = new HashMap<>();
        valueMap.put("0", "0000");
        valueMap.put("1", "0001");
        valueMap.put("2", "0010");
        valueMap.put("3", "0011");
        valueMap.put("4", "0100");
        valueMap.put("5", "0101");
        valueMap.put("6", "0110");
        valueMap.put("7", "0111");
        valueMap.put("8", "1000");
        valueMap.put("9", "1001");
        valueMap.put("A", "1010");
        valueMap.put("B", "1011");
        valueMap.put("C", "1100");
        valueMap.put("D", "1101");
        valueMap.put("E", "1110");
        valueMap.put("F", "1111");
        String result = "";
        //map each hexadecimal digits to binary value from left to right
        for (int i = 0; i < input.length(); i++) {
            result += valueMap.get(Character.toString(input.charAt(i)));    
        }
        return result;
    }

    private String convertHexToDec(String input) {
        BigInteger hexBase = new BigInteger("16");
        BigInteger decBase = new BigInteger("10");
        BigInteger valueOutput = new BigInteger("0");
        String hexDigits = "0123456789ABCDEF";
        //for each digit in input: output add digit*16^(reversed index)
        for (int i = 0; i < input.length(); i++) {
            BigInteger digit = new BigInteger(Integer.toString(
                    hexDigits.indexOf(Character.toString(input.charAt(i)))));
            valueOutput = valueOutput.add(digit.multiply(hexBase.pow(input.length() - 1 - i)));
        }
        return valueOutput.toString();
    }
}
