package rpgInventory.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.block.te.TEMold;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockForge extends BlockContainer {

//	private static boolean keepInventory = true;

	// TEXTURES
//	public static IIcon temoldOvenBottom;
//	public static IIcon temoldOvenTop;
	public static IIcon temoldOvenSide;

	public static IIcon temoldOvenFront;
	public static IIcon temoldOvenFrontActive;
	
	public boolean isBurning;

	/**
	 * Is the random generator used by furnace to drop the inventory contents in
	 * random directions.
	 */
	private Random temoldRand = new Random();

	public BlockForge(Material par2Material) {
		super(par2Material);
		this.setCreativeTab(mod_RpgInventory.tab);
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an
	 * update, as appropriate
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			Block b, int par6) {

		TEMold temold = (TEMold) par1World.getTileEntity(par2, par3, par4);
		if (temold != null) {
			for (int var8 = 0; var8 < 5; ++var8) {
				ItemStack item = temold.getStackInSlot(var8);
				if (item != null) {
					float var10 = (this.temoldRand.nextFloat() * 0.8F) + 0.1F;
					float var11 = (this.temoldRand.nextFloat() * 0.8F) + 0.1F;
					float var12 = (this.temoldRand.nextFloat() * 0.8F) + 0.1F;
					while (item.stackSize > 0) {
						int var13 = this.temoldRand.nextInt(21) + 10;
						if (var13 > item.stackSize) {
							var13 = item.stackSize;
						}
						item.stackSize -= var13;
						EntityItem var14 = new EntityItem(par1World, par2
								+ var10, par3 + var11, par4 + var12,
								new ItemStack(item.getItem(), var13,
										item.getItemDamage()));
						if (item.hasTagCompound()) {
							var14.getEntityItem().setTagCompound(
									(NBTTagCompound) item.getTagCompound()
									.copy());
						}
						float var15 = 0.05F;
						var14.motionX = (float) this.temoldRand.nextGaussian()
								* var15;
						var14.motionY = ((float) this.temoldRand.nextGaussian() * var15) + 0.2F;
						var14.motionZ = (float) this.temoldRand.nextGaussian()
								* var15;
						par1World.spawnEntityInWorld(var14);
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, b, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TEMold();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : (isBurning ?temoldOvenFrontActive : temoldOvenFront )));
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public Block idDropped(int par1, Random par2Random, int par3) {
		return this;
	}

	/**
	 * called everytime the player right clicks this block
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i2, float a, float b, float c) {
		FMLNetworkHandler.openGui(player, mod_RpgInventory.instance, 2, world,
				x, y, z);
		return true;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int x, int y, int z) {
		super.onBlockAdded(par1World, x, y, z);
		this.setDefaultDirection(par1World, x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z,
			EntityLivingBase entity, ItemStack par6ItemStack) {
		int var = MathHelper
				.floor_double(((entity.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3;
		// Unsure what the last var is for, 0 seems to skip what it does tho...
		switch (var) {
		case 0:
			w.setBlockMetadataWithNotify(x, y, z, 2, 0);
			break;

		case 1:
			w.setBlockMetadataWithNotify(x, y, z, 5, 0);
			break;

		case 2:
			w.setBlockMetadataWithNotify(x, y, z, 3, 0);
			break;

		case 3:
			w.setBlockMetadataWithNotify(x, y, z, 4, 0);
			break;
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public void randomDisplayTick(World par1World, int x, int y, int z,
			Random par5Random) {
		try {
			TEMold tile = (TEMold) Minecraft.getMinecraft().theWorld
					.getTileEntity(x, y, z);
			if (tile.isActive()) {
				int var6 = par1World.getBlockMetadata(x, y, z);
				float var7 = x + 0.5F;
				float var8 = y + 0.0F
						+ ((par5Random.nextFloat() * 6.0F) / 16.0F);
				float var9 = z + 0.5F;
				float var10 = 0.52F;
				float var11 = (par5Random.nextFloat() * 0.6F) - 0.3F;

				if (var6 == 4) {
					par1World.spawnParticle("smoke", var7 - var10, var8, var9
							+ var11, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", var7 - var10, var8, var9
							+ var11, 0.0D, 0.0D, 0.0D);
				} else if (var6 == 5) {
					par1World.spawnParticle("smoke", var7 + var10, var8, var9
							+ var11, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", var7 + var10, var8, var9
							+ var11, 0.0D, 0.0D, 0.0D);
				} else if (var6 == 2) {
					par1World.spawnParticle("smoke", var7 + var11, var8, var9
							- var10, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", var7 + var11, var8, var9
							- var10, 0.0D, 0.0D, 0.0D);
				} else if (var6 == 3) {
					par1World.spawnParticle("smoke", var7 + var11, var8, var9
							+ var10, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", var7 + var11, var8, var9
							+ var10, 0.0D, 0.0D, 0.0D);
				}
			}
		} catch (Throwable ex) {
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = temoldOvenSide  = reg
				.registerIcon("rpginventorymod:ovenSide");

		temoldOvenFront = reg.registerIcon("rpginventorymod:ovenFront");

		temoldOvenFrontActive = reg
				.registerIcon("rpginventorymod:ovenFrontBurning");
	}

	/**
	 * set this block's direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3,
			int par4) {
		if (!par1World.isRemote) {
			Block block = par1World.getBlock(par2, par3, par4 - 1);
			Block block1 = par1World.getBlock(par2, par3, par4 + 1);
			Block block2 = par1World.getBlock(par2 - 1, par3, par4);
			Block block3 = par1World.getBlock(par2 + 1, par3, par4);
			byte b0 = 3;

			if (block.func_149730_j() && !block1.func_149730_j()) {
				b0 = 3;
			}

			if (block1.func_149730_j() && !block.func_149730_j()) {
				b0 = 2;
			}

			if (block2.func_149730_j() && !block3.func_149730_j()) {
				b0 = 5;
			}

			if (block3.func_149730_j() && !block2.func_149730_j()) {
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}
}
