package RpgInventory.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import RpgInventory.IPet;
import RpgInventory.mod_RpgInventory;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrystal extends ItemRpgArmor {

	public static final String[] pets = new String[]{"Empty Crystal", "Boar", "Spider", "Bull"};

	public ItemCrystal(int id, int armorType, int maxDamage, String name) {
		super(id, armorType, maxDamage, name);
		this.setHasSubtypes(true);
		//Max stack size MUST be 1!
		this.maxStackSize = 1;
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3);

		if (var2 > 0) {
			NBTTagCompound tags = par1ItemStack.getTagCompound();
			if (tags != null) {
				if (tags.hasKey("PetLevel") && tags.hasKey("PetName")) {
					par1ItemStack.setItemName(tags.getString("PetName")
							+ " lv"
							+ String.valueOf(tags.getInteger("PetLevel")) /*+ " " + pets[var2]*/);
				}
			}
		}
		return getItemName() + "." + pets[var2];
	}

	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
		NBTTagCompound tags = stack.getTagCompound();
		int var2 = MathHelper.clamp_int(stack.getItemDamage(), 0, 3);

		if (var2 > 0) {
		}
		if (tags != null) {
			if (tags.hasKey("PetAttack")) {
				list.add(StatCollector.translateToLocal("ATK : " + String.valueOf(tags.getInteger("PetAttack"))));
			}
			if (tags.hasKey("PetHealth") && tags.hasKey("PetPrevHealth")) {
				list.add(StatCollector.translateToLocal("HP : "
						+ String.valueOf(tags.getInteger("PetPrevHealth") + "/"
								+ String.valueOf(tags.getInteger("PetHealth")))));
			}
			if (tags.hasKey("PetLevel")) {
				if (tags.getInteger("PetLevel") >= 50) {
					if (tags.hasKey("isSaddled")) {
						if (tags.getBoolean("isSaddled") == true) {
							list.add(StatCollector.translateToLocal("Saddled"));
						}
						if (tags.getBoolean("isSaddled") == false) {
							list.add(StatCollector.translateToLocal("No Saddle"));
						}
					}
				} else {
					list.add(StatCollector.translateToLocal("N/A ") + String.valueOf(50 - tags.getInteger("PetLevel")));
				}
			}
			if (tags.hasKey("OwnerName")) {
				list.add(StatCollector.translateToLocal("Owner :" + tags.getString("OwnerName")));
			}
		}
	}

	 /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));

    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack is) {
        if (is.getItemDamage() > 0) {
            return true;
        }
        return is.isItemEnchanted();
    }

    public int getColorFromItemStack(ItemStack b2, int par2) {
        if (b2.getItemDamage() == 1) {
            return 0xc36113;
        }

        if (b2.getItemDamage() == 2) {
            return 0x0a8274;
        }

        if (b2.getItemDamage() == 3) {
            return 0xe71809;
        }

        return 0xffffff;
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            try {
                if (IPet.playersWithActivePets.get(player.username) == null || IPet.playersWithActivePets.get(player.username).isDead()) {
                    IPet.playersWithActivePets.remove(player.username);
                }
                if (stack.getItemDamage() == 0) {
                    if (IPet.playersWithActivePets.containsKey(player.username)) {
                        EntityLiving e = (EntityLiving) IPet.playersWithActivePets.get(player.username);
                        if (e != null && e instanceof IPet) {
                            stack = ((IPet) e).writePetToItemStack();
                            ((EntityLiving) e).setDead();
                        }
                        IPet.playersWithActivePets.remove(player.username);
                    }
                } else if (!IPet.playersWithActivePets.containsKey(player.username)) {
                    switch (stack.getItemDamage()) {
                        case 1:
                            BoarPet Boar = new BoarPet(world, player, stack);
                            Boar.setPosition(player.posX, player.posY, player.posZ);
                            Boar.setTamed(true);
                            world.spawnEntityInWorld(Boar);
                            stack = new ItemStack(mod_RpgInventory.crystal, 1, 0);
                            break;
                        case 2:
                            SpiderPet spider = new SpiderPet(world, player, stack);
                            spider.setPosition(player.posX, player.posY, player.posZ);
                            spider.setOwner(player.username);
                            spider.setTamed(true);
                            world.spawnEntityInWorld(spider);
                            stack = new ItemStack(mod_RpgInventory.crystal, 1, 0);
                            break;
                        case 3:
                            BullPet bull = new BullPet(world, player, stack);
                            bull.setPosition(player.posX, player.posY, player.posZ);
                            bull.setTamed(true);
                            world.spawnEntityInWorld(bull);
                            stack = new ItemStack(mod_RpgInventory.crystal, 1, 0);
                            break;
                    }
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return stack;
    }
    /*
     @Override
     public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
     if (!world.isRemote) {
     if (stack.getItemDamage() > 0) {
     switch (stack.getItemDamage()) {
     case 1:
     break;
     case 2:
     SpiderPet spider = new SpiderPet(world, player, stack);
     spider.setPosition(x, y + 1, z);
     world.spawnEntityInWorld(spider);
     stack.setItemDamage(0);
     break;
     case 3:
     BullPet bull = new BullPet(world, player, stack);
     bull.setPosition(x, y + 1, z);
     world.spawnEntityInWorld(bull);
     stack.setItemDamage(0);
     break;
     }
     }
     }
     stack.setItemName(getItemNameIS(stack));

     return false;
     }*/
}
