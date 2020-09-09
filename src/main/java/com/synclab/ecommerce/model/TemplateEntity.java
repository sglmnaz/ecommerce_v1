package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

public class TemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "_id")
    private Long _Id;

    // endregion

    // region getter and setters

    // endregion

}
