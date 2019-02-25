package mycalculator;

import javafx.geometry.Orientation;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by zhangs on 1/27/2019.
 * @Copyright Dr. S. Zhang
 * Modification Permission given to SUNY Oneonta CSCI 311/324 students Only
 */
class CalculatorView  extends JFrame{

    static String CAPTIONS[]=new String[]{"0","1","2","+","3", ".","=","-"};
    static final String NAMES[]=new String[]{"zero","one","two","plus","three", "dpoint","assignment","minus"};

    private int rownum;
    private int colnum;
    private JLabel currentinput;
    private JLabel currentresult;
    JButton[][] keys;

    public void updateresultdisplay(String r)
    {
        currentresult.setText(r);

    }

    public void updateinputdisplay(String in)
    {
        currentinput.setText(in);

    }
    public int whereisname(String name)
    {
        int i;
        for(i=0;i<NAMES.length;i++)
        {
            if (NAMES[i].equals(name)) break;
        }
        return i;
    }

    public String getText(String name)
    {
        int whichone=whereisname(name);
        return CAPTIONS[whichone];
    }

    public CalculatorView()
    {
        colnum=4;
        rownum=2;
        // Creating Object of Jpanel class
        JPanel pa = new JPanel();
        // set the layout
        pa.setLayout(new BorderLayout());

        // add a new JButton with name "wel" and it is
        // lie top of the container
        JPanel display=new JPanel();
        display.setLayout(new GridLayout(2,1));
        display.setBackground(Color.WHITE);
        display.setPreferredSize(new Dimension(600, 100));

        currentinput = new JLabel("0.0", JLabel.RIGHT);
        currentresult = new JLabel("0.0 ", JLabel.RIGHT);


        currentinput.setPreferredSize(new Dimension(100,100));
        currentresult.setPreferredSize(new Dimension(100,100));

        currentresult.setBackground(Color.BLACK);
        currentresult.setForeground(Color.RED);
        currentinput.setBackground(Color.BLACK);
        currentinput.setForeground(Color.RED);

        currentresult.setOpaque(true);
        currentinput.setOpaque(true);

        display.add(currentinput);
        display.add(currentresult);
       pa.add(display, BorderLayout.NORTH);

        JPanel board=new JPanel();
        keys=new JButton[rownum][colnum];
        board.setPreferredSize(new Dimension(600, 400));
        board.setBackground(Color.YELLOW);
        GridLayout blayout=new GridLayout(rownum,colnum);
        board.setLayout(blayout);
        for(int row=0;row<rownum;row++){
            for(int col=0;col<colnum;col++){
                keys[row][col] = new JButton(CAPTIONS[row*colnum+col]);
                keys[row][col].setFont(new Font("Serif", Font.PLAIN, 32));
                keys[row][col].setName(NAMES[row*colnum+col]);
                board.add(keys[row][col]);
            }
        }
        pa.add(board, BorderLayout.CENTER);


        add(pa);

        // Function to close the operation of JFrame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set size of JFrame.
        setPreferredSize(new Dimension(600, 600));
        //setSize(600, 600);
        // Function to set visible status of JFrame.
        pack();
        setTitle("CSCI 311 Spring 2019 My Calculator");
        setVisible(true);
    }


}
