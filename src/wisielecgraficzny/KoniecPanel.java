/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import Content.MyMainFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import layouts.MyCardLayout;

/**
 *
 * @author Mateusz
 */
public class KoniecPanel extends JPanel implements KeyListener{
    
    private JButton tak = new JButton();
    private JButton nie = new JButton();
    
    
    public KoniecPanel() throws IOException
    {
        
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        
        setLayout(null);
        
        Image image= ImageIO.read(new File("tak.jpg"));
        tak.setIcon(new ImageIcon(image));
        tak.setBounds(400, 300, image.getWidth(null)-20, image.getHeight(null)-10);
        tak.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMainFrame.getMainFrame().dispose();
            }
        });
        
        
        
        image= ImageIO.read(new File("nie.jpg"));
        nie.setIcon(new ImageIcon(image));
        nie.setBounds(680, 300,  image.getWidth(null)-20, image.getHeight(null)-10);
        nie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
            }
        });
        
        
        add(nie);
        add(tak);
        
        addKeyListener(this);
        setFocusable(true);
        
        
    }
    
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image image = null;
        try {
            image = ImageIO.read(new File("menu-background7.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(KoniecPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
}

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            MyMainFrame.getMainFrame().dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
