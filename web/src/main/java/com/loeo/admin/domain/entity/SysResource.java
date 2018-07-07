package com.loeo.admin.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@TableName("sys_resource")
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
	private String dataPermission;
	@NotEmpty(groups = {Add.class, Update.class})
	private String type;
	private String pid;
	private String description;
	private String iconCls;
	private String script;
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	@TableField(fill = FieldFill.INSERT)
	private String creator;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updater;
	private Integer enable;
	private Boolean leaf;
	private Integer sortNo;
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

	public String getDataPermission() {
		return dataPermission;
	}

	public void setDataPermission(String dataPermission) {
		this.dataPermission = dataPermission;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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
