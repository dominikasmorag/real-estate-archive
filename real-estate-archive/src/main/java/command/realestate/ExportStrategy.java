package command.realestate;
import web.realestate.ResultWithMetaData;
import java.util.List;

interface ExportStrategy {
    void export(List<ResultWithMetaData> res);
}
