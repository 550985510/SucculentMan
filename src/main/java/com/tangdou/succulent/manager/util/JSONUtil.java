package com.tangdou.succulent.manager.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author 木叶丸
 * @date 2018/2/6
 */
public class JSONUtil {

    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ObjectMapper mapper = new ObjectMapper();

    private JSONUtil() {
    }

    public static ObjectMapper jsonMapperConfig() {
        return mapper;
    }

    public static <T> T fromJson(String jsonStr, Class<T> valueType) throws IOException {
        return mapper.readValue(jsonStr, valueType);
    }

    public static <T> T fromJson(String jsonStr, TypeReference<T> valueType) throws IOException {
        return mapper.readValue(jsonStr, valueType);
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    static {
        mapper.setDateFormat(DEFAULT_DATE_FORMAT);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
