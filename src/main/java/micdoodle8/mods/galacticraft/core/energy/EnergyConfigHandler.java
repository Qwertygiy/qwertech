package micdoodle8.mods.galacticraft.core.energy;

import net.minecraftforge.common.config.Configuration;

public class EnergyConfigHandler {
	private static Configuration config;
	public static float BC3_RATIO = 16.0F;
	public static float RF_RATIO = BC3_RATIO / 10.0F;
	public static float IC2_RATIO = BC3_RATIO / 2.44F;
	public static float MEKANISM_RATIO = IC2_RATIO / 10.0F;
	private static int conversionLossFactor = 100;
	public static float TO_BC_RATIO = 1.0F / BC3_RATIO;
	public static float TO_RF_RATIO = 1.0F / RF_RATIO;
	public static float TO_IC2_RATIO = 1.0F / IC2_RATIO;
	public static float TO_MEKANISM_RATIO = 1.0F / MEKANISM_RATIO;
	public static float TO_BC_RATIOdisp = 1.0F / BC3_RATIO;
	public static float TO_RF_RATIOdisp = 1.0F / RF_RATIO;
	public static float TO_IC2_RATIOdisp = 1.0F / IC2_RATIO;
}
