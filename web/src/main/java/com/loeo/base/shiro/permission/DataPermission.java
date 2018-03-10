package com.loeo.base.shiro.permission;

/**
 * @author LT(286269159 @ qq.com)
 */
public class DataPermission{
		private Domain domain;
		private int groupIndex;
		private Role role;

		public DataPermission(Domain domain, int groupIndex, Role role) {
			this.domain = domain;
			this.groupIndex = groupIndex;
			this.role = role;
		}

		public Domain getDomain() {
			return domain;
		}

		public void setDomain(Domain domain) {
			this.domain = domain;
		}

		public int getGroupIndex() {
			return groupIndex;
		}

		public void setGroupIndex(int groupIndex) {
			this.groupIndex = groupIndex;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataPermission that = (DataPermission) o;

		if (groupIndex != that.groupIndex) return false;
		if (domain != that.domain) return false;
		return role == that.role;
	}

	@Override
	public int hashCode() {
		int result = domain != null ? domain.hashCode() : 0;
		result = 31 * result + groupIndex;
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}
}