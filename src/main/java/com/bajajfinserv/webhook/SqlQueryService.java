package com.bajajfinserv.webhook;

import org.springframework.stereotype.Service;

/**
 * Service class to provide SQL solutions for the given problems
 */
@Service
public class SqlQueryService {

    /**
     * Get SQL solution for Question 2 (Even registration numbers)
     * 
     * Problem: Calculate the number of employees who are younger than each employee,
     * grouped by their respective departments.
     * 
     * Output: EMP_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_NAME, YOUNGER_EMPLOYEES_COUNT
     * Ordered by employee ID in descending order.
     */
    public String getQuestion2Solution() {
        String sqlQuery = """
            SELECT 
                e1.EMP_ID,
                e1.FIRST_NAME,
                e1.LAST_NAME,
                d.DEPARTMENT_NAME,
                COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
            FROM EMPLOYEE e1
            JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
            LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB
            GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
            ORDER BY e1.EMP_ID DESC
            """;

        System.out.println("Generated SQL Query for Question 2:");
        System.out.println(sqlQuery);

        return sqlQuery.trim();
    }

    /**
     * Get SQL solution for Question 1 (Odd registration numbers)
     * This method is included for completeness but not used for regNo 22BCE0538
     */
    public String getQuestion1Solution() {
        // This would contain the SQL solution for Question 1
        // Not implemented as regNo 22BCE0538 is even (Question 2)
        throw new UnsupportedOperationException("Question 1 solution not required for even registration number");
    }
}