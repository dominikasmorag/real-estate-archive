import org.h2.jdbcx.JdbcDataSource;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:/C:/Users/domin/Desktop/h2o/h2owy/haa.db");
        ds.setUser("sa");
        ds.setPassword("sa");

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

        for(ResultWithMetaData rsvm : result.listOfResults) {
            resultDAO.saveResult(rsvm);

        }

        conn.close();
    }

}