function parseRole(role) {
    switch (role) {
        case 1:
            return "可查看";
        case 3:
            return "可编辑";
        case 7:
            return "可管理";
        case 15:
            return "创建者";
        default:
            return "";
    }
}

function parseInviteStatus(status) {
    switch (status) {
        case 1:
            return "邀请中";
        case 2:
            return "已加入";
        default:
            return "";
    }
}