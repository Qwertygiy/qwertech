package com.kbi.qwertech.items.behavior;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.entities.extended.ExtendedPropertiesDeflected;
import cpw.mods.fml.common.registry.IThrowableEntity;
import gregapi.item.multiitem.MultiItem;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;

public class Behavior_Swing extends Behavior_AOE {

	public Behavior_Swing(double aDist) {
		super(aDist);
	}
	
	@Override 
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity)
	{
	//System.out.println("Smacked a " + aEntity.getClass().getName());
		//OreDictMaterial mat = ((MultiItemTool)aItem).getSecondaryMaterial(aStack);
		if (aEntity instanceof IProjectile) {
			String className = aEntity.getClass().getName().toLowerCase();
			if (className.contains("arrow") || className.contains("bullet") || className.contains("slug") || className.contains("shell") || className.contains("shot") || className.contains("pellet") || className.contains("ammo") || className.contains("skull") || className.contains("burst") || className.contains("beam") || className.contains("blast") || className.contains("missile") || className.contains("bomb") || className.contains("dynamite") || className.contains("nuke")) {
			} else { //only smack things that aren't high-powered weaponry
				Vec3 lookVector = aPlayer.getLookVec();
				((IProjectile)aEntity).setThrowableHeading(lookVector.xCoord, lookVector.yCoord, lookVector.zCoord, 1, 1);
				aEntity.addVelocity(lookVector.xCoord, lookVector.yCoord, lookVector.zCoord);
				for (int i = 1; i < 9; i++)
				{
					aEntity.worldObj.spawnParticle("townaura", (float)aEntity.posX + rand.nextFloat(), (float)aEntity.posY + rand.nextFloat(), (float)aEntity.posZ + rand.nextFloat(), aEntity.motionX / i, aEntity.motionY / i, aEntity.motionZ / i);
				}
				if (aEntity instanceof EntityArrow) {
					((EntityArrow)aEntity).shootingEntity = aPlayer;
				} else if (aEntity instanceof EntityFireball) {
					((EntityFireball)aEntity).shootingEntity = aPlayer;
				} else if (aEntity instanceof IThrowableEntity) {
					((IThrowableEntity)aEntity).setThrower(aPlayer);
				} else if (aEntity instanceof EntityThrowable) {
					try {
						NBTTagCompound results = UT.NBT.make();
						((EntityThrowable)aEntity).writeEntityToNBT(results);
						results.setString("ownerName", aPlayer.getCommandSenderName());
						((EntityThrowable)aEntity).readEntityFromNBT(results);
					} catch (Exception e)
					{
						System.out.println("Attempted to set thrower of a " + aEntity.getClass().getName() + " and failed");
						e.printStackTrace();
					}
				}
				aEntity.registerExtendedProperties("isDeflected", new ExtendedPropertiesDeflected());
				QwerTech.achievementHandler.issueAchievement(aPlayer, "batterUp");
			}
		} else if (aEntity instanceof EntityLivingBase){
			if ((aEntity.width < 1 && aEntity.height < 1) || ((EntityLivingBase)aEntity).isChild()) {
				Vec3 lookVector = aPlayer.getLookVec();
				aEntity.addVelocity(lookVector.xCoord, lookVector.yCoord, lookVector.zCoord);
				for (int i = 1; i < 9; i++)	
				{
					aEntity.worldObj.spawnParticle("townaura", (float)aEntity.posX + rand.nextFloat(), (float)aEntity.posY + rand.nextFloat(), (float)aEntity.posZ + rand.nextFloat(), aEntity.motionX / i, aEntity.motionY / i, aEntity.motionZ / i);
				}
				QwerTech.achievementHandler.issueAchievement(aPlayer, "batterUp");
				if (aStack.getItemDamage() == 12 && aEntity instanceof EntityBat)
				{
					QwerTech.achievementHandler.issueAchievement(aPlayer, "kingBat");
				}
			}
		}
		return false;
	}
}
