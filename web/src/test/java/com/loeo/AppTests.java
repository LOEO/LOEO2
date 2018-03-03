package com.loeo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.loeo.entity.SysRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

	@Test
	public void contextLoads() {
		//testDao.insert(new com.loeo.entity.Test(1, "22"));
	}

	@Test
	public void add() {
		/*for (int i = 0; i < 10; i++) {
			new com.loeo.entity.Test(i, "333").insert();
		}*/
	}

	@Test
	public void delete() {
		/*new com.loeo.entity.Test(2, "222").deleteById();*/
	}

	@Test
	@Rollback
	public void selectByPage() {
		SysRole sysRole = new SysRole();
		sysRole.setName("nnnnn");
		sysRole.setCreateDt(new Date());
		sysRole.setUpdateDt(new Date());
		sysRole.setCreateUser("1");
		sysRole.setUpdateUser("1");
		sysRole.setDescp("ssss");
		sysRole.setEnable(0);
		sysRole.setCode("ss");
		Assert.isTrue(sysRole.insert(),"true");
		/*List<com.loeo.entity.Test> testList = testDao.selectPage(new Page<com.loeo.entity.Test>(3, 3), null);
		for (com.loeo.entity.Test t :
				testList) {
			System.out.println(t);
		}*/
	}

	@Test
	@Transactional
	public void selectByPage1() {
		SysRole sysRole = new SysRole();
		sysRole.setName("nnnnn");
		sysRole.setCreateDt(new Date());
		sysRole.setUpdateDt(new Date());
		sysRole.setCreateUser("1");
		sysRole.setUpdateUser("1");
		sysRole.setDescp("ssss");
		sysRole.setEnable(0);
		sysRole.setCode("ss");
		Assert.isTrue(sysRole.insert(),"true");
		/*List<com.loeo.entity.Test> testList = testDao.selectPage(new Page<com.loeo.entity.Test>(3, 3), null);
		for (com.loeo.entity.Test t :
				testList) {
			System.out.println(t);
		}*/
	}


}
