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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import layouts.MyCardLayout;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mateusz
 */
public class StatystykiPanel extends JPanel implements KeyListener{
    
    private JButton bPowrót = new JButton();
    JLabel jedynka = new JLabel();
    JLabel jedynkaImieValue = new JLabel();
    JLabel jedynkaPunkty = new JLabel();
    JLabel dwójka = new JLabel();
    JLabel dwójkaImieValue = new JLabel();
    JLabel dwójkaPunkty = new JLabel();
    JLabel trójka = new JLabel();
    JLabel trójkaImieValue = new JLabel();
    JLabel trójkaPunkty = new JLabel();
    
    
    
    
    
    private Session session = ConnectionFactory.getSessionFactory().openSession();
    private List<Wyniki> lista;
    
    private Image backgroundImage = ImageIO.read(new File("top3.jpg"));
    
    public StatystykiPanel()throws IOException
    {
        
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        setLayout(null);
        
        
        jedynka.setText("1.");
        jedynka.setForeground(Color.red);
        jedynka.setFont(new Font("Courier",Font.BOLD, 32));
        jedynka.setBounds(380, 200, 1200, 50);
        
        add(jedynka);
        
        
        //2
        
        dwójka.setText("2.");
        dwójka.setForeground(Color.orange);
        dwójka.setFont(new Font("Courier",Font.BOLD, 23));
        dwójka.setBounds(380, 240, 1200, 50);
        
        add(dwójka);
        
        
        
        
        
        //3
        
        trójka.setText("3.");
        trójka.setForeground(Color.yellow);
        trójka.setFont(new Font("Courier",Font.BOLD, 16));
        trójka.setBounds(380, 280, 1200, 50);
        
        add(trójka);
        
        
        
        
        addKeyListener(this);
        setFocusable(true);
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        remove(jedynkaImieValue);
        remove(dwójkaImieValue);
        remove(trójkaImieValue);
        remove(jedynkaPunkty);
        remove(dwójkaPunkty);
        remove(trójkaPunkty);
        
        Query query = session.createQuery("FROM Wyniki w ORDER BY w.punkty DESC");
        lista =  query.list();
        
        
        try
        {
            
            if(lista.get(0)!=null)
            {
                jedynkaImieValue.setText(lista.get(0).getImie());
                jedynkaImieValue.setForeground(Color.red);
                jedynkaImieValue.setFont(new Font("Courier",Font.BOLD, 32));
                jedynkaImieValue.setBounds(430, 200, 1200, 50);

                add(jedynkaImieValue);



                jedynkaPunkty.setText(lista.get(0).getPunkty()+"");
                jedynkaPunkty.setForeground(Color.red);
                jedynkaPunkty.setFont(new Font("Courier",Font.BOLD, 32));
                jedynkaPunkty.setBounds(700, 200, 1200, 50);

                add(jedynkaPunkty);
            }
        }catch(IndexOutOfBoundsException ex)
        {
            jedynkaImieValue.setText("brak");
            jedynkaImieValue.setForeground(Color.red);
            jedynkaImieValue.setFont(new Font("Courier",Font.BOLD, 32));
            jedynkaImieValue.setBounds(430, 200, 1200, 50);

            add(jedynkaImieValue);



            jedynkaPunkty.setText("brak");
            jedynkaPunkty.setForeground(Color.red);
            jedynkaPunkty.setFont(new Font("Courier",Font.BOLD, 32));
            jedynkaPunkty.setBounds(700, 200, 1200, 50);

            add(jedynkaPunkty);
        }
            //2

        try{
            if(lista.get(1)!=null)
            {
                dwójkaImieValue.setText(lista.get(1).getImie());
                dwójkaImieValue.setForeground(Color.orange);
                dwójkaImieValue.setFont(new Font("Courier",Font.BOLD, 23));
                dwójkaImieValue.setBounds(430, 240, 1200, 50);

                add(dwójkaImieValue);


                dwójkaPunkty.setText(lista.get(1).getPunkty()+"");
                dwójkaPunkty.setForeground(Color.orange);
                dwójkaPunkty.setFont(new Font("Courier",Font.BOLD, 23));
                dwójkaPunkty.setBounds(700, 240, 1200, 50);

                add(dwójkaPunkty);
            }
        }catch(IndexOutOfBoundsException ex)
        {
            dwójkaImieValue.setText("brak");
            dwójkaImieValue.setForeground(Color.orange);
            dwójkaImieValue.setFont(new Font("Courier",Font.BOLD, 23));
            dwójkaImieValue.setBounds(430, 240, 1200, 50);

            add(dwójkaImieValue);


            dwójkaPunkty.setText("brak");
            dwójkaPunkty.setForeground(Color.orange);
            dwójkaPunkty.setFont(new Font("Courier",Font.BOLD, 23));
            dwójkaPunkty.setBounds(700, 240, 1200, 50);
        
            add(dwójkaPunkty);
        }

            //3

            try
            {
                if(lista.get(2)!= null)
                {
                    trójkaImieValue.setText(lista.get(2).getImie());
                    trójkaImieValue.setForeground(Color.yellow);
                    trójkaImieValue.setFont(new Font("Courier",Font.BOLD, 16));
                    trójkaImieValue.setBounds(430, 280, 1200, 50);

                    add(trójkaImieValue);



                    trójkaPunkty.setText(lista.get(2).getPunkty()+"");
                    trójkaPunkty.setForeground(Color.yellow);
                    trójkaPunkty.setFont(new Font("Courier",Font.BOLD, 16));
                    trójkaPunkty.setBounds(700, 280, 1200, 50);

                    add(trójkaPunkty);
                }
            }
            catch(IndexOutOfBoundsException ex)
            {   
                trójkaImieValue.setText("brak");
                trójkaImieValue.setForeground(Color.yellow);
                trójkaImieValue.setFont(new Font("Courier",Font.BOLD, 16));
                trójkaImieValue.setBounds(430, 280, 1200, 50);

                add(trójkaImieValue);
        
                trójkaPunkty.setText("brak");
                trójkaPunkty.setForeground(Color.yellow);
                trójkaPunkty.setFont(new Font("Courier",Font.BOLD, 16));
                trójkaPunkty.setBounds(700, 280, 1200, 50);

                add(trójkaPunkty);
            
            }
        
        
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
