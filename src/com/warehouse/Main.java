package com.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.warehouse.data.Package;
import com.warehouse.data.ProductModel;
import com.warehouse.data.SummaryProducModel;

public class Main {

	public static void main(String args[]) {
		List<Package> warehouseInventory = new ArrayList<Package>();

		Map<String, String> fruitAttributes = new HashMap<String, String>();
		fruitAttributes.put("nutrition", "10");
		//
		//
		Map<String, String> vegetablesAttributes = new HashMap<String, String>();
		vegetablesAttributes.put("nutrition", "10");
		vegetablesAttributes.put("produces", "Cluj");
		//
		// warehouseInventory.add(new Package("apple", "today",
		// "tomorrow",appleProduct));
		// warehouseInventory.add(new Package("pear", "today",
		// "tomorrow",pearProduct));
		// warehouseInventory.add(new Package("empty box", "", ""));
		// warehouseInventory.add(new Package("empty box", "", ""));
		// warehouseInventory.add(new Package("apple box", "today", "tomorrow",
		// "100", "1", appleProduct));
		// warehouseInventory.add(new Package("fruits", "apple", "10.10.17",
		// "20.10.17", "6", "1", fruitAttributes));
		// warehouseInventory
		// .add(new Package("vegetables", "potatoes", "10.10.17", "20.10.17",
		// "15", "10", vegetablesAttributes));
		// warehouseInventory
		// .add(new Package("vegetables", "onions", "10.10.17", "20.10.17",
		// "2.5", "4", vegetablesAttributes));
		// warehouseInventory.add(new Package("fruits", "orange", "10.02.17",
		// "20.10.17", "13", "2.5", fruitAttributes));
		// warehouseInventory.add(new Package("fruits", "peaches", "10.01.17",
		// "20.10.19", "30", "6", fruitAttributes));
		// warehouseInventory.add(new Package("others", "cracker", "10.10.17",
		// "20.10.17", "2", "Pack"));
		//
		// System.out.println(warehouseInventory);

		for (int i = 0; i < 200; i++) {
			warehouseInventory.add(randomPackageGenerator());
		}
		System.out.println("size: " + warehouseInventory.size());

		System.out.println(warehouseInventory);

	}

	public static Package randomPackageGenerator() {

		Package result = null;
		Map<String, String> fruitAttributes = new HashMap<String, String>();
		fruitAttributes.put("nutrition", "10");

		Map<String, String> vegetablesAttributes = new HashMap<String, String>();
		vegetablesAttributes.put("nutrition", "10");
		vegetablesAttributes.put("produces", "Cluj");

		int rand = new Random().nextInt(5);

		if (rand == 0) {
			String appleUnit = String.valueOf(new Random().nextInt(250 - 50) + 50);
			result = new Package("fruits", "apple", "10.10.17", "20.10.17", "6", appleUnit, fruitAttributes);
		}
		if (rand == 1) {
			String potatosUnit = String.valueOf(new Random().nextInt(25 - 15) + 15);
			result = new Package("vegetables", "potatoes", "10.10.17", "20.10.17", "15", potatosUnit,
					vegetablesAttributes);
		}
		if (rand == 2) {
			String onionsUnit = String.valueOf(new Random().nextInt(25 - 15) + 15);
			result = new Package("vegetables", "onions", "10.10.17", "20.10.17", "2.5", onionsUnit,
					vegetablesAttributes);
		}
		if (rand == 3) {
			String orangeUnit = String.valueOf(new Random().nextInt(25 - 15) + 15);
			result = new Package("fruits", "orange", "10.02.17", "20.10.17", "13", orangeUnit, fruitAttributes);
		}
		if (rand == 4) {
			String peachesUnit = String.valueOf(new Random().nextInt(60 - 30) + 30);
			result = new Package("fruits", "peaches", "10.01.17", "20.10.19", "30", peachesUnit, fruitAttributes);
		}
		if (rand == 5) {
			result = new Package("others", "cracker", "10.10.17", "20.10.17", "2", "Pack");
		}
		return result;
	}

	public void generateSummary(List<Package> warehouseList) {

		double fruitTotalQty = 0;
		double fruitTotalPrice = 0;
		double vegetaTotalQty = 0;
		double vegetaTotalPrice = 0;
		double ohterTotalQty = 0;
		double otherTotalPrice = 0;

		for (Package packageNow : warehouseList) {
			if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("fruits")) {
				SummaryProducModel fruitItem = new SummaryProducModel();
				List fruitslist = new ArrayList();
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("apple")) {
					fruitTotalQty += Integer.valueOf(packageNow.productDetails.measure);
					fruitTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;
					fruitslist.add(fruitItem);
				}
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("peaches")) {
					fruitTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 6;
					fruitTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;
					fruitslist.add(fruitItem);

				}
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("oranges")) {
					fruitTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 2.5;
					fruitTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;
					fruitslist.add(fruitItem);

				}
			}

			if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("vegetables")) {
				List vegetaList = new ArrayList();
				SummaryProducModel vegetaItem = new SummaryProducModel();
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("potatoes")) {
					vegetaTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 10;
					vegetaTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					vegetaItem.productName = packageNow.productDetails.name;
					vegetaItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					vegetaItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					vegetaItem.producTotalPrice = vegetaItem.producUnitPrice * vegetaItem.productUnit;
					vegetaList.add(vegetaItem);

				}
				if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("vegetables")) {
					if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("onions")) {
						vegetaTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 4;
						vegetaTotalPrice += Double.valueOf(packageNow.productDetails.price)
								* Integer.valueOf(packageNow.productDetails.measure);
						vegetaItem.productName = packageNow.productDetails.name;
						vegetaItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
						vegetaItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
						vegetaItem.producTotalPrice = vegetaItem.producUnitPrice * vegetaItem.productUnit;
						vegetaList.add(vegetaItem);

					}
				}
				if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("others")) {
					List othersList = new ArrayList();
					SummaryProducModel otherItem = new SummaryProducModel();
					if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("cracker")) {
						vegetaTotalQty += Integer.valueOf(packageNow.productDetails.measure);
						vegetaTotalPrice += Double.valueOf(packageNow.productDetails.price)
								* Integer.valueOf(packageNow.productDetails.measure);
						otherItem.productName = packageNow.productDetails.name;
						otherItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
						otherItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
						otherItem.producTotalPrice = otherItem.producUnitPrice * otherItem.productUnit;
						vegetaList.add(otherItem);

					}
				}
			}

		}

	}
}
