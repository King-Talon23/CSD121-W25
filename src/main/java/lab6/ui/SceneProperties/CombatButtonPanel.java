package lab6.ui.SceneProperties;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.SoldierStuff.Cover;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.Utility.GetRandom;

import java.util.List;

import static lab6.Entities.SoldierStuff.Cover.*;

public class CombatButtonPanel {

    public static VBox combatButtonPanel(Soldier soldier, List<Alien> enemies) {
        List<Button> buttonList = List.of(hunkerDown(soldier), move(soldier), reload(soldier), bandageWounds(soldier));
        for (Alien alien : enemies) {
            buttonList.addLast(shootAtTarget(soldier, alien));
        }
        VBox buttonPanel = new VBox();
        for (Button button : buttonList) {
            if (button == null) continue;
            buttonPanel.getChildren().add(button);
        }
        return buttonPanel;
    }

    private static Button hunkerDown(Soldier soldier) {
        if (soldier.coverType.ordinal() > NONE.ordinal()) { // action requires cover

            Button hunkerButton = makeStyledButton("Hunker Down");
            hunkerButton.setOnAction(actionEvent -> {
                soldier.actionPoints = 0;
                soldier.hunkerBonus = true;
                soldier.applyHunkerBonus();
            });

            return hunkerButton;
        }
        return null;
    }

    private static Button shootAtTarget(Soldier soldier, Alien alien) {
        int[] hitChance = soldier.aimAtTarget(alien);
        String buttonText = String.format("Shoot at %s [%s%s]", alien.getName(), '%', hitChance[2]);

        Button shootTarget = makeStyledButton(buttonText);
        shootTarget.setOnAction(actionEvent -> {
            soldier.actionPoints = 0;
            soldier.shootAtTarget(alien);
        });
        return shootTarget;
    }

    private static Button move(Soldier soldier) {
        Button moveButton = makeStyledButton("Move");
        moveButton.getOnMouseDragEntered();

        moveButton.setOnAction(actionEvent -> {
            Cover oldCover = soldier.coverType;
            soldier.actionPoints--;
            soldier.coverType = GetRandom.cover();
            System.out.printf("%s -> %s", oldCover, soldier.coverType);
        });

        return moveButton;
    }

    private static Button reload(Soldier soldier) {
        if (soldier.weapon.ammo < soldier.weapon.clipSize) { // action requires missing ammo
            String buttonText = String.format("Reload [+%sAM]", (soldier.weapon.clipSize - soldier.weapon.ammo));
            Button reloadButton = makeStyledButton(buttonText);
            reloadButton.getOnMouseDragEntered();

            reloadButton.setOnAction(actionEvent -> {
                if (soldier.weapon.freeReloads > 0) {
                    System.out.print("Auto-Loader activated!");
                    soldier.weapon.freeReloads--;
                } else {
                    soldier.actionPoints--;
                }
                soldier.weapon.reload();
            });

            return reloadButton;

        }
        return null;
    }

    private static Button bandageWounds(Soldier soldier) {
        if (soldier.hp < soldier.getMaxHP()) { // action requires missing health
            Button BandageButton = makeStyledButton("Bandage Wounds [+1HP]");
            BandageButton.getOnMouseDragEntered();

            BandageButton.setOnAction(actionEvent -> {
                soldier.actionPoints--;
                soldier.hp++;
            });

            return BandageButton;

        }
        return null;
    }

    private static Button makeStyledButton(String text) {
        Button button = new Button(text);

        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff7e5f, #feb47b); " +
                        "-fx-background-radius: 15; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10 20;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to left, #feb47b, #ff7e5f); " +
                        "-fx-background-radius: 15; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10 20;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff7e5f, #feb47b); " +
                        "-fx-background-radius: 15; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10 20;"
        ));
        return button;
    }
}
