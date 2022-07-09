package Entities;

import java.awt.Image;

public class WaterBorder extends GameObject {

  private boolean collision = true;

  public WaterBorder(int x, int y, int width, int height, Image image) {
    super(x, y, width, height, image);
  }

  public boolean getCollision() {
    return collision;
  }
}
