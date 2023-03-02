package CatSnake;

/* PROGRAM INFORMATION
 * Author: Caitlyn Smith
 * Course: CISS 241-300
 * Exercise: Cat Snake

 * PROGRAM DESCRIPTION
 *  */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;

class CatSnake extends JFrame implements Runnable {
    boolean stopflag;
    int x, y;
    Thread thread;

    public CatSnake() {
        stopflag = false;
        x = 0;
        y = 75;

        setTitle("Cat Snake - Graphics 2D Animation"); //set the frame title
        setSize(675,500); //set the frame size
        setLocationRelativeTo(null); //center the frame
        setVisible(true); //make visible

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (!stopflag) {
            for (int i = 0; i < getWidth(); i++) {
                x += 15;
            }

            repaint();

            if (x > getWidth() - y * 2) {
                end();
                break;
            }
        }
        end();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics = (Graphics2D) g; //initialize Graphics2D object

        //drawing sky
        Rectangle2D sky = new Rectangle2D.Float(0, 0, 675, 250);
        GradientPaint skyGradient = new GradientPaint(0, 25, Color.CYAN, 600, 25, Color.WHITE);
        graphics.setPaint(skyGradient);
        graphics.fill(sky);
        graphics.draw(sky);

        //drawing sun
        Ellipse2D sun = new Ellipse2D.Float(75, 40, 65, 65);
        graphics.setPaint(Color.YELLOW);
        graphics.fill(sun);
        graphics.draw(sun);

        //drawing tree trunk
        Line2D treeTrunk = new Line2D.Float(350, 245, 350, 105);
        graphics.setStroke(new BasicStroke(10));
        graphics.setPaint(Color.DARK_GRAY);
        graphics.fill(treeTrunk);
        graphics.draw(treeTrunk);

        //drawing leaves
        Arc2D leaves = new Arc2D.Double(300, 100, 100, 100, 0, 180, Arc2D.PIE);
        graphics.setPaint(Color.GREEN);
        graphics.fill(leaves);
        graphics.draw(leaves);

        //drawing grass
        Rectangle2D grass = new Rectangle2D.Float(0, 255, 675, 250);
        graphics.setPaint(Color.GREEN);
        graphics.fill(grass);
        graphics.draw(grass);

        try {
            Thread.sleep(125);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        repaint();

        //drawing cat
        GeneralPath cat = new GeneralPath();
        graphics.setPaint(Color.RED);
        cat.moveTo(75, 275);
        cat.lineTo(75, 350);
        cat.lineTo(145, 350);
        cat.lineTo(145, 275);
        cat.lineTo(120, 300);
        cat.lineTo(100, 300);
        cat.closePath();
        graphics.setStroke(new BasicStroke(5, CAP_ROUND, JOIN_ROUND));
        graphics.setPaint(Color.RED);
        graphics.draw(cat);

        //drawing cat eyes
        GeneralPath catEyes = new GeneralPath();
        graphics.setPaint(Color.BLUE);
        catEyes.moveTo(90,310);
        catEyes.lineTo(90,315);
        catEyes.lineTo(95,315);
        catEyes.lineTo(95,310);
        catEyes.closePath();

        catEyes.moveTo(125,310);
        catEyes.lineTo(125,315);
        catEyes.lineTo(130,315);
        catEyes.lineTo(130,310);
        catEyes.closePath();

        graphics.setStroke(new BasicStroke(10, CAP_ROUND, JOIN_ROUND));
        graphics.setPaint(Color.BLUE);
        graphics.fill(catEyes);
        graphics.draw(catEyes);

        try {
            Thread.sleep(125);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        x = x + 15;
        repaint();
    }

    public void end() {
        stopflag = true;
    }
}