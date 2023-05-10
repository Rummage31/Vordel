import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Frame extends JFrame implements ActionListener, KeyListener {
    ////////////////////////////
    JButton buttons[][] = new JButton[6][5];
    JButton resetButton;
    JButton nextButton;
    ////////////////////////////
    JPanel buttonsPanel = new JPanel();
    JPanel lowerButtons = new JPanel();
    ////////////////////////////
    JLabel label;
    JLabel score;
    ////////////////////////////
    int guess;
    int letter;
    int points;
    ////////////////////////////
    boolean canContinue;
    boolean checked[];
    boolean correct[];
    boolean gameOver;
    ////////////////////////////
    String word;
    ////////////////////////////

    public Frame(int points) {

        this.points = points;

        word = new Words().getWords();

        checked = new boolean[5];
        correct = new boolean[5];
        guess = 0;
        letter = 0;
        canContinue = true;

        //System.out.println(word);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setSize(500, 600);
////////////////////////////////////////////////////////////////////////////////////////////////////
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("MLA", Font.BOLD, 20));
        label.setForeground(new Color(162, 120, 206));
        label.setOpaque(true);
        label.setText("Vordle");
        label.setBackground(Color.BLACK);

        score = new JLabel();
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setFont(new Font("MLA", Font.BOLD, 20));
        score.setOpaque(true);
        score.setText("Score: " + points + " "); //Space after to make it look clear
        score.setBackground(Color.CYAN);

        resetButton = new JButton();
        resetButton.setForeground(Color.BLUE);
        resetButton.setText("Reset");
        resetButton.setFont(new Font("MLA", Font.BOLD, 20));
        resetButton.setBackground(Color.BLACK);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        nextButton = new JButton();
        nextButton.setForeground(Color.BLUE);
        nextButton.setText("Next");
        nextButton.setFont(new Font("MLA", Font.BOLD, 20));
        nextButton.setBackground(Color.BLACK);
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);

        buttonsPanel.setVisible(true);
        buttonsPanel.setLayout(new GridLayout(6, 5));

        lowerButtons.setVisible(true);
        lowerButtons.setLayout(new GridLayout(1, 2));
        lowerButtons.add(resetButton);
        lowerButtons.add(nextButton);

/////////////////////////////////////////////////////////////////////////////////////
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[0].length; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(Color.WHITE);
                buttons[r][c].setEnabled(false);
                buttons[r][c].setFont(new Font("MLA", Font.BOLD, 20));
                buttonsPanel.add(buttons[r][c]);


            }

        }
///////////////////////////////////////

        this.add(lowerButtons, BorderLayout.SOUTH);
        this.add(label, BorderLayout.NORTH);
        this.add(score, BorderLayout.EAST);
        this.getContentPane().setBackground(Color.WHITE);
        this.add(buttonsPanel);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setTitle("Vordle");
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


        if (!gameOver) {
            int code = e.getKeyCode();

            // System.out.println(code);


            if (code >= 65 && code <= 90) {
                if (canContinue) {
                    if (letter < 5) {
                        buttons[guess][letter].setText(String.valueOf((char) code));
                        letter++;
                    }
                } else {
                    canContinue = false;
                }


            } else if (code == 10) {
                if (guess < 6) {
                    if (letter == 5) {
                        String wordTyped = "";
                        for (int i = 0; i < 5; i++) {
                            wordTyped = wordTyped + buttons[guess][i].getText();
                            label.setForeground(new Color(103, 231, 61));
//                            label.setText(String.valueOf(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256))));
                            checkWord(wordTyped.toLowerCase());
                            checkWinner();

                        }
                        guess++;
                    }
                }
            } else if (code == 8) {
                if (letter > 0) {
                    letter--;
                    canContinue = true;
                    buttons[guess][letter].setText("");
                }

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == resetButton) {
            this.dispose();
            new Frame(0);
        }
        if (e.getSource() == nextButton) {
            this.dispose();
            new Frame(points++);
        }
    }

    public void checkWord(String toBeChecked) {
        int[] frequency = new int[5];
        for (int i = 0; i < 5; i++) {
            checked[i] = false;
            correct[i] = false;
            frequency[i] = 0;
        }

        ArrayList<Character> finalWord = new ArrayList<Character>();
        ArrayList<Character> toCharChecked = new ArrayList<Character>();
        for (int i = 0; i < word.length(); i++)
            finalWord.add(word.charAt(i));
        for (int i = 0; i < toBeChecked.length(); i++)
            toCharChecked.add(toBeChecked.charAt(i));

        for (int i = 0; i < 5; i++) {
            if ((buttons[guess][i].getText().toLowerCase()).equals(finalWord.get(i).toString())) {
                buttons[guess][i].setBackground(Color.GREEN);
                checked[i] = true;
                correct[i] = true;
                frequency[i] = 1;
            }
        }

        for (int i = 0; i < toCharChecked.size(); i++){
            for (int j = 0; j < finalWord.size(); j++) {
                if (finalWord.get(j) == toCharChecked.get(i)) {
                    if (frequency[j] < 1) {
                        if (checked[i] == false) {
                            buttons[guess][i].setBackground(Color.YELLOW);
                            checked[i] = true;
                            frequency[j] = 1;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++){
            if (checked[i] == false)
                buttons[guess][i].setBackground(Color.lightGray);
        }
    }

    public void checkWinner() {
        if (guess == 5) {
            label.setBackground(Color.RED);
            label.setForeground(Color.BLACK);
            label.setText("You Lose Stupid. The word was: " + word);

            gameOver = true;
        }

        int count = 0;
        for (int i = 0; i < correct.length; i++)
            if (correct[i] == true)
                count++;

        if (count == 5) {
            label.setBackground(Color.GREEN);
            label.setForeground(Color.BLACK);
            label.setText("WOW GREAT JOB!!!!");

            gameOver = true;
            nextButton.setEnabled(true);
        }


    }
}

