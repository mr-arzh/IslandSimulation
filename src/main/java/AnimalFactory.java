public abstract class AnimalFactory {

    public static Animal createAnimal(AnimalType type, Cell cell) {
        switch (type) {
            case AnimalType.WOLF:
                return new Wolf(type, cell);
            case AnimalType.RABBIT:
                return new Rabbit(type, cell);
            case AnimalType.BEAR:
                return new Bear(type, cell);
            case AnimalType.DUCK:
                return new Duck(type, cell);
            case AnimalType.MOOSE:
                return new Moose(type, cell);
            case AnimalType.GOAT:
                return new Goat(type, cell);
            case AnimalType.SHEEP:
                return new Sheep(type, cell);
            case AnimalType.DEER:
                return new Deer(type, cell);
            case AnimalType.HORSE:
                return new Horse(type, cell);
            case AnimalType.PARROT:
                return new Parrot(type, cell);
            case AnimalType.COW:
                return new Cow(type, cell);
            case AnimalType.BOAR:
                return new Boar(type, cell);
            case AnimalType.FOX:
                return new Fox(type, cell);
            case AnimalType.SNAKE:
                return new Snake(type, cell);
            case AnimalType.EAGLE:
                return new Eagle(type, cell);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}