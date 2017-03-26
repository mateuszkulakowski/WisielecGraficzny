/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import hibernate.ConnectionFactory;
import hibernate.dao.Pytanie;
import hibernate.dao.Wyniki;
import java.awt.CardLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import klasygra.Szubienica;
import layouts.MyCardLayout;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mateusz
 */
public class GraPanel extends JPanel implements KeyListener{
    
    //JComponenty
    private Szubienica szubienica;
    private JLabel lNapisSzubienica = new JLabel();
    private JLabel lWynik = new JLabel();
    private JLabel lWynikValue = new JLabel();
    private JLabel lWybranaKategoria = new JLabel();
    private static JLabel lWybranaKategoriaValue = new JLabel();
    private JLabel lPodpowiedzValue = new JLabel();
    private JLabel lPodpowiedz = new JLabel();
    private JLabel lOdpowiedzValue = new JLabel();
    private JLabel lLiterkaJużByła = new JLabel();
    private JLabel lNapisLiterki = new JLabel();
    private List<JLabel> WirtualaKlawiatura = new ArrayList();
    private JButton bDalej = new JButton();
    
    //zmienne dot. Pytania
    private List<Pytanie> PytaniaNieodgadniete;
    private Pytanie AktualnePytanie;
    private List<Character> ZgadnięteLiterki;
    private HashMap<Character,Boolean> WszystkieLiterki = new HashMap();
    
