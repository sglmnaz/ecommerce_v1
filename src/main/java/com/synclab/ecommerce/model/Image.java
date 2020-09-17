package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;

@Entity
@Table(name = "images")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "file")
    private SerialBlob file;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mimetype")
    private String mimetype;

    @Column(name = "file_size")
    private Long fileSize;

    @OneToOne(mappedBy = "image") 
    private Category category;

    @OneToOne(mappedBy = "image") 
    private Subcategory subCategory;

    // endregion

    // region getter and setters

    public Long getCartItemId() {
        return imageId;
    }

    public void setCartItemId(Long cartItemId) {
        this.imageId = cartItemId;
    }

    public SerialBlob getFile() {
        return file;
    }

    public void setFile(SerialBlob file) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Subcategory subCategory) {
        this.subCategory = subCategory;
    }

    // endregion

}
