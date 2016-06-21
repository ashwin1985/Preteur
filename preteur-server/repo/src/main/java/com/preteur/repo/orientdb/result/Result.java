package com.preteur.repo.orientdb.result;

public class Result<T> {

    private boolean status;
    private String failureReason;
    private Exception e;
    private T result;

    private Result(boolean status, String failureReason, Exception e, T result) {
        this.status = status;
        this.failureReason = failureReason;
        this.e = e;
        this.result = result;
    }

    public static <T> Result<T> success(T result) {
        return new Result<T>(true,null,null,result);
    }

    public static <T> Result<T> failure(String failureReason) {
        return new Result<T>(false,failureReason,null,null);
    }

    public static <T> Result<T> failure(Exception e) {
        return new Result<T>(false,e.toString(),e,null);
    }
}
