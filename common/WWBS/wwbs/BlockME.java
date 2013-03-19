package WWBS.wwbs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockME extends Block{

	public BlockME(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	@Override
	public void func_94332_a(IconRegister par1IconRegister) {
		this.field_94336_cN = par1IconRegister.func_94245_a("wwbs:M.E.");
	}
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{		
		mod_wwbs.proxy.openGui(2,par5EntityPlayer);
		return false;
	}
}
