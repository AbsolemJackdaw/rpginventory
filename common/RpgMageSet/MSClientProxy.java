/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgMageSet;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgMageSet.weapons.EntityElementalBlock;
import RpgMageSet.weapons.RenderElementalBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;


/**
 *
 * @author Home
 */
public class MSClientProxy extends MSCommonProxy{
    public void registerRendering(){
    	 MinecraftForgeClient.preloadTexture("/subaraki/weapons/Fire.png");
    	RenderingRegistry.registerEntityRenderingHandler(EntityElementalBlock.class, new RenderElementalBlock());
    	
    }
}