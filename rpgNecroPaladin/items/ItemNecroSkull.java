package rpgNecroPaladin.items;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.weapons.ItemRpgWeapon;
import rpgNecroPaladin.mod_RpgPlus;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.IMinion;
import rpgNecroPaladin.packets17.RpgPlusPacketPipeline17;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemNecroSkull extends ItemRpgWeapon {

	private int weaponDamage;
	private final ToolMaterial toolMaterial;

	public ItemNecroSkull(ToolMaterial par2EnumToolMaterial) {
		super();
		this.toolMaterial = par2EnumToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.weaponDamage = (int) (4 + this.toolMaterial.getDamageVsEntity());
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase mob,
			EntityLivingBase player) {
		if ((player instanceof EntityPlayer)) {
			EntityPlayer p = (EntityPlayer) player;
			World world = p.worldObj;
			if (!world.isRemote) {
				ItemStack weapon = p.getCurrentEquippedItem();
				PlayerRpgInventory inv = PlayerRpgInventory.get(p);

				if (weapon != null) {
					if (weapon.getItem().equals(mod_RpgPlus.necro_weapon)
							&& mod_RpgInventory.playerClass
									.contains(mod_RpgPlus.CLASSNECRO)) {

						if ((weapon.getItemDamage() + 2) >= weapon
								.getMaxDamage()) {
							// Trigger item break stuff
							weapon.damageItem((weapon.getMaxDamage() - weapon
									.getItemDamage()) + 1, p);
							// delete the item
							p.renderBrokenItemStack(weapon);
							p.setCurrentItemOrArmor(0, (ItemStack) null);
						} else {
							weapon.damageItem(2, p);
						}

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
						} else if (mob.getClass() == EntityPigZombie.class) {
							EntityPigZombie pigzombie = new EntityPigZombie(
									world);
							pigzombie.setPosition(mob.posX, mob.posY, mob.posZ);
							mob.setDead();
							world.spawnEntityInWorld(pigzombie);
							// necromancers can not make pig zombies angry !

						} else {
							if (!(mob instanceof IMinion)) {
								mob.attackEntityFrom(DamageSource.wither,
										mod_RpgInventory.donators.contains(p
												.getDisplayName()) ? 6 : 4);
								mob.addPotionEffect(new PotionEffect(
										Potion.wither.id,
										mod_RpgInventory.donators.contains(p
												.getDisplayName()) ? 60 : 40, 1));
							} else {
								mob.heal(3);
							}
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
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			try {
				//TODO sendpacket
				System.out.println("send packet here necroSkull spawning entity");
				dos.writeInt(RpgPlusPacketPipeline17.WEAPONIDS.SKULLRCLICK);
//				Packet250CustomPayload packet = new Packet250CustomPayload(
//						"RpgPlusPlus", bos.toByteArray());
//				PacketDispatcher.sendPacketToServer(packet);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return is;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer p,
			Entity entity) {
		PlayerRpgInventory inv = PlayerRpgInventory.get(p);

		ItemStack weapon = p.getCurrentEquippedItem();

		if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSNECRO)) {
			if (entity instanceof IMinion) {
				if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
					// Trigger item break stuff
					weapon.damageItem(
							(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
							p);
					// delete the item
					p.renderBrokenItemStack(weapon);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					weapon.damageItem(mod_RpgInventory.donators.contains(p
							.getDisplayName()) ? 1 : 2, p);
				}
				((EntityLiving) entity).heal(3);
				return true;
			}
		}
		return false;
	}
}
