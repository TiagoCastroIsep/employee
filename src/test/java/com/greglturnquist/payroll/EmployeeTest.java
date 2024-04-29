package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;
    private String firstName;
    private String lastName;
    private String description;
    private int jobYears;
    private String email;

    @BeforeEach
    void setup() {
        firstName = "fn";
        lastName = "ln";
        description = "d1";
        jobYears = 1;
        email = "email@domain.com";
        employee = new Employee(firstName, lastName, description, jobYears, email);
    }

    @Test
    void getId() {
        employee.setId(1L);
        assertEquals(1L, employee.getId());
    }

    @Test
    void getFirstName() {
        assertEquals("fn", employee.getFirstName());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void getFirstName(String fName) {
        assertThrows(IllegalArgumentException.class, () -> new Employee(fName, lastName, description, jobYears, email));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void getLastName(String lName) {
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lName, description, jobYears, email));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void getDescription(String descr) {
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, descr, jobYears, email));
    }

    @Test
    void getJobYears() {
        assertEquals(1, employee.getJobYears());
    }

    @Test
    void getJobYears_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, -1, email));
    }

    @Test
    void getEmail() {
        assertEquals("email@domain.com", employee.getEmail());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void testEmptyOrNullEmailField(String mail) {
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, mail));
    }

    @Test
    void testEmailNotContainsAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, "email"));
    }
}