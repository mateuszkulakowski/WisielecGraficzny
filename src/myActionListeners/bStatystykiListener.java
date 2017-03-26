/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import layouts.MyCardLayout;
import wisielecgraficzny.WisielecGraficzny;

/**
 *
 * @author Mateusz
 */
public class bStatystykiListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        MyCardLayout.getCardLayout().show(WisielecGraficzny.getContainerPanel(), "Statystyki");
    }
    
}
