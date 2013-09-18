//package rpgInventory.handelers.proxy;
//
//import java.util.Scanner;
//
//import org.lwjgl.input.Keyboard;
//
//public class main {
//
//	static Scanner in = new Scanner(System.in);
//	static double exp, level, click;{
//
//
//		exp = 0;
//		level = 1;
//
//	}
//	static String restart;{
//		restart = "R";
//	}
//	public static void main(String[] args){
//
//		System.out.println("Welcome to Inputter! You must input the number 1 to get points!");
//		loop();
//	}
//	private static void loopCount(){
//		
//		if(in.has()){
//			click = in.nextDouble();
//			if (click == 1){
//				System.out.println("+1!");
//				exp = exp+1;
//				System.out.println("You now have "+exp+" experience points at level "+level);
//				if (exp == (10+level*(3+level))){
//					level = level+1;
//					exp = 0;
//					System.out.println("You have leveled up!");
//				}
//				loop();
//			}
//		}else{
//			System.out.println("You loose! Type 'R' to restart.");
//			restart = in.next();
//			if (restart.equals("R")){
//				System.out.println("Restarting...");
//				exp = 0;
//				level = 0;
//				System.out.println("The game has restarted.");
//				loop();
//			}else{
//				System.out.println("Game Over");
//			}
//		}
//	}
//	
//	private static void loopFail(){
//		
//	}
//}
