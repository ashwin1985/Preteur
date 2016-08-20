package com.preteur.server.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.preteur.repo.orientdb.util.Helper;

public class ResponseBody<T> {
    T result;

    public ResponseBody(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public String getContent() throws JsonProcessingException {
        return Helper.convertObjectToJson(this);
    }
}
