package com.preteur.repo.orientdb.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preteur.repo.orientdb.api.impl.Preteur;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helper {
  
   public static Map convertObjectToMap(Object bean) {
     ObjectMapper m = new ObjectMapper();
     m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
     return m.convertValue(bean, Map.class);
   }
    public static String convertObjectToJson(Object bean) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bean);
    }

    public static void main(String[] args) throws JsonProcessingException {
        List<String> list = new ArrayList<>();
        list.add("1");
        System.out.println(convertObjectToJson(list));
    }
   
}
