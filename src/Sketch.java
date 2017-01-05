import processing.core.PApplet;

public class Sketch extends PApplet {

  static final int W = 1200;
  static final int H = 600;
  static final int DIM = 5;
  static final int COL = W / DIM;
  static final int ROW = H / DIM;

  private static boolean play = false;
  private GOLMatrix matrix = new GOLMatrix(this);

  public static void main(String[] args) {
    PApplet.main(new String[]{"Sketch"});
  }

  public void settings() {
    size(W, H);
  }

  public void setup() {
    frameRate(15);
    noStroke();
    matrix.drawStatic();
  }

  public void draw() {
    if (play) matrix.draw();
    else matrix.drawStatic();
  }

  public void mouseClicked() {
    matrix.toggleTile(mouseX / DIM, mouseY / DIM);
  }

  public void keyPressed() {
    play = !play;
  }
}
