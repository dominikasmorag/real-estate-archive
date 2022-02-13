import java.sql.SQLException;
import java.util.List;

public interface ExportStrategy {
    void export(List<ResultWithMetaData> res) throws SQLException;
}
