/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.renders;

import RpgInventory.IPet;
import RpgRB.beastmaster.BMPetImpl;
import RpgRB.beastmaster.BullPet;
import RpgRB.models.ModelBull;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class RenderPet extends RenderLiving {

    public RenderPet() {
        super(new ModelCow(), 0.5F);
    }

    protected void scalePet(BMPetImpl pet, float par2) {
        float var3 = pet.getPetSize();
        GL11.glScalef(var3, var3, var3);
        
    }

    protected void preRenderCallback(EntityLiving pet, float par2) {
        this.scalePet((BMPetImpl) pet, par2);
    }

    public void renderCow(BMPetImpl pet, double par2, double par4, double par6, float par8, float par9) {
       
    	this.mainModel = pet.getModel();
    	super.doRenderLiving(pet, par2, par4, par6, par8, par9);
        super.renderLivingLabel(pet, pet.getEntityName() + " Lvl." + pet.getLevel(), par2, par4, par6, 32);      

    }

    public void doRenderLiving(EntityLiving pet, double par2, double par4, double par6, float par8, float par9) {
        this.renderCow((BMPetImpl) pet, par2, par4, par6, par8, par9);

    }

    public void doRender(Entity pet, double par2, double par4, double par6, float par8, float par9) {
        this.renderCow((BMPetImpl) pet, par2, par4, par6, par8, par9);
    }
}
