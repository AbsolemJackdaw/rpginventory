package rpgInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import cpw.mods.fml.common.FMLLog;

public class CapeRenderer {

	public static ArrayList<ResourceLocation> capes = new ArrayList<ResourceLocation>();
	public static ArrayList<String> playersWithCapes = new ArrayList<String>();

	private ResourceLocation locationCape;
	private ThreadDownloadImageData downloadImageCape;


	public CapeRenderer(){
		try {
			// Create a URL for the desired page
			URL url = new URL("http://www.dnstechpack.com/user/subaraki/rpgcapes/downloadableCapes.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				FMLLog.getLogger().info(""+str);

				playersWithCapes.add(str);

				this.locationCape = getLocationCape(str);
				this.downloadImageCape = getDownloadImageCape(this.locationCape, str);
			}
			in.close();
		} catch (MalformedURLException e) {
			FMLLog.getLogger().info("nope");
		} catch (IOException e) {
			FMLLog.getLogger().info("nope");
		}
	}

	public static String getCapeUrl(String par0Str)
	{
		return String.format("http://www.dnstechpack.com/user/subaraki/rpgcapes/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
	}
	public ResourceLocation getLocationCape()
	{
		return this.locationCape;
	}
	public static ResourceLocation getLocationCape(String par0Str)
	{
		return new ResourceLocation("playerCapes/" + StringUtils.stripControlCodes(par0Str));
	}
	public static ThreadDownloadImageData getDownloadImageCape(ResourceLocation par0ResourceLocation, String par1Str)
	{
		return getDownloadImage(par0ResourceLocation, getCapeUrl(par1Str), (ResourceLocation)null, (IImageBuffer)null);
	}
	private static ThreadDownloadImageData getDownloadImage(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
	{
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		Object object = texturemanager.getTexture(par0ResourceLocation);

		if (object == null)
		{
			object = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
			texturemanager.loadTexture(par0ResourceLocation, (TextureObject)object);
		}

		return (ThreadDownloadImageData)object;
	}
}
