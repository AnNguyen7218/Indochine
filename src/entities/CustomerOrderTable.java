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
 * CustomerOrderTable generated by hbm2java
 */
@Entity
@Table(name="CustomerOrderTable"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class CustomerOrderTable  implements java.io.Serializable {


     private Integer id;
     private String customerName;
     private String customerNumber;
     private Set<OrderTable> orderTables = new HashSet<OrderTable>(0);

    public CustomerOrderTable() {
    }

    public CustomerOrderTable(String customerName, String customerNumber, Set<OrderTable> orderTables) {
       this.customerName = customerName;
       this.customerNumber = customerNumber;
       this.orderTables = orderTables;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="CustomerName")
    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    @Column(name="CustomerNumber", length=50)
    public String getCustomerNumber() {
        return this.customerNumber;
    }
    
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customerOrderTable")
    public Set<OrderTable> getOrderTables() {
        return this.orderTables;
    }
    
    public void setOrderTables(Set<OrderTable> orderTables) {
        this.orderTables = orderTables;
    }




}

