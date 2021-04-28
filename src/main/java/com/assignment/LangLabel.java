package com.assignment;

public enum LangLabel {

    DE("tyska"),
    EN("engelska"),
    ES("spanska"),
    FI("finska"),
    FR("franska"),
    IT("italienska"),
    NO("norska"),
    SV("svenska");

    private String name;

    LangLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
