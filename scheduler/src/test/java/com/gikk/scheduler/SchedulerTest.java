package com.gikk.scheduler;

//import java.util.Arrays;
//import java.util.List;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//@RunWith(Parameterized.class)
//public class SchedulerTest {
//	
//	@Parameterized.Parameters
//	public static List<Object[]> data(){
//		return Arrays.asList( new Object[1][0] );
//	}
//	
//	@Test
//	public void testScheduler1() throws InterruptedException{
//		Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
//		Scheduler scheduler = new SchedulerBuilder(1).build();
//		
//		long capacity  = scheduler.getCapacity();
//		long completed = scheduler.getCompletedTaskCount();
//		long queue     = scheduler.getScheduledQueueSize();
//		assertTrue( capacity == 1 );
//		assertTrue( completed == 0);
//		assertTrue( queue == 0 );
//	}
//	
//	@Test
//	public void testScheduler2() throws InterruptedException{
//		Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
//		Scheduler scheduler = new SchedulerBuilder(5).build();
//	
//		long capacity  = scheduler.getCapacity();
//		long completed = scheduler.getCompletedTaskCount();
//		long queue     = scheduler.getScheduledQueueSize();
//		long active    = scheduler.getActiveCount();
//		assertTrue( capacity == 5 );
//		assertTrue( completed == 0);
//		assertTrue( queue == 0 );
//		assertTrue( active == 0);
//		
//		//Schedule a task, and wait for it to finish
//		ShortTask st1 = new ShortTask();
//		scheduler.executeTask(st1);
//		
//		Thread.sleep(20);
//		
//		completed = scheduler.getCompletedTaskCount();
//		assertTrue( "Asserting completeCount == 1, is " + completed , completed == 1 );
//		assertTrue( active == 0);
//		
//		//Schedule a delayed task, and make sure it is scheduled
//		scheduler.scheduleDelayedTask(5, st1);
//		queue     = scheduler.getScheduledQueueSize();
//		completed = scheduler.getCompletedTaskCount();
//		assertTrue( queue == 1);
//		assertTrue( completed == 1 );
//		assertTrue( active == 0);
//		
//		//Wait for the task to finish, and make sure it did
//		Thread.sleep(30);
//		queue     = scheduler.getScheduledQueueSize();
//		completed = scheduler.getCompletedTaskCount();
//		assertTrue( "Asserting queueCount == 0, is " + queue   , queue == 0);
//		assertTrue( "Asserting completeCount == 2, is " + completed , completed == 2 );
//		assertTrue( "Asserting activeCount == 0, is " + active , active == 0);
//		
//		//Schedule a long task
//		LongTask lt1 = new LongTask();
//		scheduler.executeTask(lt1);
//		
//		Thread.sleep(5);	//Take some time for the task to start
//		
//		queue     = scheduler.getScheduledQueueSize();
//		completed = scheduler.getCompletedTaskCount();
//		active    = scheduler.getActiveCount();
//		assertTrue( "Asserting queueCount == 0, is " + queue   , queue == 0);
//		assertTrue( "Asserting completeCount == 2, is " + completed , completed == 2 );
//		
//		//Let the long task finish
//		Thread.sleep(50);
//		
//		completed = scheduler.getCompletedTaskCount();
//		active    = scheduler.getActiveCount();
//		assertTrue( "Asserting activeCount == 0, is " + active , active == 0);
//		assertTrue( "Asserting completeCount == 3, is " + completed , completed == 3 );
//		
//		//Dispose of the scheduler
//		scheduler.onProgramExit();
//		assertTrue( scheduler.isDisposed() );
//	}
//	
//	@Test
//	public void testScheduler3() throws InterruptedException{
//		Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
//		Scheduler scheduler = new SchedulerBuilder(1).build();
//		ShortTask task = new ShortTask();
//		
//		scheduler.scheduleRepeatedTask(0, 10, task);
//		
//		Thread.sleep(29); //This should let it finish 3 runs (the initial one + 2 runs)
//		scheduler.onProgramExit();
//		long completed = scheduler.getCompletedTaskCount();
//		assertTrue( "Asserting completeCount <= 4, is " + completed , completed <= 4);
//		
//		
//		//Wait for a while, and make sure that the repeated task doesn't keep on running
//		//Since we cannot be 100% sure if it has executed 3 or 4 times (due to the lack of
//		//real time guarantees on a Scheduler), we settle for checking for 3 or less
//		Thread.sleep(10);
//		completed = scheduler.getCompletedTaskCount();
//		assertTrue( "Asserting completeCount <= 4, is " + completed , completed <= 4);
//		
//		Thread.sleep(100);
//	}
//	
//	@Test
//	public void testScheduler4() throws InterruptedException{
//		Runnable task = new Runnable() {
//			private final long created = System.currentTimeMillis();
//			
//			@Override
//			public void run() {
//				long delta = (System.currentTimeMillis() - created)/1000;
//				System.out.println("Time: " + delta/(60*60) + "h " + (delta/60)%60 +"m " + delta%(60) + "s" );
//			}
//		};
//		Scheduler scheduler = new SchedulerBuilder(1).build();
//		scheduler.scheduleRepeatedTask(1000, 1000, task);
//		
//		Thread.sleep(24 * 60 * 60 * 1000);
//		
//		scheduler.terminate();
//	}
//	
//	class LongTask implements Runnable {
//		public long a;
//		@Override
//		public void run() {
//			for( long i = 0; i < 10000000; i++){
//				a = i;
//			}
//			System.out.println("*LONG RUN*");
//		}	
//	}
//	
//	class ShortTask implements Runnable {
//		@Override
//		public void run() { }	
//	}
//}
