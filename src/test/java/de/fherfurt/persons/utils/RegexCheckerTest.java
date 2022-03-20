package de.fherfurt.persons.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexCheckerTest {
    @Test
    public void itShouldCheckEmails() {
        assertTrue(RegexChecker.checkEmail("bhaggerwood0@eventbrite.com"));
        assertTrue(RegexChecker.checkEmail("max.mustermann@gmail.com"));
        assertTrue(RegexChecker.checkEmail("paul-obst@gmx.de"));

        assertFalse(RegexChecker.checkEmail("paul-obstgmx.de"));
        assertFalse(RegexChecker.checkEmail("max.mustermann@gmailcom"));
    }

    @Test
    public void itShouldCheckPhoneNumbers() {
        assertTrue(RegexChecker.checkPhone("0173173990644"));
        assertTrue(RegexChecker.checkPhone("+491517953677"));
        assertTrue(RegexChecker.checkPhone("06442/3839023"));

        assertFalse(RegexChecker.checkPhone("2193"));
        assertFalse(RegexChecker.checkPhone("abc131234"));
    }

    @Test
    public void itShouldCheckFax() {
        assertTrue(RegexChecker.checkFax("2102986575"));

        assertFalse(RegexChecker.checkFax("210a2986575"));
    }
}
