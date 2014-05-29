package addonBasic.items.weapons;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.ItemRpgSword;
import addonBasic.RpgBaseAddon;

public class ItemStaf extends ItemRpgSword {

	protected boolean hasAttacked = false;

	public ItemStaf() {
		super(ToolMaterial.STONE);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list,
			boolean yesno) {

		list.add(StatCollector.translateToLocal("Charge : Hold "
				+ GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindUseItem
						.getKeyCode())));

		list.add("Heal: "
				+ GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindSneak
						.getKeyCode()) + " and Release");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World par2World,
			EntityPlayer p) {
		PlayerRpgInventory inv = PlayerRpgInventory.get(p);

		// exception for archmage : this should be evaded any time, but I don't
		// have a choice here.
		if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSMAGE)
				|| RpgInventoryMod.playerClass.contains("archMage"))
			p.setItemInUse(is, this.getMaxItemUseDuration(is));
		return is;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer p, int par4) {
		int time = this.getMaxItemUseDuration(par1ItemStack) - par4;

		if (p.isSneaking()) {
			if ((time > 0) && (time <= 100)) {
				// p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj
						.spawnParticle(
								"heart",
								(p.posX + (p.worldObj.rand.nextFloat()
										* p.width * 2.0F))
										- p.width,
								p.posY
										+ 0.5D
										+ (p.worldObj.rand.nextFloat() * p.height),
								(p.posZ + (p.worldObj.rand.nextFloat()
										* p.width * 2.0F))
										- p.width, var2, var4, var6);
				int heal = time / 50;
				if ((p.getHealth() + heal) <= p.getMaxHealth())
					p.heal(heal);
				else
					p.setHealth(p.getMaxHealth());
			}
			if (time > 100) {
				// p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj
						.spawnParticle(
								"heart",
								(p.posX + (p.worldObj.rand.nextFloat()
										* p.width * 2.0F))
										- p.width,
								p.posY
										+ 0.5D
										+ (p.worldObj.rand.nextFloat() * p.height),
								(p.posZ + (p.worldObj.rand.nextFloat()
										* p.width * 2.0F))
										- p.width, var2, var4, var6);
				int heal = time / 30;
				if ((p.getHealth() + heal) <= p.getMaxHealth())
					p.heal(heal);
				else
					p.setHealth(p.getMaxHealth());
			}
		}
	}
}
