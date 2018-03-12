package com.cainew.util;

import java.util.Random;

/**
 * Created by YeCT on 2016/8/10.
 */
public class GetCode {

    public static String getFixLenthString(int strLength){
        Random rm = new Random();
        double pross = rm.nextDouble();//获得随机数
        String fixLenthString = String.valueOf(pross);// 将获得的获得随机数转化为字符串
        System.out.println(fixLenthString);
        return fixLenthString.substring(2,strLength + 2);//返回固定的长度的随机数
    }

    /**
     * 验证码检验是否过期
     *
     * @return
     */
    public static boolean isPastDue(String timeout, int minute) {
        boolean flag = false;
        if (timeout.length() == 0 || (System.currentTimeMillis() - Long.valueOf(timeout)) > minute * 60 * 1000) {
            flag = true;
        }
        return flag;
    }
}
