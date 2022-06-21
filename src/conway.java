import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class conway {

        public int w;
        public int h;

        public void drawWorld(int w, int h) throws InterruptedException {

            //we make an 2D array and fill the first input's data

            //we make an array with 2 lines more, because one line of each side are border
            int[][] game = new int[w+2][h+2];

            game[0+1][1+1] = 1 ;
            game[1+1][2+1] = 1 ;
            game[2+1][0+1] = 1 ;
            game[2+1][1+1] = 1 ;
            game[2+1][2+1] = 1 ;

            //we show the first scene of game with our first data
            print(game);
        }

        public void giveData(){
            //we give firstly width and height and check the inputs.
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Please Enter the size of Width: ");
                w = scanner.nextInt();

                System.out.println("Please Enter the size of Height: ");
                h = scanner.nextInt();

                //if inputs are less than 1, then we do something wrong to make a new error!
                if(w < 1 || h < 1){
                    w = 5/0;
                }else {
                    drawWorld(w, h);
                }
            }catch (Exception e){
                Scanner Ag = new Scanner(System.in);
                System.out.println("Please Enter a valid number! \n'y' for try insert A Number and other key to exit.");
                String again = Ag.nextLine();

                if (Objects.equals(again, "y")){
                    giveData();
                }else {
                    System.out.println("See you again! ");
                }
            }
        }

        public void print(int[][] game) throws InterruptedException {

            //we print just table without border, then we are starting from 1!
            for (int i = 1 ; i < game.length -1 ; i++) {
                for (int j = 1; j < game[i].length -1 ; j++) {
                    if (game[i][j] == 1) {
                        System.out.print(" x ");
                    } else {
                        System.out.print(" . ");
                    }
                }
                System.out.println();
            }
            System.out.println("__________________________________________________________________________________");

            //We have to write the static number of length, because it becomes next round more or less
            int w2 = game.length;
            int h2 = game[0].length;
            nextStep(game , w2 , h2);

        }

        public void nextStep(int[][] inp, int w, int h) throws InterruptedException {

            //Make a new array to carry new data into it
            int[][] last = new int[w][h];

            //We check data without border, because the borders are always 0, so they roll as frame!
            for (int i = 1 ; i < inp.length -1 ; i++){
                for (int j = 1 ; j < inp[i].length -1 ; j++){

                    //we have a var. and count the number of live neighbor
                    int population = 0;
                    for (int m = -1 ; m <= 1 ; m++){
                        for (int n = -1 ; n <= 1 ; n++){
                            population += inp[m+i][n+j];
                        }
                    }

                    //We have to don't count the first house
                    population -= inp[i][j];

                    //Here we can do our game rules
                    if (population > 3 || population < 2){
                        last[i][j] = 0;
                    }else if (population == 3){
                        last[i][j] = 1;
                    }else {
                        last[i][j] = inp[i][j];
                    }
                }
            }

            //Make delay to show better!
            TimeUnit.SECONDS.sleep(1);
            print(last);
        }
}
