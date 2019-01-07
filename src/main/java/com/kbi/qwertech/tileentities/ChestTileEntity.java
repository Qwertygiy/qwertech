package com.kbi.qwertech.tileentities;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.block.multitileentity.example.MultiTileEntityChest;
import gregapi.data.MD;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerClientChest;
import gregapi.gui.ContainerCommonChest;
import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.UT;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import static gregapi.data.CS.*;
import static gregapi.data.CS.COMPASS_FROM_SIDE;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL12.GL_RESCALE_NORMAL;

public class ChestTileEntity extends MultiTileEntityChest {
    @Override
    public String getTileEntityName() {
        return "qt." + super.getTileEntityName();
    }

    @Override public Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientChest2(aPlayer.inventory, this);}

    @SideOnly(Side.CLIENT)
    private static MultiTileEntityChest.MultiTileEntityRendererChest RENDERER;

    @Override
    @SideOnly(Side.CLIENT)
    public void onRegistrationFirstClient(MultiTileEntityRegistry aRegistry, short aID) {
        ClientRegistry.bindTileEntitySpecialRenderer(getClass(), RENDERER = new MultiTileEntityChest.MultiTileEntityRendererChest());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onRegistrationClient(MultiTileEntityRegistry aRegistry, short aID) {
        RENDERER.mResources.put(mTextureName, new ResourceLocation[] {new ResourceLocation(MD.GT.mID, TEX_DIR_MODEL + aRegistry.mNameInternal + "/" + mTextureName + ".colored.png"), new ResourceLocation(MD.GT.mID, TEX_DIR_MODEL + aRegistry.mNameInternal + "/" + mTextureName + ".plain.png")});
    }

    @SideOnly(Side.CLIENT)
    public static class MultiTileEntityRendererQTChest extends MultiTileEntityRendererChest {
        protected static final MultiTileEntityModelChest sModel = new MultiTileEntityModelChest();
        @Override
        public void renderTileEntityAt(TileEntity bTileEntity, double aX, double aY, double aZ, float aPartialTick) {
            if (bTileEntity instanceof ChestTileEntity) {
                ChestTileEntity aTileEntity = (ChestTileEntity)bTileEntity;
                double tLidAngle = 1 - (aTileEntity.oLidAngle + (aTileEntity.mLidAngle - aTileEntity.oLidAngle) * aPartialTick); tLidAngle = -(((1 - tLidAngle*tLidAngle*tLidAngle) * Math.PI) / 2);
                ResourceLocation[] tLocation = mResources.get(aTileEntity.mTextureName);
                bindTexture(tLocation[0]);
                glColor4f(1, 1, 1, 1);
                glPushMatrix();
                short[] tRGBa = UT.Code.getRGBaArray(aTileEntity.mRGBa);
                glColor4f(tRGBa[0] / 255.0F, tRGBa[1] / 255.0F, tRGBa[2] / 255.0F, 1);
                glTranslated(aX, aY + 1, aZ + 1);
                glScalef(1, -1, -1);
                glTranslated(0.5, 0.5, 0.5);
                glRotatef(COMPASS_FROM_SIDE[aTileEntity.mFacing] * 90 - 180, 0, 1, 0);
                glTranslated(-0.5, -0.5, -0.5);
                sModel.render(tLidAngle);
                glPopMatrix();
                glColor4f(1, 1, 1, 1);

                bindTexture(tLocation[1]);
                glPushMatrix();
                glTranslated(aX, aY + 1, aZ + 1);
                glScalef(1, -1, -1);
                glTranslated(0.5, 0.5, 0.5);
                glRotatef(COMPASS_FROM_SIDE[aTileEntity.mFacing] * 90 - 180, 0, 1, 0);
                glTranslated(-0.5, -0.5, -0.5);
                sModel.render(tLidAngle);
                glPopMatrix();
            }
        }
    }

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
