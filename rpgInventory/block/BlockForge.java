package rpgInventory.block;

import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.block.te.TEMold;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class BlockForge extends BlockContainer {

	private static boolean keepInventory = true;

	// TEXTUREN
	public static Icon temoldOvenBottom;

	public static Icon temoldOvenTop;
	public static Icon temoldOvenSide;
	public static Icon temoldOvenFront;
	public static Icon temoldOvenFrontActive;
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

	/**
	 * Retrieves the block texture to use based on the display side. Args:
	 * iBlockAccess, x, y, z, side
	 */
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z,
			int side) {
		int front = 0;
		Icon icon;
		TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(x, y,
				z);

		if (tile != null) {
			front = access.getBlockMetadata(x, y, z);
		} else {
			Minecraft.getMinecraft().theWorld.markBlockForUpdate(x, y, z);
		}

		switch (side) {
		case 0:
			icon = temoldOvenBottom;
			break;
		case 1:
			icon = temoldOvenTop;
			break;
		case 2:
			icon = temoldOvenSide;
			break;
		case 3:
			icon = temoldOvenSide;
			break;
		case 4:
			icon = temoldOvenSide;
			break;
		case 5:
			icon = temoldOvenSide;
			break;
		default:
			return temoldOvenSide;
		}
		if (side == front) {
			return ((TEMold) tile).isActive() ? temoldOvenFrontActive
					: temoldOvenFront;
		} else {
			return icon;
		}
	}

	public String getTextureFile() {
		return "/subaraki/RPGinventoryTM.png";
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

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = temoldOvenSide = temoldOvenTop = temoldOvenBottom = par1IconRegister
				.registerIcon("rpginventorymod:ovenSide");
		temoldOvenFront = par1IconRegister
				.registerIcon("rpginventorymod:ovenFront");
		temoldOvenFrontActive = par1IconRegister
				.registerIcon("rpginventorymod:ovenFrontBurning");
	}

	/**
	 * set a blocks direction
	 */
	// @Override
	private void setDefaultDirection(World par1World, int par2, int par3,
			int par4) {
		if (!par1World.isRemote) {
			Block var5 = par1World.getBlock(par2, par3, par4 - 1);
			Block var6 = par1World.getBlock(par2, par3, par4 + 1);
			Block var7 = par1World.getBlock(par2 - 1, par3, par4);
			Block var8 = par1World.getBlock(par2 + 1, par3, par4);
			byte var9 = 3;

			if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6]) {
				var9 = 3;
			}

			if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5]) {
				var9 = 2;
			}

			if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8]) {
				var9 = 5;
			}

			if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7]) {
				var9 = 4;
			}
			par1World.setBlock(par4, par2, par3, this, var9, 0);
		}
	}
}
