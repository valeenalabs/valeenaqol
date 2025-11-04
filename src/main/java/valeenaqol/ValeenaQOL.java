package valeenaqol;

import customsettingslib.settings.CustomModSettings;
import customsettingslib.settings.CustomModSettingsGetter;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class ValeenaQOL {
	public static CustomModSettingsGetter settingGetter;

	// Called first - register content (items, mobs, tiles, etc.)
	public void init() {
		System.out.println("Valeena's QOL Mod is loading...");
		System.out.println("Valeena's QOL Mod loaded successfully!");
	}


	// Called last - everything is loaded, safe to reference any content
	public void postInit() {
		System.out.println("Valeena's QOL Mod post-initialization complete!");

	}

	public ModSettings initSettings() {
		CustomModSettings customModSettings = new CustomModSettings()
				.addBooleanSetting("REMOVE_DOUBLE_BED_PENALTY", true);

		customModSettings.addServerSettings("REMOVE_DOUBLE_BED_PENALTY");
		settingGetter = customModSettings.getGetter();

		return customModSettings;
	}
}
