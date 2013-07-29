package rpgInventory.gui;
//package RpgInventory.gui;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutput;
//
//
//import net.minecraft.client.gui.GuiButton;
//import net.minecraft.client.gui.inventory.GuiContainerCreative;
//import net.minecraft.client.gui.inventory.GuiInventory;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.network.packet.Packet250CustomPayload;
//import cpw.mods.fml.common.network.FMLNetworkHandler;
//import cpw.mods.fml.common.network.PacketDispatcher;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class AlternativeGui extends GuiInventory
//{
//
//	public int	var1	= 85;
//	public int	var2	= 90;
//
//	@SideOnly(Side.CLIENT)
//	public AlternativeGui(EntityPlayer par1EntityPlayer)
//	{
//		super(par1EntityPlayer);
//
//	}
//	/**
//	 * Adds the buttons (and other controls) to the screen in question.
//	 */
//	@Override
//	public void initGui()
//	{
//		this.controlList.clear();
//		if (this.mc.playerController.isInCreativeMode())
//		{
//			this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
//		}
//		else
//		{
//			super.initGui();
//			int posX = (this.width) / 2;
//			int posY = (this.height) / 2;
//			this.controlList.add(new GuiButton(0, posX - var1, posY + var2, 80, 20, "Rpg Inventory"));
//			this.controlList.add(new GuiButton(1, posX - var1 + 80 + 5, posY + var2, 80, 20, "Close"));
//		}
//	}
//
//	public void actionPerformed(GuiButton button)
//	{
//		EntityPlayer player = this.mc.thePlayer;
//		
//		if (button.id == 0)
//		{
//			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//			DataOutputStream outputStream = new DataOutputStream(bytes);
//			try
//			{
//				outputStream.writeInt(1);
//				Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
//				PacketDispatcher.sendPacketToServer(packet);
//				System.out.println("Packet send");
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		if (button.id == 1)
//		{
//			player.closeScreen();
//		}
//	}
//}
