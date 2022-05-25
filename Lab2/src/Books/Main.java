package Books;
/***********************************Requirement description**********************************************/
//There is a bookstore that has different branches, each of them sell a number of books, these books are supplied by the Supplier.
//A bookstore shouldn't sell a book when the number of books is 0, it should block and notify the Supplier to provide more
//A supplier shouldn't provide a book when the max count of books is reached, when it provides a book, it should tell stores that there are more books available.
//Modify the requirement_student.java code to reflect this behaviour.
//All threads should execute in parallel, you cannot allow a thread to stop another thread (should guarantee progress)
//Follow the 8 TODOs in the Code

import static java.lang.Thread.sleep;

/***********************************************************************************************/

public class Main {
    public static void main(String [] args)
    {
        BookStock b = new BookStock (10);

        //TODO-1: Create 4 threads,
        // 1 for Supplier
        Thread Supply=new Thread(new Supplier(b));
        Supply.setName("Supplier");
        // 3 for StoreBranches and Name them as the following: Giza branch, Cairo branch, Daqahley branch
        Thread Branch1=new Thread(new Branch(b));
        Thread Branch2=new Thread(new Branch(b));
        Thread Branch3=new Thread(new Branch(b));
        Branch1.setName("Giza branch");
        Branch2.setName("Cairo branch");
        Branch3.setName("Daqahley branch");


        //TODO-2: Run the 4 threads
        Supply.start();
        Branch1.start();
        Branch2.start();
        Branch3.start();




    }
}
