package org.junit.demo.enums;

/**
 * Represents the possible statuses of a task.
 */
public enum TaskStatus {
    /** Task is newly created and not yet assigned or started. */
    NEW,

    /** Task is currently being worked on. */
    IN_PROGRESS,

    /** Task has been completed successfully. */
    COMPLETED,

    /** Task has been cancelled or rejected. */
    CANCELLED
}
