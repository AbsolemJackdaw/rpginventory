package WWBS.wwbs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBank extends Block {

	public BlockBank(int par1, Material par2Material) {
		super(par1, par2Material);

	}
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		mod_wwbs.proxy.openGui(1,par5EntityPlayer);
		return false;
	}
}
