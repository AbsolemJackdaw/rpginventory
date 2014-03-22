package rpgRogueBeast.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import rpgRogueBeast.mod_RpgRB;
import rpgRogueBeast.models.LionHead;
import rpgRogueBeast.models.ModelDaggerL;

public class ItemRpgInvArmorRB extends ItemRpgInvArmor {

	private LionHead lion = new LionHead();

	private ModelDaggerL dagger = new ModelDaggerL();

	public ItemRpgInvArmorRB(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String boundArmorClass() {

		if (this.equals(mod_RpgRB.beastShield)) {
			return mod_RpgRB.CLASSBEASTMASTER;
		}
		if (this.equals(mod_RpgRB.daggers)) {
			return mod_RpgRB.CLASSROGUE;
		}
		return super.boundArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(mod_RpgRB.beastShield)) {
			return lion;
		}
		// if(this.equals(mod_RpgRB.daggers))
		// return dagger;
		return super.getShieldModel();
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {

		PlayerRpgInventory inv = PlayerRpgInventory
				.get((EntityPlayer) par3EntityLiving);

		// RpgInv inv = mod_RpgInventory.proxy.getInventory(((EntityPlayer)
		// par3EntityLiving).getDisplayName());

		if (((EntityPlayer) par3EntityLiving).getCurrentEquippedItem()
				.getItem() == mod_RpgRB.daggers) {
			if (mod_RpgInventory.playerClass
					.contains(mod_RpgRB.CLASSROGUESHIELDED)) {
				par2EntityLiving
						.addPotionEffect(new PotionEffect(
								Potion.poison.id,
								mod_RpgInventory.donators
										.contains(((EntityPlayer) par3EntityLiving).getDisplayName()) ? 80
										: 60, 1));
			} else if (mod_RpgInventory.playerClass
					.contains(mod_RpgRB.CLASSROGUE)) {
				par2EntityLiving
						.addPotionEffect(new PotionEffect(
								Potion.poison.id,
								mod_RpgInventory.donators
										.contains(((EntityPlayer) par3EntityLiving).getDisplayName()) ? 40
										: 30, 0));
				if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
					par2EntityLiving
							.attackEntityFrom(
									DamageSource
											.causePlayerDamage((EntityPlayer) par3EntityLiving),
									10);
				}
			}
			if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
				par2EntityLiving
						.attackEntityFrom(
								DamageSource
										.causePlayerDamage((EntityPlayer) par3EntityLiving),
								10);
			} else {
				par2EntityLiving.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) par3EntityLiving), 6);
			}
		}
		return false;
	}

	@Override
	public String shieldClass() {
		if (this.equals(mod_RpgRB.beastShield)) {
			return mod_RpgRB.CLASSBEASTMASTERSHIELDED;
		}
		if (this.equals(mod_RpgRB.daggers)) {
			return mod_RpgRB.CLASSROGUESHIELDED;
		}
		return super.shieldClass();
	}

}
