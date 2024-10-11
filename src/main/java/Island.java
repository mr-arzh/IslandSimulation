import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Island {

    private Random random = new Random();
    private int maxNumberOfAnimals = 5;
    private int maxPlantPerCell = 3;
    public static int width = 100;
    public static int height = 20;
    public static Cell[][] gameField;

    private ScheduledExecutorService scheduler;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        gameField = new Cell[height][width];
        //cellInit();
        scheduler = Executors.newScheduledThreadPool(1);
        //fillCells();
    }


    public static Cell getCell(int x, int y) {

        return gameField[y][x];
    }


    public void cellInit() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = new Cell(maxNumberOfAnimals, maxPlantPerCell, width, height);

            }
        }
    }

    public void fillCells() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                Cell cell = gameField[i][j];

                //добавление животных
                int numberOfAnimalsPerCell = random.nextInt(maxNumberOfAnimals);
                for (int k = 0; k < numberOfAnimalsPerCell; k++) {
                    int randomIndex = random.nextInt(AnimalType.values().length);
                    AnimalType type = AnimalType.values()[randomIndex];
                    Animal animal = AnimalFactory.createAnimal(type, cell);
                    if (cell.canAddAnimal(animal)) {
                        cell.addAnimal(animal);
                    }
                }

                //добавление растений
                int numberOfPlantsPerCell = random.nextInt(maxPlantPerCell);
                for (int k = 0; k < numberOfPlantsPerCell; k++) {
                    Plant plant = new Plant("🌿 ");
                    cell.addPlants(plant);

                }
            }
        }
    }

    public void draw() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                Cell cell = gameField[i][j];
                if (cell.getAllAnimals().isEmpty() && cell.getPlants().isEmpty()) {
                    System.out.print(" . ");
                } else {
                    for (Animal animal : cell.getAllAnimals()) {
                        System.out.print(animal.getSymbol() + " ");
                    }
                    for (Plant plant : cell.getPlants()) {
                        System.out.print(plant.getSymbol());
                    }
                }
                System.out.println(" | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void update() {


//        if (allAnimalsAreDead()) {
//            System.out.println("All Animals are dead");
//            return;
//        } else if (allPlantsAreEaten()) {
//            System.out.println("All plants are eaten");
//            return;
//        }

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                Cell cell = gameField[i][j];
                for (Animal animal : cell.getAllAnimals()) {
                    animal.move();
                    //draw();
                    animal.eat();
                    //draw();
                    animal.multiply();
                    //draw();
                    animal.die();
                    //draw();
                }

            }
        }
        draw();
    }


//-----------------эксперимент не удался - несите следующего-------------------------
//      if (allAnimalsAreDead()) {
////            stopIslandSimulation();
//
//        }
//    }
//
//    private boolean allAnimalsAreDead() {
//        for (int i = 0; i < gameField.length; i++) {
//            for (int j = 0; j < gameField[i].length; j++) {
//                Cell cell = gameField[i][j];
//                if (cell.getAllAnimals().isEmpty()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private boolean allPlantsAreEaten() {
//        for (int i = 0; i < gameField.length; i++) {
//            for (int j = 0; j < gameField[i].length; j++) {
//                Cell cell = gameField[i][j];
//                if (!cell.getPlants().isEmpty()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public void startIslandSimulation() {
        scheduler.scheduleAtFixedRate(this::update, 0, 2, TimeUnit.SECONDS);
    }

    public void stopIslandSimulation() {

            scheduler.shutdown();
            try {

                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)){ //жду 5 сек прежде чем обрежу все потоки
                    scheduler.shutdownNow();
                }

            } catch (InterruptedException e) {
                scheduler.shutdownNow();
        }
        System.out.println("Sorry, the time is over. But you could start again!");
    }




    public static void main(String[] args) {
        Island island = new Island(height, width);
        island.cellInit();
        island.fillCells();
        //island.draw();

        island.startIslandSimulation();

        // стопнем симуляцию через 15 секунд
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        island.stopIslandSimulation();

    }

}




