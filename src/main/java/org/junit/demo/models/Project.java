package org.junit.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Represents a project which has a name and a list of participant employees.
 */
@Getter
@AllArgsConstructor
public class Project {

    /**
     * The name of the project.
     */
    private final String name;

    /**
     * The list of employees participating in this project.
     */
    private final List<Employee> participants;
}
