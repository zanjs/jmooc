package me.jcala.jmooc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jcala.jmooc.entity.auxiliary.UpFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum  JsonUtils {
    instance;

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object obj){
        String str= "";
        try {
            str=mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return str;
    }

    public List<UpFile> readJsonToFileList(String json) {
        List<UpFile> list;
        try {
            list=mapper.readValue(json, new TypeReference<List<UpFile>>() {});
        } catch (IOException e) {
            list=new ArrayList<>();
            logger.warn("UpFile list反序列化出现错误:"+e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

}