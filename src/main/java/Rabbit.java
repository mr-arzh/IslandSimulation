public class Rabbit extends Herbivore {


    public Rabbit(AnimalType type, Cell cell) {
        super(" \uD83D\uDC07 ", type, cell);
    }

    @Override
    public void eat(){
        super.eat();
        //System.out.println(this.getSymbol() +" ate " + cell..getSymbol() );
        resetHunger();
    }

    @Override
    public void multiply(){
        super.multiply();
        System.out.println(this.getSymbol() + " gave birth to " + this.getSymbol());
        increaseHunger();
    }

    @Override
    public void move(){
        super.move();
        System.out.println(this.getSymbol() +" moved to another Cell ");
        increaseHunger();
    }


    @Override
    public void die(){
        super.die();
        System.out.println(this.getSymbol() + " died from Hungry ");
    }

    public AnimalType getType(){
        return AnimalType.RABBIT;
    }

}
