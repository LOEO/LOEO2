package com.loeo.common.uniquekey;

/**
 * 功能：
 *
 * @author：renzhongshan(zhongshan.ren@ihydt.com)
 * @create：2017-03-29 10:19:56
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class EpmWorkerIdGenerator implements IIdGenerator {
	private IdWorker idWorker;

	public EpmWorkerIdGenerator(){
		this.idWorker = IdWorker.getFlowIdWorkerInstance();
	}

	public EpmWorkerIdGenerator(long workerId){
		this.idWorker = IdWorker.getFlowIdWorkerInstance(workerId);
	}

	@Override
	public synchronized Long nextId() {
		return this.idWorker.nextId();
	}
}
