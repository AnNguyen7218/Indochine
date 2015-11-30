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
 * BookedRooms generated by hbm2java
 */
@Entity
@Table(name="BookedRooms"
    ,schema="dbo"
    ,catalog="Hotel"
)
public class BookedRooms  implements java.io.Serializable {


     private Integer id;
     private BookingRoom bookingRoom;
     private Rooms rooms;

    public BookedRooms() {
    }

    public BookedRooms(BookingRoom bookingRoom, Rooms rooms) {
       this.bookingRoom = bookingRoom;
       this.rooms = rooms;
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
    @JoinColumn(name="BookingID")
    public BookingRoom getBookingRoom() {
        return this.bookingRoom;
    }
    
    public void setBookingRoom(BookingRoom bookingRoom) {
        this.bookingRoom = bookingRoom;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RoomID")
    public Rooms getRooms() {
        return this.rooms;
    }
    
    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }




}

