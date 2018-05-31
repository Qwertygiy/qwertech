package com.kbi.qwertech.client.tileentity;

import com.kbi.qwertech.tileentities.CuttingBoardTileEntity;
import gregapi.data.CS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class CountertopRenderer extends CraftingTableRenderer {

    public void renderTileEntityAt(CuttingBoardTileEntity tileEntity, double x, double y, double z, float partialTick) {
        if (mc == null) mc = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glPushMatrix();
        //GL11.glDisable(GL11.GL_LIGHTING);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short dir = tileEntity.getFacing();
        if (dir == CS.SIDE_Y_NEG || dir == CS.SIDE_X_NEG || dir == CS.SIDE_X_POS)
        {
            GL11.glRotatef(90, 0, 1, 0);
        }

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        double dist = (Math.abs(x) + Math.abs(y) + Math.abs(z));
        float timeLoop = (tileEntity.getWorld().getWorldTime() % 3600) + partialTick;
        float timeSine = (tileEntity.getWorld().getWorldTime() % 180) + partialTick;
        timeSine = timeSine > 90 ? 180 - timeSine : timeSine;

        int[] slotsX = new int[]{0, 0, 0, 1, 1, 2, 2, 2};
        int[] slotsY = new int[]{0, 1, 2, 0, 2, 0, 1, 2};

        if (dist < 16)
        {
            for (int i = 0; i < 8; i++)
            {
                ItemStack display = tileEntity.slot(i);
                if (display != null)
                {
                    if (tileEntity.mFacing == CS.SIDE_Y_NEG || tileEntity.mFacing == CS.SIDE_X_NEG || tileEntity.mFacing == CS.SIDE_Z_NEG)
                    {
                        renderItem(display.copy(), timeLoop/10, 0.5 - (0.33 * (slotsX[i] - 1)), 1.1 + (timeSine/8000), 0.5 - (0.33 * (slotsY[i] - 1)), tileEntity.getWorld(), 1, 1F);
                    } else {
                        renderItem(display.copy(), timeLoop/10, 0.5 + (0.33 * (slotsX[i] - 1)), 1.1 + (timeSine/8000), 0.5 - (0.33 * (slotsY[i] - 1)), tileEntity.getWorld(), 1, 1F);
                    }
                }
            }
        } else if (dist < 32)
        {
            for (int i = 0; i < 8; i++)
            {
                ItemStack display = tileEntity.slot(i);
                if (display != null)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glDepthFunc(GL11.GL_LESS);
                    OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
                    renderItem(display.copy(), timeLoop/10, 0.5 + (0.33 * (slotsX[i] - 1)), 1.1 + (timeSine/8000), 0.5 - (0.33 * (slotsY[i] - 1)), tileEntity.getWorld(), 1, (float)(1F - ((dist - 16F)/16F)));
                    GL11.glDepthFunc(GL11.GL_LEQUAL);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
                    GL11.glDisable(GL11.GL_BLEND);
                }
            }
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
        renderTileEntityAt((CuttingBoardTileEntity)tileEntity, x, y, z, partialTick);
    }
}
