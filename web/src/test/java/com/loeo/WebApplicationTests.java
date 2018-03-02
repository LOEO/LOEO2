package com.loeo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.loeo.entity.SysUser;
import com.loeo.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {
	@Resource
	private SysUserService sysUserService;
	@Test
	public void contextLoads() {
		SysUser sysUser = new SysUser();
		sysUser.setId(36);
		sysUser.setNickname("bbb");
		sysUserService.updateById(sysUser);
	}

}
