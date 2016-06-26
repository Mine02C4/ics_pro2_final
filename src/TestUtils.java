import java.util.Calendar;

public class TestUtils {
	public static void InitializeTestData() {
		DataHost.Single().members.AddMember("Takasaki");
		DataHost.Single().members.AddMember("Kawasaki");
		DataHost.Single().members.AddMember("Satou");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 6, 26, 18, 30);
		DataHost.Single().concerts.AddConcert("Kirara Festa", "Anime", calendar.getTime(), "TOKYODOME CITY HALL", 7560, 2000);
		calendar = Calendar.getInstance();
		calendar.set(2016, 5, 7, 13, 30);
		DataHost.Single().concerts.AddConcert("Rabbit House Tea Party 2016", "Anime", calendar.getTime(), "Pacifico Yokohama", 6480, 5000);
		calendar = Calendar.getInstance();
		calendar.set(2016, 3, 12, 17, 30);
		DataHost.Single().concerts.AddConcert("RAINBOW CANARY!! Brightest Stage", "Live", calendar.getTime(), "Budokan", 7200, 7000);
	}
}
