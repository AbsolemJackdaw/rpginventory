package WWBS.wwbs.wwme;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import WWBS.wwbs.mod_wwbs;

public class BlockME extends BlockContainer {

	public BlockME(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WwmeTE();
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
			float par9) {
		mod_wwbs.proxy.openGui(2, par5EntityPlayer, x, y, z);
		return true;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wwbs:M.E.");
	}
}
