package com.kbi.qwertech.api.armor;

import com.kbi.qwertech.api.armor.upgrades.IArmorUpgrade;
import com.kbi.qwertech.api.client.models.ModelArmorBasic;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackSet;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
import gregapi.damage.DamageSources;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.enchants.Enchantment_Radioactivity;
import gregapi.item.*;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.energy.EnergyStatDebug;
import gregapi.lang.LanguageHandler;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;
import java.util.Map.Entry;

import static gregapi.data.CS.*;

@Optional.InterfaceList(value = {
		  @Optional.Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
		, @Optional.Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
		, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
		})
public class MultiItemArmor extends ItemArmor implements IItemProjectile, IItemUpdatable, IItemGT, IItemNoGTOverride, IItemEnergy {
	public static class QT_Item_Dispense extends BehaviorProjectileDispense {
		@Override
		public ItemStack dispenseStack(IBlockSource aSource, ItemStack aStack) {
			return ((ItemBase)aStack.getItem()).onDispense(aSource, aStack);
		}
		
		@Override
		protected IProjectile getProjectileEntity(World aWorld, IPosition aPosition) {
			return null;
		}
	}
	public static final long getArmorDamage(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("QT.ArmorStats");
			if (aNBT.hasKey("k")) return aNBT.getLong("k");
			return aNBT.getLong("Damage");
		}
		return 0;
	}
	
	public static final IArmorUpgrade[] getUpgrades(ItemStack aStack) {
		IArmorUpgrade[] returnable = new IArmorUpgrade[16];
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("QT.ArmorStats");
			if (aNBT != null) {
				aNBT = aNBT.getCompoundTag("up");
				for (int q = 0; q < 16; q++)
				{
					if (aNBT.hasKey(Integer.toString(q)))
					{
						int ID = aNBT.getShort(Integer.toString(q));
						IArmorUpgrade ahem = ArmorUpgradeRegistry.instance.getUpgrade(ID);
						if (ahem != null)
						{
							returnable[q] = ahem;
						}
					}
				}
			}
		}
		return returnable;
	}
	
	public static final ItemStack setUpgrade(ItemStack aStack, short aUpgradeID, int aSlot)
	{
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			NBTTagCompound bNBT = aNBT.getCompoundTag("QT.ArmorStats");
			if (bNBT != null) {
				IArmorUpgrade upgrade = ArmorUpgradeRegistry.instance.getUpgrade(aUpgradeID);
				if (upgrade != null && upgrade.isCompatibleWith(aStack) && upgrade.baseUpgrades() + 3 + getPrimaryMaterial(aStack).mToolQuality > aSlot)
				{
					NBTTagCompound cNBT = bNBT.getCompoundTag("up");
					cNBT.setShort(Integer.toString(aSlot), aUpgradeID);
					bNBT.setTag("up", cNBT);
					aNBT.setTag("QT.ArmorStats", bNBT);
					UT.NBT.set(aStack, aNBT);
				}
			}
		}
		return aStack;
	}
	
	public static final ItemStack addUpgrade(ItemStack aStack, short aUpgradeID)
	{
		IArmorUpgrade[] upgrades = getUpgrades(aStack);
		for (int q = 0; q < upgrades.length; q++)
		{
			if (upgrades[q] == null)
			{
				aStack = setUpgrade(aStack, aUpgradeID, q);
				break;
			}
		}
		return aStack;
	}
	
	@Override
	public boolean hasColor(ItemStack stack)
	{
		return true;
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		IArmorStats tStats = getArmorStatsInternal(stack.getItemDamage());
		if (tStats != null)
		{
			return tStats.getArmorTexture(stack, entity, slot, type);
		}
        return null;
    }
	
	Object model;
	
	@Override
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot)
    {
		if (model == null)
		{
			model = new ModelArmorBasic(0.5F);
		}
		ModelArmorBasic basicModel = (ModelArmorBasic)model;
		IArmorStats tStats = getArmorStatsInternal(stack.getItemDamage());
		if (tStats != null)
		{	
			ModelBiped returnable = tStats.getArmorModel(stack, entity, slot);
			if (returnable != null)
			{
				return returnable;
			}
		}
		basicModel.bipedHead.showModel = slot == 0;
	    basicModel.bipedHeadwear.showModel = slot == 0;
		basicModel.bipedBody.showModel = slot == 1;
		basicModel.bipedLeftShoulder.showModel = slot == 1;
		basicModel.bipedRightShoulder.showModel = slot == 1;
		basicModel.bipedLeftArm.showModel = slot == 1;
		basicModel.bipedRightArm.showModel = slot == 1;
		basicModel.bipedLeftHand.showModel = slot == 1;
		basicModel.bipedRightHand.showModel = slot == 1;
		basicModel.bipedLeftLeg.showModel = slot == 2;
		basicModel.bipedRightLeg.showModel = slot == 2;
		basicModel.bipedBelt.showModel = slot == 2;
		basicModel.bipedLeftFoot.showModel = slot == 3;
		basicModel.bipedRightFoot.showModel = slot == 3;
        return basicModel;
    }
	public static final long getArmorMaxDamage(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		long returnable = 1;
		if (aNBT != null) {
			NBTTagCompound bNBT = aNBT.getCompoundTag("QT.ArmorStats");
			if (bNBT.hasKey("j")) 
			{
				returnable = returnable + Math.max(1, bNBT.getLong("j"));
			} else 
			{
				returnable = returnable + Math.max(1, bNBT.getLong("MaxDamage"));
			}
			NBTTagCompound cNBT = bNBT.getCompoundTag("up");
			for (int q = 0; q < 16; q++)
			{
				if (cNBT.hasKey(Integer.toString(q)))
				{
					IArmorUpgrade upgrade = ArmorUpgradeRegistry.instance.getUpgrade(cNBT.getShort(Integer.toString(q)));
					if (upgrade != null)
					{
						returnable = returnable + (upgrade.getMaxDurabilityMultiplier() * upgrade.getMaterial().mToolDurability);
					}
				}
			}
		}
		return returnable;
	}
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack) {
		return getPrimaryMaterial(aStack, MT.NULL);
	}
	
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack, OreDictMaterial aDefault) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("QT.ArmorStats");
			if (aNBT != null) {
				if (aNBT.hasKey("a")) return OreDictMaterial.MATERIAL_ARRAY[aNBT.getShort("a")];
				if (aNBT.hasKey("b")) return OreDictMaterial.get(aNBT.getString("b"));
				return OreDictMaterial.get(aNBT.getString("PrimaryMaterial"));
			}
		}
		return aDefault;
	}
	
	public static final boolean setArmorDamage(ItemStack aStack, long aDamage) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			UT.NBT.setNumber(aNBT.getCompoundTag("QT.ArmorStats"), "k", aDamage);
			return T;
		}
		return F;
	}
	
	protected IIcon mIcon;
	
	protected final String mModID;
	
	protected final String mName, mTooltip;
	protected static final ArmorMaterial customMat = EnumHelper.addArmorMaterial("QWERTECH", 32000, new int[]{0, 0, 0, 0}, 0);
	public final HashMap<Short, ArrayList<IBehavior<MultiItemArmor>>> mItemBehaviors = new HashMap<Short, ArrayList<IBehavior<MultiItemArmor>>>();
	public final HashMap<Short, Long[]> mFluidContainerStats = new HashMap<Short, Long[]>();
	public final HashMap<Short, IArmorStats> mArmorStats = new HashMap<Short, IArmorStats>();
	
	
	public MultiItemArmor(String aModID, String aUnlocalized)
	{
		this(aModID, aUnlocalized, "Generated Item", null);
	}
	
	public MultiItemArmor(String aModID, String aUnlocalized, String aEnglish, String aEnglishTooltip) {
		super(customMat, 1, 1);
		if (GAPI.mStartedInit) throw new IllegalStateException("Items can only be initialised within preInit!");
		mName = aUnlocalized;
		mModID = aModID;
		LH.add(mName + ".name", aEnglish);	
		if (UT.Code.stringValid(aEnglishTooltip)) LH.add(mTooltip = mName + ".tooltip_main", aEnglishTooltip); else mTooltip = null;
		GameRegistry.registerItem(this, mName);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new QT_Item_Dispense());
		setHasSubtypes(T);
		setMaxDamage(0);
	}
	
	public void addAdditionalToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		long tMaxDamage = getArmorMaxDamage(aStack), tDamage = getArmorDamage(aStack);
		OreDictMaterial tMaterial = getPrimaryMaterial(aStack, MT.NULL);
		IArmorStats tStats = getArmorStats(aStack);
		if (tMaxDamage > 0 && tStats != null) {
			aList.add(LH.Chat.WHITE + "Durability: " + LH.Chat.GREEN + (tMaxDamage - tDamage) + " / " + tMaxDamage + LH.Chat.GRAY);
			aList.add(LH.Chat.WHITE + tMaterial.getLocal() + LH.Chat.YELLOW + " lvl " + (tMaterial.mToolQuality + tStats.getBaseQuality()) + LH.Chat.GRAY);
			IArmorUpgrade[] upgrades = getUpgrades(aStack);
			for (int q = 0; q < 16; q++)
			{
				if (upgrades[q] != null)
				{
					aList = upgrades[q].getAdditionalToolTips(aList, aStack);
				}
			}
		}
	}
	
	public final ItemStack addArmor(int aID, String aEnglish, String aArmorTip, IArmorStats aArmorStats, Object... aRandomParameters) {
		if (aArmorTip == null) aArmorTip = "";
		if (aID >= 0 && aID < 32766) {
			LH.add(getUnlocalizedName() + "." +  aID	+ ".name"		, aEnglish);
			LH.add(getUnlocalizedName() + "." +  aID	+ ".tooltip"	, aArmorTip);
			mArmorStats.put((short) aID   , aArmorStats);
			mArmorStats.put((short)(aID+1), aArmorStats);
			aArmorStats.onStatsAddedToArmor(this, aID);
			ItemStack rStack = ST.make(this, 1, aID);
			List<TC_AspectStack> tAspects = new ArrayListNoNulls<TC_AspectStack>();
			for (Object tRandomParameter : aRandomParameters) {
				if (tRandomParameter instanceof TC_AspectStack)
					((TC_AspectStack)tRandomParameter).addToAspectList(tAspects);
				else if (tRandomParameter instanceof ItemStackSet)
					((ItemStackSet)tRandomParameter).add(rStack.copy());
				else
					OM.reg(tRandomParameter, rStack);
			}
			if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(rStack, tAspects, F);
			return rStack;
		}
		return null;
	}
	
	@Override
	public final void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		isItemStackUsable(aStack);
		
		String tKey = getUnlocalizedName(aStack) + ".tooltip", tString = LanguageHandler.translate(tKey, tKey);
		if (UT.Code.stringValid(tString) && !tKey.equals(tString)) aList.add(tString);
		
		IItemEnergy tEnergyStats = getEnergyStats(aStack);
		if (tEnergyStats != null) {
			if (tEnergyStats instanceof EnergyStatDebug) {
				aList.add(LH.Chat.RAINBOW_SLOW + "Works as Infinite Energy Battery" + EnumChatFormatting.GRAY);
			} else {
				for (TagData tEnergyType : tEnergyStats.getEnergyTypes(aStack)) {
					long tCapacity = tEnergyStats.getEnergyCapacity(tEnergyType, aStack);
					aList.add(LH.Chat.WHITE + Math.min(tCapacity, tEnergyStats.getEnergyStored(tEnergyType, aStack)) + " / " + tCapacity + " " + tEnergyType.mChatFormat + tEnergyType.getLocalisedNameShort() + LH.Chat.WHITE + " - Size: " + tEnergyStats.getEnergySizeInputRecommended(tEnergyType, aStack) + EnumChatFormatting.GRAY);
				}
			}
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats != null && tStats[0] > 0) {
			FluidStack tFluid = getFluidContent(aStack);
			aList.add(LH.Chat.BLUE + ((tFluid==null?"No Fluids Contained":UT.Fluids.name(tFluid, T))) + LH.Chat.GRAY);
			aList.add(LH.Chat.BLUE + ((tFluid==null?0:tFluid.amount) + "L / " + tStats[0] + "L") + LH.Chat.GRAY);
		}
		
		addAdditionalToolTips(aList, aStack, aF3_H);
		
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) aList = tBehavior.getAdditionalToolTips(this, aList, aStack);
	}

		
	/**
	 * Adds a special Item Behaviour to the Item.
	 * 
	 * Note: the boolean Behaviours sometimes won't be executed if another boolean Behaviour returned true before.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to add it to. [0 - 32765]
	 * @param aBehavior the Click Behavior you want to add.
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItemArmor addItemBehavior(int aMetaValue, IBehavior<MultiItemArmor> aBehavior) {
		if (aMetaValue < 0 || aMetaValue >= 32766 || aBehavior == null) return this;
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)aMetaValue);
		if (tList == null) {
			tList = new ArrayListNoNulls<IBehavior<MultiItemArmor>>();
			mItemBehaviors.put((short)aMetaValue, tList);
		}
		tList.add(aBehavior);
		return this;
	}
	
	@Override
	public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize) {
		IItemEnergy tStats = getEnergyStats(aStack);
		return tStats != null && tStats.canEnergyExtraction(aEnergyType, aStack, aSize);
	}
	
	@Override
	public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {
		IItemEnergy tStats = getEnergyStats(aStack);
		return tStats != null && tStats.canEnergyInjection(aEnergyType, aStack, aSize);
	}
	
	public boolean canProvideEnergy(ItemStack aStack) {return T;}
	
	public boolean canUse(ItemStack aStack, double aAmount) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, null, null, null, 0, 0, 0, F);}

	public double charge(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
		aTier = UT.Code.bind4(aTier);
		if (aCharge < V[aTier]) return 0;
		return V[aTier] * doEnergyInjection(TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	public void chargeFromArmor(ItemStack aStack, EntityLivingBase aPlayer) {/* No longer in this Part of the Code, its in the EnergyStats now.*/}
	
	public double discharge(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aBatteryAlike, boolean aSimulate) {
		aTier = UT.Code.bind4(aTier);
		if (aCharge < V[aTier]) return 0;
		return V[aTier] * doEnergyExtraction(TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	public float discharge(ItemStack aStack, float aEnergy, boolean aDoExtract) {
		if (aEnergy <= 0) return 0;
		long tMaxOut = getEnergySizeOutputMax(TD.Energy.EU, aStack);
		if (!canEnergyExtraction(TD.Energy.EU, aStack, tMaxOut)) return 0;
		long tAmount = UT.Code.bind(1, tMaxOut, (long)(aEnergy / EnergyConfigHandler.IC2_RATIO));
		return useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, F) && useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, T) ? tAmount * EnergyConfigHandler.IC2_RATIO : 0;
	}
	
	public final boolean doDamage(ItemStack aStack, long aAmount) {
		return doDamage(aStack, aAmount, null);
	}
	
	public final boolean doDamage(ItemStack aStack, long aAmount, EntityLivingBase aPlayer) {
		if (!isItemStackUsable(aStack)) return F;
		IItemEnergy tElectric = getEnergyStats(aStack);
		if (tElectric == null || RNGSUS.nextInt(25) == 0) {
			long tNewDamage = getArmorDamage(aStack) + aAmount;
			setArmorDamage(aStack, tNewDamage);
			if (tNewDamage >= getArmorMaxDamage(aStack)) {
				IArmorStats tStats = getArmorStats(aStack);
				if (tStats == null) {
					aStack.stackSize = 0;
				} else {
					if (TOOL_SOUNDS) {
						if (aPlayer == null) {
							UT.Sounds.play(tStats.getBreakingSound(), 200, 1);
						} else {
							UT.Sounds.play(tStats.getBreakingSound(), 200, 1, aPlayer);
						}
					}
					ItemStack tBroken = tStats.getBrokenItem(aStack);
					if (ST.invalid(tBroken) || tBroken.stackSize <= 0) {
						aStack.stackSize = 0;
					} else if (aPlayer instanceof EntityPlayer) {
						if (tBroken.stackSize > 64) tBroken.stackSize = 64;
						if (!aPlayer.worldObj.isRemote) UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, tBroken, F);
						aStack.stackSize = 0;
					} else {
						if (tBroken.stackSize > 64) tBroken.stackSize = 64;
						ST.set(aStack, tBroken);
					}
				}
			}
			return tElectric == null || useEnergy(TD.Energy.EU, aStack, aAmount, aPlayer, null, null, 0, 0, 0, T);
		}
		return useEnergy(TD.Energy.EU, aStack, aAmount, aPlayer, null, null, 0, 0, 0, T);
	}
	
	
	@Override
	public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.doEnergyExtraction(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoExtract);
	}
	
	@Override
	public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.doEnergyInjection(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoInject);
	}
	
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		if (aStack == null || aStack.stackSize != 1) return null;
		
		FluidStack tFluid = UT.Fluids.getFluidForFilledItem(aStack, F);
		if (tFluid != null && aMaxDrain >= tFluid.amount) {
			if (aDoDrain) {
				ItemStack tStack = ST.container(aStack, F);
				if (tStack == null) {
					aStack.stackSize = 0;
					return tFluid;
				}
				aStack.setItemDamage(ST.meta(tStack));
				aStack.func_150996_a(tStack.getItem());
			}
			return tFluid;
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0) return null;
		
		tFluid = getFluidContent(aStack);
		if (tFluid == null) return null;
		
		if (tFluid.amount < aMaxDrain) aMaxDrain = tFluid.amount;
		if (aDoDrain) {
			tFluid.amount -= aMaxDrain;
			setFluidContent(aStack, tFluid);
		}
		
		FluidStack tDrained = tFluid.copy();
		tDrained.amount = aMaxDrain;
		return tDrained;
	}
	
	public int fill(ItemStack aStack, FluidStack aFluid, boolean doFill) {
		if (aStack == null || aStack.stackSize != 1) return 0;
		
		ItemStack tStack = UT.Fluids.fillFluidContainer(aFluid, aStack, F, F, F, F);
		if (tStack != null) {
			aStack.setItemDamage(ST.meta(tStack));
			aStack.func_150996_a(tStack.getItem());
			return UT.Fluids.getFluidForFilledItem(tStack, F).amount;
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0 || aFluid == null || aFluid.amount <= 0 || !isAllowedToFill(aStack, aFluid)) return 0;
		
		FluidStack tFluid = getFluidContent(aStack);
		
		if (tFluid == null) {
			if (aFluid.amount <= tStats[0]) {
				if (doFill) {
					setFluidContent(aStack, aFluid);
				}
				return aFluid.amount;
			}
			if (doFill) {
				tFluid = aFluid.copy();
				tFluid.amount = (int)(long)tStats[0];
				setFluidContent(aStack, tFluid);
			}
			return (int)(long)tStats[0];
		}
		
		if (!tFluid.isFluidEqual(aFluid)) return 0;
		
		int space = (int)(long)tStats[0] - tFluid.amount;
		if (aFluid.amount <= space) {
			if (doFill) {
				tFluid.amount += aFluid.amount;
				setFluidContent(aStack, tFluid);
			}
			return aFluid.amount;
		}
		if (doFill) {
			tFluid.amount = (int)(long)tStats[0];
			setFluidContent(aStack, tFluid);
		}
		return space;
	}
	
	public IArmorStats getArmorStats(ItemStack aStack) {
		isItemStackUsable(aStack);
		return getArmorStatsInternal(aStack);
	}
	
	private IArmorStats getArmorStatsInternal(int aDamage) {
		return mArmorStats.get((short)aDamage);
	}
	
	private IArmorStats getArmorStatsInternal(ItemStack aStack) {
		return aStack == null ? null : getArmorStatsInternal(ST.meta(aStack));
	}

	/**
	 * This Function gets an ItemStack Version of this Armor
	 * @param aArmorID the ID of the Armor Class
	 * @param aAmount Amount of Items (well normally you only need 1)
	 * @param aPrimaryMaterial Primary Material of this Armor
	 */
	public final ItemStack getArmorWithStats(int aArmorID, int aAmount, OreDictMaterial aPrimaryMaterial) {
		return getArmorWithStats(aArmorID, aAmount, aPrimaryMaterial, 0, 0);
	}
	/**
	 * This Function gets an ItemStack Version of this Armor
	 * @param aArmorID the ID of the Armor Class
	 * @param aAmount Amount of Items (well normally you only need 1)
	 * @param aPrimaryMaterial Primary Material of this Armor
	 */
	public final ItemStack getArmorWithStats(int aArmorID, int aAmount, OreDictMaterial aPrimaryMaterial, long aMaxCharge, long aVoltage) {
		ItemStack rStack = ST.make(this, aAmount, aArmorID);
		IArmorStats tArmorStats = getArmorStats(rStack);
		if (tArmorStats != null) {
			NBTTagCompound tMainNBT = UT.NBT.make(), tArmorNBT = UT.NBT.make();
			if (aPrimaryMaterial != null) {
				if (aPrimaryMaterial.mID > 0) tArmorNBT.setShort("a", aPrimaryMaterial.mID); else tArmorNBT.setString("b", aPrimaryMaterial.toString());
				UT.NBT.setNumber(tArmorNBT, "j", ((aPrimaryMaterial.mToolDurability * tArmorStats.getMaxDurabilityMultiplier())));
			}
			if (aMaxCharge > 0) {
				tArmorNBT.setBoolean("e", T);
				UT.NBT.setNumber(tArmorNBT, "f", aMaxCharge);
				UT.NBT.setNumber(tArmorNBT, "g", aVoltage);
			}
			tMainNBT.setTag("QT.ArmorStats", tArmorNBT);
			UT.NBT.set(rStack, tMainNBT);
		}
		isItemStackUsable(rStack);
		return rStack;
	}
	/**
	 * This Function gets an ItemStack Version of this Armor
	 * @param aArmorID the ID of the Armor Class
	 * @param aPrimaryMaterial Primary Material of this Armor
	 */
	public final ItemStack getArmorWithStats(int aArmorID, OreDictMaterial aPrimaryMaterial) {
		return getArmorWithStats(aArmorID, 1, aPrimaryMaterial);
	}
	
	@Override
	public void removeColor(ItemStack stack)
    {
		NBTTagCompound tagdata = UT.NBT.getNBT(stack);
		if (tagdata != null)
		{
			NBTTagCompound armorData = tagdata.getCompoundTag("QT.ArmorStats");
			if (armorData != null)
			{
				armorData.setInteger("rgb", 0);
			}
		}
    }
	
	@Override
	public void func_82813_b(ItemStack p_82813_1_, int p_82813_2_)
    {
		setColor(p_82813_1_, p_82813_2_);
    }
	
	public final ItemStack setColor(ItemStack stack, int color)
	{
		NBTTagCompound tagdata = UT.NBT.getNBT(stack);
		if (tagdata != null)
		{
			NBTTagCompound armorData = tagdata.getCompoundTag("QT.ArmorStats");
			if (armorData != null)
			{
				armorData.setInteger("rgb", color);
			}
		}
		return stack;
	}
	
	public int getCapacity(ItemStack aStack) {
		Long[] tStats = getFluidContainerStats(aStack);
		return tStats==null?0:(int)Math.max(0, tStats[0]);
	}
	
	public double getCharge(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack);}
	
	public Item getChargedItem(ItemStack itemStack) {return this;}
	
	@Override
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		NBTTagCompound tagdata = UT.NBT.getNBT(aStack);
		if (tagdata != null)
		{
			NBTTagCompound armorData = tagdata.getCompoundTag("QT.ArmorStats");
			if (armorData != null)
			{
				int returnColor = armorData.getInteger("rgb");
				if (returnColor > 0)
				{
					return returnColor;
				}
			}
		}
		IArmorStats tStats = getArmorStatsInternal(aStack);
		if (tStats != null && tStats.getRenderPasses() > aRenderPass) return UT.Code.getRGBaInt(tStats.getRGBa(aStack, aRenderPass));
		return 16777215;
	}
	
	@Override
	public int getColor(ItemStack aStack)
	{
		return getColorFromItemStack(aStack, 0);
	}
	
	public float getElectricityStored(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack) * EnergyConfigHandler.IC2_RATIO;}
	
	public Item getEmptyItem(ItemStack itemStack) {return this;}
	
	@Override
	public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergyCapacity(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputMax(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputMin(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputRecommended(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputMax(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputMin(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputRecommended(aEnergyType, aStack);
	}
	
	public IItemEnergy getEnergyStats(ItemStack aStack) {
		return null;
	}

    @Override
	public long getEnergyStored(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergyStored(aEnergyType, aStack);
	}
    
	@Override
	public Collection<TagData> getEnergyTypes(ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return Collections.EMPTY_LIST;
		return tStats.getEnergyTypes(aStack);
	}
	
	public FluidStack getFluid(ItemStack aStack) {return getFluidContent(aStack);}
	
	public Long[] getFluidContainerStats(ItemStack aStack) {
		return null;
	}
	
	public FluidStack getFluidContent(ItemStack aStack) {
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0) return UT.Fluids.getFluidForFilledItem(aStack, F);
		return UT.Fluids.load(aStack.getTagCompound(), "gt.fluidcontent");
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		return getIcon(aStack, aRenderPass, null, null, 0);
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
		IArmorStats tStats = getArmorStatsInternal(aStack);
		if (tStats == null) return Textures.ItemIcons.VOID.getIcon(0);
		if (aRenderPass < tStats.getRenderPasses()) {
			IIcon rIcon = tStats.getIcon(aStack, aRenderPass);
			return rIcon == null ? Textures.ItemIcons.VOID.getIcon(0) : rIcon;
		}
		if (aPlayer == null) {
			aRenderPass = aRenderPass - tStats.getRenderPasses();
			if (aRenderPass == 0) {
				long tDamage = getArmorDamage(aStack), tMaxDamage = getArmorMaxDamage(aStack);
				if (tMaxDamage <= 0) return Textures.ItemIcons.VOID.getIcon(0);
				if (tDamage <= 0) return Textures.ItemIcons.DURABILITY_BAR[8].getIcon(0);
				if (tDamage >= tMaxDamage) return Textures.ItemIcons.DURABILITY_BAR[0].getIcon(0);
				return Textures.ItemIcons.DURABILITY_BAR[(int)Math.max(0, Math.min(7, ((tMaxDamage-tDamage)*8L) / tMaxDamage))].getIcon(0);
			}
			if (aRenderPass == 1) {
				IItemEnergy tElectric = getEnergyStats(aStack);
				if (tElectric != null) {
					long tStored = tElectric.getEnergyStored(TD.Energy.EU, aStack), tCapacity = tElectric.getEnergyCapacity(TD.Energy.EU, aStack);
					if (tStored <= 0) return Textures.ItemIcons.ENERGY_BAR[0].getIcon(0);
					if (tStored >= tCapacity) return Textures.ItemIcons.ENERGY_BAR[8].getIcon(0);
					return Textures.ItemIcons.ENERGY_BAR[7-(int)Math.max(0, Math.min(6, ((tCapacity-tStored)*7L) / tCapacity))].getIcon(0);
				}
			}
		}
		return Textures.ItemIcons.VOID.getIcon(0);
	}
	
	@Override
	public IIcon getIconFromDamage(int aMetaData) {
		return getIconIndex(ST.make(this, 1, aMetaData));
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		return getIconFromDamage(aMetaData);
	}
	
	@Override
	public IIcon getIconIndex(ItemStack aStack) {
		return getIcon(aStack, 0);
	}
	
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	
	@Override public int getItemEnchantability() {return 0;}
	
	@Override
	public final int getItemStackLimit(ItemStack aStack) {
		return 1;
	}
	
	public double getMaxCharge(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack);}
	
	public float getMaxElectricityStored(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack) * 	EnergyConfigHandler.IC2_RATIO;}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) {
			EntityProjectile rArrow = tBehavior.getProjectile(this, aProjectileType, aStack, aWorld, aX, aY, aZ);
			if (rArrow != null) return rArrow;
		}
		return null;
	}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) {
			EntityProjectile rArrow = tBehavior.getProjectile(this, aProjectileType, aStack, aWorld, aEntity, aSpeed);
			if (rArrow != null) return rArrow;
		}
		return null;
	}
	
	@Override
	public int getRenderPasses(int aMetaData) {
		int renderPasses = 2;
		IArmorStats tStats = getArmorStatsInternal(aMetaData);
		if (tStats != null) renderPasses = renderPasses + tStats.getRenderPasses();
		return renderPasses;
	}
	
	@Override public final boolean getShareTag() {return T;} // just to be sure.
	
	@Override
	public int getSpriteNumber() {
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public final void getSubItems(Item var1, CreativeTabs aCreativeTab, List aList) {
		OreDictMaterial[] example = new OreDictMaterial[]{MT.Al, MT.Bronze, MT.Cu, MT.Electrum, MT.Au, MT.Fe, MT.Pb, MT.Ni, MT.ObsidianSteel, MT.Plastic, MT.Rubber, MT.Steel, MT.Ti, MT.U_235, MT.Vibramantium, MT.WroughtIron};
		for (int i = 0; i < 32766; i+=1) if (getArmorStats(ST.make(this, 1, i)) != null) {
			ItemStack tStack = ST.make(this, 1, i);
			isItemStackUsable(tStack);
			aList.add(tStack);
			if (i < 8)
			{
				for (int w = 0; w < example.length; w++)
				{
					ItemStack returnable = getArmorWithStats(i, example[w]);
					isItemStackUsable(returnable);
					aList.add(returnable);
				}
			} else {
				ItemStack returnable = getArmorWithStats(i, MT.Rubber);
				isItemStackUsable(returnable);
				aList.add(returnable);
			}
		}
	}
	
	public int getTier(ItemStack aStack) {return UT.Code.tierMax(getEnergySizeInputMax(TD.Energy.EU, aStack));}
	
	public int getTierGC(ItemStack aStack) {return 1;}
	
	public String getToolTip(ItemStack aStack) {return null;} // This has its own ToolTip Handler, no need to let the IC2 Handler screw us up at this Point
	
	public float getTransfer(ItemStack aStack) {return 0;}
	
	public double getTransferLimit(ItemStack aStack) {return getEnergySizeInputRecommended(TD.Energy.EU, aStack);}
	
	@Override public final String getUnlocalizedName() {return mName;}
	
	@Override public String getUnlocalizedName(ItemStack aStack) {return getHasSubtypes()?mName+"."+getDamage(aStack):mName;}
	
	@Override
	public boolean hasContainerItem(ItemStack aStack) {
		return F;
	}
	
	@Override
	public boolean hasEffect(ItemStack aStack) {
		return F;
	}
	
	@Override
	public boolean hasEffect(ItemStack aStack, int aRenderPass) {
		return F;
	}
	
	@Override
	public boolean hasProjectile(TagData aProjectileType, ItemStack aStack) {
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) if (tBehavior.hasProjectile(this, aProjectileType, aStack)) return T;
		return F;
	}
	
	public boolean isAllowedToFill(ItemStack aStack, FluidStack aFluid) {return T;}
	
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	
	@Override
	public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return F;
		return tStats.isEnergyType(aEnergyType, aStack, aEmitting);
	}
	
	@Override
	public boolean isFull3D() {
		return T;
	}
	
	public boolean isItemStackUsable(ItemStack aStack) {
		if (aStack == null || aStack.stackSize <= 0) return F;
		IArmorStats tStats = getArmorStatsInternal(aStack);
		if (tStats == null) {
			NBTTagCompound aNBT = aStack.getTagCompound();
			if (aNBT != null) aNBT.removeTag("ench");
			return F;
		}
		OreDictMaterial aMaterial = getPrimaryMaterial(aStack, MT.NULL);
		HashMap<Integer, Integer> tMap = new HashMap<Integer, Integer>(), tResult = new HashMap<Integer, Integer>();
		for (ObjectStack<Enchantment> tEnchantment : aMaterial.mEnchantmentArmors) {
			tMap.put(tEnchantment.mObject.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.fortune) tMap.put(Enchantment.looting.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.knockback) tMap.put(Enchantment.punch.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.fireAspect) tMap.put(Enchantment.flame.effectId, tEnchantment.amountInt());
		}
		List<Enchantment> allEnchants = new ArrayList();
		List<Integer> allLevels = new ArrayList();
		Enchantment[] tEnchants = tStats.getEnchantments(aStack, aMaterial);
		int[] tLevels = tStats.getEnchantmentLevels(aStack);
		for (int q = 0; q < tEnchants.length; q++)
		{
			allEnchants.add(tEnchants[q]);
			allLevels.add(tLevels[q]);
		}
		IArmorUpgrade[] upgrades = getUpgrades(aStack);
		for (int q = 0; q < upgrades.length; q++)
		{
			IArmorUpgrade upgrade = upgrades[q];
			if (upgrade != null)
			{
				Enchantment[] uEnchants = upgrade.getEnchantments(aStack, aMaterial);
				int[] uLevels = upgrade.getEnchantmentLevels(aStack);
				for (int w = 0; w < uEnchants.length; w++)
				{
					allEnchants.add(uEnchants[w]);
					allLevels.add(uLevels[w]);
				}
			}
		}
		for (int i = 0; i < allEnchants.size(); i++) if (allLevels.get(i) > 0) {
			Enchantment selected = allEnchants.get(i);
			int select = allLevels.get(i);
			Integer tLevel = tMap.get(selected.effectId);
			tMap.put(selected.effectId, tLevel == null ? select : tLevel == select ? tLevel+1 : Math.max(tLevel, select));
		}
		for (Entry<Integer, Integer> tEntry : tMap.entrySet()) {
			if (tEntry.getKey() == 33 || (tEntry.getKey() == 20 && tEntry.getValue() > 2) || tEntry.getKey() == Enchantment_Radioactivity.INSTANCE.effectId) tResult.put(tEntry.getKey(), tEntry.getValue()); else
			switch(Enchantment.enchantmentsList[tEntry.getKey()].type) {
			case weapon:
				break;
			case all:
				tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			case armor: 
				tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			case armor_feet: 
				if (tStats.isValidInSlot(3))
				{
					tResult.put(tEntry.getKey(), tEntry.getValue());
				}
				break;
			case armor_head: 
				if (tStats.isValidInSlot(0))
				{
					tResult.put(tEntry.getKey(), tEntry.getValue());
				}
				break;
			case armor_legs: 
				if (tStats.isValidInSlot(2))
				{
					tResult.put(tEntry.getKey(), tEntry.getValue());
				}
				break;
			case armor_torso:
				if (tStats.isValidInSlot(1))
				{
					tResult.put(tEntry.getKey(), tEntry.getValue());
				}
				break;
			case bow:
				break;
			case breakable:
				break;
			case fishing_rod:
				break;
			case digger:
				break;
			}
		}
		EnchantmentHelper.setEnchantments(tResult, aStack);
		return getArmorDamage(aStack) <= getArmorMaxDamage(aStack);
	}
	
	/**
     * Determines if the specific ItemStack can be placed in the specified armor slot.
     *
     * @param stack The ItemStack
     * @param armorType Armor slot ID: 0: Helmet, 1: Chest, 2: Legs, 3: Boots
     * @param entity The entity trying to equip the armor
     * @return True if the given ItemStack can be inserted in the slot
     */
    @Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
		isItemStackUsable(stack);
		IArmorStats tStats = getArmorStatsInternal(stack.getItemDamage());
		return tStats != null && tStats.isValidInSlot(armorType);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack aStack, EntityPlayer aPlayer, EntityLivingBase aEntity) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) try {
			if (tBehavior.onRightClickEntity(this, aStack, aPlayer, aEntity)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	
	public ItemStack make(long aMetaData) {
		return ST.make(this, 1, aMetaData);
	}
	
	public ItemStack make(long aAmount, long aMetaData) {
		return ST.make(this, aAmount, aMetaData);
	}
	
	public boolean onArmorHit(World world, EntityLivingBase entity, ItemStack stack, int armorSlot, DamageSource source, float amount, LivingHurtEvent event)
	{
		IArmorStats tStats = getArmorStats(stack);
		IArmorUpgrade[] upgrades = getUpgrades(stack);
		ArrayList<IArmorUpgrade> actualUpgrades = new ArrayList();
		for (int q = 0; q < upgrades.length; q++)
		{
			IArmorUpgrade upgrade = upgrades[q];
			if (upgrade != null)
			{
				actualUpgrades.add(upgrade);
			}
		}
		if (tStats != null)
		{
			tStats.onAnyDamage(world, entity, stack, source, amount, event);
			for (IArmorUpgrade upgrade : actualUpgrades)
			{
				upgrade.onAnyDamage(world, entity, stack, source, amount, event);
			}
			if (event.isCanceled()) return true;
			if (source.isExplosion())
			{
				tStats.onExplosionDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onExplosionDamage(world, entity, stack, source, amount, event);
				}
			}
			if (source.isFireDamage())
			{
				tStats.onFireDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onFireDamage(world, entity, stack, source, amount, event);
				}
			}
			if (source.isMagicDamage())
			{
				tStats.onMagicDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onMagicDamage(world, entity, stack, source, amount, event);
				}
			}
			if (source.isProjectile())
			{
				UT.Sounds.play(tStats.getImpactSound(), 0, 0.5F, entity);
				tStats.onProjectileDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onProjectileDamage(world, entity, stack, source, amount, event);
				}
			}
			if (event.isCanceled()) return true;
			if (source.equals(DamageSource.drown))
			{
				tStats.onDrowningDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onDrowningDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSource.fall))
			{
				tStats.onFallingDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onFallingDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSource.starve))
			{
				tStats.onHungerDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onHungerDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSource.lava))
			{
				tStats.onLavaDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onLavaDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSource.wither))
			{
				tStats.onWitherDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onWitherDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSources.getHeatDamage()))
			{
				tStats.onHeatDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onHeatDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSources.getFrostDamage()))
			{
				tStats.onFrostDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onFrostDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSources.getChemDamage()))
			{
				tStats.onChemDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onChemDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSources.getBumbleDamage()))
			{
				tStats.onBumbleDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onBumbleDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.equals(DamageSources.getSpikeDamage()))
			{
				UT.Sounds.play(tStats.getImpactSound(), 0, 0.5F, entity);
				tStats.onSpikeDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onSpikeDamage(world, entity, stack, source, amount, event);
				}
			}
			if (event.isCanceled()) return true;
			if (source.getEntity() instanceof EntityPlayer && source.getEntity() != entity)
			{
				UT.Sounds.play(tStats.getImpactSound(), 0, 0.5F, entity);
				tStats.onPlayerDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onPlayerDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.getEntity() instanceof EntityMob)
			{
				UT.Sounds.play(tStats.getImpactSound(), 0, 0.5F, entity);
				tStats.onHostileDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onHostileDamage(world, entity, stack, source, amount, event);
				}
			} else if (source.getEntity() instanceof EntityAnimal)
			{
				UT.Sounds.play(tStats.getImpactSound(), 0, 0.5F, entity);
				tStats.onAnimalDamage(world, entity, stack, source, amount, event);
				for (IArmorUpgrade upgrade : actualUpgrades)
				{
					upgrade.onAnimalDamage(world, entity, stack, source, amount, event);
				}
			}
			if (event.isCanceled() || event.source.isUnblockable()) return true;
			Map<Integer, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
			for (int id : enchants.keySet())
			{
				Enchantment enchant = Enchantment.enchantmentsList[id];
				amount = amount - enchant.calcModifierDamage(enchants.get(id), source);
			}
			OreDictMaterial primaryMat = getPrimaryMaterial(stack);
			int quality = primaryMat.mToolQuality + tStats.getBaseQuality();
			for (IArmorUpgrade upgrade : actualUpgrades)
			{
				quality = quality + upgrade.getBaseQuality();
			}
			/*
			float original = amount;
			for (int q = 0; q < quality; q++)
			{
				amount = amount * tStats.getDamageProtection();
			}
			event.ammount = amount;
			if (entity instanceof EntityPlayer)
			{
				doDamage(stack, (long)((original - amount) * 200), entity);
			} else {
				doDamage(stack, (long)((original - amount) * 200));
			}*/
			float i = (50.0F * tStats.getDamageProtection()) - quality;
            float f1 = amount * i;

            if (entity instanceof EntityPlayer)
			{
				doDamage(stack, (long)(f1), entity);
			} else {
				doDamage(stack, (long)(f1));
			}
            amount = f1 / 50.0F;
            event.ammount = amount;
			if (stack.stackSize <= 0)
			{
				entity.setCurrentItemOrArmor(armorSlot, null);
			}
			return true;
		}
		return false;
	}
	
	/**
     * Called to tick armor in the armor slot. Override to do something
     *
     * @param world
     * @param player
     * @param stack
     */
    public void onArmorTicked(World world, EntityLivingBase player, ItemStack stack)
    {
		IArmorStats tStats = getArmorStatsInternal(stack.getItemDamage());
		if (tStats != null)
		{
			tStats.onArmorTick(world, player, stack);
		}
		IArmorUpgrade[] upgrades = getUpgrades(stack);
		for (int q = 0; q < upgrades.length; q++)
		{
			IArmorUpgrade upgrade = upgrades[q];
			if (upgrade != null)
			{
				upgrade.onArmorTick(world, player, stack);
			}
		}
    }
	
	@Override
	public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IArmorStats tStats = getArmorStats(aStack);
		if (tStats != null && aPlayer != null) 
		{
			tStats.onArmorCrafted(aStack, aPlayer);
			IArmorUpgrade[] upgrades = getUpgrades(aStack);
			for (int q = 0; q < upgrades.length; q++)
			{
				IArmorUpgrade upgrade = upgrades[q];
				if (upgrade != null)
				{
					upgrade.onArmorCrafted(aStack, aPlayer);
				}
			}
		}
		super.onCreated(aStack, aWorld, aPlayer);
	}
	
	public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) if (tBehavior.canDispense(this, aSource, aStack)) return tBehavior.onDispense(this, aSource, aStack);
		EnumFacing enumfacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(aSource);
		ItemStack itemstack1 = aStack.splitStack(1);
		BehaviorDefaultDispenseItem.doDispense(aSource.getWorld(), itemstack1, 6, enumfacing, iposition);
		return aStack;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) try {
			aStack = tBehavior.onItemRightClick(this, aStack, aWorld, aPlayer);
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		if (aStack != null)
		{
			IArmorStats tStats = getArmorStats(aStack);
			if (tStats != null)
			{
				for (int q = 0; q < 4; q++)
				{
					if (tStats.isValidInSlot(3 - q))
					{
						ItemStack currentArmor = aPlayer.getCurrentArmor(q);
						if (currentArmor == null)
						{
							ItemStack toReturn = aStack.copy();
							toReturn.stackSize = 0;
							aPlayer.setCurrentItemOrArmor(q + 1, aStack);
							return toReturn;
						} else {
							aPlayer.setCurrentItemOrArmor(q + 1, aStack);
							return currentArmor;
						}
					}
				}
			}
		}
		return aStack;
	}
	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) try {
			if (tBehavior.onItemUse(this, aStack, aPlayer, aWorld, aX, aY, aZ, UT.Code.side(aSide), hitX, hitY, hitZ)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) try {
			if (tBehavior.onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, UT.Code.side(aSide), hitX, hitY, hitZ)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	@Override
	public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) try {
			if (tBehavior.onLeftClickEntity(this, aStack, aPlayer, aEntity)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	@Override
	public void onUpdate(ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {
		ArrayList<IBehavior<MultiItemArmor>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItemArmor> tBehavior : tList) tBehavior.onUpdate(this, aStack, aWorld, aPlayer, aTimer, aIsInHand);
	}
	public float recharge(ItemStack aStack, float aEnergy, boolean aDoInject) {return 0;}
	@Override
	public void registerIcons(IIconRegister aIconRegister) {
		//
	}
	@Override
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY)
	{
		IArmorStats tStats = getArmorStatsInternal(stack);
		if (tStats != null)
		{
			tStats.renderHelmetOverlay(stack, player, resolution, partialTicks, hasScreen, mouseX, mouseY);
		}
		IArmorUpgrade[] upgrades = getUpgrades(stack);
		for (int q = 0; q < upgrades.length; q++)
		{
			IArmorUpgrade spot = upgrades[q];
			if (spot != null)
			{
				spot.renderHelmetOverlay(stack, player, resolution, partialTicks, hasScreen, mouseX, mouseY);
			}
		}
	}
	@Override
	public boolean requiresMultipleRenderPasses() {
		return T;
	}
	public void setElectricity(ItemStack aStack, float joules) {/**/}
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return aStack;
		return tStats.setEnergyStored(aEnergyType, aStack, aAmount);
	}
	public void setFluidContent(ItemStack aStack, FluidStack aFluid) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make(); else tNBT.removeTag("gt.fluidcontent");
		if (aFluid != null && aFluid.amount > 0) UT.Fluids.save(tNBT, "gt.fluidcontent", aFluid);
		UT.NBT.set(aStack, tNBT);
		isItemStackUsable(aStack);
	}
	@Override public final Item setUnlocalizedName(String aName) {return this;}
	@Override public String toString() {return mName;}
	@Override
	public void updateItemStack(ItemStack aStack) {
		isItemStackUsable(aStack);
	}
	public boolean use(ItemStack aStack, double aAmount, EntityLivingBase aPlayer) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, aPlayer, null, null, 0, 0, 0, T);}
	@Override
	public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return F;
		return tStats.useEnergy(aEnergyType, aStack, aEnergyAmount, aPlayer, aInventory, aWorld, aX, aY, aZ, aDoUse);
	}
	
	public boolean onClickedWearing(ItemStack armorStack, int slot, World world, EntityPlayer player, PlayerInteractEvent.Action action, int x, int y, int z, int face, PlayerInteractEvent event)
	{
		IArmorStats tStats = getArmorStats(armorStack);
		if (tStats != null)
		{
			IArmorUpgrade[] upgrades = getUpgrades(armorStack);
			for (int q = upgrades.length - 1; q > -1; q--)
			{
				IArmorUpgrade upgrade = upgrades[q];
				if (upgrade != null)
				{
					if (upgrade.onClickedWearing(armorStack, slot, world, player, action, x, y, z, face, event))
					{
						return true;
					}
				}
			}
			return tStats.onClickedWearing(armorStack, slot, world, player, action, x, y, z, face, event);
		}
		return false;
	}
}
