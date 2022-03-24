
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class TaskManager {

    void displayMenu(ArrayList<String> listOptions) {
        System.out.println("========= Task program =========");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println("  .   " + listOptions.get(i));
        }
    }

    int addTask(ArrayList<Task> taskList, int id) {
        //loop until there are no exceptions
        do {                  
            try {
                Inputter inputter = new Inputter();
                System.out.println("------------Add Task---------------");
                String requireName = inputter.getString("Requirement Name: ", "", "");
                String taskType = inputter.getTaskType("Task Type: ");
                Date date = inputter.getDate("Date: ");
                double planFrom = inputter.getDouble("From: ", "planFrom must be within 8-17h", 8.0, 17.0);
                double planTo = inputter.getDouble("To: ", "planTo must be after planFrom and not after 17h30", planFrom + 0.5, 17.5);
                String assignee = inputter.getString("Assignee: ", "", "");
                //throw exception if the task slot is avaiable to add new task. otherwise add the task
                if (checkTaskAvailable(date, assignee, planFrom, planTo, taskList)) {
                    throw new Exception("There are other tasks that exist in the same time");
                } else {
                    String reviewer = inputter.getString("Reviewer: ", "", "");
                    Task newTask = new Task(id, taskType, requireName, date, planFrom,
                            planTo, assignee, reviewer);
                    taskList.add(newTask);
                    System.out.println("Add successfully");
                }
                return id;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    
    
    private boolean checkTaskAvailable(Date date, String assignee, double planFrom, double planTo, ArrayList<Task> TaskList) {
        boolean isNotAvailable = false;
        //loop use to access each element of arraylist from begining to the end
        for (Task task : TaskList) {
            //compare date in list with date input and assignee in list and assignee input
            if (date.compareTo(task.getDate()) == 0 && assignee.equals(task.getAssignee())) {    
                //if there is collison in plan then continue to check the next task in the list. Otherwise return false
                if ((planTo < task.getPlanFrom()) || (planFrom > task.getPlanTo())) {
                    isNotAvailable = false;
                } else {
                    isNotAvailable = true;
                    break;
                }
            }
        }
        return isNotAvailable;
    }

    void deleteTask(ArrayList<Task> taskList, int lastID) throws Exception {
        Inputter inputter = new Inputter();
        //throw exception if the task list is empty. Otherwise perform delete
        if(taskList.isEmpty()) {
            throw new Exception("Empty taskList");
        } else {
            System.out.println("--------- Del Task --------");
            int taskId = inputter.getInt("ID: ", "Task id exceeds range", 1, lastID);
            int index = -1;
            //loop through task list to find the task that user want to delete
            for (Task task : taskList) {
                //if task id equals input id the get the index of the task
                if(taskId == task.getTaskID()) {
                    index = taskList.indexOf(task);
                }
            }
            //if index not equal -1 then remove it
            if(index != -1) {
                taskList.remove(index);
                System.out.println("Remove successfully");
            } else {
                throw new Exception("ID doesn't exist");
            }
        } 
    }

    void displayTask(ArrayList<Task> taskList) throws Exception {
        //throw exception if the tasklist is empty
        if (taskList.isEmpty()) {
            throw new Exception("List task is empty!");
        } else{
            System.out.println("----------------------------------------- Task -------------------------------------");
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

}
