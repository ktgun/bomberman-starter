package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utilities.SetTimeout;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void moveTo(int x, int y) {
        for (Entity element: BombermanGame.stillObjects) {
            if (element.getClass().getTypeName()
                    .equals("uet.oop.bomberman.entities.Grass")) {
                continue;
            }
            if (element.existOn(x, y)
                    || element.existOn(x, y + Sprite.SCALED_SIZE - 1)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1, y)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1,
                        y + Sprite.SCALED_SIZE - 1)) {
                return;
            }
        }
        this.x = x;
        this.y = y;
    }

    public void moveRight(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x + 5, y);
    }

    public void moveLeft(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x - 5, y);
    }

    public void moveUp(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x, y - 5);
    }

    public void moveDown(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x, y + 5);
    }

    public void planBomb(List<Entity> entities,
                         List<Entity> stillObjects) {
        int xBomb = (int) Math.round((1.0 * x / Sprite.SCALED_SIZE) / 1.0);
        int yBomb = (int) Math.round((1.0 * y / Sprite.SCALED_SIZE) / 1.0);
        Bomb bomb = new Bomb(xBomb, yBomb, Sprite.bomb.getFxImage());
//        entities.add(bomb);
        BombermanGame.updateQueue.add(bomb);
        bomb.active();

        SetTimeout.run(() -> {
//            entities.remove(bomb);
            bomb.setVisible(false);
            bomb.deactivate();
            bomb.explode(xBomb, yBomb);
        }, 2000);
    }

    @Override
    public void update() {

    }
}
