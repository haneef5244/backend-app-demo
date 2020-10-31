package com.berjaya.demoappapi.status;

public enum Status {
    SUCCESS("MSG001", "Successful"),
    INTERNAL_SERVER_ERROR("MSG002", "Internal server error");

    public String code;
    public String message;

    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
