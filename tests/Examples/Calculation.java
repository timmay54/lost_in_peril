package cs492JUnit;

//Basic Calculation class with four methods, add, subtract, multiply, and divide
public class Calculation {
	
	
	static int add(int x, int y){
		int answer = x+y;
		return answer;
	}
	
	static int subtract(int x, int y){
		int answer = x-y;
		return answer;
	}
	
	static int multiply(int x, int y){
		int answer = x*y;
		return answer;
	}

	static double divide(double x, double y) {
		double answer = x/y;
		return answer;
	}
}
