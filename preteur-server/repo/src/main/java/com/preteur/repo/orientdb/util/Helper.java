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
   
   public static void main(String ars[]) {
      Preteur p = new Preteur();

      User u = new User();
      u.setFristName("pat");
      u.setLastName("sharkey");
      u.setEmailAddress("pat.sharkey@gmail.com");
      u.setCompany("student");
       u.setDob("1970-01-01");
       u.setPhone("5865885553");
       u.setIpAddress("127.0.0.1");
       u.setAddress1("address1");
       u.setCity("plymouth");
       u.setState("MI");
       u.setPostalCode("55441");

       UserDto dto = new UserDto(u,"5456","222222226","223433212","checking","bofa");

       p.createUser(dto);
   }
   
}
