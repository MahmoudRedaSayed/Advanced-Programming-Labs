import mpi.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Req {
    public static void main(String args [])
    {



        ArrayList<Integer> array=new ArrayList<Integer>();
        try {
            Scanner myReader = new Scanner(new File("filename.txt"));
            int count=0;
            while (myReader.hasNextInt()) {
                int num=myReader.nextInt();
                array.add(num);
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int share= (int) Math.ceil(array.size()/4.0);
        int[] array2=new int[share*4] ;
        for(int i=0;i<array.size();i++)
        {
            array2[i]=array.get(i);
        }

        int localSum=0;
        int totalSum=0;


        MPI.Init(args);
        int rank=MPI.COMM_WORLD.Rank();
        int size=share*MPI.COMM_WORLD.Size();
        int recBuffer []=new int[size];
        int send[]=new int[size];



        if(rank==0)
        {



            MPI.COMM_WORLD.Scatter(array2,0,share,MPI.INT,recBuffer,0,share,MPI.INT,0);
            for(int i=0;i<share;i++)
            {
                localSum+=array2[i];
            }
            send[0]=localSum;
            System.out.println("I am Process "+rank+" and my local sum is "+localSum);
            MPI.COMM_WORLD.Gather(send,0,1,MPI.INT,recBuffer,0,1,MPI.INT,0);
            for(int i=0;i<MPI.COMM_WORLD.Size();i++)
            {
                totalSum+=recBuffer[i];
            }
            System.out.println("I am Process "+rank+" and my total sum is "+totalSum);

        }
        else
        {
            int rec[]=new int [share];
            for(int i=0;i<share;i++)
            {
                rec[i]=0;
            }

            MPI.COMM_WORLD.Scatter(send,0,share,MPI.INT,rec,0,share,MPI.INT,0);
            for(int i=0;i<share;i++)
            {
                localSum+=rec[i];
            }
            System.out.println("I am Process "+rank+" and my local sum is "+localSum);
            rec[0]=localSum;
            MPI.COMM_WORLD.Gather(rec,0,1,MPI.INT,recBuffer,0,1,MPI.INT,0);

        }
        MPI.Finalize();
    }
}
