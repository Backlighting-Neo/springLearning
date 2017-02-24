package com.xiaohongchun.xcx.service;


import com.xiaohongchun.xcx.utils.Utils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Neo on 17/2/22.
 */
@Service
public class QcloudSign {
    private String appSecret;
    private String appID;
    private String bucketName;
    private String secretID;

    private static final String MAC_NAME = "HmacSHA1";

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setSecretID(String secretID) {
        this.secretID = secretID;
    }

    public String getSign() {
        Long currentTimeStampInSecond = System.currentTimeMillis()/1000;
        String original = "a="+appID+"&b="+bucketName+"&k="+secretID+
                "&t="+(currentTimeStampInSecond)+"&e="+(currentTimeStampInSecond+1000);

        byte[] originalByte = original.getBytes();
        SecretKey secretKey = new SecretKeySpec(appSecret.getBytes(), MAC_NAME);
        Mac mac = null;
        try {
            mac = Mac.getInstance(MAC_NAME);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            mac.init(secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] tempBytes = mac.doFinal(originalByte);

        return Base64.encodeBase64String(Utils.byteConcat(tempBytes, originalByte));
    }



}
