package realestate.command;

import realestate.database.ResultDAO;

public class CommandFactory {

    public static Command createCommand(String userInput, ResultDAO resultDAO) {
        return getCommand(userInput, resultDAO);
    }

    private static Command getCommand(String userInput, ResultDAO resultDAO) {
        if(userInput.startsWith("generate-report")) {
            String[] splitter = userInput.split("\\s");
            userInput = splitter[1];
            return new ExportCommand(resultDAO, userInput);
        }
        if(userInput.equals("show-data")) {
            return new ShowDataCommand(resultDAO);
        }
        return null;
    }

}
