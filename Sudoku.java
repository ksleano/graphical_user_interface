/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.PointAndClickGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;



/**
 *
 * @author kslea_000
 */
public class Sudoku extends javax.swing.JFrame{
    
    EndGame e = new EndGame();
    private Thread timeThread;
    private JPanel[] babyBoards = new JPanel[9];
    private JTextField[][] babyBoardNums = new JTextField[9][9];
    private Boolean[][] alreadyChecked = new Boolean[9][9];
    private Boolean submitted = false;
    private int score = 540;
    private int totalScore;
    private int missed = 0;
    private int[][] solvedSudokuArray = {
        // board 1
        {8, 3, 5, 2, 9, 6, 4, 1, 7}, 
        
        // board 2
        {4, 1, 6, 8, 5, 7, 2, 9, 3},
        
        // board 3    
        {9, 2, 7, 4, 3, 1, 6 ,5 ,8},
            
        // board 4    
        {5, 6, 9, 1, 2, 3, 7, 4, 8},
            
        // board 5    
        {1, 3, 4, 6, 7, 8, 5, 2, 9,},
            
        // board 6
        {7, 8, 2, 5, 4, 9, 1, 6, 3},
            
        // board 7    
        {6, 5, 2, 9, 8, 1, 3, 7 ,4},
            
        // board 8    
        {7, 8, 1, 3, 4, 5, 9, 6, 2},
            
        // board 9
        {3, 9, 4, 2, 7, 6, 8, 1, 5}
    };
        
