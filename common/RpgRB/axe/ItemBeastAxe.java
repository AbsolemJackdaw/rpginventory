package RpgRB.axe;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemBeastAxe extends ItemSword {

	public ItemBeastAxe(int par1) {
		super(par1, EnumToolMaterial.IRON);
		// TODO Auto-generated constructor stub
	}
	
	public String getTextureFile() {
		return "/subaraki/RPGinventoryTM.png";
	}

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if(!world.isRemote){
            System.out.println("Server Triggered.");
        }else {
            List<EntityLiving> elist = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(par4 - 0.5F, par5 - 0.5F, par6 - 0.5F, par4 + 0.5F, par5 + 0.5F, par6 + 0.5F));
            System.out.println(elist);
            if(elist != null && elist.size() > 0){
                for(EntityLiving el:elist){
                    if(el.getClass() == EntityPig.class){
                        return true;
                    }else if(el.getClass() == EntityCow.class){
                        return true;
                    }else if(el.getClass() == EntitySpider.class || el.getClass() == EntityCaveSpider.class){
                        return true;
                    }
                }
            }
        }
        return super.onItemUse(par1ItemStack, par2EntityPlayer, world, par4, par5, par6, par7, par8, par9, par10);
    }
        
        
}
