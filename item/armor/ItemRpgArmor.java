package RpgInventory.item.armor;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import RpgInventory.AARpgBaseClass;
import RpgInventory.gui.inventory.RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemRpgArmor extends Item
{


	/** Holds the 'base' maxDamage that each armorType have. */
	private final int[] maxDamageArray = new int[] {30, 20, 50, 20, 30, 30};
	/**
	 * Stores the armor type: 0 is necklace, 2 is cloak, 1 is shield, 3 is gloves, 4 are rings
	 */
	public int armorType;

	/**
	 * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
	 * iron, 3 is diamond and 4 is gold.
	 */
	public int renderJewelIndex;

	public ItemRpgArmor(int par1, int par4)
	{
		super(par1);
		this.armorType = par4;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);

	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		if(par1ItemStack.itemID == AARpgBaseClass.cloakI.itemID)
		{
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if(par1ItemStack.itemID == AARpgBaseClass.cloak.itemID)
			return 16777215;
		if(par1ItemStack.itemID == AARpgBaseClass.cloakRed.itemID)
			return 0xd2120e;
		if(par1ItemStack.itemID == AARpgBaseClass.cloakGreen.itemID)
			return 0x0fb15d;
		if(par1ItemStack.itemID == AARpgBaseClass.cloakYellow.itemID)
			return 0xf7cd09;
		if(par1ItemStack.itemID == AARpgBaseClass.cloakSub.itemID)
			return 0x440001;
		if(par1ItemStack.itemID == AARpgBaseClass.cloakBlue.itemID)
			return 0x291ef6;

		return 16777215;
	}
	public void armorEffects(ItemStack is, EntityPlayer player,RpgInventory rpg)
	{

		ItemStack col    = rpg.getJewelInSlot(0);
		ItemStack shield = rpg.getJewelInSlot(1);
		ItemStack cloak  = rpg.getJewelInSlot(2);
		ItemStack want   = rpg.getJewelInSlot(3);
		ItemStack ringb  = rpg.getJewelInSlot(4);
		ItemStack ringa  = rpg.getJewelInSlot(5);

		/** Single Item Checks and emeralds*/
		if(col != null && col.getItem() == AARpgBaseClass.neckem)
		{this.effectSwitch(12, player, is);}

		if(want != null && want.getItem() == AARpgBaseClass.glovesem)
		{this.effectSwitch(15, player, is);}

		if(ringa != null && ringa.getItem() == AARpgBaseClass.ringem)
		{this.effectSwitch(12, player, is);}

		if(ringb != null && ringb.getItem()== AARpgBaseClass.ringem)
		{this.effectSwitch(14, player, is);}

		if(col !=null&&col.itemID == AARpgBaseClass.neckgold.itemID ||ringa != null &&ringa.getItem() == AARpgBaseClass.ringgold ||ringb != null &&ringb.getItem() == AARpgBaseClass.ringgold||want != null && want.getItem() == AARpgBaseClass.glovesbutter)
		{this.effectSwitch(0, player, is);}
		if(col !=null&&col.itemID == AARpgBaseClass.neckdia.itemID ||ringa != null &&ringa.getItem() == AARpgBaseClass.ringdia ||ringb != null &&ringb.getItem() == AARpgBaseClass.ringdia||want != null && want.getItem() == AARpgBaseClass.glovesdia)
		{this.effectSwitch(4, player, is);}
		if(col !=null&&col.itemID == AARpgBaseClass.necklap.itemID ||ringa != null &&ringa.getItem() == AARpgBaseClass.ringlap ||ringb != null &&ringb.getItem() == AARpgBaseClass.ringlap||want != null && want.getItem() == AARpgBaseClass.gloveslap)
		{this.effectSwitch(8, player, is);}

		/**Butterrrr*/    		
		if(ringa != null && ringa.getItem() == AARpgBaseClass.ringgold && ringb != null && ringb.getItem()== AARpgBaseClass.ringgold 
				||want != null && want.getItem() == AARpgBaseClass.glovesbutter&& ringa != null && ringa.getItem() == AARpgBaseClass.ringgold
				||want != null && want.getItem() == AARpgBaseClass.glovesbutter&& ringb != null && ringb.getItem() == AARpgBaseClass.ringgold
				||want != null && want.getItem() == AARpgBaseClass.glovesbutter&&   col != null && col.getItem() == AARpgBaseClass.neckgold
				||col != null && col.getItem() == AARpgBaseClass.neckgold && ringb != null && ringb.getItem() == AARpgBaseClass.ringgold
				||col != null && col.getItem() == AARpgBaseClass.neckgold && ringa != null && ringa.getItem() == AARpgBaseClass.ringgold)
		{ this.effectSwitch(1, player, is);}

		if(col != null &&col.getItem() == AARpgBaseClass.neckgold && want != null &&want.getItem()== AARpgBaseClass.glovesbutter && ringa != null &&ringa.getItem() == AARpgBaseClass.ringgold
				||col != null &&col.getItem() == AARpgBaseClass.neckgold && want != null &&want.getItem()== AARpgBaseClass.glovesbutter && ringb != null &&ringb.getItem() == AARpgBaseClass.ringgold
				||col != null &&col.getItem() == AARpgBaseClass.neckgold && ringa != null &&ringa.getItem()== AARpgBaseClass.ringgold && ringb != null &&ringb.getItem() == AARpgBaseClass.ringgold
				||want != null &&want.getItem() == AARpgBaseClass.glovesbutter && ringb != null &&ringb.getItem()== AARpgBaseClass.ringgold && ringa != null &&ringa.getItem() == AARpgBaseClass.ringgold)
		{ this.effectSwitch(2, player, is);}

		if(want != null && want.getItem() == AARpgBaseClass.glovesbutter &&col != null &&col.getItem() == AARpgBaseClass.neckgold && ringa != null &&ringa.getItem()== AARpgBaseClass.ringgold && ringb != null &&ringb.getItem() == AARpgBaseClass.ringgold)
		{this.effectSwitch(3, player, is);}

		/**Shiny diamonds*/    		
		if(ringa != null && ringa.getItem() == AARpgBaseClass.ringdia && ringb != null && ringb.getItem()== AARpgBaseClass.ringdia 
				||want != null && want.getItem() == AARpgBaseClass.glovesdia&& ringa != null && ringa.getItem() == AARpgBaseClass.ringdia
				||want != null && want.getItem() == AARpgBaseClass.glovesdia&& ringb != null && ringb.getItem() == AARpgBaseClass.ringdia
				||want != null && want.getItem() == AARpgBaseClass.glovesdia&&   col != null && col.getItem() == AARpgBaseClass.neckdia
				||col != null && col.getItem() == AARpgBaseClass.neckdia && ringb != null && ringb.getItem() == AARpgBaseClass.ringdia
				||col != null && col.getItem() == AARpgBaseClass.neckdia && ringa != null && ringa.getItem() == AARpgBaseClass.ringdia)
		{ this.effectSwitch(5, player, is);}

		if(col != null &&col.getItem() == AARpgBaseClass.neckdia && want != null &&want.getItem()== AARpgBaseClass.glovesdia && ringa != null &&ringa.getItem() == AARpgBaseClass.ringdia
				||col != null &&col.getItem() == AARpgBaseClass.neckdia && want != null &&want.getItem()== AARpgBaseClass.glovesdia && ringb != null &&ringb.getItem() == AARpgBaseClass.ringdia
				||col != null &&col.getItem() == AARpgBaseClass.neckdia && ringa != null &&ringa.getItem()== AARpgBaseClass.ringdia && ringb != null &&ringb.getItem() == AARpgBaseClass.ringdia
				||want != null &&want.getItem() == AARpgBaseClass.glovesdia && ringb != null &&ringb.getItem()== AARpgBaseClass.ringdia && ringa != null &&ringa.getItem() == AARpgBaseClass.ringdia)
		{ this.effectSwitch(6, player, is);}

		if(want != null && want.getItem() == AARpgBaseClass.glovesdia &&col != null &&col.getItem() == AARpgBaseClass.neckdia && ringa != null &&ringa.getItem()== AARpgBaseClass.ringdia && ringb != null &&ringb.getItem() == AARpgBaseClass.ringdia)
		{this.effectSwitch(7, player, is);}

		/**lapis*/
		if(ringa != null && ringa.getItem() == AARpgBaseClass.ringlap && ringb != null && ringb.getItem()== AARpgBaseClass.ringlap 
				||want != null && want.getItem() == AARpgBaseClass.gloveslap&& ringa != null && ringa.getItem() == AARpgBaseClass.ringlap
				||want != null && want.getItem() == AARpgBaseClass.gloveslap&& ringb != null && ringb.getItem() == AARpgBaseClass.ringlap
				||want != null && want.getItem() == AARpgBaseClass.gloveslap&&   col != null && col.getItem() == AARpgBaseClass.neckgold
				||col != null && col.getItem() == AARpgBaseClass.necklap && ringb != null && ringb.getItem() == AARpgBaseClass.ringlap
				||col != null && col.getItem() == AARpgBaseClass.necklap && ringa != null && ringa.getItem() == AARpgBaseClass.ringlap)
		{ this.effectSwitch(9, player, is);}

		if(col != null &&col.getItem() == AARpgBaseClass.necklap && want != null &&want.getItem()== AARpgBaseClass.gloveslap && ringa != null &&ringa.getItem() == AARpgBaseClass.ringlap
				||col != null &&col.getItem() == AARpgBaseClass.necklap && want != null &&want.getItem()== AARpgBaseClass.gloveslap && ringb != null &&ringb.getItem() == AARpgBaseClass.ringlap
				||col != null &&col.getItem() == AARpgBaseClass.necklap && ringa != null &&ringa.getItem()== AARpgBaseClass.ringlap && ringb != null &&ringb.getItem() == AARpgBaseClass.ringlap
				||want != null &&want.getItem() == AARpgBaseClass.gloveslap && ringb != null &&ringb.getItem()== AARpgBaseClass.ringlap && ringa != null &&ringa.getItem() == AARpgBaseClass.ringlap)
		{ this.effectSwitch(10, player, is);}

		if(want != null && want.getItem() == AARpgBaseClass.gloveslap &&col != null &&col.getItem() == AARpgBaseClass.necklap && ringa != null &&ringa.getItem()== AARpgBaseClass.ringlap && ringb != null &&ringb.getItem() == AARpgBaseClass.ringgold)
		{this.effectSwitch(11, player, is);}

		if(shield != null )
		{
			if(shield.itemID == AARpgBaseClass.shieldwood.itemID)
			{ 	this.effectSwitch(16, player, is);
			}
			if(shield.itemID == AARpgBaseClass.shieldiron.itemID)
			{ 	this.effectSwitch(17, player, is);
			}
			if(shield.itemID == AARpgBaseClass.shieldgold.itemID)
			{ 	this.effectSwitch(18, player, is);
			}
		}
		if(cloak != null )
		{
			if(cloak.itemID == AARpgBaseClass.cloakI.itemID)
			{ 	this.effectSwitch(19, player, is);
			}
			if(cloak.itemID == AARpgBaseClass.cloak.itemID)
			{ 	

			}
		}

		if(player.getHealth() <= 0)
		{
			dropJewels(player);
		}
		if(player.isDead)
		{
			dropJewels(player);
		}
	}

	/**called upon player's death. Will drop Jewels in the world*/
	public void dropJewels(EntityPlayer player)
	{
		RpgInventory rpg = AARpgBaseClass.proxy.getInventory(player.username);

		int var1;
		for (var1 = 0; var1 < rpg.armorSlots.length; ++var1)
		{
			if (rpg.armorSlots[var1] != null)
			{
				player.dropPlayerItemWithRandomChoice(rpg.armorSlots[var1], true);
				rpg.armorSlots[var1] = null;
			}
		}
	}
	/** gets the desired effect for jewelry of a certain kind. 0-3 speed boost
	 * 4-7 healing, 8-11 damage and weapon healing, 12-15 emerald effects, 16-19 shield n cloak*/ 

	public void effectSwitch (int id, EntityPlayer player, ItemStack is)
	{
		switch(id)
		{
		case 0:   player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20,0));
		break;
		case 1:	  player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20,0));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 0));
		break;
		case 2:   player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20,1));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 0));
		break;
		case 3:	  player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20,1));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 1));
		break;
		case 4: this.getRpgEffect(is,200,player);break;
		case 5: this.getRpgEffect(is,200,player);break;
		case 6: this.getRpgEffect(is,200,player);break;
		case 7: this.getRpgEffect(is,200,player);break;
		case 8:player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20,0));
		case 9:player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20,0));
		this.healWeapon(player,100);break;
		case 10:player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20,1));
		this.healWeapon(player,100);break;
		case 11:player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20,1));
		this.healWeapon(player,50);break;
		case 12: getMilkEffect(is, 400, player);break;
		case 13:player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20, 1));break;
		case 14:player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20,1));break;
		case 15:player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 0));break;
		case 16:player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 0));break;
		case 17:player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 1));break;
		case 18:player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 2));break;
		case 19:player.addPotionEffect(new PotionEffect(Potion.invisibility.id,20,1));break;

		}
	}

	/** effect obtained when having equiped all diamond items.
	 * gets a tagcompound who keeps track of the ticks, and heals the player 
	 * when reached 0*/
	public void getRpgEffect(ItemStack itemstack, int duration, EntityPlayer player)
	{
		RpgInventory rpg = new RpgInventory(player);

		if( itemstack.stackTagCompound == null ) {
			itemstack.setTagCompound( new NBTTagCompound() );
		}

		if( !itemstack.stackTagCompound.hasKey( "healer" ) ) {
			itemstack.stackTagCompound.setInteger( "healer", duration );
			return;
		}

		int currentTick = itemstack.stackTagCompound.getInteger( "healer" );

		if( player.getHealth() < 20 ) {
			currentTick--;
			System.out.println(currentTick);
			if( currentTick == 0 ) {
				currentTick = duration;
				if(player.getHealth() < 20  ){
					player.heal(1);
				}
			}
		}
		else
			currentTick = duration;
		itemstack.stackTagCompound.setInteger( "healer", currentTick );

	}
	/** an effect that simulates drinking from a milk bucket, but only for debuffs.*/
	public void getMilkEffect(ItemStack itemstack, int time, EntityPlayer player)
	{
		RpgInventory rpg = new RpgInventory(player);

		if( itemstack.stackTagCompound == null ) {
			itemstack.setTagCompound( new NBTTagCompound() );
		}
		if( !itemstack.stackTagCompound.hasKey( "milkEffect" ) ) {
			itemstack.stackTagCompound.setInteger( "milkEffect", time );
			return;
		}
		int currentTick = itemstack.stackTagCompound.getInteger( "milkEffect" );
		currentTick--;
		System.out.println(currentTick);

		if( currentTick == 0 )
		{
			currentTick = time;
			player.removePotionEffect(Potion.moveSlowdown.id);
			player.removePotionEffect(Potion.blindness.id);
			player.removePotionEffect(Potion.confusion.id);
			player.removePotionEffect(Potion.digSlowdown.id);
			player.removePotionEffect(Potion.wither.id);
			player.removePotionEffect(Potion.harm.id);
			player.removePotionEffect(Potion.poison.id);
		}

		else
			currentTick = time;
		itemstack.stackTagCompound.setInteger( "milkEffect", currentTick );
	}

	public void healWeapon(EntityPlayer player, int chances)
	{
		ItemStack get = player.inventory.getCurrentItem();
		Random rand = new Random();

		int k = rand.nextInt(chances);
		if(k == chances/2 && get != null && !player.worldObj.isRemote)
		{
			if(get.itemID == Item.swordDiamond.itemID || get.itemID == Item.swordGold.itemID 
					|| get.itemID == Item.swordSteel.itemID || get.itemID == Item.swordStone.itemID || get.itemID == Item.swordWood.itemID)
			{
				int dam = get.getItemDamage();
				if(dam > 0){
					dam -= 2;
				}
				if (dam < 0){
					dam = 0;
				}
				get.setItemDamage(dam);
			}
		}

	}
	/**
	 * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
	 * multiplied by armor material factor)
	 */
	int[] getMaxDamageArray()
	{
		return maxDamageArray;
	}

	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}


}
