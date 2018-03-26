'use strict';

class GOL {
  constructor(dim, row, col, den) {
    this.dim = dim
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
    for (let row = 0; row < this.row; row++) {
      for (let col = 0; col < this.col; col++) {
        fill(this.mat[row][col] ? '#fff' : '#000')
        rect(col * this.dim, row * this.dim, this.dim, this.dim)
      }
    }
    this.update()
  }

  toggle(row, col) {
    this.mat[row][col] ^= 1
  }
}

const dim = 10
const col = 40
const row = 40
const gol = new GOL(dim, row, col, 0.3)

let cnv

function centerCanvas() {
  let x = (windowWidth - width) / 2
  let y = (windowHeight - height) / 2
  cnv.position(x, y)
}

function setup() {
  cnv = createCanvas(col * dim + 1, row * dim + 1)
  centerCanvas()
  background(255, 0, 200)
  frameRate(10)
  stroke('#111')
  gol.draw()
}

function windowResized() {
  centerCanvas()
}

function draw() {
  gol.draw()
}
