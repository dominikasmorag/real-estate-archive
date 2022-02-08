import org.h2.jdbcx.JdbcDataSource;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(args[0]);
        ds.setUser(args[1]);
        ds.setPassword(args[2]);

        Connection conn = ds.getConnection();

        try {
            DataBase.createSchema(conn);
        } catch (Exception ex) {
            System.out.println("ex.getMessage(): " + ex.getMessage());
            ex.printStackTrace();
        }

        Result result = new Result();
        result.getData();
        ResultDAO resultDAO = new ResultDAO(conn);

        for (ResultWithMetaData rsvm : result.listOfResults) {
            resultDAO.saveResultToFile(rsvm);
        }

        conn.close();

        Connection conn2 = ds.getConnection();

        Operations operations = new Operations();
        operations.executeSomeOperation(conn2);

    }

}