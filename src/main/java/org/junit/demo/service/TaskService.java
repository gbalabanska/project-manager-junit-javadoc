package org.junit.demo.service;

import org.junit.demo.enums.TaskStatus;
import org.junit.demo.models.Employee;
import org.junit.demo.models.Project;
import org.junit.demo.models.Task;

/**
 * Service class that handles business logic for managing tasks.
 */
public class TaskService {

    /**
     * Creates a new task with the given data.
     *
     * @param title   the title of the task (required, non-empty)
     * @param project the project this task belongs to (required)
     * @param status  the initial status of the task (required)
     * @param message a status message or description (can be null)
     * @param employee the assigned employee; must be set for non-NEW tasks, and null for NEW
     * @return a newly created {@link Task} instance
     * @throws IllegalArgumentException if required fields are missing or inconsistent
     */
    public Task createTask(String title, Project project, TaskStatus status, String message, Employee employee) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (project == null) {
            throw new IllegalArgumentException("Project cannot be empty");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status cannot be empty");
        }

        if (status != TaskStatus.NEW && employee == null) {
            throw new IllegalArgumentException("Non-NEW tasks must have an assigned employee");
        }

        Task task = new Task();
        task.setTitle(title);
        task.setProject(project);
        task.setStatus(status);
        task.setStatusMessage(message);
        task.setEmployee(employee);

        return task;
    }

    /**
     * Assigns an employee to a task.
     *
     * @param task     the task to update
     * @param employee the employee to assign (required)
     * @throws IllegalArgumentException if the employee is null
     */
    public void assignEmployee(Task task, Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        task.setEmployee(employee);
    }

    /**
     * Changes the status of a task and updates its status message.
     *
     * @param task      the task to update
     * @param newStatus the new status to set
     * @param message   informational message about the status change (required)
     * @throws IllegalStateException    if the task is already completed or if IN_PROGRESS is set without an employee
     * @throws IllegalArgumentException if the message is null or blank
     */
    public void changeStatus(Task task, TaskStatus newStatus, String message) {
        if (newStatus == TaskStatus.IN_PROGRESS && task.getEmployee() == null) {
            throw new IllegalStateException("Cannot start work without assigned employee");
        }

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot change status of a completed task");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Status change must include a message");
        }

        task.setStatus(newStatus);
        task.setStatusMessage(message);
    }
}
