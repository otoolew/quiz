package quiz;

import java.io.*;
import java.util.*;
/**
 * University of Pittsburgh
 * CS401 Intermediate Java
 * Fall 2014
 * @author William O'Toole
 */
public class Player
{
        private String N;  // Name 
	private int C;  // Correct
	private int W;  // Wrong
        private boolean Z;
	// Create a Player
	public Player(String name)
	{
		N = name;               
        }
        
        public Player(String name, int correct, int wrong, boolean Z){
                
           N = name;
           C = correct;
           W = wrong;
           Z = true;
            
        }
	// Return the question part of the Question
	public String getN()
	{    
		return N;
	}
	
	// Return the answer part of the Question
	public void getC(int cor)
	{       
		C = cor;
                Z = true;
	}
	
        public void getW(int wrg)
        {
                W = wrg;
        }
        
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append(N+"\n");
		S.append(C+"\n");
                S.append(W);
		return S.toString();
	}
	
	 public void count()throws IOException	
 {      
        File file = new File("quizplayers.txt");
        Scanner inputeFile =new Scanner(file);
        
          
            int cCount = 0;
            int wCount = 0;
            int pCount = 0;
            

            while(inputeFile.hasNext())
            {
                if(inputeFile.hasNextLine()){
                    inputeFile.nextLine();
                    pCount++;
                }
                if(inputeFile.hasNextInt()){
					int cnt= Integer.parseInt(inputeFile.nextLine());  
                 
                    cCount += cnt;
                }
                if(inputeFile.hasNextInt()){
				
                    int wnt= Integer.parseInt(inputeFile.nextLine());  
                 
                    wCount += wnt;             
                }
            }

           

            System.out.println("\nOverall Stats\nNumber of Players: " + pCount );
            System.out.println("Number of Correct: " + cCount);
            System.out.println("Number of Wrong: " + wCount);
			System.out.println("Avg of All Players: " + 100*  cCount / (cCount+wCount)+ "%");
			inputeFile.close();
    }
}
