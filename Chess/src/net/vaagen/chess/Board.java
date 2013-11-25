package net.vaagen.chess;

import java.awt.Point;

public class Board {

	private Tower[][] board = new Tower[8][8];
	private Color[][] board_color = new Color[8][8];
	
	private Color yourColor;
	private Color opponentColor;
	
	public Board(Color color){		
		yourColor = color;
		opponentColor = color == Color.Black ? Color.White : Color.Black;
		
		for(int x = 0; x < 8; x++){
			setTower(Tower.Pawns, x, 6, yourColor);
		}
		
		for(int x = 0; x < 8; x++){
			setTower(Tower.Pawns, x, 1, opponentColor);
		}
		
		setTower(Tower.Rooks, 0, 7, yourColor);
		setTower(Tower.Rooks, 7, 7, yourColor);
		setTower(Tower.Knights, 1, 7, yourColor);
		setTower(Tower.Knights, 6, 7, yourColor);
		setTower(Tower.Bishops, 2, 7, yourColor);
		setTower(Tower.Bishops, 5, 7, yourColor);
		setTower(Tower.Queen, 3, 7, yourColor);
		setTower(Tower.King, 4, 7, yourColor);
		
		setTower(Tower.Rooks, 0, 0, opponentColor);
		setTower(Tower.Rooks, 7, 0, opponentColor);
		setTower(Tower.Knights, 1, 0, opponentColor);
		setTower(Tower.Knights, 6, 0, opponentColor);
		setTower(Tower.Bishops, 2, 0, opponentColor);
		setTower(Tower.Bishops, 5, 0, opponentColor);
		setTower(Tower.Queen, 3, 0, opponentColor);
		setTower(Tower.King, 4, 0, opponentColor);
	}
	
	public void setTower(Tower tower, int x, int y, Color color){
		board[x][y] = tower;
		board_color[x][y] = color;
	}
	
	public Tower getTower(int x, int y){
		return board[x][y];
	}
	
	public Color getColor(int x, int y){
		return board_color[x][y];
	}
	
