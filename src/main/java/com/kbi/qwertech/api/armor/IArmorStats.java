package com.kbi.qwertech.api.armor;

import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/**
 * @author Qwertygiy
 * Various properties for different pieces of armor.
 * Not including anything influenced by material!
 */
public interface IArmorStats {
	
	/**
	 * What to render if the armor is on the player's head.
	 * @param stack The armor stack.
	 * @param player The player wearing it.
	 * @param resolution The screen resolution.
	 * @param partialTicks Partial ticks between world tick.
	 * @param hasScreen If a GUI is displayed.
	 * @param mouseX Where the mouse is horizontally.
	 * @param mouseY Where the mouse is vertically.
	 * @return True if drawn.
	 */
	public boolean renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY);
	
	/**
	 * The quality adjustment to add to the primary material's quality.
	 * @return 0 by default
	 */
	public int getBaseQuality();
	
	/**
	 * The sound event to be played when the armor breaks
	 * @return the string sound event
	 */
	public String getBreakingSound();
	
	/**
	 * The ItemStack to be created when the armor is broken.
	 * @param aStack The armor stack
	 * @return The broken item
	 */
	public ItemStack getBrokenItem(ItemStack aStack);
	
	/**
	 * The multiplier to apply to damage recieved.
	 */
	public float getDamageProtection();

	/**
	 * The enchantment levels to be granted to this type of armor (NOT INCLUDING REGULAR MATERIAL BONUSES)
	 * @param aStack The armor stack
	 * @return An int[] array of levels
	 */
	public int[] getEnchantmentLevels(ItemStack aStack);
	
	/**
	 * The enchantments to be granted to this type of armor (NOT INCLUDING REGULAR MATERIAL BONUSES)
	 * @param aStack The armor stack
	 * @param aMaterial The primary armor material
	 * @return An Enchantment[] array of enchantments
	 */
	public Enchantment[] getEnchantments(ItemStack aStack, OreDictMaterial aMaterial);
	
	/**
	 * Returns the icon for the ITEMSTACK.
	 * @param aStack The ItemStack to be rendered.
	 * @param aRenderPass Which render pass it is on.
	 * @return the IIcon to use.
	 */
	public IIcon getIcon(ItemStack aStack, int aRenderPass);
	
	/**
	 * The sound event to be played when the armor is hit
	 * @return the string sound event
	 */
	public String getImpactSound();
	
	/**
	 * How much primary material this armor has
	 * @return The amount, based on CS.U
	 */
	public long getMaterialAmount();

	/**
	 * The modifier of this armor type on the material's durability.
	 * @return The multiplier. 100 is standard.
	 */
	public long getMaxDurabilityMultiplier();

	/**
	 * Render passes for the ITEMSTACK.
	 * @return the number of passes.
	 */
	public int getRenderPasses();
	
	/**
	 * Returns the color for the ITEMSTACK.
	 * @param aStack The ItemStack to be rendered.
	 * @param aRenderPass Which render pass it is on.
	 * @return the short[red, green, blue, trans] to use.
	 */
	public short[] getRGBa(ItemStack aStack, int aRenderPass);
	
	/**
	 * Checks to see if the armor can be put into the specified armor slot.
	 * @param armorType 0 = Helmet, 1 = Chest, 2 = Legs, 3 = Boots.
	 * @return True if equippable.
	 */
	public boolean isValidInSlot(int armorType);
	/**
	 * Called when the armor takes damage from a non-hostile mob, such as a wolf or turkey.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onAnimalDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);
	
	/**
	 * Called when the armor takes damage from any source at all.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onAnyDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);
	
	/**
	 * Adds behaviors and anything else that should happen when the armor is made.
	 * @param aStack The armor stack.
	 * @param aPlayer The player crafting the armor (POTENTIALLY NULL).
	 */
	public void onArmorCrafted(ItemStack aStack, EntityPlayer aPlayer);

	/**
     * Called to tick armor in the armor slot. Override to do something
     *
     * @param world
     * @param player
     * @param itemStack
     */
	public void onArmorTick(World world, EntityLivingBase player, ItemStack stack);

	/**
	 * Called when the armor takes damage from drowning.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onDrowningDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage marked as exploding.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onExplosionDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage from falling.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onFallingDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage marked as fire.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onFireDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage from a hostile mob.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onHostileDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage from starving.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onHungerDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage in lava.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onLavaDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage marked as magic.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onMagicDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage from a player.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onPlayerDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when the armor takes damage marked as projectile.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onProjectileDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	/**
	 * Called when a new armor piece is registered
	 * @param armor The armor Item being added to.
	 * @param ID The registered metadata value, between 0 and 32766.
	 */
	public void onStatsAddedToArmor(MultiItemArmor armor, int ID);

	/**
	 * Called when the armor takes damage from withering.
	 * @param world The world in which the event takes place.
	 * @param entity The entity wearing the armor.
	 * @param stack The armor stack.
	 * @param source The DamageSource causing the damage.
	 * @param amount The amount of damage dealt.
	 * @param event The LivingHurtEvent. If source or amount are changed, change event.source or event.ammount!
	 */
	public boolean onWitherDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);
	
	/**
	 * Sets the material amount (relies on CS.U)
	 * @param aMaterialAmount The amount of primary material this armor has.
	 * @return This object, for ease of construction.
	 */
	public IArmorStats setMaterialAmount(long aMaterialAmount);

	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type);

	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity,
			int slot);

	public void onHeatDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	public void onFrostDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	public void onChemDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	public void onBumbleDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);

	public void onSpikeDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event);
	
	/**
	 * How many upgrades this type of armor has beyond the usual 3 + material quality.
	 */
	public int baseUpgrades();
	
	/**
	 * If anything should happen if the player clicks while wearing this armor.
	 * @param armorStack The ItemStack being worn.
	 * @param slot Which slot on the player it is in.
	 * @param world The world in which it takes place.
	 * @param player The player wearing the armor.
	 * @param action Whether it's left clicking a block, right clicking a block, or right clicking air.
	 * @param x The x location of the click.
	 * @param y The y location of the click.
	 * @param z The z location of the click.
	 * @param face Which face was clicked.
	 * @param event The event itself. If any parameters are modified, SET THEM IN THE EVENT.
	 * @return
	 */
	public boolean onClickedWearing(ItemStack armorStack, int slot, World world, EntityPlayer player, PlayerInteractEvent.Action action, int x, int y, int z, int face, PlayerInteractEvent event);
}
