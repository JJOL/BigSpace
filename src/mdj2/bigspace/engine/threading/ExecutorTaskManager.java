package mdj2.bigspace.engine.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mdj2.bigspace.engine.services.IService;

public class ExecutorTaskManager implements IService, ITaskManager{

	private ExecutorService executor;
	private boolean active;
	
	public ExecutorTaskManager() {
		executor = Executors.newCachedThreadPool();
		active = false;
	}

	@Override
	public void runTask(Runnable task) {
		if (active)
			executor.execute(task);
	}

	@Override
	public void startService() {
		active = true;
	}

	@Override
	public void stopService() {
		active = false;
	}
}
