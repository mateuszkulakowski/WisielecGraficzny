/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import wisielecgraficzny.WisielecGraficzny;

/**
 *
 * @author Mateusz
 */
public class MyMainFrame {
    
    private static WisielecGraficzny wisielec;
    
    public static WisielecGraficzny getMainFrame()
    {
        return wisielec;
    }
    
    public static void setMainFrame(WisielecGraficzny g)
    {
        wisielec = g;
    }
    
    
}
