package Strategy;

public class CanNotSwim implements SwimBehavior {

    @Override
    public void swim() {
        System.out.println("Holy shit!!! I can't fucking swim!!!!");
    }
    
}
