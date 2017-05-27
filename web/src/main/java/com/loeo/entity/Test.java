package com.loeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-05-25 16:52:53
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Test extends Model<Test> {
	@TableId("id")
	private Integer id;
	@TableField("name")
	private String name;

	@Override
	protected Serializable pkVal() {
		return id;
	}

	@Override
	public String toString() {
		return "Test{" +
				"id=" + id +
				", name='" + name + '\'' +
				"} " + super.toString();
	}
}
