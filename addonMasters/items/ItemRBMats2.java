package addonMasters.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.mod_RpgRB;
import addonMasters.entity.BoarPet;
import addonMasters.entity.BullPet;
import addonMasters.entity.IPet;
import addonMasters.entity.SpiderPet;
import addonMasters.entity.IPet.PetID;

public class ItemRBMats2 extends Item {

	public ItemRBMats2() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack whistle, World world,EntityPlayer player) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		ItemStack stack = inv.getCrystal();

		//				if(!world.isRemote){
		//					SpiderPet pig = new SpiderPet(player.worldObj , player, stack );
		//					pig.setPosition(player.posX, player.posY, player.posZ);
		//					pig.setOwner(player.getCommandSenderName());
		//					pig.setTamed(true);
		//					pig.setName("James");
		//					pig.setLevel(20);
		//					pig.setHealth(100);
		//					pig.setSaddled(true);
		//					pig.setWorld(world);
		//					pig.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
		//					player.worldObj.spawnEntityInWorld(pig);
		//					IPet.playersWithActivePets.put(player.getDisplayName(), new PetID(
		//							pig.dimension, pig.getEntityId()));
		//				}

		//			player.addChatMessage(new ChatComponentText("walla"));

//		
		System.out.println(mod_RpgInventory.playerClass);
		
			if (!world.isRemote && (player.ridingEntity == null) ){
				if (mod_RpgInventory.playerClass.contains(mod_RpgRB.CLASSBEASTMASTER)){
				System.out.println("2");

				try {
					if (stack != null)
						// Pet is in the world.
						if (IPet.playersWithActivePets.containsKey(player
								.getDisplayName())) {
							EntityLiving e = (EntityLiving) IPet.playersWithActivePets
									.get(player.getDisplayName()).getPet();
							IPet.playersWithActivePets.remove(player
									.getDisplayName());
							if ((e != null) && !e.isDead) {
								stack = ((IPet) e).writePetToItemStack();
								inv.setInventorySlotContents(6, stack);
								e.setDead();
								System.out.println("Put Away");
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
									if (Boar.getHealth() <= 0)
										Boar.setHealth(1);
								} catch (Throwable ex) {

								}
								if(!world.isRemote)
									world.spawnEntityInWorld(Boar);
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
									if (spider.getHealth() <= 0) 
										spider.setHealth(1);
								}
								
									System.out.println("[INFO] ~ Pet was freshly spawned. This is normal, unless your pet is not new.");
								
								if(!world.isRemote)
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
									if (bull.getHealth() <= 0)
										bull.setHealth(1);
								} catch (Throwable ex) {
								}
								if(!world.isRemote)
									world.spawnEntityInWorld(bull);
								break;
							}
							inv.setInventorySlotContents(6, stack);
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
