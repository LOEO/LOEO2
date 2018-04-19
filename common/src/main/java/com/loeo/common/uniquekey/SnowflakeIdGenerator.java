package com.loeo.common.uniquekey;

/**
 * 功能：
 *
 * @author ：renzhongshan(zhongshan.ren@ihydt.com)
 * @create ：2017-03-29 10:35:24
 * @version ：2017 Version：1.0

 */
public class SnowflakeIdGenerator implements IIdGenerator {
	private Snowflake snowflake;
	public SnowflakeIdGenerator(long workerId, long datacenterId){
		this.snowflake = new Snowflake(workerId,datacenterId);
	}

	@Override
	public synchronized Long nextId() {
		return this.snowflake.nextId();
	}
}
