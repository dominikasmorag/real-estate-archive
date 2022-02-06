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

    void setId(int id) { this.id = id; }
    void setTitle(String title) { this.title = title; }
    void setLocation(String location) { this.location = location; }
    void setPrice(BigDecimal price) { this.price = price; }
    void setRooms(int rooms) { this.rooms = rooms; }
    void setSquareMeters(float squareMeters) { this.squareMeters = squareMeters; }
    void setImage(String image) { this.image = image; }
    void setDuration(int duration) { this.duration = duration; }
    void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }


    int getId() { return id; }
    String getTitle() {
        return title;
    }
    String getLocation() {
        return location;
    }
    BigDecimal getPrice() {
        return price;
    }
    int getRooms() {
        return rooms;
    }
    float getSquareMeters() {
        return squareMeters;
    }
    String getImage() {
        return image;
    }
    int getDuration() { return duration; }
    Timestamp getTimestamp() { return timestamp; }


    public String toString() {
        return "Title: " + title + "\nLocation: " + location + "\nPrice: " + price + "\nRooms: " + rooms + "\nSquare meters: " + squareMeters + "\nImage: " + image + "\nDuration: " + duration + "\nDate: " + timestamp  + "\n====";
    }
}
