
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<String> listOptions = new ArrayList(Arrays.asList(
                "Add Task",
                "Delete task",
                "Display Task",
                "exit"));
        int choice;
        int id = 0;
        //Loop until user choose exit
        do {
            //1, display menu
            manager.displayMenu(listOptions);
            //2. get option from user
            choice = Inputter.getOption("Your choice: ", 1, 4);
            try {
                //3. perform function based on user choice
                switch (choice) {
                    //add task
                    case 1:
                        id = manager.addTask(taskList, id + 1);
                        break;
                    //delete task
                    case 2:
                        manager.deleteTask(taskList, id);
                        break;
                    //display task
                    case 3:
                        manager.displayTask(taskList);
                        break;
                    //exit
                    default:
                        System.exit(0);
                }             
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

}
