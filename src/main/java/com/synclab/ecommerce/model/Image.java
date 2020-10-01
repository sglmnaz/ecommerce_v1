package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
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

    @Column(name = "file", columnDefinition="BLOB")
    private byte[] file;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mimetype")
    private String mimetype;

    @Column(name = "file_size")
    private Long fileSize;


}
