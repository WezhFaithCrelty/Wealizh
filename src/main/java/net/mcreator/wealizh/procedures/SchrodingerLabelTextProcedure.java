package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.wealizh.init.WealizhModItems;

public class SchrodingerLabelTextProcedure {
	public static String execute() {
		if (Mth.nextInt(RandomSource.create(), 1, 13) == 1) {
			if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
				return (Mth.nextInt(RandomSource.create(), 1, 7) == 1 ? "\u00A7a" : "\u00A75") + "[Existence]";
			} else {
				return (Mth.nextInt(RandomSource.create(), 1, 7) == 1 ? "\u00A7a" : "\u00A75") + "[\u5B58\u5728]";
			}
		} else if (Mth.nextInt(RandomSource.create(), 1, 9) == 1) {
			if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
				return (Mth.nextInt(RandomSource.create(), 1, 7) == 1 ? "\u00A7c" : "\u00A75") + "[Destruction]";
			} else {
				return (Mth.nextInt(RandomSource.create(), 1, 7) == 1 ? "\u00A7c" : "\u00A75") + "[\u6BC1\u706D]";
			}
		}
		return (Mth.nextInt(RandomSource.create(), 1, 7) == 1 ? "\u00A7b" : "\u00A75") + "" + new ItemStack(WealizhModItems.SCHRODINGER.get()).getDisplayName().getString();
	}
}