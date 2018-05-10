package com.loeo.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-03 17:47:06
 */
@ConfigurationProperties("app.websocket")
public class WebSocketProperties {
	private String stompEndpoint;
	private String destPrefix;
	@NestedConfigurationProperty
	private Topics topics;

	public String getStompEndpoint() {
		return stompEndpoint;
	}

	public void setStompEndpoint(String stompEndpoint) {
		this.stompEndpoint = stompEndpoint;
	}

	public String getDestPrefix() {
		return destPrefix;
	}

	public void setDestPrefix(String destPrefix) {
		this.destPrefix = destPrefix;
	}

	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public static class Topics{
		private String swipingCard;

		public String getSwipingCard() {
			return swipingCard;
		}

		public void setSwipingCard(String swipingCard) {
			this.swipingCard = swipingCard;
		}
	}
}
