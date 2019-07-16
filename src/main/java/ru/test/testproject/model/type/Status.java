package ru.test.testproject.model.type;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {
    CREATED(1, "created"),
    RUNNING(2, "running"),
    FINISHED(3, "finished"),
    NOT_FOUND(4, "not found");

    private final int value;
    private final String name;
    private static final Map<Integer, Status> CODE_MAP = new LinkedHashMap<>();
    
    static {
        for(Status status : values()) {
            CODE_MAP.put(status.getValue(), status);
        }
    }

    Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    
    public static Status getByValue(Integer value) {
        return CODE_MAP.get(value);
    }

    @Override
    public String toString() {
        return name;
    }
}
