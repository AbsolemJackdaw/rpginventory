package rpgInventory.item.weapons;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;

public class ItemStaf extends ItemRpgSword {

	protected boolean hasAttacked = false;

	public ItemStaf(int par1) {
		super(par1, EnumToolMaterial.STONE);
	}

	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {

		list.add(StatCollector.translateToLocal("Charge : Hold " +
				GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindUseItem.keyCode)));
		
		list.add("Heal: "+GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode) +
				" and Release");
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer p, int par4) {
		int time = this.getMaxItemUseDuration(par1ItemStack) - par4;

		if (p.isSneaking()) {
			if (time > 0 && time <= 100) {
				//                p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj.spawnParticle("heart", p.posX + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (p.worldObj.rand.nextFloat() * p.height), p.posZ + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, var2, var4, var6);
				int heal = (int) time / 50;
				if (p.func_110143_aJ() + heal <= p.func_110138_aP()) {
					p.heal(heal);
				} else {
					p.setEntityHealth(p.func_110138_aP());
				}
			}
			if (time > 100) {
				//                p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj.spawnParticle("heart", p.posX + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (p.worldObj.rand.nextFloat() * p.height), p.posZ + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, var2, var4, var6);
				int heal = (int) time / 30;
				if (p.func_110143_aJ() + heal <= p.func_110138_aP()) {
					p.heal(heal);
				} else {
					p.setEntityHealth(p.func_110138_aP());
				}
			}
		}
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer p) {
		RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
		inv.classSets = EnumRpgClass.getPlayerClasses(p);
		if (inv.hasClass(EnumRpgClass.MAGE) || inv.hasClass(EnumRpgClass.ARCHMAGE)) {
			p.setItemInUse(is, this.getMaxItemUseDuration(is));
		}
		return is;
	}
}
