package com.wx.main.Enum;

public enum StudentLevel {
    LEVEL_1(2,"初级");

    private Integer value;

    private String message;

    StudentLevel(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
