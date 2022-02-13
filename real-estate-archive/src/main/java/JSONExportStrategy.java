import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONExportStrategy implements ExportStrategy {

    public JSONExportStrategy() {

        }
    @Override
    public void export(List<ResultWithMetaData> list) {
        ArrayList<JsonObject> objectsList = new ArrayList<>();

        try(PrintWriter pw = new PrintWriter("JSONreport.json")) {
            JsonBuilderFactory factory = Json.createBuilderFactory(Collections.emptyMap());
            for (ResultWithMetaData resultWithMetaData : list) {
                JsonObject jsonObject = factory.createObjectBuilder()
                        .add("id", resultWithMetaData.getId())
                        .add("title", resultWithMetaData.getTitle())
                        .add("location", resultWithMetaData.getLocation())
                        .add("price", resultWithMetaData.getPrice())
                        .add("rooms", resultWithMetaData.getRooms())
                        .add("sqaure meters", resultWithMetaData.getSquareMeters())
                        .add("image", resultWithMetaData.getImage())
                        .add("duration", resultWithMetaData.getDuration())
                        .add("timestamp", String.valueOf(resultWithMetaData.getTimestamp()))
                        .build();
                objectsList.add(jsonObject);
            }
            for (JsonObject jsonObject : objectsList) {
                pw.println(jsonObject);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
