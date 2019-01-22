package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShowUserTest {

    @Test
    public void test() {
        ShowUser user = new ShowUser();
        user.setId(1);
        assertEquals(user.getId().intValue(),1);

        user.setUsername("zs");
        assertNotNull(user.getUsername());

        user.setEmail("test@eemail");
        assertNotNull(user.getEmail());
    }
}