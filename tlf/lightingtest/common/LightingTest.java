package tlf.lightingtest.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "LT", name = "LightingTest", version = "pre-alpha")
public class LightingTest
{
	public static final Block lightBlock = new BlockLight(1000).setUnlocalizedName("lightBlock");
	public static final BlockSpotlight spotlight = (BlockSpotlight) (new BlockSpotlight(1001).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("spotlight").setCreativeTab(CreativeTabs.tabDecorations));
	
	public static boolean errored = false;

	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		GameRegistry.registerBlock(lightBlock, "lightingtest.lightblock");
		GameRegistry.registerBlock(spotlight, "lightingtest.spotlight");

		LanguageRegistry.addName(spotlight, "Spotlight");

		GameRegistry.addRecipe(new ItemStack(spotlight, 1), 
				"XXX", " * ", " * ",'X', Block.blockLapis, '*', Item.stick
				);
	}
}
