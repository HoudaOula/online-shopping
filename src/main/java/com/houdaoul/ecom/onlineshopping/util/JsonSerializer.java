package com.houdaoul.ecom.onlineshopping.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonSerializer {
    private final ObjectMapper mapper;

    public JsonSerializer() {
        mapper = new ObjectMapper();
    }

    public String serialize(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T deserialize(String src, Class<T> c) {
        try {
            return mapper.readerFor(c).readValue(src);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
