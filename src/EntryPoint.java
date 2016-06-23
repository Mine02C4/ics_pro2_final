import java.util.Date;

public class EntryPoint {
	public static void main(String[] args) {
		InitializeTestData();
		System.out.println(DataHost.Single().concerts);
		DataHost.Single().Save();
	}
	
	public static void InitializeTestData() {
		DataHost.Single().members.AddMember("Takasaki");
		DataHost.Single().members.AddMember("Kawasaki");
		DataHost.Single().members.AddMember("Satou");
		DataHost.Single().concerts.AddConcert("Concert1", 1, new Date(), 1, 1000);
		DataHost.Single().concerts.AddConcert("Concert2", 1, new Date(), 1, 1000);
		DataHost.Single().concerts.AddConcert("Concert3", 1, new Date(), 1, 1000);
		DataHost.Single().concerts.AddConcert("Concert4", 1, new Date(), 1, 1000);
	}
}
