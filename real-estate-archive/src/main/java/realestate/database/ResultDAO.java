package realestate.database;

import realestate.web.ResultWithMetaData;

import java.sql.*;
import java.util.ArrayList;

public class ResultDAO {
    private final PreparedStatement insertStatement;
    private Statement getDataStatement;
    private final Connection connection;
    private ResultSet rs;

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

    public ArrayList<ResultWithMetaData> selectAll() throws SQLException, NumberFormatException {
        ArrayList<ResultWithMetaData> resultDaoList = new ArrayList<>();
        this.getDataStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.rs = getDataStatement.executeQuery("SELECT * FROM results LIMIT 1000;");
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
            resultDaoList.add(temporaryResult);
        }
        return resultDaoList;
    }

}
