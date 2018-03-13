package com.loeo.base.shiro.permission;

import java.io.Serializable;

/**
 * @author LT(286269159 @ qq.com)
 */
public class DataPermission {
	private Domain domain;
	private Serializable resourceId;
	private Role role;

	public DataPermission(Domain domain, Serializable resourceId, Role role) {
		this.domain = domain;
		this.resourceId = resourceId;
		this.role = role;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Serializable getResourceId() {
		return resourceId;
	}

	public void setResourceId(Serializable resourceId) {
		this.resourceId = resourceId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean authorize() {
		return domain.getDataPermissionAuthorizer().authorize(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataPermission that = (DataPermission) o;

		if (domain != that.domain) return false;
		if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
		return role == that.role;
	}

	@Override
	public int hashCode() {
		int result = domain != null ? domain.hashCode() : 0;
		result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}
}