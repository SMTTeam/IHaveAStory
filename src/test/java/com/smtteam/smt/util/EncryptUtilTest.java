package com.smtteam.smt.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptUtilTest {

    @Test
    public void SHA256() {
        assertTrue(EncryptUtil.SHA256("123456").equals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"));
        assertTrue(EncryptUtil.SHA256("").equals(""));
        assertTrue(EncryptUtil.SHA256(null).equals(""));
    }
}