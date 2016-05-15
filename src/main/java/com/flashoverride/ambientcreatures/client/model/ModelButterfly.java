package com.flashoverride.ambientcreatures.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.flashoverride.ambientcreatures.entity.passive.EntityButterfly;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelButterfly
  extends ModelBase
{
	ModelRenderer body;
	ModelRenderer body_wingLeft;
	ModelRenderer body_wingRight;
	
    protected double distanceMovedTotal = 0.0D;

    protected static final double CYCLES_PER_BLOCK = 0.25D;
    protected int cycleIndex = new Random().nextInt(6);
    protected float[][] flappingCycle = new float[][]
    {
            { 10F, -60F, -120F, 0F, -10F },
            { 0F, -15F, -165F, -5F, -5F },
            { -15F, 30F, 150F, -10F, 0F },
            { -15F, 30F, 150F, -10F, 0F },
            { 0F, -15F, -165F, -5F, -5F },
            { 10F, -60F, -120F, 0F, -10F },
    };
    
	public ModelButterfly()
	{
	     textureWidth = 64;
	     textureHeight = 32;
	     setTextureOffset("antennaLeft.antennaLeft", 15, 0);
	     setTextureOffset("antennaRight.antennaRight", 15, 0);
	     setTextureOffset("wingRight.wingRight", 6, 0);
	     setTextureOffset("wingLeft.wingLeft", 6, 0);

	     body = new ModelRenderer(this,"body");
	     body.addBox(-1F, -1F, -4F, 2, 2, 12);
	     body.setRotationPoint(0F ,0F, 0F);
	     setRotation(body, 0F, 0F, 0F);

	     ModelRenderer body_antennaLeft = new ModelRenderer(this,"antennaLeft");
	     body_antennaLeft.addBox("antennaLeft", 0F, 0F, -4F, 4, 0, 4);
	     body_antennaLeft.setRotationPoint(0F, -0.5F, -4F);
	     setRotation(body_antennaLeft, 0F, 0F, -0.4886922F);
	     body.addChild(body_antennaLeft);

	     ModelRenderer body_antennaRight = new ModelRenderer(this,"antennaRight");
	     body_antennaRight.addBox("antennaRight", 0F, 0F, -4F, 4, 0, 4);
	     body_antennaRight.setRotationPoint(0F, -0.5F, -4F);
	     setRotation(body_antennaRight, 0F, 0F, -2.6529F);
	     body.addChild(body_antennaRight);

	     body_wingRight = new ModelRenderer(this,"wingRight");
	     body_wingRight.addBox("wingRight", 1F, 0F, -6F, 16, 0, 24);
	     body_wingRight.setRotationPoint(0F, 0F, -2F);
	     setRotation(body_wingRight, 0F, 0F, -2.094395F);
	     body.addChild(body_wingRight);

	     body_wingLeft = new ModelRenderer(this,"wingLeft");
	     body_wingLeft.addBox("wingLeft", 1F, 0F, -7F, 16, 0, 24);
	     body_wingLeft.setRotationPoint(0F, 0F, -1F);
	     setRotation(body_wingLeft, 0F, 0F, -1.047198F);
	     body.addChild(body_wingLeft);
	}

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
     model.rotateAngleX = x;
     model.rotateAngleY = y;
     model.rotateAngleZ = z;
  }

  public int getButterflySize()
  {
     return 36;
  }

  public void render(Entity entity, float time, float swingSuppress, float p_78088_4_, float headAngleY, float headAngleX, float p_78088_7_)
  {
     EntityButterfly entitybutterfly = (EntityButterfly)entity;
     
     setRotationAngles(time, swingSuppress, p_78088_4_, headAngleY, headAngleX, p_78088_7_, entitybutterfly);

       GL11.glPushMatrix();
       GL11.glScalef(0.5F, 0.5F,  0.5F);
       
       

       this.body.render(p_78088_7_);

       GL11.glPopMatrix();
   }

   @Override
   public void setRotationAngles(float time, float swingSuppress, float par3, float headAngleY, float headAngleX, float par6, Entity entitybutterfly)
   {
       // animate if moving        
       updateDistanceMovedTotal(entitybutterfly);
       
       cycleIndex = (int) ((getDistanceMovedTotal(entitybutterfly)*CYCLES_PER_BLOCK) %flappingCycle.length);
       
       body.rotateAngleX = degToRad(flappingCycle[cycleIndex][0]) ;
       body_wingLeft.rotateAngleZ = degToRad(flappingCycle[cycleIndex][1]) ;
       body_wingRight.rotateAngleZ = degToRad(flappingCycle[cycleIndex][2]) ;
       body_wingLeft.rotateAngleX = degToRad(flappingCycle[cycleIndex][3]) ;
       body_wingRight.rotateAngleX = degToRad(flappingCycle[cycleIndex][4]) ;
   }

   protected void updateDistanceMovedTotal(Entity parEntity) 
   {
//       distanceMovedTotal += Math.max(parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ), 0.1D);
       distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ);
   }
   
   protected double getDistanceMovedTotal(Entity parEntity) 
   {
       return (distanceMovedTotal);
   }

   protected float degToRad(float degrees)
   {
       return degrees * (float)Math.PI / 180 ;
   }

}
