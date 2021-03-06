package com.warehouse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.warehouse.data.Package;
import com.warehouse.data.SummaryDataModel;
import com.warehouse.data.SummaryProducModel;

public class WarehouseOperations {

	public static Package randomPackageGenerator() {

		Package result = null;
		Map<String, String> fruitAttributes = new HashMap<String, String>();
		fruitAttributes.put("nutrition", "10");

		Map<String, String> vegetablesAttributes = new HashMap<String, String>();
		vegetablesAttributes.put("nutrition", "10");
		vegetablesAttributes.put("produces", "Cluj");

		int rand = new Random().nextInt(6);

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
			result = new Package("others", "cracker", "10.10.17", "20.10.17", "2",
					String.valueOf(new Random().nextInt(10)));
		}
		return result;
	}

	public static SummaryDataModel generateSummary(List<Package> warehouseList) {

		SummaryDataModel result = new SummaryDataModel();

		for (Package packageNow : warehouseList) {
			if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("fruits")) {
				SummaryProducModel fruitItem = new SummaryProducModel();

				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("apple")) {
					fruitItem.producUnitTotal = Integer.valueOf(packageNow.productDetails.measure);
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;

				}
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("peaches")) {
					fruitItem.producUnitTotal = Integer.valueOf(packageNow.productDetails.measure) * 6;
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;

				}
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("orange")) {
					fruitItem.producUnitTotal = Integer.valueOf(packageNow.productDetails.measure) * 2.5;
					fruitItem.productName = packageNow.productDetails.name;
					fruitItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					fruitItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					fruitItem.producTotalPrice = fruitItem.producUnitPrice * fruitItem.productUnit;
				}

				result.fruitTotalQty += Double.valueOf(fruitItem.producUnitTotal);
				result.fruitTotalPrice += Double.valueOf(fruitItem.producTotalPrice);
				result.fruitslist.add(fruitItem);
			}

			if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("vegetables")) {
				SummaryProducModel vegetaItem = new SummaryProducModel();
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("potatoes")) {
					result.vegetaTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 10;
					result.vegetaTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					vegetaItem.productName = packageNow.productDetails.name;
					vegetaItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					vegetaItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					vegetaItem.producTotalPrice = vegetaItem.producUnitPrice * vegetaItem.productUnit;

				}
				if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("vegetables")) {
					if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("onions")) {
						result.vegetaTotalQty += Integer.valueOf(packageNow.productDetails.measure) * 4;
						result.vegetaTotalPrice += Double.valueOf(packageNow.productDetails.price)
								* Integer.valueOf(packageNow.productDetails.measure);
						vegetaItem.productName = packageNow.productDetails.name;
						vegetaItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
						vegetaItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
						vegetaItem.producTotalPrice = vegetaItem.producUnitPrice * vegetaItem.productUnit;

					}
				}
				result.vegetaList.add(vegetaItem);

			}

			if (packageNow.productDetails.category.toLowerCase().trim().contentEquals("others")) {
				SummaryProducModel otherItem = new SummaryProducModel();
				if (packageNow.productDetails.name.toLowerCase().trim().contentEquals("cracker")) {
					result.ohterTotalQty += Integer.valueOf(packageNow.productDetails.measure);
					result.otherTotalPrice += Double.valueOf(packageNow.productDetails.price)
							* Integer.valueOf(packageNow.productDetails.measure);
					otherItem.productName = packageNow.productDetails.name;
					otherItem.producUnitPrice = Double.valueOf(packageNow.productDetails.price);
					otherItem.productUnit = Double.valueOf(packageNow.productDetails.measure);
					otherItem.producTotalPrice = otherItem.producUnitPrice * otherItem.productUnit;

				}
				result.otherlist.add(otherItem);

			}

		}
		return result;
	}

	public static double calculateDiscount(String expirationDate, String category) throws ParseException {
		double discount = 0;
		SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy");
		SimpleDateFormat diff = new SimpleDateFormat("dd");
		Date expiration = df.parse(expirationDate);
		Date currentDate = new Date();
		
		System.out.println(expiration + " -- " + currentDate );
		
		
		long diffDays = ((new java.util.Date()).getTime() - expiration.getTime()) / (1000 * 60 * 60 * 24);
		

		System.out.println(diffDays);

		if (category.contains("fruit")) {

		}

		if (category.contains("vegetable")) {

		}

		return discount;
	}

	public static void main(String args[]) throws ParseException {
		calculateDiscount("21.08.2017", "fruit");
	}

}
