package com.yyc.bot_tools.Util;

import java.util.Random;

public class JigUtil {
    public static String jigEmail(String email){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb1=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<5; ++i){
            //产生0-61的数字
            int number=random.nextInt(52);
            //将产生的数字通过length次承载到sb中
            sb1.append(str.charAt(number));
        }
        String sb2 = email.substring(email.indexOf("@"));
        String newEmail = String.valueOf(sb1) + sb2;
        return newEmail;
    }

    public static String jigPhone(String phone){
        //定义一个字符串（0-9）即10位；
        String str="0123456789";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb1=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<7; ++i){
            //产生0-61的数字
            int number=random.nextInt(10);
            //将产生的数字通过length次承载到sb中
            sb1.append(str.charAt(number));
        }
        String sb2 = phone.substring(0,3);
        String newPhone = sb2 + (sb1);
        return newPhone;
    }

    public static String jigAddress(String address){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb1=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<4; ++i){
            //产生0-61的数字
            int number=random.nextInt(52);
            //将产生的数字通过length次承载到sb中
            sb1.append(str.charAt(number));
        }
        String newAddress = String.valueOf(sb1) + ' ' + address;
        return newAddress;
    }
}
