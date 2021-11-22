package uet.oop.bomberman.entities.buffItems;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public Bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_bombs.getFxImage());
    }

    public void update() {

    }
}
