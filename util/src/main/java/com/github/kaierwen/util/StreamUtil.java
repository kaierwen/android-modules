package com.github.kaierwen.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 数据流转换工具类
 *
 * @author zhangky@chinasunfun.com
 * @see @author chenjl 2015-04-02
 * @since 2017/5/18
 */
public class StreamUtil {

    private static final String TAG = "StreamUtil";

    /**
     * 输入流转换字符串
     *
     * @param inputStream inputStream
     * @return String String
     */
    public static String inputStreamToString(InputStream inputStream) {
        return inputStreamToString(inputStream, "UTF-8");
    }

    /**
     * 输入流转换字符串
     *
     * @param inputStream inputStream
     * @param encoding    编码格式
     * @return String input stream into string
     */
    public static String inputStreamToString(InputStream inputStream, String encoding) {
        if (inputStream == null) return null;

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));

            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStream);
        }
        return sb.toString();
    }


    /**
     * 输入流转换二进制数据
     *
     * @param inputStream inputStream
     * @return 字节数组
     * @throws Exception ex
     */
    public static byte[] inputStreamToByteArray(InputStream inputStream) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;

        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        closeStream(inputStream);
        closeStream(outputStream);
        return outputStream.toByteArray();
    }

    /**
     * 输入流转二进制流
     *
     * @param inputStream inputStream
     * @return 字节数组
     * @throws Exception ex
     */
    public static byte[] inputStreamToByte(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int ch;

        while ((ch = inputStream.read()) != -1) {
            byteStream.write(ch);
        }

        byte byteData[] = byteStream.toByteArray();

        closeStream(byteStream);

        return byteData;
    }

    /**
     * 流中读取指定大小的数据
     *
     * @param ip        inputStream
     * @param nTotalLen 长度
     * @return byte[]
     * @throws IOException ex
     */
    public static byte[] inputStreamToByte(InputStream ip, int nTotalLen) throws IOException {
        if (ip == null || nTotalLen <= 0) {
            return null;
        }

        byte[] buffer = new byte[nTotalLen];
        int nIdx = 0;
        int nReadLen = 0;

        while (nIdx < nTotalLen) {
            nReadLen = ip.read(buffer, nIdx, nTotalLen - nIdx);

            if (nReadLen > 0) {
                nIdx = nIdx + nReadLen;
            } else {
                break;
            }
        }
        closeStream(ip);
        return buffer;
    }

    /**
     * 关闭流
     *
     * @param stream stream
     */
    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                Log.e(TAG, "Could not close stream", e);
            }
        }
    }
}
