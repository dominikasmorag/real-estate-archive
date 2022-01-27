import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class WebsiteInfo {
    private int pages;
    private int results;

    public int getPages() throws IOException {
        Document doc = Jsoup.connect(Main.BASIC_URL).timeout(6500).get();
        Element strong = doc.select("strong").first();
        results = Integer.parseInt(strong.child(1).siblingElements().text());
        pages = results/50;
        if (results % 50 != 0) {
            pages++;
        }

        return pages;
    }



}
