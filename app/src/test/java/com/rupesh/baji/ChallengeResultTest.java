package com.rupesh.baji;

import com.rupesh.baji.bbl.LoginBBL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChallengeResultTest {
    @Test
    public void testLogin() {
        LoginBBL loginTest = new LoginBBL();
        boolean result = loginTest.checkUser("tina55", "tina55");
        assertEquals(false, result); //
    }
}
