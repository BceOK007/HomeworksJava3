import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{
    private List<T> content = new ArrayList<>();

    /**
     * Метод который высчитывает вес коробки
     * @return общий вес фруктов в коробке
     */
    public float getWeigh() {
        float weighBox = 0;

        for (T item: content) {
            weighBox += item.getWeigh();
        }

        return weighBox;
    }

    /**
     * Метод добавления фрукта в коробку.
     * @param fruit фрукт
     */
    public void addFruit(T fruit) {
        content.add(fruit);
    }

    /**
     * Метод, который позволяет сравнить текущую коробку с той,
     * которую подадут в compare в качестве параметра,
     * true - если их веса равны, false в противном случае
     * (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     * @param box коробка с которой необходимо сравнить текущую
     * @return true - если их веса равны, false в противном случае
     */
    public boolean compare (Box box) {
        return Math.abs(this.getWeigh() - box.getWeigh()) < 0.0000001;
    }

    /**
     * метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
     * (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
     * соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
     * которые были в этой коробке;
     * @param destination коробка назначения
     */
    public void moveContent(Box<T> destination) {
        if(content == destination) {
            System.out.println("Нельзя пересыпать коробку саму в себя!");
        }
        for (T item : content) {
            destination.addFruit(item);
        }
        content.clear();//очищаем коробку с которой пересыпали все фрукты
    }
}
