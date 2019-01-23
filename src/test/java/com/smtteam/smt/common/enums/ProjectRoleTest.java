package com.smtteam.smt.common.enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectRoleTest {

    @Test
    public void getRole() {
        ProjectRole role =ProjectRole.Owner;
        role.getRole();
        role.getRoleName();
        role.canEditProject();
        role.canBrower();
        role.canEditMap();
    }
}