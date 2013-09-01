package rpgInventory.gui.pet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rpgInventory.IPet;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.handelers.packets.RpgPacketHandler;
import rpgRogueBeast.entity.BMPetImpl;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.SpiderPet;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PetGui extends GuiScreen {

	BMPetImpl thePet;
	int petType; /*Spider 2, bull 3, boar 1*/

	EntityPlayer p;
	ItemStack petCrystal;
	RpgInv inv;

	public PetGui(EntityPlayer p1, RpgInv inv) {
		p = p1;
		this.inv = inv;
	}
	private GuiTextField textfield;
	public int xSizeOfTexture = 181;
	public int ySizeOfTexture = 166;
	public String petStats = "Stats :";
	public String info = "Additional Info";
	public short petAtk;
	public short currentHP;
	public short totalHP;
	public String saddle;
	public String levelInfo;
	public short PetLevel;
	public String PetName;
	public String Name = "Name";
	public short playerLevel;
	private short rotationCounter = 0;
	private short petcandyConsumed = 0;
	private short petLevelsAdded = 0;
	private short playerLevelsLost = 0;
	Class clazz;
	RenderLiving ren;
	EntityLiving fake;

	public void initGui() {
		petCrystal = this.inv.getCrystal();
		if (IPet.playersWithActivePets.containsKey(p.username)) {
			thePet = (BMPetImpl) IPet.playersWithActivePets.get(p.username).getPet();
			if (thePet != null) {
				//make sure crystal is updated with the mob info
				petCrystal = thePet.writePetToItemStack();
				this.inv.setInventorySlotContents(6, petCrystal);
			}
		}
		petType = petCrystal.getItemDamage();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(new ResourceLocation("rpginventorymod:textures/gui/petgui.png"));

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		buttonList.clear();
		buttonList.add(new GuiButton(1, posX + 70, posY + 36, 40, 20, "Submit"));
		buttonList.add(new GuiButton(4, posX + 182, posY + 120, 40, 20, "Imbue"));
		buttonList.add(new GuiButton(3, posX + 182, posY + 22, 30, 20, "Back"));
		buttonList.add(new GuiButton(2, posX + 182, posY, 30, 20, "Close"));

		levelInfo = "";

		NBTTagCompound tags = petCrystal.getTagCompound();
		if (tags == null) {
			petCrystal.setTagCompound(new NBTTagCompound());
		}
		try {
			PetLevel = (short) tags.getInteger("PetLevel");
		} catch (NullPointerException ex) {
			PetLevel = 0;
		}
		try {
			petAtk = (short) tags.getInteger("PetAttack");
		} catch (NullPointerException ex) {
			petAtk = 4;
		}
		try {
			currentHP =  (short) tags.getFloat("PetHealth");
		} catch (NullPointerException ex) {
			currentHP = 18;
		}
		try {
			totalHP = (short) tags.getFloat("PetMaxHealth");
		} catch (NullPointerException ex) {
			totalHP = 18;
		}
		try {
			PetName = tags.getString("PetName");
		} catch (NullPointerException ex) {
			PetName = petCrystal.getDisplayName();
		}

		if (petAtk == 0) {
			petAtk = 4;
		}
		if (totalHP == 0) {
			currentHP = totalHP = 18;
		}
		if (currentHP == 0) {
			currentHP = 1;
		}
		if (PetName == null || PetName.isEmpty()) {
			PetName = petCrystal.getDisplayName();
		}
		if (PetLevel >= 50) {
			if (tags.hasKey("isSaddled")) {
				if (tags.getBoolean("isSaddled") == true) {
					saddle = PetName + " is saddled.";
				} else {
					saddle = PetName + " is not saddled.";
				}
			} else {
				saddle = PetName + " is not saddled.";
			}
		} else {
			saddle = PetName + " needs lv50 to be ridden.";
		}

		textfield = new GuiTextField(fontRenderer, posX + 70, posY + 14, 100, 20);
		textfield.setText(PetName);
		textfield.setMaxStringLength(32);
		playerLevel = (short) Minecraft.getMinecraft().thePlayer.experienceLevel;
		clazz = petType == 1 ? BoarPet.class : petType == 2 ? SpiderPet.class : BullPet.class;
		ren = (RenderLiving) RenderManager.instance.getEntityClassRenderObject(clazz);
		try {
			fake = (EntityLiving) clazz.getConstructor(World.class).newInstance(p.worldObj);
		} catch (Throwable ex) {
			Logger.getLogger(PetGui.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.id == 1) {
			PetName = textfield.getText();
		}
		if (guibutton.id == 2) {
			this.mc.thePlayer.closeScreen();
		}
		if (guibutton.id == 3) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//			ObjectOutput out;
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(RpgPacketHandler.OPENRPGINV);
				Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
				//System.out.println("Packet send");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (guibutton.id == 4) {
			levelInfo = "";
			if (playerLevel < PetLevel / 2 + 1 && PetLevel < 200) {
				levelInfo = "You need " + (MathHelper.floor_float(PetLevel / 2) + 1) + " levels to level your pet.";
				//levelInfo2 = "to level your pet.";
			} else if (PetLevel >= 200) {
				PetLevel = 200;
				levelInfo = "Pet has reached maximum level.";
				//            } else if (mc.thePlayer.inventory.hasItem(mod_RpgInventory.petCandy.itemID)) {
				//                petcandyConsumed++;
				//                levelInfo = "Consumed " + petcandyConsumed + " Rare PetCandy and added " + petcandyConsumed + " pet level";
			} else if (playerLevel >= PetLevel / 2 + 1) {
				levelInfo = "Added 1 pet Level. Cost: " + (MathHelper.floor_float(PetLevel / 2.0F) + 1) + " player levels;";
				//So first levelup is not free;
				playerLevel -= MathHelper.floor_float(PetLevel / 2.0F) + 1;
				PetLevel += 1;
				currentHP = totalHP = (short) (petType == 1 ? 15 + MathHelper.floor_float(((float) PetLevel) / 2.5F)
						: petType == 2 ? 18 + MathHelper.floor_float(((float) PetLevel) / 2.2F)
								: 20 + MathHelper.floor_float(((float) PetLevel) / 2F));
				petLevelsAdded++;
				playerLevelsLost += Math.floor((float) PetLevel) / 2.0F + 1.0F;
			}
		}
	}

	@Override
	public void onGuiClosed() {
		sendChanges();
		super.onGuiClosed();
	}

	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		textfield.textboxKeyTyped(c, i);
	}

	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		textfield.mouseClicked(i, j, k);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		try {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.func_110577_a(new ResourceLocation("rpginventorymod:textures/gui/petgui.png"));
			int posX = (this.width - xSizeOfTexture) / 2;
			int posY = (this.height - ySizeOfTexture) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		} finally {
			textfield.drawTextBox();
		}
		drawString(fontRenderer, Name + ": " + PetName, this.width / 2 - 20, this.height / 2 - 79, 0xffffff);
		drawString(fontRenderer, petStats, this.width / 2 - 85, this.height / 2 - 10, 0x00ffff);
		drawString(fontRenderer, "Lvl : " + PetLevel, this.width / 2 - 80, this.height / 2, 0xffffff);
		drawString(fontRenderer, "Atk : " + petAtk, this.width / 2 - 80, this.height / 2 + 10, 0xffffff);
		drawString(fontRenderer, "HP : " + currentHP + "/" + totalHP, this.width / 2 - 80, this.height / 2 + 20, 0xffffff);

		drawString(fontRenderer, info, this.width / 2 - 85, this.height / 2 + 45, 0xff00ff);
		//Shadow
		fontRenderer.drawSplitString(saddle, this.width / 2 - 80 + 1, this.height / 2 + 55 + 1, xSizeOfTexture - 9, 0x444444);
		//text
		fontRenderer.drawSplitString(saddle, this.width / 2 - 80, this.height / 2 + 55, xSizeOfTexture - 10, 0xffffff);
		//drawString(fontRenderer, saddle, this.width / 2 - 80, this.height / 2 + 55, 0xffffff);
		//Shadow
		fontRenderer.drawSplitString(levelInfo, this.width / 2 + 95 + 1, this.height / 2 + 65 + 1, this.width - (this.width / 2 + 95) + 1, 0x444444);
		//text
		fontRenderer.drawSplitString(levelInfo, this.width / 2 + 95, this.height / 2 + 65, this.width - (this.width / 2 + 95), 0xffffff);
		//drawString(fontRenderer, levelInfo, this.width / 2 + 95, this.height / 2 + 65, 0xffffff);
		//drawString(fontRenderer, levelInfo2, this.width / 2 + 95, this.height / 2 + 75, 0xffffff);
		if (PetLevel >= 200) {
			PetLevel = 200;
			drawString(fontRenderer, "Maxed out.", this.width / 2 - 30, this.height / 2, 0x00ff00);
		}
		super.drawScreen(i, j, f);
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(this.width / 2 - 52, this.height / 2 - 23, 40);
			GL11.glRotatef((float) 180, 0f, 1f, 0f);
			GL11.glRotatef((float) 180, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter++, 0, 1, 0);
			fake.setPositionAndRotation(0, 0, 0, 0, 0);
			GL11.glScaled(40, 40, -40);
			ren.doRender(fake, 0, 0, 0, 0, 1);
		}
		GL11.glPopMatrix();
	}

	public void sendChanges() {
			
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(15);
			dos.writeUTF(new String(PetName.getBytes("UTF-8"), "UTF-8"));
			dos.writeShort(PetLevel);
			dos.writeShort(currentHP);
			dos.writeShort(totalHP);
			dos.writeShort(petAtk);
			dos.writeShort(playerLevelsLost);
			dos.writeShort(petLevelsAdded);
			dos.writeShort(petcandyConsumed);

			PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("RpgRBPacket", bos.toByteArray()));
			} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}