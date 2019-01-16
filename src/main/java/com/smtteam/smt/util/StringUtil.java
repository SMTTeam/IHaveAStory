package com.smtteam.smt.util;

import com.smtteam.smt.common.bean.Constants;

import java.util.Random;

/**
 * 类说明：字符串工具类
 * 创建者：Zeros
 * 创建时间：2019-01-02 16:37
 * 包名：com.smtteam.smt.util
 */

public class StringUtil {

    /**
     * 密文加密和解析字典，必须private，可以根据需要打乱这些字符的顺序，打乱后，可以得到不同的密码，最好按需打乱
     */
    private static final char[] array = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g',
            'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '8', '5', '2', '7', '3', '6', '4', '0', '9', '1',
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
            'C', 'V', 'B', 'N', 'M', '+', '-' };

    private static Random random = new Random();

    /**
     * 获取盐
     * @return
     */
    public static String getSalt() {
        Integer length = Constants.SALT_LENGTH;
        return getRandomString(length);
    }

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    private static String getRandomString(int length){
       //Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(array.length);
            sb.append(array[number]);
        }
        return sb.toString();
    }


}
