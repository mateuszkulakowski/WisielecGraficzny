/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import layouts.MyCardLayout;
import myActionListeners.bKoniecListener;
import myActionListeners.bNowaGraListener;
import myActionListeners.bStatystykiListener;

/**
 *
 * @author Mateusz
 */
public class MenuPanel extends JPanel implements KeyListener{
    
    private JButton button = new JButton("Nowa Gra");
    
    private bNowaGraListener nowagraListener = new bNowaGraListener();
    private bStatystykiListener statystykiListener = new bStatystykiListener();
    private bKoniecListener koniecListener = new bKoniecListener();
    
    private Image backgroundImage = ImageIO.read(new File("menu-background5.jpg"));
    
    private int ktory = 0;
    
    
    public MenuPanel() throws IOException
    {
        
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        
        setLayout(null);
        
        
        button.setBounds(430, 202, 380, 120);
        Image image = ImageIO.read(new File("nowagra.jpg"));
        button.setIcon(new ImageIcon(image));
        button.addActionListener(nowagraListener);
        
        
        add(button);
        
        addKeyListener(this);
        setFocusable(true);
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            ktory = ktory % 3;
            
            switch (ktory) {
                case 0:
                    {
                        Image image = null;
                        try {
                            image = ImageIO.read(new File("statystyki.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        button.removeActionListener(nowagraListener);
                        button.removeActionListener(statystykiListener);
                        button.addActionListener(statystykiListener);
                        
                        try {
                                backgroundImage = ImageIO.read(new File("menu-background6.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory++;
                        break;
                    }
                case 1:
                    {
                        Image image = null;
                        try {
                            image = ImageIO.read(new File("koniec.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        button.removeActionListener(nowagraListener);
                        button.removeActionListener(statystykiListener);
                        button.addActionListener(koniecListener);
                        
                        try {
                                backgroundImage = ImageIO.read(new File("menu-background4.jpg"));
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
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            ktory = ktory % 3;
            
            switch (ktory) {
                case 1:
                    {
                        Image image = null;
                        try {
                            image = ImageIO.read(new File("nowagra.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        button.removeActionListener(statystykiListener);
                        button.removeActionListener(koniecListener);
                        button.addActionListener(nowagraListener);
                        
                        try {
                                backgroundImage = ImageIO.read(new File("menu-background5.jpg"));
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        setFocusable(true);
                        repaint();
                        ktory--;
                        break;
                    }
                case 2:
                    {
                        Image image = null;
                        try {
                            image = ImageIO.read(new File("statystyki.jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }       button.setIcon(new ImageIcon(image));
                        button.removeActionListener(nowagraListener);
                        button.removeActionListener(koniecListener);
                        button.addActionListener(statystykiListener);
                        
                        try {
                                backgroundImage = ImageIO.read(new File("menu-background6.jpg"));
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
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Koniec");
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(ktory == 0)
            {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Kategoria");
            }
            else if(ktory == 1)
            {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Statystyki");
            }
            else
            {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Koniec");
            }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g); 
        g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null),null);
    }
    
    
    
    
}
