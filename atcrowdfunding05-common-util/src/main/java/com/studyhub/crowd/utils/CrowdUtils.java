package com.studyhub.crowd.utils;

import com.studyhub.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author haoren
 * @create 2020-12-01 12:45
 */
public class CrowdUtils {
    /**
     * 判断当前请求是否位ajax请求 如果是 返回true
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String acceptInfo = request.getHeader("Accept");
        String xRequestInfo = request.getHeader("X-Request-With");

        return (acceptInfo != null && acceptInfo.contains("application/json")) || (xRequestInfo != null && "XMLHttpRequest".equals(xRequestInfo));
    }

    /**
     * 对明文字符串进行MD5加密
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        //1.判断source是否有效
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        //3.获取MessageDigest对象
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            //获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            //执行加密
            byte[] output = messageDigest.digest(input);

            //创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            //按照16进制将bigInteger转换位字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
