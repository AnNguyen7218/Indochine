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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Suppliers generated by hbm2java
 */
@Entity
@Table(name="Suppliers"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class Suppliers  implements java.io.Serializable {


     private Integer supplierId;
     private String supplierName;
     private Boolean isActive;
     private Set<OrderForSupplier> orderForSuppliers = new HashSet<OrderForSupplier>(0);
     private Set<Products> productses = new HashSet<Products>(0);

    public Suppliers() {
    }

    public Suppliers(String supplierName, Boolean isActive, Set<OrderForSupplier> orderForSuppliers, Set<Products> productses) {
       this.supplierName = supplierName;
       this.isActive = isActive;
       this.orderForSuppliers = orderForSuppliers;
       this.productses = productses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="SupplierID", unique=true, nullable=false)
    public Integer getSupplierId() {
        return this.supplierId;
    }
    
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    
    @Column(name="SupplierName")
    public String getSupplierName() {
        return this.supplierName;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    
    @Column(name="isActive")
    public Boolean getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="suppliers")
    public Set<OrderForSupplier> getOrderForSuppliers() {
        return this.orderForSuppliers;
    }
    
    public void setOrderForSuppliers(Set<OrderForSupplier> orderForSuppliers) {
        this.orderForSuppliers = orderForSuppliers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="suppliers")
    public Set<Products> getProductses() {
        return this.productses;
    }
    
    public void setProductses(Set<Products> productses) {
        this.productses = productses;
    }




}

