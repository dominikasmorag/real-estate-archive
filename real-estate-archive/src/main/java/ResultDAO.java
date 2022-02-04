import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        this.connection = connection;
        this.selectAllStatement = connection.prepareStatement("SELECT * FROM RESULTS WHERE LOCATION LIKE '%Ceglana%';");

    }
}
