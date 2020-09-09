package com.synclab.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "templateEntities")
public class Temp implements Serializable {

    private static final long serialVersionUID = 1L;

    // region fields

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(name = "cart_id")
    private Long cartId;

    // endregion

    // region getter and setters

    // endregion

}
