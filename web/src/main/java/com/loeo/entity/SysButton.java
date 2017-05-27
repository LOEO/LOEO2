package com.loeo.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("t_sys_button")
public class SysButton extends Model<SysButton> {

	private static final long serialVersionUID = 1L;


	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 按钮名称
	 */
	private String name;
	/**
	 * 按钮类型
	 */
	private String cls;
	/**
	 * 按钮图标
	 */
	private String iconCls;
	/**
	 * 按钮脚本
	 */
	private String script;
	/**
	 * 菜单id
	 */
	private Integer menuId;
	/**
	 * 排序
	 */
	private Integer orde;
	/**
	 * 按钮状态
	 */
	private Integer enable;
	private Integer createUser;
	private Date createDt;
	private Integer updateUser;
	private Date updateDt;


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

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
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

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getOrde() {
		return orde;
	}

	public void setOrde(Integer orde) {
		this.orde = orde;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysButton{" +
				"id=" + id +
				", name='" + name + '\'' +
				", cls='" + cls + '\'' +
				", iconCls='" + iconCls + '\'' +
				", script='" + script + '\'' +
				", menuId=" + menuId +
				", orde=" + orde +
				", enable=" + enable +
				", createUser=" + createUser +
				", createDt=" + createDt +
				", updateUser=" + updateUser +
				", updateDt=" + updateDt +
				'}';
	}

}
