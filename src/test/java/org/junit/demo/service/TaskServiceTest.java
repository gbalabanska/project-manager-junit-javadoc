package org.junit.demo.service;

import org.junit.demo.enums.TaskStatus;
import org.junit.demo.models.Developer;
import org.junit.demo.models.Employee;
import org.junit.demo.models.Project;
import org.junit.demo.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;
    private Project sampleProject;
    private Employee dev;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
        sampleProject = new Project("Test Project123", List.of());
        dev = new Developer("Gabby");
    }

    @Test
    void createTask_withValidData_returnsTask() {
        Task task = taskService.createTask(
                "Implement feature",
                sampleProject,
                TaskStatus.NEW,
                "Task created",
                null
        );

        assertEquals("Implement feature", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(sampleProject, task.getProject());
        assertNull(task.getEmployee());
        assertEquals("Task created", task.getStatusMessage());
    }

    @Test
    void createTask_withNonNewStatusAndNoEmployee_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask("Fix bug", sampleProject, TaskStatus.IN_PROGRESS, "Starting work", null));
        assertEquals("Non-NEW tasks must have an assigned employee", ex.getMessage());
    }

    @Test
    void createTask_withNullTitle_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask(null, sampleProject, TaskStatus.NEW, "Message", null));
    }

    @Test
    void createTask_withBlankTitle_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask("  ", sampleProject, TaskStatus.NEW, "Message", null));
    }

    @Test
    void createTask_withNullProject_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask("Task", null, TaskStatus.NEW, "Message", null));
    }

    @Test
    void createTask_withNullStatus_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask("Task", sampleProject, null, "Message", null));
    }

    @Test
    void assignEmployee_successfullyAssigns() {
        Task task = taskService.createTask("New Task", sampleProject, TaskStatus.NEW, "Created", null);
        taskService.assignEmployee(task, dev);

        assertEquals(dev, task.getEmployee());
    }

    @Test
    void assignEmployee_withNullEmployee_throwsException() {
        Task task = taskService.createTask("Task", sampleProject, TaskStatus.NEW, "Init", null);
        assertThrows(IllegalArgumentException.class, () -> taskService.assignEmployee(task, null));
    }

    @Test
    void changeStatus_withValidData_changesStatusAndMessage() {
        Task task = taskService.createTask("Develop", sampleProject, TaskStatus.IN_PROGRESS, "Assigned", dev);
        taskService.changeStatus(task, TaskStatus.CANCELLED, "Stopped work");

        assertEquals(TaskStatus.CANCELLED, task.getStatus());
        assertEquals("Stopped work", task.getStatusMessage());
    }

    @Test
    void changeStatus_toInProgressWithoutEmployee_throwsException() {
        Task task = taskService.createTask("Task", sampleProject, TaskStatus.NEW, "Init", null);
        assertThrows(IllegalStateException.class, () ->
                taskService.changeStatus(task, TaskStatus.IN_PROGRESS, "Trying to start"));
    }

    @Test
    void changeStatus_onCompletedTask_throwsException() {
        Task task = taskService.createTask("Finalized", sampleProject, TaskStatus.COMPLETED, "Done", dev);
        assertThrows(IllegalStateException.class, () ->
                taskService.changeStatus(task, TaskStatus.IN_PROGRESS, "Retry"));
    }

    @Test
    void changeStatus_withBlankMessage_throwsException() {
        Task task = taskService.createTask("Some Task", sampleProject, TaskStatus.IN_PROGRESS, "Start", dev);
        assertThrows(IllegalArgumentException.class, () ->
                taskService.changeStatus(task, TaskStatus.CANCELLED, "  "));
    }
}