/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import hibernate.dao.Wyniki;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import layouts.MyCardLayout;
import org.hibernate.Transaction;

/**
 *
 * @author Mateusz
 */
public class PobierzImiePanel extends JPanel implements KeyListener{

    private JLabel tImie = new JLabel();
    private static JTextField tImieValue = new JTextField();
    private JButton bDalej = new JButton();
    private JLabel lWybranaKateogria = new JLabel();
    private JLabel lWybranaKategoriaValue = new JLabel();
    
    private static String imie;
    private Image backgroundImage = ImageIO.read(new File("czysty.jpg"));
    
    public PobierzImiePanel() throws IOException
    {
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        setLayout(null);
        
        lWybranaKateogria.setBounds(290, 180, 300, 50);
        lWybranaKateogria.setForeground(Color.WHITE);
        lWybranaKateogria.setFont(new Font("Courier",Font.BOLD,22));
        lWybranaKateogria.setText("Wybrana Kategoria: ");
        
        lWybranaKategoriaValue.setBounds(500, 180, 300, 50);
        lWybranaKategoriaValue.setFont(new Font("Courier",Font.BOLD,22));
        lWybranaKategoriaValue.setForeground(Color.RED);
        
        
        tImie.setBounds(290, 245, 300, 50);
        tImie.setForeground(Color.WHITE);
        tImie.setFont(new Font("Courier",Font.BOLD,22));
        tImie.setText("Podaj Imię: ");
        
        tImieValue.setBounds(440, 250, 300, 50);
        tImieValue.setFont(new Font("Courier",Font.BOLD,22));
        tImieValue.setBackground(Color.GRAY);
        
        Image image = ImageIO.read(new File("dalej.jpg"));
        bDalej.setIcon(new ImageIcon(image));
        bDalej.setBounds(500, 350, image.getWidth(null)-20, image.getHeight(null)-10);
        bDalej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(tImieValue.getText() != null && !tImieValue.getText().isEmpty())
                {
                    imie = tImieValue.getText();
                    MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Gra");
                }
                
            }
        });
        
        
        add(lWybranaKategoriaValue);
        add(lWybranaKateogria);
        add(tImie);
        add(bDalej);
        add(tImieValue);
        addKeyListener(this);
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        tImieValue.setText("");
        lWybranaKategoriaValue.setText(KategoriaPanel.getWybranaKategoria());
        
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
            if(tImieValue.getText() != null && !tImieValue.getText().isEmpty())
                {
                    imie = tImieValue.getText();
                    MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Gra");
                }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Kategoria");
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }
    
    
    
    public static String getImie()
    {
        return imie;
    }
    
}
