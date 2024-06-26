package com.elleined.image_server_api.model.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {

    private String name;
    private byte[] bytes;

    public CustomMultipartFile(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }

    @Override
    public long getSize() {
        return bytes.length;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void transferTo(File destination) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(destination)) {
            fos.write(bytes);
        }
    }
}