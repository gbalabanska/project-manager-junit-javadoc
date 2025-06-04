package org.junit.demo.models;

import org.junit.demo.enums.TaskStatus;

/**
 * Represents a task that belongs to a project and can be assigned to an employee.
 */
public class Task {

    /**
     * Title or short description of the task.
     */
    private String title;

    /**
     * The project this task is associated with.
     */
    private Project project;

    /**
     * The employee assigned to work on this task. Can be null for NEW tasks.
     */
    private Employee employee;

    /**
     * The current status of the task (e.g., NEW, IN_PROGRESS, COMPLETED, CANCELLED).
     */
    private TaskStatus status;

    /**
     * Informational message associated with the current status.
     */
    private String statusMessage;

    public Task() {
    }

    public Task(String title, Project project, Employee employee, TaskStatus status, String statusMessage) {
        this.title = title;
        this.project = project;
        this.employee = employee;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
