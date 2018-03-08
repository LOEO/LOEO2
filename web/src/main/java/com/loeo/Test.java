package com.loeo;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.loeo.base.config.ShiroConfig;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-02 11:04:56
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class Test extends HashedCredentialsMatcher {
	public static void main(String[] args) {
		Test test = new Test();
		test.setHashAlgorithmName(ShiroConfig.HASH_ALGORITHM_NAME);
		System.out.println(test.hashProvidedCredentials("123", "1", 1));
		System.out.println(new SimpleHash(ShiroConfig.HASH_ALGORITHM_NAME, "123123", "admin1", 1));
	}
}
