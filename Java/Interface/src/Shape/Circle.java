package Shape;

public class Circle implements Shape{
	private double radius;
	protected String name;
	protected double area;
	protected double perimeter;
	
	Circle(){}
	Circle(int r){
		name = "Circle";
		radius = r;
	}
	@Override
	public void findArea() {
		area = Math.pow(radius, 2);
	}
	@Override
	public void findPerimeter() {
		perimeter = 2*Math.PI * radius;
	}
	@Override
	public void printDetails() {//Override methods from interface 'Shape' - printing everything
		System.out.printf("Name: "+ name + "   Area: "+ area + "   Perimeter: %.2f \n", perimeter);
	}
}
