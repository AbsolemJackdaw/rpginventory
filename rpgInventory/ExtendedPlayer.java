//package rpgInventory;
//
//import cpw.mods.fml.common.FMLLog;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.world.World;
//import net.minecraftforge.common.IExtendedEntityProperties;
//
//public class ExtendedPlayer implements IExtendedEntityProperties {
//
//	
//
//	public final EntityPlayer player;
//
//	public final PlayerRpgInventory inventory;
//	
//	public ExtendedPlayer(EntityPlayer player)
//	{
//		this.player = player;
//		FMLLog.getLogger().info("extended player constructor" );
//		inventory = new PlayerRpgInventory(player);
//	}
//
//	/**
//	 * Used to register these extended properties for the player during EntityConstructing event
//	 * This method is for convenience only; it will make your code look nicer
//	 */
//	public static final void register(EntityPlayer player)
//	{
//		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
//		FMLLog.getLogger().info("extended player registered" );
//	}
//
//	/**
//	 * Returns ExtendedPlayer properties for player
//	 * This method is for convenience only; it will make your code look nicer
//	 */
//	public static final ExtendedPlayer get(EntityPlayer player)
//	{
//		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
//	}
//
//	@Override
//	public void saveNBTData(NBTTagCompound compound) {
//
//		NBTTagCompound properties = new NBTTagCompound();
//		this.inventory.writeToNBT(properties);
//		compound.setTag(EXT_PROP_NAME, properties);
//	}
//
//	@Override
//	public void loadNBTData(NBTTagCompound compound) {
//
//		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
//		this.inventory.readFromNBT(properties);
//	}
//
//	@Override
//	public void init(Entity entity, World world) {
//		FMLLog.getLogger().info("extended player init" );
//	}
//	
//	
//	@Deprecated
//	public void addEntry(PlayerRpgInventory inv){
//	}
//	
//	@Deprecated
//	public PlayerRpgInventory getInventory(){
//		PlayerRpgInventory inventory = new PlayerRpgInventory(player);
//		inventory.readFromNBT(new NBTTagCompound());
//		return inventory;
//	}
//}
