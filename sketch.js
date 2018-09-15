'use strict';

class GOLClass {
  constructor(row, col, den) {
    this.dim = 10
    this.row = row
    this.col = col
    this.mat = this.initmatrix(den)
  }

  initmatrix(den) {
    let mat = []
    for (let row = 0; row < this.row; row++) {
      if (mat[row] === undefined) mat[row] = []
      for (let col = 0; col < this.col; col++) {
        mat[row][col] = Math.random() < den ? 1 : 0
      }
    }
    return mat
  }

  neighbours(row, col) {
    let count = 0
    for (let r = -1; r <= 1; r++)
      for (let c = -1; c <= 1; c++)
        if (r !== 0 || c !== 0)
          try { if (this.mat[row+r][col+c]) count++ } catch (e) {}
    return count
  }

  update() {
    let aux = []
    for (let row = 0; row < this.row; row++) {
      if (aux[row] === undefined) aux[row] = []
      for (let col = 0; col < this.col; col++) {
        let nei = this.neighbours(row, col)
        aux[row][col] = this.mat[row][col] ?
          ((nei === 2 || nei === 3) ? 1 : 0) : ((nei === 3) ? 1 : 0)
      }
    }
    this.mat = aux
  }

  draw() {
    const dim = height / this.row
    stroke('#0e0e0e')
    for (let row = 0; row < this.row; row++) {
      for (let col = 0; col < this.col; col++) {
        fill(this.mat[row][col] ? '#fff' : '#000')
        rect(col * dim, row * dim, dim, dim)
      }
    }
    this.update()
  }

  toggle(row, col) {
    this.mat[row][col] ^= 1
  }
}

const GOL = new GOLClass(50, 50, 0.3)

function setup() {
  createCenteredCanvas()
  frameRate(10)
  draw()
}

function windowResized() {
  createCenteredCanvas()
  draw()
}

function createCenteredCanvas() {
  const size =
    (windowWidth <= 540 || windowHeight <= 630) +
    (windowWidth <= 960 || windowHeight <= 800)
  const dim = [600, 450, 300][size]
  createCanvas(dim, dim).position(
    (windowWidth - width) / 2,
    (windowHeight - height) / 2
  )
}

function draw() {
  GOL.draw()
  drawFrame()
}

function drawFrame() {
  stroke('#000')
  strokeWeight(1)
  line(0, 0, width, 0)
  line(0, 0, 0, height)
  line(width-1, height-1, width-1, 0)
  line(width-1, height-1, 0, height-1)
}