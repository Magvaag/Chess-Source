package net.vaagen.draw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.vaagen.chess.Board;
import net.vaagen.chess.Color;
import net.vaagen.chess.Tower;

public class Main {

	static int square_size = 100;
	
	Image white = new ImageIcon("src/net/vaagen/draw/white.png").getImage();
	Image black = new ImageIcon("src/net/vaagen/draw/black.png").getImage();
	
	Image green_square = new ImageIcon("src/net/vaagen/draw/green_square.png").getImage();
	Image red_square = new ImageIcon("src/net/vaagen/draw/red_square.png").getImage();
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Main();
			}			
		});
	}
	
	Board board;
	Point[] points;
	
	public Main(){
		board = new Board(Color.Black);

		board.moveTower(0, 0, new Point(4, 4));
		
		JFrame frame = new JFrame();
		
		frame.setSize(square_size*8, square_size*8);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		Screen screen = this.new Screen();
		frame.add(screen);
		
		frame.addMouseListener(new MouseListener(){
			boolean playerWhite = true; // True then player white's go | false then player black's go
			Tower tower = null;
			int tower_x;
			int tower_y;
			
			public void mouseClicked(MouseEvent arg0) {
				
			}

			public void mouseEntered(MouseEvent arg0) {
				
			}

			public void mouseExited(MouseEvent arg0) {
				
			}

			public void mousePressed(MouseEvent e) {
				int x = e.getX() / square_size;
				int y = e.getY() / square_size;
				
				if(tower == null){
					Tower currTower = board.getTower(x, y);	
					Color color = board.getColor(x, y);	
					
					if(currTower != null){
						if((color == Color.White) == playerWhite){					
							if(tower == null){
								tower = board.getTower(x, y);
								
								points = board.getPossibleTowerMovements(color, x, y);
															
								if(points == null){
									tower = null;
								}else{
									tower_x = x;
									tower_y = y;
								}
							}else{
								tower = null;
								points = null;
							}
						}
					}else{
						tower = null;
						points = null;
					}
				}else{
					for(int i = 0; i < points.length; i++){
						if(points[i] != null){	
							if(points[i].x == x && points[i].y == y){
								board.moveTower(tower_x, tower_y, points[i]);
								playerWhite = !playerWhite;
								points = null;
								tower = null;
								break;
							}
						}
					}

					points = null;
					tower = null;
				}
			}

			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
	}
	
	private class Screen extends JPanel implements Runnable{
		Thread thread = new Thread(this);
		
		public Screen(){
			thread.start();
		}
		
		public void paintComponent(Graphics g){
			g.clearRect(0, 0, square_size*8, square_size*8);
			
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					if((y + x%2) % 2 == 0) g.drawImage(white, x * square_size, y *square_size, square_size, square_size, null);
					if((y + x%2-1) % 2 == 0) g.drawImage(black, x * square_size, y *square_size, square_size, square_size, null);
					
					if(board.getTower(x, y) != null) g.drawImage(getImageFromTower(board.getTower(x, y), board.getColor(x, y)), x * square_size, y * square_size, square_size, square_size, null);
				}	
			}
			
			if(points != null){
				for(int i = 0; i < points.length; i++){
					if(points[i] != null){
						if(board.getTower(points[i].x, points[i].y) == null){
							g.drawImage(green_square, points[i].x*square_size, points[i].y*square_size,  square_size, square_size, null);
						}else{
							g.drawImage(red_square, points[i].x*square_size, points[i].y*square_size,  square_size, square_size, null);
						}
					}
				}
			}
		}
		
		Image king_black = new ImageIcon("src/net/vaagen/draw/king_black.png").getImage();
		Image king_white = new ImageIcon("src/net/vaagen/draw/king_white.png").getImage();
		Image queen_black = new ImageIcon("src/net/vaagen/draw/queen_black.png").getImage();
		Image queen_white = new ImageIcon("src/net/vaagen/draw/queen_white.png").getImage();
		Image rook_black = new ImageIcon("src/net/vaagen/draw/rook_black.png").getImage();
		Image rook_white = new ImageIcon("src/net/vaagen/draw/rook_white.png").getImage();
		Image knight_black = new ImageIcon("src/net/vaagen/draw/knight_black.png").getImage();
		Image knight_white = new ImageIcon("src/net/vaagen/draw/knight_white.png").getImage();
		Image bishop_black = new ImageIcon("src/net/vaagen/draw/bishop_black.png").getImage();
		Image bishop_white = new ImageIcon("src/net/vaagen/draw/bishop_white.png").getImage();
		Image pawn_black = new ImageIcon("src/net/vaagen/draw/pawn_black.png").getImage();
		Image pawn_white = new ImageIcon("src/net/vaagen/draw/pawn_white.png").getImage();
		
		private Image  getImageFromTower(Tower tower, Color color){
			if(tower == Tower.King) return color == Color.Black ? king_black : king_white;
			if(tower == Tower.Queen) return color == Color.Black ? queen_black : queen_white;
			if(tower == Tower.Rooks) return color == Color.Black ? rook_black : rook_white;
			if(tower == Tower.Knights) return color == Color.Black ? knight_black : knight_white;
			if(tower == Tower.Bishops) return color == Color.Black ? bishop_black : bishop_white;
			if(tower == Tower.Pawns) return color == Color.Black ? pawn_black : pawn_white;
			
			return white;
		}
		
		public void run() {
			while(true){
				repaint();
			}
		}
		
	}
	
}
