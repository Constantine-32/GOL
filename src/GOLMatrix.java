import processing.core.PApplet;

public class GOLMatrix {
  private PApplet p;
  private int[][] MAT;
  private int[][] AUX;

  public GOLMatrix(PApplet p) {
    this.p = p;
    MAT = new int[Sketch.COL][Sketch.ROW];
    AUX = new int[Sketch.COL][Sketch.ROW];
    for (int x = 0; x < Sketch.COL; x++) {
      for (int y = 0; y < Sketch.ROW; y++) {
//        MAT[x][y] = Math.round(p.random(0, 1));
        MAT[x][y] = 0;
      }
    }
  }

  public void toggleTile(int x, int y) {
    MAT[x][y] ^= 1;
  }

  public void drawStatic() {
    for (int x = 0; x < Sketch.COL; x++) {
      for (int y = 0; y < Sketch.ROW; y++) {
        if (MAT[x][y] == 1) p.fill(0);
        else p.fill(192);
        p.rect(x*Sketch.DIM, y*Sketch.DIM, Sketch.DIM, Sketch.DIM);
      }
    }
  }

  public void draw() {
    for (int x = 0; x < Sketch.COL; x++) {
      for (int y = 0; y < Sketch.ROW; y++) {
        if (MAT[x][y] == 1) p.fill(0);
        else p.fill(192);
        p.rect(x*Sketch.DIM, y*Sketch.DIM, Sketch.DIM, Sketch.DIM);
      }
    }
    update();
  }

  private int neighbours(int x, int y) {
    int C = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if ((i != 0 || j != 0) && x+i >= 0 && x+i < Sketch.COL && y+j >= 0 && y+j < Sketch.ROW && MAT[x+i][y+j] == 1) C++;
      }
    }
    return C;
  }

  private int neighboursC(int x, int y) {
    int C = 0;
    int x1;
    int y1;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        x1 = x+i;
        y1 = y+j;
        if (x1 < 0) x1 = Sketch.COL-1;
        if (x1 >= Sketch.COL) x1 = 0;
        if (y1 < 0) y1 = Sketch.ROW-1;
        if (y1 >= Sketch.ROW) y1 = 0;
        if ((i != 0 || j != 0) && MAT[x1][y1] == 1) C++;
      }
    }
    return C;
  }

  private void update() {
    for (int x = 0; x < Sketch.COL; x++) {
      for (int y = 0; y < Sketch.ROW; y++) {
        if (MAT[x][y] == 1) {
          if (neighboursC(x, y) == 2 || neighboursC(x, y) == 3) AUX[x][y] = 1;
          else AUX[x][y] = 0;
        } else {
          if (neighboursC(x, y) == 3) AUX[x][y] = 1;
          else AUX[x][y] = 0;
        }
      }
    }
    for (int x = 0; x < Sketch.COL; x++) {
      System.arraycopy(AUX[x], 0, MAT[x], 0, Sketch.ROW);
    }
  }

  private void LWSS(int x, int y) {
    MAT[x+2][y+0] = 1;
    MAT[x+3][y+0] = 1;
    MAT[x+0][y+1] = 1;
    MAT[x+1][y+1] = 1;
    MAT[x+3][y+1] = 1;
    MAT[x+4][y+1] = 1;
    MAT[x+0][y+2] = 1;
    MAT[x+1][y+2] = 1;
    MAT[x+2][y+2] = 1;
    MAT[x+3][y+2] = 1;
    MAT[x+1][y+3] = 1;
    MAT[x+2][y+3] = 1;
  }

  private void PulsarP3(int x, int y) {
    MAT[x+2][y+0] = 1;
    MAT[x+3][y+0] = 1;
    MAT[x+4][y+0] = 1;
    MAT[x+8][y+0] = 1;
    MAT[x+9][y+0] = 1;
    MAT[x+10][y+0] = 1;
    MAT[x+0][y+2] = 1;
    MAT[x+5][y+2] = 1;
    MAT[x+7][y+2] = 1;
    MAT[x+12][y+2] = 1;
    MAT[x+0][y+3] = 1;
    MAT[x+5][y+3] = 1;
    MAT[x+7][y+3] = 1;
    MAT[x+12][y+3] = 1;
    MAT[x+0][y+4] = 1;
    MAT[x+5][y+4] = 1;
    MAT[x+7][y+4] = 1;
    MAT[x+12][y+4] = 1;
    MAT[x+2][y+5] = 1;
    MAT[x+3][y+5] = 1;
    MAT[x+4][y+5] = 1;
    MAT[x+8][y+5] = 1;
    MAT[x+9][y+5] = 1;
    MAT[x+10][y+5] = 1;
    MAT[x+2][y+7] = 1;
    MAT[x+3][y+7] = 1;
    MAT[x+4][y+7] = 1;
    MAT[x+8][y+7] = 1;
    MAT[x+9][y+7] = 1;
    MAT[x+10][y+7] = 1;
    MAT[x+0][y+8] = 1;
    MAT[x+5][y+8] = 1;
    MAT[x+7][y+8] = 1;
    MAT[x+12][y+8] = 1;
    MAT[x+0][y+9] = 1;
    MAT[x+5][y+9] = 1;
    MAT[x+7][y+9] = 1;
    MAT[x+12][y+9] = 1;
    MAT[x+0][y+10] = 1;
    MAT[x+5][y+10] = 1;
    MAT[x+7][y+10] = 1;
    MAT[x+12][y+10] = 1;
    MAT[x+2][y+12] = 1;
    MAT[x+3][y+12] = 1;
    MAT[x+4][y+12] = 1;
    MAT[x+8][y+12] = 1;
    MAT[x+9][y+12] = 1;
    MAT[x+10][y+12] = 1;
  }
}
