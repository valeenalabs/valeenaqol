package valeenaqol;

import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class ValeenaQOL {

	// Called first - register content (items, mobs, tiles, etc.)
	public void init() {
		System.out.println("Valeena's QOL Mod is loading...");
		System.out.println("Valeena's QOL Mod loaded successfully!");
	}


	// Called last - everything is loaded, safe to reference any content
	public void postInit() {
		System.out.println("Valeena's QOL Mod post-initialization complete!");

	}
}
