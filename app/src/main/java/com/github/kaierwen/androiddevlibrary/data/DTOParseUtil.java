package com.github.kaierwen.androiddevlibrary.data;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/5/18
 */
public class DTOParseUtil {

    public static DTO parseDTO(InputStream inputStream) {
        DTO dto = new DTO();
        return dto;
    }

    private void parse(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void parseData(JsonReader reader) {
        if (reader == null) return;
        try {
            reader.beginObject();
            while (reader.hasNext()){

            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