    //zmienne pomocnicze
    private Boolean wszystkie_zgadniete = false;
    private Boolean przegrana = false;
    private Font podpowiedzFont = new Font("Courier",Font.BOLD,20);
    private Font haslofont = new Font("Courier",Font.BOLD,30);
    private Font literkajuzbylafont = new Font("Courier",Font.BOLD,18);
    private static int wynik = 0;
    private Image backgroundImage = ImageIO.read(new File("czysty.jpg"));
    private Session session = ConnectionFactory.getSessionFactory().openSession();
    
    
    public GraPanel() throws IOException
    {
        
        super();
        addComponentListener( new ComponentAdapter() {
        @Override
        public void componentShown( ComponentEvent e ) {
            requestFocusInWindow();
        }
    });
        
        
        setLayout(null);
        
        try {
            this.szubienica = new Szubienica();
            
        } catch (IOException ex) {
            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pobierzListeHaseł();
        wylosujPytanie();
        inicjacjaZgadnieteLiterki();
        
        //WirtualnaKlawiatura
        Font font = new Font("Courier",Font.BOLD,16);
        
        int poczatek = 20;
        int odstepy = 20;
        int wysokosclinia = 450;
        int odstepylinie = 20;
        int width = 30;
        int height = 30;
        
        //linia 1
        JLabel a = new JLabel();
        a.setText("a");
        a.setForeground(Color.white);
        a.setFont(font);
        a.setBounds(poczatek, wysokosclinia, width, height);
        WirtualaKlawiatura.add(a);
        
        JLabel b = new JLabel();
        b.setText("b");
        b.setForeground(Color.white);
        b.setFont(font);
        b.setBounds(poczatek+odstepy, wysokosclinia, width, height);
        WirtualaKlawiatura.add(b);
        
        JLabel c = new JLabel();
        c.setText("c");
        c.setForeground(Color.white);
        c.setFont(font);
        c.setBounds(poczatek+odstepy*2, wysokosclinia, width, height);
        WirtualaKlawiatura.add(c);
        
        JLabel ć = new JLabel();
        ć.setText("ć");
        ć.setForeground(Color.white);
        ć.setFont(font);
        ć.setBounds(poczatek+odstepy*3, wysokosclinia, width, height);
        WirtualaKlawiatura.add(ć);
        
        JLabel d = new JLabel();
        d.setText("d");
        d.setForeground(Color.white);
        d.setFont(font);
        d.setBounds(poczatek+odstepy*4, wysokosclinia, width, height);
        WirtualaKlawiatura.add(d);
        
        JLabel e = new JLabel();
        e.setText("e");
        e.setForeground(Color.white);
        e.setFont(font);
        e.setBounds(poczatek+odstepy*5, wysokosclinia, width, height);
        WirtualaKlawiatura.add(e);
        
        JLabel ę = new JLabel();
        ę.setText("ę");
        ę.setForeground(Color.white);
        ę.setFont(font);
        ę.setBounds(poczatek+odstepy*6, wysokosclinia, width, height);
        WirtualaKlawiatura.add(ę);
        
        JLabel f = new JLabel();
        f.setText("f");
        f.setForeground(Color.white);
        f.setFont(font);
        f.setBounds(poczatek+odstepy*7, wysokosclinia, width, height);
        WirtualaKlawiatura.add(f);
        
        JLabel g = new JLabel();
        g.setText("g");
        g.setForeground(Color.white);
        g.setFont(font);
        g.setBounds(poczatek+odstepy*8, wysokosclinia, width, height);
        WirtualaKlawiatura.add(g);
        
        JLabel h = new JLabel();
        h.setText("h");
        h.setForeground(Color.white);
        h.setFont(font);
        h.setBounds(poczatek+odstepy*9, wysokosclinia, width, height);
        WirtualaKlawiatura.add(h);
        
        JLabel i = new JLabel();
        i.setText("i");
        i.setForeground(Color.white);
        i.setFont(font);
        i.setBounds(poczatek+odstepy*10, wysokosclinia, width, height);
        WirtualaKlawiatura.add(i);
        
        JLabel j = new JLabel();
        j.setText("j");
        j.setForeground(Color.white);
        j.setFont(font);
        j.setBounds(poczatek+odstepy*11, wysokosclinia, width, height);
        WirtualaKlawiatura.add(j);
        
        
        //linia 2
        
        JLabel k = new JLabel();
        k.setText("k");
        k.setForeground(Color.white);
        k.setFont(font);
        k.setBounds(poczatek, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(k);
        
        
        JLabel l = new JLabel();
        l.setText("l");
        l.setForeground(Color.white);
        l.setFont(font);
        l.setBounds(poczatek+odstepy, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(l);
        
        
        JLabel ł = new JLabel();
        ł.setText("ł");
        ł.setForeground(Color.white);
        ł.setFont(font);
        ł.setBounds(poczatek+odstepy*2, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(ł);
        
        JLabel m = new JLabel();
        m.setText("m");
        m.setForeground(Color.white);
        m.setFont(font);
        m.setBounds(poczatek+odstepy*3, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(m);
        
        JLabel n = new JLabel();
        n.setText("n");
        n.setForeground(Color.white);
        n.setFont(font);
        n.setBounds(poczatek+odstepy*4, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(n);
        
        JLabel ń = new JLabel();
        ń.setText("ń");
        ń.setForeground(Color.white);
        ń.setFont(font);
        ń.setBounds(poczatek+odstepy*5, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(ń);
        
        JLabel o = new JLabel();
        o.setText("o");
        o.setForeground(Color.white);
        o.setFont(font);
        o.setBounds(poczatek+odstepy*6, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(o);
        
        
        JLabel ó = new JLabel();
        ó.setText("ó");
        ó.setForeground(Color.white);
        ó.setFont(font);
        ó.setBounds(poczatek+odstepy*7, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(ó);
        
        JLabel p = new JLabel();
        p.setText("p");
        p.setForeground(Color.white);
        p.setFont(font);
        p.setBounds(poczatek+odstepy*8, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(p);
        
        JLabel r = new JLabel();
        r.setText("r");
        r.setForeground(Color.white);
        r.setFont(font);
        r.setBounds(poczatek+odstepy*9, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(r);
        
        JLabel s = new JLabel();
        s.setText("s");
        s.setForeground(Color.white);
        s.setFont(font);
        s.setBounds(poczatek+odstepy*10, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(s);
        
        JLabel ś = new JLabel();
        ś.setText("ś");
        ś.setForeground(Color.white);
        ś.setFont(font);
        ś.setBounds(poczatek+odstepy*11, wysokosclinia+odstepylinie, width, height);
        WirtualaKlawiatura.add(ś);
        
        //linia 3
        
        JLabel t = new JLabel();
        t.setText("t");
        t.setForeground(Color.white);
        t.setFont(font);
        t.setBounds(poczatek, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(t);
        
        JLabel u = new JLabel();
        u.setText("u");
        u.setForeground(Color.white);
        u.setFont(font);
        u.setBounds(poczatek+odstepy, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(u);
        
        JLabel w = new JLabel();
        w.setText("w");
        w.setForeground(Color.white);
        w.setFont(font);
        w.setBounds(poczatek+odstepy*2, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(w);
        
        JLabel x = new JLabel();
        x.setText("x");
        x.setForeground(Color.white);
        x.setFont(font);
        x.setBounds(poczatek+odstepy*3, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(x);
        
        JLabel y = new JLabel();
        y.setText("y");
        y.setForeground(Color.white);
        y.setFont(font);
        y.setBounds(poczatek+odstepy*4, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(y);
        
        JLabel z = new JLabel();
        z.setText("z");
        z.setForeground(Color.white);
        z.setFont(font);
        z.setBounds(poczatek+odstepy*5, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(z);
        
        JLabel ź = new JLabel();
        ź.setText("ź");
        ź.setForeground(Color.white);
        ź.setFont(font);
        ź.setBounds(poczatek+odstepy*6, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(ź);
        
        JLabel ż = new JLabel();
        ż.setText("ż");
        ż.setForeground(Color.white);
        ż.setFont(font);
        ż.setBounds(poczatek+odstepy*7, wysokosclinia+odstepylinie*2, width, height);
        WirtualaKlawiatura.add(ż);
        
        
        
        //JButton przejście dalej po przegranej / wygranej 
        
        
        Image image = ImageIO.read(new File("dalej.jpg"));
        bDalej.setIcon(new ImageIcon(image));
        bDalej.setBounds(920, 500, image.getWidth(null)-20, image.getHeight(null)-10);
        bDalej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lWybranaKategoriaValue.setText("Brak");
                
                Transaction tx = session.beginTransaction();
      
                Wyniki wyniki = new Wyniki();
                wyniki.setImie(PobierzImiePanel.getImie());
                wyniki.setPunkty(wynik);
        
        
                session.save(wyniki);
                session.flush();
                tx.commit();
                
                MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "KoniecGry");
            }
        });
        
        
        //JLabele
       
        
        lNapisLiterki.setText("Kliknięte Literki:");
        lNapisLiterki.setForeground(Color.LIGHT_GRAY);
        lNapisLiterki.setFont(font);
        lNapisLiterki.setBounds(60, 410, 150, 15);
        
        
        lLiterkaJużByła.setText("Podana literka już była !");
        lLiterkaJużByła.setForeground(Color.RED);
        lLiterkaJużByła.setFont(literkajuzbylafont);
        lLiterkaJużByła.setBounds(20, 520, 250, 50);
        
        
        lOdpowiedzValue.setText(getHasłoEkran());
        lOdpowiedzValue.setForeground(Color.RED);
        lOdpowiedzValue.setBounds(500, 350, 800, 50);
        lOdpowiedzValue.setFont(haslofont);
        
        lPodpowiedz.setText("Podpowiedź: ");
        lPodpowiedz.setForeground(Color.RED);
        lPodpowiedz.setBounds(350, 120, 200, 50);
        lPodpowiedz.setFont(podpowiedzFont);
        
        lPodpowiedzValue.setText(AktualnePytanie.getPodpowiedz());
        lPodpowiedzValue.setForeground(Color.white);
        lPodpowiedzValue.setBounds(380, 170, 810, 50);
        lPodpowiedzValue.setFont(podpowiedzFont);
        
        
        lWybranaKategoria.setText("Wybrana kategoria: ");
        lWybranaKategoria.setForeground(Color.white);
        lWybranaKategoria.setBounds(970, 10, 150, 50);
        
        lWybranaKategoriaValue.setText(KategoriaPanel.getWybranaKategoria());
        lWybranaKategoriaValue.setForeground(Color.RED);
        lWybranaKategoriaValue.setBounds(1090, 10, 100, 50);
        
        
        lWynik.setText("Aktualny wynik: ");
        lWynik.setForeground(Color.white);
        lWynik.setBounds(995, 50, 100, 50);
        
        lWynikValue.setText(wynik+"");
        lWynikValue.setForeground(Color.RED);
        lWynikValue.setBounds(1090, 50, 50, 50);
        
        lNapisSzubienica.setText("Stan szubienicy");
        lNapisSzubienica.setForeground(Color.white);
        lNapisSzubienica.setBounds(20, 30, 100, 50);
        
        for(JLabel label : WirtualaKlawiatura)add(label);
        
        add(lNapisLiterki);
        add(lOdpowiedzValue);
        add(lPodpowiedz);
        add(lPodpowiedzValue);
        add(lWynikValue);
        add(lWybranaKategoriaValue);
        add(lWybranaKategoria);
        add(lWynik);
        add(lNapisSzubienica);
        
        addKeyListener(this);
        setFocusable(true);
        
    }
    
    public Szubienica getSzubienica() {
        return szubienica;
    }

    public void setSzubienica(Szubienica szubienica) {
        this.szubienica = szubienica;
    }
    
    public void nastepnaSzubienica() throws IOException
    {
        if(szubienica.getSzanse() == 0)//koniec gry
        {
            szubienica.setAktualnyNR(12);
        }
        
        szubienica.nastepnaSzubienica();
    }
    
   

    @Override
    protected void paintComponent(Graphics g) {
        
        if(!KategoriaPanel.getWybranaKategoria().toLowerCase().equals(lWybranaKategoriaValue.getText().toLowerCase()))
        {
            wszystkie_zgadniete = false;
            przegrana = false;
            remove(bDalej);
            pobierzListeHaseł();
            wylosujPytanie();
            inicjacjaZgadnieteLiterki();
            wynik = 0;
            try {
                szubienica = new Szubienica();
            } catch (IOException ex) {
                Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            WszystkieLiterki = new HashMap();
            
            for(JLabel label: WirtualaKlawiatura)label.setForeground(null);

            lOdpowiedzValue.setText(getHasłoEkran());
            lPodpowiedzValue.setText(AktualnePytanie.getPodpowiedz());
            lWybranaKategoriaValue.setText(KategoriaPanel.getWybranaKategoria());
            lWynikValue.setText(wynik+"");
        }
        
            //Painting
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);
            szubienica.draw(g);
        
    }



    
    
    
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "PotwierdzenieGra");
        }
        
        
        
        
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        remove(lLiterkaJużByła);
        repaint();
        Boolean czy_trafiona;
        
        if(szubienica.getSzanse() != 0 && !czyZgadnieteHaslo())
        { 
            if(e.getKeyChar() == 'a')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ą')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {
                        
                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();

                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'b')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'c')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ć')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'd')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {
                        
                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();

                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }if(e.getKeyChar() == 'e')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ę')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'f')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'g')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'h')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'i')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'j')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'k')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'l')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ł')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'm')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'n')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ń')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'o')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ó')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'p')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'r')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 's')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ś')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 't')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'u')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'w')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'x')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'y')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'z')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ź')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {
                        
                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();

                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
            if(e.getKeyChar() == 'ż')
            {

                //sprawdzanie czy już wystąpiła
                Boolean czy_już_jest = sprawdźCzyLiterkaJestWMapie(e.getKeyChar());

                if(!czy_już_jest)//literki nie było
                {

                    //sprawdzanie czy trafiona czy nie
                    czy_trafiona = updateHasłoEkran(e.getKeyChar());

                    if(czy_trafiona)//trafiona
                    {
                        
                        wynik++;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na zielono literkę

                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.GREEN);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), true);
                        lOdpowiedzValue.setText(getHasłoEkran());
                        repaint();
                    }
                    else//literka nie trafiona
                    {

                        wynik-=2;
                        lWynikValue.setText(wynik+"");
                
                        repaint();
                        
                        //zapalamy na czerwono literke
                        for(JLabel label: WirtualaKlawiatura)
                        {
                            if(label.getText().charAt(0) == e.getKeyChar())
                            {
                                label.setForeground(Color.red);
                                repaint();
                                break;
                            }
                        }


                        WszystkieLiterki.put(e.getKeyChar(), false);
                        try 
                        {
                            szubienica.nastepnaSzubienica();
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                    }
                }
                else //literka już jest w mapie
                {
                    add(lLiterkaJużByła);
                    repaint();
                }
            }
        
        
        
        }
        
        
        if(czyZgadnieteHaslo()) //wygrana
        {
            
            Boolean czy_wszystkie_zgadniete = wylosujPytanie();
            
            if(!czy_wszystkie_zgadniete)
            {
                if(!wszystkie_zgadniete)
                {
                    wszystkie_zgadniete = true;
                    wynik+=100;
                }
                
                
                lWynikValue.setText(wynik+"");
                System.out.println("Koniec gry zgadniete wszystko");
                
                add(bDalej);
                
                repaint();
            }
            else
            {
                System.out.println("Losuję kolejne pytanie");
                
                inicjacjaZgadnieteLiterki();
                WszystkieLiterki = new HashMap();
                wynik+=10;
                lWynikValue.setText(wynik+"");
                try {
                    szubienica = new Szubienica();
                } catch (IOException ex) {
                    Logger.getLogger(GraPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for(JLabel label: WirtualaKlawiatura)label.setForeground(null);
                    
                
                lOdpowiedzValue.setText(getHasłoEkran());
                lPodpowiedzValue.setText(AktualnePytanie.getPodpowiedz());
                
                repaint();
                
            }
            
        }
         //przegrana
         if(szubienica.getSzanse() == 0)
        {
            if(!przegrana)
                {
                    przegrana = true;
                    wynik-=10;
                }
            
            
            lWynikValue.setText(wynik+"");
            System.out.println("przegrana");
            add(bDalej);
            
            repaint();
        }
    }
    
    
    
    
    
    
    
    private void pobierzListeHaseł()
    {
        String hql = "FROM Pytanie p WHERE p.kategoria='"+KategoriaPanel.getWybranaKategoria()+"'";
        Query query = session.createQuery(hql);
        PytaniaNieodgadniete = query.list();
    }
    
    
    private Boolean wylosujPytanie()//false gdy na liście nie ma elementów
    {
        Random random = new Random();
        if(PytaniaNieodgadniete.isEmpty())return false;
        
        int wylosowanyNumer = random.nextInt(PytaniaNieodgadniete.size());
        AktualnePytanie = PytaniaNieodgadniete.get(wylosowanyNumer);
        
        PytaniaNieodgadniete.remove(wylosowanyNumer);
        
        return true;
    }
    
    private Boolean czyZgadnieteHaslo()
    {
        Boolean czy = true;
        
        for(Character literka: ZgadnięteLiterki)
        {
            if(literka == '_')
            {
                czy=false;
            }
        }
        return czy;
    }
    
    
    private void inicjacjaZgadnieteLiterki()
    {
        ZgadnięteLiterki = new ArrayList();
        String haslo = AktualnePytanie.getOdpowiedz();
        
        for (int i = 0; i < haslo.length(); i++) {

            if(haslo.charAt(i) == ' ')ZgadnięteLiterki.add(i, ' ');
            else ZgadnięteLiterki.add(i, '_');
            
        }
        
    }
    
    
    private String getHasłoEkran()
    {
        String wydruk = "";
        
        for(int i=0; i<ZgadnięteLiterki.size(); i++)
        {
            if(ZgadnięteLiterki.get(i) == ' ')wydruk+=" ";
            else if(ZgadnięteLiterki.get(i) == '_')wydruk+="_ ";
            else wydruk+= ZgadnięteLiterki.get(i)+" ";
        }
        
        return wydruk;
    }
    
    
    
    private Boolean updateHasłoEkran(char literka)
    {
        Boolean czy_trafienie = false;
        String haslo = AktualnePytanie.getOdpowiedz();
        
        for(int i=0; i<haslo.length(); i++)
        {
            if(Character.toUpperCase(haslo.charAt(i)) == Character.toUpperCase(literka))//literka wystepuje w naszym haśle, musimy ją podmienić żeby było widać że zgadnięta
            {
                ZgadnięteLiterki.set(i, Character.toUpperCase(literka));
                czy_trafienie=true;
            }
        }
        
        return czy_trafienie;
    }
    
    
    private Boolean sprawdźCzyLiterkaJestWMapie(char literka)
    {
        Boolean czy=false;
       for(Character key : WszystkieLiterki.keySet())
       {
           if(key.equals(literka))
           {
               czy=true;
               break;
           }
       }
        return czy;
    }

    public static int getWynik() {
        return wynik;
    }

    
    public static void setKategoria(String kategoria)
    {
        lWybranaKategoriaValue.setText(kategoria);
    }
    
    
}
