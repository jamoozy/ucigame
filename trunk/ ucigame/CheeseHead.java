import ucigame.*;

public class CheeseHead extends Ucigame
{
	public static final double G = .5;    // gravity
	public static final int FLOOR = 500;  // y-coord of the floor.
	public static final int FPS = 20;

//	private Brick[] bricks;
//	private Platform[] platforms;
//	private Player player;
//	private Door door;
//	private Pooper pooper;

	Stage[] stages;            // All stages.

	private int frame;         // The frame we're on.
	private int currentStage;  // "pointer" in the array of stages to the current stage.

	/**
	 * Set up the window and stages.
	 */
	public void setup()
	{
		framerate(FPS);
		window.size(800,600);
		window.title("Cheese Head");
		canvas.background(0,125,255);
		
		frame = 0;
		currentStage = 2;
		
		stages = new Stage[3];
		stages[0] = new Stage();
		stages[0].setDoor(250, 229);
		stages[0].setPlayer(20, 438);
		stages[0].setPooper(400,322);		stages[0].addBrick(300,400);
		stages[0].addBrick(550, 300);
		stages[0].addPlatform(500,450);
		stages[0].addPlatform(400,400);
		stages[0].addPlatform(300,350);
		stages[0].addPlatform(200,300);

		stages[1] = new Stage();
		stages[1].setDoor(77, 9);
		stages[1].setPooper(700, 472);
		stages[1].setPlayer(20,438);
		stages[1].addPlatform(20, 80);
		stages[1].addPlatform(600, 400);
		stages[1].addPlatform(600, 300);
		stages[1].addPlatform(600, 200);
		stages[1].addPlatform(600, 100);
		stages[1].addPlatform(20, 180);
		stages[1].addBrick(270, 284);
		
		stages[2] = new Stage();
		stages[2].setDoor(700,429);
		stages[2].setPooper(372, 472);
		stages[2].setPlayer(20, 439);
		stages[2].addBrick(80, 468);
		stages[2].addBrick(80, 436);
		stages[2].addBrick(80, 404);
		stages[2].addBrick(80, 372);
		stages[2].addBrick(80, 340);
		stages[2].addBrick(80, 308);
		stages[2].addBrick(0, 404);
		stages[2].addPlatform(112, 308);
		stages[2].addPlatform(263, 308);
		stages[2].addPlatform(414, 308);
		stages[2].addPlatform(565, 308);
		stages[2].addBrick(716, 308);
		stages[2].addBrick(716, 340);
		stages[2].addBrick(716, 372);
		stages[2].addBrick(684, 372);
		stages[2].addBrick(652, 404);
		stages[2].addBrick(652, 436);
		stages[2].addBrick(652, 468);
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

	/**
	 * Puts out the message at the end.
	 */
	public void startScore()
	{
		canvas.clear();
		canvas.color(0);
//		canvas.font("Arial", BOLD, 30);
		canvas.putText("ehm ... you win?", 300, 300);
	}
	

	/**
	 * Updates the screen every frame during the score screen.
	 */
	public void drawScore()
	{
	}
	

	/**
	 * Init-type stuff for the main game.
	 */
	public void startMainGame()
	{
	}
	

	/**
	 * Draws everything during normal gameplay.
	 */
	public void drawMainGame()
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
	

	/**
	 * Draws a rectangle from (x1,y1) to (x2,y2).
	 * 
	 * @param x1 X-coord of one corner.
	 * @param y1 Y-coord of one corner.
	 * @param x2 X-coord of the other corner.
	 * @param y2 Y-coord of the other corner.
	 */
	private void drawrect(int x1, int y1, int x2, int y2)
	{
		for (int i = y1; i <= y2; i++)
			canvas.line(x1, i, x2, i);
	}
	

	/**
	 * Draws a cross at (x,y) with a size of 2lx2l.
	 * 
	 * @param x The x-coordinate of the center.
	 * @param y The y-coordinate of the center.
	 * @param l Twice the height & width of the cross. 
	 */
	private void drawcross(int x, int y, int l)
	{
		canvas.line(x-l, y, x+l, y);
		canvas.line(x, y-l, x, y+l);
	}

	/**
	 * Control the player sprite.  F or LEFT moves left.  S or RIGHT moves right
	 * D or DOWN pushes through a platform.  SPACE jumps.
	 */
	public void onKeyPressMainGame()
	{
		if (keyboard.isDown(keyboard.F, keyboard.RIGHT)) {
//			System.out.println("right");
			stages[currentStage].player().moveRight();
		} else if (keyboard.isDown(keyboard.S, keyboard.LEFT)) {
//			System.out.println("left");
			stages[currentStage].player().moveLeft();
		} if (keyboard.isDown(keyboard.SPACE)) {
//			System.out.println("jump");
			stages[currentStage].player().jump();
		} if (keyboard.isDown(keyboard.D, keyboard.DOWN)) {
//			System.out.println("down");
			stages[currentStage].player().down();
		}
	}

	
	/**
	 * Does nothing yet.  Only keeps exceptions from being thrown when a
	 * key is pressed in this screen.
	 */
	public void onKeyPressScore()
	{
	}



	////////////////////////////////////////////////////////////////////////////
	// ----------------------------- Stages --------------------------------- //
	////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The abstract way to store the makeup of a stage.
	 */
	
	/**
	 * The abstract representation of a stage.  This includes the start positions
	 * of the player, goal, and pooper, as well as the positions of all the
	 * bricks and platforms that make up the stage.
	 */
	private class Stage
	{
		private Brick[] bricks;        // Bricks in the stage.
		private Platform[] platforms;  // Platforms.
		private Player player;         // Player.
		private Door door;             // Door/goal.
		private Pooper pooper;         // cute animal-thing.
		
		private int numBricks;     // Count of bricks -- different than capacity.
		private int numPlatforms;  // Count of platforms -- different than capacity.
		
		/**
		 * Creates new Stage object.  Call other methods to alter this guy.
		 */
		public Stage()
		{
			bricks = new Brick[10];
			platforms = new Platform[10];
			
			numBricks = 0;
			numPlatforms = 0;
		}
		
		/**
		 * Adds a new brick at (x,y).
		 * 
		 * @param x x-coord of the new brick.
		 * @param y y-coord of the new brick.
		 */
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
		
		/**
		 * Adds a new platform at (x,y).
		 * 
		 * @param x x-coord of the new Platform.
		 * @param y y-coord of the new Platform.
		 */
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

		/**
		 * Set's the player's start position to (x,y).  Calling this method
		 * makes the stage overwrite the old position.
		 * 
		 * @param x X-coord of the player's start pos.
		 * @param y Y-coord of the player's start pos.
		 */
		public void setPlayer(int x, int y)
		{
			player = new Player(x,y);
		}
		
		/**
		 * Set's pooper's start position to (x,y).  Calling this method
		 * makes the stage overwrite pooper's old position.
		 * 
		 * @param x X-coord of the player's start pos.
		 * @param y Y-coord of the player's start pos.
		 */
		public void setPooper(int x, int y)
		{
			pooper = new Pooper(x,y);
		}

		/**
		 * Set's the goal's start position to (x,y).  Calling this method
		 * makes the stage overwrite the goal's old position.
		 * 
		 * @param x X-coord of the goal's start position.
		 * @param y Y-coord of the goal's start position.
		 */
		public void setDoor(int x, int y)
		{
			door = new Door(x, y);
		}
		
		/**
		 * Draws this stage and all it's components.
		 */
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
		public int numBricks() { return numBricks; }
		public int numPlatforms() { return numPlatforms; }
		
		/**
		 * Creates and returns a correctly-sized array of bricks.
		 * 
		 * @return The bricks in this stage.
		 */
		public Brick[] bricks()
		{
			Brick[] temp = new Brick[numBricks];
			for (int i = 0; i < numBricks; i++)
				temp[i] = bricks[i];
			return temp;
		}
		
		/**
		 * Creates and returns a correctly-sized array of platforms.
		 * 
		 * @return The platforms in this stage.
		 */
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
	
	/**
	 * The player/sprite that the user controls.
	 */
	private class Player
	{
		public static final int MOVE_SPEED = 5;
		public static final int JUMP_SPEED = -10;
		
		private Sprite face;
//		private USprite side;
		private Sprite jump;
		private Sprite walk;
		
		private double x, y;          // coordinates of the player.
		private double yv;            // y-velocity
		private boolean grounded;     // Are we standing or in mid-air?
		private boolean moving;       // Are we currently moving (in this frame)?
		private boolean leftNOTright; // The only way I could get the jump to look right...
		
		public Player(int x, int y)
		{
			Image walkimage = getImage("images/playerwalk.png", 192);
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
			checkCollisions();

//			if (collides()) {
//				if (yv != 0)
//					System.out.printf("Collision! %1.2f\n", yv);
//				grounded = true;
//				yv = 0;
//			} else {
//				grounded = false;
//			}
			
			y += yv;  // Take velocity into account;
			yv += G;  // Adjust for gravity
			
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
		
		private void checkCollisions()
		{
			if (y >= FLOOR - face.height() && yv > 0)
			{
//				System.out.println("floor-foot collision");
				y = FLOOR - face.height();
//				System.out.println("Hit floor.");
				grounded = true;
				yv = 0;
				return;
			}
			
			for (Platform p : stages[currentStage].platforms()) if (p.x() < x + face.width() && p.x() + p.width() > x && yv > 0 && p.y() >= y + face.height() && p.y() < y() + height() + yv)
			{
//				System.out.println("platform-foot collision");
				y = p.y() - face.height();
//				System.out.printf("%d < %d + %d && %d + %d > %d\n", platform.x(), sprite.x(), sprite.width(), platform.x(), platform.width(), sprite.x());
//				System.out.println("Hit platform.");
				grounded = true;
				yv = 0;
				return;
			}
			
			for (Brick b : stages[currentStage].bricks())
			{
				if (b.x() < x + face.width() && b.x() + b.width() > x)
				{
					if (yv > 0 && b.y() >= y + face.height() && b.y() < y() + height() + yv)
					{
//						System.out.println("block-foot collision");
						y = b.y() - face.height();
						yv = 0;
						grounded = true;
						return;
					}
					else if (yv < 0 && b.y() + b.height() <= y() && b.y() + b.height() > y() + yv)
					{
//						System.out.println("block-head collision");
						y = b.y() + b.height();
						yv = 0;
						grounded = false;
						return;
					}
//					else
//						System.out.printf("yv:%2.0f\nblock y+height:%d\ny:%2.0f\n", yv, b.y() + b.height(), y);
				}
			}
//			System.out.printf("%d == %d + %d && %1.2f > 0\n", platform.y(), sprite.y(), sprite.height(), yv);
//			System.out.printf("%d >= %d - %d && %1.2f > 0\n", face.y(), FLOOR, face.height(), yv);
//			System.out.printf("%d < %1.2f + %d && %d + %d > %1.2f && %1.2f > 0 && %d == %1.2f + %d\n", platforms[1].x(), x, face.width(), platforms[1].x(), platforms[1].width(), x, yv, platforms[1].y(), y, face.height());
			
			grounded = false;
		}
		
		private void move(int dx, int dy)
		{
			moving = true;
			for (Brick b : stages[currentStage].bricks())
			{
				if (b.y() < y() + height() && b.y() + b.height() > y() && b.x() < x() + width() + dx && b.x() + b.width() > x() + dx)
				{
					moving = false;
					return;
				}
			}
			this.x += dx;
			this.y += dy;
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
		
		public void down()
		{
			// TODO Fill me up.
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
			Image image = getImage("images/pooper.png", 255);
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
			Image image = getImage("images/door.png", 192);
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
		protected Sprite sprite;
		
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
		
		public int height()
		{
			return sprite.height();
		}
	}
}
