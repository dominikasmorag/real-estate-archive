import java.math.BigDecimal;
import java.util.Date;

public class Result {
    Date date;
    String title;
    String location;
    BigDecimal price;
    int rooms;
    float squareMeters;
    String image;
    long duration;
    static int id;

    void setTitle(String title) { this.title = title; }
    void setLocation(String location) { this.location = location; }
    void setPrice(BigDecimal price) { this.price = price; }
    void setRooms(int rooms) { this.rooms = rooms; }
    void setSquareMeters(float squareMeters) { this.squareMeters = squareMeters; }
    void setImage(String image) { this.image = image; }
    void setDate(Date date) { this.date = date; }
    void setDuration(long duration) { this.duration = duration; }
    void setId(int id) { this.id = id; }

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

    Date getDate() {
        return date;
    }

    public String toString() {
        return "Id: " + id + "\nTitle: " + title + "\nLocation: " + location + "\nPrice: " + price + "\nRooms: " + rooms + "\nSquare meters: " + squareMeters + "\nImage: " + image + "\nDate: " + date +"\nDuration[ms]: " + duration +"\n====";
    }
}
