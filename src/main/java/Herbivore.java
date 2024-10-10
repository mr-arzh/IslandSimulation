import java.util.ArrayList;
import java.util.Random;

public abstract class Herbivore extends Animal {

    private Cell cell;
    private ArrayList<Plant> plantsInCell;
    private Random random = new Random();
    //private ArrayList<Animal> allAnimals;

    public ArrayList<Plant> getPlantsInCell() {
        return plantsInCell;
    }

    private ArrayList<Plant> currentPlants;






    public Herbivore(String symbol, AnimalType type, Cell cell) {
        super(symbol, type, cell);
        this.cell = cell;
    }

    @Override
    public void eat() {
        plantsInCell = cell.getPlants();
        if (!plantsInCell.isEmpty()) {
            int randomIndex = random.nextInt(plantsInCell.size());
            Plant chosenPlant = plantsInCell.get(randomIndex);
            this.cell.eatenPlant(chosenPlant);
            System.out.println(this.getSymbol() + " ate " + chosenPlant.getSymbol());
            //setHungryLevel(Math.min(getHungryLevel()+1, 100));
        }
    }


    @Override
    public void multiply() {
        ArrayList<Herbivore> herbivoresOfSameType = new ArrayList<Herbivore>();
        currentPlants = cell.getPlants();
        //cell.getAllAnimals() = cell.getAllAnimals();
        if (!currentPlants.isEmpty()) {
            for (Animal animal : cell.getAllAnimals()) {
                if (animal.getClass().equals(this.getClass())) {
                    herbivoresOfSameType.add((Herbivore) animal);
                }
            }
            //ищу пару
            if (herbivoresOfSameType.size() >= 2) {

                Herbivore herbivoreFirst = null;
                Herbivore herbivoreSecond = null;
                //проверяю не голодны ли пара
                for (Herbivore herbivore : herbivoresOfSameType) {
                    if (!herbivore.isHungry()) {
                        if (herbivoreFirst == null) {
                            herbivoreFirst = herbivore;
                        } else if (herbivoreSecond == null) {
                            herbivoreSecond = herbivore;
                            break;
                        }
                    }
                }
                ////Проверяю, что мы ТОЧНО нашли двух неголодных травоядных
                if ((herbivoreFirst != null) && (herbivoreSecond != null)) {
                    if (herbivoresOfSameType.size() < cell.getMaxAnimalsPerCell()) {
                        Animal newHerbivoreAnimal = AnimalFactory.createAnimal(this.getType(), cell);
                        if (newHerbivoreAnimal != null) {
                            cell.addAnimal(newHerbivoreAnimal);
                            //System.out.println(this.getSymbol() + " gave birth to " + newHerbivoreAnimal.getSymbol());
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isFoodEnoughInCell() {
        plantsInCell = cell.getPlants();
        if (!plantsInCell.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void move(){
        if (isFoodEnoughInCell()){
            super.move();
        }
    }

    @Override
    public void die(){
        super.die();
    }
}

