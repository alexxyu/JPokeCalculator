package pokemon;

public class CaptureCalc {

	public CaptureCalc(){
		
	}
	
	public static double calc(int currentHP, int catchRate, int maxHP, double ballMod, double statusMod, double capturePowerMod){

		return (ballMod * statusMod * capturePowerMod * catchRate * (3 * maxHP - 2 * currentHP)) / (3 * maxHP * 255) * 100;
		// return Math.min(255, down(round(down(round(round((3 * maxHP - 2 * currentHP)) * catchRate * ballMod) / (3 * maxHP)) * statusMod) * capturePowerMod / 100));
	}
	
	/*
	private static double down(double x) {
		// Rounds down to the nearest 1/4096th
		return Math.floor(x * 4096) / 4096;
	}

	private static double round(double x) {
		// Rounds to the nearest 1/4096th
		return Math.round(x * 4096) / 4096;
	}
	*/

}
