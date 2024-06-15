package com.memorizei.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JsonUtils {

    public static String toJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("Erro ao converter o objeto para json");
            throw new RuntimeException("Erro ao converter o objeto para json");
        }
    }

}
