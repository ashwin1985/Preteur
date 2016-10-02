package com.preteur.server.dto;

public class ResponseBody<T> {
    T result;

    public ResponseBody(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

//    public String getContent() throws JsonProcessingException {
//        return Helper.convertObjectToJson(this);
//    }
}
