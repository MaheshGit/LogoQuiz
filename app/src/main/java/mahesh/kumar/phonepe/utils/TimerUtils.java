package mahesh.kumar.phonepe.utils;

/**
 * Pause timer in onPause of activity to control pause and play of game
 * This class will be used to get time
 */
public class TimerUtils {
    private static  long startTime;
    private static long endTime;

    public static void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public static void endTime(){
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime(){
        return endTime - startTime;
    }
}
