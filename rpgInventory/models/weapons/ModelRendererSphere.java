/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.models.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


/**
 *
 * @author Home
 */
public class ModelRendererSphere extends ModelRenderer {
    int textureOffsetX;
    int textureOffsetY;
    public ModelRendererSphere(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public ModelRendererSphere(ModelBase par1ModelBase, String par2Str) {
        super(par1ModelBase, par2Str);
    }

    public ModelRendererSphere(ModelBase par1ModelBase, int par2, int par3) {
        super(par1ModelBase, par2, par3);
    }

    public ModelRenderer addSphere(float par1, float par2, float par3, int par4, int par5, int par6) {
        this.cubeList.add(new ModelSphere(this, this.textureOffsetX, this.textureOffsetY, par1, par2, par3, par4, par5, par6, 0.0F));
        return this;
    }
    
    public ModelRenderer setTextureOffset(int par1, int par2)
    {
        this.textureOffsetX = par1;
        this.textureOffsetY = par2;
        return this;
    }
}
