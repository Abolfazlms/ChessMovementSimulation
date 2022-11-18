
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Player p1 = new Player("white");//Player white Constructor
        Player p2 = new Player("black");//Player black Constructor
        Player p3 = new Player("white");//Test for many player build exception

        boolean turn = true;//Determines which player's turn it is
        boolean flag = true;//Related to the first UX menu
        boolean flag2 = true;//Related to the second UX menu (game menu)

        System.out.println(p3.color);//Test for players build exception and show the exception massage
        System.out.println();
        System.out.println();


        System.out.println("welcome to the chess simulator !");
        System.out.println();
        /*start UX and the chess simulator */

        while(flag){


            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            int select;//The selected part is stored in this variable
            System.out.println("1 - Play chess");
            System.out.println("2 - Exit");
            System.out.println();
            System.out.println("Select a section : ");
            select=scan.nextInt();//This section specifies the selected section

            switch (select){
                case 1 :
                {
                    while (flag2) {
                        System.out.println();

                        /* White player's section */

                        if (turn) {
                            System.out.println("now it's the turn of the white player");
                            System.out.println();
                            System.out.println("White player pieces : ");

                            /* Display pieces names (number) and positions*/
                            for (int i = 0; i < 16; i++) {
                                System.out.println(i + "-" + Player.pw[i].name + " " + Player.pw[i].p.getRow() + " " + Player.pw[i].p.getCol());
                            }
                            System.out.println("Now enter the number and position of a chess piece : ");

                            int num = scan.nextInt();//Receives the pieces number
                            int num2 = scan.nextInt();//Receives the pieces row
                            int num3 = scan.nextInt();//Receives the pieces column

                            /* Exit to main menu code*/
                            if (num == 0 && num2 == 0 && num3 == 0) {
                                flag2 = false;
                            }
                            /*Move the white pieces*/
                            else if(p1.Move(Player.getWhitePlayer(num), new Position(num2, num3))) {
                                turn = false;
                            }
                            else{
                                System.out.println();
                                System.out.println("please move the piece correctly");
                            }


                        }

                        /* Black player's section */

                        else {
                            System.out.println("now it's the turn of the black player");
                            System.out.println();
                            System.out.println("Black player pieces : ");

                            /* Display pieces names (number) and positions */

                            for (int i = 0; i < 16; i++) {

                                System.out.println(i + "-" + Player.pb[i].name + " " + Player.pb[i].p.getRow() + " " + Player.pb[i].p.getCol());
                            }
                            System.out.println("Now enter the number and position of a chess piece : ");

                            int num = scan.nextInt();//Receives the pieces number
                            int num2 = scan.nextInt();//Receives the pieces row
                            int num3 = scan.nextInt();//Receives the pieces column

                            /* Exit to main menu code*/

                            if (num == 0 && num2 == 0 && num3 == 0) {
                                flag2 = false;
                            }
                            /*Move the black pieces*/
                            else if(p2.Move(Player.getBlackPlayer(num), new Position(num2, num3))) {
                                turn = true;
                            }
                            else {
                                System.out.println();
                                System.out.println("please move the piece correctly");
                            }


                        }
                    }
                    break;
                }
                //this part will exit the program
                case 2 :
                {
                    System.out.println();
                    System.out.println("thanks for test that game!");
                    System.out.println();
                    System.out.println("**************************************************");
                    flag=false;
                    break;
                }
            }
        }
    }
}
//class Mohreh
class Pieces {
    public String Color;//color of the pieces
    public Position p;//position of the pieces (row,column)
    protected String name;//name of the pieces
    public boolean Delete;//this section is a flag and indicates that the pieces has been removed

    /* constructor to build a pieces object */
    Pieces(String color,Position p){
        this.Color = color;//set color
        this.p = p;//set position
    }
    //this method checks whether the movement of the pieces is valid or not
    protected boolean isValidMove(Position p){
        return true;
    }
}
//class sarbaz
class Pawn extends Pieces{
    boolean flag;//this field checking the correctness of the movement
    boolean first_move;//this field checks if the pawn's pieces has made the first move or not.
    //Because the soldier can move 2 squares in first move

