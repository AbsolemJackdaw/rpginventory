package rpgRogueBeast.packets17;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.CommonTickHandler;
import rpgRogueBeast.mod_RpgRB;
import rpgRogueBeast.entity.EntityTeleportStone;

public class PacketTeleport extends RpgRBAbstractPacket {

	private World world;
	private Random rand;

	public PacketTeleport() {
	}

	public PacketTeleport(World world, Random rand) {
		super();
		this.world = world;
		this.rand = rand;
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {

		if (!world.isRemote) {
			ItemStack dagger = player.getCurrentEquippedItem();

			if (!mod_RpgInventory.developers.contains(player.getDisplayName()
					.toLowerCase())) {
				if (!mod_RpgInventory.playerClass
						.contains(mod_RpgRB.CLASSROGUE)) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(player
					.getDisplayName())) {
				CommonTickHandler.globalCooldownMap.put(
						player.getDisplayName(), 0);
			}
			if (CommonTickHandler.globalCooldownMap
					.get(player.getDisplayName()) <= 0) {
				CommonTickHandler.globalCooldownMap.put(
						player.getDisplayName(),
						(mod_RpgInventory.donators.contains(player
								.getDisplayName()) ? 3 : 5) * 20);
				if ((dagger.getItemDamage() + 3) >= dagger.getMaxDamage()) {
					dagger.damageItem(
							dagger.getMaxDamage() - dagger.getItemDamage(),
							player);
					player.renderBrokenItemStack(dagger);
					player.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(player
							.getDisplayName().toLowerCase())) {
						dagger.damageItem(3, player);
					}
				}
				player.worldObj.spawnEntityInWorld(new EntityTeleportStone(
						player.worldObj, player));
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				player.worldObj
						.spawnParticle(
								"largesmoke",
								(player.posX + (rand.nextFloat() * player.width * 2.0F))
										- player.width,
								player.posY + 0.5D
										+ (rand.nextFloat() * player.height),
								(player.posZ + (rand.nextFloat() * player.width * 2.0F))
										- player.width, d0, d1, d2);

			} else {
				player.addChatMessage(new ChatComponentText(
						"You must wait for energy to replenish, left: "
								+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
										.get(player.getDisplayName()) / 20))
								+ " seconds"));
			}
		}
	}
}
