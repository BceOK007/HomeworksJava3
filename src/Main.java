import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] arr = {"1", "2", "3wertyjk", "4", "5"};
        System.out.print("Исходный массив: ");
        printArray(arr);
        swapElements(arr, 1, 3);
        System.out.print("Полученный массив: ");
        printArray(arr);

        System.out.println("\nПолученный ArrayList: " + arrayToArrayList(arr));

        //создаем коробки
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        //создаем фрукты и сразу же добавляем их в коробки
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());

        appleBox2.addFruit(new Apple());

        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());

        //сравнение двух коробок
        System.out.println("\nКоробки равны по весу? " + appleBox1.compare(orangeBox1));

        //пересыпание фруктов из одной коробки в другую
        System.out.println("\nВеса коробок до пересыпания\nПервая коробка: " + appleBox1.getWeigh());
        System.out.println("Вторая коробка: " + appleBox2.getWeigh());
        appleBox1.moveContent(appleBox2);
        System.out.println("\nВеса коробок после пересыпания\nПервая коробка: " + appleBox1.getWeigh());
        System.out.println("Вторая коробка: " + appleBox2.getWeigh());
    }

    /**
     *  Метод, который меняет два элемента массива местами.
     * @param arr массив
     * @param pos1 позиция первого элемента
     * @param pos2 позиция второго элемента
     */
    public static <T> void swapElements (T[] arr, int pos1, int pos2) {
        if (pos1 < 0 || pos1 > arr.length - 1 || pos2 < 0 || pos2 > arr.length -1 ) {
            throw new RuntimeException(String.format("Один или оба индекса за границами массива. Укажите индексы от 0 до %d", arr.length-1));
        }

        if (pos1==pos2) return;

        T buffer;
        buffer = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = buffer;
    }

    public static <T> void printArray(T[] arr){
        for (T item:arr ) {
            System.out.print("[" + item + "] ");
        }
        System.out.println();
    }

    /**
     *  Метод, который преобразует массив в ArrayList
     *
     * @param arr массив
     * @return ArrayList
     */
    public static <T> List<T> arrayToArrayList(T[] arr) {
        List<T> result = new ArrayList<>();

        result.addAll(Arrays.asList(arr));

        return result;
    }
}
