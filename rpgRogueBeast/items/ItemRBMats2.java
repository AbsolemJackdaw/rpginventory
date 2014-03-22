package rpgRogueBeast.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgRogueBeast.mod_RpgRB;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.IPet;
import rpgRogueBeast.entity.SpiderPet;

public class ItemRBMats2 extends Item {

	public ItemRBMats2(int par1) {
		super(par1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack whistle, World world,
			EntityPlayer player) {
		if (mod_RpgInventory.playerClass.contains(mod_RpgRB.CLASSBEASTMASTER)) {
			if (!world.isRemote && (player.ridingEntity == null)) {
				try {
					PlayerRpgInventory inv = PlayerRpgInventory.get(player);

					ItemStack stack = inv.getCrystal();
					if (stack != null) {
						// Pet is in the world.
						if (IPet.playersWithActivePets
								.containsKey(player.getDisplayName())) {
							EntityLiving e = (EntityLiving) IPet.playersWithActivePets
									.get(player.getDisplayName()).getPet();
							IPet.playersWithActivePets.remove(player.getDisplayName());
							if ((e != null) && !e.isDead) {
								stack = ((IPet) e).writePetToItemStack();
								inv.setInventorySlotContents(6, stack);
								e.setDead();
								// System.out.println("Put Away");
								return whistle;
							}
							// pet is not in the world
						} else {
							world.playSoundAtEntity(player,
									"subaraki:petWhistle", 1f, 1f);

							switch (stack.getItemDamage()) {
							case 1:

								BoarPet Boar = new BoarPet(world, player, stack);
								Boar.setPosition(player.posX,
										player.posY + 0.5F, player.posZ);
								Boar.setTamed(true);
								try {
									Boar.setName(stack.stackTagCompound
											.getString("PetName"));
									Boar.setLevel(stack.stackTagCompound
											.getInteger("PetLevel"));
									Boar.setHealth(stack.stackTagCompound
											.getFloat("PetHealth"));
									if (Boar.getHealth() <= 0) {
										Boar.setHealth(1);
									}
								} catch (Throwable ex) {
								}
								world.spawnEntityInWorld(Boar);
								break;
							case 2:
								SpiderPet spider = new SpiderPet(world, player,
										stack);
								spider.setPosition(player.posX,
										player.posY + 0.5F, player.posZ);
								spider.setOwner(player.getDisplayName());
								spider.setTamed(true);
								try {
									spider.setName(stack.stackTagCompound
											.getString("PetName"));
									spider.setLevel(stack.stackTagCompound
											.getInteger("PetLevel"));
									spider.setHealth(stack.stackTagCompound
											.getFloat("PetHealth"));
									if (spider.getHealth() <= 0) {
										spider.setHealth(1);
									}
								} catch (Throwable ex) {
								}
								world.spawnEntityInWorld(spider);
								break;
							case 3:
								BullPet bull = new BullPet(world, player, stack);
								bull.setPosition(player.posX,
										player.posY + 0.5F, player.posZ);
								bull.setTamed(true);
								try {
									bull.setName(stack.stackTagCompound
											.getString("PetName"));
									bull.setLevel(stack.stackTagCompound
											.getInteger("PetLevel"));
									bull.setHealth(stack.stackTagCompound
											.getFloat("PetHealth"));
									if (bull.getHealth() <= 0) {
										bull.setHealth(1);
									}
								} catch (Throwable ex) {
								}
								world.spawnEntityInWorld(bull);
								break;
							}
							inv.setInventorySlotContents(6, stack);
						}
					}
				} catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}
		return whistle;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:"
				+ itemName);
	}
}
