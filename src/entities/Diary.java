package entities;
// Generated Nov 19, 2015 12:30:29 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Diary generated by hbm2java
 */
@Entity
@Table(name="Diary"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class Diary  implements java.io.Serializable {


     private Integer diaryId;
     private Accounts accounts;
     private Date dateTime;
     private String description;

    public Diary() {
    }

    public Diary(Accounts accounts, Date dateTime, String description) {
       this.accounts = accounts;
       this.dateTime = dateTime;
       this.description = description;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="DiaryID", unique=true, nullable=false)
    public Integer getDiaryId() {
        return this.diaryId;
    }
    
    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AccountID")
    public Accounts getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DateTime", length=23)
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    
    @Column(name="Description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


