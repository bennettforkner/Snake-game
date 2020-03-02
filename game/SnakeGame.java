package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SnakeGame {
	private int snakeLength;
	//1 = north
	//2 = east
	//3 = south
	//4 = west
	private int direction;
	
	private boolean over;
	
	public boolean[][] board = new boolean[30][30];
	
	private boolean[][] food = new boolean[30][30];
	
	private int x;
	private int y;
	
	private SnakeFace face;
	
	public ArrayList<SnakePiece> pieces = new ArrayList<>();
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	
	public SnakeGame(SnakeFace sF) {
		this.face = sF;
		snakeLength = 10;
		direction = 1;
		x = 15;
		y = 25;
		
		/*face.buttonMap.get("^").addActionListener(new DirectionListener('^',this));
		face.buttonMap.get(">").addActionListener(new DirectionListener('>',this));
		face.buttonMap.get("v").addActionListener(new DirectionListener('v',this));
		face.buttonMap.get("<").addActionListener(new DirectionListener('<',this));*/
		
	}
	
	public void endGame() {
		over = true;
		face.printGameOver();
		face.buttonMap.get("^").setVisible(true);
		face.buttonMap.get(">").setVisible(true);
		face.buttonMap.get("v").setVisible(true);
		face.buttonMap.get("<").setVisible(true);
		face.buttonMap.get("RESET").setVisible(true);
		
		while (over) {try {face.printGameOver(); Thread.sleep(700); face.clearGameOver(); Thread.sleep(600);} catch (Exception e) {}} play();
	}
	
	public void changeDirection(int direction) {
		boolean possible = false;
		if (this.direction == 1 && direction == 2 || direction == 4)
			possible = true;
		else if (this.direction == 2 && direction == 1 || direction == 3)
			possible = true;
		else if (this.direction == 3 && direction == 2 || direction == 4)
			possible = true;
		else if (this.direction == 4 && direction == 1 || direction == 3)
			possible = true;
		if (possible)
			this.direction = direction;
	}
	
	public void eatOrb() {
		snakeLength++;
	}
	private int rx;
	private int ry;
	public void play() {
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(new Color(255,0,0));
		colors.add(new Color(255,128,0));
		colors.add(new Color(245,245,0));
		colors.add(new Color(0,170,0));
		colors.add(new Color(0,0,255));
		colors.add(new Color(128,0,255));
		
		
		
		
		
		
		
		face.buttonMap.get("^").setVisible(false);
		face.buttonMap.get(">").setVisible(false);
		face.buttonMap.get("v").setVisible(false);
		face.buttonMap.get("<").setVisible(false);
		face.buttonMap.get("RESET").addActionListener(new ClearListener(this));
		face.buttonMap.get("RESET").addKeyListener(new ArrowKey(this));
		face.clearGameOver();
		face.buttonMap.get("RESET").setVisible(true);
		/*face.buttonMap.get("^").setVisible(true);
		face.buttonMap.get(">").setVisible(true);
		face.buttonMap.get("v").setVisible(true);
		face.buttonMap.get("<").setVisible(true);*/
		int count = 0;
		int time = 0;
		while (!over) {
			count++;
			if (count >= 4) {
				count = 0;
				time++;
				face.setTime(time);
			}
			boolean isFood = false;
			for (int r = 0; r < 30; r++) {
				for (int c = 0; c < 30; c++) {
					if (food[r][c]) {
						isFood = true;
						foodAge++;
						face.screen[r][c].setBackground(colors.get((foodAge) % 6));
						face.screen[r][c].setBorder(BorderFactory.createLineBorder(colors.get(6 - (((foodAge) % 6) + 1)),1,true));
					}
				}
			}
			if (!isFood) {
				boolean snakeHere = false;
				rx = (int)(Math.random() * 30);
				ry = (int)(Math.random() * 30);
				for (SnakePiece i: pieces) {
					if (i.x == rx && i.y == ry) {
						snakeHere = true;
					}
				}
				while (snakeHere) {
					rx = (int)(Math.random() * 30);
					ry = (int)(Math.random() * 30);
					snakeHere = false;
					for (SnakePiece i: pieces) {
						if (i.x == rx && i.y == ry) {
							snakeHere = true;
						}
					}
				}
				isFood = true;
				food[ry][rx] = true;
				//face.screen[ry][rx].setBackground(new Color(255,0,155));
				//face.screen[ry][rx].setBorder(BorderFactory.createLineBorder(new Color(255,0,155), 1));
				
			}
			
			if (direction == 1) {
				y--;
			} else if (direction == 2) {
				x++;
			} else if (direction == 3) {
				y++;
			} else if (direction == 4) {
				x--;
			} else {System.err.println("NoDirection Exception: Java.SnakeGame.36");}
			
			if (x >= 30 || x < 0 || y >= 30 || y < 0) {
				endGame();
			}
			else {
				for (int i = 0; i < pieces.size(); i++) {
					if (pieces.get(i).x == x && pieces.get(i).y == y) {
						endGame();
					}
				}
				face.screen[y][x].setBackground(new Color(0,255,0));
				face.screen[y][x].setBorder(BorderFactory.createLineBorder(new Color(0,255,0),1));
				if (food[y][x]) {
					this.snakeLength+=10;
					face.setScore(face.getScore() + 1);
					food[y][x] = false;
				}
				pieces.add(new SnakePiece(x,y,face,this));
				for (int i = 0; i < pieces.size(); i++) {
					pieces.get(i).checkAge(this.snakeLength);
				}
				/*face.screen[rx][ry].setBackground(new Color(255,0,155));
				face.screen[rx][ry].setBorder(BorderFactory.createLineBorder(new Color(255,0,155), 1));*/
				
			} 
			
			
			try {Thread.sleep(200);} catch (Exception i) {}
		}
		endGame();
	}
	private boolean[][] canMoveTo = new boolean[30][30];
	private int foodAge;
	public void computerPlay() {
		face.buttonMap.get("RESET").setVisible(true);
		face.buttonMap.get("^").setVisible(false);
		face.buttonMap.get(">").setVisible(false);
		face.buttonMap.get("v").setVisible(false);
		face.buttonMap.get("<").setVisible(false);
		int count = 0;
		int time = 0;
		while (!over) {
			
			for (int r = 0; r < canMoveTo.length; r++) {
				for (int c = 0; c < canMoveTo[r].length; c++) {
					canMoveTo[r][c] = true;
				}
			}
			for (SnakePiece piece: pieces) {
				canMoveTo[piece.y][piece.x] = false;
			}
			if (y-1 >= 0 && x-1 >= 0 && x+1 < 30 && y+1 < 30) {
				if (!canMoveTo[y-1][x] && !canMoveTo[y][x-1] && !canMoveTo[y+1][x] && !canMoveTo[y][x+1])
					clearGame();
			}
			//Timing
			count++;
			if (count >= 4) {
				count = 0;
				time++;
				face.setTime(time);
			//Setting Food
			boolean isFood = false;
			for (int r = 0; r < 30; r++) {
				for (int c = 0; c < 30; c++) {
					if (food[r][c]) {
						isFood = true;
						foodAge = 10;
						face.screen[r][c].setBackground(new Color(foodAge,255 - foodAge, 100));
					}
				}
			}
			if (!isFood) {
				boolean snakeHere = false;
				int rx = (int)(Math.random() * 30);
				int ry = (int)(Math.random() * 30);
				for (SnakePiece i: pieces) {
					if (i.x == rx && i.y == ry) {
						snakeHere = true;
					}
				}
				while (snakeHere) {
					rx = (int)(Math.random() * 30);
					ry = (int)(Math.random() * 30);
					snakeHere = false;
					for (SnakePiece i: pieces) {
						if (i.x == rx && i.y == ry) {
							snakeHere = true;
						}
					}
				}
				isFood = true;
				food[ry][rx] = true;
				
			}
			
			
			canMoveTo = new boolean[30][30];
			for (int r = 0; r < canMoveTo.length; r++) {
				for (int c = 0; c < canMoveTo[r].length; c++) {
					canMoveTo[r][c] = true;
				}
			}
			for (SnakePiece piece: pieces) {
				canMoveTo[piece.y][piece.x] = false;
			}
			int fX = 0,fY = 0;
			for (int r = 0; r < food.length; r++) {
				for (int c = 0; c < food[r].length; c++) {
					if (food[r][c]) {
						fX = c;
						fY = r;
					}
				}
			}
			
			if (y == fY) {
				if (fX > x) {
					direction = 2;
				} else if (fX < x) {
					direction = 4;
				}
			}
			else if (x == fX) {
				if (fY > y) {
					direction = 3;
				} else if (fY < y) {
					direction = 1;
				}
			}
			
			while (adjustDirection()) {}
			
			
			if (x >= 30 || x < 0 || y >= 30 || y < 0) {
				endGame();
			}
			else {
				for (int i = 0; i < pieces.size(); i++) {
					if (pieces.get(i) != null && pieces.get(i).x == x && pieces.get(i).y == y) {
						endGame();
					}
				}
				face.screen[y][x].setBackground(new Color(0,255,0));
				face.screen[y][x].setBorder(BorderFactory.createLineBorder(new Color(0,220,0),1));
				if (food[y][x]) {
					this.snakeLength+=10;
					face.setScore(face.getScore() + 1);
					food[y][x] = false;
				}
				pieces.add(new SnakePiece(x,y,face,this));
				for (int i = 0; i < pieces.size(); i++) {
					pieces.get(i).checkAge(this.snakeLength);
				}

			}
			
			
			try {Thread.sleep(10);} catch (Exception i) {}
		}}
		endGame();
	}

	private boolean adjustDirection() {
		SnakePiece p1 = null;
		SnakePiece p2 = null;
		int lessZeroX;
		int lessZeroY;
		int lessZero;
		int lessZero2;
		int option;
		int greaterZero;
		int less30;
		if (direction == 1) {
			greaterZero = y-1;
			less30 = y+1;
			lessZero = lessZeroY = y-1;
			lessZero2 = lessZeroX = x;
			option = 2;
		} else if (direction == 2) {
			greaterZero = x-1;
			less30 = x+1;
			lessZero = lessZeroX = x+1;
			lessZero2 = lessZeroY = y;
			option = 1;
		} else if (direction == 3) {
			greaterZero = y-1;
			less30 = y+1;
			lessZero = lessZeroY = y+1;
			lessZero2 = lessZeroX = x;
			option = 2;
		} else {
			greaterZero = x-1;
			less30 = x+1;
			lessZero = lessZeroX = x-1;
			lessZero2 = lessZeroY = y;
			option = 1;
		}
		
		if (x < 0) {
			if (y < 0) {
				direction = 2;
				return true;
			} else if (y >= 30) {
				return true;
			}
		} else if (x >= 30){
			if (y < 0) {
				return true;
			} else if (y >= 30) {
				return true;
			}
		}
		
		
			if ((direction == 2 || direction == 3) && lessZero >= 30) {
				
				
				if (lessZero2 <= 15) {
					direction = option;
				}
				else direction = option + 2;
				
				
				
			} else if ((direction == 1 || direction == 4) && lessZero < 0) {
				
				
				if (lessZero2 <= 15) {
					direction = option + 2;
				}
				else direction = option;
				
				
				
			} else if (lessZeroY >= 0 && lessZeroX >= 0 && lessZeroY < 30 && lessZeroX < 30 && !canMoveTo[lessZeroY][lessZeroX]) {
				for (int i = 0; i < pieces.size(); i++) {
					if (lessZeroX == x) {
						if (i < pieces.size() && pieces.get(i) != null && pieces.get(i).x == x-1 && pieces.get(i).y == lessZeroY) {
							p1 = pieces.get(i);
						} else if (i < pieces.size() && pieces.get(i) != null && pieces.get(i).x == x+1 && pieces.get(i).y == lessZeroY) {
							p2 = pieces.get(i);
						}
					} else if (lessZeroY == y) {
						if (i < pieces.size() && pieces.get(i) != null && pieces.get(i).x == lessZeroX && pieces.get(i).y == y-1) {
							p1 = pieces.get(i);
						} else if (i < pieces.size() && pieces.get(i) != null && pieces.get(i).x == lessZeroX && pieces.get(i).y == y+1) {
							p2 = pieces.get(i);
						}
					}
				}
				if (p1 == null) {
					if (greaterZero >= 0)
						direction = option + 2;
					else
						direction = option;
				}
				else if (p2 == null) {
					if (less30 < 30)
						direction = option;
					else
						direction = option + 2;
				}
				else if (p1.age > p2.age) {
					direction = option + 2;
				}
				else direction = option;
			} else {
				if (direction == 1) {
					y--;
					System.out.println("d: " + 1);
				} else if (direction == 2) {
					x++;
					System.out.println("d: " + 2);
				} else if (direction == 3) {
					y++;
					System.out.println("d: " + 3);
				} else {
					x--;
					System.out.println("d: " + 4);
				}
				return false;
			}
			return true;
		
	}
	public void clearGame() {
		long z = 0;
		face.setScore(z);
		face.setTime(z);
		snakeLength = 20;
		direction = 1;
		x = 15;
		y = 25;
		pieces.clear();
		for (int r = 0; r < 30; r++) {
			for (int c = 0; c < 30; c++) {
				food[r][c] = false;
				face.screen[r][c].setBackground(new Color(255, 255, 255));
				face.screen[r][c].setBorder(BorderFactory.createLineBorder(new Color(245,245,245),1));
			}
		}
		try {Thread.sleep(300);} catch (Exception e) {}
		if (over) {
			over = false;
		}
	}
	public int getDirection() {
		return direction;
	}
}
