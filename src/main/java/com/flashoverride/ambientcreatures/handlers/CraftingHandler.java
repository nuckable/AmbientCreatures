package com.flashoverride.ambientcreatures.handlers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingHandler
{
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent e)
	{
		ItemStack itemstack = e.crafting;
		Item item = itemstack.getItem();
		IInventory inventory = e.craftMatrix;
		
		if (item == null || inventory == null) return;

	}
}
