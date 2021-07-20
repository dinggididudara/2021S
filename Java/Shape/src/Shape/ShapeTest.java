package Shape;

import java.util.Scanner;

public class ShapeTest{
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("How many shapes you have?");
		int n = sc.nextInt();
		Shape[] arr = new Shape[n];
		
		for(int i=0;i<arr.length;i++) {
			System.out.println("Shape" + (i+1) + "\nwhich is a Shape Object");
			System.out.println("1. Square\n2. Rectangle");
			int a = sc.nextInt();
		
			if(a==1) {
				arr[i] = new Square();
				
			}else if(a==2){
				arr[i] = new Rectangle();
				
			}	
		arr[i].printArea();
		arr[i].printPerimeter();
		}
		sc.close();
	}
}
