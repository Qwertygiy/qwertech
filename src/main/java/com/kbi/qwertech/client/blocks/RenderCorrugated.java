package com.kbi.qwertech.client.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import gregapi.data.MT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderCorrugated implements ISimpleBlockRenderingHandler {

	//private final int renderId;
	public static RenderCorrugated instance;
	private Tessellator tessellator;
	private static double w1 = (0.05);
	private static double w2 = (-0.025);
	private static double l1 = (0);
	private static double l2 = (0.067);
	private static double l3 = (0.1);
	private static double l4 = (0.233);
	private static double l5 = (0.267);
	private static double l6 = (0.4);
	private static double l7 = (0.433);
	private static double l8 = (0.5);
	
	public RenderCorrugated()
	{
		//renderId = RenderingRegistry.getNextAvailableRenderId();
		instance = this;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		short[] RGBa = new short[]{255, 255, 255, 255};
		switch (metadata)
		{
			case 0:
				RGBa = MT.Fe.mRGBaSolid; //iron
				break;
			case 1:
				RGBa = MT.Al.mRGBaSolid; //aluminum
				break;
			case 2:
				RGBa = MT.Au.mRGBaSolid; //gold
				break;
			case 3:
				RGBa = MT.Steel.mRGBaSolid; //steel
				break;
			case 4:
				RGBa = MT.Bronze.mRGBaSolid; //bronze
				break;
			case 5:
				RGBa = MT.Brass.mRGBaSolid; //brass
				break;
			case 6:
				RGBa = MT.Ag.mRGBaSolid; //silver
				break;
			case 7:
				RGBa = MT.StainlessSteel.mRGBaSolid; //stainless steel
				break;
			case 8:
				RGBa = MT.WroughtIron.mRGBaSolid; //wrought iron
				break;
			case 9:
				RGBa = new short[]{230, 230, 200, 255}; //vinyl/plastic
				break;
			case 10:
				RGBa = MT.Ti.mRGBaSolid; //titanium
				break;
			case 11:
				RGBa = MT.TungstenSteel.mRGBaSolid; //tsteel
				break;
			case 12:
				RGBa = MT.Invar.mRGBaSolid;
				break;
			case 13:
				RGBa = MT.TinAlloy.mRGBaSolid;
				break;
			case 14:
				RGBa = MT.SteelGalvanized.mRGBaSolid;
				break;
			case 15:
				RGBa = MT.Electrum.mRGBaSolid;
				break;
			default:
				break;
		}
		tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_F(RGBa[0]/255F, RGBa[1]/255F, RGBa[2]/255F);
		IIcon icon = block.getIcon(0, metadata);
		renderWall(icon, true, false, 0, 0, 0);
		renderWall(icon, true, true, 0, 0, 0);
		renderColumn(icon, 0.5F, 0F, 0, 0, 0);
		renderColumn(icon, 0.5F, 1F, 0, 0, 0);
		tessellator.setColorOpaque_F(1F, 1F, 1F);
		tessellator.draw();
	}
	
	public void tav(double x, double y, double z, double u, double v)
	{
		tessellator.addVertexWithUV(x, y, z, u, v);
	}
	
	public void tav(double x, double y, double z, double u, double v, boolean swapper)
	{
		if (swapper)
		{
			tessellator.addVertexWithUV(z, y, x, u, v);
		} else {
			tessellator.addVertexWithUV(x, y, z, u, v);
		}
	}
	
	public void renderColumn(IIcon icon, float x, float z, int posx, int posy, int posz)
	{
		float partialX = icon.getInterpolatedU(16);
		float partialY = icon.getInterpolatedV(16);
		float fullX = icon.getMaxU();
		float fullY = icon.getMaxV();
		float nonX = icon.getInterpolatedU(13);
		float nonY = icon.getInterpolatedV(13);
		tav(posx + x + 0.1, posy, posz + z, partialX, fullY);
		tav(posx + x + 0.1, posy + 1, posz + z, partialX, nonY);
		tav(posx + x, posy + 1, posz + z + 0.1, nonX, nonY);
		tav(posx + x, posy, posz + z + 0.1, nonX, fullY);
		
		tav(posx + x, posy, posz + z + 0.1, partialX, fullY);
		tav(posx + x, posy + 1, posz + z + 0.1, partialX, nonY);
		tav(posx + x - 0.1, posy + 1, posz + z, nonX, nonY);
		tav(posx + x - 0.1, posy, posz + z, nonX, fullY);
		
		tav(posx + x - 0.1, posy, posz + z, partialX, fullY);
		tav(posx + x - 0.1, posy + 1, posz + z, partialX, nonY);
		tav(posx + x, posy + 1, posz + z - 0.1, nonX, nonY);
		tav(posx + x, posy, posz + z - 0.1, nonX, fullY);
		
		tav(posx + x, posy, posz + z - 0.1, partialX, fullY);
		tav(posx + x, posy + 1, posz + z - 0.1, partialX, nonY);
		tav(posx + x + 0.1, posy + 1, posz + z, nonX, nonY);
		tav(posx + x + 0.1, posy, posz + z, nonX, fullY);
		
		tav(posx + x + 0.1, posy, posz + z, partialX, partialY);
		tav(posx + x, posy, posz + z + 0.1, partialX, nonY);
		tav(posx + x - 0.1, posy, posz + z, nonX, nonY);
		tav(posx + x, posy, posz + z - 0.1, nonX, partialY);
		
		tav(posx + x, posy + 1, posz + z - 0.1, nonX, partialY);
		tav(posx + x - 0.1, posy + 1, posz + z, nonX, nonY);
		tav(posx + x, posy + 1, posz + z + 0.1, partialX, nonY);
		tav(posx + x + 0.1, posy + 1, posz + z, partialX, partialY);
	}
	
	public void rendP(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2)
	{
		tav(x2, y1, z2, u2, v2);
		tav(x2, y2, z2, u2, v1);
		tav(x1, y2, z1, u1, v1);
		tav(x1, y1, z1, u1, v2);
	}
	
	public void rendY(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2)
	{
		tav(x2, y1, z1, u2, v1);
		tav(x2, y1, z2, u2, v2);
		tav(x1, y1, z2, u1, v2);
		tav(x1, y1, z1, u1, v1);
	}
	
	public void rendYXA(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2, double width)
	{
		tav(x2 - width, y1, z2, u2, v2);
		tav(x1 - width, y1, z1, u1, v1);
		tav(x1, y1, z1, u1, v1);
		tav(x2, y1, z2, u2, v2);
	}
	
	public void rendYZA(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2, double width)
	{
		tav(x2, y1, z2 - width, u2, v2);
		tav(x1, y1, z1 - width, u1, v1);
		tav(x1, y1, z1, u1, v1);
		tav(x2, y1, z2, u2, v2);
	}
	
	public void rendCX(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2, double width){
		rendP(x1 - width, y1, z1, u1, v1, x2 - width, y2, z2, u2, v2);
		rendP(x2, y1, z2, u2, v2, x1, y2, z1, u1, v1);
		rendYXA(x1, y1, z1, u1, v1, x2, y1, z2, u2, v2, width);
		rendYXA(x2, y2, z2, u2, v2, x1, y2, z1, u1, v1, width);
	}
	
	public void rendCZ(double x1, double y1, double z1, double u1, double v1, double x2, double y2, double z2, double u2, double v2, double width){
		rendP(x1, y1, z1, u1, v1, x2, y2, z2, u2, v2);
		rendP(x2, y1, z2 - width, u2, v2, x1, y2, z1 - width, u1, v1);
		rendYZA(x1, y2, z1, u1, v1, x2, y2, z2, u2, v2, width);
		rendYZA(x2, y1, z2, u2, v2, x1, y1, z1, u1, v1, width);
	}
	
	public void renderWall(IIcon icon, boolean isNorth, boolean offset, int posx, int posy, int posz)
	{
		float[] u = new float[17];
		float[] v = new float[17];
		for (int q = 0; q < 17; q++)
		{
			if (offset) {u[q] = icon.getInterpolatedU(Math.min(16, q + 3));} else {
			u[q] = icon.getInterpolatedU(q); }
			v[q] = icon.getInterpolatedV(q);
		}
		double startX = posx;
		double startZ = posz;
		double a1 = w1;
		double a2 = w2;
		double[] ls = new double[]{l1, l2, l3, l4, l5, l6, l7, l8};
		double topy = posy + 1;
		if (isNorth)
		{
			startX = startX + 0.5;
			if (offset) {
				startZ  = startZ + 0.5;
				a1 = w2;
				a2 = w1;
			}
			a1 = a1 + startX;
			a2 = a2 + startX;
			for (int q = 0; q < ls.length; q++)
			{
				ls[q] = ls[q] + startZ;
			}
		} else {
			startZ = startZ + 0.5;
			if (offset) {
				startX = startX + 0.5;
				a1 = w2;
				a2 = w1;
			}
			a1 = a1 + startZ;
			a2 = a2 + startZ;
			for (int q = 0; q < ls.length; q++)
			{
				ls[q] = ls[q] + startX;
			}
		}
		
		if (isNorth)
		{
			rendCX(a1, posy, ls[1], u[1], v[16], a1, topy, ls[0], u[0], v[0], -0.05);	
			rendCX(a2, posy, ls[2], u[2], v[16], a1, topy, ls[1], u[1], v[0], -0.05);
			rendCX(a2, posy, ls[3], u[4], v[16], a2, topy, ls[2], u[2], v[0], -0.05);
			rendCX(a1, posy, ls[4], u[5], v[16], a2, topy, ls[3], u[4], v[0], -0.05);
			rendCX(a1, posy, ls[5], u[7], v[16], a1, topy, ls[4], u[5], v[0], -0.05);
			rendCX(a2, posy, ls[6], u[8], v[16], a1, topy, ls[5], u[7], v[0], -0.05);
			rendCX(a2, posy, ls[7], u[9], v[16], a2, topy, ls[6], u[8], v[0], -0.05);
		} else {
			rendCZ(ls[1], posy, a1, u[1], v[16], ls[0], topy, a1, u[0], v[0], -0.05);	
			rendCZ(ls[2], posy, a2, u[2], v[16], ls[1], topy, a1, u[1], v[0], -0.05);
			rendCZ(ls[3], posy, a2, u[4], v[16], ls[2], topy, a2, u[2], v[0], -0.05);
			rendCZ(ls[4], posy, a1, u[5], v[16], ls[3], topy, a2, u[4], v[0], -0.05);
			rendCZ(ls[5], posy, a1, u[7], v[16], ls[4], topy, a1, u[5], v[0], -0.05);
			rendCZ(ls[6], posy, a2, u[8], v[16], ls[5], topy, a1, u[7], v[0], -0.05);
			rendCZ(ls[7], posy, a2, u[9], v[16], ls[6], topy, a2, u[8], v[0], -0.05);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		tessellator = Tessellator.instance;
		boolean renderEast = world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, true) || world.getBlock(x - 1, y, z) == block;
		boolean renderWest = world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, true) || world.getBlock(x + 1, y, z) == block;
		boolean renderNorth = world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) || world.getBlock(x, y, z - 1) == block;
		boolean renderSouth = world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) || world.getBlock(x, y, z + 1) == block;
		
		int lightValue = block.getMixedBrightnessForBlock(world, x, y, z);
		tessellator.setBrightness(lightValue);
		short[] RGBa = new short[]{255, 255, 255, 255};
		int MD = world.getBlockMetadata(x, y, z);
		switch (MD)
		{
			case 0:
				RGBa = MT.Fe.mRGBaSolid; //iron
				break;
			case 1:
				RGBa = MT.Al.mRGBaSolid; //aluminum
				break;
			case 2:
				RGBa = MT.Au.mRGBaSolid; //gold
				break;
			case 3:
				RGBa = MT.Steel.mRGBaSolid; //steel
				break;
			case 4:
				RGBa = MT.Bronze.mRGBaSolid; //bronze
				break;
			case 5:
				RGBa = MT.Brass.mRGBaSolid; //brass
				break;
			case 6:
				RGBa = MT.Ag.mRGBaSolid; //silver
				break;
			case 7:
				RGBa = MT.StainlessSteel.mRGBaSolid; //stainless steel
				break;
			case 8:
				RGBa = MT.WroughtIron.mRGBaSolid; //wrought iron
				break;
			case 9:
				RGBa = new short[]{230, 230, 200, 255}; //vinyl/plastic
				break;
			case 10:
				RGBa = MT.Ti.mRGBaSolid; //titanium
				break;
			case 11:
				RGBa = MT.TungstenSteel.mRGBaSolid; //tsteel
				break;
			case 12:
				RGBa = MT.Invar.mRGBaSolid;
				break;
			case 13:
				RGBa = MT.TinAlloy.mRGBaSolid;
				break;
			case 14:
				RGBa = MT.SteelGalvanized.mRGBaSolid;
				break;
			case 15:
				RGBa = MT.Electrum.mRGBaSolid;
				break;
			default:
				break;
		}
		//tessellator.setColorOpaque_F(1F, 1F, 1F);
		//GL11.glColor4f(RGBa[0]/255F, RGBa[1]/255F, RGBa[2]/255F, 1.0F);
		tessellator.setColorOpaque_F(RGBa[0]/255F, RGBa[1]/255F, RGBa[2]/255F);
		
		IIcon icon = block.getIcon(0, world.getBlockMetadata(x, y, z));
		
		if (renderEast) {
			renderWall(icon, false, false, x, y, z);
		}
		if (renderWest) {
			renderWall(icon, false, true, x, y, z);
		}
		if (renderNorth) {
			renderWall(icon, true, false, x, y, z);
		}
		if (renderSouth) {
			renderWall(icon, true, true, x, y, z);
		}
		if (!(renderNorth && renderSouth) && !(renderEast && renderWest)) {
			renderColumn(icon, 0.5F, 0.5F, x, y, z);
		}
		
		//GL11.glColor4f(1F, 1F, 1F, 1F);
		tessellator.setColorOpaque_F(1F, 1F, 1F);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
