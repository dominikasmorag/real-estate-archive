package web.realestate;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ResultWithMetaData {
    private int id;
    private String title;
    private String location;
    private BigDecimal price;
    private int rooms;
    private float squareMeters;
    private String image;
    private int duration;
    private Timestamp timestamp;

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setLocation(String location) { this.location = location; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setRooms(int rooms) { this.rooms = rooms; }
    public void setSquareMeters(float squareMeters) { this.squareMeters = squareMeters; }
    public void setImage(String image) { this.image = image; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public int getId() { return id; }
    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getRooms() {
        return rooms;
    }
    public float getSquareMeters() {
        return squareMeters;
    }
    public String getImage() {
        return image;
    }
    public int getDuration() { return duration; }
    public Timestamp getTimestamp() { return timestamp; }

    public String toString() {
        return "Id: " + id + "\nTitle: " + title + "\nLocation: " + location + "\nPrice: " + price + "\nRooms: " + rooms + "\nSquare meters: " + squareMeters + "\nImage: " + image + "\nDuration: " + duration + "\nDate: " + timestamp  + "\n====";
    }
}
