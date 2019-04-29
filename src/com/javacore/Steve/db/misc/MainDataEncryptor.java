package com.javacore.Steve.db.misc;

import com.javacore.Steve.MainApplication;
import com.javacore.Steve.db.DBApplication;

public class MainDataEncryptor implements DataEncryptor {
    @Override
    public String encrypt(String text) {
        if (DBApplication.DATA_ENCRYPTION_LEVEL.equals("LOW")){
            return encryptLOW(text);
        }
        return null;
    }

    private String encryptLOW(String text) {
        //to hex
        StringBuilder buf = new StringBuilder(200);
        for (char symbol: text.toCharArray()) {
            if (buf.length() > 0)
                buf.append(' ');
                buf.append(String.format("%02x", (int) symbol));
        }
        return buf.toString();
    }

    private String encryptMED(String text, int number) {
        //to caesar
        String newString = "";
        int carr[] = new int[text.length()];
        for(int i = 0;i<text.length();i++){
            if((int)text.charAt(i)>=65 && (int)text.charAt(i)<=90){
                carr[i] = ((int)text.charAt(i));
                if((carr[i]+(number%26))>90){
                    newString+=(char)(carr[i]-90+64+(number%26));
                }
                else if((carr[i]+(number%26))<=90){
                    newString+=(char)(carr[i]+(number%26));
                }}
            if((int)text.charAt(i)>=97 && (int)text.charAt(i)<=122){
                carr[i] = ((int)text.charAt(i));
                if((carr[i]+(number%26))>122){
                    newString+=(char)(carr[i]-122+96+(number%26));
                }
                else if((carr[i]+(number%26))<=122){
                    newString+=(char)(carr[i]+(number%26));
                }}
            if((int)text.charAt(i)>=91&&(int)text.charAt(i)<=96){
                carr[i] = (int)text.charAt(i);
                newString+=(char)(carr[i]);
            }
            if((int)text.charAt(i)<65 || (int)text.charAt(i)>122){
                carr[i] = (int)text.charAt(i);
                newString+=(char)(carr[i]);
            }
        }
        return newString;
    }

    private String encryptHIGH(String text) {
        return null;
    }

}