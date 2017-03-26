/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisielecgraficzny;

import Content.MyMainFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import layouts.MyCardLayout;

/**
 *
 * @author Mateusz
 */
public class WisielecGraficzny extends JFrame{

    private static JPanel pContainer = new JPanel();
    private MenuPanel pMenu = new MenuPanel();
    private GraPanel pGra = new GraPanel();
    private StatystykiPanel pStatystyki = new StatystykiPanel();
    private KoniecPanel pKoniec = new KoniecPanel();
    private KategoriaPanel pKategoria = new KategoriaPanel();
    private KoniecGryPanel pKoniecGry = new KoniecGryPanel();
    private PobierzImiePanel pPobierzImie = new PobierzImiePanel();
    private PotwierdzenieWyjsciaGraPanel pPotwierdzenieGra = new PotwierdzenieWyjsciaGraPanel();
    
    
    public WisielecGraficzny() throws IOException
    {
        setTitle("Wisielec - Gra");
        setSize(1200,675);
        Image image = ImageIO.read(new File("icon.png"));
        setIconImage(image);
        
        pContainer.setLayout(MyCardLayout.getCardLayout());
        pContainer.add(pMenu, "Menu");
        pContainer.add(pGra, "Gra");
        pContainer.add(pStatystyki, "Statystyki");
        pContainer.add(pKoniec, "Koniec");
        pContainer.add(pKategoria, "Kategoria");
        pContainer.add(pKoniecGry, "KoniecGry");
        pContainer.add(pPobierzImie, "PobierzImie");
        pContainer.add(pPotwierdzenieGra, "PotwierdzenieGra");
        
        
        add(pContainer);
        
        MyCardLayout.getCardLayout().show(pContainer, "Menu");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public static JPanel getContainerPanel()
    {
        return pContainer;
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        WisielecGraficzny wisielec = new WisielecGraficzny();
        
        MyMainFrame.setMainFrame(wisielec);
        
    }
    
}
