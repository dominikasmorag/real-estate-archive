import org.h2.jdbcx.JdbcDataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("exit")) {
            System.out.println("enter the command: ");
            userInput = sc.nextLine();
            if(!userInput.startsWith("generate-report")) {
                try {
                    throw new IllegalArgumentException("you can use only generate-report [html, json] command ");
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                if (userInput.startsWith("generate-report")) {
                    System.out.println(userInput);
                    String[] splitted = userInput.split("\\s+");
                    String exportArg = splitted[1];
                    Command a = new ExportCommand(resultDAO, exportArg);
                    try {
                        a.execute();
                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}

