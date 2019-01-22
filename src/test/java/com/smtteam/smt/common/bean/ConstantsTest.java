package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    @Test
    public void testConstants(){

        Constants constants = new Constants();

        assertEquals(Constants.PROJECT_INVITING.intValue(), 1);
        assertEquals(Constants.PROJECT_INVITED.intValue(), 2);

        assertEquals(Constants.USEREMAIL_VERIFYING.intValue(), 1);
        assertEquals(Constants.USEREMAIL_VERIFIED.intValue(), 2);

        assertEquals(Constants.USER_DEFAULT_GENDA.intValue(), 1);
        assertTrue("".equals(Constants.USER_DEFAULT_VERIFY));

        assertEquals(Constants.SALT_LENGTH.intValue(), 20);
    }

}