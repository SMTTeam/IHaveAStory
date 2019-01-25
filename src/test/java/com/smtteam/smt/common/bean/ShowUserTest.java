package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShowUserTest {

    @Test
    public void test() {
        ShowUser user = new ShowUser();
        user.setId(1);
        assertEquals(1,user.getId().intValue());

        user.setUsername("zs");
        assertNotNull(user.getUsername());

        user.setEmail("test@eemail");
        assertNotNull(user.getEmail());
    }
}