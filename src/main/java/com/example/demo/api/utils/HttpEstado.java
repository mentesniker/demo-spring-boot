package com.example.demo.api.utils;

public enum HttpEstado {

    OK(200, "OK"),


    BAD_REQUEST(400, "Bad Request"),

    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented");
   

    private final int codigo;

    private final String causa;


    HttpEstado(int codigo, String causa) {
        this.codigo = codigo;
        this.causa = causa;
    }

    public int codigo() {
        return this.codigo;
    }

    public String getCausa() {
        return this.causa;
    }


}
