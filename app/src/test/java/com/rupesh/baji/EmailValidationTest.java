package com.rupesh.baji;

import com.aryan.cricrazy.activity.RegisterActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailValidationTest {

    @Test
    public void ValidInputTestTrue(){
        RegisterActivity registerActivity = new RegisterActivity();
        boolean result = registerActivity.emailValidation("aryan@gmail.com");
        assertEquals(true, result);
    }
}
