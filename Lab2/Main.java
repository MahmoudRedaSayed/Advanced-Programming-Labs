
import java.lang.Double;
import java.util.Random;
class  Matrix<T extends Number> {
    //The member data of the Matrix class
    protected int Rows;
    protected int Cols;
    protected T [][] Elements;
    //----------------------------------//

    //-------------// Inner class MultiplicationException //--------//
    
    final static class MultiplicationException extends Exception{


        //Data Members only errorMessage
        public String errorMessage;
        //-------------------------//


        //The constructor take one parameter to set the Error message
        public MultiplicationException(String message){

            errorMessage=message;
        }
        //--------------------------------------------------//

        // Function message print the error message with no parameters
        public void message(){
            System.out.printf("%s \n",this.errorMessage);
        }
        //--------------------------------------------//

    }

    //---------------// End of MultiplicationException //------------------//


    //1-The constructor of the Matrix class
    /*The constructor takes two parameters one to the rows and columns 
    and construct the Two D array Elements*/
    public  Matrix(int RowsNum,int ColsNum){
        this.Rows=RowsNum;
        this.Cols=ColsNum;
        this.Elements = (T[][]) new Number[RowsNum][ColsNum];
    }
    //---------------------------------------//





/*The SetNumbers function take one deminational Array and convert it to the two Deminational Array
    First : Check if the Number of the elements of the array is equal to the Required number 
    if equal then fill and return True 
    Else return Flase.............................
    RowLimit is used to identify the limits of the row in the array
    CounterLimit is used  as indexer in the columns
*/

    public boolean SetNumbers(T [] Array){
        if(Array.length>=(this.Rows*this.Cols))
        {
            int RowLimit=0,CounterLimit=0;
            for (int x = 0; x < this.Rows; ++x)
            {
                for (int k = 0+RowLimit; k <Array.length; ++k)
                {
                    if(CounterLimit<this.Cols)
                    {
                        Elements[x][CounterLimit] = Array[k];
                    }
                    else
                    {
                        break;
                    }
                    
                    CounterLimit++;
                    
                }
                CounterLimit=0;
                RowLimit+=this.Cols;
            }
            return true;
        }
        return false;
    }



//------------------------------------------------------//
/*Function Print to Print the Data of the matrix */
public void Print(){
    for (int x = 0; x < this.Rows; ++x)
    {
        for (int k = 0; k <this.Cols; ++k)
        {
            System.out.printf(Elements[x][k]+" ");
        }
        System.out.println("\n");
    }
}
//------------------------------------------------------//



//------------------------------------------------------//
/*The Transpose Function is used to reverse the Elements of the array 
    First : check if the array is empty or not 
    then create a temp Array to fill in it
    then swap the two arrays
 */

public void Transpose(){
    if(Elements.length==0)
    {
        System.out.println("The matrix is Empty !!!!!!!!!!!!!!!");
        return;
    }

    T [][] transposedMatrix = (T[][]) new Number[this.Cols][this.Rows];


    for(int x = 0; x < this.Cols; x++) {
        for(int y = 0; y < this.Rows; y++) {
            transposedMatrix[x][y] = Elements[y][x];
        }

    }
    Elements = (T[][]) new Number[this.Cols][this.Rows]; 
    int temp=this.Cols;
    this.Cols=this.Rows;
    this.Rows=temp;
    for(int x = 0; x <this.Rows; x++) 
    {
        for(int y = 0; y < this.Cols; y++)
        {
            Elements[x][y]=transposedMatrix[x][y];
        }
    }
}
//---------------------------------------------------------//


//-----------------------------------------------------------//
/*Function Mulitpy take one parameter is a MatrixB  
    and return a TempMatrix with the result of the mulitplication
    First: check if the operation is valid according to the sizes 
    and then make the operation
 */
    public Matrix<T> mulitply(Matrix<T> MatrixB){

        if(this.Cols==MatrixB.Rows)
        {
            Matrix<T> TempMatrix=new Matrix<T>(this.Rows,MatrixB.Cols);

            for(int i=0;i<this.Rows;i++)
            {    
                for(int j=0;j<MatrixB.Cols;j++)
                { 
                    (Integer)TempMatrix.Elements[i][j]=(Integer)0;      
                    for(int k=0;k<this.Cols;k++)      
                    {      
                        (Double)(TempMatrix.Elements[i][j])+=(Double)((Double)this.Elements[i][k]*(Double)MatrixB.Elements[k][j]);      
                    } 
                }
            } 
            return TempMatrix;
        }
        else
        {
            //Throwing the Excepation 
            throw new MultiplicationException("Exception occured while trying to multiply two matrices of dimensions("+this.Rows+","+this.Cols+") and ("+MatrixB.Rows+","+MatrixB.Cols+")");    
        }
    }

//-----------------------------------------------------------------------//
}



class Identity<T extends Number> extends Matrix<T>{
    //-------------------------//
    //The constructor to the class
    public Identity(int M){
        super( M,M);
    }
    //-------------------------//


