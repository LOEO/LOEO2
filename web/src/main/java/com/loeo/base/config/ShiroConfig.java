package com.loeo.base.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import com.loeo.base.cache.RedisUtils;
import com.loeo.base.config.properties.AppProperties;
import com.loeo.base.shiro.cache.RedisCacheManager;
import com.loeo.base.shiro.filter.SysUserFilter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.loeo.base.event.ResourceUpdateEvent;
import com.loeo.base.shiro.LoeoRealm;
import com.loeo.base.shiro.filter.SysDataFilter;
import com.loeo.base.shiro.filter.SysPermFilter;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2017/05/31 22:44
 */
@Configuration
public class ShiroConfig implements ApplicationRunner, ApplicationListener<ResourceUpdateEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	public static final String HASH_ALGORITHM_NAME = Md5Hash.ALGORITHM_NAME;
	private final SysPermFilter sysPermFilter = new SysDataFilter();

	@Bean
	public Realm realm(CredentialsMatcher credentialsMatcher, CacheManager cacheManager, AppProperties appProperties) {
		AuthorizingRealm realm = new LoeoRealm(appProperties.getAdminId());
		realm.setAuthorizationCacheName(appProperties.getPermCachePrefix());
		realm.setCredentialsMatcher(credentialsMatcher);
		realm.setCacheManager(cacheManager);
		return realm;
	}

	/**
	 * 配置密码比较器
	 */
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		return new HashedCredentialsMatcher(ShiroConfig.HASH_ALGORITHM_NAME);
	}

	@Bean
	public CacheManager cacheManager(AppProperties appProperties, RedisUtils redisUtils) {
		return new RedisCacheManager(appProperties.getPermCacheExpire(), redisUtils);
	}

	/**
	 * 通过ShiroFilterFactoryBean 初始化shiro
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	@Lazy
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager, AppProperties appProperties) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/accessDenied");
		//配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/api/login", "anon,sysPerm");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/hello/**", "anon");
		filterChainDefinitionMap.put("/webSocket", "anon");
		filterChainDefinitionMap.put("/resources/**", "anon");
		filterChainDefinitionMap.put("/actuator/**", "anon");
		filterChainDefinitionMap.put("/**", "sysUser,sysPerm");
		Map<String, Filter> filters = new LinkedHashMap<>();
		sysPermFilter.setLoginApi(appProperties.getLoginApi());
		filters.put("sysUser", new SysUserFilter());
		filters.put("sysPerm", sysPermFilter);
		shiroFilterFactoryBean.setFilters(filters);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager(Realm realm) {
		return new DefaultWebSecurityManager(realm);
	}

	/**
	 * 对shiro中实现了 Initializable 和 Destroyable 接口的类进行初始化和销毁工作
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 会拦截添加下列注解的方法
	 * RequiresPermissions.class, RequiresRoles.class,
	 * RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class
	 *
	 * @param manager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		sysPermFilter.init();
	}

	@Override
	public void onApplicationEvent(ResourceUpdateEvent event) {
		sysPermFilter.updateResource(event);
	}
}
