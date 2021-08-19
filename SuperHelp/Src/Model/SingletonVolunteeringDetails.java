package Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingletonVolunteeringDetails {
    private static SingletonVolunteeringDetails firstInstance = new SingletonVolunteeringDetails();

    private final String _url = "https://www.science.co.il/municipal/Cities.php";
    private List<Integer> _ages = setAges();
    private List<String> _locations = setLocations();
    private final String[] _gender = { "male", "female" };

    private SingletonVolunteeringDetails() { }

    public static SingletonVolunteeringDetails getInstance() {
        return firstInstance;
    }

    private List<Integer> setAges() {
        List<Integer> ages = new ArrayList<>();
        for (int i = 16; i <= 120; i++) {
            ages.add(i);
        }
        return ages;
    }

    private List<String> setLocations() {
        List<String> locatinos = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://www.science.co.il/municipal/Cities.php").get();
            Elements table = doc.getElementsByClass("content");
            table.forEach(city -> {
                city.getElementsByTag("a").forEach(el -> {
                    locatinos.add(el.text());
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locatinos;
    }

    public List<Integer> getAge() {
        return _ages;
    }

    public List<String> getLocation() {
        return _locations;
    }
    public String[] getGender() {
        return _gender;
    }
}
