package com.loeo.common.uniquekey;

import java.nio.ByteBuffer;

import com.loeo.common.utils.ChStringUtils;

/**
 * 功能：数据库主键生成器接口
 *
 * @author：renzhongshan(zhongshan.ren@ihydt.com)
 * @create：2017-03-29 9:29:25
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface IIdGenerator {
	Long nextId();

	default String nextStringId(){
		/**
		 * 0123456789abcdefghijklmnopqrstuvwxyz
		 * 36进制后，不足12位的高位补0
		 */
		return ChStringUtils.highFill0(Long.toString(this.nextId(), 36), 12);
	}

	/**
	 * 暂时没用，将删除
	 * @param l
	 * @return
	 */
	@Deprecated
	default byte[] longToBytes(Long l) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(l);
		return buffer.array();
	}
}
