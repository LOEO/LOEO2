package com.loeo.dto;

/**
 * Created by LT on 2016/10/03 0:08
 */
public class MenuAndButton {
    private Integer id;
    private String name;
    private String iconCls;
    private String type;
    private Integer pid;
    private Integer privilegeId;
    private String checked;

    public String getChecked() {
        return privilegeId==null?"false":"true";
    }

    public void setChecked(String check) {
        this.checked = check;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}
