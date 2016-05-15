package com.flashoverride.ambientcreatures;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import com.bioxx.tfc.TerraFirmaCraft;
import com.flashoverride.ambientcreatures.core.ModBlocks;
import com.flashoverride.ambientcreatures.core.ModCommonProxy;
import com.flashoverride.ambientcreatures.core.ModDetails;
import com.flashoverride.ambientcreatures.core.ModItems;
import com.flashoverride.ambientcreatures.core.ModRecipes;
import com.flashoverride.ambientcreatures.core.player.ModPlayerTracker;
import com.flashoverride.ambientcreatures.entity.passive.EntityButterfly;
import com.flashoverride.ambientcreatures.entity.passive.EntityMoth;
import com.flashoverride.ambientcreatures.handlers.ChunkEventHandler;
import com.flashoverride.ambientcreatures.handlers.CraftingHandler;
import com.flashoverride.ambientcreatures.handlers.network.InitClientWorldPacket;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModDetails.ModID, name = ModDetails.ModName, version = ModDetails.ModVersion, dependencies = ModDetails.ModDependencies)
public class AmbientCreatures
{
	@Instance(ModDetails.ModID)
	public static AmbientCreatures instance;

	@SidedProxy(clientSide = ModDetails.CLIENT_PROXY_CLASS, serverSide = ModDetails.SERVER_PROXY_CLASS)
	public static ModCommonProxy proxy;
	
	public File getMinecraftDirectory()
	{
		return proxy.getMinecraftDirectory();
	}
	
	@EventHandler
	public void preInitialize(FMLPreInitializationEvent e)
	{
		ModContainer mod = Loader.instance().getIndexedModList().get(ModDetails.MODID_TFC);
		if (mod != null)
		{
//			String updatePath = ModDetails.VersionCheckerUpdatePath.replace("{0}", mod.getVersion());
//			FMLInterModComms.sendRuntimeMessage(ModDetails.ModID, "VersionChecker", "addVersionCheck", updatePath);
		}

		instance = this;		
		
		// Load our settings
		proxy.loadOptions();
		
		proxy.registerTickHandler();
		
		ModBlocks.initialise();	

		// Register Key Bindings(Client only)
		proxy.registerKeys();
		// Register KeyBinding Handler (Client only)
		proxy.registerKeyBindingHandler();
		// Register Handler (Client only)
		proxy.registerHandlers();
		// Register Tile Entities
		proxy.registerTileEntities(true);
		// Register Sound Handler (Client only)
		proxy.registerSoundHandler();
		
		ModItems.initialise();
		
		// Register Gui Handler
		proxy.registerGuiHandler();		
		
		proxy.registerModEntityWithEgg(EntityButterfly.class, "EntityButterfly", 0xf4b60f, 0x000000, 40, 1, 4);
		proxy.registerModEntityWithEgg(EntityMoth.class, "EntityMoth", 0x535353, 0x292929, 40, 1, 4);

	}

	@EventHandler
	public void initialize(FMLInitializationEvent e)
	{
		// Register packets in the TFC PacketPipeline
		TerraFirmaCraft.PACKET_PIPELINE.registerPacket(InitClientWorldPacket.class);
		
		// Register the player tracker
		FMLCommonHandler.instance().bus().register(new ModPlayerTracker());
		
		// Register the tool classes
		proxy.registerToolClasses();

		// Register Crafting Handler
		FMLCommonHandler.instance().bus().register(new CraftingHandler());

		// Register the Chunk Load/Save Handler
		MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
		
		// Register all the render stuff for the client
		proxy.registerRenderInformation();

		ModRecipes.initialise();
		
		// Register WAILA classes
		proxy.registerWailaClasses();
		proxy.hideNEIItems();		
	}

	@EventHandler
	public void postInitialize(FMLPostInitializationEvent e)
	{
	}
}
