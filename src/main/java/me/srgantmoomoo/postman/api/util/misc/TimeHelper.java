package me.srgantmoomoo.postman.api.util.misc;

public class TimeHelper {
	
	private static long lastMS = 0L;
	
	public boolean isDelayComplete(float f) {
		if(System.currentTimeMillis() - TimeHelper.lastMS >= f) {
			return true;
		}
		return false;
	}
	
	public static long getCurrentMS() {
		return System.nanoTime() / 1000000L;
	}
	
	public void setLastMS(long lastMS) {
		TimeHelper.lastMS = System.currentTimeMillis();
	}
	
	public int convertToMS(int perSecond) {
		return 1000 / perSecond;
	}
	
	public boolean hasReached(long milliseconds) {
		return getCurrentMS() - lastMS >= milliseconds;
	}
	
	public void reset() {
		lastMS = getCurrentMS();
	}

}
