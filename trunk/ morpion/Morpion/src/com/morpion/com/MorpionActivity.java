package com.morpion.com;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MorpionActivity extends Activity implements OnClickListener 
{
	

		int hauteurScreen ;
		int intNbRows = 3;
		int largeurScreen;
		int intNbColumns = 3;
		Button[][] plateau = new Button [3][3];
		int tour =0,croix=0;
		
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            //init the button in terms of the size of the screen
            initSizeScreen();
            initButton();

        }

        
    	//listener for the click
    	public void onClick(View arg0)
    	{
    		int i=0,j=0;
    		
    		
    		for( i = 0;i<=intNbRows-1;i++)
    		{
    			for( j = 0;j<=intNbColumns-1; j++)
    			{
    				if(arg0 == plateau[i][j]) 
	    				{
	    				play(i,j);
	    				}
    		}
    		}
    	}    
   
    /*----------------------------------------------------------------------------------*/
    	//INIT FONCTIONS
   /*----------------------------------------------------------------------------------*/
    	//to init the grid of button
    	private void initButton()
        {
    		  plateau[0][0]= (Button)findViewById(R.id.button1);
              plateau[0][1]= (Button)findViewById(R.id.button2);
              plateau[0][2]= (Button)findViewById(R.id.button3);
              plateau[1][0]= (Button)findViewById(R.id.button4);
              plateau[1][1]= (Button)findViewById(R.id.button5);
              plateau[1][2]= (Button)findViewById(R.id.button6);
              plateau[2][0]= (Button)findViewById(R.id.button7);
              plateau[2][1]= (Button)findViewById(R.id.button8);
              plateau[2][2]= (Button)findViewById(R.id.button9);
              
        	for(int i = 0;i<=intNbRows-1;i++)
    		{
    			for(int j = 0;j<=intNbColumns-1; j++)
    				{
    				 
    				 plateau[i][j].setMinimumHeight (hauteurScreen/intNbRows);
    				 plateau[i][j].setMinimumWidth (largeurScreen/intNbColumns);
    				 plateau[i][j].setPadding(0, 0, 0, 0);
    				 plateau[i][j].setOnClickListener(this);
    				 //plateau[i][j].setText(null);
    				}
    		}	
		}
    	
    	
    	
    	//to init the width and the height of the grid of buttons,proportional of the size of the phone's screen
        private void initSizeScreen()
        {
        	WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
     	    Display display= wm.getDefaultDisplay();
     	    hauteurScreen = display.getHeight() - (display.getHeight()/15);
     	    largeurScreen = display.getWidth();  
        	
        }
        
    /*----------------------------------------------------------------------------------*/
    	//RESET FUNCTIONS 
   /*----------------------------------------------------------------------------------*/    
        //to reset the value text of each button of the grid
    	private void resetButton()
    	{
    		for(int i = 0;i<=intNbRows-1;i++)
    		{
    			for(int j = 0;j<=intNbColumns-1; j++)
    				{
    				 plateau[i][j].setText(null);
    				}
    		}	
    		
    	}
        
    	
    /*----------------------------------------------------------------------------------*/
    	//CONTROL FONCTIONS
   /*----------------------------------------------------------------------------------*/	
        //when a player click on a button, to drop his pion
    	private void play(int ligne , int colonne)
    	{
    		//if the case is already used by a other player
    		String actuel = (String)plateau[ligne][colonne].getText();
			if((actuel.equals("X")) || (actuel.equals("O")))
				{
				msg("try_again");
				return;
				}
			//else to alternate the player
			tour = tour + 1;
		    croix  = tour % 2;
			
		    
		    if(croix==1)
		    	{
		        plateau[ligne][colonne].setText("X");
		 	   	}
		    else
		    	{
		    	plateau[ligne][colonne].setText("O");
		    	}
		    
		    //test if a player win or all the grid is occuped
		    if(testEndGame() || tour%9==0) 
		    	{
		    	//if it's right, a dialog ask if the players want play again or quit the activity
		    	    Builder builder = new AlertDialog.Builder(this);
		    	    builder.setTitle(R.string.end_game);
		    	         
		    	    builder.setMessage(R.string.again);
		    	    builder.setPositiveButton(R.string.accept,new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							resetButton();
						}
					} );
		    	    builder.setCancelable(true);
		    	    builder.setNegativeButton(R.string.Refuse, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
							closeActivity();
							
						}
					});
		    	    builder.show(); 
		    	
		    	}
    		
    	}
    	
    	//to test if a player win 
    	private boolean testEndGame()
    	{
    	//test each case to win the game, to improve
    		
    	//Row
    		//first row
    	if(((plateau[0][0].getText().equals("O"))||(plateau[0][0].getText().equals("X")))&&(plateau[0][0].getText().equals(plateau[0][1].getText()))&&(plateau[0][0].getText().equals(plateau[0][2].getText())))
	    	 return true;
    		//second row
    	if(((plateau[1][0].getText().equals("O"))||(plateau[1][0].getText().equals("X")))&&(plateau[1][0].getText().equals(plateau[1][1].getText()))&&(plateau[1][0].getText().equals(plateau[1][2].getText())))
	    	 return true;
    		//third row
    	if(((plateau[2][0].getText().equals("O"))||(plateau[2][0].getText().equals("X")))&&(plateau[2][0].getText().equals(plateau[2][1].getText()))&&(plateau[2][0].getText().equals(plateau[2][2].getText())))
	    	 return true;
    	
    	//Column
    		//first column
    	if(((plateau[0][0].getText().equals("O"))||(plateau[0][0].getText().equals("X")))&&(plateau[0][0].getText().equals(plateau[1][0].getText()))&&(plateau[0][0].getText().equals(plateau[2][0].getText())))
	    	 return true;
    		//second column
    	if(((plateau[0][1].getText().equals("O"))||(plateau[0][1].getText().equals("X")))&&(plateau[0][1].getText().equals(plateau[1][1].getText()))&&(plateau[0][1].getText().equals(plateau[2][1].getText())))
	    	 return true;
    		//third column
    	if(((plateau[0][2].getText().equals("O"))||(plateau[0][2].getText().equals("X")))&&(plateau[0][2].getText().equals(plateau[1][2].getText()))&&(plateau[0][2].getText().equals(plateau[2][2].getText())))
	    	 return true;
		
		
	    //Diagonal
			//first diagonal
    	if(((plateau[0][0].getText().equals("O"))||(plateau[0][0].getText().equals("X")))&&(plateau[0][0].getText().equals(plateau[1][1].getText()))&&(plateau[0][0].getText().equals(plateau[2][2].getText())))
	    	 return true;
    		//second diagonal
    	if(((plateau[2][0].getText().equals("O"))||(plateau[2][0].getText().equals("X")))&&(plateau[2][0].getText().equals(plateau[1][1].getText()))&&(plateau[2][0].getText().equals(plateau[0][2].getText())))
	    	 return true;
    	
    	
    	return false;
    	}
    	
    	//to quit the activity
    	private void closeActivity()
    	{
    		
    		this.finish();
    	}
   
    /*----------------------------------------------------------------------------------*/
    	//SHOW FONCTIONS
   /*----------------------------------------------------------------------------------*/	
    	//to show a toast with a message
    	private void msg(String string)
    	{
    		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();	
    	}
  }
