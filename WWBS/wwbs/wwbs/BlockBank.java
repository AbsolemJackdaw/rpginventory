package WWBS.wwbs.wwbs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import WWBS.wwbs.mod_wwbs;

public class BlockBank extends BlockContainer {

	public BlockBank(int par1, Material par2Material) {
		super(par1, par2Material);

	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wwbs:bank");
	}
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{		
		mod_wwbs.proxy.openGui(1,par5EntityPlayer,x,y,z);
		
		return true;
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WwbsTe();
	}
}