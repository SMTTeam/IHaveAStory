package com.smtteam.smt.common.enums;

public enum ProjectRole {
    Brower(1, "可查看"), Map_Editor(3, "可编辑"), Project_Editor(7, "可管理"), Owner(15, "创建者");

    private int role;
    private String roleName;

    ProjectRole(int role, String roleName) {
        this.role = role;
        this.roleName = roleName;
    }

    public int getRole() {
        return role;
    }

    public String getRoleName() {
        return roleName;
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
