package com.cuibg.shadowdrawable;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description :
 * created time: 2020/4/10 15:46
 * created by: cuibenguang
 */
public class L {
    public static String a(byte[] paramArrayOfbyte) {
        byte b = 0;
        char[] arrayOfChar = new char[16];
        arrayOfChar[0] = '0';
        arrayOfChar[1] = '1';
        arrayOfChar[2] = '2';
        arrayOfChar[3] = '3';
        arrayOfChar[4] = '4';
        arrayOfChar[5] = '5';
        arrayOfChar[6] = '6';
        arrayOfChar[7] = '7';
        arrayOfChar[8] = '8';
        arrayOfChar[9] = '9';
        arrayOfChar[10] = 'a';
        arrayOfChar[11] = 'b';
        arrayOfChar[12] = 'c';
        arrayOfChar[13] = 'd';
        arrayOfChar[14] = 'e';
        arrayOfChar[15] = 'f';
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(paramArrayOfbyte);
            paramArrayOfbyte = messageDigest.digest();
            char[] arrayOfChar1 = new char[32];
            int i = 0;
            while (b < 16) {
                byte b1 = paramArrayOfbyte[b];
                int j = i + 1;
                arrayOfChar1[i] = (char) arrayOfChar[b1 >>> 4 & 0xF];
                i = j + 1;
                arrayOfChar1[j] = (char) arrayOfChar[b1 & 0xF];
                b++;
            }
            return new String(arrayOfChar1);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
            noSuchAlgorithmException = null;
        }
        return "";
    }

    public static int a(String paramString) {
        char c = Character.MIN_VALUE;
        if (!TextUtils.isEmpty(paramString)) {
            c = paramString.charAt(0);
        }
        return c;
    }

}
