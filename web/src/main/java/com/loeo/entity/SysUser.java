package com.loeo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_sys_user")
public class SysUser extends Model<SysUser> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	@NotNull(groups = Update.class)
	private String id;
	@NotEmpty(groups = Add.class)
	@Size(max = 20)
	private String username;
	@NotEmpty(groups = {Add.class, Update.class})
	@Size(min = 6)
	private String password;
	@NotEmpty(groups = Add.class)
	@Size(max = 20, groups = {Add.class, Update.class})
	private String nickname;
	private Integer age;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotEmpty(groups = Add.class)
	private Date birthday;
	@NotEmpty(groups = Add.class)
	private String sex;
	private String avatar;
	@NotEmpty(groups = Add.class)
	private String email;
	@NotEmpty(groups = Add.class)
	private String phone;
	private Integer orgId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateUser;
	private Integer enable;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
