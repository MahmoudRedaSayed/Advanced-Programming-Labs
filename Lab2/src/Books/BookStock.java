package Books;

public class BookStock {
    private int book_count;
    private int max;
    public BookStock  (int max) {
        this.max = max;
    }
    public  void produce() {
        book_count++;
    }

    public  void consume () {
        book_count--;
    }

    public  int getCount () {
        return book_count;
    }

    public int getMax(){
        return this.max;
    }
}

