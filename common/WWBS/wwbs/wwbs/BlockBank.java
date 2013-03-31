package WWBS.wwbs.wwbs;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import WWBS.wwbs.mod_wwbs;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class BlockBank extends BlockContainer {

	public BlockBank(int par1, Material par2Material) {
		super(par1, par2Material);

	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wwbs:bank");
	}
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{		
		mod_wwbs.proxy.openGui(1,par5EntityPlayer);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(1);
			
			outputStream.writeInt(x);
			outputStream.writeInt(y);
			outputStream.writeInt(z);
			
			Packet250CustomPayload packet = new Packet250CustomPayload("wwbsData", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
			PacketDispatcher.sendPacketToPlayer(packet, (Player)par5EntityPlayer);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new WwbsTe();
	}
}