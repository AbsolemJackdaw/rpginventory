package addonDread.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import rpgInventory.item.ItemRpgSword;

public class ItemPaladinSword extends ItemRpgSword {

	public ItemPaladinSword(int par1, ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase) {

		if(par2EntityLivingBase instanceof IMob){
			if(par2EntityLivingBase.getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD)){
				par1ItemStack.damageItem(-1, par3EntityLivingBase);
			}
		}

		return super.hitEntity(par1ItemStack, par2EntityLivingBase,
				par3EntityLivingBase);
	}
}
