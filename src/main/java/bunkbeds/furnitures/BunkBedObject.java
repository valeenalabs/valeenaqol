package bunkbeds.furnitures;

import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.PlayerInventorySlot;
import necesse.level.gameObject.furniture.BedObject;
import necesse.level.gameObject.furniture.FurnitureObject;
import necesse.level.gameObject.furniture.SettlerBedObject;
import necesse.level.maps.Level;
import necesse.level.maps.LevelObject;
import necesse.level.maps.multiTile.MultiTile;

import java.awt.*;
import java.io.FileNotFoundException;

public class BunkBedObject extends FurnitureObject implements SettlerBedObject {
    private final String textureName;
    public GameTexture baseTexture;
    public GameTexture[] maskTextures;
    protected int counterID;

    public BunkBedObject() {
        super();
        this.textureName = "bunkbed";
    }

    @Override
    public boolean isMasterBedObject(Level level, int i, int i1) {
        return false;
    }

    @Override
    public LevelObject getSettlerBedMasterLevelObject(Level level, int i, int i1) {
        return null;
    }

    @Override
    public Rectangle getSettlerBedTileRectangle(Level level, int i, int i1) {
        return null;
    }
}

