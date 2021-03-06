package entities;
// Generated Nov 19, 2015 12:30:29 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrderForSupplier generated by hbm2java
 */
@Entity
@Table(name="OrderForSupplier"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class OrderForSupplier  implements java.io.Serializable {


     private Integer id;
     private Accounts accounts;
     private Suppliers suppliers;
     private String dateOrder;
     private Set<OrderLineForSupplier> orderLineForSuppliers = new HashSet<OrderLineForSupplier>(0);

    public OrderForSupplier() {
    }

    public OrderForSupplier(Accounts accounts, Suppliers suppliers, String dateOrder, Set<OrderLineForSupplier> orderLineForSuppliers) {
       this.accounts = accounts;
       this.suppliers = suppliers;
       this.dateOrder = dateOrder;
       this.orderLineForSuppliers = orderLineForSuppliers;
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
    @JoinColumn(name="StaffID")
    public Accounts getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SupplierID")
    public Suppliers getSuppliers() {
        return this.suppliers;
    }
    
    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    
    @Column(name="DateOrder")
    public String getDateOrder() {
        return this.dateOrder;
    }
    
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="orderForSupplier")
    public Set<OrderLineForSupplier> getOrderLineForSuppliers() {
        return this.orderLineForSuppliers;
    }
    
    public void setOrderLineForSuppliers(Set<OrderLineForSupplier> orderLineForSuppliers) {
        this.orderLineForSuppliers = orderLineForSuppliers;
    }




}


