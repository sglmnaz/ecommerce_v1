package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "images")
@JsonIgnoreProperties(value = { "file" })
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "file")
    private Blob file;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mimetype")
    private String mimetype;

    @Column(name = "file_size")
    private Long fileSize;


    // getter and setters

    public Long getCartItemId() {
        return imageId;
    }

    public void setCartItemId(Long cartItemId) {
        this.imageId = cartItemId;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

 
}
