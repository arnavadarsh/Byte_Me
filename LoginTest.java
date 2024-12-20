package Byte_Me;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void testInvalidAdminLogin() {
        Admin admin = new Admin();
        String wrongEmail = "wrong@byteme.com";
        String wrongPassword = "wrongpassword";
        boolean isValidLogin = admin.getEmail().equals(wrongEmail) && admin.getPassword().equals(wrongPassword);
        assertFalse(isValidLogin, "Admin login must be denied for incorrect credentials.");
    }

    @Test
    void testInvalidCustomerLogin() {
        Admin admin = new Admin();
        Customer customer = new Customer("student5@byteme.com", "123", false, admin);
        boolean isAuthenticated = customer.authenticate("wrong@byteme.com", "wrongpassword");
        assertFalse(isAuthenticated, "Customer login must be denied for incorrect credentials.");
    }

    @Test
    void testValidCustomerLogin() {
        Admin admin = new Admin();
        Customer customer = new Customer("student2@byteme.com", "123", false, admin);
        boolean isAuthenticated = customer.authenticate("student2@byteme.com", "123");
        assertTrue(isAuthenticated, "Customer login should succeed for correct credentials.");
    }
}
