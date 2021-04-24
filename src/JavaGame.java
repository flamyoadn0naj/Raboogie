import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaGame extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private Image dbImage;
    //private Image background;
    private Graphics dbg;

    Tank tank = new Tank(100,100,50);
    //Circle c = new Circle(100, 100, 30, 90);

    //Soldier    soldier    = new Soldier(150, 150, Soldier.DOWN);

    Robot robot = new Robot(475,290,Robot.LEFT);
    //Rect r = new Rect(150,155, 70, 115);


    //------------------------------------------------------------------------//


    // S is the scaling factor for the map.
    public static final int S = 32;


    String[] mapdata =
            {
                    "............................................................",
                    "............................................................",
                    "............................................................",
                    "............................................................",
                    "..................AC........................................",
                    "..................DEBBBBBC..................................",
                    "..................DEEEEEEF..................................",
                    "..................DEEEEEEF..................................",
                    "..................DEEEEEEF..................................",
                    "..................DEEEEEEF..................................",
                    "............................................................",
                    "........BBBBBBBBBBBBBBBBBBBBB......BBBBBBBBBBBBBBBBB.......B",
                    "........B..................................................B",
                    "........B..................................................B",
                    "........B.........BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB..B",
                    "........B...............................................B..B",
                    "........B...............................................B..B",
                    "........BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB.....B..B",
                    "..................................................B.....B..B",
                    "..................................................B.....B..B",
                    "...BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB.....B..B",
                    "...B....................................................B..B",
                    "...B....................................................B..B",
                    "...B..........BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB.B",
                    "...B.......................................................B",
                    "...B.......................................................B",
                    "QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ",
            };

    Image background = getImage("Resources Root/TileMap/BG.png");
    Image[] tile;

    TileMap map;

    public JavaGame() {

        Camera.x = 856;
        Camera.y = 512;
        int numOfTiles = 18;
        tile = new Image[numOfTiles];

        for (int i = 0; i < numOfTiles; i++) {
            tile[i] = getImage("Resources Root/TileMap/" + (i+1) + ".png");
        }

        map = new TileMap(mapdata,tile,background,32);
        // Game properties
        setTitle("JavaGame");
        setSize(1500,800);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        requestFocus();

        addKeyListener(this);

        addMouseListener(this);

        addMouseMotionListener(this);

    }

    public void inGameLoop() {
        // Boundaries of my robot to detect collision
        int top    = robot.y;
        int bottom = robot.y + S-1;
        int left   = robot.x;
        int right  = robot.x + S-1;

        if(!map.clearBelow(robot))           // You are standing on the ground
        {
            robot.stops();
            Camera.stops();

            if(pressing[UP])
            {
                if(map.clearAbove(robot))
                {
                    robot.jumpUp(S/2);

                    Camera.jump(S/2);
                }
            }
        }
        else                             // You are airborne
        {
            robot.applyGravity();
            Camera.applyGravity();
        }



        if(pressing[LT])
        {
            if(map.clearLeftOf(robot))
            {
                robot.moveLeft(S/4);

                Camera.moveLeft(S/4);
            }
        }

        if(pressing[RT]) {
            if(map.clearRightOf(robot)) {
                robot.moveRight(S/4);

                Camera.moveRight(S/4);
            }
        }

        if(!map.clearLeftOf(robot)) {
                robot.vx = 0;
                Camera.vx = 0;
        }

        if(!map.clearRightOf(robot)) {
                robot.vx = 0;
                Camera.vx = 0;
        }

        if(!map.clearAbove(robot)) {
                robot.vy=0;
                Camera.vy = 0;
        }

        robot.update();
        Camera.update();
    }

    public void paintComponent(Graphics g){
        map.draw(g);
        robot.draw(g);
        //r.draw(g);
        //soldier.draw(g);
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,null);
    }

    @Override
    public void run() {
        try {
            while(true) {
                inGameLoop();	// To be overridden with Game Loop functionality

                repaint();     // Ask the OS to call paint (via update)

                Thread.sleep(15);     // Pause iteration until a 60th of a second has passed
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JavaGame jg = new JavaGame();
        Thread t = new Thread(jg);
        t.start();
    }

    //------------------------------------------------------------------------//
    // This boolean array will hold a record of the keys the user
    // is and is not pressing each iteration of the Game Loop
    //------------------------------------------------------------------------//
    boolean[] pressing = new boolean[1024];

    //------------------------------------------------------------------------//
    // These final variables are shorthand for the KeyEvent virtual keys
    //------------------------------------------------------------------------//
    public static final int UP          = KeyEvent.VK_UP;
    public static final int DN          = KeyEvent.VK_DOWN;
    public static final int LT          = KeyEvent.VK_LEFT;
    public static final int RT          = KeyEvent.VK_RIGHT;

    public static final int _A          = KeyEvent.VK_A;
    public static final int _B          = KeyEvent.VK_B;
    public static final int _C          = KeyEvent.VK_C;
    public static final int _D          = KeyEvent.VK_D;
    public static final int _E          = KeyEvent.VK_E;
    public static final int _F          = KeyEvent.VK_F;
    public static final int _G          = KeyEvent.VK_G;
    public static final int _H          = KeyEvent.VK_H;
    public static final int _I          = KeyEvent.VK_I;
    public static final int _J          = KeyEvent.VK_J;
    public static final int _K          = KeyEvent.VK_K;
    public static final int _L          = KeyEvent.VK_L;
    public static final int _M          = KeyEvent.VK_M;
    public static final int _N          = KeyEvent.VK_N;
    public static final int _O          = KeyEvent.VK_O;
    public static final int _P          = KeyEvent.VK_P;
    public static final int _Q          = KeyEvent.VK_Q;
    public static final int _R          = KeyEvent.VK_R;
    public static final int _S          = KeyEvent.VK_S;
    public static final int _T          = KeyEvent.VK_T;
    public static final int _U          = KeyEvent.VK_U;
    public static final int _V          = KeyEvent.VK_V;
    public static final int _W          = KeyEvent.VK_W;
    public static final int _X          = KeyEvent.VK_X;
    public static final int _Y          = KeyEvent.VK_Y;
    public static final int _Z          = KeyEvent.VK_Z;

    public static final int _1          = KeyEvent.VK_1;
    public static final int _2          = KeyEvent.VK_2;
    public static final int _3          = KeyEvent.VK_3;
    public static final int _4          = KeyEvent.VK_4;
    public static final int _5          = KeyEvent.VK_5;
    public static final int _6          = KeyEvent.VK_6;
    public static final int _7          = KeyEvent.VK_7;
    public static final int _8          = KeyEvent.VK_8;
    public static final int _9          = KeyEvent.VK_9;

    public static final int CTRL        = KeyEvent.VK_CONTROL;
    public static final int SHFT        = KeyEvent.VK_SHIFT;
    public static final int ALT         = KeyEvent.VK_ALT;

    public static final int SPACE       = KeyEvent.VK_SPACE;

    public static final int COMMA       = KeyEvent.VK_COMMA;
    public static final int PERIOD      = KeyEvent.VK_PERIOD;
    public static final int SLASH       = KeyEvent.VK_SLASH;
    public static final int SEMICOLON   = KeyEvent.VK_SEMICOLON;
    public static final int COLON       = KeyEvent.VK_COLON;
    public static final int QUOTE       = KeyEvent.VK_QUOTE;

    public static final int F1          = KeyEvent.VK_F1;
    public static final int F2          = KeyEvent.VK_F2;
    public static final int F3          = KeyEvent.VK_F3;
    public static final int F4          = KeyEvent.VK_F4;
    public static final int F5          = KeyEvent.VK_F5;
    public static final int F6          = KeyEvent.VK_F6;
    public static final int F7          = KeyEvent.VK_F7;
    public static final int F8          = KeyEvent.VK_F8;
    public static final int F9          = KeyEvent.VK_F9;
    public static final int F10         = KeyEvent.VK_F10;
    public static final int F11         = KeyEvent.VK_F11;
    public static final int F12         = KeyEvent.VK_F12;

    //------------------------------------------------------------------------//
    // To preserve mouse location between MouseEvents
    //------------------------------------------------------------------------//
    int mx = 0;
    int my = 0;

    @Override
    public void keyPressed(KeyEvent e) {
        pressing[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        pressing[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //---------------------------------//
    // All mouse related Event methods
    //---------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int nx = e.getX();
        int ny = e.getY();

        int dx = nx - mx;
        int dy = ny - my;

        mx = nx;
        my = ny;
    }

    public Image getImage(String filename)
    {
        return Toolkit.getDefaultToolkit().getImage(filename);
    }
}