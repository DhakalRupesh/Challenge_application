package com.rupesh.baji;

import com.rupesh.baji.activities.Signup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailValidationTest {

    @Test
    public void ValidInputTestTrue(){
        Signup registerActivity = new Signup();
        boolean result = registerActivity.emailValidation("aryan@gmail.com");
        assertEquals(true, result);
    }
}
