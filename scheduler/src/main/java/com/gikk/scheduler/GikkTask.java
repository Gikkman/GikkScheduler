package com.gikk.scheduler;

/**A {@code Task} is a job that will be performed on a thread within a {@link Scheduler}.
 * Tasks can be scheduled to be executed as soon as possible, or after a delay; one time or several times.
 * 
 * @author Gikkman
 *
 */
@FunctionalInterface
public interface GikkTask {
	public void onExecute();
}
