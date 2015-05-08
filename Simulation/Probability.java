import java.util.*;
public class Probability{
	static Random r = new Random();
	public static int random(int[] choices, int[] distribution){

		int sum = 0;
		for(int i = 0; i < distribution.length; i++){
			sum += distribution[i];
		}

		int rnd = r.nextInt(sum);
		sum = 0;
		for(int i = 0; i < distribution.length; i++){
			sum += distribution[i];
			if(sum > rnd) return choices[i];
		}
		return -1;
	}
	public static int normal(int mean, int variance){
		double d = r.nextGaussian();
		d *= variance/4;
		int val = (int)(d) + mean;
		if(val < mean - variance) val = mean - variance;
		else if(val > mean + variance) val = mean + variance;
		return val;
	}
	public static double normalize(double val, double mean){
		return (Math.atan(Math.abs(val)/mean))/(Math.PI/2);
	}
	public static int tails(int min, int variance){
		int mean = Probability.normal(min, variance);
		if(mean < min) mean = (min) + (min - mean);
		return mean;
	}
}