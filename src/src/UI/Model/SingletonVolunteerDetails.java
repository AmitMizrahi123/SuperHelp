package Model;

public class SingletonVolunteerDetails {
    private static SingletonVolunteerDetails firstInstance=null;

    public SingletonVolunteerDetails() {}

    public static SingletonVolunteerDetails getInstance() {
        if (firstInstance == null) {
            firstInstance = new SingletonVolunteerDetails();
        }
        return firstInstance;
    }
}
