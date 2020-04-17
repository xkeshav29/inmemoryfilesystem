package com.myapp.model;

public class File {
    String name;
    Block start;

    public File(String name, Block start) {
        this.name = name;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public Block getStart() {
        return start;
    }
}
