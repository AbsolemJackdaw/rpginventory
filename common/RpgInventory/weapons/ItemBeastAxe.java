package RpgInventory.weapons;

import RpgInventory.EnumRpgClass;
import RpgInventory.RichTools.Targetting;
import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemBeastAxe extends ItemSword {

    private List<Class> pettypes = Arrays.asList(new Class[]{EntityPig.class, EntityCow.class, EntitySpider.class, EntityCaveSpider.class});
    private int charmTime;
    private int particleTime;
    private Random rng = new Random(1051414);

    public ItemBeastAxe(int par1) {
        super(par1, mod_RpgInventory.BeastAxeMaterial);
        this.maxStackSize = 1;
        this.setMaxDamage(1500);
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }

    public void func_94581_a(IconRegister par1IconRegister) {
        this.iconIndex = par1IconRegister.func_94245_a("RPGInventoryMod:forestAxe");
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
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
        if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.BEASTMASTER)) {
            World world = player != null ? player.worldObj : null;
            if (world!= null && world.isRemote) {
                Minecraft mc = Minecraft.getMinecraft();
                //Truer Randomization
                rng = new Random(rng.nextLong() + System.currentTimeMillis());
                EntityLiving el = Targetting.isTargetingLivingEntity(4.0D);
                if (el != null && pettypes.contains(el.getClass())) {
                    charmTime++;
                    particleTime++;
                } else {
                    charmTime = 0;
                    particleTime = 0;
                }

                if (particleTime >= 7) {
                    particleTime = 0;
                    net.minecraft.client.particle.EntityHeartFX efx = new net.minecraft.client.particle.EntityHeartFX(world, el.posX, el.posY + 0.5F + rng.nextFloat(), el.posZ, rng.nextFloat(), rng.nextFloat() + 0.4F, rng.nextFloat());
                    mc.effectRenderer.addEffect(efx);
                }
                if (charmTime >= 100) {
                    charmTime = 0;
                    float num = rng.nextFloat();
                    if (num > 0.80F) {
                        System.out.println("Charmed");
                        net.minecraft.client.particle.EntityLargeExplodeFX exfx = new net.minecraft.client.particle.EntityLargeExplodeFX(mc.renderEngine, world, el.posX, el.posY + 0.5F, el.posZ, rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
                        exfx.setRBGColorF(0F, 1.0F, 0F);
                        mc.effectRenderer.addEffect(exfx);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        DataOutputStream dos = new DataOutputStream(bos);
                        try {
                            dos.writeInt(11);
                            dos.writeInt(el.entityId);
                        } catch (Throwable ex) {
                        }
                        Packet250CustomPayload pcp = new Packet250CustomPayload("RpgInv", bos.toByteArray());
                        PacketDispatcher.sendPacketToServer(pcp);
                    } else {
                        System.out.println("Failed!");
                        net.minecraft.client.particle.EntityLargeExplodeFX exfx = new net.minecraft.client.particle.EntityLargeExplodeFX(mc.renderEngine, world, el.posX, el.posY + 0.5F, el.posZ, rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
                        exfx.setRBGColorF(1.0F, 0F, 0F);
                        mc.effectRenderer.addEffect(exfx);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        DataOutputStream dos = new DataOutputStream(bos);
                        try {
                            dos.writeInt(11);
                            dos.writeInt(0);
                        } catch (Throwable ex) {
                        }
                        Packet250CustomPayload pcp = new Packet250CustomPayload("RpgInv", bos.toByteArray());
                        PacketDispatcher.sendPacketToServer(pcp);
                    }
                }
            }
        }
        super.onUsingItemTick(stack, player, count);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        particleTime = 0;
        charmTime = 0;
        super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, par4);
    }
}
