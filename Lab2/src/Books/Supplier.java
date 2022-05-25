package Books;

//TODO-3: should it implement or extend anything?
 public class Supplier implements  Runnable {
    private BookStock b;

    public Supplier (BookStock b) {
        this.b = b;
    }
    //TODO-4:is a function missing ?
    public void doWork () {
        while (true) {
            //TODO-5: how to make it stop producing when it reaches max, without adding extra sleeps or busy waiting ?
                synchronized (b) {
                    if (b.getCount() < b.getMax()) {
                        b.produce();
                        if(b.getCount()==1)
                        {
                            // will wake the branch from the waiting
                            b.notifyAll();
                        }
                        System.out.println(Thread.currentThread().getName() + " provided a book, total " + b.getCount());
                    }
                if(b.getCount()==b.getMax()){
                        System.out.println("There is more books are available ");
                        try {
                            b.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "is awaken");
            }
        }
    }
    public void run(){
        doWork();
    }

}
