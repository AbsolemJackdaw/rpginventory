package rpgRogueBeast.items;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.item.weapons.ItemRpgSword;
import rpgInventory.richUtil.Targetting;
import rpgRogueBeast.mod_RpgRB;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemBeastAxe extends ItemRpgSword {

	private List<Class> pettypes = Arrays.asList(new Class[] { EntityPig.class,
			EntityCow.class, EntitySpider.class, EntityCaveSpider.class });
	private int charmTime;
	private int particleTime;
	private Random rng = new Random(1051414);

	public ItemBeastAxe(ToolMaterial mat) {
		super(mat);
		this.maxStackSize = 1;
		this.setMaxDamage(1500);
	}

	public boolean canHarvestBlock(Block par2Block) {
		return (par2Block != null)
				&& ((par2Block.getMaterial() == Material.wood)
						|| (par2Block.getMaterial() == Material.plants) || (par2Block
						.getMaterial() == Material.vine)) ? true : false;
	}

	// public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
	// return (par2Block != null)
	// && ((par2Block.getMaterial() == Material.wood)
	// || (par2Block.getMaterial() == Material.plants) ||
	// (par2Block.getMaterial() == Material.vine)) ? 15f
	// : super.getStrVsBlock(par1ItemStack, par2Block);
	// }

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		par2EntityLiving
				.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) par3EntityLiving), 6);
		return false;
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
		if (mod_RpgInventory.playerClass.contains(mod_RpgRB.CLASSBEASTMASTER)) {
			World world = player != null ? player.worldObj : null;
			if ((world != null)
					&& world.isRemote
					&& FMLCommonHandler.instance().getEffectiveSide()
							.isClient()) {
				Minecraft mc = Minecraft.getMinecraft();
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
					mod_RpgInventory.proxy.spawnParticle(world, el, rng);
				}
				if (charmTime >= 100) {
					charmTime = 0;
					float num = rng.nextFloat();
					if (num > (mod_RpgInventory.donators.contains(player
							.getDisplayName()) ? 0.50F : 0.80F)) {
						mod_RpgInventory.proxy.spawnCharmParticle(world, el,
								rng, true);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(bos);
						try {
							dos.writeInt(11);
							dos.writeInt(el.getEntityId());
						} catch (Throwable ex) {
						}

						// TODO
						System.out.println("send packet here");
						// Packet250CustomPayload pcp = new
						// Packet250CustomPayload(
						// "RpgRBPacket", bos.toByteArray());
						// PacketDispatcher.sendPacketToServer(pcp);
					} else {
						mod_RpgInventory.proxy.spawnCharmParticle(world, el,
								rng, false);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(bos);
						try {
							dos.writeInt(11);
							dos.writeInt(0);
						} catch (Throwable ex) {
						}

						// TODO
						System.out.println("send packet here");
						// Packet250CustomPayload pcp = new
						// Packet250CustomPayload(
						// "RpgInv", bos.toByteArray());
						// PacketDispatcher.sendPacketToServer(pcp);
					}
				}
			}
		}
		super.onUsingTick(stack, player, count);
	}
}
