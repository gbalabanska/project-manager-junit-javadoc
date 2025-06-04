package org.junit.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a project manager employee.
 */
@Getter
@AllArgsConstructor
public class Manager implements Employee {
    /**
     * The name of the manager.
     */
    private final String name;

    @Override
    public String getPosition() {
        return "Manager";
    }
}
