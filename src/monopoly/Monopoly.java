
package monopoly;

 
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import static monopoly.Monopoly.WINDOW_BORDER;

public class Monopoly extends JFrame implements Runnable {
    static final int XBORDER = 25;
    static final int YBORDER = 25;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 900;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 950;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;

    
    final int innerboarder=160;
    final int innerboarder2=220;
    
//    int numRows=11;
//    int numColumns=11;
//
////    int board[][];
////    
//    int currentRow;
////    int currentColumn;

    
    static Monopoly frame1;
    public static void main(String[] args) {
        frame1 = new Monopoly();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Monopoly() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_RIGHT == e.getKeyCode())
                {
                  
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    
                }
                if (e.VK_UP == e.getKeyCode())
                {
                  
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                   
                }

                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(Color.gray);

        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
        
        int x2[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y2[] = {getY(0), getY(0), getY(getHeight2()-innerboarder2), getY(getHeight2()-innerboarder2), getY(0)};
        g.setColor(Color.blue);
        g.fillPolygon(x2, y2, 4);
        // draw border
        
        int x3[] = {getXInnerBorder(), getX(getWidth2()-innerboarder), getX(getWidth2()-innerboarder), getX(innerboarder), getX(innerboarder)};
        int y3[] = {getYInnerBorder(), getY(innerboarder), getY(getHeight2()-innerboarder2-innerboarder), getY(getHeight2()-innerboarder2-innerboarder), getY(innerboarder)};
        g.setColor(Color.red);
        g.drawPolyline(x3, y3, 5);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getXInnerBorder(), getYInnerBorder(), getInnerBorderWidth()+1, getInnerBorderHeight()+1);
        
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.setColor(Color.yellow);
        
        g.fillRect(getX(0),getY(0), innerboarder,innerboarder);
        g.fillRect(getX(0),getY(getHeight2()-innerboarder2-innerboarder), innerboarder,innerboarder);
        g.fillRect(getX(getWidth2()-innerboarder),getY(0), innerboarder,innerboarder);
        g.fillRect(getX(getWidth2()-innerboarder),getY(getHeight2()-innerboarder2-innerboarder), innerboarder,innerboarder);
//horizontal lines
//        for (int zi=1;zi<numRows;zi++)
//        {
//            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows);
//        }
//vertical lines
//        for (int zi=1;zi<numColumns;zi++)
//        {
//            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,getX(0)+zi*getWidth2()/numColumns,getY(getHeight2()));
//        }

//Display the objects of the board
        

                  
                
        gOld.drawImage(image, 0, 0, null);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
//Allocate memory for the 2D array that represents the board.
//        board = new int[numRows][numColumns];
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }

            reset();
           
        }

        
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    } 
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
    public int getXInnerBorder(){
        return (getX(innerboarder));
    }
    public int getYInnerBorder(){
        return (getY(innerboarder));
    }
    public int getInnerBorderWidth(){
         return(getX(getWidth2()-innerboarder)-getXInnerBorder()+1);
    }
    public int getInnerBorderHeight(){
        return (getY(getHeight2()-innerboarder2-innerboarder)- getYInnerBorder()+1);
    }
}





