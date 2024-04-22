import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Viewport extends JPanel implements ActionListener 
{
    private Timer timer;
    private Player player;
    private final int DELAY=10; 
    private int x;
    private int y;

    public Viewport(int w, int h)
    {
        initBoard();
        x=w/2;
        y=h-300;
        player.setX(x);
        player.setY(y);
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        player=new Player();

        timer=new Timer(DELAY,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void draw(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        
        g2d.drawImage(player.getImage(),player.getX(),player.getY(),this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        step();
    }

    private void step()
    {
        player.move();
        repaint(player.getX()-1,player.getY()-1,player.getWidth()+2,player.getHeight()+2);
    }

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) 
        {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            player.keyPressed(e);
        }
    }
}