package com.test.common.utils;

import java.util.Random;

public class PasswordUtils {
   public static final String[] LOWER_CASES = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
   public static final String[] UPPER_CASES = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
   public static final String[] NUMS_LIST = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
   public static final String[] SYMBOLS_ARRAY = new String[]{"!", "~", "^", "_", "*"};

   public static void main(String[] args) {
      System.out.println(genRandomPwd(12));
   }

   public static String genRandomPwd(int pwd_len) {
      if (pwd_len >= 6 && pwd_len <= 20) {
         int lower = pwd_len / 2;
         int upper = (pwd_len - lower) / 2;
         int num = (pwd_len - lower) / 2;
         int symbol = pwd_len - lower - upper - num;
         StringBuffer pwd = new StringBuffer();
         Random random = new Random();

         for(int position = 0; lower + upper + num + symbol > 0; System.out.println(pwd.toString())) {
            if (lower > 0) {
               position = random.nextInt(pwd.length() + 1);
               pwd.insert(position, LOWER_CASES[random.nextInt(LOWER_CASES.length)]);
               --lower;
            }

            if (upper > 0) {
               position = random.nextInt(pwd.length() + 1);
               pwd.insert(position, UPPER_CASES[random.nextInt(UPPER_CASES.length)]);
               --upper;
            }

            if (num > 0) {
               position = random.nextInt(pwd.length() + 1);
               pwd.insert(position, NUMS_LIST[random.nextInt(NUMS_LIST.length)]);
               --num;
            }

            if (symbol > 0) {
               position = random.nextInt(pwd.length() + 1);
               pwd.insert(position, SYMBOLS_ARRAY[random.nextInt(SYMBOLS_ARRAY.length)]);
               --symbol;
            }
         }

         return pwd.toString();
      } else {
         return "";
      }
   }
}
