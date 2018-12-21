package com.kbi.qwertech;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.loaders.RegisterArmor;
import com.kbi.qwertech.loaders.RegisterMobs;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.data.CS;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.minecart.MinecartEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.*;


/**
 * @author Max Mustermann
 * 
 * An example implementation for a Common Proxy using my System.
 */
public class CommonProxy extends Abstract_Proxy {
	// Insert your common implementation of Stuff here
	public static int wallRenderID;
	
	@Override
	public void onProxyBeforePreInit		(Abstract_Mod aMod, FMLPreInitializationEvent	aEvent) {/**/}
	@Override
	public void onProxyBeforeInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent) {/**/}
	@Override
	public void onProxyBeforePostInit		(Abstract_Mod aMod, FMLPostInitializationEvent	aEvent) {/**/}
	@Override
    public void onProxyBeforeServerStarting	(Abstract_Mod aMod, FMLServerStartingEvent		aEvent) {/**/}
    @Override
    public void onProxyBeforeServerStarted	(Abstract_Mod aMod, FMLServerStartedEvent		aEvent) {/**/}
    @Override
    public void onProxyBeforeServerStopping	(Abstract_Mod aMod, FMLServerStoppingEvent		aEvent) {/**/}
    @Override
    public void onProxyBeforeServerStopped	(Abstract_Mod aMod, FMLServerStoppedEvent		aEvent) {/**/}
    
    @Override
	public void onProxyAfterPreInit			(Abstract_Mod aMod, FMLPreInitializationEvent	aEvent) {/**/}
    @Override
	public void onProxyAfterInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent) {/**/}
	@Override
	public void onProxyAfterPostInit		(Abstract_Mod aMod, FMLPostInitializationEvent	aEvent) {
		MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
		//this allows it to read game events for other classes without having to be added unnecessarily to all the game building events.
	}
    @Override
	public void onProxyAfterServerStarting	(Abstract_Mod aMod, FMLServerStartingEvent		aEvent) {/**/}
    @Override
    public void onProxyAfterServerStarted	(Abstract_Mod aMod, FMLServerStartedEvent		aEvent) {/**/}
    @Override
    public void onProxyAfterServerStopping	(Abstract_Mod aMod, FMLServerStoppingEvent		aEvent) {/**/}
    @Override
    public void onProxyAfterServerStopped	(Abstract_Mod aMod, FMLServerStoppedEvent		aEvent) {/**/}
    
    //@SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onCommandGiven(CommandEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChatted(ServerChatEvent event)
    {
    	
    }
    
  //@SubscribeEvent
    public void onBrewing(PotionBrewEvent.Pre event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBrewed(PotionBrewEvent.Post event)
    {
    	
    }
    
    //@SubscribeEvent
    public void canEntityUpdate(EntityEvent.CanUpdate event)
    {
    	
    }
    
    //@SubscribeEvent
    public void entityEnteringChunk(EntityEvent.EnteringChunk event)
    {
    	
    }
    
    //@SubscribeEvent
    public void entityConstructing(EntityEvent.EntityConstructing event)
    {
    	
    }
    
    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event)
    {
    	RegisterMobs.instance.onAdded(event);
    }
    
    //@SubscribeEvent
    public void entityStruckByLightning(EntityStruckByLightningEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void entityPlayingSound(PlaySoundAtEntityEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void itemEvent(ItemEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void itemExpiring(ItemExpireEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void itemTossed(ItemTossEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void enderTeleport(EnderTeleportEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void livingAttacked(LivingAttackEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void livingDied(LivingDeathEvent event)
    {
    	QwerTech.achievementHandler.onEntityDeath(event);
    }
    
    @SubscribeEvent
    public void livingDrops(LivingDropsEvent event)
    {
    	RegisterMobs.instance.onDrop(event);
    }
    
    //@SubscribeEvent
    public void livingJumps(LivingEvent.LivingJumpEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void livingUpdated(LivingEvent.LivingUpdateEvent event)
    {
    	RegisterArmor.instance.updateEntityArmor(event);
    }
    
    //@SubscribeEvent
    public void livingFall(LivingFallEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void livingHeal(LivingHealEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event)
    {
    	RegisterMobs.instance.onLivingHurt(event);
    }
    
    //@SubscribeEvent
    public void livingPack(LivingPackSizeEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void livingAttackTarget(LivingSetAttackTargetEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void livingSpawn(LivingSpawnEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void canDespawn(LivingSpawnEvent.AllowDespawn event)
    {
    	
    }
    
    @SubscribeEvent
    public void checkSpawn(LivingSpawnEvent.CheckSpawn event)
    {
    	RegisterMobs.instance.checkSpawn(event);
    }
    
    @SubscribeEvent
    public void specialSpawn(LivingSpawnEvent.SpecialSpawn event)
    {
    	RegisterMobs.instance.specialSpawn(event);
    }
    
    //@SubscribeEvent
    public void zombieConvert(ZombieEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void zombieSummons(ZombieEvent.SummonAidEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void minecartCollision(MinecartCollisionEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void minecartDoesSomething(MinecartEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void minecartInteracted(MinecartEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void minecartUpdated(MinecartUpdateEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void onAchievement(AchievementEvent event)
    {
    	QwerTech.achievementHandler.onAchieved(event);
    }
    
    //@SubscribeEvent
    public void onAnvilRepair(AnvilRepairEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onArrowLaunched(ArrowLooseEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onArrowChosen(ArrowNockEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void playerAttacks(AttackEntityEvent event)
    {
    	QwerTech.achievementHandler.onEntityHurt(event);
    }
    
    //@SubscribeEvent
    public void onBonemeal(BonemealEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void onPlayerEntityInteraction(EntityInteractEvent event)
    {
    	RegisterMobs.instance.onInteracted(event);
    }
    
    @SubscribeEvent
    public void onPickupItem(EntityItemPickupEvent event)
    {
    	QwerTech.achievementHandler.onItemPickup(event);
    }
    
    //@SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void onPlayerDestroyItem(PlayerDestroyItemEvent event)
    {
    	QwerTech.achievementHandler.onBroken(event);
    }
    
    //@SubscribeEvent
    public void onPlayerDrop(PlayerDropsEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void getBreakSpeed(PlayerEvent.BreakSpeed event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
    	
    }
    
    //@SubscribeEvent
    public void getHarvestable(PlayerEvent.HarvestCheck event)
    {
    	
    }
    
    //@SubscribeEvent
    public void loadPlayerData(PlayerEvent.LoadFromFile event)
    {
    	
    }
    
    //@SubscribeEvent
    public void loadNameFormat(PlayerEvent.NameFormat event)
    {
    	
    }
    
    //@SubscribeEvent
    public void savePlayerData(PlayerEvent.SaveToFile event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerTracksEntity(PlayerEvent.StartTracking event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerLosesEntity(PlayerEvent.StopTracking event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onFlyingFall(PlayerFlyableFallEvent event)
    {

    }
    
    @SubscribeEvent
    public void onPlayerWorldInteraction(PlayerInteractEvent event)
    {
    	QwerTech.achievementHandler.onRightClick(event);
    	RegisterArmor.instance.onClickedWearingArmor(event);
    }
    
    //@SubscribeEvent
    public void onPlayerOpensContainer(PlayerOpenContainerEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onXPEarned(PlayerPickupXpEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerSleeping(PlayerSleepInBedEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerItemUseStart(PlayerUseItemEvent.Start event)
    {
    	
    }
    
    @SubscribeEvent
    public void onPlayerItemUseFinish(PlayerUseItemEvent.Finish event)
    {
    	if (event.item != null && event.item.getItem() == CS.ToolsGT.sMetaTool && event.item.getItemDamage() == CS.ToolsGT.WRENCH)
        {
          if (event.entityPlayer != null)
          {
            int previousSlot = event.entityPlayer.inventory.currentItem - 1;
            if (previousSlot > -1) {
              ItemStack previous = event.entityPlayer.inventory.getStackInSlot(previousSlot);
              if (previous != null)
              {
                if (previous.getItem() instanceof MultiItemTool)
                {
                  if (MultiItemTool.getToolDamage(previous) == 0)
                  {
                    OreDictMaterial mat = MultiItemTool.getPrimaryMaterial(previous);
                    ItemStack stack = previous;
                    if (previous.getItem() == CS.ToolsGT.sMetaTool)
                    {
                      switch(previous.getItemDamage())
                      {
                        case CS.ToolsGT.SWORD:
                          stack = OP.toolHeadSword.mat(mat, 1);
                          break;
                        case CS.ToolsGT.CHISEL:
                          stack = OP.toolHeadChisel.mat(mat, 1);
                          break;
                        case CS.ToolsGT.FILE:
                          stack = OP.toolHeadFile.mat(mat, 1);
                          break;
                        case CS.ToolsGT.HARDHAMMER:
                        case CS.ToolsGT.SOFTHAMMER:
                          stack = OP.toolHeadHammer.mat(mat, 1);
                          break;
                        case CS.ToolsGT.PICKAXE:
                          stack = OP.toolHeadPickaxe.mat(mat, 1);
                          break;
                        case CS.ToolsGT.GEM_PICK:
                          stack = OP.toolHeadPickaxeGem.mat(mat, 1);
                          break;
                        case CS.ToolsGT.CONSTRUCTION_PICK:
                          stack = OP.toolHeadConstructionPickaxe.mat(mat, 1);
                          break;
                        case CS.ToolsGT.AXE:
                          stack = OP.toolHeadAxe.mat(mat, 1);
                          break;
                        case CS.ToolsGT.DOUBLE_AXE:
                          stack = OP.toolHeadAxeDouble.mat(mat, 1);
                          break;
                        case CS.ToolsGT.BUTCHERYKNIFE:
                          stack = OP.plate.mat(mat, 4);
                          break;
                        case CS.ToolsGT.HOE:
                          stack = OP.toolHeadHoe.mat(mat, 1);
                          break;
                        case CS.ToolsGT.PLOW:
                          stack = OP.toolHeadPlow.mat(mat, 1);
                          break;
                        case CS.ToolsGT.SAW:
                          stack = OP.toolHeadSaw.mat(mat, 1);
                          break;
                        case CS.ToolsGT.SCREWDRIVER:
                          stack = OP.toolHeadScrewdriver.mat(mat, 1);
                          break;
                        case CS.ToolsGT.SENSE:
                          stack = OP.toolHeadSense.mat(mat, 1);
                          break;
                        case CS.ToolsGT.SHOVEL:
                          stack = OP.toolHeadShovel.mat(mat, 1);
                          break;
                        case CS.ToolsGT.SPADE:
                          stack = OP.toolHeadSpade.mat(mat, 1);
                          break;
                        case CS.ToolsGT.UNIVERSALSPADE:
                          stack = OP.toolHeadUniversalSpade.mat(mat, 1);
                          break;
                        default:
                          return;
                      }
                    } else if (previous.getItem() == QTI.qwerTool.getItem())
                    {
                      switch(previous.getItemDamage())
                      {
                        case 0:
                          stack = OreDictPrefix.get("toolHeadMattock").mat(mat, 1);
                          break;
                        case 6:
                          stack = OreDictPrefix.get("toolHeadMace").mat(mat, 1);
                          break;
                        default:
                          return;
                      }
                    }
                    if (stack != previous) {
                      CS.ToolsGT.sMetaTool.doDamage(event.item, 20);
                      UT.Sounds.send(event.entityPlayer.worldObj, "qwertech:metal.slide", 0.5F, (CS.RNGSUS.nextInt(5) + 8)/10F, (int)event.entityPlayer.posX, (int)event.entityPlayer.posY, (int)event.entityPlayer.posZ);
                      event.entityPlayer.inventory.setInventorySlotContents(previousSlot, stack);
                    }
                  }
                }
              }
            }
          }
        }
    }
    
    @SubscribeEvent
    public void onPlayerItemUseStop(PlayerUseItemEvent.Stop event)
    {
    	QwerTech.achievementHandler.onItemFinish(event);
    }
    
    //@SubscribeEvent
    public void onPlayerItemUsing(PlayerUseItemEvent.Tick event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerWakeUp(PlayerWakeUpEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerHoes(UseHoeEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeColor(BiomeEvent.BiomeColor event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeDecorator(BiomeEvent.CreateDecorator event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeFoliageColor(BiomeEvent.GetFoliageColor event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeGrassColor(BiomeEvent.GetGrassColor event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeVillageID(BiomeEvent.GetVillageBlockID event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeVillageMeta(BiomeEvent.GetVillageBlockMeta event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeWaterColor(BiomeEvent.GetWaterColor event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onNoiseField(ChunkProviderEvent.InitNoiseField event)
    {
    	
    }
    
    //@SubscribeEvent
    public void replaceBiomeBlocks(ChunkProviderEvent.ReplaceBiomeBlocks event)
    {
    	
    }
    
    //@SubscribeEvent
    public void decorateBiome(DecorateBiomeEvent.Decorate event)
    {
    	
    }
    
    //@SubscribeEvent
    public void decorateBiomeBefore(DecorateBiomeEvent.Pre event)
    {
    	
    }
    
    //@SubscribeEvent
    public void decorateBiomeAfter(DecorateBiomeEvent.Post event)
    {
    	
    }
    
    //@SubscribeEvent
    public void deferredBiomeDecorator(DeferredBiomeDecorator event)
    {
    	
    }
    
    //@SubscribeEvent
    public void initMapGen(InitMapGenEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void initNoiseGen(InitNoiseGensEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onGenerateOre(OreGenEvent.GenerateMinable event)
    {
    	
    }
    //@SubscribeEvent
    public void onGenerateOreBefore(OreGenEvent.Pre event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onGenerateOreAfter(OreGenEvent.Post event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPopulateChunk(PopulateChunkEvent.Populate event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPopulateChunkBefore(PopulateChunkEvent.Pre event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPopulateChunkAfter(PopulateChunkEvent.Post event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onSaplingGrow(SaplingGrowTreeEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onWorldType(WorldTypeEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomeSize(WorldTypeEvent.BiomeSize event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBiomesChosen(WorldTypeEvent.InitBiomeGens event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void onBlockDrop(BlockEvent.HarvestDropsEvent event)
    {
    	QwerTech.achievementHandler.onBlockBreak(event);
    }
    
    //@SubscribeEvent
    public void onBlockMultiPlace(BlockEvent.MultiPlaceEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkDataLoaded(ChunkDataEvent.Load event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkDataSaved(ChunkDataEvent.Save event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkLoaded(ChunkEvent.Load event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkUnloaded(ChunkEvent.Unload event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkWatched(ChunkWatchEvent.Watch event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onChunkUnwatched(ChunkWatchEvent.UnWatch event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onExplosionStart(ExplosionEvent.Start event)
    {
    	
    }
    
    @SubscribeEvent
    public void onExplosionDetonate(ExplosionEvent.Detonate event)
    {
    	QwerTech.achievementHandler.onExploded(event);
    }
    
    //@SubscribeEvent
    public void createSpawn(WorldEvent.CreateSpawnPosition event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPickSpawns(WorldEvent.PotentialSpawns event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onWorldSave(WorldEvent.Save event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event)
    {
    	
    }
    
    @SubscribeEvent
    public void onItemCrafted(cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event)
    {
    	QwerTech.achievementHandler.onCrafted(event);
    }
    
    //@SubscribeEvent
    public void onItemPickup(cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void onItemSmelted(cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent event)
    {
    	QwerTech.achievementHandler.onSmelted(event);
    }
    
    //@SubscribeEvent
    public void onPlayerDimension(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerLoggedIn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerLoggedOut(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerRespawn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onPlayerTick(cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onServerTick(cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent event)
    {
    	
    }
    
    //@SubscribeEvent
    public void onWorldTick(cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent event)
    {
    	
    }
}