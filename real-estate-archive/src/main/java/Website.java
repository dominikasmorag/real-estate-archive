import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Website {
    static final String BASIC_URL = "https://www.otodom.pl/pl/oferty/sprzedaz/mieszkanie/katowice?priceMin=300000&priceMax=480000&roomsNumber=%5BTHREE%5D&PAGE=11&limit=50&page=";
    public static int pages;


    public static int getPages() throws IOException {
        Document doc = Jsoup.connect(BASIC_URL).timeout(6500).get();
        Element strong = doc.select("strong").first();
        assert strong != null;
        int results = Integer.parseInt(strong.child(1).siblingElements().text());
        pages = results/50;
        if (results % 50 != 0) {
            pages++;
        }
        return pages;
    }





}
