package mdj2.bigspace.engine.threading;

import mdj2.bigspace.engine.services.IService;

public class NullTaskManager implements IService, ITaskManager {

	@Override
	public void runTask(Runnable task) {
		// TODO Auto-generated method stub
		System.err.println("[NullTaskManager] runTask() being called!");
	}

	@Override
	public void startService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub
		
	}

}
