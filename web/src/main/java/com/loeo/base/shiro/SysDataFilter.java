package com.loeo.base.shiro;

import java.io.IOException;
import java.util.regex.Matcher;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.loeo.base.exception.DataPermissionResolveException;
import com.loeo.domain.entity.SysResource;
import com.loeo.service.ShiroService;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-08 17:15:01
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class SysDataFilter extends SysPermFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		if (super.isAccessAllowed(request, response, mappedValue)) {
			Matcher matcher = super.getCurPathMatcher();
			SysResource sysResource = super.getCurSysResource();
			String script = sysResource.getScript();
			DataPermission dataPermission = resolveDataPermission(script);
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		super.init();

	}

	/**
	 * 域:正则中的group索引:职责
	 * org:1:member
	 *
	 * @param script
	 */
	private DataPermission resolveDataPermission(String script) {
		String[] scriptArr = script.split(ShiroService.PART_DIVIDER_TOKEN);
		if (scriptArr.length == 3) {
			return new DataPermission(Domain.valueOf(scriptArr[1]), Integer.valueOf(scriptArr[1]), Responsibility.valueOf(scriptArr[2]));
		}
		throw new DataPermissionResolveException();
	}

	private static class DataPermission {
		private Domain domain;
		private int groupIndex;
		private Responsibility responsibility;

		public DataPermission(Domain domain, int groupIndex, Responsibility responsibility) {
			this.domain = domain;
			this.groupIndex = groupIndex;
			this.responsibility = responsibility;
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

		public Responsibility getResponsibility() {
			return responsibility;
		}

		public void setResponsibility(Responsibility responsibility) {
			this.responsibility = responsibility;
		}
	}

	private enum Domain {
		/**
		 * 用户
		 */
		USER,
		/**
		 * 组织
		 */
		ORG
	}

	private enum Responsibility {
		/**
		 * 创建者
		 */
		OWNER,
		/**
		 * 管理员
		 */
		ADMIN,
		/**
		 * 成员
		 */
		MEMBER
	}

}
