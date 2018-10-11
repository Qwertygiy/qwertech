package com.kbi.qwertech.loaders;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.armor.upgrades.IArmorUpgrade;
import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.data.QTMT;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.armor.upgrades.Upgrade_SpringBoots;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.passive.EntityFrog;
import com.kbi.qwertech.items.behavior.Behavior_Spawn;
import com.kbi.qwertech.items.behavior.Behavior_ThrowEgg;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import gregapi.data.*;
import gregapi.data.CS.ModIDs;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.food.FoodStat;
import gregapi.item.multiitem.food.FoodStatFluid;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

@InterfaceList(value = {
		@Interface(iface = "squeek.applecore.api.food.IEdible", modid = ModIDs.APC)
		, @Interface(iface = "ic2.api.item.IItemReactorPlanStorage", modid = ModIDs.IC2C)
		, @Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
		, @Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
		, @Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
		})
public class RegisterItems {

	public static void run()
	{
		new MultiItemRandom(QwerTech.MODID, "qwertech.internal") {@Override public void addItems() {
			QTI.moldArrow.set(addItem(1, "Mold Shape Arrow"				, "", null, TD.Creative.HIDDEN));
			QTI.moldAxe.set(addItem(2, "Mold Shape Axe"				, "", null, TD.Creative.HIDDEN));
			QTI.moldBolt.set(addItem(3, "Mold Shape Bolt"				, "", null, TD.Creative.HIDDEN));
			QTI.moldChisel.set(addItem(4, "Mold Shape Chisel"				, "", null, TD.Creative.HIDDEN));
			QTI.moldChunk.set(addItem(5, "Mold Shape Chunk"				, "", null, TD.Creative.HIDDEN));
			QTI.moldDoubleAxe.set(addItem(6, "Mold Shape Double Axe"			, "", null, TD.Creative.HIDDEN));
			QTI.moldFile.set(addItem(7, "Mold Shape File"				, "", null, TD.Creative.HIDDEN));
			QTI.moldGear.set(addItem(8, "Mold Shape Gear"				, "", null, TD.Creative.HIDDEN));
			QTI.moldHammer.set(addItem(9, "Mold Shape Hammer"				, "", null, TD.Creative.HIDDEN));
			QTI.moldHoe.set(addItem(10, "Mold Shape Hoe"				, "", null, TD.Creative.HIDDEN));
			QTI.moldIngot.set(addItem(11, "Mold Shape Ingot"				, "", null, TD.Creative.HIDDEN));
			QTI.moldItemCasing.set(addItem(12, "Mold Shape Item Casing"		, "", null, TD.Creative.HIDDEN));
			QTI.moldLongRod.set(addItem(13, "Mold Shape Long Rod"			, "", null, TD.Creative.HIDDEN));
			QTI.moldPickaxe.set(addItem(14, "Mold Shape Pickaxe"			, "", null, TD.Creative.HIDDEN));
			QTI.moldPlate.set(addItem(15, "Mold Shape Plate"				, "", null, TD.Creative.HIDDEN));
			QTI.moldPlow.set(addItem(16, "Mold Shape Plow"				, "", null, TD.Creative.HIDDEN));
			QTI.moldRing.set(addItem(17, "Mold Shape Ring"				, "", null, TD.Creative.HIDDEN));
			QTI.moldRod.set(addItem(18, "Mold Shape Rod"				, "", null, TD.Creative.HIDDEN));
			QTI.moldSaw.set(addItem(19, "Mold Shape Saw"				, "", null, TD.Creative.HIDDEN));
			QTI.moldScrewdriver.set(addItem(20, "Mold Shape Screwdriver"		, "", null, TD.Creative.HIDDEN));
			QTI.moldSense.set(addItem(21, "Mold Shape Sense"				, "", null, TD.Creative.HIDDEN));
			QTI.moldShovel.set(addItem(22, "Mold Shape Shovel"			, "", null, TD.Creative.HIDDEN));
			QTI.moldSmallGear.set(addItem(23, "Mold Shape Small Gear"		, "", null, TD.Creative.HIDDEN));
			QTI.moldSword.set(addItem(24, "Mold Shape Sword"				, "", null, TD.Creative.HIDDEN));
			QTI.moldTinyPlate.set(addItem(25, "Mold Shape Tiny Plate"		, "", null, TD.Creative.HIDDEN));
			QTI.moldUniversalSpade.set(addItem(26, "Mold Shape Universal Spade"	, "", null, TD.Creative.HIDDEN));
			QTI.moldSpade.set(addItem(27, "Mold Shape Spade"				, "", null, TD.Creative.HIDDEN));
			QTI.moldMattock.set(addItem(28, "Mold Shape Mattock"			, "", null, TD.Creative.HIDDEN));
			QTI.moldMace.set(addItem(29, "Mold Shape Mace"				, "", null, TD.Creative.HIDDEN));
			QTI.batEmblem.set(addItem(30, "Achievement Bat"				, "", null, TD.Creative.HIDDEN));
			addItem(10000, "", "", null, TD.Creative.HIDDEN);
		}};

		QTI.syringe.set(new MultiItemRandom(QwerTech.MODID, "qwertech.dna") {
			@Override
			public void addItems() {
				addItem(0, "Empty Syringe", "This won't hurt a bit...");
				addItem(1, "Contaminated Syringe", "Trust me, you don't want to use this");
				addItem(2, "Salty Syringe", "A bit... crusty, inside");
				addItem(3, "Sugary Syringe", "A bit... crusty, inside");
				addItem(4, "Syringe of Water", "Drippity dropper", UT.Fluids.make("water", 100L));
                addItem(5, "Syringe of Distilled Water", "Purified droppity dripper", UT.Fluids.make("ic2distilledwater", 100L));

				addItem(10, "Syringe of Saltwater", "Non-potable", UT.Fluids.make("saltwater", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("saltwater", 100L), make(10), make(0), T));
				addItem(11, "Syringe of Salty Water", "Non-potable", UT.Fluids.make("seawater", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("seawater", 100L), make(11), make(0), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("water", 100L), make(11), make(2), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("ic2distilledwater", 100L), make(11), make(2), T));

				addItem(20, "Syringe of Apple Juice", "WARNING: Keep away from medical professionals", new FluidContainerData(UT.Fluids.make("binnie.juiceapple", 100L), make(20), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceapple", 100L), make(20), make(3), T));
				addItem(21, "Syringe of Beet Juice", "Not THAT kind of healthy for you", new FluidContainerData(UT.Fluids.make("beetjuice", 100L), make(21), make(0), T), new FluidContainerData(UT.Fluids.make("beetjuice", 100L), make(21), make(3), T));
				addItem(22, "Syringe of Kiwi Juice", "The fruit, not the bird... ew", new FluidContainerData(UT.Fluids.make("kiwijuice", 100L), make(22), make(0), T), new FluidContainerData(UT.Fluids.make("kiwijuice", 100L), make(22), make(3), T));
				addItem(23, "Syringe of Melon Juice", "Not to be used for melon enhancement", new FluidContainerData(UT.Fluids.make("melonjuice", 100L), make(23), make(0), T), new FluidContainerData(UT.Fluids.make("melonjuice", 100L), make(23), make(3), T));
				addItem(24, "Syringe of Plum Juice", "Prune juice isn't a thing", new FluidContainerData(UT.Fluids.make("binnie.juiceplum", 100L), make(24), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceplum", 100L), make(24), make(3), T));
				addItem(25, "Syringe of Blackberry Juice", "They prefer to be called African-Americanberries", new FluidContainerData(UT.Fluids.make("blackberryjuice", 100L), make(25), make(0), T), new FluidContainerData(UT.Fluids.make("blackberryjuice", 100L), make(25), make(3), T));
				addItem(26, "Syringe of Apricot Juice", "APRICODABRA! ...Nothing happened.", new FluidContainerData(UT.Fluids.make("binnie.juiceapricot", 100L), make(26), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceapricot", 100L), make(26), make(3), T));
				addItem(27, "Syringe of Carrot Juice", "Bunny transfusion", new FluidContainerData(UT.Fluids.make("binnie.juicecarrot", 100L), make(27), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicecarrot", 100L), make(27), make(3), T));
				addItem(28, "Syringe of Red Grape Juice", "Baby's First Wine", new FluidContainerData(UT.Fluids.make("binnie.juiceredgrape", 100L), make(28), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceredgrape", 100L), make(28), make(3), T));
				addItem(29, "Syringe of Blood*", "(actually Tomato Juice)", new FluidContainerData(UT.Fluids.make("binnie.juicetomato", 100L), make(29), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicetomato", 100L), make(29), make(3), T));
				addItem(30, "Syringe of Blood*", "(actually Cherry Juice)", new FluidContainerData(UT.Fluids.make("binnie.juicecherry", 100L), make(30), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicecherry", 100L), make(30), make(3), T));
				addItem(31, "Syringe of Fig Juice", "Figgin' useless", new FluidContainerData(UT.Fluids.make("figjuice", 100L), make(31), make(0), T), new FluidContainerData(UT.Fluids.make("figjuice", 100L), make(31), make(3), T));
				addItem(32, "Syringe of Pear Juice", "Will not give you a pear shape", new FluidContainerData(UT.Fluids.make("binnie.juicepear", 100L), make(32), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicepear", 100L), make(32), make(3), T));
				addItem(33, "Syringe of Peach Juice", "The 'ch' is important", new FluidContainerData(UT.Fluids.make("binnie.juicepeach", 100L), make(33), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicepeach", 100L), make(33), make(3), T));
				addItem(34, "Syringe of Blood*", "(actually Strawberry Juice)", new FluidContainerData(UT.Fluids.make("strawberryjuice", 100L), make(34), make(0), T), new FluidContainerData(UT.Fluids.make("strawberryjuice", 100L), make(34), make(3), T));
				addItem(35, "Syringe of Pomegranate Juice", "More useful than granite pom-poms", new FluidContainerData(UT.Fluids.make("pomegranatejuice", 100L), make(35), make(0), T), new FluidContainerData(UT.Fluids.make("pomegranatejuice", 100L), make(35), make(3), T));
				addItem(36, "Syringe of Juice", "No, I don't know, it's just... juice", new FluidContainerData(UT.Fluids.make("juice", 100L), make(36), make(0), T), new FluidContainerData(UT.Fluids.make("juice", 100L), make(36), make(3), T));
				addItem(37, "Syringe of Lemon Juice", "Concentrated sour", new FluidContainerData(UT.Fluids.make("binnie.juicelemon", 100L), make(37), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicelemon", 100L), make(37), make(3), T));
				addItem(38, "Syringe of Lime Juice", "It's like lemon juice, but green, because green is cooler", new FluidContainerData(UT.Fluids.make("binnie.juicelime", 100L), make(38), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicelime", 100L), make(38), make(3), T));
				addItem(39, "Syringe of Papaya Juice", "No, I didn't say pie juice", new FluidContainerData(UT.Fluids.make("papayajuice", 100L), make(39), make(0), T), new FluidContainerData(UT.Fluids.make("papayajuice", 100L), make(39), make(3), T));
				addItem(40, "Syringe of Currant Juice", "The most up-to-date juice there is", new FluidContainerData(UT.Fluids.make("currantjuice", 100L), make(40), make(0), T), new FluidContainerData(UT.Fluids.make("currantjuice", 100L), make(40), make(3), T));
				addItem(41, "Syringe of Blueberry Juice", "Be more careful with this than Willy", new FluidContainerData(UT.Fluids.make("blueberryjuice", 100L), make(41), make(0), T), new FluidContainerData(UT.Fluids.make("blueberryjuice", 100L), make(41), make(3), T));
				addItem(42, "Syringe of Potato Juice", "Yes. Potato juice. Pure potato.", new FluidContainerData(UT.Fluids.make("potatojuice", 100L), make(42), make(0), T), new FluidContainerData(UT.Fluids.make("potatojuice", 100L), make(42), make(3), T));
				addItem(43, "Syringe of Olive Juice", "Yes, it's a thing", new FluidContainerData(UT.Fluids.make("binnie.juiceolive", 100L), make(43), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceolive", 100L), make(43), make(3), T));
				addItem(44, "Syringe of Blood*", "(actually Cranberry Juice)", new FluidContainerData(UT.Fluids.make("binnie.juicecranberry", 100L), make(44), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicecranberry", 100L), make(44), make(3), T));
				addItem(45, "Syringe of White Grape Juice", "The good kind", new FluidContainerData(UT.Fluids.make("binnie.juicewhitegrape", 100L), make(45), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicewhitegrape", 100L), make(45), make(3), T));
				addItem(46, "Syringe of Mango Juice", "Go Juiceman", new FluidContainerData(UT.Fluids.make("mangojuice", 100L), make(46), make(0), T), new FluidContainerData(UT.Fluids.make("mangojuice", 100L), make(46), make(3), T));
				addItem(47, "Syringe of Banana Juice", "I ain't no hollaback girl", new FluidContainerData(UT.Fluids.make("binnie.juicebanana", 100L), make(47), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicebanana", 100L), make(47), make(3), T));
				addItem(48, "Syringe of Grapefruit Juice", "NOT to be confused with Grape fruit-juice", new FluidContainerData(UT.Fluids.make("binnie.juicegrapefruit", 100L), make(48), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicegrapefruit", 100L), make(48), make(3), T));
				addItem(49, "Syringe of Orange Juice", "Actually more of a Yellow Juice", new FluidContainerData(UT.Fluids.make("binnie.juiceorange", 100L), make(49), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceorange", 100L), make(49), make(3), T));
				addItem(50, "Syringe of Persimmon Juice", "1 persimmon per simmon", new FluidContainerData(UT.Fluids.make("persimmonjuice", 100L), make(50), make(0), T), new FluidContainerData(UT.Fluids.make("persimmonjuice", 100L), make(50), make(3), T));
				addItem(51, "Syringe of Elderberry Juice", "Will not help with age wrinkles", new FluidContainerData(UT.Fluids.make("binnie.juiceelderberry", 100L), make(51), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juiceelderberry", 100L), make(51), make(3), T));
				addItem(52, "Syringe of Blood*", "(actually Raspberry Juice)", new FluidContainerData(UT.Fluids.make("raspberryjuice", 100L), make(52), make(0), T), new FluidContainerData(UT.Fluids.make("raspberryjuice", 100L), make(52), make(3), T));
				addItem(53, "Syringe of Pumpkin Juice", "Juice'o'Lantern", new FluidContainerData(UT.Fluids.make("pumpkinjuice", 100L), make(53), make(0), T), new FluidContainerData(UT.Fluids.make("pumpkinjuice", 100L), make(53), make(3), T));
				addItem(54, "Syringe of Gooseberry Juice", "Has nothing to do with birds", new FluidContainerData(UT.Fluids.make("gooseberryjuice", 100L), make(54), make(0), T), new FluidContainerData(UT.Fluids.make("gooseberryjuice", 100L), make(54), make(3), T));
				addItem(55, "Syringe of Starfruit Juice", "No, no... star juice is just Hydrogen", new FluidContainerData(UT.Fluids.make("starfruitjuice", 100L), make(55), make(0), T), new FluidContainerData(UT.Fluids.make("starfruitjuice", 100L), make(55), make(3), T));
				addItem(56, "Syringe of Pineapple Juice", "Or Ananas Juice, if you are so inclined", new FluidContainerData(UT.Fluids.make("binnie.juicepineapple", 100L), make(56), make(0), T), new FluidContainerData(UT.Fluids.make("binnie.juicepineapple", 100L), make(56), make(3), T));
				addItem(57, "Syringe of Grape Juice", "It's grrrrrrrape", new FluidContainerData(UT.Fluids.make("grapejuice", 100L), make(57), make(0), T), new FluidContainerData(UT.Fluids.make("grapejuice", 100L), make(57), make(3), T));
                addItem(58, "Syringe of Sugarwater", "100cc of sugar helps the medicine flow down", UT.Fluids.make("sugarwater", 100L), new FluidContainerData(UT.Fluids.make("water", 100L), make(58), make(3), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("sugarwater", 100L), make(58), make(0), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("ic2distilledwater", 100L), make(58), make(3), T));

				addItem(100, "Syringe of Dirty Water", "Needs to be flushed a few times", new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("waterdirty", 100L), make(100), make(1), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("water", 100L), make(100), make(1), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("waterdirty", 100L), make(100), make(0), T), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("ic2distilledwater", 100L), make(100), make(1), T));
				addItem(101, "Syringe of Milk", "Not for infant injection", UT.Fluids.make("milk", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("milk", 100L), make(101), make(0), T));
				addItem(102, "Syringe of Blood", "", UT.Fluids.make("blood", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("blood", 100L), make(102), make(0), T));
				addItem(103, "Syringe of DNA", "", UT.Fluids.make("dna", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("dna", 100L), make(103), make(0), T));
				addItem(104, "Syringe of Soymilk", "Not actually milk", UT.Fluids.make("soymilk", 100L), new FluidContainerRegistry.FluidContainerData(UT.Fluids.make("soymilk", 100L), make(104), make(0), T));

                CR.shaped(make(2, 0), new Object[]{"ABA", " C ", " D ", 'A', OP.round.dat(MT.Plastic).toString(), 'B', OP.stick.dat(MT.Plastic).toString(), 'C', OP.pipeTiny.dat(MT.Plastic).toString(), 'D', OP.bolt.dat(ANY.Steel).toString()});
			}

            @Override
            public int getCapacity(ItemStack aStack) {
                return 100;
            }

            @Override
			public int fill(ItemStack aStack, FluidStack aFluid, boolean doFill) {
				int dam = aStack.getItemDamage();
				switch(dam)
				{
					/*case 1:
					case 3:
						if (aFluid.isFluidEqual(UT.Fluids.make("water", 1))) {
							if (doFill) aStack.setItemDamage(0);
							return fill(doFill ? aStack : make(0), UT.Fluids.make("waterdirty", aFluid.amount), doFill);
						}
						break;
					case 2:
						if (aFluid.isFluidEqual(UT.Fluids.make("water", 1))) {
							if (doFill) aStack.setItemDamage(0);
							return fill(doFill ? aStack : make(0), UT.Fluids.make("saltwater", aFluid.amount), doFill);
						}
						break;*/
                    case 1:
                    case 2:
                    case 3:
                        if (aFluid.isFluidEqual(UT.Fluids.make("dna", 1)) || aFluid.isFluidEqual(UT.Fluids.make("blood", 1)))
                        {
                            NBTTagCompound taggy = UT.NBT.getOrCreate(aStack);
                            taggy.setBoolean("contaminated", true);
                            aStack.setTagCompound(taggy);
                        }
                        break;
				}
				return super.fill(aStack, aFluid, doFill);
			}

            @Override
            public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
			    System.out.println("DRAIN");
                return super.drain(aStack, aMaxDrain, aDoDrain);
            }

            @Override public ItemStack getContainerItem(ItemStack aStack) {
				int dam = aStack.getItemDamage();
                if (CS.RNGSUS.nextInt(10) == 1)
                {
                    return this.make(0);
                }
				if (dam > 100 && dam < 1000) {
					return this.make(1);
				} else if(dam >= 10 && dam < 20) {
					return this.make(2);
				} else if(dam >= 20 && dam < 100) {
					return this.make(3);
				}
				switch(dam)
				{
					case 100:
						return this.make(1);
					case 4:
                    case 5:
						return this.make(0);
				}
				return null;
			}

			Object emptySyringe = null;
			Object fullSyringe = null;
			Object syringeContents = null;
			@Override
			public void registerIcons(IIconRegister aIconRegister) {
				emptySyringe = aIconRegister.registerIcon("qwertech:qwertech.dna/syringeEmpty");
				fullSyringe = aIconRegister.registerIcon("qwertech:qwertech.dna/syringeFull");
				syringeContents = aIconRegister.registerIcon("qwertech:qwertech.dna/syringeContents");
			}

			@Override
			public boolean requiresMultipleRenderPasses()
			{
				return true;
			}

			@Override
			public int getRenderPasses(int metadata)
			{
				if (metadata > 3) {
					return 2;
				}
				return 1;
			}

			@Override
			public IIcon getIconIndex(ItemStack stack)
			{
				return getIcon(stack, 0);
			}

			@Override
			public IIcon getIcon(ItemStack stack, int renderpass)
			{
				if (renderpass <= 0) {
					if (this.getFluid(stack) != null)
					{
						return (IIcon)fullSyringe;
					} else {
						return (IIcon)emptySyringe;
					}
				} else {
					return ((IIcon)syringeContents);
				}
			}

			@Override
			public IIcon getIconFromDamage(int aMetaData) {
				return getIconFromDamageForRenderPass(aMetaData, 0);
			}

			@Override
			public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
				return this.getIcon(aStack, aRenderPass);
			}

			@Override
			public IIcon getIconFromDamageForRenderPass(int damage, int renderpass)
			{
				return this.getIcon(ST.make(this, 1, damage), renderpass);
			}
		});
		
		QTI.qwerFood.set(new MultiItemRandom(QwerTech.MODID, "qwertech.food") {@Override public void addItems() {
			addItem(0, "Mozzarella"					, "Itsa good cheese", 					new FoodStat(3, 0.5F, 0F, 310F, 0.1F, EnumAction.eat, null, false, false, false, false));
			addItem(1, "Raw Parmesan"				, "Needs to be soaked in saltwater", 	new FoodStat(1, 0.1F, 0F, 310F, 0.1F, EnumAction.eat, null, false, false, false, false));
			addItem(2, "Parmesan"					, "Ready for grating!", 				new FoodStat(3, 0.5F, -10F, 310F, 0.1F, EnumAction.eat, null, false, false, false, false));
			addItem(3, "Grated Parmesan"			, "Pasta perfection", 					new FoodStat(3, 0.5F, -10F, 310F, 0.1F, EnumAction.eat, null, false, false, false, false));
			addItem(4, "Flat Dough with Sauce"		, "For making Pizza");
			addItem(5, "Raw Turkey Breast"			, "",									"listAllmeatraw", "listAllturkeyraw", new FoodStat(3, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 12, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(6, "Cooked Turkey Breast"		, "",									"listAllmeatcooked", "listAllturkeycooked", new FoodStat(6, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 12, EnumAction.eat, null, false, true, false, true));
			addItem(7, "Raw Turkey Leg" 			, "",									"listAllmeatraw", "listAllturkeyraw", new FoodStat(3, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 3, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(8, "Cooked Turkey Leg"			, "",									"listAllmeatcooked", "listAllturkeycooked", new FoodStat(8, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 3, EnumAction.eat, null, false, true, false, true));
			addItem(9, "Raw Chicken Wing"			, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 5, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(10, "Cooked Chicken Wing"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 5, EnumAction.eat, null, false, true, false, true));
			addItem(11, "Raw Turkey Wing"			, "",									"listAllmeatraw", "listAllturkeyraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 3, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(12, "Cooked Turkey Wing"		, "",									"listAllmeatcooked", "listAllturkeycooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 3, EnumAction.eat, null, false, true, false, true));
			addItem(13, "Turkey Egg"				, "",									"listAllegg", new Behavior_ThrowEgg());
			addItem(14, "Hard-Boiled Turkey Egg"	, "",									"foodBoiledegg", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 10, EnumAction.eat, null, false, true, false, true));
			addItem(15, "Hard-Boiled Egg"			, "",									"foodBoiledegg", new FoodStat(4, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 5, EnumAction.eat, null, false, true, false, true));
			addItem(16, "Raw Chicken Leg"			, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 5, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(17, "Cooked Chicken Leg"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(6, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 12, EnumAction.eat, null, false, true, false, true));
			addItem(18, "Fried Chicken Leg"			, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(6, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 30, EnumAction.eat, null, false, true, false, true));
			addItem(19, "Fried Turkey Leg"			, "",									"listAllmeatcooked", "listAllturkeycooked", new FoodStat(8, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 30, EnumAction.eat, null, false, true, false, true));
			addItem(20, "Fried Chicken Wing"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(6, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 30, EnumAction.eat, null, false, true, false, true));
			addItem(21, "Fried Turkey Wing"			, "",									"listAllmeatcooked", "listAllturkeycooked", new FoodStat(6, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 30, EnumAction.eat, null, false, true, false, true));
			addItem(22, "Feather"			        , "",	                    			"itemFeather", "feather", "craftingFeather");
			addItem(23, "Whole Raw Turkey"			, "Too big to eat",						"carcassTurkey");
			addItem(24, "Whole Cooked Turkey"		, "Too big to eat",						"carcassTurkey");
			addItem(25, "Raw Chicken Breast"		, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(3, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 16, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(26, "Cooked Chicken Breast"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 12, EnumAction.eat, null, false, true, false, true));
			addItem(27, "Whole Raw Chicken"			, "Too big to eat",						"carcassChicken");
			addItem(28, "Whole Cooked Chicken"		, "Too big to eat",						"carcassTurkey");
			addItem(29, "Raw Frog Leg"				, "Le slimey",							"listAllmeatraw", "listAllfrograw", new FoodStat(2, 0.5F, 0.0F, 310.0F, 0.1F, 0, 0, 3, 0, 4, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20, Potion.confusion.id, 500, 1, 50));
			addItem(30, "Cooked Frog Leg"			, "Lucky ribbit foot",					"listAllmeatcooked", "listAllfrogcooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 3, 0, 4, EnumAction.eat, null, false, true, false, true, Potion.confusion.id, 100, 1, 10, Potion.jump.id, 1000, 1, 10));
			addItem(31, "Frog Eggs"					, "Just add water",						"foodCaviar");
			addItem(32, "Chicken Egg"				, "",  									"listAllegg");
			addItem(33, "Whole Raw Junglefowl"		, "",									"carcassChicken");
			addItem(34, "Whole Cooked Junglefowl"	, "", 									"carcassChicken");
			addItem(35, "Raw Junglefowl Breast"		, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 14, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(36, "Cooked Junglefowl Breast"	, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(4, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 10, EnumAction.eat, null, false, true, false, true));
			addItem(37, "Raw Junglefowl Wing"		, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 4, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(38, "Cooked Junglefowl Wing"	, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(4, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 4, EnumAction.eat, null, false, true, false, true));
			addItem(39, "Raw Junglefowl Leg"		, "",									"listAllmeatraw", "listAllchickenraw", new FoodStat(2, 0.6F, 0.0F, 310.0F, 0.1F, 0, 0, 0, 0, 5, EnumAction.eat, null, false, true, false, true, Potion.hunger.id, 1000, 1, 20));
			addItem(40, "Cooked Junglefowl Leg"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 10, EnumAction.eat, null, false, true, false, true));
			addItem(41, "Fried Junglefowl Leg"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 26, EnumAction.eat, null, false, true, false, true));
			addItem(42, "Fried Junglefowl Wing"		, "",									"listAllmeatcooked", "listAllchickencooked", new FoodStat(5, 0.6F, 0.0F, 311.0F, 0.5F, 0, 0, 0, 0, 26, EnumAction.eat, null, false, true, false, true));
			addItem(43, "Junglefowl Egg"			, "",  									"listAllegg");


			addItem(1000, "Tomato Sauce"			, "", 									UT.Fluids.make("tomatosauce", 250L), FoodStatFluid.INSTANCE );
			addItem(1001, "Salsa"					, "Mild", 								UT.Fluids.make("mildsalsa", 250L), FoodStatFluid.INSTANCE );
			addItem(1002, "Salsa"					, "Medium", 							UT.Fluids.make("salsa", 250L), FoodStatFluid.INSTANCE );
			addItem(1003, "Salsa"					, "Magmatic", 							UT.Fluids.make("hotsalsa", 250L), FoodStatFluid.INSTANCE );
			
			QTI.mozzarella.set(ST.make(this, 1, 0));
			QTI.parmesanRaw.set(ST.make(this, 1, 1));
			QTI.parmesan.set(ST.make(this, 1, 2));
			QTI.parmesanGrated.set(ST.make(this, 1, 3));
			QTI.doughFlatSauce.set(ST.make(this, 1, 4));
			QTI.turkeyBreastRaw.set(ST.make(this, 1, 5));
			QTI.turkeyBreastCooked.set(ST.make(this, 1, 6));
			QTI.turkeyLegRaw.set(ST.make(this, 1, 7));
			QTI.turkeyLegCooked.set(ST.make(this, 1, 8));
			QTI.chickenWingRaw.set(ST.make(this, 1, 9));
			QTI.chickenWingCooked.set(ST.make(this, 1, 10));
			QTI.turkeyWingRaw.set(ST.make(this, 1, 11));
			QTI.turkeyWingCooked.set(ST.make(this, 1, 12));
			QTI.turkeyEgg.set(ST.make(this, 1, 13));
			QTI.turkeyEggHardBoiled.set(ST.make(this, 1, 14));
			QTI.chickenEggHardBoiled.set(ST.make(this, 1, 15));
			QTI.chickenLegRaw.set(ST.make(this, 1, 16));
			QTI.chickenLegCooked.set(ST.make(this, 1, 17));
			QTI.chickenLegFried.set(ST.make(this, 1, 18));
			QTI.turkeyLegFried.set(ST.make(this, 1, 19));
			QTI.chickenWingFried.set(ST.make(this, 1, 20));
			QTI.turkeyWingFried.set(ST.make(this, 1, 21));
			QTI.turkeyFeather.set(ST.make(this, 1, 22));
			QTI.turkeyWholeRaw.set(ST.make(this, 1, 23));
			QTI.turkeyWholeCooked.set(ST.make(this, 1, 24));
			QTI.chickenBreastRaw.set(ST.make(this, 1, 25));
			QTI.chickenBreastCooked.set(ST.make(this, 1, 26));
			QTI.chickenWholeRaw.set(ST.make(this, 1, 27));
			QTI.chickenWholeCooked.set(ST.make(this, 1, 28));
			QTI.frogLegRaw.set(ST.make(this, 1, 29));
			QTI.frogLegCooked.set(ST.make(this, 1, 30));
			QTI.frogEggs.set(ST.make(this, 1, 31));
			QTI.chickenEgg.set(ST.make(this, 1, 32));
			QTI.junglefowlWholeRaw.set(ST.make(this, 1, 33));
			QTI.junglefowlWholeCooked.set(ST.make(this, 1, 34));
			QTI.junglefowlBreastRaw.set(ST.make(this, 1, 35));
			QTI.junglefowlBreastCooked.set(ST.make(this, 1, 36));
			QTI.junglefowlWingRaw.set(ST.make(this, 1, 37));
			QTI.junglefowlWingCooked.set(ST.make(this, 1, 38));
			QTI.junglefowlLegRaw.set(ST.make(this, 1, 39));
			QTI.junglefowlLegCooked.set(ST.make(this, 1, 40));
			QTI.junglefowlLegFried.set(ST.make(this, 1, 41));
			QTI.junglefowlWingFried.set(ST.make(this, 1, 42));
			QTI.junglefowlEgg.set(ST.make(this, 1, 43));

			QTI.tomatoSauce.set(ST.make(this, 1, 1000));
			QTI.salsaMild.set(ST.make(this, 1, 1001));
			QTI.salsaMedium.set(ST.make(this, 1, 1002));
			QTI.salsaMagmatic.set(ST.make(this, 1, 1003));
			
			RM.add_smelting(QTI.turkeyBreastRaw.get(1), QTI.turkeyBreastCooked.get(1));
			RM.add_smelting(QTI.turkeyLegRaw.get(1), QTI.turkeyLegCooked.get(1));
			RM.add_smelting(QTI.chickenWingRaw.get(1), QTI.chickenWingCooked.get(1));
			RM.add_smelting(QTI.turkeyWingRaw.get(1), QTI.turkeyWingCooked.get(1));
			RM.add_smelting(QTI.chickenLegRaw.get(1), QTI.chickenLegCooked.get(1));
			RM.add_smelting(QTI.turkeyWholeRaw.get(1), QTI.turkeyWholeCooked.get(1));
			RM.add_smelting(QTI.chickenBreastRaw.get(1), QTI.chickenBreastCooked.get(1));
			RM.add_smelting(QTI.chickenWholeRaw.get(1), QTI.chickenWholeCooked.get(1));
			RM.add_smelting(QTI.frogLegRaw.get(1), QTI.frogLegCooked.get(1));
			RM.add_smelting(QTI.junglefowlBreastRaw.get(1), QTI.junglefowlBreastCooked.get(1));
			RM.add_smelting(QTI.junglefowlLegRaw.get(1), QTI.junglefowlLegCooked.get(1));
			RM.add_smelting(QTI.junglefowlWholeRaw.get(1), QTI.junglefowlWholeCooked.get(1));
			RM.add_smelting(QTI.junglefowlWingRaw.get(1), QTI.junglefowlWingCooked.get(1));
			
			//mix cheese chunks to make mozzarella
			RM.Mixer.addRecipe2(true, 16L, 16L, OP.chunkGt.mat(MT.Cheese, 2), OP.chunkGt.mat(MT.Cheese, 2), QTI.mozzarella.get(1));
			//mix cheese powder and milk to make raw parmesan
			RM.Mixer.addRecipe1(true, 16L, 32L, OP.dustSmall.mat(MT.Cheese, 3), UT.Fluids.make("milk", 1000), null, QTI.parmesanRaw.get(1));
			//mix raw parmesan and saltwater to make parmesan
			RM.Bath.addRecipe1(true, 0L, 256L, QTI.parmesanRaw.get(1), UT.Fluids.make("saltwater", 1000), UT.Fluids.make("water", 1000), QTI.parmesan.get(1));
			RM.Bath.addRecipe1(true, 0L, 256L, QTI.parmesanRaw.get(1), UT.Fluids.make("seawater", 1000), UT.Fluids.make("water", 1000), QTI.parmesan.get(1));
			RM.Mortar.addRecipe1(true, 16L, 16L, QTI.parmesan.get(1), QTI.parmesanGrated.get(1));
			
			//replace "dough with ketchup" with "dough with sauce", and cheese slice pizza with mozzarella pizza
			CR.remout(IL.Food_Dough_Flat_Ketchup.get(1));
			CR.remout(IL.Food_Pizza_Cheese_Raw.get(1));
			CR.remout(IL.Food_Pizza_Meat_Raw.get(1));
			CR.remout(IL.Food_Pizza_Veggie_Raw.get(1));
			CR.remout(IL.Food_Pizza_Ananas_Raw.get(1));
			CR.shapeless(QTI.doughFlatSauce.get(1), CR.DEF_NAC, new Object[] { ST.make(this, 1, 1000), IL.Food_Dough_Flat });
			CR.shapeless(QTI.doughFlatSauce.get(2), CR.DEF_NAC, new Object[] { ST.make(this, 1, 1000), IL.Food_Dough_Flat, IL.Food_Dough_Flat });
			CR.shapeless(QTI.doughFlatSauce.get(3), CR.DEF_NAC, new Object[] { ST.make(this, 1, 1000), IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat });
			CR.shapeless(QTI.doughFlatSauce.get(4), CR.DEF_NAC, new Object[] { ST.make(this, 1, 1000), IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat });
			CR.shapeless(QTI.doughFlatSauce.get(5), CR.DEF_NAC, new Object[] { ST.make(this, 1, 1000), IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat });
			CR.shapeless(IL.Food_Pizza_Cheese_Raw.get(1), CR.DEF, new Object[] {QTI.doughFlatSauce, QTI.mozzarella, QTI.mozzarella, QTI.mozzarella});
			CR.shapeless(IL.Food_Pizza_Meat_Raw.get(1), CR.DEF, new Object[] {QTI.doughFlatSauce, QTI.mozzarella, OP.dust.dat(MT.MeatCooked)});
			CR.shapeless(IL.Food_Pizza_Meat_Raw.get(1), CR.DEF, new Object[] {QTI.doughFlatSauce, QTI.mozzarella, OP.scrap.dat(MT.MeatCooked)});
			CR.shapeless(IL.Food_Pizza_Veggie_Raw.get(1), CR.DEF, new Object[] {QTI.doughFlatSauce, QTI.mozzarella, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
			CR.shapeless(IL.Food_Pizza_Ananas_Raw.get(1), CR.DEF, new Object[] {QTI.doughFlatSauce,  IL.Food_Ananas_Sliced, IL.Food_Ananas_Sliced, IL.Food_Ham_Slice_Cooked, QTI.mozzarella});
			
			//cut chicken into legs and breasts
			CR.shapeless(QTI.chickenLegRaw.get(2), CR.DEF, new Object[]{QTI.chickenWholeRaw, "craftingToolKnife"});
			CR.shapeless(QTI.chickenLegCooked.get(2), CR.DEF, new Object[]{QTI.chickenWholeCooked, "craftingToolKnife"});
			CR.shapeless(QTI.turkeyLegRaw.get(2), CR.DEF, new Object[]{QTI.turkeyWholeRaw, "craftingToolKnife"});
			CR.shapeless(QTI.turkeyLegCooked.get(2), CR.DEF, new Object[]{QTI.turkeyWholeCooked, "craftingToolKnife"});
			CR.shapeless(QTI.junglefowlLegRaw.get(2), CR.DEF, new Object[]{QTI.junglefowlWholeRaw, "craftingToolKnife"});
			CR.shapeless(QTI.junglefowlLegCooked.get(2), CR.DEF, new Object[]{QTI.junglefowlWholeCooked, "craftingToolKnife"});

			//fry chicken
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.chickenLegRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.chickenLegFried.get(1));
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.chickenWingRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.chickenWingFried.get(1));
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.turkeyLegRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.turkeyLegFried.get(1));
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.turkeyWingRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.turkeyWingFried.get(1));
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.junglefowlLegRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.junglefowlLegFried.get(1));
			RM.Bath.addRecipe1(true, 0L, 16L, QTI.junglefowlWingRaw.get(1), UT.Fluids.make("hotfryingoil", 10), null, QTI.junglefowlLegFried.get(1));

			//make sure turkey feathers can be quills too
			CR.shapeless(ST.make(Items.writable_book, 1, 0), CR.DEF, new Object[]{ST.make(Items.book, 1, 0), "feather", "dyeBlack"});
			
			//temporary ketchup -> tomato sauce recipe
			CR.shapeless(QTI.tomatoSauce.get(1), CR.DEF, new Object[] {"foodKetchup", "cropTomato"});
			
		}

			@Override
			public void addAdditionalToolTips(List aList, ItemStack aStack, boolean aF3_H) {
				super.addAdditionalToolTips(aList, aStack, aF3_H);
				NBTTagCompound tag = UT.NBT.getOrCreate(aStack);
				if (tag.hasKey("QTgenes"))
				{
					if (tag.hasKey("Timer"))
					{
						if (net.minecraft.client.Minecraft.getMinecraft().theWorld.getTotalWorldTime() > tag.getLong("Timer"))
						{
							aList.add("Ready to hatch!");
						} else {
							aList.add("Will hatch in " + (tag.getLong("Timer") - net.minecraft.client.Minecraft.getMinecraft().theWorld.getTotalWorldTime()));
						}
					} else {
						aList.add("Fertilized");
					}
				}
			}

			@Override
			public int getColorFromItemStack(ItemStack item, int rp) {
				NBTTagCompound tag = UT.NBT.getOrCreate(item);
				if (tag.hasKey("itemColor"))
				{
					return tag.getInteger("itemColor");
				} else
				{
					return super.getColorFromItemStack(item, rp);
				}
			}

			@Override
		public boolean onEntityItemUpdate(EntityItem entityItem)
	    {
			if (entityItem.worldObj.isRemote) return false;
			switch(entityItem.getEntityItem().getItemDamage())
			{
				case 31:
					if (entityItem.isInsideOfMaterial(Material.water))
					{
						if (entityItem.worldObj.rand.nextInt(4000) == 1)
						{
							for (int q = 0; q < entityItem.getEntityItem().stackSize; q++)
							{
								EntityFrog frog = new EntityFrog(entityItem.worldObj);
								frog.setPosition(entityItem.posX, entityItem.posY, entityItem.posZ);
								frog.setGrowingAge(-8000);
								entityItem.worldObj.spawnEntityInWorld(frog);
								if (q > 3) break;
							}
							entityItem.setDead();
							return true;
						}
					}
					break;
				case 13:
				case 32:
				case 43:
					ItemStack IT = entityItem.getEntityItem();
					NBTTagCompound nbt = UT.NBT.getOrCreate(IT);
					if (nbt.hasKey("Timer"))
					{
						if (entityItem.worldObj.getTotalWorldTime() > nbt.getLong("Timer"))
						{
							if (nbt.hasKey("QTgenes"))
							{
								EntityPhasianidae ep = new EntityPhasianidae(entityItem.worldObj);
								ep.setPosition(entityItem.posX, entityItem.posY, entityItem.posZ);
								int maturity = nbt.getCompoundTag("QTgenes").getShort("maturity") * -1;
								ep.readEntityFromNBT(nbt);
								ep.setGrowingAge(maturity);
								entityItem.worldObj.spawnEntityInWorld(ep);
								entityItem.setDead();
								return true;
							}
						}
					}
					break;
				default:
					break;
			}
	        return false;
	    }
		
		@Override public ItemStack getContainerItem(ItemStack aStack) {
			if (aStack.getItemDamage() >= 1000 && aStack.getItemDamage() < 2000) {
				return IL.Bottle_Empty.get(1L);
			}
			switch (aStack.getItemDamage())
			{
				case 23:
				{
					return ST.make(this, 3, 5);
				}
				case 24:
				{
					return ST.make(this, 3, 6);
				}
				case 27:
				{
					return ST.make(this, 2, 25);
				}
				case 28:
				{
					return ST.make(this, 2, 26);
				}
				case 33:
				{
					return ST.make(this, 2, 35);
				}
				case 34:
				{
					return ST.make(this, 2, 36);
				}
				default:
				{
					return null;
				}
			}			
		}});
		
		QTI.qwerFood.getItem().setCreativeTab(CreativeTabs.tabFood);
		QTI.qwerFood.getItem().setFull3D();
		
		new MultiItemRandom(QwerTech.MODID, "qwertech.animals") {@Override public void addItems() {
			addItem(0, "Spawn Turkey", "", new Behavior_Spawn(EntityTurkey.class));
			addItem(1, "Spawn Frog", "", new Behavior_Spawn(EntityFrog.class));
			addItem(2, "Spawn Chicken", "", new Behavior_Spawn(EntityPhasianidae.class));

			addItem(1000, "Frog-in-a-Jar", "Greenie in a Bottle", new Behavior_Spawn(EntityFrog.class));
			addItem(1001, "Chicken-in-a-Jar", "Bottle O'Birb", new Behavior_Spawn(EntityChicken.class));
			addItem(1002, "Slime-in-a-Jar", "Greenie in a Bottle", new Behavior_Spawn(EntitySlime.class));
			addItem(1003, "Magma-in-a-Jar", "Straight from the Nether", new Behavior_Spawn(EntityMagmaCube.class));
			addItem(1004, "Spider-in-a-Jar", "The humane way", new Behavior_Spawn(EntityCaveSpider.class));
			addItem(1005, "Silverfish-in-a-Jar", "Not good enough for the goldfish", new Behavior_Spawn(EntitySilverfish.class));
			addItem(1006, "Bat-in-a-Jar", "Got ham?", new Behavior_Spawn(EntityBat.class));
			
			addItem(2000, "Bunny-in-a-Jar", "Thumpernickel Bread");
			addItem(2001, "Birb-in-a-Jar", LH.Chat.CYAN + "4 New Tweets");
			addItem(2002, "Spiderling-in-a-Jar", "Cute!");
			addItem(2003, "SIMJ Squirrel-in-my-Jar", "Squirrels! Squirrels!");
			addItem(2004, "Raven-in-a-Jar", LH.Chat.UNDERLINE + LH.Chat.BOLD + "NEVERMORE");
			addItem(2005, "Baby-Birb-in-a-Jar", "I tot I taw a puddy tat!");
			addItem(2006, "Amazing Slime-In-a-Jar", "Gooey Deschanel");
			
			if (MD.TF.mLoaded)
			{
				try {
					Class bunny = Class.forName("twilightforest.entity.passive.EntityTFBunny");
					this.addItemBehavior(2000, new Behavior_Spawn(bunny));
					Class birb = Class.forName("twilightforest.entity.passive.EntityTFBird");
					this.addItemBehavior(2001, new Behavior_Spawn(birb));
					Class spiderling = Class.forName("twilightforest.entity.EntityTFSwarmSpider");
					this.addItemBehavior(2002, new Behavior_Spawn(spiderling));
					Class squirrel = Class.forName("twilightforest.entity.passive.EntityTFSquirrel");
					this.addItemBehavior(2003, new Behavior_Spawn(squirrel));
					Class raven = Class.forName("twilightforest.entity.passive.EntityTFRaven");
					this.addItemBehavior(2004, new Behavior_Spawn(raven));
					Class babybirb = Class.forName("twilightforest.entity.passive.EntityTFTinyBird");
					this.addItemBehavior(2005, new Behavior_Spawn(babybirb));
					Class mazeslime = Class.forName("twilightforest.entity.EntityTFMazeSlime");
					this.addItemBehavior(2006, new Behavior_Spawn(mazeslime));
				} catch (Throwable T) {
					System.out.println("Twilight Forest was loaded, but unable to add jars!");
					T.printStackTrace();
				}
			}
			
			QTI.spawnTurkey.set(ST.make(this, 1, 0));
			QTI.spawnFrog.set(ST.make(this, 1, 1));
			QTI.jarFrog.set(ST.make(this, 1, 1000));
			QTI.jarChicken.set(ST.make(this, 1, 1001));
			QTI.jarSlime.set(ST.make(this, 1, 1002));
			QTI.jarMagmaCube.set(ST.make(this, 1, 1003));
			QTI.jarCaveSpider.set(ST.make(this, 1, 1004));
			QTI.jarSilverfish.set(ST.make(this, 1, 1005));
			QTI.jarBat.set(ST.make(this, 1, 1006));
			
			QTI.jarTFBunny.set(ST.make(this, 1, 2000));
			QTI.jarTFBird.set(ST.make(this, 1, 2001));
			QTI.jarTFSpider.set(ST.make(this, 1, 2002));
			QTI.jarTFSquirrel.set(ST.make(this, 1, 2003));
			QTI.jarTFRaven.set(ST.make(this, 1, 2004));
			QTI.jarTFTinyBird.set(ST.make(this, 1, 2005));
			QTI.jarTFMazeSlime.set(ST.make(this, 1, 2006));
		}
		
		@Override public ItemStack getContainerItem(ItemStack aStack) {
			if (aStack.getItemDamage() >= 1000 && aStack.getItemDamage() < 3000) {
				return IL.Bottle_Empty.get(1L);
			}
			return null;
		}

			@Override
			public void addAdditionalToolTips(List aList, ItemStack aStack, boolean aF3_H) {
				super.addAdditionalToolTips(aList, aStack, aF3_H);
				try {
					if (aStack.hasTagCompound()) {
						NBTTagCompound nbt = aStack.getTagCompound();
						if (nbt.hasKey("QTgenes")) {
							NBTTagCompound genes = nbt.getCompoundTag("QTgenes");
							if (genes.hasKey("species")) {
								Species species = MobSpeciesRegistry.getSpecies(EntityPhasianidae.class, genes.getShort("species"));
								aList.add("Species: " + species.getCommonName());
								if (genes.hasKey("subtype")) {
									Subtype subtype = species.getSubtype(genes.getShort("subtype"));
									aList.add("Subtype: " + subtype.getCommonName());
								}
							}
						}
					}
				} catch (Exception e)
				{
					//nope
				}
			}

			@Override
			public void getSubItems(Item aItem, CreativeTabs aCreativeTab, List aList) {
				super.getSubItems(aItem, aCreativeTab, aList);
				Species[] species = MobSpeciesRegistry.getSpeciesList(EntityPhasianidae.class);
				for (short q = 0; q < species.length; q++)
				{
					if (species[q] != null) {
						Subtype[] subtypes = species[q].subtypes;
						for (short w = 0; w < subtypes.length; w++) {
							if (subtypes[w] != null) {
								ItemStack stacker = ST.make(this, 1, 2);
								NBTTagCompound nbt = stacker.getTagCompound();
								if (nbt == null) nbt = new NBTTagCompound();
								NBTTagCompound genetics = new NBTTagCompound();
								genetics.setShort("species", q);
								genetics.setShort("subtype", w);
								nbt.setTag("QTgenes", genetics);
								stacker.setTagCompound(nbt);
								aList.add(stacker);
							}
						}
					}
				}
			}
		};
		
		
		new MultiItemRandom(QwerTech.MODID, "qwertech.armor.upgrades") {@Override public void addItems() {
			addItem(0, "Dual Steel Springs"					, "Boingy boingy boingy");
			addItem(1, "Dual Stainless Steel Springs"		, "Boingy boingy boingy");
			addItem(2, "Dual Brass Springs"					, "Boingy boingy boingy");
			addItem(3, "Dual Aluminium Springs"				, "Boingy boingy boingy");
			addItem(4, "Dual Thaumium Springs"				, "Boingy boingy boingy");
			
			CR.shapeless(ST.make(this, 1, 0), CR.DEF, new Object[]{"springSmallSteel", "springSmallSteel"});
			CR.shapeless(ST.make(this, 1, 1), CR.DEF, new Object[]{"springSmallStainlessSteel", "springSmallStainlessSteel"});
			CR.shapeless(ST.make(this, 1, 2), CR.DEF, new Object[]{"springSmallBrass", "springSmallBrass"});
			CR.shapeless(ST.make(this, 1, 3), CR.DEF, new Object[]{"springSmallAluminium", "springSmallAluminium"});
			CR.shapeless(ST.make(this, 1, 4), CR.DEF, new Object[]{"springSmallThaumium", "springSmallThaumium"});
			
			IArmorUpgrade upgrade1 = new Upgrade_SpringBoots(MT.Steel);
			upgrade1.setMaterialAmount(OP.springSmall.mAmount * 2);
			upgrade1.addUpgradeStack(ST.make(this,1, 0));
			IArmorUpgrade upgrade2 = new Upgrade_SpringBoots(MT.StainlessSteel);
			upgrade2.setMaterialAmount(OP.springSmall.mAmount * 2);
			upgrade2.addUpgradeStack(ST.make(this, 1, 1));
			IArmorUpgrade upgrade3 = new Upgrade_SpringBoots(MT.Brass);
			upgrade3.setMaterialAmount(OP.springSmall.mAmount * 2);
			upgrade3.addUpgradeStack(ST.make(this,1, 2));
			IArmorUpgrade upgrade4 = new Upgrade_SpringBoots(MT.Al);
			upgrade4.setMaterialAmount(OP.springSmall.mAmount * 2);
			upgrade4.addUpgradeStack(ST.make(this, 1, 3));
			IArmorUpgrade upgrade5 = new Upgrade_SpringBoots(MT.Thaumium);
			upgrade5.setMaterialAmount(OP.springSmall.mAmount * 2);
			upgrade5.addUpgradeStack(ST.make(this, 1, 4));
			
			ArmorUpgradeRegistry.instance.addUpgrade(0, upgrade1);
			ArmorUpgradeRegistry.instance.addUpgrade(1, upgrade2);
			ArmorUpgradeRegistry.instance.addUpgrade(2, upgrade3);
			ArmorUpgradeRegistry.instance.addUpgrade(3, upgrade4);
			ArmorUpgradeRegistry.instance.addUpgrade(4, upgrade5);
		}
		};
		
		//0   	-3999: iron bucket
		//4000	-4999: bismuth bucket
		//5000	-5999: bismuth bronze bucket
		//6000	-6999: brass bucket
		//7000	-7999: bronze bucket
		//8000	-8999: copper bucket
		//9000	-9999: lead bucket
		//10000	-10999: tin bucket
		//11000	-11999: zinc bucket
		//12000	-15999: bottle
		QTI.buckets.set(new MultiItemRandom(QwerTech.MODID, "qwertech.fluidcontainers") {@Override public void addItems() {
			addItem(0, "Bucket of Chemical X", LH.Chat.RED + "Unstable", QTMT.ChemicalX.liquid(CS.U, true));
			addItem(1, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
			addItem(2, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(4001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(4002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(5001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(5002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(6001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(6002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(7001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(7002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(8001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(8002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

            addItem(9001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(9002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(10001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(10002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

            addItem(11001, "Bucket of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(11002, "Bucket of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));

			addItem(12000, "Bottle of Chemical X", LH.Chat.CYAN + "Volatile", QTMT.ChemicalX.liquid(CS.U, true));
			addItem(12001, "Bottle of Chemical Y", "", QTMT.ChemicalY.liquid(CS.U, true));
            addItem(12002, "Bottle of Sugarwater", "", UT.Fluids.make("sugarwater", 1000));
		}
		
		@Override public ItemStack getContainerItem(ItemStack aStack) {
			int damage = aStack.getItemDamage();
			if (damage < 4000)
			{
				return ST.make(Items.bucket, 1, 0);
			} else if (damage < 5000)
			{
				return IL.Wooden_Bucket_Bismuth.get(1);
			} else if (damage < 6000)
			{
				return IL.Wooden_Bucket_BismuthBronze.get(1);
			} else if (damage < 7000)
			{
				return IL.Wooden_Bucket_Brass.get(1);
			} else if (damage < 8000)
			{
				return IL.Wooden_Bucket_Bronze.get(1);
			} else if (damage < 9000)
			{
				return IL.Wooden_Bucket_Copper.get(1);
			} else if (damage < 10000)
			{
				return IL.Wooden_Bucket_Lead.get(1);
			} else if (damage < 11000)
			{
				return IL.Wooden_Bucket_Tin.get(1);
			} else if (damage < 12000)
			{
				return IL.Wooden_Bucket_Zinc.get(1);
			} else {
				return IL.Bottle_Empty.get(1);
			}
		}

		@Override
		public int getColorFromItemStack(ItemStack stack, int renderpass)
		{
			if (renderpass > 0) {
				return this.getFluid(stack).getFluid().getColor();
			}
			return super.getColorFromItemStack(stack, renderpass);
		}

		Object woodBucket = null;
		Object metalBucket = null;
		Object glassBottle = null;
		@Override
		public void registerIcons(IIconRegister aIconRegister) {
			woodBucket = aIconRegister.registerIcon("qwertech:qwertech.internal/woodbucket");
			metalBucket = aIconRegister.registerIcon("qwertech:qwertech.internal/metalbucket");
			glassBottle = aIconRegister.registerIcon("qwertech:qwertech.internal/glassbottle");
		}

		@Override
		public boolean requiresMultipleRenderPasses()
		{
			return true;
		}

		@Override
		public int getRenderPasses(int metadata)
		{
			return 2;
		}

		@Override
		public IIcon getIconIndex(ItemStack stack)
		{
			return getIcon(stack, 0);
		}

		@Override
		public IIcon getIcon(ItemStack stack, int renderpass)
		{
			ItemStack CI = this.getContainerItem(stack);
			Item I = CI.getItem();
			if (renderpass <= 0) {
				return I.getIcon(CI, 0);
			} else {
				if (I == Items.bucket)
				{
					return (IIcon)metalBucket;
				} else if (I == IL.Bottle_Empty.getItem())
				{
					return (IIcon)glassBottle;
				}
				return (IIcon)woodBucket;
			}
		}

		@Override
		public IIcon getIconFromDamage(int aMetaData) {
			return getIconFromDamageForRenderPass(aMetaData, 0);
		}

		@Override
		public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
			return this.getIcon(aStack, aRenderPass);
		}

		@Override
		public IIcon getIconFromDamageForRenderPass(int damage, int renderpass)
		{
			return this.getIcon(ST.make(this, 1, damage), renderpass);
		}

		});
	}
}
