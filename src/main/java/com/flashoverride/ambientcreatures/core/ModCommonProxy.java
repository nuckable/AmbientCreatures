package com.flashoverride.ambientcreatures.core;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.bioxx.tfc.Handlers.ServerTickHandler;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.flashoverride.ambientcreatures.AmbientCreatures;
import com.flashoverride.ambientcreatures.items.ItemCreaturePlacer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModCommonProxy
{
	private int modEntityID = 0;
	
	public String getCurrentLanguage()
	{
		return null;
	}

	public World getCurrentWorld()
	{
		return MinecraftServer.getServer().getEntityWorld();
	}

	public boolean getGraphicsLevel()
	{
		return false;
	}
	
	public File getMinecraftDirectory()
	{
		return FMLCommonHandler.instance().getMinecraftServerInstance().getFile("");
	}

	public void hideNEIItems()
	{
	}
	
	public boolean isRemote()
	{
		return false;
	}

	public void loadOptions()
	{
		//Load our settings from the Options file
		ModOptions.loadSettings();
	}
	
	public void onClientLogin()
	{
	}

	public void registerFluidIcons()
	{
	}

	public void registerGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(com.flashoverride.ambientcreatures.AmbientCreatures.instance, new com.flashoverride.ambientcreatures.handlers.GuiHandler());
	}

	public void registerHandlers()
	{
	}

	public void registerKeys()
	{
	}

	public void registerKeyBindingHandler()
	{
	}

	public void registerRenderInformation()
	{
		// NOOP on server
	}

	public void registerSoundHandler()
	{
	}

	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
	
	public void registerTileEntities(boolean flag)
	{
		// non TESR registers

		if (flag)
		{
			// TESR registers
		}
	}

	public void registerToolClasses()
	{
	}

	public void registerWailaClasses()
	{
	}

	public void uploadKeyBindingsToGame()
	{
	}
	
	public void addAmbientSpawn(Class entityClass, int weightedProb, int min, int max)
	{
	    for (int i = 0; i < TFCBiome.getBiomeGenArray().length; i++)
	    {
	    	if (TFCBiome.getBiomeGenArray()[i] != null)
	    	{
	    		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, EnumCreatureType.ambient, TFCBiome.getBiomeGenArray()[i]);
	    	}
	    }
	}
	
	public void registerModEntityWithSpawn(Class entityClass, String entityName, int weightedProb, int min, int max)
	{
		EntityRegistry.registerModEntity(entityClass, entityName, ++modEntityID, AmbientCreatures.instance, 80, 3, false);
   		this.addAmbientSpawn(entityClass, weightedProb, min, max);
	}
	
	public void registerModEntityWithEgg(Class parEntityClass, String parEntityName, int parEggColor, int parEggSpotsColor, int weightedProb, int min, int max)
	{
	    this.registerModEntityWithSpawn(parEntityClass, parEntityName, weightedProb, min, max);
	    registerSpawnEgg(parEntityName, parEggColor, parEggSpotsColor);
	}

	public void registerSpawnEgg(String parSpawnName, int parEggColor, int parEggSpotsColor)
	{
		Item itemSpawnEgg = new ItemCreaturePlacer(parSpawnName, parEggColor, parEggSpotsColor).setUnlocalizedName(ModDetails.ModID + ".spawn_egg_"+parSpawnName.toLowerCase()).setTextureName("ambientcreatures:spawn_egg");
		GameRegistry.registerItem(itemSpawnEgg, "spawnEgg"+parSpawnName);
	}
}
