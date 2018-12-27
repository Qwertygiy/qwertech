package com.kbi.qwertech.items.behavior;

import com.kbi.qwertech.entities.projectile.EntityShuriken;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Dispenser_Shuriken extends BehaviorProjectileDispense {
	
	private OreDictMaterial mat;
	@Override
	public ItemStack dispenseStack(IBlockSource bs, ItemStack is)
	{
	    World world = bs.getWorld();
	    IPosition iposition = BlockDispenser.func_149939_a(bs);
	    EnumFacing enumfacing = BlockDispenser.func_149937_b(bs.getBlockMetadata());
	    this.mat = OreDictManager.INSTANCE.getItemData(is).mMaterial.mMaterial;
	    IProjectile iprojectile = this.getProjectileEntity(world, iposition);
	    iprojectile.setThrowableHeading(enumfacing.getFrontOffsetX() * 1.1, (enumfacing.getFrontOffsetY() + 0.1F) * 1.1, enumfacing.getFrontOffsetZ() * 1.1, this.func_82500_b(), this.func_82498_a());
	    world.spawnEntityInWorld((Entity)iprojectile);
	    is.splitStack(1);
	    return is;
	}

	/**
	 * Play the dispense sound from the specified block.
	 */
	@Override
	protected void playDispenseSound(IBlockSource p_82485_1_)
	{
	    UT.Sounds.send(p_82485_1_.getWorld(), "qwertech:metal.slide", 0.5F, 1F, p_82485_1_.getXInt(), p_82485_1_.getYInt(), p_82485_1_.getZInt());
	}

	@Override
	protected IProjectile getProjectileEntity(World world,
			IPosition pos) {
		EntityShuriken es = new EntityShuriken(world, pos.getX(), pos.getY(), pos.getZ(), this.mat);
		es.canBePickedUp = 1;
		return es;
	}

}
