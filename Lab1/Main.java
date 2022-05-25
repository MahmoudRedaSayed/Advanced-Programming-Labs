
import java.lang.Double;
class  Matrix<T extends Number> {
    //The member data of the Matrix class
    protected int Rows;
    protected int Cols;
    protected T [][] Elements;
    //----------------------------------//


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
        if(Array.length==(this.Rows*this.Cols))
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



// ------------------------------------------------------------------//
//The main class to test the classes with the data
public class Main{
    public static void main(String[] args)
    {
        // creating an object from Matrix
        Matrix<Integer> IntMatrix=new Matrix<Integer>(3,2);
        Integer arr[]={1,0,2,3,1,0};
        // Check the Functions{SetNumbers,Print,Transpose}
        IntMatrix.SetNumbers(arr);
        System.out.println("Print After using the SetNumbers function \n");
        IntMatrix.Print();
        IntMatrix.Transpose();
        System.out.println("Print After using the Transpose function \n");
        IntMatrix.Print();
        //-----------------------------------------------------//

        // Creating an object from Identity
        Identity<Integer> IdentityObj=new Identity<Integer>(3);
        Integer arr2[]={1,0,0,0,1,0,0,0,1};
        IdentityObj.SetNumbers(arr2);
        System.out.println("Print After using the SetNumbers function \n");
        IdentityObj.Print();
        IdentityObj.Transpose();
        System.out.println("Print After using the Transpose function \n");
        IdentityObj.Print();
        //Check to the double
        Identity<Double> IdentityObj2=new Identity<Double>(3);
        Double arr3[]={1.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,1.0};
        IdentityObj2.SetNumbers(arr3);
        System.out.println("Print After using the SetNumbers function \n");
        IdentityObj2.Print();
        IdentityObj2.Transpose();
        System.out.println("Print After using the Transpose function \n");
        IdentityObj2.Print();
    }
}


