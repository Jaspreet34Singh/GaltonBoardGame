import sun.util.calendar.CalendarSystem;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String playAgain;
       do {
           final int NUM_SLOTS = 10;
           Date date = new Date();
           System.out.println("Date is " + args + " and the system time is " + System.currentTimeMillis());
           System.out.println("Welcome to Galton Board");
           System.out.println("You will drop balls in the Galton Board and they will");
           System.out.println("hit a series of nails and fall into one of "+ NUM_SLOTS +" slots.");
           System.out.println("After all the balls are dropped, the resulting accumulation");
           System.out.println("of balls in each slot will be displayed for you to see!");

           Scanner scan = new Scanner(System.in);
           System.out.print("Enter the number of balls to drop: ");
           int numberOfBalls = scan.nextInt();

           printGameResults(printBallsPathAndReturnStats(numberOfBalls, NUM_SLOTS));
           System.out.print("Do you want to play the game again (y/n)? ");
           playAgain = scan.next();
       }while (Objects.equals(playAgain, "y") || Objects.equals(playAgain, "Y"));

    }

    public static int[] printBallsPathAndReturnStats(int numberOfBalls, int NUM_SLOTS){

        double initialPosition;
        // Assigning the initial position of the ball
        initialPosition = (NUM_SLOTS / 2.0) + 0.5;
        System.out.println(initialPosition);

        // Creating a 2-dimensional array with one int [Slot_Number] [Number_of_balls]
        int[] positionOfBalls = new int[numberOfBalls];

        for (int i = 0 ; i < numberOfBalls ; i++){
            String path = "";
            double position = initialPosition;
            for (int j = 0 ; j < NUM_SLOTS - 1 ; j++){
                if(Randm.generateRandomNum() % 2 != 0){
                    position -= 0.5;
                    path = path + "L";
                }
                else{
                    position += 0.5;
                    path = path + "R";
                }
            }
            positionOfBalls[i] = (int)(position);

            System.out.printf("Ball %-15s", (i+1)+":");
            for (int j=0; j < NUM_SLOTS - 1 ; j++){
                System.out.printf("%3s", path.charAt(j));
            }
            System.out.print(" Slot is "+ (int)(position) + "\n");
        }
        return stats(positionOfBalls, NUM_SLOTS);
    }

    public static int[] stats(int[] positionOfBalls, int NUM_SLOTS){
        int length = positionOfBalls.length;
        int[] slots = new int[NUM_SLOTS];

        for (int i =0 ; i< length ; i++){
            int positionOfBall = positionOfBalls[i];
            for (int j = 0 ; j < NUM_SLOTS ; j++){
                if((positionOfBall == j+1) && (positionOfBall <= NUM_SLOTS ) ){
                    slots[j] += 1;
                }
            }
        }
        for (int i =0 ; i<NUM_SLOTS; i++){
            System.out.printf("Slot number %d %3d \n",(i+1),slots[i]);
        }
        return slots;
    }

    public static void printGameResults(int[] slots){

        System.out.println("Game Results: ");

        // finding the maximum number of balls in slot
        int max = slots[0];
        for (int i =0 ; i < slots.length ; i++){
            if(slots[i] > max){
                max = slots[i];
            }
        }
//        for (int i = 0 ; i < slots.length - 1 ; i++) {
//            for (int j = 0; j < slots.length; j++) {
//
//            }
//        }
        for (int i = max ; i> 0 ; i--){
            for (int j = 0 ; j< slots.length ; j++){
                if(j<9){
                    if(i <= slots[j]){
                        System.out.print("|  O  ");
                    }
                    else
                        System.out.print("|     ");
                }
                else {
                    if(i <= slots[j]){
                        System.out.print("|  O   ");
                    }
                    else
                        System.out.print("|      ");
                }
            }
            System.out.print("| \n");
        }
        for (int j = 0 ; j< slots.length ; j++){
                System.out.printf("   %d  ", (j+1));

        }
        System.out.print("\n");
    }


}