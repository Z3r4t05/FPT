
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class Task {

    private int TaskID;
    private String TaskTypeID;
    private String RequirementName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String Assignee;
    private String Reviewer;

    public Task(int TaskID, String TaskTypeID, String RequirementName, Date date, double planFrom, double planTo, String Assignee, String Reviewer) {
        this.TaskID = TaskID;
        this.TaskTypeID = TaskTypeID;
        this.RequirementName = RequirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.Assignee = Assignee;
        this.Reviewer = Reviewer;
    }

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int TaskID) {
        this.TaskID = TaskID;
    }

    public String getTaskTypeID() {
        return TaskTypeID;
    }

    public void setTaskTypeID(String TaskTypeID) {
        this.TaskTypeID = TaskTypeID;
    }

    public String getRequirementName() {
        return RequirementName;
    }

    public void setRequirementName(String RequirementName) {
        this.RequirementName = RequirementName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String Assignee) {
        this.Assignee = Assignee;
    }

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String Reviewer) {
        this.Reviewer = Reviewer;
    }

    @Override
    public String toString() {
        double time = planTo - planFrom;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = dateFormat.format(date);
        String planTime = String.format("%.1f", time);
        String result = String.format("%-6d%-20s%-13s%-13s%-14s%-13s%-15s", TaskID, RequirementName, TaskTypeID, stringDate, planTime, Assignee, Reviewer);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("----------------------------------------- Task ----------------------------------------");
        System.out.format("%-6s%-20s%-13s%-13s%-14s%-13s%-15s\n", "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        System.out.format("%-6d%-20s%-13s%-13s%-14s%-13s%-15s\n", 1, "Dev Program", "Code", "28-08-2015", "8.0", "Dev", "Lead");
    }

}
