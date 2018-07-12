package com.loeo.admin.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.loeo.base.IdEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("sys_user_org")
public class SysUserOrg extends Model<SysUserOrg> implements IdEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	private String id;
	private String orgId;
	private String userId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
