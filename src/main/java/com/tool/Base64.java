package com.tool;

import sun.misc.BASE64Encoder;

public class Base64 {

    public static String encryptBASE64(String info) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(info.getBytes());
    }



}

