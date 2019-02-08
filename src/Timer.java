
public class Timer {
	private int frames;
	private int max;
	private int oFrames;
	
	public Timer(int s)
	{
		frames = s * 30 - 1;
		oFrames = frames;
		max = s;
	}
	
	public void startTimer()
	{
		if(frames != 0)
		{
			frames --;
		}
	}
	
	public int getTime()
	{
		return frames / 30; 
	}
	
	public void resetTimer()
	{
		frames = max * 30 - 1;
	}
	
	public int getFrames()
	{
		return frames;
	}
	
	public int getOFrames(){
		return oFrames;
	}
	
	public void setTime(int t){
		frames = 30 * t - 1;
	}
}
