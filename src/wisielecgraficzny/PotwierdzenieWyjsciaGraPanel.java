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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import layouts.MyCardLayout;

/**
 *
 * @author Mateusz
 */
public class PotwierdzenieWyjsciaGraPanel extends JPanel implements KeyListener{

    private Image backgroundImage = ImageIO.read(new File("wyjsciePodczasGry.jpg"));
    
    private JButton bTak = new JButton();
    private JButton bNie = new JButton();
    
    
    public PotwierdzenieWyjsciaGraPanel() throws IOException
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
        bTak.setIcon(new ImageIcon(image));
        bTak.setBounds(400, 330, image.getWidth(null)-20, image.getHeight(null)-10);
        bTak.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GraPanel.setKategoria("brak");
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
            }
        });
        
        
        
        image= ImageIO.read(new File("nie.jpg"));
        bNie.setIcon(new ImageIcon(image));
        bNie.setBounds(680, 330,  image.getWidth(null)-20, image.getHeight(null)-10);
        bNie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Gra");
            }
        });
        
        
        
        add(bTak);
        add(bNie);
        addKeyListener(this);
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
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            GraPanel.setKategoria("brak");
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Gra");
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
