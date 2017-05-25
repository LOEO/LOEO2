package com.loeo;

import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.entity.SysButton;
import com.loeo.mapper.SysButtonMapper;
import com.loeo.mapper.TestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

	@Autowired
	private TestDao testDao;

	@Autowired
	private SysButtonMapper sysButtonMapper;

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
	public void selectByPage() {
		List<com.loeo.entity.Test> testList = testDao.selectPage(new Page<com.loeo.entity.Test>(3, 3), null);
		for (com.loeo.entity.Test t :
				testList) {
			System.out.println(t);
		}
	}

	@Test
	public void select() {
		SysButton sysButton = sysButtonMapper.selectById(1);
		System.out.println(sysButton.getName());
	}

}
