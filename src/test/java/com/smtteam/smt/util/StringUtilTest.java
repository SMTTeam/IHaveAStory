package com.smtteam.smt.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void getSalt() {
        assertNotNull(StringUtil.getSalt());
    }
}