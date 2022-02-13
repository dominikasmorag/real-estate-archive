import java.util.Scanner;

public class ShowDataCommand implements Command {
    ResultDAO resultDAO;

    public ShowDataCommand(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    private void showCommands() {
        String userInput= "";
        Scanner sc = new Scanner(System.in);
        while(!userInput.equals("exit"))
        try {
            System.out.println("enter the command");
            userInput = sc.nextLine();

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

            if (!userInput.startsWith("generate-report")) {
                throw new IllegalArgumentException("command has to start with - generate-report");
            } } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void execute() {
        try {
            showCommands();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
