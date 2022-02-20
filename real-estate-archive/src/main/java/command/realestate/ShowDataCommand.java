package command.realestate;

import database.realestate.ResultDAO;
import web.realestate.ResultWithMetaData;

import java.sql.SQLException;
import java.util.ArrayList;

class ShowDataCommand implements Command {
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
        if (list != null) {
            list.forEach(System.out::println);
        }
    }
}
