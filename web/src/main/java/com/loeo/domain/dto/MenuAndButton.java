package com.loeo.domain.dto;

/**
 * Created by LT on 2016/10/03 0:08
 */
public class MenuAndButton {
    private String id;
    private String name;
    private String iconCls;
    private String type;
    private String pid;
    private String privilegeId;
    private String checked;

    public String getChecked() {
        return privilegeId==null?"false":"true";
    }

    public void setChecked(String check) {
        this.checked = check;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }
}
