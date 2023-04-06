package Strategy;

public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        flyBehavior = new FlyRocketPower();
        quackBehavior = new Quack();
        swimBehavior = new CanSwim();
    }

    @Override
    public void display() {
        System.out.println("I am red head duck, i am very beautiful, hahahahha");
    }
    
}
