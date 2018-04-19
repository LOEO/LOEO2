package com.loeo.common.uniquekey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：
 * 	tweeter的snowflake 移植到Java:
 *   (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 *       注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id
 *   (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 *
 * @author ：renzhongshan(zhongshan.ren@ihydt.com)
 * @create ：2017-03-28 11:02:49
 * @version ：2017 Version：1.0

 */
public class IdWorker implements IIdGenerator {
	private final static Logger logger = LoggerFactory.getLogger(IdWorker.class);
	private final long workerId;
	/**
	 * 时间起始标记点，作为基准，一般取系统的最近时间
	 */
	private final long epoch = 1403854494756L;
	/**
	 *  机器标识位数
	 */
	private final long workerIdBits = 10L;
	/**
	 * 机器ID最大值: 1023
	 */
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
	/**
	 * 0，并发控制
	 */
	private long sequence = 0L;
	/**
	 * 毫秒内自增位
	 */
	private final long sequenceBits = 12L;
	/**
	 * 12
	 */
	private final long workerIdShift = this.sequenceBits;
	/**
	 * 22
	 */
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
	/**
	 * 4095,111111111111,12位
	 */
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
	private long lastTimestamp = -1L;

	private static IdWorker flowIdWorker = new IdWorker(1);

	private IdWorker(long workerId) {
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}
	public static IdWorker getFlowIdWorkerInstance() {
		return flowIdWorker;
	}

	public static IdWorker getFlowIdWorkerInstance(long workerId) {
		return new IdWorker(workerId);
	}

	@Override
	public synchronized Long nextId() {
		long timestamp = System.currentTimeMillis();
		// 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
		if (this.lastTimestamp == timestamp) {
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				// 重新生成timestamp
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			logger.error("clock moved backwards.Refusing to generate id for {} milliseconds", (this.lastTimestamp - timestamp));
			throw new RuntimeException(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
		}

		this.lastTimestamp = timestamp;
		return (timestamp - this.epoch) << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
	}

	/**
	 * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = System.currentTimeMillis();
		while (timestamp <= lastTimestamp) {
			timestamp = System.currentTimeMillis();
		}
		return timestamp;
	}
}