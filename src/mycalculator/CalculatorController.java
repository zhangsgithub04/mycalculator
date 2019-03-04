package mycalculator;

import java.awt.event.ActionListener;

/**
 * Created by zhangs on 1/27/2019.
 * @Copyright Dr. S. Zhang
 * Modification Permission given to SUNY Oneonta CSCI 311/324 students Only
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;

public class CalculatorController implements ActionListener{

    /**
     * @author s zhang
     */
     CalculatorModel model;
     CalculatorView view;

     public CalculatorController(CalculatorModel model, CalculatorView view) {
         this.model = model;
         this.view = view;

         addActionListeners();
        }

     private void addActionListeners() {
         for (JButton[] rows: this.view.keys) {
             for (JButton k: rows)
                 k.addActionListener(this);
         }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked");
            //System.out.println(e.getSource());
            System.out.println(((JButton)e.getSource()).getName());
            String keytext=view.getText(((JButton)e.getSource()).getName());
            model.processinputsignal(keytext);
            view.updateresultdisplay(model.getresult());
            view.updateinputdisplay(model.getinput());


        }
    }
