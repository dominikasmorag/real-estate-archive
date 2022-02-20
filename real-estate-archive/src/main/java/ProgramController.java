import command.realestate.*;
import realestate.command.Command;
import realestate.command.CommandFactory;
import realestate.database.ResultDAO;

import java.util.Scanner;

public class ProgramController {
    ResultDAO resultDAO;

    ProgramController(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public void showAllCommands() {
        showCommands();
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
            } catch (IllegalArgumentException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    }

}
