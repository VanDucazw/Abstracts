package advance.dev;

import java.util.Scanner;

//Lớp cơ sở trừu tượng Shape
abstract class Shape {

	// Phương thức trừu tượng để tính chu vi
	public abstract double chuVi();

	// Phương thức trừu tượng để tính diện tích
	public abstract double dienTich();
}

//Lớp Triangle kế thừa từ lớp Shape
class Triangle extends Shape {
	double a, b, c; // Các cạnh của tam giác

	public Triangle(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	// Thực thi phương thức tính chu vi của tam giác
	@Override
	public double chuVi() {
		return a + b + c;
	}

	// Thực thi phương thức tính diện tích của tam giác
	@Override
	public double dienTich() {
		double p = chuVi() / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}
}

//Lớp Circle kế thừa từ lớp Shape
class Circle extends Shape {
	double r; // Bán kính của hình tròn

	public Circle(double r) {
		this.r = r;
	}

	// Thực thi phương thức tính chu vi của hình tròn
	@Override
	public double chuVi() {
		return 2 * Math.PI * r;
	}

	// Thực thi phương thức tính diện tích của hình tròn
	@Override
	public double dienTich() {
		return Math.PI * r * r;
	}
}

//Lớp Rectangle kế thừa từ lớp Shape
class Rectangle extends Shape {
	double a, b; // Độ dài 2 cạnh của hình chữ nhật

	public Rectangle(double a, double b) {
		this.a = a;
		this.b = b;
	}

	// Thực thi phương thức tính chu vi của hình chữ nhật
	@Override
	public double chuVi() {
		return 2 * (a + b);
	}

	// Thực thi phương thức tính diện tích của hình chữ nhật
	@Override
	public double dienTich() {
		return a * b;
	}
}

public class MainApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Shape[] shapes = new Shape[3]; // chỉ nhập 3 hình
		input(shapes, scanner);
		print(shapes);
		findLargestArea(shapes);
		findLargestCircleRectangleTriangleArea(shapes);
	}

	// Phương thức nhập thông tin các hình
	public static void input(Shape[] shapes, Scanner scanner) {
		for (int i = 0; i < 3; i++) { // chỉ chạy 3 lần
			System.out.println("Nhập thông tin cho hình số " + (i + 1) + ":");
			System.out.println("Chọn loại hình (1. Tam giác, 2. Hình tròn, 3. Hình chữ nhật): ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Nhập độ dài các cạnh của tam giác:");
				double side1 = scanner.nextDouble();
				double side2 = scanner.nextDouble();
				double side3 = scanner.nextDouble();
				shapes[i] = new Triangle(side1, side2, side3);
				break;
			case 2:
				System.out.println("Nhập bán kính của hình tròn:");
				double radius = scanner.nextDouble();
				shapes[i] = new Circle(radius);
				break;
			case 3:
				System.out.println("Nhập độ dài 2 cạnh của hình chữ nhật:");
				double length = scanner.nextDouble();
				double width = scanner.nextDouble();
				shapes[i] = new Rectangle(length, width);
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
				i--; // Nếu lựa chọn không hợp lệ, yêu cầu người dùng nhập lại
			}
		}
	}

	// Phương thức in thông tin của các hình
	public static void print(Shape[] shapes) {
		System.out.println("Thông tin các hình đã nhập:");
		for (int i = 0; i < shapes.length; i++) {
			System.out.println("Hình số " + (i + 1) + ":");
			System.out.println("Chu vi: " + shapes[i].chuVi());
			System.out.println("Diện tích: " + shapes[i].dienTich());
		}
	}

	// Phương thức tìm hình có diện tích lớn nhất
	public static void findLargestArea(Shape[] shapes) {
		double maxArea = Double.MIN_VALUE;
		Shape largestShape = null;
		for (Shape shape : shapes) {
			if (shape.dienTich() > maxArea) {
				maxArea = shape.dienTich();
				largestShape = shape;
			}
		}
		System.out.println("Hình có diện tích lớn nhất là:");
		System.out.println("Diện tích: " + largestShape.dienTich());
	}

	// Phương thức tìm diện tích lớn nhất của mỗi hình tròn, chữ nhật, tam giác
	public static void findLargestCircleRectangleTriangleArea(Shape[] shapes) {
		double maxCircleArea = Double.MIN_VALUE;
		double maxRectangleArea = Double.MIN_VALUE;
		double maxTriangleArea = Double.MIN_VALUE;

		for (Shape shape : shapes) {
			if (shape instanceof Circle) {
				double area = shape.dienTich();
				if (area > maxCircleArea) {
					maxCircleArea = area;
				}
			} else if (shape instanceof Rectangle) {
				double area = shape.dienTich();
				if (area > maxRectangleArea) {
					maxRectangleArea = area;
				}
			} else if (shape instanceof Triangle) {
				double area = shape.dienTich();
				if (area > maxTriangleArea) {
					maxTriangleArea = area;
				}
			}
		}

		System.out.println("Diện tích lớn nhất của:");
		System.out.println("Hình tròn: " + maxCircleArea);
		System.out.println(

				"Hình chữ nhật: " + maxRectangleArea);
		System.out.println("Tam giác: " + maxTriangleArea);
	}
}
