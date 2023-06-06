package com.mycompany.mindmap.Telas.Inicio;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton{

    public ActionButton(){
//        setContentAreaFilled(false);
//        setBorder(new EmptyBorder(3, 3, 3, 3));
          setBorder(BorderFactory.createLineBorder(Color.blue));
    }  
        
}
