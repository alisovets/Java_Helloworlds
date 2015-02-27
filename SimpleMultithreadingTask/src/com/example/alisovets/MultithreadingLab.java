package com.example.alisovets;

public class MultithreadingLab {
	private static int NUMBER_OF_WRITER_THREADS = 5;
	private static int NUMBER_OF_TRANSFER_THREADS = 2;
	private static int NUMBER_OF_MAIN_ITERATOINS = 100;
	
	
	public static void main(String[] args){
		LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<String>(10);
		LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<String>(15);

		Thread[] writerThreads = new Thread[NUMBER_OF_WRITER_THREADS];
		Thread[] transferThreads = new Thread[NUMBER_OF_TRANSFER_THREADS];
		
		for(int i = 0; i < NUMBER_OF_WRITER_THREADS; i++ ){
			writerThreads[i] = new QueueWriterThread("Wreater thread # " + (i + 1), queue1);
			
		}
		
		for(int i = 0; i < NUMBER_OF_TRANSFER_THREADS; i++ ){
			transferThreads[i] = new QueueTrasferThread("Transfer thread # " + (i + 1), queue1, queue2);
			
		}
		
		for(int i = 0; i < NUMBER_OF_WRITER_THREADS; i++ ){
			writerThreads[i].start();
		}
		
		for(int i = 0; i < NUMBER_OF_TRANSFER_THREADS; i++ ){
			transferThreads[i].start();
		}
		
		for(int i = 0; i < NUMBER_OF_MAIN_ITERATOINS; i++){
			System.out.println(queue2.read());
		}
		
	}

	
	private static class QueueTrasferThread extends Thread{
		LinkedBlockingQueue<String> inQueue;
		LinkedBlockingQueue<String> outQueue;
		
		QueueTrasferThread(String name, LinkedBlockingQueue<String> inQueue, LinkedBlockingQueue<String> outQueue){
			super(name);
			this.inQueue = inQueue;
			this.outQueue = outQueue;
			setDaemon(true);
		}
		
		public void run(){
			while(true){
				String message = getName() + " transfers message: '" + inQueue.read() + "'";
				outQueue.write(message);
			}
		}	
	}
	
	private static class QueueWriterThread extends Thread{
		LinkedBlockingQueue<String> queue;
		
		QueueWriterThread(String name, LinkedBlockingQueue<String> queue){
			super(name);
			this.queue = queue;
			setDaemon(true);
		}
		
		public void run(){
			while(true){
				String message = getName() + " generates this message";
				queue.write(message);
			}
		}	
	}
}
