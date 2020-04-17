package com.myapp.service;

import com.myapp.model.Block;
import com.myapp.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InMemoryFileSystem implements FileSystem {

    Map<String, Block> files = new HashMap<>();

    private BlockService blockService;

    @Autowired
    public InMemoryFileSystem(BlockService blockService) {
        this.blockService = blockService;
    }

    @Override
    public File create(String name, String content) {
        Block startBlock = blockService.createBlocks(content);
        files.put(name, startBlock);
        return new File(name, startBlock);
    }

    @Override
    public String read(String name) {
        Block startBlock = files.get(name);
        return blockService.getContent(startBlock);
    }

    @Override
    public void update(String name, String content) {
        Block startBlock = files.get(name);
        blockService.updateBlocks(startBlock, content);
    }

    @Override
    public void delete(String name) {
        Block startBlock = files.get(name);
        blockService.removeContent(startBlock);
    }
}
