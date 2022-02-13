import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class ExportCommand implements Command {
    private final ResultDAO resultDAO;
    private final String exportArg;
    private final HashMap<String, ExportStrategy> commandsMap = initializeCommands();

    public ExportCommand(ResultDAO resultDAO, String exportArg) {
        this.resultDAO = resultDAO;
        this.exportArg = exportArg;
    }

    public void execute() {
        ExportStrategy exportStrategy = getExportStrategy(exportArg);
        try {
            exportStrategy.export(resultDAO.selectAll());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ExportStrategy getExportStrategy(String exportArg) {
        return commandsMap.get(exportArg);
    }

    private HashMap<String, ExportStrategy> initializeCommands() {
        HashMap<String, ExportStrategy> commandsMap = new HashMap<>();
        commandsMap.put("html", new HTMLExportStrategy());
        commandsMap.put("json", new JSONExportStrategy());
        return commandsMap;
    }

}
