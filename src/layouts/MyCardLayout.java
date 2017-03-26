/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layouts;

import java.awt.CardLayout;

/**
 *
 * @author Mateusz
 */
public class MyCardLayout {
    
    private static CardLayout cardLayout;
    
    
    public static CardLayout getCardLayout()
    {
        if(cardLayout == null)
        {
            cardLayout = new CardLayout();
        }
        
        return cardLayout;
    }
    
}
