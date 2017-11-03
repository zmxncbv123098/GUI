import java.util.Scanner;


interface ISort{
    int[] sort(int[] arg);
}

class GnSort implements ISort {

    public static void main(String[] args) {
        int[] l = new int[5];
        for (int i = 0; i < 5; i++) {
            Scanner k = new Scanner(System.in);
            int res = k.nextInt();
            l[i] = res;
        }
    }

    @Override
    public int[] sort(int[] arg) {
        int i = 1;
        while (i < arg.length) {
            if (i == 0 || arg[i - 1] <= arg[i]) {
                i++;
            } else {
                int temp = arg[i];
                arg[i] = arg[i - 1];
                arg[i - 1] = temp;
                i--;
            }
        }
        return arg;
    }

}

public class BubbleSort implements ISort {

    /*
    public static void main(String[] args) {
        List<Integer> i = new ArrayList<Integer>();
        ISort bubble = new BubbleSort();
        ISort newSort = new NewSort();
        ISort qu = new GnSort();
        System.out.println(bubble.sort(i));
        System.out.println(newSort.sort(i));
        System.out.println(qu.sort(i));
    }
    */


    @Override
    public int[] sort(int[] arg) {
        for (int i = arg.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if (arg[j] > arg[j + 1]) {
                    int tmp = arg[j];
                    arg[j] = arg[j + 1];
                    arg[j + 1] = tmp;
                }
            }
        }

        return arg;
    }
}

class selectionSort implements ISort {

    @Override
    public int[] sort(int[] arg) {
        for (int i = 0; i < arg.length; i++) {
        /*Предполагаем, что первый элемент (в каждом
           подмножестве элементов) является минимальным */
            int min = arg[i];
            int min_i = i;
        /*В оставшейся части подмножества ищем элемент,
           который меньше предположенного минимума*/
            for (int j = i+1; j < arg.length; j++) {
                //Если находим, запоминаем его индекс
                if (arg[j] < min) {
                    min = arg[j];
                    min_i = j;
                }
            }
        /*Если нашелся элемент, меньший, чем на текущей позиции,
          меняем их местами*/
            if (i != min_i) {
                int tmp = arg[i];
                arg[i] = arg[min_i];
                arg[min_i] = tmp;
            }
        }

        return arg;
    }

}


class BucketSort implements ISort {


    @Override
    public int[] sort(int[] arg) {
        int maxVal = 1000;
        int[] bucket = new int[maxVal + 1];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < arg.length; i++) {
            bucket[arg[i]]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                arg[outPos++] = i;

            }
        }
        return arg;
    }
}

class CocktailSort implements ISort{

    @Override
    public int[] sort(int[] arg) {

        int left = 0;
        int right = arg.length - 1;

        do {
            for (int i = left; i < right; i++) {
                if (arg[i - 1] > arg[i]) {
                    int tmp = arg[i];
                    arg[i] = arg[i - 1];
                    arg[i -1 ] = tmp;
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (arg[i] <  arg[i - 1]) {
                    int tmp = arg[i-1];
                    arg[i - 1] = arg[i];
                    arg[i] = tmp;
                }

            }
            left++;

        } while (left <= right);
        return arg;
    }
}