    public class KeyBinds extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                    
                case KeyEvent.VK_F1:
                    JOptionPane.showMessageDialog(Sudoku.this, " Karl Santino Leano -- 008291296\n "
                            + "Fanny Avila -- 009337549\n "
                            + "Point And Click Game Final Boss\n"
                            + " Winter 2016");
                    break;
            
            }
        }
        
    }                                         
                                        
            
    public Sudoku() {
        initComponents();    
        totalScore = e.getScore();
        System.out.println("in sudoko " + totalScore);
        previousScoreLabel.setText("+ " + totalScore);     
        initBoards();
        generatePremadePuzzle();
        
        Runnable myThread = new Runnable(){

            @Override
            public void run() {
              try{
                  for( ; ; ){
                      Thread.sleep(100);
                      SwingUtilities.invokeLater(new Runnable(){
                      public void run(){
                          updateTime();
                      }
                  });
                  }
              }
              catch(InterruptedException exc){
                  System.out.println("Call to sleep was interrupted.");    
                  System.exit(1);
                  }
              }
            };
       timeThread = new Thread(myThread);
       timeThread.start();
       
    }
    
    
    
    // set the fixed values. Theres probably a better way to do this right?
    private  void generatePremadePuzzle(){
        // board 1
        babyBoardNums[0][0].setText("8");
        babyBoardNums[0][0].setEditable(false);
        babyBoardNums[0][7].setText("1");
        babyBoardNums[0][7].setEditable(false);
        
        // board 2
        babyBoardNums[1][0].setText("4");
        babyBoardNums[1][0].setEditable(false);
        babyBoardNums[1][2].setText("6");
        babyBoardNums[1][2].setEditable(false);
        
        // board 3
        babyBoardNums[2][2].setText("7");
        babyBoardNums[2][2].setEditable(false);
        babyBoardNums[2][3].setText("4");
        babyBoardNums[2][3].setEditable(false);
        babyBoardNums[2][6].setText("6");
        babyBoardNums[2][6].setEditable(false);
        babyBoardNums[2][7].setText("5");
        babyBoardNums[2][7].setEditable(false);
        
        // board 4
        babyBoardNums[3][0].setText("5");
        babyBoardNums[3][0].setEditable(false);
        babyBoardNums[3][2].setText("9");
        babyBoardNums[3][2].setEditable(false);
        babyBoardNums[3][7].setText("4");
        babyBoardNums[3][7].setEditable(false);
        babyBoardNums[3][8].setText("8");
        babyBoardNums[3][8].setEditable(false);
        
        // board 5
        babyBoardNums[4][1].setText("3");
        babyBoardNums[4][1].setEditable(false);
        babyBoardNums[4][4].setText("7");
        babyBoardNums[4][4].setEditable(false);
        babyBoardNums[4][7].setText("2");
        babyBoardNums[4][7].setEditable(false);
        
        // board 6
        babyBoardNums[5][0].setText("7");
        babyBoardNums[5][0].setEditable(false);
        babyBoardNums[5][1].setText("8");
        babyBoardNums[5][1].setEditable(false);
        babyBoardNums[5][6].setText("1");
        babyBoardNums[5][6].setEditable(false);
        babyBoardNums[5][8].setText("3");
        babyBoardNums[5][8].setEditable(false);
        
        // board 7
        babyBoardNums[6][1].setText("5");
        babyBoardNums[6][1].setEditable(false);
        babyBoardNums[6][2].setText("2");
        babyBoardNums[6][2].setEditable(false);
        babyBoardNums[6][5].setText("1");
        babyBoardNums[6][5].setEditable(false);
        babyBoardNums[6][6].setText("3");
        babyBoardNums[6][6].setEditable(false);
        
        // board 8
        babyBoardNums[7][6].setText("9");
        babyBoardNums[7][6].setEditable(false);
        babyBoardNums[7][8].setText("2");
        babyBoardNums[7][8].setEditable(false);
        
        
        // board 9
        babyBoardNums[8][1].setText("9");
        babyBoardNums[8][1].setEditable(false);
        babyBoardNums[8][8].setText("5");
        babyBoardNums[8][8].setEditable(false);
        
    }
    
    private void initBoards(){
        
        scoreLabel.setText(score+"");
        // set the main soduko board as a 3x3 panel
        sudokuBoard.setLayout(new GridLayout(3, 3));
        
        //  limit inputs to 1-9
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(9);
        formatter.setCommitsOnValidEdit(true);
        
        /*  populate the panel with 3x3 boards. the numbers are tracked using 
            nested for loops with i as the board number. 
        */
        for(int i = 0; i < 9; i++){
            babyBoards[i] = new JPanel();
            babyBoards[i].setLayout(new GridLayout(3,3,1,1));
            
            for(int j = 0; j < 9; j++){
                babyBoardNums[i][j] = new JTextField();
                alreadyChecked[i][j] = false;
                babyBoardNums[i][j].setHorizontalAlignment(JTextField.CENTER);
                
                //  Set tooltips. Used later to get THE INDEX of individual cells
                babyBoardNums[i][j].setToolTipText("[" + i + "] [" + j + "]");
                
                //  Add action listeners on each juan
                babyBoardNums[i][j].addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        checkCell(e);
                    }
                });
                
                //  check valid keys
                babyBoardNums[i][j].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    
                    char c = e.getKeyChar();
                    if (!(c >= '1' && c <= '9')|| (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        System.out.println("Invalid Input");
                        e.consume();
                    }
                }
                
                // KEY BINDS
                public void keyPressed(KeyEvent e){
                    int keyCode = e.getKeyCode();
                    switch(keyCode){
                        case KeyEvent.VK_ESCAPE:
                            System.exit(0);
                            break;

                        case KeyEvent.VK_F1:
                            JOptionPane.showMessageDialog(Sudoku.this, " Karl Santino Leano 008291296\n "
                                    + "Fanny Avila 009337549\n "
                                    + "Point And Click Game Final Boss\n"
                                    + " Winter 2016");
                            break;

                    }
                }
                });
                
                // create a border for the baby boards going to the baby daddy
                babyBoards[i].setBorder(new LineBorder(Color.darkGray, 1));
                babyBoards[i].add(babyBoardNums[i][j]);
            }
            // add the individual boards to the main board
            sudokuBoard.add(babyBoards[i]);
            
        }
        
    }  
    
    /*  
        Check every cell on each board. 
    */
    private void checkInput(){
        for(int board = 0; board < 9; board++){
            for(int cell = 0; cell < 9; cell++){
                //  catch the empty strings and penalize using try-catch
                try{
                    //  lock correct answers
                    if(solvedSudokuArray[board][cell] == Integer.parseInt(babyBoardNums[board][cell].getText())){
                            babyBoardNums[board][cell].setForeground(Color.GREEN);
                            babyBoardNums[board][cell].setEditable(false);
                            alreadyChecked[board][cell] = true;
                        
                    }else{
                        // check if the cell is already visited
                        if(alreadyChecked[board][cell] == false){
                            missed++;
                            babyBoardNums[board][cell].setBackground(Color.GRAY);
                            System.out.println(missed);
                            alreadyChecked[board][cell] = true;
                        }
                    }
                }catch(Exception e){
                    //  for empty Strings. Should work the same as else
                    if(alreadyChecked[board][cell] == false){
                        missed++;
                        score = score - 10;
                        scoreLabel.setText(score+"");
                        babyBoardNums[board][cell].setBackground(Color.GRAY);
                        System.out.println(missed);
                        alreadyChecked[board][cell] = true;
                        
                    }
                }
            }
        }    
    }
    
    private void updateScore(int s){
        EndGame e = new EndGame();
        e.addScore(s);
        System.out.println("Score in Endgame after sudoku =  " + e.getScore());
    }
    
    private void updateTime(){
        Date temp = Calendar.getInstance().getTime();
        currentTime.setText(temp.toString());
    }
    
    /*  As of now I have no idea how to dereference the tooltip assigned on each
        cell to find the exact indexes of the text fields. 
        My alternative is to traverse the array and find out the FOCUS OWNER
        using isFocusOwner on each index.
    */
    private void checkCell(java.awt.event.ActionEvent evt) {                                         
        for(int board = 0; board < 9; board++){
            for(int cell = 0; cell < 9; cell++){
                if(babyBoardNums[board][cell].isFocusOwner()){
                    System.out.println("[" + board + "] [" + cell + "]");
                    if(solvedSudokuArray[board][cell] == Integer.parseInt(babyBoardNums[board][cell].getText())){
                        babyBoardNums[board][cell].setForeground(Color.GREEN);
                        babyBoardNums[board][cell].setEditable(false);
                        alreadyChecked[board][cell] = true;
                    }else{
                        // check if the cell is already visited
                        if(alreadyChecked[board][cell] == false){
                            score = score - 10;
                            scoreLabel.setText(score+ "");
                            babyBoardNums[board][cell].setBackground(Color.GRAY);
                            alreadyChecked[board][cell] = true;
                        }
                    }      
                }
            }
        } 
    }   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        topPanel = new javax.swing.JPanel();
        currentTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        previousScoreLabel = new javax.swing.JLabel();
        sodokuPanel = new javax.swing.JPanel();
        sudokuBoard = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        quit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));

        topPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        currentTime.setText("TIME");
        topPanel.add(currentTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 11, 190, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        jLabel1.setText("POSSIBLE SCORE: ");
        topPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        scoreLabel.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        scoreLabel.setText("SCORE");
        topPanel.add(scoreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        previousScoreLabel.setText("+ previous Score");
        topPanel.add(previousScoreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        sodokuPanel.setMaximumSize(new java.awt.Dimension(372, 372));
        sodokuPanel.setMinimumSize(new java.awt.Dimension(372, 372));
        sodokuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sudokuBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sudokuBoard.setMaximumSize(new java.awt.Dimension(360, 360));
        sudokuBoard.setPreferredSize(new java.awt.Dimension(360, 360));
        sudokuBoard.setLayout(new java.awt.GridLayout(3, 3));
        sodokuPanel.add(sudokuBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 426, -1));

        submit.setText("Submit ALL ");
        submit.setToolTipText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        sodokuPanel.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        quit.setText("Quit");
        quit.setToolTipText("You pansy");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });
        sodokuPanel.add(quit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, -1, -1));

        jLabel2.setText("Press ENTER");
        sodokuPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        jLabel3.setText("to submit one cell");
        sodokuPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(sodokuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sodokuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 369, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(616, 439));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        // TODO add your handling code here:
        checkInput();
        e.setFinalScore();
        System.out.print("final score from endgame = " + e.getScore());
        e.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_quitActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        checkInput();
        if(submitted != true){
            updateScore(score);
            submitted = true;
        }
    }//GEN-LAST:event_submitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sudoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sudoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sudoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sudoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sudoku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel previousScoreLabel;
    private javax.swing.JButton quit;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JPanel sodokuPanel;
    private javax.swing.JButton submit;
    private javax.swing.JPanel sudokuBoard;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

}
