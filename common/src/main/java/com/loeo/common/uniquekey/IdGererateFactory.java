package com.loeo.common.uniquekey;

/**
 * 功能：
 *
 * @author：renzhongshan(zhongshan.ren@ihydt.com)
 * @create：2017-03-29 10:40:20
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public final class IdGererateFactory implements IIdGenerator {
	private IdGererateFactory(){

	}
	private IIdGenerator idGenerator;
	public IdGererateFactory(Long workerId){
		this.idGenerator = new EpmWorkerIdGenerator(workerId);
	}
	public IdGererateFactory(Long workerId,Long dataCenterId){
		if(dataCenterId == null){
			this.idGenerator = new EpmWorkerIdGenerator(workerId);
		}else{
			this.idGenerator = new SnowflakeIdGenerator(workerId,dataCenterId);
		}
	}

	@Override
	public Long nextId() {
		return this.idGenerator.nextId();
	}
}
