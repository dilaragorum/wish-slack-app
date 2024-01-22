package com.example.wishslackapp.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public String objectToJson(Object object) {
        if (object != null && String.class.isAssignableFrom(object.getClass())) {
            return (String) object;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