    //-------------------------//
    //The Trasnpose function will do nothing 
    public void Transpose(){
        //will do nothing 
        return;
    }
    //-------------------------//



    //-------------------------//
    // The Set Numbers function will take Array and fill with checking on the input data if the data is correct or not
    // and if the data is all ones and zeros or not 
    // if not the function will return false
    //and will reset the array to empty array

    public boolean SetNumbers(T [] Array){
        if(Array.length==(this.Rows*this.Cols))
        {
            int n=0;
                    for (int x = 0; x < this.Rows; ++x)
                    {
                        for (int k = 0; k <this.Cols; ++k)
                        {
                                if(x==k &&(Number)Array[n]==(Number)(1))
                                {
                                    this.Elements[x][k] = Array[n];
                                }
                                else if((x==k&&Double.compare((Double)(Array[n]),((Double)(1.0)))==0)==true)
                                {
                                    this.Elements[x][k] = Array[n];
                                }
                                else if(x!=k &&(Number)Array[n]==(Number)(0))
                                {
                                    this.Elements[x][k] = Array[n];
                                }
                                else if((x!=k&&Double.compare((Double)(Array[n]),((Double)(0.0)))==0)==true)
                                {
                                    this.Elements[x][k] = Array[n];
                                }
                                else
                                {
                                    this.Elements = (T[][]) new Number[this.Rows][this.Cols];
                                    return false;
                                }
                                n++;
                        }
                    }
                    return true;
            }
            
        return false;
    }
    //-------------------------//

    //------------------------------------------------------//

}

//--------------------------------------------------------------//
class MultiplicationThread implements Runnable{
    //Data Members 
    private Matrix<Integer> MatrixA;
    private Matrix<Integer> MatrixB;
    private Matrix<Integer> Result;

    //Constructor to take two args and set the MatrixA and MatrixB and Set Result by multiply the two
    public MultiplicationThread(Matrix<Integer>Arg1 , Matrix<Integer> Arg2)
    {
        MatrixA=Arg1;
        MatrixB=Arg2;
        Result=MatrixA.mulitply(MatrixB);
    }

}
//------------------------------------------------------------------//



// ------------------------------------------------------------------//
//The main class to test the classes with the data
public class Main{
    public static void main(String[] args)
    {
        // creating an Three objects from Matrix
        Matrix<Integer> MatrixA=new Matrix<Integer>(3,4);
        Matrix<Integer> MatrixB=new Matrix<Integer>(4,2);
        Matrix<Integer> MatrixC=new Matrix<Integer>(2,5);

        //Array to use it in setting the values in the Matrices
        Integer arr[]={1,2,3,4,5,6,7,8,9,10,11,12};

        // Set The Numbers in the Matrices
        MatrixA.SetNumbers(arr);
        MatrixB.SetNumbers(arr);
        MatrixC.SetNumbers(arr);

        // trying to Mulitply MatrixA and MatrixB  and use try and catch if the excepation is throwed
        try{
            long Start=System.nanoTime();
            Matrix<Integer> Result1=MatrixA.mulitply(MatrixB);
            Result1.Print();
            long End=System.nanoTime();
            System.out.printf("Time needed to multiply MatrixA and MatrixB is %d \n",(End-Start));

        }
        catch(Matrix.MultiplicationException MultiplicationError){
            MultiplicationError.message();
            return;
        }

        // trying to Mulitply MatrixA and MatrixC and use try and catch if the excepation is throwed
        try{
            long Start=System.nanoTime();
            Matrix<Integer> Result2=MatrixA.mulitply(MatrixC);
            Result2.Print();
            long End=System.nanoTime();
            System.out.printf("Time needed to multiply MatrixA and MatrixC is %d \n",(End-Start));
        }
        catch(Matrix.MultiplicationException MultiplicationError){
            MultiplicationError.message();
            return;
        }

        // creating two objects with size 500*500
        Matrix<Integer> MatrixD=new Matrix<Integer>(500,500);
        Matrix<Integer> MatrixE=new Matrix<Integer>(500,500);

        //Array to use it in setting the values in the Matrices
        Integer  arr2[] =new Integer[250000];

        // create a Random object to fill the arr2
        Random rd = new Random();

        //loop to fill arr2
        for(int i=0;i<250000;i++)
        {
            arr2[i]=rd.nextInt();
        }

        // Set The Numbers in the Matrices
        MatrixD.SetNumbers(arr2);
        MatrixE.SetNumbers(arr2);

        // trying to Mulitply MatrixD and MatrixE  and use try and catch if the excepation is throwed
        try{
            long Start=System.nanoTime();
            Matrix<Integer> Result1=MatrixD.mulitply(MatrixE);
            Result1.Print();
            long End=System.nanoTime();
            System.out.printf("Time needed to multiply MatrixD and MatrixE is %d \n",(End-Start));

        }
        catch(Matrix.MultiplicationException MultiplicationError){
            MultiplicationError.message();
            return;
        }
    }
}
//------------------//The end of the main class//---------------------//