	public Point[] getPossibleTowerMovements(Color color, int x, int y){
		Tower tower = getTower(x, y);
		Point[] points = new Point[50];
		
		int currentPoint = 0;
		
		//PAWNS
		if(tower == Tower.Pawns){
			if(color == yourColor){
				if(y > 0){
					if(getTower(x, y-1) == null) points[0] = new Point(x, y-1);
					
					if(x < 7){
						if(getTower(x+1, y-1) != null && getColor(x+1, y-1) != color) points[2] = new Point(x+1, y-1);
					}
					
					if(x > 0){
						if(getTower(x-1, y-1) != null && getColor(x-1, y-1) != color) points[3] = new Point(x-1, y-1);
					}
				}
				
				if(y == 6){
					if(getTower(x, y-2) == null) points[1] = new Point(x, y-2);
				}
			}else{
				if(y > 0){
					if(getTower(x, y+1) == null) points[0] = new Point(x, y+1);
					
					if(x < 7){
						if(getTower(x+1, y+1) != null && getColor(x+1, y+1) != color) points[2] = new Point(x+1, y+1);
					}
					
					if(x > 0){
						if(getTower(x-1, y+1) != null && getColor(x-1, y+1) != color) points[3] = new Point(x-1, y+1);
					}
				}
				
				if(y == 1){
					if(getTower(x, y+2) == null) points[1] = new Point(x, y+2);
				}
			}
		}
			
			
		//TOWER
		if(tower == Tower.Rooks){
			for(int x1 = x+1; x1 < 8; x1++){
				points[currentPoint] = new Point(x1, y);
				
				if(getColor(x1, y) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x1, y) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int x1 = x-1; x1 >= 0; x1--){
				points[currentPoint] = new Point(x1, y);
				
				if(getColor(x1, y) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x1, y) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int y1 = y+1; y1 < 8; y1++){
				points[currentPoint] = new Point(x, y1);
				
				if(getColor(x, y1) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x, y1) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int y1 = y-1; y1 >= 0; y1--){
				points[currentPoint] = new Point(x, y1);
				
				if(getColor(x, y1) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x, y1) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
		}
		
		//QUEEN
		if(tower == Tower.Queen){
			for(int x1 = x+1; x1 < 8; x1++){
				points[currentPoint] = new Point(x1, y);
				
				if(getColor(x1, y) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x1, y) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int x1 = x-1; x1 >= 0; x1--){
				points[currentPoint] = new Point(x1, y);
				
				if(getColor(x1, y) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x1, y) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int y1 = y+1; y1 < 8; y1++){
				points[currentPoint] = new Point(x, y1);
				
				if(getColor(x, y1) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x, y1) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int y1 = y-1; y1 >= 0; y1--){
				points[currentPoint] = new Point(x, y1);
				
				if(getColor(x, y1) == color){
					points[currentPoint] = null;
					break;
				}else if(getTower(x, y1) != null){
					currentPoint++;
					break;
				}
				
				currentPoint++;
			}
			
			for(int i = 1; i < 8; i++){
				if(x + i < 8 && y + i < 8){
					if(getColor(x + i, y + i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x + i, y + i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x + i < 8 && y - i >= 0){
					if(getColor(x + i, y - i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x + i, y - i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x - i >= 0 && y - i >= 0){
					if(getColor(x - i, y - i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x - i, y - i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x - i >= 0 && y + i < 8){
					if(getColor(x - i, y + i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x - i, y + i);
					currentPoint++;
				}
			}
		}
		
		//KNIGHT
		if(tower == Tower.Knights){
			int x1 = 0;
			int y1 = 0;
			
			for(int i = 0; i < 8; i++){
				if(i == 0 || i == 3) x1 = 1;
				if(i == 1 || i == 2) x1 = 2;
				if(i == 4 || i == 7) x1 = -1;
				if(i == 5 || i == 6) x1 = -2;
				
				if(i == 0 || i == 7) y1 = -2;
				if(i == 1 || i == 6) y1 = -1;
				if(i == 2 || i == 5) y1 = 1;
				if(i == 3 || i == 4) y1 = 2;
				
				if(x + x1 >= 0 && y + y1 >= 0 && x + x1 < 8 && y + y1 < 8 && getColor(x+x1, y+y1) != color){
					points[currentPoint] = new Point(x + x1, y + y1);
					currentPoint++;
				}
			}
		}
		
		//BISHOPS
		if(tower == Tower.Bishops){
			for(int i = 1; i < 8; i++){
				if(x + i < 8 && y + i < 8){
					if(getColor(x + i, y + i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x + i, y + i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x + i < 8 && y - i >= 0){
					if(getColor(x + i, y - i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x + i, y - i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x - i >= 0 && y - i >= 0){
					if(getColor(x - i, y - i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x - i, y - i);
					currentPoint++;
				}
			}
			
			for(int i = 1; i < 8; i++){
				if(x - i >= 0 && y + i < 8){
					if(getColor(x - i, y + i) == color){
						break;
					}
					
					points[currentPoint] = new Point(x - i, y + i);
					currentPoint++;
				}
			}
		}
		
		//KING
		if(tower == Tower.King){
			if(y+1 < 8 && getColor(x, y+1) != color) points[0] = new Point(x, y+1);
			if(y-1 >= 0 && getColor(x, y-1) != color) points[1] = new Point(x, y-1);
			if(y+1 < 8 && x+1 < 8 && getColor(x+1, y+1) != color) points[2] = new Point(x+1, y+1);
			if(y-1 >= 0 && x+1 < 8 && getColor(x+1, y) != color) points[3] = new Point(x+1, y);
			if(y-1 >= 0 && x+1 < 8 && getColor(x+1, y-1) != color) points[4] = new Point(x+1, y-1);
			if(y+1 < 8 && x-1 >= 0 && getColor(x-1, y+1) != color) points[5] = new Point(x-1, y+1);
			if(y-1 >= 0 && x-1 >= 0 && getColor(x-1, y) != color) points[6] = new Point(x-1, y);
			if(y-1 >= 0 && x-1 >= 0 && getColor(x-1, y-1) != color) points[7] = new Point(x-1, y-1);
		}
		
		for(int i = 0; i < points.length; i++){
			if(points[i] != null){
				return points;
			}
		}
		
		return null;
	}		
	
	public void moveTower(int x, int y, Point point){
		board[point.x][point.y] = getTower(x, y);
		board_color[point.x][point.y] = getColor(x, y);
		
		board[x][y] = null;
		board_color[x][y] = null;
	}
	
	public void setBoard(Tower[][] board){
		this.board = board;
	}
	
}
