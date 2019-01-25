package com.smtteam.smt.common.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShowProjectUserTest {

    @Test
    public void test() {
        ShowProjectUser user = new ShowProjectUser();

        user.setUserId(1);
        assertEquals(1,user.getUserId().intValue() );

        user.setProId(1);
        assertEquals(1,user.getProId().intValue());


        user.setAvatar("1.png");
        assertNotNull(user.getAvatar());

        user.setUsername("zs");
        assertNotNull(user.getUsername());

        user.setRole(1);
        assertEquals(1,user.getRole().intValue() );

        user.setStatus(1);
        assertEquals(1,user.getStatus().intValue() );
    }

}