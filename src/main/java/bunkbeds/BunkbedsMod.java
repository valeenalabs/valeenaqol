package bunkbeds;

import bunkbeds.furnitures.BunkBedObject;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.ObjectRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.engine.registries.RecipeTechRegistry;

@ModEntry
public class BunkbedsMod {
    
    // Called first - register content (items, mobs, tiles, etc.)
    public void init() {
        System.out.println("BunkBeds is loading...");
        System.out.println("BunkBeds loaded successfully!");
    }

    
    // Called last - everything is loaded, safe to reference any content
    public void postInit() {
        System.out.println("BunkBeds post-initialization complete!");

    }
}
