package com.kbi.qwertech.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.example.MultiTileEntityChest;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerClientChest;
import gregapi.gui.ContainerCommonChest;
import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import static gregapi.data.CS.F;
import static gregapi.data.CS.RES_PATH_GUI;

public class ChestTileEntity extends MultiTileEntityChest {
    @Override
    public String getTileEntityName() {
        return "qt." + super.getTileEntityName();
    }

    @Override public Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientChest2(aPlayer.inventory, this);}

    @SideOnly(Side.CLIENT)
    public class ContainerClientChest2 extends ContainerClient {
        private int mRows;

        public ContainerClientChest2(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
            this(new ContainerCommonChest(aInventoryPlayer, aTileEntity));
        }

        public ContainerClientChest2(ContainerCommonChest aContainer) {
            super(aContainer, RES_PATH_GUI + "chests/" + aContainer.mSlotCount + ".png");
            allowUserInput = F;
            mRows = aContainer.mSlotCount / 9 + (aContainer.mSlotCount % 9 == 0 ? 0 : 1);
            ySize = 112 + mRows * 18;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
            fontRendererObj.drawString(mContainer.mTileEntity.getInventoryNameGUI(), 8, 6, 4210752);
            fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer2(float par1, int par2, int par3) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            int k = (width - xSize) / 2;
            int l = (height - ySize) / 2;
            drawTexturedModalRect(k, l + 1, 0, 0, xSize, mRows * 18 + 17);
            drawTexturedModalRect(k, l + mRows * 18 + 17, 0, ySize - 96, xSize, 96);
        }
    }

}
