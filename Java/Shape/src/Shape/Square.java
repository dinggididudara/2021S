package Shape;

import java.util.Scanner;

public class Square extends Shape{
	private double side;

	Scanner sc = new Scanner(System.in);
	
	Square(){
		System.out.println("side?");
		side = sc.nextDouble();
		sc.close();
	}

	public void findArea() {
		area = side * side;
		super.printArea();
	}

	public void findPerimeter() {
		perimeter = 4 * side;
		super.printPerimeter();
	}
}
