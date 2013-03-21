package WWBS.wwbs.wwbs;

import WWBS.wwbs.mod_wwbs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBank extends BlockContainer {

	public BlockBank(int par1, Material par2Material) {
		super(par1, par2Material);

	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wwbs:bank");
	}
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{		
		mod_wwbs.proxy.openGui(1,par5EntityPlayer);
		return false;
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WwbsTe();
	}
}
