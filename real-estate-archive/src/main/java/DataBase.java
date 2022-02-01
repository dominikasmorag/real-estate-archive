import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

class DataBase {


    private static final String RESULTS_TABLE = "RESULTS";
    private static final String CREATE_RESULTS = "CREATE TABLE " + RESULTS_TABLE + " (" +
            "ID NUMBER AUTO_INCREMENT PRIMARY KEY," +
            "TITLE VARCHAR(200)," +
            "LOCATION VARCHAR(100)," +
            "PRICE DECIMAL(10)," +
            "ROOMS INT(5)," +
            "SQUAREMETERS DECIMAL(10)," +
            "IMAGE VARCHAR(500))";

    private static void createResultsTable(Connection conn) throws SQLException {
        PreparedStatement createResults = conn.prepareStatement(CREATE_RESULTS);
        createResults.execute();
    }

    public static void createSchema(Connection conn) throws SQLException {
        createResultsTable(conn);
    }

    public static void addValues(Connection conn, Result result) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO RESULTS(TITLE, LOCATION, PRICE, ROOMS, SQUAREMETERS, IMAGE) VALUES(?,?,?,?,?,?)");
        statement.setString(1, result.title);
        statement.setString(2, result.location);
        statement.setBigDecimal(3, result.price);
        statement.setInt(4, result.rooms);
        statement.setFloat(5, result.squareMeters);
        statement.setString(6, result.image);
        statement.executeUpdate();

    }
}

