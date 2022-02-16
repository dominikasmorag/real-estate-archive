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

    private void showCommands() throws IllegalArgumentException, NullPointerException {
        String userInput= "";
        Scanner sc = new Scanner(System.in);
        while(!userInput.equals("exit")) {
            try {
                System.out.println("enter the command");
                userInput = sc.nextLine();

                if (userInput.equals("exit")) {
                    return;
                }
                else {
                    Command command = CommandFactory.createCommand(userInput, resultDAO);
                    command.execute();
                }
            } catch (IllegalArgumentException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

}
