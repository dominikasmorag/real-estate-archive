import org.h2.jdbcx.JdbcDataSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    static final String BASIC_URL = "https://www.otodom.pl/pl/oferty/sprzedaz/mieszkanie/katowice?priceMin=300000&priceMax=480000&roomsNumber=%5BTHREE%5D&PAGE=11&limit=50&page=";


    public static void main(String[] args) throws SQLException, IOException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:/C:/Users/domin/Desktop/h2o/h2owy/labadabadu.db");
        ds.setUser("sa");
        ds.setPassword("sa");



        Connection conn = ds.getConnection();

        try {
            DataBase.createSchema(conn);
        } catch (Exception ex) {
            System.out.println("ex.getMessage(): " + ex.getMessage());
            ex.printStackTrace();
        }



        int id = 1;
        int durationSum = 0;
        ArrayList<Result> list = new ArrayList<>();

        WebsiteInfo webInfo = new WebsiteInfo();
        int pages = webInfo.getPages();
        String[] url = new String[pages];


        for (int i = 0; i < pages; i++) {
            url[i] = BASIC_URL + (i + 1);
        }

        for (int i = 0; i < pages; i++) {


            Document doc = Jsoup.connect(url[i]).timeout(6000).get();

            Element body = doc.getElementsByAttributeValue("data-cy", "search.listing").last();
            assert body != null;
            Elements selectedElements = body.select("li");
            for (Element e : selectedElements) {
                Date startDate = new Date();
                long startTime = startDate.getTime();
                String title, price, location, rooms, squareMeters, image;
                long duration = 0;
                Result result = new Result();

                title = e.select("h3").attr("title");
                if (title==null || title.trim().isEmpty()) {
                    continue;
                }

                location = e.select("p").attr("title");
                location = location.replaceAll("Mieszkanie na sprzedaż: ", "");

                price = e.select("p").last().text().replaceAll("[ zł]", "");
                price = price.replaceAll(",", ".");
                if (price.equalsIgnoreCase("zapytaj o cenę") || price.equalsIgnoreCase("zapytajocenę")) {
                    price = "0";
                }

                rooms = e.select("span").get(1).text();
                if (rooms.trim().equals(location.trim())) {
                    rooms = e.select("span").get(2).text();
                }

                rooms = rooms.replaceAll(" pokoje",
                        "");

                squareMeters = e.select("span").get(2).text();
                if (squareMeters.contains("pokoje")) {
                    squareMeters = e.select("span").get(3).text();
                }


                rooms = rooms.replaceAll(" pokoje",
                        "");

                squareMeters = squareMeters.replaceAll(" m²",
                        "");

                image = e.select("source").attr("srcset");

                Date endDate = new Date();
                long endTime = endDate.getTime();
                duration = endTime - startTime;
                durationSum = (int) (durationSum + duration);


            try {
                if (!price.equals("0")) {
                    Date now = new Date();
                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dateString = formatDate.format(now);
                    result.setId(id);
                    result.setTitle(title);
                    result.setLocation(location);
                    result.setPrice(BigDecimal.valueOf(Float.parseFloat(price)));
                    result.setRooms(Integer.parseInt(rooms));
                    result.setSquareMeters(Float.parseFloat(squareMeters));
                    result.setImage(image);
                    result.setDate(dateString);
                    result.setDuration((int) duration);
                    id++;

                    list.add(result);

                }
                } catch(Exception ex) {
                System.out.println("ex.getMessage() = " + ex.getMessage());
                ex.printStackTrace();
            }

            }
        }

        ResultDAO resultDAO = new ResultDAO(conn);

        for(Result result : list) {
            resultDAO.saveResult(result);
        }

       resultDAO.selectAll(conn);

        conn.close();

    }

}