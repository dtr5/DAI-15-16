package ua.dlsi.dai.autocorrector;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Practica4  {
	
	public static String url = "http://localhost:8080/px/";
	public static String NormalFile = "normal.png";
	public static String CompactFile = "compact.png";
	public static String [] files;
	
    public static void main(String[] args) {

		url = "http://localhost:8888";

		JUnitCore junit = new JUnitCore();
		Result result = junit.run(Practica4Tests.class);

		for (Failure f : result.getFailures()) {
			System.out.println("Error: " + f.getMessage());
		}
		
		System.out.println("Fallaste " + result.getFailureCount() + " de " + result.getRunCount() + " pruebas"); 
		System.exit(0);
	
    }
}