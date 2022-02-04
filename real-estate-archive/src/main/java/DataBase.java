import java.sql.*;

class DataBase {


    private static final String RESULTS_TABLE = "RESULTS";
    private static final String CREATE_RESULTS = "CREATE TABLE " + RESULTS_TABLE + " (" +
            "ID NUMBER AUTO_INCREMENT PRIMARY KEY," +
            "TITLE VARCHAR(200)," +
            "LOCATION VARCHAR(100)," +
            "PRICE DECIMAL(10)," +
            "ROOMS INT(5)," +
            "SQUAREMETERS DECIMAL(10)," +
            "IMAGE VARCHAR(500)," +
            "DURATION VARCHAR(10)," +
            "CREATED VARCHAR(50));";

    private static void createResultsTable(Connection conn) throws SQLException {
        PreparedStatement createResults = conn.prepareStatement(CREATE_RESULTS);
        createResults.execute();
    }

    public static void createSchema(Connection conn) throws SQLException {
        createResultsTable(conn);
    }


}

