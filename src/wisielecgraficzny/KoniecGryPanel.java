/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import hibernate.ConnectionFactory;
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
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import layouts.MyCardLayout;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mateusz
 */
public class KoniecGryPanel extends JPanel implements KeyListener{

    private JLabel lKomunikat = new JLabel();
    private JLabel lKomunikatValue = new JLabel();
    private JButton bDalej = new JButton();
    
    private JLabel lMiejsceWRankingu = new JLabel();
    private JLabel lMiejsceWRankinguValue = new JLabel();
    
    private Image backgroundImage = ImageIO.read(new File("czysty.jpg"));
    private Session session = ConnectionFactory.getSessionFactory().openSession();
    private List<Wyniki> lista;
    
    public KoniecGryPanel() throws IOException
    {
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        setLayout(null);
        
        
        Image image = ImageIO.read(new File("dalej.jpg"));
        bDalej.setIcon(new ImageIcon(image));
        bDalej.setBounds(920, 500, image.getWidth(null)-20, image.getHeight(null)-10);
        bDalej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
            }
        });
        
        
        
        lKomunikat.setText("Twój wynik to: ");
        lKomunikat.setBounds(390, 150, 300, 80);
        lKomunikat.setForeground(Color.white);
        lKomunikat.setFont(new Font("Courier",Font.BOLD,22));
        
        lKomunikatValue.setBounds(550, 150, 300, 80);
        lKomunikatValue.setForeground(Color.red);
        lKomunikatValue.setFont(new Font("Courier",Font.BOLD,22));
        
        lMiejsceWRankingu.setText("Twoje miejsce w rankingu: ");
        lMiejsceWRankingu.setBounds(390, 250, 300, 80);
        lMiejsceWRankingu.setForeground(Color.white);
        lMiejsceWRankingu.setFont(new Font("Courier",Font.BOLD,22));
        
        lMiejsceWRankinguValue.setBounds(680, 250, 300, 80);
        lMiejsceWRankinguValue.setForeground(Color.red);
        lMiejsceWRankinguValue.setFont(new Font("Courier",Font.BOLD,22));
        
        
        add(bDalej);
        add(lMiejsceWRankingu);
        add(lMiejsceWRankinguValue);
        add(lKomunikatValue);
        add(lKomunikat);
        
        
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Query query = session.createQuery("FROM Wyniki w");
        lista =  query.list();
        int ktoryWRankingu = 1;
        
        for(Wyniki w: lista)
        {
            if(w.getPunkty() > GraPanel.getWynik())ktoryWRankingu++;
        }
        
        
        lMiejsceWRankinguValue.setText(ktoryWRankingu+"");
        lKomunikatValue.setText(GraPanel.getWynik()+"");
        
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
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Menu");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    
}
