package com.flashoverride.ambientcreatures.entity.passive;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;

public class EntityMoth extends EntityAmbientCreatureTFC
{
	  private ChunkCoordinates spawnPosition;
	  
	  public EntityMoth(World p_i1680_1_)
	  {
		   super(p_i1680_1_);
		    setSize(0.5F, 0.9F);
		    this.getNavigator().setAvoidsWater(true);
		    this.setSkin(this.worldObj.rand.nextInt(2));
		  }
		  
		  protected void entityInit()
		  {
		    super.entityInit();
		    this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
		  }
		  
			@Override
			public void onEntityUpdate()
			{
				super.onEntityUpdate();
				if (this.isBurning())
					this.attackEntityFrom(DamageSource.onFire, 50);
			}

		  protected float getSoundVolume()
		  {
		    return 0.1F;
		  }

		  protected float getSoundPitch()
		  {
		    return super.getSoundPitch() * 2.0F;
		  }

		  protected String getHurtSound()
		  {
		    return "mob.bat.hurt";
		  }

		  protected String getDeathSound()
		  {
		    return "mob.bat.death";
		  }

		  public boolean canBePushed()
		  {
		    return false;
		  }
		  
		  protected void collideWithEntity(net.minecraft.entity.Entity p_82167_1_) {}
		  
		  protected void collideWithNearbyEntities() {}
		  
		  protected void applyEntityAttributes()
		  {
		    super.applyEntityAttributes();
		    getEntityAttribute(net.minecraft.entity.SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
		  }

		  protected boolean isAIEnabled()
		  {
		    return true;
		  }

		@Override
		public void onLivingUpdate()
		{
		    if (this.ticksExisted % 100 == 0 && !this.worldObj.isRemote)
		    {
		    	int i = MathHelper.floor_double(this.posX);
		    	int j = MathHelper.floor_double(this.boundingBox.minY);
		    	int k = MathHelper.floor_double(this.posZ);
		        
				if (this.worldObj.isDaytime() ||
						this.worldObj.isRaining() ||
						TFC_Climate.getHeightAdjustedTemp(this.worldObj, i, j, k) < 13 ||
						TFC_Climate.getHeightAdjustedTemp(this.worldObj, i, j, k) >= 42)
				{
					this.worldObj.removeEntity(this);
				}
		    }
		    
		    super.onLivingUpdate();
		}
		
		  public void onUpdate()
		  {
		    super.onUpdate();
		    this.motionY *= 0.6000000238418579D;
		  }
		  
		  protected void updateAITasks()
		  {
		    super.updateAITasks();

		      if ((this.spawnPosition != null) && ((!this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ)) || (this.spawnPosition.posY < 1)))
		      {
		        this.spawnPosition = null;
		      }
		      
		      if ((this.spawnPosition == null) || (this.rand.nextInt(30) == 0) || (this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F))
		      {
		        this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - this.rand.nextInt(3) - 1, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		      }

		      double d0 = this.spawnPosition.posX + 0.5D - this.posX;
		      double d1 = this.spawnPosition.posY + 0.1D - this.posY;
		      double d2 = this.spawnPosition.posZ + 0.5D - this.posZ;
		      this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
		      this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
		      this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
		      float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / 3.141592653589793D) - 90.0F;
		      float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
		      this.moveForward = 0.5F;
		      this.rotationYaw += f1;
		  }
		  




		  protected boolean canTriggerWalking()
		  {
		    return false;
		  }

		  protected void fall(float p_70069_1_) {}

		  protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {}

		  public boolean doesEntityNotTriggerPressurePlate()
		  {
		    return true;
		  }

		  public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
		  {
		    if (isEntityInvulnerable())
		    {
		      return false;
		    }

		    return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		  }

	  @Override
	  public boolean getCanSpawnHere()
	   {
	     int i = MathHelper.floor_double(this.posX);
	     int j = MathHelper.floor_double(this.boundingBox.minY);
	     int k = MathHelper.floor_double(this.posZ);

	     if (TFC_Core.isGrass(this.worldObj.getBlock(i, j-1, k)) &&
    			 TFC_Climate.getHeightAdjustedTemp(this.worldObj, i, j, k) >= 13 &&
    			 TFC_Climate.getHeightAdjustedTemp(this.worldObj, i, j, k) < 42)
     {
    	 return (!this.worldObj.isRemote && !this.worldObj.isDaytime() && super.getCanSpawnHere());
     }
	     else return false;
	   }
	  
	  @Override
	  protected boolean canDespawn()
	   {
		return this.ticksExisted > 3000;
	   }
	   
	  public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	  {
	      super.writeEntityToNBT(p_70014_1_);
	      p_70014_1_.setInteger("SkinType", this.getSkin());
	  }

	  public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	  {
	      super.readEntityFromNBT(p_70037_1_);
	      this.setSkin(p_70037_1_.getInteger("SkinType"));
	  }
	  
	  public int getSkin()
	  {
	      return this.dataWatcher.getWatchableObjectByte(18);
	  }

	  public void setSkin(int p_70912_1_)
	  {
	      this.dataWatcher.updateObject(18, Byte.valueOf((byte)p_70912_1_));
	  }

	  @Override
	  public boolean isInsect()
	  {
		  return true;
	  }
	}
