public class CommandFactory {

    public static void createCommand(String input, ResultDAO resultDAO) {
        getCommand(input, resultDAO);
    }

    private static Command getCommand(String input, ResultDAO resultDAO) {
        if(input.startsWith("generate-report")) {
            String[] splitter = input.split("\\s");
            input = splitter[1];
            return new ExportCommand(resultDAO, input);
        }
        if(input.equals("show-data")) {
            return new ShowDataCommand(resultDAO);
        }
        return null;
    }

}
