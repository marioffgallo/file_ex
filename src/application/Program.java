package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Source file: ");
		String sourceFile = sc.nextLine();
		
		File sourcePath = new File(sourceFile);
		String targetPathDir = sourcePath.getParent();
		
		boolean dirSuccess = new File(targetPathDir + "\\out").mkdir();
		
		String targetPathFile = targetPathDir + "\\out\\summary.csv";
		
		try (BufferedReader source = new BufferedReader(new FileReader(sourceFile))) {
			
			String lines = source.readLine();
			
			while (lines != null) {
				String[] listP = lines.split(",");
				
				String name = listP[0];
				Double price = Double.parseDouble(listP[1]);
				Integer quantity = Integer.parseInt(listP[2]);
				
				list.add(new Product(name, price, quantity));
				
				lines = source.readLine();
			}
			try (BufferedWriter newFile = new BufferedWriter(new FileWriter(targetPathFile))) {
				
				for(Product x : list) {
					newFile.write(x.getItemName() + "," + String.format("%.2f", x.productTotal()));
					newFile.newLine();
				}
			}
			catch (IOException e) {
				System.out.print("Error: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.out.print("Error: " + e.getMessage());
		}
		System.out.println("Finished.");
	}
}
