package algorithms.Cryptography;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Motivated by Khan Academy and Edx online learning platform course,
 * I thought it would be fun to code the ceasar cipher
 * @author Nguyen
 */
public class CeasarCipher {
    /**
     * A function for shifting alphabet
     * @param shift: shifting value
     * @return an alphabet which is shifted forwards (backwards if negative)
     * by the given value from the original alphabet
     */
    private static String shiftAlp(int shift) {
        StringBuilder result = new StringBuilder();
        if (shift > 26) {
            shift = shift % 26;
        }
        else if (shift < 0) {
            shift = 26 + shift % 26;
        }
        int start = (int) 'A';

        for (int i = shift; i <= (int) 'Z' - 'A' + shift; i++) {
            int val = (start + i);
            if (val > (int) 'Z') {
                val = (int) 'A' + val % (int) 'Z' - 1;
            }
            result.append((char) val);
        }
        return result.toString();
    }

    /**
     * Remove all punctuation
     * Capitalize all letters
     * @param str: given String
     * @return a string, contains only letters, all capitalized
     */
    private static String normalizeText(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            int curVal = (int) current;
            if (curVal >= 65 && curVal < 65 + 26) {
                result.append("").append(current);
            }
            else if (curVal >= 97 && curVal < 97 + 26) {
                String cur = current + "";
                cur = cur.toUpperCase();
                result.append(cur);
            }
        }
        return result.toString();
    }

    /**
     * Condition: the String given must be normalized
     * @param str: the given String to be encrypted
     * @param key: this is the shifting value
     * @return a String being shifted by the given key value
     */
    private static String caesarify(String str, int key) {
        String original = shiftAlp(0);
        String shifted = shiftAlp(key);

        Map<Character, String> dict = new HashMap<>();

        for (int i = 0; i < original.length(); i++) {
            Character sub1 = original.charAt(i);
            String sub2 = "" + shifted.charAt(i);
            dict.put(sub1, sub2);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Character sub = str.charAt(i);
            result.append(dict.get(sub));
        }

        return result.toString();
    }

    /**
     * @param str: given String
     * @param num: number of character per group
     * @return a String consists of the input String broken into groups
     * with the number of letters specified
     * Rule: if there are not enough letters in the input String
     * to fill out all the groups, add 'x's to complete.
     * Condition: num > 0, str != null
     */
    private static String groupify(String str, int num) {
        if (str.length() <= num) {
            int xToAdd = num - str.length();
            StringBuilder result = new StringBuilder(str);
            for (int i = 0; i < xToAdd; i++) {
                result.append("x");
            }
            return result.toString();
        }
        else {
            return str.substring(0, num) + " " + groupify(str.substring(num), num);
        }
    }

    /**
     * De-groupify the encrypted message
     * @param str: encrypted message
     * @param num: group's size
     * @return the degroupified version of the encrypted message
     */
    private static String deGroupify(String str, int num) {
        return deGroupHelper(str.replaceAll(" ", ""), num);
    }

    /**
     * Recursive helper method for deGroupify
     * @preCondition: String given has no space
     * @param str: the encrypted message
     * @param num: group's size
     * @return the degroupified version of the encrypted message
     */
    private static String deGroupHelper(String str, int num) {
        if (str.length() <= num) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < str.length(); i ++) {
                if (str.substring(i, i + 1).equals("x")) break;
                result.append(str.substring(i, i + 1));
            }
            return result.toString();
        }
        else {
            return str.substring(0, num) + deGroupHelper(str.substring(num), num);
        }
    }

    /**
     * Encrypt the message:
     * Basically turning a meaningful message into a non-sense block of characters
     * @param str: given String to be encrypted
     * @param key: shifting value
     * @param size: group's size
     * @return the encrypted String
     */
    private static String encrypt(String str, int key, int size) {
        String result = str;
        result = normalizeText(result);
        result = caesarify(result, key);
        result = groupify(result, size);
        return result;
    }

    /**
     * Decrypt the encrypted message to get back to original message
     * @param str: the encrypted message in String
     * @param key: the key (shifting value)
     * @param size: group's size
     * @return the original message
     */
    private static String decrypt(String str, int key, int size){
        String result = str;
        result = deGroupify(result, size);
        result = caesarify(result, - key);
        return result;
    }

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        System.out.println("Hello, please enter your private message: ");

        String original = user.nextLine();
        String origin = original;

        origin = normalizeText(origin);
        System.out.println(origin.length() % 4);

        String encryptMessage = encrypt(original, 5, 4);
        System.out.println(encryptMessage);

        String decryptMessage = decrypt(encryptMessage, 5, 4);
        System.out.println(decryptMessage);
    }
}
