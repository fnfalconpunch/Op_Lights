package tlf.lightingtest.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpotlight extends Block
{
	public static int range = 9;
	public static Block lightBlock = LightingTest.lightBlock;
	
	private Icon blockIcon;
	
	private boolean oldRenderType = false;
	
	public BlockSpotlight(int par1) {
		super(par1, Material.glass);
	}
	
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		//TODO: Set all loaded chunks to max brightness
		
		Chunk mainChunk = par1World.getChunkFromBlockCoords(par2, par4);
		
		int chunkX = mainChunk.xPosition;
		int chunkZ = mainChunk.zPosition;
		
		for (int cX = -((int)Math.ceil(range/2)); cX <= (int)Math.ceil(range/2); cX++)
		{
			for (int cZ = -((int)Math.ceil(range/2)); cZ <= (int)Math.ceil(range/2); cZ++)
			{
				Chunk chunk = par1World.getChunkFromChunkCoords(chunkX + cX, chunkZ + cZ);
				
				if (!par1World.isRemote)
				{
					for (int x = 0; x < 16; x++)
					{
						for (int z = 0; z < 16; z++)
						{
							int y = chunk.heightMap[z << 4 | x];
							//par1World.setLightValue(EnumSkyBlock.Sky, x + (chunk.xPosition * 16), y, z + (chunk.zPosition * 16), 15);
							par1World.setBlock(x + (chunk.xPosition * 16), y, z + (chunk.zPosition * 16), LightingTest.lightBlock.blockID);
						}
					}
				}
			}
		}
		
		return par9;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
	{
		Chunk mainChunk = par1World.getChunkFromBlockCoords(par2, par4);
		
		int chunkX = mainChunk.xPosition;
		int chunkZ = mainChunk.zPosition;
		
		for (int cX = -((int)Math.ceil(range/2)); cX <= (int)Math.ceil(range/2); cX++)
		{
			for (int cZ = -((int)Math.ceil(range/2)); cZ <= (int)Math.ceil(range/2); cZ++)
			{
				Chunk chunk = par1World.getChunkFromChunkCoords(chunkX + cX, chunkZ + cZ);
				
				if (!par1World.isRemote)
				{
					for (int x = 0; x < 16; x++)
					{
						for (int z = 0; z < 16; z++)
						{
							int y = chunk.heightMap[z << 4 | x];
							//par1World.setLightValue(EnumSkyBlock.Sky, x + (chunk.xPosition * 16), y, z + (chunk.zPosition * 16), 0);
							if (par1World.getBlockId(x + (chunk.xPosition * 16), y, z + (chunk.zPosition * 16)) == LightingTest.lightBlock.blockID)
							{
								par1World.setBlockToAir(x + (chunk.xPosition * 16), y, z + (chunk.zPosition * 16));
							}
						}
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("tlf:spotlight");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
