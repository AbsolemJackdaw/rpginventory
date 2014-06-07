package rpgInventory.renderer;

//custom capes got deactivated a long time ago. still want to keep this as a backup though

//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.IImageBuffer;
//import net.minecraft.client.renderer.ThreadDownloadImageData;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.StringUtils;
//import cpw.mods.fml.common.FMLLog;
//
//public class CapeRenderer {
//
//	public static ArrayList<ResourceLocation> capes = new ArrayList<ResourceLocation>();
//	public static ArrayList<String> playersWithCapes = new ArrayList<String>();
//
//	public static String getCapeUrl(String par0Str) {
//		return String.format(
//				"http://www.dnstechpack.com/user/subaraki/rpgcapes/%s.png",
//				new Object[] { StringUtils.stripControlCodes(par0Str) });
//	}
//
//	private static ThreadDownloadImageData getDownloadImage(
//			ResourceLocation par0ResourceLocation, String par1Str,
//			ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer) {
//		TextureManager texturemanager = Minecraft.getMinecraft()
//				.getTextureManager();
//		Object object = texturemanager.getTexture(par0ResourceLocation);
//
//		if (object == null) {
//			object = new ThreadDownloadImageData(par1Str,par2ResourceLocation,par3IImageBuffer);
//			texturemanager.bindTexture(par0ResourceLocation);
//		}
//
//		return (ThreadDownloadImageData) object;
//	}
//
//	public static ThreadDownloadImageData getDownloadImageCape(
//			ResourceLocation par0ResourceLocation, String par1Str) {
//		return getDownloadImage(par0ResourceLocation, getCapeUrl(par1Str),
//				(ResourceLocation) null, (IImageBuffer) null);
//	}
//
//	public static ResourceLocation getLocationCape(String par0Str) {
//		return new ResourceLocation("Rpgcloaks/"
//				+ StringUtils.stripControlCodes(par0Str));
//	}
//
//	private ResourceLocation locationCape;
//	private ThreadDownloadImageData downloadImageCape;
//
//	public CapeRenderer() {
//		try {
//			// Create a URL for the desired page
//			URL url = new URL(
//					"http://www.dnstechpack.com/user/subaraki/rpgcapes/downloadableCapes.txt");
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					url.openStream()));
//			String str;
//			while ((str = in.readLine()) != null) {
//
//				playersWithCapes.add(str);
//
//				this.locationCape = getLocationCape(str);
//				this.downloadImageCape = getDownloadImageCape(
//						this.locationCape, str);
//			}
//			in.close();
//			FMLLog.getLogger().info(
//					"Added capes for : " + playersWithCapes.toString());
//
//		} catch (MalformedURLException e) {
//			FMLLog.getLogger().info("[ERROR] Couldn't Handle Capes. Index 1.");
//		} catch (IOException e) {
//			FMLLog.getLogger().info("[ERROR] Couldn't Handle Capes. Index 2.");
//		}
//	}
//
//	public ResourceLocation getLocationCape() {
//		return this.locationCape;
//	}
//}
