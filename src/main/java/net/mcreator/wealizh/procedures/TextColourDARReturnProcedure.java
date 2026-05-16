package net.mcreator.wealizh.procedures;

public class TextColourDARReturnProcedure {
	public static String execute(double mv, double v) {
		double mvu = 0;
		mvu = mv / 5;
		if (0 >= v) {
			return "\u00A77";
		} else if (mvu >= v) {
			return "\u00A7b";
		} else if (mvu * 2 >= v) {
			return "\u00A7a";
		} else if (mvu * 3 >= v) {
			return "\u00A7e";
		} else if (mvu * 4 >= v) {
			return "\u00A76";
		} else if (mv >= v) {
			return "\u00A74";
		}
		return "\u00A78";
	}
}