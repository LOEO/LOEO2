package com.loeo.common.uniquekey;

/**
 * 功能：生成唯一主键
 *
 * @author：renzhongshan(zhongshan.ren@ihydt.com)
 * @create：2017-03-17 9:57:44
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class Snowflake implements IIdGenerator {
	private static final long twepoch = 1288834974657L;

	private static final long workerIdBits = 5L;
	/**
	 * 注意：这个值不要随意改动:
	 * 用1时数据库36进制后70年内最大长度为12
	 * 用5时数据库36进制后70年内最大长度为13
	 */
	private static final long datacenterIdBits = 1L;
	private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private static final long sequenceBits = 12L;

	private static final long workerIdShift = sequenceBits;
	private static final long datacenterIdShift = sequenceBits + workerIdBits;
	private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long lastTimestamp = -1L;

	/**
	 *
	 */
	private long workerId = 0;
	private long datacenterId = 0;
	private long sequence = 0L;

	/**
	 *
	 * @param workerId
	 * @param datacenterId
	 */
	public Snowflake(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0.", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenterId can't be greater than %d or less than 0.", maxDatacenterId));
		}

		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}
	/**
	 *
	 * @return
	 */
	public long getWorkerId() {
		return workerId;
	}

	/**
	 *
	 * @return
	 */
	public long getDatacenterId() {
		return datacenterId;
	}

	/**
	 *
	 * @return
	 */
	public long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 *
	 * @return
	 */
	@Override
	public synchronized Long nextId() {
		long id = 0L;
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format("clock moved backwards. refusing to generate id for %d milliseconds.", lastTimestamp - timestamp));
		}
		if (timestamp == lastTimestamp) {
			sequence = (1 + sequence) & sequenceMask;
			if (0 == sequence) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		id = ((timestamp - twepoch) << timestampLeftShift) |
				(datacenterId << datacenterIdShift) |
				(workerId << workerIdShift) |
				sequence;
		return id;
	}

	/**
	 *
	 * @param lastTimestamp
	 * @return
	 */
	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 *
	 * @return
	 */
	protected long timeGen() {
		return System.currentTimeMillis();
	}
}
