package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShowProjectUserTest {

    @Test
    public void test() {
        ShowProjectUser user = new ShowProjectUser();

        user.setUserId(1);
        assertEquals(user.getUserId().intValue(), 1);

        user.setProId(1);
        assertEquals(user.getProId().intValue(), 1);


        user.setAvatar("1.png");
        assertNotNull(user.getAvatar());

        user.setUsername("zs");
        assertNotNull(user.getUsername());

        user.setRole(1);
        assertEquals(user.getRole().intValue(), 1);

        user.setStatus(1);
        assertEquals(user.getStatus().intValue(), 1);
    }

}