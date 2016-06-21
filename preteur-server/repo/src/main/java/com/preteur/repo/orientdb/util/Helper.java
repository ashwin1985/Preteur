package com.preteur.repo.orientdb.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preteur.repo.orientdb.api.impl.Preteur;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.model.User;

import java.util.Map;

public class Helper {
  
   public static Map convertObjectToMap(Object bean) {
     ObjectMapper m = new ObjectMapper();
     m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
     return m.convertValue(bean, Map.class);
   }
   
}
