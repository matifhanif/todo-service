package com.example.todo.models;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    NOT_DONE (0),
    DONE(1),
    PAST_DUE(2);

    private int statusCode;
    private static Map map = new HashMap<>();

    private Status(int sCode) {
        this.statusCode = sCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
    static {
        for (Status s : Status.values()) {
            map.put(s.statusCode, s);
        }
    }

    public static Status valueOf(int statusCode) {
        return (Status) map.get(statusCode);
    }


}
