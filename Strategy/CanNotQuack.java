package Strategy;

public class CanNotQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Haizz, i can't say QUACK");
    }
    
}

