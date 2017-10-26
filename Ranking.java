/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.PointAndClickGame;


/**
 *
 * @author kslea_000
 */
public class Ranking implements Comparable<Ranking>{
    private String playerName;
    private int playerScore;
    private static Ranking[] playerList = new Ranking[100];
    private static int count = 0;

    public Ranking(int i, String s){
        playerName = s;
        playerScore = i;
        playerList[count] = this;
        count++;
    }
    
    /////////////////////////////// Metodz ////////////////////////////////////
    
    public String getPlayerName(){
        return playerName;
    }
    
    public int getPlayerScore(){
        return playerScore;
    }
    
    public String getErrthang(){
        return getPlayerName() + " ------- "+ getPlayerScore();
    }
    
    public int compareTo(Ranking list){
        // ?
	if (playerScore < list.playerScore)
            return -1;
	else if(playerScore == list.playerScore)
            return playerName.compareTo(list.playerName);
	return 1;
		
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
}
