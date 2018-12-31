package com.smtteam.smt.common.enums;

public enum ProjectRole {
    Brower(1), Map_Editor(3), Project_Editor(7), Owner(15);

    private int role;

    ProjectRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public boolean canBrower(){
        return (role & 1) == 1;
    }

    public boolean canEditMap(){
        return ((role >> 1) & 1) == 1;
    }

    public boolean canEditProject(){
        return ((role >> 2) & 1) == 1;
    }

}
