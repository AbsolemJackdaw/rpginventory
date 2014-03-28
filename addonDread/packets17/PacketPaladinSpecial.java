package addonDread.packets17;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import addonDread.mod_RpgPlus;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.CommonTickHandler;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketPaladinSpecial extends RpgPlusAbstractPacket {

	private EntityPlayer player;
	private ItemStack weapon;

	public PacketPaladinSpecial() {
	}

	public PacketPaladinSpecial(EntityPlayer player) {
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

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);

		inv.markDirty();

		if (!mod_RpgInventory.developers.contains(player.getDisplayName()
				.toLowerCase()) || (weapon == null)) {
			if (!mod_RpgInventory.playerClass
					.contains(mod_RpgPlus.CLASSPALADIN)) {
				return;
			}
		}

		if (!CommonTickHandler.globalCooldownMap.containsKey(player
				.getDisplayName())) {
			CommonTickHandler.globalCooldownMap.put(player.getDisplayName(), 0);
		}
		if (CommonTickHandler.globalCooldownMap.get(player.getDisplayName()) <= 0) {
			CommonTickHandler.globalCooldownMap
					.put(player.getDisplayName(), (mod_RpgInventory.donators
							.contains(player.getDisplayName()) ? 5 : 7) * 20);
			// System.out.println("Healing time!");
			// Allow staff/hammer to perform one last aoe then break the weapon
			// if its damaged enough.
			if ((weapon.getItemDamage() + 3) >= weapon.getMaxDamage()) {
				// Trigger item break stuff
				weapon.damageItem(
						(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
						player);
				// delete the item
				player.renderBrokenItemStack(weapon);
				player.setCurrentItemOrArmor(0, (ItemStack) null);
			} else {
				if (!mod_RpgInventory.developers.contains(player
						.getDisplayName().toLowerCase())) {
					weapon.damageItem(3, player);
				}
			}
			float rad = 6.0f;
			AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
					player.posX - rad, player.posY - rad, player.posZ - rad,
					player.posX + rad, player.posY + rad, player.posZ + rad);
			List<EntityLiving> entl = player.worldObj.getEntitiesWithinAABB(
					EntityLiving.class, pool);
			if ((entl != null) && (entl.size() > 0)) {
				for (EntityLiving el : entl) {
					if (el != null) {
						double dist = player.getDistanceSqToEntity(el);
						double potstrength = 1.0D - (Math.sqrt(dist) / 4.0D);
						Potion.heal.affectEntity(player, el,
								(mod_RpgInventory.donators.contains(player
										.getDisplayName()) ? 5 : 2),
								potstrength * 2);
					}
				}
			}
		} else {
			player.addChatMessage(new ChatComponentText(
					"You must wait for energy to replenish, left: "
							+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
									.get(player.getDisplayName()) / 20))
							+ " seconds"));
		}
	}
}
