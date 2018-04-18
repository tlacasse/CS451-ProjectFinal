package start;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledPool {

	public static void main(String[] args){
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
		exec.schedule(callable, delay, unit)
		exec.shutdown();
	}
	
}
