package lab6.ui.SceneProperties;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.SoldierStuff.Cover;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.Utility.GetRandom;

import java.util.ArrayList;
import java.util.List;

import static lab6.Entities.SoldierStuff.Cover.*;

public class CombatButtonPanel {

    public static TilePane combatButtonPanel(Soldier soldier, List<Alien> enemies) {
        List<Button> buttonList = new ArrayList<>();
        for (Alien alien : enemies) {
            addShootAtTarget(soldier, alien, buttonList);
        }
        addMove(soldier, buttonList);
        addHunkerDown(soldier, buttonList);
        reload(soldier, buttonList);
        bandageWounds(soldier, buttonList);

        TilePane buttonPanel = new TilePane();
        buttonPanel.getChildren().addAll(buttonList);
        buttonPanel.setHgap(10);
        buttonPanel.setVgap(10);
        buttonPanel.setPrefColumns(8);
        buttonPanel.setAlignment(Pos.CENTER);

        return buttonPanel;

    }

    private static void addHunkerDown(Soldier soldier, List<Button> buttonList) {
        if (soldier.coverType.ordinal() > NONE.ordinal()) { // action requires cover
            Button hunkerButton = makeStyledButton("Hunker Down");
            hunkerButton.setOnAction(actionEvent -> {
                soldier.actionPoints = 0;
                soldier.hunkerBonus = true;
                soldier.applyHunkerBonus();
            });
            buttonList.add(hunkerButton);
        }
    }

    private static void addShootAtTarget(Soldier soldier, Alien alien, List<Button> buttonList) {
        int[] hitChance = soldier.aimAtTarget(alien);
        String buttonText = String.format("Shoot at %s [%s%s]", alien.getName(), '%', hitChance[2]);

        Button shootTarget = makeStyledButton(buttonText);
        shootTarget.setOnAction(actionEvent -> {
            soldier.actionPoints = 0;
            soldier.shootAtTarget(alien);
            if (!alien.isAlive) {
                shootTarget.getParent().getChildrenUnmodifiable().remove(shootTarget);
            }
        });
        buttonList.add(shootTarget);
    }

    private static void addMove(Soldier soldier, List<Button> buttonList) {
        Button moveButton = makeStyledButton("Move");
        moveButton.getOnMouseDragEntered();

        moveButton.setOnAction(actionEvent -> {
            Cover oldCover = soldier.coverType;
            soldier.actionPoints--;
            soldier.coverType = GetRandom.cover();
            System.out.printf("%s -> %s", oldCover, soldier.coverType);
        });

        buttonList.add(moveButton);
    }

    private static void reload(Soldier soldier, List<Button> buttonList) {
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
            buttonList.add(reloadButton);
        }
    }

    private static void bandageWounds(Soldier soldier, List<Button> buttonList) {
        if (soldier.hp < soldier.getMaxHP()) { // action requires missing health
            Button BandageButton = makeStyledButton("Bandage Wounds [+1HP]");
            BandageButton.getOnMouseDragEntered();

            BandageButton.setOnAction(actionEvent -> {
                soldier.actionPoints--;
                soldier.hp++;
            });
            buttonList.add(BandageButton);
        }
    }

    private static Button makeStyledButton(String text) {
        Button button = new Button(text);

        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #2193b0, #6dd5ed); " +
                        "-fx-background-radius: 10; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8 15;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, #f857a6, #ff5858); " +
                        "-fx-background-radius: 10; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8 15;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, #2193b0, #6dd5ed); " +
                        "-fx-background-radius: 10; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8 15;"
        ));

        return button;
    }
}
