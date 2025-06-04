package org.junit.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a developer employee.
 */
@Getter
@AllArgsConstructor
public class Developer implements Employee {
    /**
     * The name of the developer.
     */
    private final String name;

    @Override
    public String getPosition() {
        return "Developer";
    }
}
