public class Main extends Ucigame
{
	public static final double G = .2; // gravity
	public static final int FLOOR = 500;
	
	private Brick[] bricks;
	private Platform platform;
	private Player player;
	
	void setup()
	{
		framerate(60);
		window.size(800,600);
		window.title("John's Hopkins");
		window.showFPS();
		canvas.background(0,125,255);
		
		bricks = new Brick[3];
		bricks[0] = new Brick(300,400);
		bricks[1] = new Brick(20, 30);
		bricks[2] = new Brick(80,90);
		
		platform = new Platform(500,450);
		
		player = new Player(20, 538);
	}
	
	void draw()
	{
		canvas.clear();
		canvas.color(20,200,100);
		drawrect(0,FLOOR, 800,600);
		
		for (Brick b : bricks) b.draw();
		player.draw();
		platform.draw();
	}
	
	private void drawrect(int x1, int y1, int x2, int y2)
	{
		for (int i = y1; i <= y2; i++)
			canvas.line(x1, i, x2, i);
	}
	
	void onKeyPress()
	{
		if (keyboard.key() == keyboard.F)
			player.moveRight();
		else if (keyboard.key() == keyboard.S)
			player.moveLeft();
		else if (keyboard.key() == keyboard.SPACE)
			player.jump();
//		else if (keyboard.key() == keyboard.E)
//			player.move(0,-Player.MOVE_SPEED);
//		else if (keyboard.key() == keyboard.D)
//			player.move(0,Player.MOVE_SPEED);
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////
	// ---------------------------- Sprites --------------------------------- //
	////////////////////////////////////////////////////////////////////////////
	
	private class Player
	{
		public static final int MOVE_SPEED = 5;
		public static final int JUMP_SPEED = -5;
		
		private USprite sprite;
		private double x;
		private double y;
		private double yv; // y-velocity
		private boolean grounded; // Are we standing or not?
		
		public Player(int x, int y)
		{
			sprite = makeSprite(getImage("images/player.png", 255));
			sprite.position(x, y);
			
			this.x = x;
			this.y = y;
			
			grounded = false;
		}
		
		public void draw()
		{
			y += yv;  // Take velocity into account;
			yv += G;  // Adjust for gravity

			if (y > FLOOR)
			{
				grounded = true;
				System.out.printf("At (%1.2f,%1.2f) and setting yv to 0 (was %1.2f).\n", x, y, yv);
				yv = 0;
			}
			else
			{
				grounded = false;
			}

			sprite.position(this.x, this.y);
			sprite.draw();
		}
		
		public void move(int x, int y)
		{
			this.x += x;
			this.y += y;
		}
		
		public void moveLeft()
		{
			move(-MOVE_SPEED, 0);
		}
		
		public void moveRight()
		{
			move(MOVE_SPEED, 0);
		}

		public void jump()
		{
			if (grounded) yv = JUMP_SPEED;
		}
	}
	
	/**
	 * This is on of the Bricks you see magically floating around.
	 * @author Andrew
	 */
	private class Brick
	{
		private USprite sprite;
//		private int x;
//		private int y;
		
		public Brick(int x, int y)
		{
			sprite = makeSprite(getImage("images/brick.png", 255));
			sprite.position(x,y);
//			this.x = x;
//			this.y = y;
		}
		
		public void draw()
		{
			sprite.draw();
		}
	}
	
	private class Platform
	{
		private USprite sprite;
		
		public Platform(int x, int y)
		{
			sprite = makeSprite(getImage("images/platform.png", 255));
			sprite.position(x,y);
		}
		
		public void draw()
		{
			sprite.draw();
		}
	}
}
