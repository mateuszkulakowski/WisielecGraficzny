/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

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
import javax.swing.JPanel;
import layouts.MyCardLayout;

/**
 *
 * @author Mateusz
 */
public class KategoriaPanel extends JPanel implements KeyListener{

    private JButton button = new JButton();
    
    private Image backgroundImage = ImageIO.read(new File("kat1.jpg"));
    
    private int ktory = 0;
    private static String wybrana_kategoria = "Sport";
    
    public KategoriaPanel() throws IOException
    {
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        setLayout(null);
        
        Image image = ImageIO.read(new File("sport.jpg"));
        button.setIcon(new ImageIcon(image));
        button.setBounds(430, 202, image.getWidth(null)-20, image.getHeight(null)-10);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "PobierzImie");
            }
        });
        
        
        add(button);
        addKeyListener(this);
        setFocusable(true);
        
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);
        
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
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            
            ktory = ktory % 3;
            Image image = null;
            
            switch(ktory)
            {
                case 0:
                        wybrana_kategoria = "Historia";
                        try {
                            image = ImageIO.read(new File("historia.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        
                        try {
                                backgroundImage = ImageIO.read(new File("kat2.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory++;
                    break;
                
                
                case 1:
                        wybrana_kategoria = "Kraje";
                        try {
                            image = ImageIO.read(new File("kraje.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        
                        try {
                                backgroundImage = ImageIO.read(new File("kat3.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory++;
                    
                    break;
                    
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            ktory = ktory % 3;
            
            Image image = null;
            
            switch(ktory)
            {
                case 1:
                        wybrana_kategoria = "Sport";
                        try {
                            image = ImageIO.read(new File("sport.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        
                        try {
                                backgroundImage = ImageIO.read(new File("kat1.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory--;
                    break;
                
                
                case 2:
                        wybrana_kategoria = "Historia";
                        try {
                            image = ImageIO.read(new File("historia.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        
                        try {
                                backgroundImage = ImageIO.read(new File("kat2.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory--;
                    
                    break;
                    
            }
            
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "PobierzImie");
        }
            
            
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    public static String getWybranaKategoria()
    {
        return wybrana_kategoria;
    }
    
    
    
}
