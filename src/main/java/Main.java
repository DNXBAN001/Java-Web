
public class Main {

	public static void main(String[] args) {
		
		String id = "9705125894084";
		System.out.println(id.matches("-?\\d+(\\.\\d+)?") && id.length()==13);
	}

}
