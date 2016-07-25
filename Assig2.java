package quiz;

import java.io.*; // needed for IOException

import java.util.Scanner;
/**
 * University of Pittsburgh
 * CS401 Intermediate Java
 * Fall 2014
 * @author William O'Toole
 */
public class Assig2
{
    private final String fName = "questions.txt";    
    File file = new File(fName);
    Scanner scan = new Scanner(System.in);
    
	public static void main(String[] args) throws IOException
	{   
            
            Scanner scan = new Scanner(System.in);
            
            System.out.println("Welcome to the Quiz!"+ "\nPlease Enter your name:");
            
            String newPlayer = scan.next();
           
            File qFile = new File("quizplayers.txt");
            Scanner theFile = new Scanner(qFile);
            
            while(theFile.hasNextLine()){
                
                String input = theFile.nextLine();
                
                if(newPlayer.equalsIgnoreCase(input)){
                    
                  int numCor = Integer.parseInt(theFile.nextLine()); 
                  int numWrg = Integer.parseInt(theFile.nextLine());
                  
                    Player player = new Player(input, numCor, numWrg, true);
                    System.out.println("\nYour Results:");
                    System.out.println("Player: "+input);
                    System.out.println("Correct: "+numCor);
                    System.out.println("Wrong: "+numWrg);
                    System.out.println("Percent " + 100 * numCor/(numCor+numWrg) + "%\n");
					
					player.count();
                    return;
                }
                
            }
                    
            Player player = new Player(newPlayer);
                                        
            Quiz Q;
            Q = new Quiz("quizquestions.txt");
           
            takeQuiz(Q,player);

            addToEnd(player);
			player.count();
			
	}
	
	public static void takeQuiz(Quiz theQuiz,Player player)
	{
		Scanner scan = new Scanner(System.in);  
        int numCorrect = 0;
        int numWrong =0;

		Question quest;
		
		int status;		

		if (theQuiz.hasAQuestion())
			System.out.println("Questions are available");
		
			status = theQuiz.getStatus();
		if (status == 0)
			System.out.println("Quiz is ok");

		int num = 1;
		while (theQuiz.hasAQuestion())
		{
			quest = theQuiz.getQuestion();
                        String Q = quest.getQ();
                        String A = quest.getA();
                        int numTries = 0;
			if (quest != null)
                            
			{
				System.out.println("Question " + num + ": " + quest.getQ());
                                
                                while (numTries<2){
                                    String pAns = scan.next();
                                    
                                    if(pAns.equalsIgnoreCase(A)){
                                        System.out.println("Correct!");
                                        numCorrect++;
                                        break;
                                    }

                                    if(!pAns.equalsIgnoreCase(A)){
                                        System.out.println("Wrong! Please Try Again.");    
                                        if(numTries == 1)
                                            numWrong++;                   
                                    }

                                    numTries++;
                                }
                                
                                
				System.out.println("Answer " + num + ": " + quest.getA());
				System.out.println();
				num++;
			}
		}
                
                player.getC(numCorrect);
                player.getW(numWrong);
				
				System.out.println("Your Results:");
				
				System.out.println(player);
				
                System.out.println("Percent " + 100 * numCorrect/(numCorrect+numWrong) + "%");
		status = theQuiz.getStatus();
		if (status == 1)
			System.out.println("");
		else if (status == 2)
			System.out.println("Error in question file");
	}
public static void addToEnd(Player player)throws IOException{
    
    PrintWriter fileOut = new PrintWriter(new FileOutputStream("quizplayers.txt",true));
    
    fileOut.println(player + "\n");
	
    fileOut.close();
	
}
        
}