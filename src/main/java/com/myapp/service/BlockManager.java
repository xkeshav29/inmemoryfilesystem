package com.myapp.service;

import com.myapp.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class BlockManager {

    private int count;

    private int size;

    private List<Block> availableBlocks = new LinkedList<>();

    public BlockManager(int size, int numBytes) {
        this.count = numBytes / size;
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < count; i++)
            availableBlocks.add(new Block(i * size, null));
    }

    public Block getAvailableBlock() {
        if (availableBlocks.isEmpty())
            throw new RuntimeException("Memory full");
        Block block = availableBlocks.get(0);
        availableBlocks.remove(0);
        return block;
    }

    public void markAvailable(Block block) {
        block.setNext(null);
        availableBlocks.add(block);
    }

}
