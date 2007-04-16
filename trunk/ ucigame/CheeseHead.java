public class CheeseHead extends Ucigame
{
	public static final double G = .5; // gravity
	public static final int FLOOR = 500;
	public static final int FPS = 20;

//	private Brick[] bricks;
//	private Platform[] platforms;
//	private Player player;
//	private Door door;
//	private Pooper pooper;
	
	Stage[] stages;
	
	private int frame;   // The frame we're on.
	private int currentStage = 0;
	
	
	void setup()
	{
		framerate(FPS);
		window.size(800,600);
		window.title("Cheese Head");
//		window.showFPS();
		canvas.background(0,125,255);
		
		frame = 0;
		
		stages = new Stage[1];
		stages[0] = new Stage();
		stages[0].addBrick(300,400);
		stages[0].addBrick(550, 300);
//		stages[0].addBrick(80,90);
		stages[0].addPlatform(500,450);
		stages[0].addPlatform(400,400);
		stages[0].addPlatform(300,350);
		stages[0].addPlatform(200,300);
		stages[0].setDoor(250, 229);
		stages[0].setPlayer(20, 438);
		stages[0].setPooper(400,322);
		
		startScene("MainGame");
	}
	
	
	/**
	 * What to do when the player has reached the goal door.
	 */
	private void stageComplete()
	{
//		System.out.println("Arrived at the goal!!!");
		if (++currentStage >= stages.length)
			startScene("Score");
		
	}
	
//	void start()
//	{
//		bricks = new Brick[3];
//		bricks[0] = new Brick(300,400);
//		bricks[1] = new Brick(20, 30);
//		bricks[2] = new Brick(80,90);
//		
//		platforms = new Platform[4];
//		platforms[0] = new Platform(500,450);
//		platforms[1] = new Platform(400,400);
//		platforms[2] = new Platform(300,350);
//		platforms[3] = new Platform(200,300);
//		
//		door = new Door(250, 229);
//
//		pooper = new Pooper(400,322);
//
//		player = new Player(20, 438);//	}

	
	void startScore()
	{
		canvas.clear();
		canvas.color(0);
//		canvas.font("Times", BOLD, 20);
		canvas.putText("ehm ... you win?", 300, 300);
	}
	
	
	void drawScore()
	{
	}
	
	void startMainGame()
	{
		
	}
	
	void drawMainGame()
	{
		frame++;

		canvas.clear();
		canvas.color(20,200,100);
		drawrect(0,FLOOR+1, 800,600);
		canvas.color(0);
		canvas.line(0,FLOOR, 800,FLOOR);
		
		stages[currentStage].draw();
//		canvas.color(0);
//		drawcross(20,438,10);
	}

	
	private void drawrect(int x1, int y1, int x2, int y2)
	{
		for (int i = y1; i <= y2; i++)
			canvas.line(x1, i, x2, i);
	}
	
	private void drawcross(int x, int y, int l)
	{
		canvas.line(x-l, y, x+l, y);
		canvas.line(x, y-l, x, y+l);
	}

	void onKeyPressMainGame()
	{
		if (keyboard.key() == keyboard.F || keyboard.key() == keyboard.RIGHT)
			stages[currentStage].player().moveRight();
		else if (keyboard.key() == keyboard.S || keyboard.key() == keyboard.LEFT)
			stages[currentStage].player().moveLeft();
		else if (keyboard.key() == keyboard.SPACE)
			stages[currentStage].player().jump();
//		else if (keyboard.key() == keyboard.D)
//			System.out.printf("player:(%d,%d)  door:(%d,%d)\n", player.x(), player.y(), door.x(), door.y());
//		else if (keyboard.key() == keyboard.E)
//			player.move(0,-Player.MOVE_SPEED);
//		else if (keyboard.key() == keyboard.D)
//			player.move(0,Player.MOVE_SPEED);
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	// ----------------------------- Stages --------------------------------- //
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The abstract way to store the makeup of a stage.
	 */
	private class Stage
	{
		private Brick[] bricks;
		private Platform[] platforms;
		private Player player;
		private Door door;
		private Pooper pooper;
		
		private int numBricks;
		private int numPlatforms;
		
		public Stage()
		{
			bricks = new Brick[10];
			platforms = new Platform[10];
			
			numBricks = 0;
			numPlatforms = 0;
		}
		
		public void addBrick(int x, int y)
		{
			if (numBricks >= bricks.length)
			{
				Brick[] temp = new Brick[bricks.length * 2];
				for (int i = 0; i < bricks.length; i++)
					temp[i] = bricks[i];
				bricks = temp;
			}			bricks[numBricks++] = new Brick(x,y);
		}
		
		public void addPlatform(int x, int y)
		{
			if (numPlatforms >= platforms.length)
			{
				Platform[] temp = new Platform[platforms.length * 2];
				for (int i = 0; i < platforms.length; i++)
					temp[i] = platforms[i];
				platforms = temp;
			}
			platforms[numPlatforms++] = new Platform(x,y);
		}
		
		public void setPlayer(int x, int y)
		{
			player = new Player(x,y);
		}
		
		public void setPooper(int x, int y)
		{
			pooper = new Pooper(x,y);
		}
		
		public void setDoor(int x, int y)
		{
			door = new Door(x, y);
		}
		
		public void draw()
		{
			for (int i = 0; i < numBricks; i++)
				bricks[i].draw();
			for (int i = 0; i < numPlatforms; i++)
				platforms[i].draw();
			pooper.draw();
			player.draw();	
			door.draw();
		}
		
		public Player player() { return player; }
		public Door door() { return door; }
		public Pooper pooper() { return pooper; }
		public Brick[] bricks()
		{
			Brick[] temp = new Brick[numBricks];
			for (int i = 0; i < numBricks; i++)
				temp[i] = bricks[i];
			return temp;
		}
		
		public Platform[] platforms()
		{
			Platform[] temp = new Platform[numPlatforms];
			for (int i = 0; i < numPlatforms; i++)
				temp[i] = platforms[i];
			return temp;
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	// ----------------------------- Player --------------------------------- //
	////////////////////////////////////////////////////////////////////////////
	
	private class Player
	{
		public static final int MOVE_SPEED = 5;
		public static final int JUMP_SPEED = -10;
		
		private USprite face;
//		private USprite side;
		private USprite jump;
		private USprite walk;
		
		private double x, y;          // coordinates of the player.
		private double yv;            // y-velocity
		private boolean grounded;     // Are we standing or in mid-air?
		private boolean moving;       // Are we currently moving (in this frame)?
		private boolean leftNOTright; // The only way I could get the jump to look right...
		
		public Player(int x, int y)
		{
			UImage walkimage = getImage("images/playerwalk.png", 192);
			face = makeSprite(getImage("images/player.png", 192));
//			side = makeSprite(getImage("images/playerside.png", 192));
			jump = makeSprite(getImage("images/playerjump.png", 192));
			walk = makeSprite(walkimage, 32,61);
			walk.addFrames(walkimage, 33,0 , 66,0 , 99,0);
			walk.framerate(10);

			this.x = x;
			this.y = y;

			grounded = false;
			moving = false;
		}
	
		public void draw()
		{
			y += yv;  // Take velocity into account;
			yv += G;  // Adjust for gravity

			if (collides()) {
//				if (yv != 0)
//					System.out.printf("Collision! %1.2f\n", yv);
				grounded = true;
				yv = 0;
			} else {
				grounded = false;
			}
			
			// Play the appropriate animation or show the correct frame given
			// the state of the player.
			if (!grounded) {
				if (leftNOTright) jump.flipHorizontal();
				jump.position(x, y);
				jump.draw();
			} else if (moving) {
//				if (leftNOTright) side.flipHorizontal();
//				side.position(x, y);
//				side.draw();
				if (leftNOTright) walk.flipHorizontal();
				walk.position(x, y);
				walk.draw();
			} else {
				if (leftNOTright) face.flipHorizontal();
				face.position(x, y);
				face.draw();
			}
			
			moving = false;
		}
		
		private boolean collides()
		{
			if (y >= FLOOR - face.height() && yv > 0)
			{
				y = FLOOR - face.height();
//				System.out.println("Hit floor.");
				return true;
			}
			
			for (Platform p : stages[currentStage].platforms()) if (p.x() < x + face.width() && p.x() + p.width() > x && yv > 0 && approxEquals(p.y(), y + face.height()))
			{
				y = p.y() - face.height();
//				System.out.printf("%d < %d + %d && %d + %d > %d\n", platform.x(), sprite.x(), sprite.width(), platform.x(), platform.width(), sprite.x());
//				System.out.println("Hit platform.");
				return true;
			}
			
			for (Brick b : stages[currentStage].bricks()) if (b.x() < x + face.width() && b.x() + b.width() > x && yv > 0 && approxEquals(b.y(), y + face.height()))
			{
				y = b.y() - face.height();
				return true;
			}
//			System.out.printf("%d == %d + %d && %1.2f > 0\n", platform.y(), sprite.y(), sprite.height(), yv);
//			System.out.printf("%d >= %d - %d && %1.2f > 0\n", face.y(), FLOOR, face.height(), yv);
//			System.out.printf("%d < %1.2f + %d && %d + %d > %1.2f && %1.2f > 0 && %d == %1.2f + %d\n", platforms[1].x(), x, face.width(), platforms[1].x(), platforms[1].width(), x, yv, platforms[1].y(), y, face.height());

			return false;
		}
			
		private boolean approxEquals(double a, double b)
		{
			return Math.abs(a-b) <= yv;
		}
		
		public void move(int x, int y)
		{
			this.x += x;
			this.y += y;
			moving = true;
		}
		
		public void moveLeft()
		{
			if (x - MOVE_SPEED > 0)
				move(-MOVE_SPEED, 0);
			leftNOTright = true;
		}
		
		public void moveRight()
		{
			if (x + face.width() + MOVE_SPEED < 800)
				move(MOVE_SPEED, 0);
			leftNOTright = false;
		}

		public void jump()
		{
			if (grounded) yv = JUMP_SPEED;
		}
		
		public int x()
		{
			return (int)Math.round(x);
		}
		
		public int y()
		{
			return (int)Math.round(y);
		}
	
		public int width()
		{
			return face.width();
		}
		
		public int height()
		{
			return face.height();
		}
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////
	// ---------------------------- Sprites --------------------------------- //
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * That strange little creature.  Named by Ashley.
	 * @author Andrew Correa
	 */
	private class Pooper extends Collidable
	{
		private boolean attached;   // Is pooper following the player?
		private int frameOfFirstContact;
		
		private int[] px;  // px and py keep track of the player's x and y coords
		private int[] py;  // for the past second so that the pooper can follow
		                   // the player's every move. (parallel circular arrays)
		private int nextp; // The index of the next position to assign in px & py

		public Pooper(int x, int y)
		{
			UImage image = getImage("images/pooper.png", 255);
			sprite = makeSprite(image, 28, 28);
			sprite.addFrames(image, 28, 0);
			sprite.framerate(3);
			sprite.position(x, y);
	
			attached = false;
			frameOfFirstContact = -100;
			
			// Make enough slots for one frame (equal to the number of frames per sec)
			px = new int[FPS];
			py = new int[FPS];
			for (int i = 0; i < FPS; i++)
			{
				px[i] = -1;
				py[i] = -1;
			}
			nextp = 0;
		}
		
		public void draw()
		{
			if (attached)
			{
				setNextPos();
				super.draw();
			}
			else
			{
				super.draw();
				if (playerIsNear()) {
					if (frameOfFirstContact == -100) {
						System.out.println("Ticking...");
						frameOfFirstContact = frame;
					} else if (frame - frameOfFirstContact >= FPS * 2) {
						System.out.println("Attached!");
						attached = true;
					}
				} else {
					if (frameOfFirstContact != -100)
						System.out.println("no");
					frameOfFirstContact = -100;
				}
			}
		}
		
		private void setNextPos()
		{
			px[nextp] = stages[currentStage].player().x();
			py[nextp] = stages[currentStage].player().y() + stages[currentStage].player().height() - sprite.height();
			nextp = (nextp + 1) % px.length;
			int nextX = px[nextp];
			int nextY = py[nextp];
			
			if (nextX != -1 && nextY != -1)
				sprite.position(nextX, nextY);
		}
		
		private boolean playerIsNear()
		{
			return (stages[currentStage].player().x() < sprite.x() + 10 && stages[currentStage].player().x() > sprite.x() - 10 && stages[currentStage].player().y() + stages[currentStage].player().height() > sprite.y() + sprite.height() - 5 && stages[currentStage].player().y() + stages[currentStage].player().height() < sprite.y() + sprite.height() + 5);
		}
	}
	
	/**
	 * The goal.
	 * @author Andrew Correa
	 */
	private class Door extends Collidable
	{
		public Door(int x, int y)
		{
			UImage image = getImage("images/door.png", 192);
			sprite = makeSprite(image, 37, 71);
			sprite.addFrames(image, 37,0 , 74,0 , 111,0 , 148,0);
			sprite.framerate(1);
			sprite.position(x, y);
		}
		
		public void draw()
		{
			if (playerHasArrive())
				stageComplete();
			else
				super.draw();
		}
		
		private boolean playerHasArrive()
		{
			return (stages[currentStage].player().y() == sprite.y() + 10 && stages[currentStage].player().x() < sprite.x() + 20 && stages[currentStage].player().x() > sprite.x() - 20);
		}
	}
	
	/**
	 * This is one of the bricks you see magically floating around.
	 * @author Andrew Correa
	 */
	private class Brick extends Collidable
	{
		public Brick(int x, int y)
		{
			sprite = makeSprite(getImage("images/brick.png", 255));
			sprite.position(x,y);
		}
	}
	
	/**
	 * This is one of the platforms you see magically floating around.
	 * @author Andrew Correa
	 */
	private class Platform extends Collidable
	{
		public Platform(int x, int y)
		{
			sprite = makeSprite(getImage("images/platform.png", 255));
			sprite.position(x,y);
		}
	}
	
	/**
	 * This wraps up a USprite object to make it easier (for me) to use.
	 * @author Andrew Correa
	 */
	private abstract class Collidable
	{
		protected USprite sprite;
		
		public void draw()
		{
			sprite.draw();
		}
		
		public int x()
		{
			return sprite.x();
		}
		
		public int y()
		{ 
			return sprite.y();
		}
		
		public int width()
		{
			return sprite.width();
		}		
	}
}
