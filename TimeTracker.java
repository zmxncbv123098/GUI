import java.util.Scanner;

public class TimeTracker {

    public static int[] getData(int len) {
        int a = 0; // from 0
        int b = 1000; // to 1000

        int[] l = new int[len];

        for (int i = 0; i < len; i++) {
            int rn = a + (int) (Math.random() * b);
            l[i] = rn;

        }
        return l;
    }



    public static void printList(int[] a) {

        for (Integer elem : a) {
            System.out.print(elem + ", ");
        }
    }

    public static int inputInt(){
        Scanner k = new Scanner(System.in);
        return k.nextInt();
    }

    public static void main(String[] args) {
        int len = inputInt();
        int[] l = getData(len);

        SortTester[] interArr = new SortTester[] {
                new SortTester(new GnSort(), "gnomeSort"),
                new SortTester(new BubbleSort(), "bubbleSort"),
                new SortTester(new selectionSort(), "selectionSort"),
                new SortTester(new BucketSort(), "bucketSort"),
                //new SortTester(new CocktailSort(), "cocktailSort")

        };

        for (SortTester i : interArr) {
            printList(l);
            i.sort(l);

        }
    }
}


class SortTester{
    ISort alg;
    public String name;
    double time;


    public SortTester(ISort alg,String name) {
        this.alg = alg;
        this.name = name;
    }

    public int[] sort(int[] arg) {

        int[] li = arg.clone();

        long start = System.nanoTime();
        alg.sort(li);
        long f = System.nanoTime();

        System.out.println(name);
        System.out.println((f - start)/1000000000);
        time = (f - start)/1000000000.0;
        System.out.println(time);
        return null;

    }

}
