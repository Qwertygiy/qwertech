package com.kbi.qwertech.blocks;

import com.kbi.qwertech.CommonProxy;
import gregapi.block.BlockBaseMeta;
import gregapi.data.MT;
import gregapi.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockCorrugated extends BlockBaseMeta {
	
	float[] hardness = new float[16];
	

	public BlockCorrugated(Class<? extends ItemBlock> aItemClass,
			String aNameInternal, Material aMaterial, SoundType aSoundType,
			long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
		hardness[0] = MT.Fe.mToolQuality;
		hardness[1] = MT.Al.mToolQuality;
		hardness[2] = MT.Au.mToolQuality;
		hardness[3] = MT.Steel.mToolQuality;
		hardness[4] = MT.Bronze.mToolQuality;
		hardness[5] = MT.Brass.mToolQuality;
		hardness[6] = MT.Ag.mToolQuality;
		hardness[7] = MT.StainlessSteel.mToolQuality;
		hardness[8] = MT.WroughtIron.mToolQuality;
		hardness[9] = MT.Plastic.mToolQuality;
		hardness[10] = MT.Ti.mToolQuality;
		hardness[11] = MT.TungstenSteel.mToolQuality;
		hardness[12] = MT.Invar.mToolQuality;
		hardness[13] = MT.TinAlloy.mToolQuality;
		hardness[14] = MT.SteelGalvanized.mToolQuality;
		hardness[15] = MT.Electrum.mToolQuality;
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		return 0.5F * hardness[world.getBlockMetadata(x,  y,  z)];
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		boolean renderEast = world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, true) || world.getBlock(x - 1, y, z) == this;
		boolean renderWest = world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, true) || world.getBlock(x + 1, y, z) == this;
		boolean renderNorth = world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) || world.getBlock(x, y, z - 1) == this;
		boolean renderSouth = world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) || world.getBlock(x, y, z + 1) == this;
		
		float minY = 0;
		float minX;
		float minZ;
		float maxY = 1;
		float maxX;
		float maxZ;
		
		if (renderEast)  {minX = 0F;} else {minX = 0.3F;}
		if (renderWest)  {maxX = 1F;} else {maxX = 0.7F;}
		if (renderNorth) {minZ = 0F;} else {minZ = 0.3F;}
		if (renderSouth) {maxZ = 1F;} else {maxZ = 0.7F;}
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	
	@Override
	public float getExplosionResistance(int meta)
	{
		return 3.3F * hardness[meta];
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
        return side == ForgeDirection.UP || side == ForgeDirection.DOWN;
	}
	
	@Override
	public int getHarvestLevel(int meta)
	{
		return (int)Math.floor(hardness[meta]);
	}
	
	
	@Override
	public boolean isSealable(int aMeta, byte aSide)
	{
	   return false;
	}
	  
	@Override
	public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide)
	{
	  return false;
	}
	  
	@Override
	public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide)
	{
	  return false;
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
	{
		boolean flag2 = world.isSideSolid(x - 1, y, z, ForgeDirection.WEST, true) || world.getBlock(x - 1, y, z) == this;
		boolean flag3 = world.isSideSolid(x + 1, y, z, ForgeDirection.EAST, true) || world.getBlock(x + 1, y, z) == this;
		boolean flag = world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) || world.getBlock(x, y, z - 1) == this;
		boolean flag1 = world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) || world.getBlock(x, y, z + 1) == this;
		float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag || flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
            super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        }

        f2 = 0.375F;
        f3 = 0.625F;

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        if (flag2 || flag3 || !flag && !flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
            super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        }

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}
	
	@Override
    public boolean renderAsNormalBlock(){
		return false;
    }
	
	@Override public IIcon getIcon(int aSide, int aMeta) {return this.mIcons[0].getIcon(0);}

    @Override
    public int getRenderType(){
        return CommonProxy.wallRenderID;
    }
    
    @Override
    public int getLightOpacity(){
    	return 2;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
    {
        return false;
    }
}