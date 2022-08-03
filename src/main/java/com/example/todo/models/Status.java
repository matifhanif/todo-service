package com.example.todo.models;

public enum Status {
    NOT_DONE (1),
    DONE(2),
    PAST_DUE(3);

    private int statusCode;

    Status(int sCode) {
        this.statusCode = sCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
