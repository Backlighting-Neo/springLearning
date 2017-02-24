package com.xiaohongchun.xcx.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Neo on 17/2/23.
 */

public class Utils {
    public static void log(String itemKey, String itemValue) {
        System.out.print("===================== "+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())+" =====================\n");
        System.out.print("["+itemKey+"]    "+itemValue+"\n");
    }

    public static byte[] byteConcat(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}
