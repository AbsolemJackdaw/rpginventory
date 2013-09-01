package rpgInventory.models.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GrandSword extends ModelBase
{
  //fields
    ModelRenderer Part1;
    ModelRenderer Part2;
    ModelRenderer Part3;
    ModelRenderer Part4;
    ModelRenderer Part5;
    ModelRenderer Part6;
    ModelRenderer Part8;
    ModelRenderer Part13;
    ModelRenderer Part14;
    ModelRenderer Part15;
    ModelRenderer Part16;
    ModelRenderer Part17;
    ModelRenderer Part18;
    ModelRenderer Part19;
    ModelRenderer Part20;
    ModelRenderer Part21;
    ModelRenderer Part22;
    ModelRenderer Part23;
    ModelRenderer Part24;
    ModelRenderer Part25;
    ModelRenderer Part26;
    ModelRenderer Part27;
  
  public GrandSword()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      Part1 = new ModelRenderer(this, 115, 0);
      Part1.addBox(-1.5F, -19F, -0.5F, 3, 22, 1);
      Part1.setRotationPoint(0F, 0F, 0F);
      Part1.setTextureSize(64, 32);
      Part1.mirror = true;
      setRotation(Part1, 0F, 0F, 0F);
      Part2 = new ModelRenderer(this, 105, 0);
      Part2.addBox(-1F, -18F, -0.8F, 2, 21, 1);
      Part2.setRotationPoint(0F, 0F, 0F);
      Part2.setTextureSize(64, 32);
      Part2.mirror = true;
      setRotation(Part2, 0F, 0F, 0F);
      Part3 = new ModelRenderer(this, 90, 0);
      Part3.addBox(-2F, -18F, -0.5F, 4, 21, 1);
      Part3.setRotationPoint(0F, 0F, 0F);
      Part3.setTextureSize(64, 32);
      Part3.mirror = true;
      setRotation(Part3, 0F, 0F, 0F);
      Part4 = new ModelRenderer(this, 80, 0);
      Part4.addBox(-1.5F, -17F, -1F, 1, 20, 2);
      Part4.setRotationPoint(0F, 0F, 0F);
      Part4.setTextureSize(64, 32);
      Part4.mirror = true;
      setRotation(Part4, 0F, 0F, 0F);
      Part5 = new ModelRenderer(this, 81, 27);
      Part5.addBox(-0.5F, -19.6F, -0.5F, 1, 1, 1);
      Part5.setRotationPoint(0F, 0F, 0F);
      Part5.setTextureSize(64, 32);
      Part5.mirror = true;
      setRotation(Part5, 0F, 0F, 0F);
      Part6 = new ModelRenderer(this, 65, 0);
      Part6.addBox(-2.5F, -16F, -0.5F, 5, 19, 1);
      Part6.setRotationPoint(0F, 0F, 0F);
      Part6.setTextureSize(64, 32);
      Part6.mirror = true;
      setRotation(Part6, 0F, 0F, 0F);
      Part8 = new ModelRenderer(this, 80, 0);
      Part8.addBox(0.5F, -17F, -1F, 1, 20, 2);
      Part8.setRotationPoint(0F, 0F, 0F);
      Part8.setTextureSize(64, 32);
      Part8.mirror = true;
      setRotation(Part8, 0F, 0F, 0F);
      Part13 = new ModelRenderer(this, 105, 0);
      Part13.addBox(-1F, -18F, -0.2F, 2, 21, 1);
      Part13.setRotationPoint(0F, 0F, 0F);
      Part13.setTextureSize(64, 32);
      Part13.mirror = true;
      setRotation(Part13, 0F, 0F, 0F);
      Part14 = new ModelRenderer(this, 50, 0);
      Part14.addBox(-2F, -16F, -0.8F, 4, 19, 1);
      Part14.setRotationPoint(0F, 0F, 0F);
      Part14.setTextureSize(64, 32);
      Part14.mirror = true;
      setRotation(Part14, 0F, 0F, 0F);
      Part15 = new ModelRenderer(this, 50, 0);
      Part15.addBox(-2F, -16F, -0.2F, 4, 19, 1);
      Part15.setRotationPoint(0F, 0F, 0F);
      Part15.setTextureSize(64, 32);
      Part15.mirror = true;
      setRotation(Part15, 0F, 0F, 0F);
      Part16 = new ModelRenderer(this, 70, 27);
      Part16.addBox(-1F, -19.3F, -0.5F, 2, 1, 1);
      Part16.setRotationPoint(0F, 0F, 0F);
      Part16.setTextureSize(64, 32);
      Part16.mirror = true;
      setRotation(Part16, 0F, 0F, 0F);
      Part17 = new ModelRenderer(this, 60, 27);
      Part17.addBox(-1.8F, -18.6F, -0.5F, 3, 1, 1);
      Part17.setRotationPoint(0F, 0F, 0F);
      Part17.setTextureSize(64, 32);
      Part17.mirror = true;
      setRotation(Part17, 0F, 0F, 0F);
      Part18 = new ModelRenderer(this, 55, 27);
      Part18.addBox(0.8F, -18.6F, -0.5F, 1, 1, 1);
      Part18.setRotationPoint(0F, 0F, 0F);
      Part18.setTextureSize(64, 32);
      Part18.mirror = true;
      setRotation(Part18, 0F, 0F, 0F);
      Part19 = new ModelRenderer(this, 35, 27);
      Part19.addBox(-2.2F, -17.5F, -0.5F, 1, 2, 1);
      Part19.setRotationPoint(0F, 0F, 0F);
      Part19.setTextureSize(64, 32);
      Part19.mirror = true;
      setRotation(Part19, 0F, 0F, 0F);
      Part20 = new ModelRenderer(this, 35, 27);
      Part20.addBox(0F, -17.5F, -0.5F, 1, 2, 1);
      Part20.setRotationPoint(1.2F, 0F, 0F);
      Part20.setTextureSize(64, 32);
      Part20.mirror = true;
      setRotation(Part20, 0F, 0F, 0F);
      Part21 = new ModelRenderer(this, 45, 27);
      Part21.addBox(-1F, -18.6F, -0.6F, 2, 1, 1);
      Part21.setRotationPoint(0F, 0F, 0F);
      Part21.setTextureSize(64, 32);
      Part21.mirror = true;
      setRotation(Part21, 0F, 0F, 0F);
      Part22 = new ModelRenderer(this, 20, 27);
      Part22.addBox(-2F, -17.3F, -0.8F, 4, 2, 1);
      Part22.setRotationPoint(0F, 0F, 0F);
      Part22.setTextureSize(64, 32);
      Part22.mirror = true;
      setRotation(Part22, 0F, 0F, 0F);
      Part23 = new ModelRenderer(this, 30, 0);
      Part23.addBox(-3F, 3F, -1F, 6, 1, 2);
      Part23.setRotationPoint(0F, 0F, 0F);
      Part23.setTextureSize(64, 32);
      Part23.mirror = true;
      setRotation(Part23, 0F, 0F, 0F);
      Part24 = new ModelRenderer(this, 30, 5);
      Part24.addBox(-3.5F, 3F, -0.5F, 7, 1, 1);
      Part24.setRotationPoint(0F, 0F, 0F);
      Part24.setTextureSize(64, 32);
      Part24.mirror = true;
      setRotation(Part24, 0F, 0F, 0F);
      Part25 = new ModelRenderer(this, 30, 10);
      Part25.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
      Part25.setRotationPoint(0F, 0F, 0F);
      Part25.setTextureSize(64, 32);
      Part25.mirror = true;
      setRotation(Part25, 0F, 0F, 0F);
      Part26 = new ModelRenderer(this, 30, 10);
      Part26.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
      Part26.setRotationPoint(0F, 0F, 0F);
      Part26.setTextureSize(64, 32);
      Part26.mirror = true;
      setRotation(Part26, 0F, 0.4089647F, 0F);
      Part27 = new ModelRenderer(this, 30, 10);
      Part27.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
      Part27.setRotationPoint(0F, 0F, 0F);
      Part27.setTextureSize(64, 32);
      Part27.mirror = true;
      setRotation(Part27, 0F, 0.9294653F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Part1.render(f5);
    Part2.render(f5);
    Part3.render(f5);
    Part4.render(f5);
    Part5.render(f5);
    Part6.render(f5);
    Part8.render(f5);
    Part13.render(f5);
    Part14.render(f5);
    Part15.render(f5);
    Part16.render(f5);
    Part17.render(f5);
    Part18.render(f5);
    Part19.render(f5);
    Part20.render(f5);
    Part21.render(f5);
    Part22.render(f5);
    Part23.render(f5);
    Part24.render(f5);
    Part25.render(f5);
    Part26.render(f5);
    Part27.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }

}
