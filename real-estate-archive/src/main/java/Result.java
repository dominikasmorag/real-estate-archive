import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {

    Date dateNow = new Date();
    String title;
    String location;
    BigDecimal price;
    int rooms;
    float squareMeters;
    String image;
    int duration;
    static int id;
    Date date = new Date();
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String dateString = formatDate.format(date);

    void setTitle(String title) { this.title = title; }
    void setLocation(String location) { this.location = location; }
    void setPrice(BigDecimal price) { this.price = price; }
    void setRooms(int rooms) { this.rooms = rooms; }
    void setSquareMeters(float squareMeters) { this.squareMeters = squareMeters; }
    void setImage(String image) { this.image = image; }
    void setDate(String dateString) { this.dateString = dateString; }
    void setDuration(int duration) { this.duration = duration; }
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
    String getDateString() { return dateString; }
    int getDuration() { return duration; }


    public String toString() {
        return "Id: " + id + "\nTitle: " + title + "\nLocation: " + location + "\nPrice: " + price + "\nRooms: " + rooms + "\nSquare meters: " + squareMeters + "\nImage: " + image + "\nDate: " + date +"\nDuration[ms]: " + duration +"\n====";
    }
}
