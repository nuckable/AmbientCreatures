package com.flashoverride.ambientcreatures.entity.passive;

import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.world.World;

public class EntityAmbientCreatureTFC extends EntityAmbientCreature
	{
	public EntityAmbientCreatureTFC(World world)
	{
		super(world);
	}
	
	public boolean isInsect()
	{
		return false;
	}
}
