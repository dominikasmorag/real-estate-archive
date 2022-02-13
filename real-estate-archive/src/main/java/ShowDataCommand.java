import java.sql.SQLException;
import java.util.ArrayList;

public class ShowDataCommand implements Command {
    private final ResultDAO resultDAO;

    public ShowDataCommand(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    @Override
    public void execute() {
        showData();
    }

    private void showData() {
        ArrayList<ResultWithMetaData> list = null;
        try {
            list = resultDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
    }
}
