package valeenaqol.Patches.Gameplay;

import necesse.engine.localization.message.GameMessageBuilder;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.friendly.human.HappinessModifier;
import necesse.entity.mobs.friendly.human.HumanMob;
import necesse.level.maps.levelData.settlementData.SettlementBed;
import necesse.level.maps.levelData.settlementData.SettlementRoom;
import necesse.level.maps.levelData.settlementData.settler.*;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.asm.Advice.This;

import java.util.ArrayList;
import java.util.List;

@ModMethodPatch(target = HumanMob.class, name = "getHappinessModifiers", arguments = {})
public class HappinessModifiersPatch {
	@OnMethodExit
	static void onExit(@This HumanMob ThisSettler, @Return(readOnly = false) List<HappinessModifier> list) {
		ArrayList<HappinessModifier> Modifiers = new ArrayList<>();

		if (ThisSettler.getWorldEntity() != null) {
			// Adds the corresponding food modifier happiness to the settler
			if (ThisSettler.lastFoodEaten != null) {
				Modifiers.add(ThisSettler.lastFoodEaten.quality.getModifier());
			} else {
				Modifiers.add(FoodQuality.noFoodModifier);
			}

			int differentFoodsEaten = (int) ThisSettler.recentFoodItemIDsEaten.stream().distinct().count();
			DietThought DietThought = Settler.getDietThought(differentFoodsEaten);
			// Adds the diet thought happiness modifier if present
			if (DietThought != null) {
				Modifiers.add(DietThought.getModifier());
			}

			if (ThisSettler.levelSettler != null) {
				SettlementBed Bed = ThisSettler.levelSettler.getBed();
				if (Bed != null) {
					SettlementRoom Room = Bed.getRoom();
					if (Room != null) {
						int NumberOfOccupiedBeds = Bed.getRoom().getOccupiedBeds();
						if (NumberOfOccupiedBeds == 2) {
							Modifiers.add(new HappinessModifier(0, (new GameMessageBuilder()).append("settlement", "sharingroom")));
						} else if (NumberOfOccupiedBeds >= 3) {
							Modifiers.add(new HappinessModifier(-10 * NumberOfOccupiedBeds, (new GameMessageBuilder()).append("settlement", "sharingroom")));
						}

						int RoomSize = Room.getRoomSize();
						if (RoomSize > 0) {
							RoomSize Size = Settler.getRoomSize(RoomSize);
							Modifiers.add(Size.getModifier());
						}

						int RoomQuality = Room.getFurnitureScore();
						if (RoomQuality > 0) {
							RoomQuality Quality = Settler.getRoomQuality(RoomQuality);
							Modifiers.add(Quality.getModifier());
						}

						if (Room.getRoomProperty("lights") <= 0) {
							Modifiers.add(new HappinessModifier(-10, (new GameMessageBuilder().append("settlement", "roommissinglights"))));
						}
						if (Room.getRoomProperty("outsidefloor") > 0) {
							Modifiers.add(HappinessModifier.bedOutsideModifier);
						}
					}
				} else {
					Modifiers.add(HappinessModifier.noBedModifier);
				}

				PopulationThought PoulationThought = Settler
						.getPopulationThough(ThisSettler.levelSettler.data.countTotalSettlers());

				if (PoulationThought != null) {
					Modifiers.add(PoulationThought.getModifier());
				}
			}
		}
		//list = Modifiers;
	}
}
