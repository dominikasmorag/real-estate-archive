import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Result {
    ArrayList<ResultWithMetaData> listOfResults = new ArrayList<>();
    private String title;
    private String location;
    private String price;
    private String rooms;
    private String squareMeters;
    private String image;
    private int duration;
    private Timestamp timestamp;

    public void getData() throws IOException {
        getAllMetaData();
    }
        private void getAllMetaData() throws IOException {
            for (int i = 0; i < Website.getPages(); i++) {
                Document doc = Jsoup.connect(Website.BASIC_URL + (i + 1)).timeout(6000).get();
                Element body = doc.getElementsByAttributeValue("data-cy", "search.listing").last();
                assert body != null;
                Elements selectedElements = body.select("li");
                for (Element e : selectedElements) {
                    Date date = new Date();
                    long startTime  = date.getTime();
                    Result result = new Result();
                    result.title = e.select("h3").attr("title");
                    if (result.title==null || result.title.trim().isEmpty()) {
                        continue;
                    }

                    location = e.select("p").attr("title");
                    result.location = location.replaceAll("Mieszkanie na sprzedaż: ", "");

                    price = e.select("p").last().text().replaceAll("[ zł]", "");
                    result.price = price.replaceAll(",", ".");
                    if (price.equalsIgnoreCase("zapytaj o cenę") || price.equalsIgnoreCase("zapytajocenę")) {
                        result.price = "0";
                    }

                    rooms = e.select("span").get(1).text();
                    if (rooms.trim().equals(result.location.trim())) {
                        rooms = e.select("span").get(2).text();
                    }

                    squareMeters = e.select("span").get(2).text();
                    if (squareMeters.contains("pokoje")) {
                        squareMeters = e.select("span").get(3).text();
                    }


                    result.rooms = rooms.replaceAll(" pokoje",
                            "");

                    result.squareMeters = squareMeters.replaceAll(" m²",
                            "");

                    result.image = e.select("source").attr("srcset");

                    Date date2 = new Date();
                    long endtime = date2.getTime();
                    result.duration = (int) (startTime - endtime);
                    result.timestamp = new Timestamp(date2.getTime());
                        setAllMetaData(result);

                }
            }
        }

                    private void setAllMetaData(Result result) {
                        try {
                             {
                                 ResultWithMetaData resultWithMetaData = new ResultWithMetaData();
                                 if(!result.price.equals("0")) {
                                     resultWithMetaData.setTitle(result.title);
                                     resultWithMetaData.setLocation(result.location);
                                     resultWithMetaData.setPrice(BigDecimal.valueOf(Float.parseFloat(result.price)));
                                     resultWithMetaData.setRooms(Integer.valueOf(result.rooms));
                                     resultWithMetaData.setSquareMeters(Float.parseFloat(String.valueOf(result.squareMeters)));
                                     resultWithMetaData.setImage(result.image);
                                     resultWithMetaData.setDuration(result.duration);
                                     resultWithMetaData.setTimestamp(result.timestamp);

                                     listOfResults.add(resultWithMetaData);
                                 }
                            }
                        } catch(Exception ex) {
                            System.out.println("ex.getMessage() = " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }


}
