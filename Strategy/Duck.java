package Strategy;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    SwimBehavior swimBehavior;

    public Duck() {

    }

    ///////////////// SET BEHAVIOR //////////////////////

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setSwimBehavior(SwimBehavior swimBehavior) {
        this.swimBehavior = swimBehavior;
    }

    /////////////////// PERFORM //////////////////////

    public void flyPerform() {
        flyBehavior.fly();
    }

    public void quackPerform() {
        quackBehavior.quack();
    }

    public void swimPerform() {
        swimBehavior.swim();
    }

    /////////////////// ABSTRACT //////////////////////

    public abstract void display();

    /////////////////// MAIN //////////////////////
    public static void main(String[] args) {
        Duck redHeadDuck = new RedHeadDuck();

        redHeadDuck.flyPerform();
        redHeadDuck.quackPerform();
        redHeadDuck.swimPerform();
    }
}
