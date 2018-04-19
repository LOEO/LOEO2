package com.loeo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-03-02 11:04:56
 * @version ：2018 Version：1.0

 */
public class Test extends HashedCredentialsMatcher {
	public static void main(String[] args) {
		/*Test test = new Test();
		test.setHashAlgorithmName(ShiroConfig.HASH_ALGORITHM_NAME);
		System.out.println(test.hashProvidedCredentials("123", "1", 1));
		System.out.println(new SimpleHash(ShiroConfig.HASH_ALGORITHM_NAME, "123123", "admin1", 1));*/
		Pattern pattern = Pattern.compile("/api/users/1(\\d+)/menus");
		Matcher matcher = pattern.matcher("/api/users/11231231/menus");
	}
}
