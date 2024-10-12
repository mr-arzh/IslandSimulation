import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {


    private AnimalType type;

    private Cell cell;

    private String symbol;

    private ArrayList<Cell> listOfCellsToMove;

    private Random random = new Random();

    private int iterationsWithoutFood;


    public Animal(String symbol, AnimalType type, Cell cell) {
        this.symbol = symbol;
        this.type = type;
        this.cell = cell;

    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public boolean isHungry() {
        if (iterationsWithoutFood >= 1 && iterationsWithoutFood <= 3) {
            return false;
        }
        return true;
    }


    public void eat() {
        //return Math.min(hungryLevel + 30, 100); //как вариант? пока оставлю
    }


    public void move() {

        if (!isFoodEnoughInCell()) { // тут будет логика метода в классах наследниках Herbivore/Predator
            listOfCellsToMove = cell.getNeighboringCells();// реализую этот метод в классе Cell
            //if (!listOfCellsToMove.isEmpty()) {
                int randomIndex = random.nextInt(listOfCellsToMove.size());
                Cell targetCell = listOfCellsToMove.get(randomIndex);
                if (targetCell.getAllAnimals().size() <= targetCell.getMaxAnimalsPerCell()) { //чекаю можно ли добавить новое животное в клетку
                    cell.removeAnimal(this);
                    targetCell.addAnimal(this);
                    setCell(targetCell); //устанавливаю новые координаты для пермещенного объекта
                    increaseHunger();

            }
        }
    }

    public abstract void multiply();

    public void resetHunger() {
        iterationsWithoutFood--;
    }

    public void increaseHunger() {
        iterationsWithoutFood++;
    }


    public void die() {
        if (iterationsWithoutFood >= 3) {
            cell.removeAnimal(this);
        }
    }


    public AnimalType getType() {
        return this.type;
    }

    public String getSymbol() {
        return symbol;
    }
    public abstract boolean isFoodEnoughInCell ();

}



//-----------на будущее можно добавить класс птица которая будет есть и мелких животных и растения------------------------

//        public void canEat(ArrayList<Animal> animals){
//        animals = new ArrayList<>();
//        for (Animal animal : animals){
//            if (animal instanceof Herbivore){
//                ArrayList<Plant> plantsInCell = cell.getPlants();
//                if (!plantsInCell.isEmpty()) {
//                    int randomIndex = random.nextInt(plantsInCell.size());
//                    Plant chosenPlant = plantsInCell.get(randomIndex);
//                    this.cell.eatenPlant(chosenPlant);
//                }
//            } else if (animal instanceof Animal){
//                ArrayList<Animal> herbivores = cell.getHerbivores();
//                if (!herbivores.isEmpty()) {
//                    int max = herbivores.size() - 1;
//                    int randomElement = random.nextInt(max + 1);
//                    Animal eatenAnimal = herbivores.get(randomElement);
//                    cell.getAllAnimals().remove(eatenAnimal);
//                }
//            } else if (animal instanceof Birds){
//                //both of them
//                if()
//            }
//        }
//        }




