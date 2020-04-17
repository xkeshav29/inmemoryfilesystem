package com.myapp.model;

public class Block {
    int start;
    Block next;

    public Block(int start, Block next) {
        this.start = start;
        this.next = next;
    }

    public int getStart() {
        return start;
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }
}
