package net.mcreator.wealizh.procedures;

public class TextLOrganizationValueReturnProcedure {
	public static String execute(double n) {
		if (0 == n) {
			return "0 \u00A77L*\u00A7r";
		} else if (Math.pow(10, -15) > Math.abs(n)) {
			return n / Math.pow(10, -18) + " \u00A73pL\u00A7r";
		} else if (Math.pow(10, -12) > Math.abs(n)) {
			return n / Math.pow(10, -15) + " \u00A7bAL\u00A7r";
		} else if (Math.pow(10, -9) > Math.abs(n)) {
			return n / Math.pow(10, -12) + " \u00A7bFL\u00A7r";
		} else if (Math.pow(10, -6) > Math.abs(n)) {
			return n / Math.pow(10, -9) + " \u00A7bNL\u00A7r";
		} else if (Math.pow(10, -3) > Math.abs(n)) {
			return n / Math.pow(10, -6) + " \u00A7b\u03BCL\u00A7r";
		} else if (1 > Math.abs(n)) {
			return n / Math.pow(10, -3) + " \u00A7bmL\u00A7r";
		} else if (Math.pow(10, 3) > Math.abs(n)) {
			return n + " \u00A7fE\u00A7r";
		} else if (Math.pow(10, 6) > Math.abs(n)) {
			return n / Math.pow(10, 3) + " \u00A7eKL\u00A7r";
		} else if (Math.pow(10, 9) > Math.abs(n)) {
			return n / Math.pow(10, 6) + " \u00A7eML\u00A7r";
		} else if (Math.pow(10, 12) > Math.abs(n)) {
			return n / Math.pow(10, 9) + " \u00A7eGL\u00A7r";
		} else if (Math.pow(10, 15) > Math.abs(n)) {
			return n / Math.pow(10, 12) + " \u00A7eTL\u00A7r";
		} else if (Math.pow(10, 18) > Math.abs(n)) {
			return n / Math.pow(10, 15) + " \u00A7ePL\u00A7r";
		} else if (Math.pow(10, 21) > Math.abs(n)) {
			return n / Math.pow(10, 18) + " \u00A7eXL\u00A7r";
		} else if (Math.pow(10, 24) > Math.abs(n)) {
			return n / Math.pow(10, 21) + " \u00A7eZL\u00A7r";
		} else if (Math.pow(10, 27) > Math.abs(n)) {
			return n / Math.pow(10, 24) + " \u00A7eYL\u00A7r";
		} else if (Math.pow(10, 30) > Math.abs(n)) {
			return n / Math.pow(10, 27) + " \u00A7eRL\u00A7r";
		} else if (Math.pow(10, 33) > Math.abs(n)) {
			return n / Math.pow(10, 30) + " \u00A7eQL\u00A7r";
		} else if (Math.pow(10, 36) > Math.abs(n)) {
			return n / Math.pow(10, 33) + " \u00A76HL\u00A7r";
		} else if (Math.pow(10, 39) > Math.abs(n)) {
			return n / Math.pow(10, 36) + " \u00A76UL\u00A7r";
		} else if (Math.pow(10, 42) > Math.abs(n)) {
			return n / Math.pow(10, 39) + " \u00A76CL\u00A7r";
		} else if (!(Double.POSITIVE_INFINITY == n || Double.NEGATIVE_INFINITY == Math.abs(n) || Double.NaN == Math.abs(n))) {
			return n / Math.pow(10, 42) + " \u00A74fL\u00A7r";
		}
		return "\u00A75" + n + " L*\u00A7r";
	}
}