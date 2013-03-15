package RpgRB.weapons.axe;

import RpgInventory.RichTools.Targetting;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemBeastAxe extends Item {
    private List<Class> pettypes = Arrays.asList(new Class[]{EntityPig.class, EntityCow.class, EntitySpider.class, EntityCaveSpider.class});
    private boolean charming;
    private int charmTime;
    private int particleTime;
    public ItemBeastAxe(int par1) {
        super(par1);
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? 15f : super.getStrVsBlock(par1ItemStack, par2Block);
    }

    public boolean canHarvestBlock(Block par2Block) {
        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? true : false;
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 6);
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
        par1ItemStack.damageItem(1, par7EntityLiving);
        return true;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer par3EntityPlayer) {
        if (world.isRemote) {
            EntityLiving el = Targetting.isTargetingLivingEntity(4.0D);
            if(el != null && pettypes.contains(el.getClass())){
                charming = true;
            }else{
                charming = false;
            }
        }
        return super.onItemRightClick(par1ItemStack, world, par3EntityPlayer);
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
        if(charming){
            charmTime++;
            particleTime++;
        }else{
            charmTime = 0;
            particleTime = 0;
        }
        if(particleTime >= 10){
            particleTime = 0;
            //TODO:Charming Particles
        }
        if(charmTime >= 75){
            charmTime = 0;
            //TODO:Charm attempt
        }
        
    }
}
