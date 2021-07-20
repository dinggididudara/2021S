package Shape;

import java.util.Scanner;

public class Rectangle extends Shape{
	private double length;
	private double width;
	Scanner sc = new Scanner(System.in);
	
	Rectangle(){}
	Rectangle(double l, double w){
		System.out.println("length?");
		length = sc.nextDouble();
		System.out.println("width?");
		width = sc.nextDouble();
		
		sc.close();
	}
	
	public void findArea() {
		area = length * width;
		super.printArea();
	}
	
	public void findPerimeter() {
		perimeter = 2 * (length + width);
		super.printPerimeter();
	}
}
