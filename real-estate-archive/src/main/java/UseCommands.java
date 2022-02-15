import java.util.Scanner;

public class UseCommands implements Command {
    ResultDAO resultDAO;

    public UseCommands(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    @Override
    public void execute() {
        try {
            showCommands();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showCommands() {
        String userInput= "";
        Scanner sc = new Scanner(System.in);
        while(!userInput.equals("exit"))
        try {
            System.out.println("enter the command");
            userInput = sc.nextLine();

            if(userInput.equals("show-data")) {
                ShowDataCommand showDataCommand = new ShowDataCommand(resultDAO);
                showDataCommand.execute();
                continue;
            }

            if(userInput.startsWith("generate-report")) {
                String[] splitter = userInput.split("\\s");
                String exportArg = splitter[1];
                Command com = new ExportCommand(resultDAO, exportArg);
                try {
                    com.execute();
                } catch (NullPointerException | IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }

            if (userInput.equals("exit")) {
                return;
            }
            else  {
                throw new IllegalArgumentException("command has to start with - generate-report");
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

}
