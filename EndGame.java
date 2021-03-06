/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.PointAndClickGame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;


/**
 *
 * @author kslea_000
 */
public class EndGame extends javax.swing.JFrame {
    Hangman h = new Hangman();
    private static int score;
    private String name;
    File file = new File("example.txt");
    
    public class KeyBinds extends KeyAdapter{
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;

                    case KeyEvent.VK_F1:
                        JOptionPane.showMessageDialog(EndGame.this, " Karl Santino Leano 008291296\n "
                                + "Fanny Avila 009337549\n "
                                + "Point And Click Game Final Boss\n"
                                + " Winter 2016");
                        break;

                }
            }
        }
        
        
    /**
     * Creates new form EndGame
     */
    public EndGame(){
        initComponents();
        nameBox.addKeyListener(new KeyBinds());
    }
    
    ////////////////////////////// Methods ////////////////////////////////////
    
    // write to the text file for processing. 
    private void updateHighScores(){
        try{
            PrintWriter output = new PrintWriter((new FileOutputStream(new File("highscores.txt"), true)));
            output.printf("%n%d %s", getScore(),nameBox.getText());
            output.close();
        }catch(IOException ex){
            System.out.printf("Error: %s\n", ex);
        }
        

    }
    
    
    
    
    public void ijustwanttohidethis(){
/*  For Hangman v1
    
    public EndGame(int i){
        setScore(i);
        setFinalScore();
    }
    
    // for Hangman V1
    public EndGame(int i, boolean solved, boolean skipped) {
        initComponents();
        setScore(i);
        if(solved){
            scoreLabel.setText(getScore()+"");
        }else{
            if(skipped){
                scoreLabel.setText("0");
            }else{
                scoreLabel.setText(getScore() +"");
            }
        }
        
    }
*/
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////// GET/SET ////////////////////////////////////
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void addScore(int i){
        System.out.println("score from color me " + i + "score in EG = " + getScore());
        this.score = getScore() + i;
    }
    
    public void setFinalScore(){
        System.out.println("set final score " + getScore());
        scoreLabel.setText(getScore() + "");
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scoreLabel = new javax.swing.JLabel();
        gameOverLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        yourScoreLabel = new javax.swing.JLabel();
        namePromptLabel = new javax.swing.JLabel();
        nameBox = new javax.swing.JTextField();
        confirmButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        scoreLabel.setFont(new java.awt.Font("Chiller", 0, 36)); // NOI18N
        scoreLabel.setText("Score");

        gameOverLabel.setFont(new java.awt.Font("Chiller", 1, 48)); // NOI18N
        gameOverLabel.setText("Game Over");

        backButton.setText("Main Menu");
        backButton.setToolTipText("Back to Main Menu");
        backButton.setFocusable(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        yourScoreLabel.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        yourScoreLabel.setText("Your Score:");

        namePromptLabel.setText("Enter your name: ");

        nameBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameBoxMouseClicked(evt);
            }
        });
        nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameBoxActionPerformed(evt);
            }
        });

        confirmButton.setText("Enter");
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(namePromptLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmButton)
                .addGap(0, 176, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gameOverLabel)
                        .addGap(210, 210, 210))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(yourScoreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreLabel)
                        .addGap(230, 230, 230))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(109, 109, 109)
                .addComponent(gameOverLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scoreLabel)
                    .addComponent(yourScoreLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(namePromptLabel)
                    .addComponent(nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // RESET THE SCORE 
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        setScore(0);
        this.setVisible(false);
        MainMenu m = new MainMenu();
        m.setVisible(true);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        updateHighScores();
        setScore(0);
        this.setVisible(false);
        MainMenu m = new MainMenu();
        m.setVisible(true);
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameBoxActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        nameBox.setFocusable(false);
    }//GEN-LAST:event_formMouseClicked

    private void nameBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameBoxMouseClicked
        // TODO add your handling code here:
        nameBox.setFocusable(true);
    }//GEN-LAST:event_nameBoxMouseClicked

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
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EndGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JToggleButton confirmButton;
    private javax.swing.JLabel gameOverLabel;
    private javax.swing.JTextField nameBox;
    private javax.swing.JLabel namePromptLabel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel yourScoreLabel;
    // End of variables declaration//GEN-END:variables
}
