import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class HTMLExportStrategy implements ExportStrategy {
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

        public HTMLExportStrategy() {

        }

        @Override
        public void export(List<ResultWithMetaData> list) {
                try (PrintWriter pw = new PrintWriter("index.html")) {
                        pw.println(HTML_BEGINNING);
                        for (ResultWithMetaData resultWithMetaData : list) {
                                pw.println("<tr>");
                                pw.println("<td>" + resultWithMetaData.getId() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getTitle() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getLocation() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getPrice() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getRooms() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getSquareMeters() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getImage() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getDuration() + "</td>");
                                pw.println("<td>" + resultWithMetaData.getTimestamp() + "</td>");
                                pw.println("</tr>");
                        }
                        pw.println(HTML_ENDING);
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }
}
