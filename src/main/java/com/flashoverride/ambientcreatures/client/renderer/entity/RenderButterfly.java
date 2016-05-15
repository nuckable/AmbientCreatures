package com.flashoverride.ambientcreatures.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.flashoverride.ambientcreatures.client.model.ModelButterfly;
import com.flashoverride.ambientcreatures.entity.passive.EntityButterfly;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderButterfly extends RenderLiving
{
	private static final ResourceLocation butterflyTexture0 = new ResourceLocation("ambientcreatures", "textures/entity/butterfly0.png");
	private static final ResourceLocation butterflyTexture1 = new ResourceLocation("ambientcreatures", "textures/entity/butterfly1.png");


	private int renderedButterflySize;
  
  

  public RenderButterfly()
  {
    super(new ModelButterfly(), 0.15F);
    this.renderedButterflySize = ((ModelButterfly)this.mainModel).getButterflySize();
  }
  






  public void doRender(EntityButterfly p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    int i = ((ModelButterfly)this.mainModel).getButterflySize();
    
    if (i != this.renderedButterflySize)
    {
      this.renderedButterflySize = i;
      this.mainModel = new ModelButterfly();
    }
    
    super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  



  protected ResourceLocation getEntityTexture(EntityButterfly entity)
  {
	  switch (entity.getSkin())
	  {
	  case 0:
	  default:
		  return butterflyTexture0;
	  case 1:
		  return butterflyTexture1;
	  }
  }
  




  protected void preRenderCallback(EntityButterfly p_77041_1_, float p_77041_2_)
  {
    GL11.glScalef(0.35F, 0.35F, 0.35F);
  }
  



  protected void renderLivingAt(EntityButterfly p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_)
  {
    super.renderLivingAt(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
  }
  
  protected void rotateCorpse(EntityButterfly p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
  {
    GL11.glTranslatef(0.0F, -0.1F, 0.0F);

    
    super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  






  public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    doRender((EntityButterfly)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  




  protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
  {
    preRenderCallback((EntityButterfly)p_77041_1_, p_77041_2_);
  }
  
  protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
  {
    rotateCorpse((EntityButterfly)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  



  protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_)
  {
    renderLivingAt((EntityButterfly)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
  }
  






  public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    doRender((EntityButterfly)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  



  protected ResourceLocation getEntityTexture(Entity p_110775_1_)
  {
    return getEntityTexture((EntityButterfly)p_110775_1_);
  }
  






  public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    doRender((EntityButterfly)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}
