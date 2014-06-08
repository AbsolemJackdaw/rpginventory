package addonDread.items;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.ItemRpgSword;
import addonDread.RpgDreadAddon;
import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;
import addonDread.minions.IMinion;
import addonDread.packets.DreadServerPacketHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class ItemNecroSkull extends ItemRpgSword  {

	public ItemNecroSkull(ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		this.maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase mob,EntityLivingBase player) {
		if ((player instanceof EntityPlayer)) {
			EntityPlayer p = (EntityPlayer) player;
			World world = p.worldObj;
			if (!world.isRemote) {
				ItemStack weapon = p.getCurrentEquippedItem();
				PlayerRpgInventory.get(p);

				if (weapon != null) {
					if (weapon.getItem().equals(RpgDreadAddon.necroSkull)&& RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {

						if (mob.getClass() == EntityZombie.class) {

							EntityMinionZ var4 = new EntityMinionZ(world, p);
							var4.setPosition(mob.posX, mob.posY, mob.posZ);
							
							for (int i = 0; i < 5; i++) {
								ItemStack stack = mob.getEquipmentInSlot(i);
								if (stack != null) {
									var4.setCurrentItemOrArmor(i, stack);
								}
							}
							
							mob.setDead();

							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.getDisplayName());

						} else if (mob.getClass() == EntitySkeleton.class) {
							EntityMinionS var4 = new EntityMinionS(world, p);
							var4.setPosition(mob.posX, mob.posY, mob.posZ);
							mob.setDead();
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.getDisplayName());
						} else if (!(mob instanceof IMinion)) {
							mob.addPotionEffect(new PotionEffect(Potion.wither.id, RpgInventoryMod.donators.contains(p.getDisplayName()) ? 80: 60, 1));
						} else {
							mob.heal(3);
						}
					}
				}

			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world,
			EntityPlayer player) {

		if(RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out =new ByteBufOutputStream(buf);
				out.writeInt(DreadServerPacketHandler.SKULLRCLICK);
				RpgDreadAddon.Channel.sendToServer(new FMLProxyPacket(buf, "DreadPacket"));
				out.close();

			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("skull");

			}
		}
		return is;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer p,
			Entity entity) {
		PlayerRpgInventory.get(p);

		ItemStack weapon = p.getCurrentEquippedItem();

		if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
			if (entity instanceof IMinion) {
				if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
					weapon.damageItem((weapon.getMaxDamage() - weapon.getItemDamage()) + 1,p);
					p.renderBrokenItemStack(weapon);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					weapon.damageItem(RpgInventoryMod.donators.contains(p
							.getDisplayName()) ? 1 : 2, p);
				}
				((EntityLiving) entity).heal(3);
				return true;
			}
		}
		return false;
	}
}
