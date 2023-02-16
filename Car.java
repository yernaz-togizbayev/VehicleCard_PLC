package PLC;


public class Car extends Vehicle {
	
	
	private static final long serialVersionUID = 1528372978335788542L;
	private int lastInspection;
	
	public Car(int id, String brand, String model, int yearBuilt, double basePrice, int lastInspection) {
		super(id, brand, model, yearBuilt, basePrice);
		
		if (lastInspection < yearBuilt || lastInspection > 2022)
			throw new IllegalArgumentException("Error: Inspection year invalid.");
		this.lastInspection = lastInspection;
	}
	
	public int getInspection() {
		return lastInspection;
	}
	
	@Override
	public String getType() {
		return Car.class.getSimpleName();
	}

	@Override
	double getDiscount() {
		double totalDiscount = 0.05 * getAge() * getBasePrice();
		
		if (lastInspection > getYearBuilt())
			totalDiscount *= 0.02 * getBasePrice();
		
		if (totalDiscount > 0.15 * getBasePrice())
			return 0.15 * getBasePrice();
		
		
		return totalDiscount;
	}

	@Override
	public String toString() {
		String new_str =String.format("%-11s %s\n", "Type: ", getType()) +
				String.format("%-11s %s\n", "Id: ", getId()) +
				String.format("%-11s %s\n", "Brand: ", getBrand()) + 
				String.format("%-11s %s\n", "Model: ", getModel()) + 
				String.format("%-11s %s\n", "Year: ", getYearBuilt()) +
				String.format("Inspection: %s\n", getInspection()) +
				String.format("Base price: %.2f\n", getBasePrice()) +
				String.format("%-11s %.2f\n", "Price: ", getPrice());
		return new_str;
	}
}