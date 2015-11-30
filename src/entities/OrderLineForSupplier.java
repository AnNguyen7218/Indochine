package entities;
// Generated Nov 19, 2015 12:30:29 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderLineForSupplier generated by hbm2java
 */
@Entity
@Table(name="OrderLineForSupplier"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class OrderLineForSupplier  implements java.io.Serializable {


     private Integer id;
     private OrderForSupplier orderForSupplier;
     private Integer productId;
     private Integer quantity;

    public OrderLineForSupplier() {
    }

    public OrderLineForSupplier(OrderForSupplier orderForSupplier, Integer productId, Integer quantity) {
       this.orderForSupplier = orderForSupplier;
       this.productId = productId;
       this.quantity = quantity;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OrderID")
    public OrderForSupplier getOrderForSupplier() {
        return this.orderForSupplier;
    }
    
    public void setOrderForSupplier(OrderForSupplier orderForSupplier) {
        this.orderForSupplier = orderForSupplier;
    }

    
    @Column(name="ProductID")
    public Integer getProductId() {
        return this.productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
    @Column(name="Quantity")
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}


