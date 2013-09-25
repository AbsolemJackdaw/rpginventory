package rpgRogueBeast.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import rpgInventory.EnumRpgClass;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import rpgRogueBeast.mod_RpgRB;
import rpgRogueBeast.models.LionHead;

public class ItemRpgInvArmorRB extends ItemRpgInvArmor {

	public ItemRpgInvArmorRB(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);
		// TODO Auto-generated constructor stub
	}

	private LionHead lion = new LionHead();

	@Override
	public MainShield getShieldModel() {
		if(this.equals(mod_RpgRB.beastShield))
			return lion;
		return super.getShieldModel();
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {

		PlayerRpgInventory inv = PlayerRpgInventory.get((EntityPlayer) par3EntityLiving);

		//RpgInv inv = mod_RpgInventory.proxy.getInventory(((EntityPlayer) par3EntityLiving).username);
		inv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) par3EntityLiving);

		if (((EntityPlayer) par3EntityLiving).getCurrentEquippedItem().getItem() == mod_RpgRB.daggers) {
			if (inv.hasClass(EnumRpgClass.NINJA)) {
				par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 1));
			}else if (inv.hasClass(EnumRpgClass.ROGUE)) {
				par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 40, 0));
				if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
					par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 10);
				}
			} 
			if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
				par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 10);
			} else {
				par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 6);
			}
		}
		return false;
	}

}
