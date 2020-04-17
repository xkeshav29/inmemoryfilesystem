package com.myapp.service;

import com.myapp.model.File;

public interface FileSystem {

    File create(String name, String content);

    String read(String name);

    void update(String name, String content);

    void delete(String name);
}
