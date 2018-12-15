package com.kbi.qwertech.client;

import gregapi.item.multiitem.MultiItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/**
 * @author Team COFH + Qwertygiy
 **/
public class RenderShortcuts {

    public static final ResourceLocation BLOCK = new ResourceLocation("textures/atlas/blocks.png");
    public static final ResourceLocation ITEM = new ResourceLocation("textures/atlas/items.png");

    public static void preRenderIconWorld(IIcon icon, double z) {

        Tessellator.instance.addVertexWithUV(0, 1, z, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1, 1, z, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1, 0, z, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, z, icon.getMinU(), icon.getMinV());
    }

    public static void preRenderIconInv(IIcon icon, double z) {

        Tessellator.instance.addVertexWithUV(0, 16, z, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 16, z, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 0, z, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, z, icon.getMinU(), icon.getMinV());
    }

    public static void renderMask(IIcon maskIcon, IIcon subIcon, ItemRenderType type) {
        if (maskIcon == null || subIcon == null) {
            return;
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDisable(GL11.GL_CULL_FACE);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        if (type.equals(ItemRenderType.INVENTORY)) {
            preRenderIconInv(maskIcon, 10);
        } else {
            preRenderIconWorld(maskIcon, 0.001);
        }
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        if (type.equals(ItemRenderType.INVENTORY)) {
            preRenderIconInv(maskIcon, -0.0635);
        } else {
            preRenderIconWorld(maskIcon, -0.0635);
        }
        tessellator.draw();

        Minecraft.getMinecraft().renderEngine.bindTexture(BLOCK);

        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDepthMask(false);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        if (type.equals(ItemRenderType.INVENTORY)) {
            preRenderIconInv(subIcon, 10);
        } else {
            preRenderIconWorld(subIcon, 0.001);
        }
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        if (type.equals(ItemRenderType.INVENTORY)) {
            preRenderIconInv(subIcon, -0.0635);
        } else {
            preRenderIconWorld(subIcon, -0.0635);
        }
        tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static void preItemRender() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static void postItemRender() {
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
    }

    public static void renderPlainItem(EntityLivingBase entity, ItemStack p_78443_2_, int p_78443_3_, ItemRenderType type)
    {
        GL11.glPushMatrix();

        IIcon iicon = p_78443_2_.getItem().getIcon(p_78443_2_, p_78443_3_);
        if (entity != null) {
            iicon = entity.getItemIcon(p_78443_2_, p_78443_3_);
        }

        if (iicon == null)
        {
            GL11.glPopMatrix();
            return;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(p_78443_2_.getItemSpriteNumber()));
        TextureUtil.func_152777_a(false, false, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        float f = iicon.getMinU();
        float f1 = iicon.getMaxU();
        float f2 = iicon.getMinV();
        float f3 = iicon.getMaxV();
        float f4 = 0.0F;
        float f5 = 0.3F;
        //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f6 = 1.5F;
        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

        if (p_78443_2_.hasEffect(p_78443_3_))
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(768, 1, 1, 0);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }

        //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(p_78443_2_.getItemSpriteNumber()));
        TextureUtil.func_147945_b();
        GL11.glPopMatrix();
    }

    public static void renderItemFluid(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        preItemRender();
        if (type.equals(ItemRenderType.ENTITY)) {
            GL11.glRotated(180, 0, 0, 1);
            GL11.glRotated(90, 0, 1, 0);
            GL11.glScaled(0.75, 0.75, 0.75);
            GL11.glTranslated(-0.5, -0.6, 0);
        } else if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            GL11.glTranslated(1, 1, 0);
            GL11.glRotated(180, 0, 0, 1);
        } else if (type.equals(ItemRenderType.EQUIPPED)) {
            GL11.glRotated(180, 0, 0, 1);
            GL11.glTranslated(-1, -1, 0);
        }

        FluidStack fluid = ((MultiItem)item.getItem()).getFluid(item);

        if (fluid != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(ITEM);
                //System.out.println("l");
            int color = fluid.getFluid().getColor();
            GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
            renderMask(item.getItem().getIcon(item, 1), fluid.getFluid().getIcon(), type);
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(ITEM);

        /*
        if (!type.equals(ItemRenderType.INVENTORY)) {
            if (item.getItemDamage() == 1) {
                RenderHelper.renderItemIn2D(florbs[1]);
            } else {
                RenderHelper.renderItemIn2D(florbs[0]);
            }
        } else {
            if (item.getItemDamage() == 1) {
                RenderHelper.renderIcon(florbs[1], 4);
            } else {
                RenderHelper.renderIcon(florbs[0], 4);
            }
        }*/

        //for (int i = 0; i < item.getItem().getRenderPasses(item.getItemDamage()); i++)
        //{
        //}
        postItemRender();
        GL11.glPopMatrix();
        int j = item.getItem().getColorFromItemStack(item, 0);
        float f5 = (j >> 16 & 255) / 255.0F;
        float f2 = (j >> 8 & 255) / 255.0F;
        float f3 = (j & 255) / 255.0F;
        GL11.glColor4f(f5, f2, f3, 1.0F);
        if (type != ItemRenderType.INVENTORY) {
            renderPlainItem((EntityLivingBase) data[1], item, 0, type);
        } else {
            RenderItem.getInstance().renderIcon(0, 0, item.getItem().getIcon(item, 0), 16, 16);
        }
    }
}
