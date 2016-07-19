package com.preteur.repo.orientdb.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preteur.repo.orientdb.api.impl.Preteur;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;

import java.util.Map;

public class Helper {
  
   public static Map convertObjectToMap(Object bean) {
     ObjectMapper m = new ObjectMapper();
     m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
     return m.convertValue(bean, Map.class);
   }

    public static void main(String args[]) {
//        Preteur p = new Preteur();
//
//        User u = new User();
//        u.setFristName("apar");
//        u.setLastName("nath");
//        u.setEmailAddress("apar.nath@gmail.com");
//        u.setCompany("student");
//        u.setDob("1970-01-01");
//        u.setPhone("5865885553");
//        u.setIpAddress("127.0.0.1");
//        u.setAddress1("address1");
//        u.setCity("plymouth");
//        u.setState("MI");
//        u.setPostalCode("55441");
//
//        UserDto dto = new UserDto(u,"5456","222222226","223433212","checking","bofa");
////
//        p.createUser(dto);


//        Result<Boolean> r = p.addBondBetweenUsers("sandhya.palanki@gmail.com", "pat.sharkey@gmail.com", 1);
//
//        if(r.isStatus()) {
//            System.out.println("Transfer Complete");
//        } else {
//            System.out.println(r.getFailureReason());
//        }
    }
   
}
