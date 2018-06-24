package com.kbi.qwertech.blocks;

import gregapi.block.BlockBaseMeta;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockSoil extends BlockBaseMeta {

    IIconContainer[][] icons = new IIconContainer[][]{{new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil0"), new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil1"), new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil2"), new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil3"), new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil4"), new Textures.BlockIcons.CustomIcon("qwertech:soil/potsoil5")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip0"), new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip1"), new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip2"), new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip3"), new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip4"), new Textures.BlockIcons.CustomIcon("qwertech:soil/woodchip5")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch0"), new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch1"), new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch2"), new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch3"), new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch4"), new Textures.BlockIcons.CustomIcon("qwertech:soil/mulch5")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost0"), new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost1"), new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost2"), new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost3"), new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost4"), new Textures.BlockIcons.CustomIcon("qwertech:soil/freshcompost5")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/compost0"), new Textures.BlockIcons.CustomIcon("qwertech:soil/compost1"), new Textures.BlockIcons.CustomIcon("qwertech:soil/compost2"), new Textures.BlockIcons.CustomIcon("qwertech:soil/compost3"), new Textures.BlockIcons.CustomIcon("qwertech:soil/compost4"), new Textures.BlockIcons.CustomIcon("qwertech:soil/compost5")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/pinestraw")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/drystraw")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/rottenstraw")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/freshleaves")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/dryleaves")},
            {new Textures.BlockIcons.CustomIcon("qwertech:soil/rottenleaves")}};

    public BlockSoil(Class<? extends ItemBlock> aItemClass,
                           String aNameInternal, Material aMaterial, SoundType aSoundType,
                           long aMaxMeta, IIconContainer[] aIcons) {
        super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
        icons.clone();
    }

    @Override
    public IIcon getIcon(int aSide, int aMeta) {
        IIconContainer[] iconset = icons[aMeta];
        if (iconset.length > 1)
        {
            return iconset[aSide % iconset.length].getIcon(0);
        } else {
            return iconset[0].getIcon(0);
        }
    }

    @Override
    public IIcon getIcon(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
        int MD = aWorld.getBlockMetadata(aX, aY, aZ);
        IIconContainer[] iconset = icons[MD];
        if (iconset.length > 1)
        {
            return iconset[Math.abs(aX + aY + aZ + aSide) % iconset.length].getIcon(0);
        } else {
            return iconset[0].getIcon(0);
        }
    }

    @Override
    public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
        switch (aWorld.getBlockMetadata(aX, aY, aZ))
        {
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
        }
        return false;
    }

    @Override
    public String getHarvestTool(int aMeta) {
        return "shovel";
    }

    @Override
    public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
        switch (aWorld.getBlockMetadata(aX, aY, aZ))
        {
            case 5:
            case 8:
                boolean isWet = WD.liquid(aWorld, aX - 1, aY, aZ) || WD.liquid(aWorld, aX, aY - 1, aZ) || WD.liquid(aWorld, aX, aY, aZ - 1) || WD.liquid(aWorld, aX + 1, aY, aZ) || WD.liquid(aWorld, aX, aY + 1, aZ) || WD.liquid(aWorld, aX, aY, aZ + 1);
                if (isWet)
                {
                    //System.out.println("We're wet!");
                    WD.set(aWorld, aX, aY, aZ, this, aWorld.getBlockMetadata(aX, aY, aZ) + 2, 2, false);
                    return;
                }
                aY = aY + 1;
                boolean isDry = aWorld.canBlockSeeTheSky(aX - 1, aY, aZ) && aWorld.canBlockSeeTheSky(aX, aY, aZ) && aWorld.canBlockSeeTheSky(aX + 1, aY, aZ) && aWorld.canBlockSeeTheSky(aX - 1, aY, aZ - 1) && aWorld.canBlockSeeTheSky(aX, aY, aZ - 1) && aWorld.canBlockSeeTheSky(aX + 1, aY, aZ - 1) && aWorld.canBlockSeeTheSky(aX - 1, aY, aZ + 1) && aWorld.canBlockSeeTheSky(aX, aY, aZ + 1) && aWorld.canBlockSeeTheSky(aX + 1, aY, aZ + 1);
                aY = aY - 1;
                if (isDry)
                {
                    //System.out.println("We're dry!");
                    WD.set(aWorld, aX, aY, aZ, this, aWorld.getBlockMetadata(aX, aY, aZ) + 1, 2, false);
                }
        }
    }
}
