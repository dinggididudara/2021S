package Shape;

import java.util.Scanner;

public class ShapeTest {
	public static void main(String[] args) {
		System.out.println("How many shapes you have?");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("What kind of shape is it?\n1.Rectangle \n2.Circle");
			int k = sc.nextInt();
			if(k==1) {
				System.out.println("What is length?");
				int l = sc.nextInt();
				System.out.println("What is width?");
				int w = sc.nextInt();
				Rectangle r = new Rectangle(l,w);
				r.findArea();
				r.findPerimeter();
				r.printDetails();
			}else if(k==2){
				System.out.println("What is radius?");
				int ri = sc.nextInt();
				Circle c = new Circle(ri);
				c.findArea();
				c.findPerimeter();
				c.printDetails();
			}
		}
	}
}
