package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class TemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger cartId;

    // endregion

    // region getter and setters

    // endregion

}
