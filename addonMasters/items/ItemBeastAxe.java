package addonMasters.items;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.item.ItemRpgSword;
import rpgInventory.richUtil.Targetting;
import addonMasters.RpgMastersAddon;
import addonMasters.packets.PacketCrystal;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemBeastAxe extends ItemRpgSword {

	private List<Class> pettypes = Arrays.asList(new Class[] { EntityPig.class,
			EntityCow.class, EntitySpider.class, EntityCaveSpider.class, EntityChicken.class });

	private int charmTime;
	private int particleTime;
	private Random rng = new Random(1051414);

	public ItemBeastAxe(ToolMaterial mat) {
		super(mat);
	}

	public boolean canHarvestBlock(Block par2Block) {
		return (par2Block != null)
				&& ((par2Block.getMaterial() == Material.wood)
						|| (par2Block.getMaterial() == Material.plants) || (par2Block
								.getMaterial() == Material.vine)) ? true : false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			Block b, int par4, int par5, int par6,
			EntityLivingBase par7EntityLiving) {
		par1ItemStack.damageItem(1, par7EntityLiving);
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		particleTime = 0;
		charmTime = 0;
		super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer,
				par4);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if (RpgInventoryMod.playerClass.contains(RpgMastersAddon.CLASSBEASTMASTER)) {
			World world = player != null ? player.worldObj : null;
			if ((world != null)&& world.isRemote&& FMLCommonHandler.instance().getEffectiveSide().isClient()) {
				Minecraft.getMinecraft();
				// Truer Randomization
				rng = new Random(rng.nextLong() + System.currentTimeMillis());
				EntityLivingBase el = Targetting.isTargetingLivingEntity(4.0D);
				if ((el != null) && pettypes.contains(el.getClass())) {
					charmTime++;
					particleTime++;
				} else {
					charmTime = 0;
					particleTime = 0;
				}

				if (particleTime >= 7) {
					particleTime = 0;
					RpgInventoryMod.proxy.spawnParticle(world, el, rng);
				}
				if (charmTime >= 100) {
					charmTime = 0;
					float num = rng.nextFloat();
					if (num > (0.80F)) {
						RpgInventoryMod.proxy.spawnCharmParticle(world, el,rng, true);
						RpgMastersAddon.SNW.sendToServer(new PacketCrystal(el.getEntityId()));
					} else {
						RpgInventoryMod.proxy.spawnCharmParticle(world, el,rng, false);
						RpgMastersAddon.SNW.sendToServer(new PacketCrystal(0));
					}
				}
			}
		}
		super.onUsingTick(stack, player, count);
	}
}
