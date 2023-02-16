package PLC;
 
 import java.io.Serializable;

public abstract class Vehicle implements Serializable {

		
		private static final long serialVersionUID = 1L;
		private int id;
		private String brand;
		private String model;
		private int yearBuilt;
		private double basePrice;

		public Vehicle(int id, String brand, String model, int yearBuilt, double basePrice) {
			if (brand == null || brand.isEmpty())
				throw new IllegalArgumentException("Error: Invalid parameter.");
			this.brand = brand;
			
			if (model == null || model.isEmpty())
				throw new IllegalArgumentException("Error: Invalid parameter.");
			this.model = model;
			
			if (yearBuilt < 1950 || yearBuilt > 2022)
				throw new IllegalArgumentException("Error: Year built invalid.");
			this.yearBuilt = yearBuilt;
			
			if (basePrice <= 0)
				throw new IllegalArgumentException("Error: Base price invalid.");
			this.basePrice = basePrice;
			
			if (id <= 0)
				throw new IllegalArgumentException("Error: Invalid parameter.");
			this.id = id;
		}
		
		public abstract String getType();
		
		public String getBrand() {
			return brand;
		}
		
		public String getModel() {
			return model;
		}
		
		public int getYearBuilt() {
			return yearBuilt;
		}
		
		public double getBasePrice() {
			return basePrice;
		}
		
		public int getId() {
			return id;
		}
		
		public int getAge() {
			return 2022 - yearBuilt;
		}
		
		abstract double getDiscount();
		
		public double getPrice() {
			return basePrice - getDiscount();
		}
		
		
		@Override
		public abstract String toString();
		
		
		
}