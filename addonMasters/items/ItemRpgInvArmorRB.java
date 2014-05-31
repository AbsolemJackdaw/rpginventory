package addonMasters.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonMasters.RpgMastersAddon;
import addonMasters.models.LionHead;
import addonMasters.models.ModelDaggerL;

public class ItemRpgInvArmorRB extends ItemRpgInvArmor {

	private LionHead lion = new LionHead();

	private ModelDaggerL dagger = new ModelDaggerL();

	public ItemRpgInvArmorRB(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, -1, resourcelocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String bindShieldToArmorClass() {

		if (this.equals(RpgMastersAddon.beastShield)) {
			return RpgMastersAddon.CLASSBEASTMASTER;
		}
		if (this.equals(RpgMastersAddon.daggers)) {
			return RpgMastersAddon.CLASSROGUE;
		}
		return super.bindShieldToArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(RpgMastersAddon.beastShield)) {
			return lion;
		}
		if(this.equals(RpgMastersAddon.daggers)) {
			return dagger;
		}
		return super.getShieldModel();
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {

		PlayerRpgInventory
		.get((EntityPlayer) par3EntityLiving);

		// RpgInv inv = mod_RpgInventory.proxy.getInventory(((EntityPlayer)
		// par3EntityLiving).getDisplayName());

		if (((EntityPlayer) par3EntityLiving).getCurrentEquippedItem()
				.getItem() == RpgMastersAddon.daggers) {
			if (RpgInventoryMod.playerClass
					.contains(RpgMastersAddon.CLASSROGUESHIELDED)) {
				par2EntityLiving.addPotionEffect(new PotionEffect(
						Potion.poison.id, RpgInventoryMod.donators
						.contains(((EntityPlayer) par3EntityLiving)
								.getDisplayName()) ? 80 : 60, 1));
			} else if (RpgInventoryMod.playerClass
					.contains(RpgMastersAddon.CLASSROGUE)) {
				par2EntityLiving.addPotionEffect(new PotionEffect(
						Potion.poison.id, RpgInventoryMod.donators
						.contains(((EntityPlayer) par3EntityLiving)
								.getDisplayName()) ? 40 : 30, 0));
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
		if (this.equals(RpgMastersAddon.beastShield)) {
			return RpgMastersAddon.CLASSBEASTMASTERSHIELDED;
		}
		if (this.equals(RpgMastersAddon.daggers)) {
			return RpgMastersAddon.CLASSROGUESHIELDED;
		}
		return super.shieldClass();
	}

}
