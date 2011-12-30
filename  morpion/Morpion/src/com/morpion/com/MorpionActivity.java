package com.morpion.com;



import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MorpionActivity extends Activity implements OnClickListener 
{
	
	    TextView affichage;
		int hauteurScreen ;
		int intNbRows = 3;
		int largeurScreen;
		int intNbColumns = 3;
		Button[][] plateau = new Button [3][3];
		int[][] game = new int [3][3];
  
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            initSizeScreen();
            initGame();
            
           
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
    				}
    		}
            
            
            //affichage avec le textview
            affichage = (TextView)findViewById(R.id.debug);
           affichage.setText(String.valueOf(largeurScreen)+" "+" "+String.valueOf(hauteurScreen));        
          
          
            
            
        }

        private void initGame()
        {
        	
        	for(int i = 0;i <= intNbRows-1;i++)
        		{
        		for(int j = 0;j <= intNbColumns-1; j++)
        			{
        			game[i][j] = 0;
        			}
        		}
        }
        
        private void initSizeScreen()
        {
        	 WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
     	    Display display= wm.getDefaultDisplay();
     	    hauteurScreen = display.getHeight()-(display.getHeight()/5);
     	    largeurScreen = display.getWidth();  
        	
        }
    	
    	public void onClick(View arg0)
    	{
    		
    		for(int i = 0;i<=intNbRows-1;i++)
    		{
    			for(int j = 0;j<=intNbColumns-1; j++)
    			{
    				if(arg0 == plateau[i][j]) affichage.setText(arg0.toString()+"\n"+
    															String.valueOf(i)+"\n"+
    															String.valueOf(j));
    			}
    		}
    		
    	}    
  }
