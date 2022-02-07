import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Operations {
    private final String GENERATE_JSON = "generate-report json";
    private final String GENERATE_HTML = "generate-report html";
    private final String EXIT = "exit";
    private final String ERROR_MESSAGE = "==============\nwrong command\nyou can only use commands below: \n" + GENERATE_HTML + "\n" + GENERATE_JSON + "\n" + EXIT + "\n===========";
    private final String HTML_BEGINNING = "<html>" +
            "<head>" +
            "<style>" +
            "table, th, td {" +
            "border: 1px solid black;" +
            "}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<table>" +
            "<tr>" +
            "    <th>id</th>" +
            "    <th>title</th>" +
            "    <th>location</th>" +
            "    <th>price</th>" +
            "    <th>rooms</th>" +
            "    <th>square meters</th>" +
            "    <th>image</th>" +
            "    <th>duration</th>" +
            "    <th>created</th>" +
            "</tr>";
    private final String HTML_ENDING = "</table>" +
            "</body>" +
            "</html>";
    private Connection conn;
    ArrayList<JsonObject> objectsList = new ArrayList<>();


    public void executeSomeOperation(Connection conn) throws SQLException {
        this.conn = conn;
        Scanner sc = new Scanner(System.in);
        String s = "";
        while (true) {
            System.out.println("What would u like to do? ");
            s = sc.nextLine();
            if (s.equals(GENERATE_HTML)) {
                try {
                    generateReportHTML(conn);
                    System.out.println("html report generated");
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                } finally {
                    conn.close();
                }
                continue;
            }

            if (s.equals(GENERATE_JSON)) {
                try {
                    generateReportJSON(conn);
                    System.out.println("json report generated");
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                } finally {
                    conn.close();
                }
                continue;
            }
            if (s.equals(EXIT)) {
                break;
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }


    private void generateReportHTML(Connection conn) throws IOException, SQLException {
        ResultDataFromDB resultDataFromDB = new ResultDataFromDB();
        this.conn = conn;
        resultDataFromDB.printData(conn);
        try (PrintWriter pw = new PrintWriter("index.html")) {
            pw.println(HTML_BEGINNING);
            for (int i = 0; i < ResultDataFromDB.listToTable.size(); i++) {
                pw.println("<tr>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getId() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getTitle() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getLocation() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getPrice() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getRooms() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getSquareMeters() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getImage() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getDuration() + "</td>");
                pw.println("<td>" + ResultDataFromDB.listToTable.get(i).getTimestamp() + "</td>");
                pw.println("</tr>");
            }
            pw.println(HTML_ENDING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateReportJSON(Connection conn) throws IOException, SQLException {
        ResultDataFromDB resultDataFromDB = new ResultDataFromDB();
        this.conn = conn;
        resultDataFromDB.printData(conn);
        try (PrintWriter pw = new PrintWriter("document.json")){
            JsonBuilderFactory factory = Json.createBuilderFactory(Collections.emptyMap());
            for (int i = 0; i < ResultDataFromDB.listToTable.size(); i++) {
                JsonObject jsonObject = factory.createObjectBuilder()
                        .add("id", ResultDataFromDB.listToTable.get(i).getId())
                        .add("title", ResultDataFromDB.listToTable.get(i).getTitle())
                        .add("location", ResultDataFromDB.listToTable.get(i).getLocation())
                        .add("price", ResultDataFromDB.listToTable.get(i).getPrice())
                        .add("rooms", ResultDataFromDB.listToTable.get(i).getRooms())
                        .add("sqaure meters", ResultDataFromDB.listToTable.get(i).getSquareMeters())
                        .add("image", ResultDataFromDB.listToTable.get(i).getImage())
                        .add("duration", ResultDataFromDB.listToTable.get(i).getDuration())
                        .add("timestamp", String.valueOf(ResultDataFromDB.listToTable.get(i).getTimestamp()))
                        .build();
                objectsList.add(jsonObject);
            }

            for (int i = 0; i < objectsList.size(); i++) {
                pw.println(objectsList.get(i));
            }

        } catch (JsonException ex) {
            ex.printStackTrace();
        }
    }
}