    /* pawn pieces constructor */
    Pawn(String color , Position p){
        super(color,p);//set color and position
        first_move=true;//set first_move means that the pawn's pieces can move 2 squares or not
        name="pawn";//set name
    }

    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position position){
        if(Color.equalsIgnoreCase("white")){
            if(position.getCol()==this.p.getCol()){
                if(first_move && (position.getRow()-this.p.getRow()==-1 ||position.getRow()-this.p.getRow()==-2)){
                    this.flag = true;
                    first_move=false;
                }
                else this.flag= position.getRow() - this.p.getRow() == -1;
            }
            else{
                this.flag=false;
            }
        }
        else if(Color.equalsIgnoreCase("black")){
            if(position.getCol()==this.p.getCol()){
                if(first_move && (position.getRow()-this.p.getRow()==1 ||position.getRow()-this.p.getRow()==2)){
                    this.flag = true;
                    first_move=false;
                }
                else this.flag= position.getRow() - this.p.getRow() == 1;
            }
            else{
                this.flag=false;
            }

        }

        return this.flag;
    }
}
//class rokh
class Rook extends Pieces{
    boolean flag;//this field checking the correctness of the movement

    /* rook pieces constructor */
    Rook(String color , Position p){
        super(color,p);//set color and position
        name ="rook";//set name
    }

    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position p) {
        if(p.getRow()==this.p.getRow()){
            this.flag= p.getCol() <= 8 && p.getCol() >= 1;
        }
        else if(p.getCol()==this.p.getCol()){
            this.flag= p.getRow() <= 8 && p.getRow() >= 1;
        }
        else{
            this.flag=false;
        }
        return this.flag;
    }

}
//class shah
class King extends Pieces{
    boolean flag;//this field checking the correctness of the movement
    /* king pieces constructor */
    King(String color , Position p){
        super(color,p);//set color and position
        name="king";//set name
    }
    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position p) {
        if(p.getRow()==this.p.getRow()+1||p.getRow()==this.p.getRow()-1||p.getRow()==this.p.getRow()){
            this.flag= p.getCol() == this.p.getCol() || p.getCol() == this.p.getCol() + 1 || p.getCol() == this.p.getCol() - 1;
        }
        else if(p.getCol()==this.p.getCol()+1||p.getCol()==this.p.getCol()-1||this.p.getCol()==this.p.getCol()){
            this.flag= p.getRow() == this.p.getRow() - 1 || p.getRow() == this.p.getRow() + 1 || p.getRow() == this.p.getRow();
        }
        else{
            this.flag=false;
        }
        return this.flag;
    }
}
//class asb
class Knight extends Pieces{
    boolean flag;//this field checking the correctness of the movement
    /* knight pieces constructor */
    Knight(String color , Position p)
    {
        super(color,p);//set color and position
        name = "knight";//set name
    }
    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position p){
        if(p.getRow()==this.p.getRow()+1||p.getRow()==this.p.getRow()-1){
            this.flag= p.getCol() == this.p.getCol() + 2 || p.getCol() == this.p.getCol() - 2;
        }
        else if(p.getRow()==this.p.getRow()+2||p.getRow()==this.p.getRow()-2){
            this.flag= p.getCol() == this.p.getCol() + 1 || p.getCol() == this.p.getCol() - 1;
        }
        else{
            this.flag=false;
        }
        return this.flag;
    }
}
//class fill
class Bishop extends Pieces{
    boolean flag;//this field checking the correctness of the movement
    /* bishop pieces constructor */
    Bishop(String color , Position p){
        super(color,p);//set color and position
        name="bishop";//set name
    }
    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position p){
        this.flag= this.p.getRow() - p.getRow() == p.getCol() - this.p.getCol() || p.getRow() - this.p.getRow() == p.getCol() - this.p.getCol() || this.p.getRow() - p.getRow() == this.p.getCol() - p.getCol() || p.getRow() - this.p.getRow() == this.p.getCol() - p.getCol();
        return flag;
    }
}
//class vazir
class Queen extends Pieces{
    boolean flag;//this field checking the correctness of the movement
    /* queen pieces constructor */
    Queen(String color , Position p){
        super(color,p);//set color and position
        name="queen";//set name
    }
    //this method checks whether the movement of the pieces is valid or not
    @Override
    protected boolean isValidMove(Position p){
        if(this.p.getRow()-p.getRow()==p.getCol()-this.p.getCol()||p.getRow()-this.p.getRow()==p.getCol()-this.p.getCol()||this.p.getRow()-p.getRow()==this.p.getCol()-p.getCol()||p.getRow()-this.p.getRow()==this.p.getCol()-p.getCol()){
            this.flag=true;
        }
        else if(p.getRow()==this.p.getRow()){
            this.flag= p.getCol() <= 8 && p.getCol() >= 1;
        }
        else if(p.getCol()==this.p.getCol()){
            this.flag= p.getRow() <= 8 && p.getRow() >= 1;
        }
        else{
            this.flag=false;
        }
        return flag;
    }


}

class Position{
    public static final int Row_Count = 8;//number of allowed rows of the chess board
    public static final int Col_Count = 8;//number of allowed column of the chess board
    public boolean flag_p=false;//this field indicates that the pieces has been removed
    private int row;//input value for the chess pieces row
    private int col;//input value for the chess pieces column

