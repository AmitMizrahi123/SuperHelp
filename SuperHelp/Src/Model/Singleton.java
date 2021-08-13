package Model;

public class Singleton {
    private static final Singleton firstInstance = new Singleton();

    private Singleton() {  }

    public static Singleton getInstance() {
        return firstInstance;
    }
}
