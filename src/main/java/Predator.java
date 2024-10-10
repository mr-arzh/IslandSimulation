import java.util.ArrayList;
import java.util.Random;

public abstract class Predator extends Animal {
    //private ArrayList<Animal> allAnimals;
    private Random random = new Random();

    private Cell cell;

    //private int maxAnimalsPerType;



    public Predator(String symbol, AnimalType type, Cell cell) {
        super(symbol, type, cell);
        this.cell = cell;

    }


    public void eat() {
        ArrayList<Animal> herbivores = cell.getHerbivores();
        if (!herbivores.isEmpty()) {
            int max = herbivores.size() - 1;
            int randomElement = random.nextInt(max + 1);
            Animal eatenAnimal = herbivores.get(randomElement);
            cell.getAllAnimals().remove(eatenAnimal);
            System.out.println(this.getSymbol() +" ate " + eatenAnimal.getSymbol() );
            //setHungryLevel(Math.min(getHungryLevel()+1, 100));
        }
    }

    @Override
    public void multiply() {
        ArrayList<Predator> predatorsOfSameType = new ArrayList<>();
        for (Animal animal : cell.getAllAnimals()) {
            if (animal.getClass().equals(this.getClass())) {
                predatorsOfSameType.add((Predator) animal);
            }
        }
        //ищу пару для размножения
        if (predatorsOfSameType.size() >= 2) {

            //ввожу 2 переменные которые будут хранить ссылки на 2х НЕголодных животных одного типа
            Predator firstPredator = null;
            Predator secondPredator = null;
            for (Predator predator : predatorsOfSameType) {
                if (!predator.isHungry()) {
                    if (firstPredator == null) {
                        firstPredator = predator;
                    } else if (secondPredator == null) {
                        secondPredator = predator;
                        break;
                    }
                }
            }
            //Проверяю, что мы ТОЧНО нашли двух неголодных хищников
            if ((firstPredator != null) && (secondPredator != null)) {
                if (predatorsOfSameType.size() < cell.getMaxAnimalsPerCell()) {
                    Animal newPredatorOfType = AnimalFactory.createAnimal(this.getType(), cell);
                    if (newPredatorOfType != null) {
                        cell.addAnimal(newPredatorOfType);
                        //System.out.println( this.getSymbol() + " gave birth to " + newPredatorOfType.getSymbol());
                    }
                }
            }
        }
    }

    @Override
    public void move(){
        if (!isFoodEnoughInCell()){
            super.move();
        }
    }

    @Override
    public boolean isFoodEnoughInCell(){
        if (!cell.getHerbivores().isEmpty()){
                return true;
        }
        return false; //если дошли до конца и не нашли травоядных
    }

    @Override
    public void die(){
        super.die();
    }
}




