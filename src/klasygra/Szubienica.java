/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasygra;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mateusz
 */
public class Szubienica {
    
    Image image;
    int aktualnyNR;
    
    public Szubienica() throws IOException
    {
        image = ImageIO.read(new File("12.png"));
        aktualnyNR = 12;
    }
    
    public int getAktualnyNR() {
        return aktualnyNR;
    }

    public void setAktualnyNR(int aktualnyNR) {
        this.aktualnyNR = aktualnyNR;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public Image nastepnaSzubienica() throws IOException
    {
        aktualnyNR--;
        String nazwaSzubienicy = aktualnyNR+".png";
        
        image = ImageIO.read(new File(nazwaSzubienicy));
        
        return image;
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(image, 15, 60, image.getWidth(null), image.getHeight(null), null);
    }
    
    
    public int getSzanse()
    {
        return aktualnyNR;
    }
    
    
    
}
