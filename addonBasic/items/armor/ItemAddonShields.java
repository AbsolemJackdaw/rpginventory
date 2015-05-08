package addonBasic.items.armor;

import net.minecraft.util.ResourceLocation;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonBasic.RpgBaseAddon;
import addonBasic.models.item.ModelIronThorn;
import addonBasic.models.item.ModelMageBook;
import addonBasic.models.item.ModelShield;

public class ItemAddonShields extends ItemRpgInvArmor{

	private ModelIronThorn it = new ModelIronThorn();
	private ModelShield ms= new ModelShield();
	private ModelMageBook bm = new ModelMageBook();

	public static final ResourceLocation mantle = new ResourceLocation("subaraki:jewels/talisman.png");
	
	public ItemAddonShields(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, -1, resourcelocation);
	}

	@Override
	public ResourceLocation getMantleTexture() {
		return mantle;
	}

	@Override
	public String bindShieldToArmorClass() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return RpgBaseAddon.CLASSBERSERKER;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return RpgBaseAddon.CLASSARCHER;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return RpgBaseAddon.CLASSALCHEMIST;
		}

		return super.bindShieldToArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return it;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return ms;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return bm;
		}
		return super.getShieldModel();
	}

	@Override
	public boolean isMantle() {
		return this.equals(RpgBaseAddon.talisman);
	}

	@Override
	public String shieldClass() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return RpgBaseAddon.CLASSBERSERKERSHIELD;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return RpgBaseAddon.CLASSARCHERSHIELD;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return RpgBaseAddon.CLASSALCHEMISTSHIELD;
		}
		return super.shieldClass();
	}
	
	@Override
	public int getBlockChance() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return 43;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return 75;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return 50;
		}
		return super.getBlockChance();
	}
}
