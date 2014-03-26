package rpgNecroPaladin.packets17;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgNecroPaladin.CommonTickHandlerRpgPlus;
import rpgNecroPaladin.mod_RpgPlus;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSpawnMinion extends RpgPlusAbstractPacket {

	public EntityPlayer player;
	public ItemStack weapon;

	public PacketSpawnMinion() {
	}

	public PacketSpawnMinion(EntityPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.weapon = ByteBufUtils.readItemStack(buffer);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		ByteBufUtils.writeItemStack(buffer,
				this.player.getCurrentEquippedItem());
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		if (weapon.getItem().equals(mod_RpgPlus.necro_weapon)
				&& mod_RpgInventory.playerClass
						.contains(mod_RpgPlus.CLASSNECRO)) {
			if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(player
					.getDisplayName())) {
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(
						player.getDisplayName(), 0);
			}
			if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(player
					.getDisplayName()) <= 0) {
				// 2 second cooldown
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(player
						.getDisplayName(), 20 * (mod_RpgInventory.donators
						.contains(player.getDisplayName()) ? 1 : 2));
				// System.out.println("SpawnMob");
				// Allow staff/hammer to perform one last aoe then break the
				// weapon if its damaged enough.
				if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
					// Trigger item break stuff
					weapon.damageItem(
							(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
							player);
					// delete the item
					player.renderBrokenItemStack(weapon);
					player.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					weapon.damageItem(2, player);
				}
				World world = player.worldObj;
				if (mod_RpgInventory.playerClass
						.contains(mod_RpgPlus.CLASSNECROSHIELD)) {
					if (!world.isRemote) {
						EntityMinionS var4 = new EntityMinionS(world, player);
						if (var4 != null) {
							var4.setPosition(player.posX, player.posY,
									player.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(player.getDisplayName());
						}
					}
				} else {
					if (!world.isRemote) {
						EntityMinionZ var4 = new EntityMinionZ(world, player);
						if (var4 != null) {
							var4.setPosition(player.posX, player.posY,
									player.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(player.getDisplayName());
						}
					}
				}
			} else {
				player.addChatMessage(new ChatComponentText(
						"You must wait for energy to replenish, left: "
								+ Math.floor(1 + (CommonTickHandlerRpgPlus.rpgPluscooldownMap
										.get(player.getDisplayName()) / 20))
								+ " seconds"));
			}
		}
	}
}
