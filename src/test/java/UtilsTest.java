import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void itShouldCheckEmails() {
        assertTrue(Utils.checkEmail("bhaggerwood0@eventbrite.com"));
        assertTrue(Utils.checkEmail("max.mustermann@gmail.com"));
        assertTrue(Utils.checkEmail("paul-obst@gmx.de"));

        assertFalse(Utils.checkEmail("paul-obstgmx.de"));
        assertFalse(Utils.checkEmail("max.mustermann@gmailcom"));
    }

    @Test
    void itShouldCheckPhoneNumbers() {
        assertTrue(Utils.checkPhone("0173173990644"));
        assertTrue(Utils.checkPhone("+491517953677"));
        assertTrue(Utils.checkPhone("06442/3839023"));

        assertFalse(Utils.checkPhone("2193"));
        assertFalse(Utils.checkPhone("abc131234"));
    }

    @Test
    void itShouldCheckFax() {
        assertTrue(Utils.checkFax("2102986575"));

        assertFalse(Utils.checkFax("210a2986575"));
    }

}
