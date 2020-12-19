import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private  List<Integer>PlayerPosition;
	private  List<Integer>ComputerPosition;
	private  List<List<Integer>>winConditions;
	private  char[][] board= {{' ', '|', ' ', '|', ' '},
						      {'-', '+', '-', '+', '-'},
					          {' ', '|', ' ', '|', ' '},
					          {'-', '+', '-', '+', '-'},
					          {' ', '|', ' ', '|', ' '}};
	
	public TicTacToe() {
		PlayerPosition =new ArrayList<>();
		ComputerPosition =new ArrayList<>();
		winConditions=winnerConditions();	
	}
	
	
	 public  void GameHandler() {
		  Scanner scan=new Scanner (System.in);    
	      while(true) {                                                                         //for continuous playing
				System.out.print("Enter the position on the board (0-9) your symbol is # : ");
			 ////User turn
				int position=scan.nextInt();
				String player="user";
				while(PlayerPosition.contains(position) || ComputerPosition.contains(position)) {  //to check not to over placed current position
					System.out.println("this place already taken... try other place");
					position=scan.nextInt();
				}
				UserSymbolPlacement( position,player);
				String result=Checkwinner( winConditions);
				if(result.length()>0) {
					printGameBoard();	
					System.out.println(result);
					break;
				}
			
			///computer turn
				player="computer";
				Random ran=new Random();
				position= ran.nextInt(9)+1;
				while(PlayerPosition.contains(position) || ComputerPosition.contains(position)) {  //to check not to over placed current position
					position= ran.nextInt(9)+1;
				}
				UserSymbolPlacement( position, player);
				
				printGameBoard();	
				
				 result=Checkwinner( winConditions);
				if(result.length()>0) {
					if(result!="Computer wins!.. Sorry:(")
					printGameBoard();	
					System.out.println(result);
					break;
			}
		  }
	    }

	  private String Checkwinner( List<List<Integer>>winConditions) {
			for(List<Integer>l: winConditions) {
				if(PlayerPosition.containsAll(l)) {
					return "Congratulations you won the game!!";
				}else if(ComputerPosition.containsAll(l)) {
					return "Computer wins!.. Sorry:(";
				}
			}
			 if(ComputerPosition.size()+PlayerPosition.size()==9) {
				return "Tie";
			}
			return "";
	   }

	  private List<List<Integer>> winnerConditions() {
		//all winning possibilities
		 List<Integer> topRow = Arrays.asList(1,2,3);
		 List<Integer> midRow = Arrays.asList(4,5,6);
		 List<Integer> bottomRow = Arrays.asList(7,8,9);
		 List<Integer> leftCol = Arrays.asList(1,4,7);
		 List<Integer> midCol = Arrays.asList(2,5,8);
		 List<Integer> lastCol = Arrays.asList(3,6,9);
		 List<Integer> diagonal_1 = Arrays.asList(1,5,9);
		 List<Integer> diagonal_2 = Arrays.asList(3,5,7);
		 
		 List<List<Integer>>winningList =new ArrayList<>();
		 winningList.add(topRow);
		 winningList.add(midRow);
		 winningList.add(bottomRow);
		 winningList.add(leftCol);
		 winningList.add(midCol);
		 winningList.add(lastCol);
		 winningList.add(diagonal_1);
		 winningList.add(diagonal_2);
		
		 return winningList;
	 }

	void printGameBoard() {
		for(char[] row: board ) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		 }
	  }
	
	 private void UserSymbolPlacement( int position, String player) {
		 char symbol;
		 if(player=="user") {
			 symbol='#';
			 PlayerPosition.add(position);
		 }else {
			 symbol='0';
			 ComputerPosition.add(position);
		 }
		switch(position) {
		case 1: board[0][0]=symbol;
				break;
		case 2: board[0][2]=symbol;
		        break;
		case 3: board[0][4]=symbol;
		        break;
		case 4: board[2][0]=symbol;
		        break;
		case 5: board[2][2]=symbol;
		        break;
		case 6: board[2][4]=symbol;
		        break;
		case 7: board[4][0]=symbol;
		        break;
		case 8: board[4][2]=symbol;
		        break;
		case 9: board[4][4]=symbol;
		        break;
		default: break;
		}
	}
    
}
