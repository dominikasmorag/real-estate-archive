import java.sql.*;

public class ResultDAO {

    PreparedStatement insertStatement;
    Connection connection;


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

    public void saveResultToFile(ResultWithMetaData result) throws SQLException {
        insertStatement.setString(1, result.getTitle());
        insertStatement.setString(2, result.getLocation());
        insertStatement.setBigDecimal(3, result.getPrice());
        insertStatement.setInt(4, result.getRooms());
        insertStatement.setFloat(5, result.getSquareMeters());
        insertStatement.setString(6, result.getImage());
        insertStatement.setInt(7, result.getDuration());
        insertStatement.setTimestamp(8, result.getTimestamp());
        insertStatement.executeUpdate();

    }

}
