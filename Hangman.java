/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.PointAndClickGame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


/**
 *
 * @author kslea_000
 */


public class Hangman extends javax.swing.JFrame implements ActionListener{
    
    private boolean solved = false, skipped = false;
    private Thread timeThread;
    private String magicWord;
    private char[] magicWordArray;
    private char[] solvedMagicWordArray;
    private final String[] veryGraphicImages = {"0.png","1.png","2.png","3.png",
                                                "4.png","5.png","6.png"};
    private static char inputBuffer;
    private int score = 100;
    
    private int numTries = 0;
    private String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy",
                                 "climbing"};
    private Random randomPicker = new Random(System.currentTimeMillis());
    
        public class KeyBinds extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                    
                case KeyEvent.VK_F1:
                    JOptionPane.showMessageDialog(Hangman.this, " Karl Santino Leano 008291296\n "
                            + "Fanny Avila 009337549\n "
                            + "Point And Click Game Final Boss\n"
                            + " Winter 2016");
                    break;
            
            }
        }
        
    }
    
    public Hangman() {
        initComponents();
        buttonSkip.addKeyListener(new KeyBinds());
        generateWord();
        
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
    
    //------------------------------------------------------------------------//
    // pick word from array and create a char array. New word, new game. Update 
    // the score back to 100;
    private void generateWord(){
        magicWord = wordList[randomPicker.nextInt(5)];
        scoreLabel.setText(score+"");
        displayWord();
    }
    
    // set the length of magicWordArray AFTER generatingWord. Use sb
    // to build the underscored word 
    private void displayWord(){
        magicWordArray = new char[magicWord.length()];
        solvedMagicWordArray = new char[magicWord.length()];
        String s= "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<magicWord.length(); i++ ){
            magicWordArray[i] = magicWord.charAt(i);
            solvedMagicWordArray[i] = '_';
            sb.append("_").append(" ");
             System.out.print(magicWordArray[i]);

        }
        wordLabel.setText(s+sb);
        System.out.println(s+sb);
    }
   
    // now the magicWordArray is populated by chars, we can check user input
    // and update the game.
    // For multiple characters, itterate the whole magic word
    // and keep track of each "hits" received. If there are no hits, update the image
    private void checkUserInput(){
        int hits = 0;
        for(int i = 0; i < magicWord.length(); i++){
            if(inputBuffer == magicWord.charAt(i)){
                hits++;
                updateWordLabel(i);
                completedWord();
            }
        }
        if(hits == 0){
            numTries++;
            updateScore();
            updateImage();     
            if(numTries == 6)
                results();
        }
    
    }   
    
    // if the solvedMagicWordArray has no more "_" then we're done
    private void completedWord(){
        if (!(new String(solvedMagicWordArray).contains("_"))) {
            results();
            solved = true;
        }
    }
    
    /*  Create a delay so we can at least see the hangman completed.
        Goes to gameOverTimer to open up the EndGame and pass scores for later
        prints.
    */
    private void results(){
        Timer timer = new Timer(250, gameOverTimer);
        timer.setRepeats(false);
        timer.start();
        System.out.println(score);
    }
    
    // Goes to ColorMe. V1 goes directly to EndGame.
    ActionListener gameOverTimer = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
           updateImage();
           //EndGame e = new EndGame(score,solved,skipped);
           EndGame e = new EndGame();
           e.setScore(score);
           System.out.println(e.getScore());
           
           ColorMe c = new ColorMe();
           c.setVisible(true);
           closeWindow();
           
                
        }            
    };
    // create a StringBuilder holder. Build it from the solved word array. 
    // concat to be the new label. 
    private void updateWordLabel(int index){
        StringBuilder s = new StringBuilder();
        solvedMagicWordArray[index] = magicWordArray[index];
        for (int i = 0; i < magicWord.length(); i ++){
            s.append(solvedMagicWordArray[i]).append(' ');
        }
        wordLabel.setText(s+"");
    }
    
    private void updateImage(){
        ImageIcon image = new ImageIcon(getClass().getResource("/img/"+veryGraphicImages[numTries]));
        hangmanImage.setIcon(image); 
    }
    
    private void updateScore(){
        score = score -10;
        setScore(score);
        scoreLabel.setText(score+"");
    }
    
    public void closeWindow(){
        WindowEvent windowClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
    }
    
    private void updateTime(){
        Date temp = Calendar.getInstance().getTime();
        currentTime.setText(temp.toString());
    }
    
    
    public void actionPerformed(ActionEvent e){
        // i have 26 buttons to worry about and I should have used a Jbutton array 
    }
    /**
     * Creates new form Hangman
     */
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int s){
        score = s;
    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        dateAndTime = new javax.swing.JPanel();
        buttonSkip = new javax.swing.JButton();
        hangmanImage = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        currentTime = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonB = new javax.swing.JButton();
        buttonH = new javax.swing.JButton();
        buttonU = new javax.swing.JButton();
        buttonI = new javax.swing.JButton();
        buttonM = new javax.swing.JButton();
        buttonK = new javax.swing.JButton();
        buttonQ = new javax.swing.JButton();
        buttonE = new javax.swing.JButton();
        buttonX = new javax.swing.JButton();
        buttonZ = new javax.swing.JButton();
        buttonW = new javax.swing.JButton();
        buttonO = new javax.swing.JButton();
        buttonC = new javax.swing.JButton();
        buttonV = new javax.swing.JButton();
        buttonD = new javax.swing.JButton();
        buttonF = new javax.swing.JButton();
        buttonA = new javax.swing.JButton();
        buttonP = new javax.swing.JButton();
        buttonN = new javax.swing.JButton();
        buttonS = new javax.swing.JButton();
        buttonJ = new javax.swing.JButton();
        buttonG = new javax.swing.JButton();
        buttonT = new javax.swing.JButton();
        buttonL = new javax.swing.JButton();
        buttonR = new javax.swing.JButton();
        buttonY = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        wordLabel = new javax.swing.JLabel();

        jButton4.setText("jButton1");
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton1");
        jButton5.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton1");
        jButton6.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));
        setResizable(false);

        buttonSkip.setText("Skip");
        buttonSkip.setToolTipText("Go the next game");
        buttonSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSkipActionPerformed(evt);
            }
        });

        hangmanImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/0.png"))); // NOI18N
        hangmanImage.setPreferredSize(new java.awt.Dimension(74, 150));

        scoreLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        scoreLabel.setText("score");

        currentTime.setText("jLabel1");

        javax.swing.GroupLayout dateAndTimeLayout = new javax.swing.GroupLayout(dateAndTime);
        dateAndTime.setLayout(dateAndTimeLayout);
        dateAndTimeLayout.setHorizontalGroup(
            dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dateAndTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hangmanImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSkip)
                    .addComponent(currentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        dateAndTimeLayout.setVerticalGroup(
            dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dateAndTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel)
                    .addComponent(currentTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSkip)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dateAndTimeLayout.createSequentialGroup()
                .addComponent(hangmanImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        buttonB.setText("B");
        buttonB.setToolTipText("B");
        buttonB.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBActionPerformed(evt);
            }
        });

        buttonH.setText("H");
        buttonH.setToolTipText("H");
        buttonH.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHActionPerformed(evt);
            }
        });

        buttonU.setText("U");
        buttonU.setToolTipText("U");
        buttonU.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUActionPerformed(evt);
            }
        });

        buttonI.setText("I");
        buttonI.setToolTipText("I");
        buttonI.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIActionPerformed(evt);
            }
        });

        buttonM.setText("M");
        buttonM.setToolTipText("M");
        buttonM.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMActionPerformed(evt);
            }
        });

        buttonK.setText("K");
        buttonK.setToolTipText("K");
        buttonK.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKActionPerformed(evt);
            }
        });

        buttonQ.setText("Q");
        buttonQ.setToolTipText("Q");
        buttonQ.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQActionPerformed(evt);
            }
        });

        buttonE.setText("E");
        buttonE.setToolTipText("E");
        buttonE.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEActionPerformed(evt);
            }
        });

        buttonX.setText("X");
        buttonX.setToolTipText("X");
        buttonX.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXActionPerformed(evt);
            }
        });

        buttonZ.setText("Z");
        buttonZ.setToolTipText("dont bother");
        buttonZ.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonZActionPerformed(evt);
            }
        });

        buttonW.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        buttonW.setText("W");
        buttonW.setToolTipText("W");
        buttonW.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWActionPerformed(evt);
            }
        });

        buttonO.setText("O");
        buttonO.setToolTipText("O");
        buttonO.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOActionPerformed(evt);
            }
        });

        buttonC.setText("C");
        buttonC.setToolTipText("C");
        buttonC.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCActionPerformed(evt);
            }
        });

        buttonV.setText("V");
        buttonV.setToolTipText("V");
        buttonV.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVActionPerformed(evt);
            }
        });

        buttonD.setText("D");
        buttonD.setToolTipText("D");
        buttonD.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDActionPerformed(evt);
            }
        });

        buttonF.setText("F");
        buttonF.setToolTipText("F");
        buttonF.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFActionPerformed(evt);
            }
        });

        buttonA.setText("A");
        buttonA.setToolTipText("A");
        buttonA.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAActionPerformed(evt);
            }
        });

        buttonP.setText("P");
        buttonP.setToolTipText("P");
        buttonP.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPActionPerformed(evt);
            }
        });

        buttonN.setText("N");
        buttonN.setToolTipText("N");
        buttonN.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNActionPerformed(evt);
            }
        });

        buttonS.setText("S");
        buttonS.setToolTipText("S");
        buttonS.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSActionPerformed(evt);
            }
        });

        buttonJ.setText("J");
        buttonJ.setToolTipText("J");
        buttonJ.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJActionPerformed(evt);
            }
        });

        buttonG.setText("G");
        buttonG.setToolTipText("G");
        buttonG.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGActionPerformed(evt);
            }
        });

        buttonT.setText("T");
        buttonT.setToolTipText("T");
        buttonT.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTActionPerformed(evt);
            }
        });

        buttonL.setText("L");
        buttonL.setToolTipText("L");
        buttonL.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLActionPerformed(evt);
            }
        });

        buttonR.setText("R");
        buttonR.setToolTipText("R");
        buttonR.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRActionPerformed(evt);
            }
        });

        buttonY.setText("Y");
        buttonY.setToolTipText("Y");
        buttonY.setPreferredSize(new java.awt.Dimension(40, 40));
        buttonY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buttonU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buttonK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(buttonA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setMaximumSize(new java.awt.Dimension(600, 72));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        wordLabel.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        wordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wordLabel.setText("wordlabel");
        jPanel1.add(wordLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateAndTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(dateAndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBActionPerformed
        
        inputBuffer = 'b';
        checkUserInput();
        buttonB.setEnabled(false);
    }//GEN-LAST:event_buttonBActionPerformed

    private void buttonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCActionPerformed
        
        inputBuffer = 'c';
        checkUserInput();
        buttonC.setEnabled(false);
    }//GEN-LAST:event_buttonCActionPerformed

    private void buttonAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAActionPerformed
        
        inputBuffer = 'a';
        checkUserInput();
        buttonA.setEnabled(false);
    }//GEN-LAST:event_buttonAActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buttonFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFActionPerformed
        inputBuffer = 'f';
        checkUserInput();
        buttonF.setEnabled(false);
    }//GEN-LAST:event_buttonFActionPerformed

    private void buttonEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEActionPerformed
        inputBuffer = 'e';
        checkUserInput();
        buttonE.setEnabled(false);
    }//GEN-LAST:event_buttonEActionPerformed

    private void buttonDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDActionPerformed
        inputBuffer = 'd';
        checkUserInput();
        buttonD.setEnabled(false);
    }//GEN-LAST:event_buttonDActionPerformed

    private void buttonJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJActionPerformed
        inputBuffer = 'j';
        checkUserInput();
        buttonJ.setEnabled(false);
    }//GEN-LAST:event_buttonJActionPerformed

    private void buttonHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHActionPerformed
        inputBuffer = 'h';
        checkUserInput();
        buttonH.setEnabled(false);
    }//GEN-LAST:event_buttonHActionPerformed

    private void buttonGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGActionPerformed
        inputBuffer = 'g';
        checkUserInput();
        buttonG.setEnabled(false);
    }//GEN-LAST:event_buttonGActionPerformed

    private void buttonIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIActionPerformed
        inputBuffer = 'i';
        checkUserInput();
        buttonI.setEnabled(false);
    }//GEN-LAST:event_buttonIActionPerformed
    
    // now im just being lazy
    private void buttonTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTActionPerformed
        inputBuffer = 't';checkUserInput();
        buttonT.setEnabled(false);
    }//GEN-LAST:event_buttonTActionPerformed

    private void buttonSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSActionPerformed
        inputBuffer = 's';checkUserInput();
        buttonS.setEnabled(false);
    }//GEN-LAST:event_buttonSActionPerformed

    private void buttonRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRActionPerformed
        inputBuffer = 'r';checkUserInput();
        buttonR.setEnabled(false);
    }//GEN-LAST:event_buttonRActionPerformed

    private void buttonQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQActionPerformed
        inputBuffer = 'q';checkUserInput();
        buttonQ.setEnabled(false);
    }//GEN-LAST:event_buttonQActionPerformed

    private void buttonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPActionPerformed
        inputBuffer = 'p';checkUserInput();
        buttonP.setEnabled(false);
    }//GEN-LAST:event_buttonPActionPerformed

    private void buttonOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOActionPerformed
        inputBuffer = 'o';checkUserInput();
        buttonO.setEnabled(false);
    }//GEN-LAST:event_buttonOActionPerformed

    private void buttonNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNActionPerformed
        inputBuffer = 'n';checkUserInput();
        buttonN.setEnabled(false);
    }//GEN-LAST:event_buttonNActionPerformed

    private void buttonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMActionPerformed
        inputBuffer = 'm';checkUserInput();
        buttonM.setEnabled(false);
    }//GEN-LAST:event_buttonMActionPerformed

    private void buttonLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLActionPerformed
        inputBuffer = 'l';checkUserInput();
        buttonL.setEnabled(false);
    }//GEN-LAST:event_buttonLActionPerformed

    private void buttonKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKActionPerformed
        inputBuffer = 'm';checkUserInput();
        buttonK.setEnabled(false);
    }//GEN-LAST:event_buttonKActionPerformed

    private void buttonUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUActionPerformed
        inputBuffer = 'u';checkUserInput();
        buttonU.setEnabled(false);
    }//GEN-LAST:event_buttonUActionPerformed

    private void buttonVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVActionPerformed
        inputBuffer = 'v';checkUserInput();
        buttonV.setEnabled(false);
    }//GEN-LAST:event_buttonVActionPerformed

    private void buttonWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWActionPerformed
        inputBuffer = 'w';checkUserInput();
        buttonW.setEnabled(false);
    }//GEN-LAST:event_buttonWActionPerformed

    private void buttonXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXActionPerformed
        inputBuffer = 'x';checkUserInput();
        buttonX.setEnabled(false);
    }//GEN-LAST:event_buttonXActionPerformed

    private void buttonYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonYActionPerformed
        inputBuffer = 'y';checkUserInput();
        buttonY.setEnabled(false);
    }//GEN-LAST:event_buttonYActionPerformed

    private void buttonZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonZActionPerformed
        inputBuffer = 'z';checkUserInput();
        buttonZ.setEnabled(false);
    }//GEN-LAST:event_buttonZActionPerformed

    private void buttonSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSkipActionPerformed
       skipped = true;
       //EndGame e = new EndGame(score,solved,skipped);
       EndGame e = new EndGame();
       ColorMe c = new ColorMe();
       e.setScore(0);
       c.setVisible(true);
       closeWindow();
        
    }//GEN-LAST:event_buttonSkipActionPerformed

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
            java.util.logging.Logger.getLogger(Hangman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hangman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hangman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hangman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hangman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonA;
    private javax.swing.JButton buttonB;
    private javax.swing.JButton buttonC;
    private javax.swing.JButton buttonD;
    private javax.swing.JButton buttonE;
    private javax.swing.JButton buttonF;
    private javax.swing.JButton buttonG;
    private javax.swing.JButton buttonH;
    private javax.swing.JButton buttonI;
    private javax.swing.JButton buttonJ;
    private javax.swing.JButton buttonK;
    private javax.swing.JButton buttonL;
    private javax.swing.JButton buttonM;
    private javax.swing.JButton buttonN;
    private javax.swing.JButton buttonO;
    private javax.swing.JButton buttonP;
    private javax.swing.JButton buttonQ;
    private javax.swing.JButton buttonR;
    private javax.swing.JButton buttonS;
    private javax.swing.JButton buttonSkip;
    private javax.swing.JButton buttonT;
    private javax.swing.JButton buttonU;
    private javax.swing.JButton buttonV;
    private javax.swing.JButton buttonW;
    private javax.swing.JButton buttonX;
    private javax.swing.JButton buttonY;
    private javax.swing.JButton buttonZ;
    private javax.swing.JLabel currentTime;
    private javax.swing.JPanel dateAndTime;
    private javax.swing.JLabel hangmanImage;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel wordLabel;
    // End of variables declaration//GEN-END:variables
}
