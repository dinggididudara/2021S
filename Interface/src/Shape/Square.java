package Shape;

public class Square implements Shape{
	private double side;
	protected String name;
	protected double area;
	protected double perimeter;
	
	@Override
	public void findArea() {
		area = side * side;
	}
	
	@Override
	public void findPerimeter() {
		perimeter = 4 * side;
	}
	
	@Override
	public void printDetails() {
		System.out.println("Name: "+ name + "   Area: "+ area + "   Perimeter: "+ perimeter);
	}
}
