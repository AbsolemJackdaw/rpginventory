package RpgRB.weapons.axe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBeastAxe extends Item {

	public ItemBeastAxe(int par1) {
		super(par1);
	}

	public String getTextureFile() {
		return "/subaraki/RPGinventoryTM.png";
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? 15f : super.getStrVsBlock(par1ItemStack, par2Block);
	}
	public boolean canHarvestBlock(Block par2Block)
	{
		return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? true : false;	    
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		if(par2EntityLiving instanceof EntityMob)
		{
			
			return true;
		}
		return false;
	}
	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
	{
		par1ItemStack.damageItem(1, par7EntityLiving);
		return true;
	}
}
