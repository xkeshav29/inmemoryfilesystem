package com.myapp.service;

import com.myapp.model.Block;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BlockService {

    private static final int BLOCK_SIZE = 10;

    private static final int NUM_BYTES = 100000;

    private static final byte[] memory = new byte[NUM_BYTES];

    private BlockManager blockManager;

    public BlockService(){
        this.blockManager = new BlockManager(BLOCK_SIZE, NUM_BYTES);
    }

    Block createBlocks(String content) {
        List<byte[]> contentBlocks = Arrays.stream(content.split("(?<=\\G.{10})"))
                .map(String::getBytes)
                .collect(Collectors.toList());
        Iterator<byte[]> iterator = contentBlocks.iterator();
        Block firstBlock = blockManager.getAvailableBlock();
        Block prevBlock = firstBlock;
        while(iterator.hasNext()) {
            Block nextBlock = blockManager.getAvailableBlock();
            memCopy(prevBlock.getStart(), iterator.next());
            prevBlock.setNext(nextBlock);
            prevBlock = nextBlock;
        }
        prevBlock.setNext(null);
        return firstBlock;
    }

    public String getContent(Block start) {
        StringBuilder content = new StringBuilder();
        while(start.getNext()!=null) {
           content.append(getString(start.getStart()));
           start = start.getNext();
        }
        return new String(content);
    }

    public void removeContent(Block start) {

    }

    public void updateBlocks(Block start, String content) {

    }

    private void memCopy(int startIndex, byte[] content) {
        IntStream.range(0, content.length)
                .forEach(index -> memory[startIndex + index] = content[index]);
    }

    private String getString(int startIndex) {
        byte[] content = new byte[10];
        for(int k = 0, i = startIndex; i < 10 + startIndex; i++) {
            content[k++] = memory[i];
        }
        return new String(content);
    }
}
