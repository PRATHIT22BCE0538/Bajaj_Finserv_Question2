package com.bajajfinserv.webhook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebhookSpringBootApplicationTests {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
    }

    @Test
    void testSqlQueryGeneration() {
        SqlQueryService sqlQueryService = new SqlQueryService();
        String query = sqlQueryService.getQuestion2Solution();

        // Basic validation
        assert query != null;
        assert query.contains("SELECT");
        assert query.contains("YOUNGER_EMPLOYEES_COUNT");
        assert query.contains("ORDER BY e1.EMP_ID DESC");

        System.out.println("SQL Query validation passed!");
    }
}