    /* position constructor */
    Position(int Row,int Col) {
        setRow(Row);
        setCol(Col);
    }
    // this method specifies the pieces row
    public void setRow(int row)  {
        try{
            if(row>Row_Count&&(flag_p=false)||row<1&&(flag_p=false)){
                throw new InvalidRowException("The value entered for the row is invalid .");
            }
            else{
                this.row=row;
            }
        }
        catch(InvalidRowException e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    // this method specifies the pieces column
    public void setCol(int col){
        try {
            if (col > Col_Count &&(flag_p=false)|| col < 1&&(flag_p=false)) {
                throw new InvalidColException("The value entered for the column is invalid .");
            } else {
                this.col = col;
            }
        }
        catch (InvalidColException e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
}

/* this section indicates exceptions */
class InvalidRowException extends Exception{
    public InvalidRowException(String massage){
        super(massage);
    }
}
class InvalidColException extends Exception{
    public InvalidColException(String massage){
        super(massage);
    }
}
class SamePositionException extends Exception{
    public SamePositionException(String massage){
        super(massage);
    }
}
class InvalidMoveException extends Exception{
    public InvalidMoveException(String massage){
        super(massage);
    }
}
class InvalidPiecesException extends Exception{
    public InvalidPiecesException(String massage){
        super(massage);
    }
}
class ManyPlayersBuildException extends Exception{
    public ManyPlayersBuildException(String massage){
        super(massage);
    }
}
class PawnFrontPiecesException extends Exception{
    public PawnFrontPiecesException(String massage){
        super(massage);
    }
}

class Player{
    public String color;//color of the player pieces
    private static final int limit = 2;//this final variable is the determinant that two players object must be made no more
    public static int counter = 0;//counters for the number of players made
    public static Pieces[] pw = new Pieces[16];//pieces of white player
    public static Pieces[] pb = new Pieces[16];//pieces of black player
    public boolean pb_checked;//checked the white king pieces
    public boolean pw_checked;//checked the black king pieces
    /* player constructor */
    public Player(String color) {
        /* this section make and store the white pieces */
        if(color.equalsIgnoreCase("white")&&counter<limit){
            this.color="white";
            pw[0]=new Pawn("white",new Position(7,1));
            pw[1]=new Pawn("white",new Position(7,2));
            pw[2]=new Pawn("white",new Position(7,3));
            pw[3]=new Pawn("white",new Position(7,4));
            pw[4]=new Pawn("white",new Position(7,5));
            pw[5]=new Pawn("white",new Position(7,6));
            pw[6]=new Pawn("white",new Position(7,7));
            pw[7]=new Pawn("white",new Position(7,8));
            pw[8]=new Rook("white",new Position(8,1));
            pw[9]=new Rook("white",new Position(8,8));
            pw[10]=new Knight("white",new Position(8,2));
            pw[11]=new Knight("white",new Position(8,7));
            pw[12]=new Bishop("white",new Position(8,3));
            pw[13]=new Bishop("white",new Position(8,6));
            pw[14]=new Queen("white",new Position(8,5));
            pw[15]=new King("white",new Position(8,4));

        }
        /* this section make and store the black pieces */
        else if(color.equalsIgnoreCase("black")&&counter<limit){
            this.color="black";
            pb[0]=new Pawn("black",new Position(2,1));
            pb[1]=new Pawn("black",new Position(2,2));
            pb[2]=new Pawn("black",new Position(2,3));
            pb[3]=new Pawn("black",new Position(2,4));
            pb[4]=new Pawn("black",new Position(2,5));
            pb[5]=new Pawn("black",new Position(2,6));
            pb[6]=new Pawn("black",new Position(2,7));
            pb[7]=new Pawn("black",new Position(2,8));
            pb[8]=new Rook("black",new Position(1,1));
            pb[9]=new Rook("black",new Position(1,8));
            pb[10]=new Knight("black",new Position(1,2));
            pb[11]=new Knight("black",new Position(1,7));
            pb[12]=new Bishop("black",new Position(1,3));
            pb[13]=new Bishop("black",new Position(1,6));
            pb[14]=new Queen("black",new Position(1,5));
            pb[15]=new King("black",new Position(1,4));

        }
        /* this section indicates that the player has been made is unauthorized */
        else if(counter>=limit) {
            try {
                throw new ManyPlayersBuildException("you can't create more players");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        counter ++;
    }
    //this part returns a white pieces
    public static Pieces getWhitePlayer(int i){
        return pw[i];
    }
    //this part returns a black pieces
    public static Pieces getBlackPlayer(int i){
        return pb[i];
    }

    //this method is used to move and determine the new position of the chess pieces
    public boolean Move(Pieces ps,Position position){
        try {
            boolean flag =ps.isValidMove(position);
            int test_color=0;//1 for white and 2 for black
            String colors = ps.Color;
            if(colors.equalsIgnoreCase("white")){
                test_color=1;
            }
            else if(colors.equalsIgnoreCase("black")) {
                test_color = 2;
            }
            if(!color.equals(ps.Color)){
                throw new InvalidPiecesException("Invalid pieces selected .");
            }
            if(ps.name.equalsIgnoreCase("pawn")&& !flag){
                if(ps.Color.compareTo("white")==0){
                    for (int i=0;i<16;i++){
                        {
                            if(ps.p.getRow()-pb[i].p.getRow()==1 && (ps.p.getCol()-pb[i].p.getCol() ==-1||ps.p.getCol()-pb[i].p.getCol()==1)){

                                flag = true;
                                break;
                            }
                        }
                    }
                }
                else if (ps.Color.compareTo("black")==0){
                    for (int i=0;i<16;i++){
                        {
                            if(ps.p.getRow()-pw[i].p.getRow()==-1 &&(ps.p.getCol()- pw[i].p.getCol()==-1||ps.p.getCol()-pw[i].p.getCol()==1)){
                                flag=true;
                                break;

                            }

                        }
                    }
                }

            }
            else if(ps.name.equalsIgnoreCase("pawn")&&flag){
                for (int i=0;i<16;i++){
                    if(ps.Color.compareTo("white")==0){
                        if(ps.p.getCol()==pb[i].p.getCol()&&ps.p.getRow()-pb[i].p.getRow()==1){
                            throw new PawnFrontPiecesException("This position is occupied by a Piece but pawn can't do this move .");

                        }
                    }
                    else if(ps.Color.compareTo("black")==0){
                        if(ps.p.getCol()==pw[i].p.getCol()&&ps.p.getRow()-pw[i].p.getRow()==-1){
                            throw new PawnFrontPiecesException("This position is occupied by a Piece but pawn can't do this move .");
                        }
                    }
                }
            }

            if(flag){

                try{

                    if(test_color==1){
                        for(int i = 0;i<16;i++){
                            if(pw[i].p.getRow()==position.getRow()&&pw[i].p.getCol()==position.getCol()){
                                throw new SamePositionException("This position is occupied by a Piece of the same color .");
                            }
                        }
                        for(int i = 0;i<16;i++){
                            if((pb[i].p.getRow()==position.getRow()&&pb[i].p.getCol()==position.getCol()) && (pb[i].name.compareTo("king")!=0)){

                                System.out.println("You removed the opponent's "+pb[i].name+" pieces");
                                pb[i].p.setRow(0);
                                pb[i].p.setCol(0);
                                pb[i].Delete=true;
                                ps.p=position;
                                pb[i].p.flag_p=true;
                                break;
                            }
                            else if((pb[i].p.getRow()==position.getRow()&&pb[i].p.getCol()==position.getCol()) && (pb[i].name.compareTo("king")==0)){
                                System.out.println("You are checked the opponent's king Piece");
                                pb_checked=true;

                            }

                        }
                        System.out.println("the move was successful");
                        ps.p=position;
                        return true;
                    }
                    else if(test_color==2){
                        for(int i = 0;i<16;i++){
                            if(pb[i].p.getRow()==position.getRow()&&pb[i].p.getCol()==position.getCol()){
                                throw new SamePositionException("This position is occupied by a bead of the same color .");
                            }
                        }
                        for(int i = 0;i<16;i++){
                            if((pw[i].p.getRow()==position.getRow()&&pw[i].p.getCol()==position.getCol()) && (pw[i].name.compareTo("king")!=0)){

                                System.out.println("You removed the opponent's "+pw[i].name+" pieces");
                                pw[i].p.setRow(0);
                                pw[i].p.setCol(0);
                                pw[i].Delete=true;
                                pw[i].p.flag_p=true;
                                ps.p=position;
                                break;
                            }
                            else if((pw[i].p.getRow()==position.getRow()&&pw[i].p.getCol()==position.getCol()) && (pw[i].name.compareTo("king")==0)){
                                System.out.println("You are checked the opponent's king Piece");
                                pw_checked=true;
                            }
                        }
                        System.out.println("the move was successful");
                        ps.p=position;
                        return true;

                    }


                }
                catch (SamePositionException e) {
                    e.printStackTrace();
                }
            }
            else{

                throw new InvalidMoveException("Invalid Move .");
            }

        }
        catch (InvalidMoveException | InvalidPiecesException | PawnFrontPiecesException e){
            e.printStackTrace();
        }
        return false;
    }


}
