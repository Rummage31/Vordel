import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Frame extends JFrame implements ActionListener, KeyListener {

    JButton buttons[][] = new JButton[6][5];
    JPanel buttonsPanel = new JPanel();

    int guess,letter;
    boolean canContinue = true;
    boolean  gameOver;
    String word;

    public Frame(){

        word = new Words().getWords();
        //System.out.println(word);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setSize(500,600);

        buttonsPanel.setVisible(true);
        buttonsPanel.setLayout(new GridLayout(6,5));


        for(int r = 0; r<buttons.length;r++){
            for(int c = 0; c<buttons[0].length;c++){
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(Color.WHITE);
                buttons[r][c].setEnabled(false);
                buttons[r][c].setFont(new Font("MLA", Font.BOLD,20));
                buttonsPanel.add(buttons[r][c]);



            }

        }
        this.add(buttonsPanel);
        this.setTitle("Vordle");
        this.addKeyListener(this);
        this.setFocusable(true);
        this.repaint();
        this.revalidate();
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();
       // System.out.println(code);


        if(code>=65 && code<=90) {
            if (canContinue) {
                if (letter < 5) {
                    buttons[guess][letter].setText(String.valueOf((char)code));
                    letter++;
                }
            }
            else{
                canContinue = false;
            }


        } else if (code == 10) {
            if(guess<6){
                if(letter == 5){
                    String wordTyped ="";
                    for(int i = 0;i<5;i++){
                        wordTyped = wordTyped + buttons[guess][i].getText();
                    }
                }
            }
        }


        else if (code == 8){
            if(letter>0){
                letter--;
                canContinue = true;
                buttons[guess][letter].setText("");
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    

}
