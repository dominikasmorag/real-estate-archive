import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResultDataFromDB extends ResultWithMetaData {
    private Connection conn;
    private ResultSet rs;
    private Statement statement;
    static ArrayList<ResultWithMetaData> listToTable = new ArrayList<>();

    public void printData(Connection conn) throws SQLException, NumberFormatException {
        this.conn = conn;
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = statement.executeQuery("SELECT * FROM results;");
        while(rs.next()){
            ResultWithMetaData temporaryResult = new ResultWithMetaData();
            temporaryResult.setId(rs.getInt(1));
            temporaryResult.setTitle(rs.getString(2));
            temporaryResult.setLocation(rs.getString(3));
            temporaryResult.setPrice(rs.getBigDecimal(4));
            temporaryResult.setRooms(rs.getInt(5));
            temporaryResult.setSquareMeters(rs.getFloat(6));
            temporaryResult.setImage(rs.getString(7));
            temporaryResult.setDuration(rs.getInt(8));
            temporaryResult.setTimestamp(rs.getTimestamp(9));
            listToTable.add(temporaryResult);
        }
    }

}
