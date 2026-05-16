package net.mcreator.wealizh.procedures;

public class TextEOrganizationValueReturnProcedure {
	public static String execute(double n) {
		if (0 == n) {
			return "0 \u00A77E*\u00A7r";
		} else if (Math.pow(10, -15) > Math.abs(n)) {
			return n / Math.pow(10, -18) + " \u00A73pE\u00A7r";
		} else if (Math.pow(10, -12) > Math.abs(n)) {
			return n / Math.pow(10, -15) + " \u00A7bAE\u00A7r";
		} else if (Math.pow(10, -9) > Math.abs(n)) {
			return n / Math.pow(10, -12) + " \u00A7bFE\u00A7r";
		} else if (Math.pow(10, -6) > Math.abs(n)) {
			return n / Math.pow(10, -9) + " \u00A7bNE\u00A7r";
		} else if (Math.pow(10, -3) > Math.abs(n)) {
			return n / Math.pow(10, -6) + " \u00A7b\u03BCE\u00A7r";
		} else if (1 > Math.abs(n)) {
			return n / Math.pow(10, -3) + " \u00A7bmE\u00A7r";
		} else if (Math.pow(10, 3) > Math.abs(n)) {
			return n + " \u00A7fE\u00A7r";
		} else if (Math.pow(10, 6) > Math.abs(n)) {
			return n / Math.pow(10, 3) + " \u00A7eKE\u00A7r";
		} else if (Math.pow(10, 9) > Math.abs(n)) {
			return n / Math.pow(10, 6) + " \u00A7eME\u00A7r";
		} else if (Math.pow(10, 12) > Math.abs(n)) {
			return n / Math.pow(10, 9) + " \u00A7eGE\u00A7r";
		} else if (Math.pow(10, 15) > Math.abs(n)) {
			return n / Math.pow(10, 12) + " \u00A7eTE\u00A7r";
		} else if (Math.pow(10, 18) > Math.abs(n)) {
			return n / Math.pow(10, 15) + " \u00A7ePE\u00A7r";
		} else if (Math.pow(10, 21) > Math.abs(n)) {
			return n / Math.pow(10, 18) + " \u00A7eXE\u00A7r";
		} else if (Math.pow(10, 24) > Math.abs(n)) {
			return n / Math.pow(10, 21) + " \u00A7eZE\u00A7r";
		} else if (Math.pow(10, 27) > Math.abs(n)) {
			return n / Math.pow(10, 24) + " \u00A7eYE\u00A7r";
		} else if (Math.pow(10, 30) > Math.abs(n)) {
			return n / Math.pow(10, 27) + " \u00A7eRE\u00A7r";
		} else if (Math.pow(10, 33) > Math.abs(n)) {
			return n / Math.pow(10, 30) + " \u00A7eQE\u00A7r";
		} else if (Math.pow(10, 36) > Math.abs(n)) {
			return n / Math.pow(10, 33) + " \u00A76HE\u00A7r";
		} else if (Math.pow(10, 39) > Math.abs(n)) {
			return n / Math.pow(10, 36) + " \u00A76UE\u00A7r";
		} else if (Math.pow(10, 42) > Math.abs(n)) {
			return n / Math.pow(10, 39) + " \u00A76CE\u00A7r";
		} else if (!(Double.POSITIVE_INFINITY == n || Double.NEGATIVE_INFINITY == Math.abs(n) || Double.NaN == Math.abs(n))) {
			return n / Math.pow(10, 42) + " \u00A74fE\u00A7r";
		}
		return "\u00A75" + n + " E*\u00A7r";
	}
}