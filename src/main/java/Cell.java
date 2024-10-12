import java.util.ArrayList;

public class Cell {

    private Cell cell;



    private ArrayList<Animal> listOfAnimals;
    private int maxAnimalsPerCell = 2;

    private ArrayList<Plant> plants;

    private int maxPlantPerCell;

    public int getMaxPlantPerCell() {
        return maxPlantPerCell;
    }

    public int getMaxAnimalsPerCell() {
        return maxAnimalsPerCell;
    }

    private int x;
    private int y;




    public Cell(int maxAnimalsPerType, int maxPlantPerCell, int x, int y) {
        this.listOfAnimals = new ArrayList<>();
        this.maxAnimalsPerCell = maxAnimalsPerType;
        this.plants = new ArrayList<>();
        this.maxPlantPerCell = maxPlantPerCell;
        this.x = x;
        this.y = y;
    }



    //добавление животных
    public Boolean addAnimal(Animal animal) {
        if (getCountByType(animal.getType()) < maxAnimalsPerCell) {
            listOfAnimals.add(animal);
            //animal.setCell();
            return true;
        } else {
            return false;
        }
    }

    //удаление животных
    public void removeAnimal(Animal animal) {
        listOfAnimals.remove(animal);
    }

    //
    public int getCountByType(AnimalType type) {
        int count = 0;
        for (Animal animal : listOfAnimals) {
            if (animal.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Animal> getAllAnimals() {
        return new ArrayList<Animal>(listOfAnimals); //вернули копию списка животных всех типов
    }

    public ArrayList<Animal> getAnimalByType(AnimalType type) {
        ArrayList<Animal> animalsByType = new ArrayList<Animal>();
        for (Animal animal : listOfAnimals) {
            if (animal.getType() == type) {
                animalsByType.add(animal);
            }
        }
        return animalsByType; //отфильтровали изначальный список животных по типам
    }


    public boolean canAddAnimal(Animal animal) {

        return (listOfAnimals.size() < maxAnimalsPerCell);
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void addPlants(Plant plant) {
        if (plants.size() < maxPlantPerCell) {
            plants.add(plant);
        }
    }

    public void eatenPlant(Plant plant) {
        if (plants.contains(plant)) {
            plants.remove(plant);
        }
    }

    public ArrayList<Cell> getNeighboringCells() {
        ArrayList<Cell> neighboringCells = new ArrayList<Cell>();

        for (int xx = -1; xx <= 1; xx++) { //перебираем ячейки от -1; 0; 1 по оси Х
            for (int yy = -1; yy <= 1; yy++) { //перебираем ячейки от -1; 0; 1 по оси Y
                if (xx == 0 && yy == 0) {
                    continue; //пропускаем шаг когда ячейка оказывается на своем месте(в нулях)
                }
                int newX = x + xx; //присваиваю новой переменной Х новое уже смещенное значение
                int newY = y + yy;
                if (newX >= 0 && newX < Island.width && newY >= 0 && newY < Island.height) {
                    neighboringCells.add(Island.getCell(newX, newY));
                }
            }
        }
        return neighboringCells;
    }

    public ArrayList<Animal> getHerbivores() {
        ArrayList<Animal> herbivores = new ArrayList<>();
        for (Animal animal : listOfAnimals) {
            if (animal instanceof Herbivore) {
                herbivores.add((Herbivore) animal);
            }
        }
        return herbivores;
    }

//    public ArrayList<Animal> getBothHerbsAndPreds(){
//        ArrayList<Animal> herbsAndPreds = new ArrayList<>();
//        for (Animal animal : listOfAnimals){
//            if (animal instanceof Birds){
//                herbsAndPreds.add(animal);
//            }
//        }
//        return herbsAndPreds;
//    }
}
// перебор клеток более избыточный способ
//        if ((0 ≤ x < 100) && (0 ≤ y < 20)){
//        for (int i = 0; i < (x-1); i++) {// left neighbor
//            for (int j = 0; j < y; j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < (i+1); i++) { //right neighbor
//            for (int j = 0; j < y; j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < x; i++) { //down neighbor
//            for (int j = 0; j < (y-1); j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < x; i++) { //up neighbor
//            for (int j = 0; j < (y+1); j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < (x-1); i++) { // diog
//            for (int j = 0; j < (y-1); j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < (x-1); i++) { // diog
//            for (int j = 0; j < (y+1); j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < (x+1); i++) { // diog
//            for (int j = 0; j < (y-1); j++) {
//                neighboringCells.add(this);
//            }
//        }
//
//        for (int i = 0; i < (x+1); i++) { // diog
//            for (int j = 0; j < (y+1); j++) {
//                neighboringCells.add(this);
//            }
//        }

