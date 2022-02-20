package realestate.command;
import realestate.web.ResultWithMetaData;
import java.util.List;

interface ExportStrategy {
    void export(List<ResultWithMetaData> res);
}
