package com.loeo.base.event;

import org.springframework.context.ApplicationEvent;

import com.loeo.admin.domain.entity.SysResource;

/**
 * 功能：资源更新事件类
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-26 09:46:07
 * @version ：2018 Version：1.0

 */
public class ResourceUpdateEvent extends ApplicationEvent {
	private Action action;

	public ResourceUpdateEvent(SysResource source) {
		super(source);
	}

	public ResourceUpdateEvent(SysResource source,Action action) {
		super(source);
		this.action = action;
	}

	@Override
	public SysResource getSource() {
		return (SysResource) super.source;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public enum Action{
		/**
		 * 添加资源
		 */
		ADD,
		/**
		 * 修改资源
		 */
		UPDATE,
		/**
		 * 删除资源
		 */
		DELETE
	}
}
