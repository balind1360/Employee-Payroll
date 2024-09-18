/**
 * Name: Timer                                 Last Updated: 11/18/24
 * 
 * Creator: E. Blocke
 * 
 * Purpose: Class to set up a Timer
 */


public class Timer{
	long start;
	long end;
	boolean running;

	public Timer (){
		start = 0;
		running = false;
		end = 0;
	}
	//sets the start back to 0
	public void reset (){
		start = 0;
	}
	//sets the clock to the current time
	public void setClock (){
		start = System.currentTimeMillis() / (1000 * 60 * 60);
	}
	//starts timer
	public void startClock (){
		running = true;
		start = System.currentTimeMillis() / (1000 * 60 * 60);
	}
	//stops timer
	public void stopClock (){
		running = false;
		end = System.currentTimeMillis() / (1000 * 60 * 60);
	}
	//finds difference between end and start time
	public long timeElapsed (){
		return end - start;
	}
}
