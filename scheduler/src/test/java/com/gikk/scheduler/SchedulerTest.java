package com.gikk.scheduler;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SchedulerTest {
	
	@Test
	public void testScheduler1() throws InterruptedException{
		Scheduler scheduler = new SchedulerBuilder(1).build();
		
		long capacity  = scheduler.getCapacity();
		long completed = scheduler.getCompletedTaskCount();
		long queue     = scheduler.getScheduledQueueSize();
		assertTrue( capacity == 1 );
		assertTrue( completed == 0);
		assertTrue( queue == 0 );
	}
	
	@Test
	public void testScheduler2() throws InterruptedException{
		Scheduler scheduler = new SchedulerBuilder(5).build();
	
		long capacity  = scheduler.getCapacity();
		long completed = scheduler.getCompletedTaskCount();
		long queue     = scheduler.getScheduledQueueSize();
		long active    = scheduler.getActiveCount();
		assertTrue( capacity == 5 );
		assertTrue( completed == 0);
		assertTrue( queue == 0 );
		assertTrue( active == 0);
		
		//Schedule a task, and wait for it to finish
		ShortTask st1 = new ShortTask();
		scheduler.executeTask(st1);
		
		Thread.sleep(5);
		
		completed = scheduler.getCompletedTaskCount();
		assertTrue( completed == 1 );
		assertTrue( active == 0);
		
		//Schedule a delayed task, and make sure it is scheduled
		scheduler.scheduleDelayedTask(5, st1);
		queue     = scheduler.getScheduledQueueSize();
		completed = scheduler.getCompletedTaskCount();
		assertTrue( queue == 1);
		assertTrue( completed == 1 );
		assertTrue( active == 0);
		
		//Wait for the task to finish, and make sure it did
		Thread.sleep(10);
		queue     = scheduler.getScheduledQueueSize();
		completed = scheduler.getCompletedTaskCount();
		assertTrue( queue == 0);
		assertTrue( completed == 2 );
		assertTrue( active == 0);
		
		//Schedule a long task
		LongTask lt1 = new LongTask();
		scheduler.executeTask(lt1);
		
		Thread.sleep(1);	//Take some time for the task to start
		
		queue     = scheduler.getScheduledQueueSize();
		completed = scheduler.getCompletedTaskCount();
		active    = scheduler.getActiveCount();
		assertTrue( active == 1);
		assertTrue( queue == 0);
		assertTrue( completed == 2 );
		
		//Let the long task finish
		Thread.sleep(10);
		
		completed = scheduler.getCompletedTaskCount();
		active    = scheduler.getActiveCount();
		assertTrue( active == 0);
		assertTrue( completed == 3 );
		
		//Dispose of the scheduler
		scheduler.onProgramExit();
		assertTrue( scheduler.isDisposed() );
	}
	
	class LongTask implements GikkTask {
		public long a;
		@Override
		public void onExecute() {
			for( long i = 0; i < 100000; i++){
				a = i;
			}
		}	
	}
	
	class ShortTask implements GikkTask {
		@Override
		public void onExecute() { }	
	}
}
