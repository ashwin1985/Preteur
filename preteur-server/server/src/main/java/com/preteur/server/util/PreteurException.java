package com.preteur.server.util;

public class PreteurException extends RuntimeException {
    public PreteurException() { super(); }
    public PreteurException(String message) { super(message); }
    public PreteurException(String message, Throwable cause) { super(message, cause); }
    public PreteurException(Throwable cause) { super(cause); }
}
