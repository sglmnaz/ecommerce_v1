package com.synclab.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "image")
public class Image implements Serializable {

    private static final Long serialVersionUID = 1L;

    // fields

    @Id
    private String id;
    @JsonIgnore
    @Column(name = "file", columnDefinition = "BLOB")
    private byte[] file;
    private String fileName;
    private String mimetype;
    private Long fileSize;
    
    //methods

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getImageId() {
        return id;
    }

    public void setImageId(String imageId) {
        this.id = imageId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
