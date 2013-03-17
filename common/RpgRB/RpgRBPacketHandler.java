package RpgRB;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import RpgInventory.CommonTickHandler;
import RpgInventory.IPet;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BMPetImpl;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class RpgRBPacketHandler implements IPacketHandler {
	private Random rand = new Random(5);

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		handleRandom(packet, player);
		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;
		ObjectInputStream dis;
		try {
			dis = new ObjectInputStream(new ByteArrayInputStream(packet.data));
			String newName = dis.readUTF();
			setPetName(p, newName);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("nameRecievingFailed");
		}
	}
	private void handleRandom(Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

		try
		{
			int guiId = dis.readInt();
			switch (guiId) {
			case 14:
				if (!world.isRemote) {
					ItemStack dagger = p.getCurrentEquippedItem();
					ItemStack var311 = p.inventory.armorItemInSlot(3);
					ItemStack var211 = p.inventory.armorItemInSlot(2);
					ItemStack var111 = p.inventory.armorItemInSlot(1);
					ItemStack var011 = p.inventory.armorItemInSlot(0);
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						if (dagger == null || var311 == null || var211 == null || var111 == null || var011 == null) {
							break;
						}
						if (dagger.getItem() != mod_RpgInventory.daggers || var311.getItem() != mod_RpgInventory.rogueHood || var211.getItem() != mod_RpgInventory.rogueChest || var111.getItem() != mod_RpgInventory.rogueLegs || var011.getItem() != mod_RpgInventory.rogueBoots) {
							break;
						}
					}
					if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
						CommonTickHandler.globalCooldownMap.put(p.username, 0);
					}
					if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
						CommonTickHandler.globalCooldownMap.put(p.username, 5 * 20);
						if (dagger.getItemDamage() + 3 >= dagger.getMaxDamage()) {
							dagger.damageItem(dagger.getMaxDamage() - dagger.getItemDamage(), p);
							p.renderBrokenItemStack(dagger);
							p.setCurrentItemOrArmor(0, (ItemStack) null);
						} else {
							if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
								dagger.damageItem(3, p);
							}
						}
						p.worldObj.spawnEntityInWorld(new EntityTeleportStone(p.worldObj, p));
						double d0 = this.rand.nextGaussian() * 0.02D;
						double d1 = this.rand.nextGaussian() * 0.02D;
						double d2 = this.rand.nextGaussian() * 0.02D;
						p.worldObj.spawnParticle("largesmoke", p.posX + (double) (this.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (this.rand.nextFloat() * p.height), p.posZ + (double) (this.rand.nextFloat() * p.width * 2.0F) - (double) p.width, d0, d1, d2);

					} else {
						p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
					}
				}
				break;
			}
		}
		catch(Throwable e)
		{

		}
	}
	public void setPetName(EntityPlayer p, String newName) {
		try {
			System.out.println(newName);
			ItemStack petCrystal = mod_RpgInventory.proxy.getInventory(p.username).getCrystal();
			BMPetImpl thePet = null;
			if (IPet.playersWithActivePets.containsKey(p.username)) {
				thePet = (BMPetImpl) IPet.playersWithActivePets.get(p.username).getPet();
			}
			if (thePet != null && !((EntityLiving) thePet).isDead) {
				thePet.setName(newName);
			}
			petCrystal.getTagCompound().setString("PetName", newName);
			((NBTTagCompound) petCrystal.getTagCompound().getTag("RPGPetInfo")).setString("Name", newName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
