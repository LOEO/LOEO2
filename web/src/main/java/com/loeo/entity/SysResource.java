package com.loeo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.loeo.utils.validate.group.Add;
import com.loeo.utils.validate.group.Update;


/**
 * <p>
 * <p>
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("t_sys_resource")
public class SysResource extends Model<SysResource> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	@NotNull(groups = Update.class)
	private String id;
	@NotEmpty(groups = {Add.class, Update.class})
	private String name;
	private String api;
	private String method;
	@NotEmpty(groups = {Add.class, Update.class})
	private String type;
	private String pid;
	private String descp;
	private String iconCls;
	private String script;
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateUser;
	private Integer enable;
	private Integer isLeaf;
	private Integer orde;
	@TableField(exist = false)
	private Boolean checked;


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

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getOrde() {
		return orde;
	}

	public void setOrde(Integer orde) {
		this.orde = orde;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
