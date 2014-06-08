package addonMasters.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.RpgMastersAddon;
import addonMasters.entity.BoarPet;
import addonMasters.entity.BullPet;
import addonMasters.entity.ChickenPet;
import addonMasters.entity.IPet;
import addonMasters.entity.IPet.PetID;
import addonMasters.entity.SpiderPet;

public class ItemPetWhistle extends Item {

	public ItemPetWhistle() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack whistle, World world,EntityPlayer player) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		ItemStack stack = inv.getCrystal();

		if (!world.isRemote && (player.ridingEntity == null) ) {
			if (RpgInventoryMod.playerClass.contains(RpgMastersAddon.CLASSBEASTMASTER)) {
				try {
					if (stack != null) {
						// Pet is in the world.
						if (IPet.playersWithActivePets.containsKey(player.getDisplayName())) {
							EntityLiving e = (EntityLiving) IPet.playersWithActivePets.get(player.getDisplayName()).getPet();
							IPet.playersWithActivePets.remove(player.getDisplayName());
							if ((e != null) && !e.isDead) {
								stack = ((IPet) e).writePetToItemStack();
								inv.setInventorySlotContents(6, stack);
								e.setDead();
								return whistle;
							}
							// pet is not in the world
						} else {
							//							world.playSoundAtEntity(player,
							//									"subaraki:petWhistle", 1f, 1f);

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
								if(!world.isRemote) {
									world.spawnEntityInWorld(Boar);
								}
								break;
							case 2:
								SpiderPet spider = new SpiderPet(world, player,	stack);
								spider.setPosition(player.posX,player.posY + 0.5F, player.posZ);
								spider.setOwner(player.getDisplayName());
								spider.setTamed(true);
								IPet.playersWithActivePets.put(player.getDisplayName(), new PetID(
										spider.dimension, spider.getEntityId()));
								if(stack.stackTagCompound != null){
									spider.setName(stack.stackTagCompound.getString("PetName"));
									spider.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
									spider.setHealth(stack.stackTagCompound.getFloat("PetHealth"));
									if (spider.getHealth() <= 0) {
										spider.setHealth(1);
									}
								}
								if(!world.isRemote) {
									world.spawnEntityInWorld(spider);
								}

								break;
							case 3:
								BullPet bull = new BullPet(world, player, stack);
								bull.setPosition(player.posX,player.posY + 0.5F, player.posZ);
								bull.setTamed(true);
								try {
									bull.setName(stack.stackTagCompound.getString("PetName"));
									bull.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
									bull.setHealth(stack.stackTagCompound.getFloat("PetHealth"));
									if (bull.getHealth() <= 0) {
										bull.setHealth(1);
									}
								} catch (Throwable ex) {
								}
								if(!world.isRemote) {
									world.spawnEntityInWorld(bull);
								}
								break;

							case 4:
								ChickenPet rooster = new ChickenPet(world, player,stack);
								rooster.setPosition(player.posX,player.posY + 0.5F, player.posZ);
								rooster.setOwner(player.getDisplayName());
								rooster.setTamed(true);
								IPet.playersWithActivePets.put(player.getDisplayName(), new PetID(
										rooster.dimension, rooster.getEntityId()));
								if(stack.stackTagCompound != null){
									rooster.setName(stack.stackTagCompound.getString("PetName"));
									rooster.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
									rooster.setHealth(stack.stackTagCompound.getFloat("PetHealth"));
									if (rooster.getHealth() <= 0) {
										rooster.setHealth(1);
									}
								}
								if(!world.isRemote) {
									world.spawnEntityInWorld(rooster);
								}

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
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:" + itemName;
		return this;
	}
}
