import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;

public class ResultDAO {

    PreparedStatement insertStatement;
    PreparedStatement selectAllStatement;
    Connection connection;
    private final int TITLE = 1;
    private final int LOCATION = 2;
    private final int ROOMS = 3;
    private final int SQUARE_METERS = 4;
    private final int IMAGE = 5;
    private final int DURATION = 6;
    private final int DATE = 7;

    public ResultDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.insertStatement = connection.prepareStatement("INSERT INTO RESULTS(" +
                "TITLE, " +
                "LOCATION, " +
                "PRICE, " +
                "ROOMS, " +
                "SQUAREMETERS, " +
                "IMAGE, " +
                "DURATION, " +
                "CREATED) " +
                "VALUES(?,?,?,?,?,?,?,?);");
    }

    public void saveResult(Result result) throws SQLException {
        insertStatement.setString(1, result.getTitle());
        insertStatement.setString(2, result.getLocation());
        insertStatement.setBigDecimal(3, result.getPrice());
        insertStatement.setInt(4, result.getRooms());
        insertStatement.setFloat(5, result.getSquareMeters());
        insertStatement.setString(6, result.getImage());
        insertStatement.setInt(7, result.getDuration());
        insertStatement.setString(8, result.getDateString());
        insertStatement.executeUpdate();

    }
    public void selectAll(Connection connection) throws SQLException, FileNotFoundException {
        String query = "SELECT * FROM RESULTS;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                String title = rs.getString(1);
                String location = rs.getString(2);
                BigDecimal price = rs.getBigDecimal(3);
                int rooms = rs.getInt(4);
                float sqm = rs.getFloat(5);
                String image = rs.getString(6);
                int duration = rs.getInt(7);
                String date = rs.getString(8);

                System.out.println(title + " " + location + " " + price + " " + rooms + " " + sqm + " " + image + " " + duration + " " + date + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
