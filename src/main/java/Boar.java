public class Boar extends Herbivore {


    public Boar(AnimalType type, Cell cell) {
        super(" \uD83D\uDC17 ", type, cell);
    }

    @Override
    public void eat(){
        super.eat();
        //System.out.println(this.getSymbol() +" ate " + this.getSymbol() );
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
        return AnimalType.BOAR;
    }

}
