package com.rupesh.baji;

import com.rupesh.baji.activities.Login;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidInputTest {

    @Test
    public void ValidInputTest(){
        Login loginActivity = new Login();
        boolean result = loginActivity.validInput("", "");
        assertEquals(false, result);
    }
}
