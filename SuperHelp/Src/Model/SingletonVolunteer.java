package Model;

public class SingletonVolunteer {
    private static SingletonVolunteer firstInstance = null;

    private SingletonVolunteer() {  }

    public static SingletonVolunteer getInstance() {
        if (firstInstance == null)
            firstInstance = new SingletonVolunteer();

        return firstInstance;
    }
}
