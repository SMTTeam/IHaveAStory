package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    @Test
    public void testConstants(){

        Constants constants = new Constants();

        assertEquals(1,Constants.PROJECT_INVITING.intValue());
        assertEquals(2,Constants.PROJECT_INVITED.intValue());

        assertEquals(1,Constants.USEREMAIL_VERIFYING.intValue());
        assertEquals(2,Constants.USEREMAIL_VERIFIED.intValue() );

        assertEquals(1,Constants.USER_DEFAULT_GENDA.intValue());
        assertTrue("".equals(Constants.USER_DEFAULT_VERIFY));

        assertEquals(Constants.SALT_LENGTH.intValue(), 20);
    }

}