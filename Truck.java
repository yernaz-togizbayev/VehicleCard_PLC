package PLC;

public class Truck extends Vehicle {

	

	private static final long serialVersionUID = 8713643960872264041L;

	public Truck(int id, String brand, String model, int yearBuilt, double basePrice) {
		super(id, brand, model, yearBuilt, basePrice);
	}
	
	@Override
	public String getType() {
		return Truck.class.getSimpleName();
	}
	
	@Override
	double getDiscount() {
		double totalDiscount = 0.05 * getAge() * getBasePrice();
		
		if (totalDiscount > 0.2 * getBasePrice())
			return 0.2 * getBasePrice();
		
		
		return totalDiscount;
	}

	@Override
	public String toString() {
		String new_str =String.format("%-11s %s\n", "Type: ", getType()) +
				String.format("%-11s %s\n", "Id: ", getId()) +
				String.format("%-11s %s\n", "Brand: ", getBrand()) + 
				String.format("%-11s %s\n", "Model: ", getModel()) + 
				String.format("%-11s %s\n", "Year: ", getYearBuilt()) +
				String.format("Base price: %.2f\n", getBasePrice()) +
				String.format("%-11s %.2f\n", "Price: ", getPrice());
		return new_str;
	}
}