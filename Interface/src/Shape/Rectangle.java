package Shape;

public class Rectangle implements Shape{
	private int length;
	private int width;
	protected String name;
	protected double area;
	protected double perimeter;
	
	Rectangle(){}
	Rectangle(int len, int wid){
		name = "Rectangle";
		length = len;
		width = wid;
	}
	@Override
	public void findArea() {
		area=length*width;
	}
	@Override
	public void findPerimeter() {
		perimeter = 2*(length+width);
	}
	@Override
	public void printDetails() {
		System.out.println("Name: "+name+"   Area: "+area+"   Perimeter: "+perimeter);
	}
}
