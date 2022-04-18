package Books;

import Books.BookStock;

//TODO-6: should it implement or extend anything?
public class Branch implements  Runnable {
    private BookStock b;

    public Branch (BookStock b) {
        this.b = b;
    }

    //TODO-7: is a function missing ?
    public  void doWork () {
        while (true) {
            //TODO-8: how to make it stop consuming when the store is empty, without adding extra sleeps or busy waiting ?
                synchronized (b) {
                    if (b.getCount() == 0) {
                        try {
                            // will be blocked and wait for the supplier to produce more books
                            b.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (b.getCount() > 0) {
                        b.consume();
                        if(b.getCount()+1==b.getMax())
                        {
                            // will notify the supplier if the number of the books is less than the number of the max
                            b.notifyAll();
                        }
                        System.out.println(Thread.currentThread().getName() + " sold a book");

                    }
                }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "is awaken");
            }
        }
    }
    public void run(){
        doWork();
    }
}