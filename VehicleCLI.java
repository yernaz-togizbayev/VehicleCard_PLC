package PLC;

import java.util.List;

public class VehicleCLI {
	
	private enum Commands {
		SHOW("show"),
		ADD("add"),
		DEL("del"),
		COUNT("count"),
		MEANPRICE("meanprice"),
		OLDEST("oldest"),
		PRICERANGE("pricerange");
		
		private final String command;
		
		Commands(String command) {
			this.command = command;
		}
		
		public static Commands forCommand (String command) {
			for(Commands value : Commands.values()) {
				if(value.command.equals(command))
					return value;
			}
			
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		
		
	}
	
	private enum VehicleType {
		CAR("car"),
		TRUCK("truck"),
		BUS("bus");
		
		private final String vehicleType;
		
		VehicleType(String vehicleType) {

			if(vehicleType == null || vehicleType.isEmpty()) {
				throw new IllegalArgumentException("Error: Invalid parameter.");
			}
			this.vehicleType = vehicleType;
		}
		
		
		public static VehicleType typeOfVehicle (String vehicleType) {
			for(VehicleType value : VehicleType.values()) {
				if(value.vehicleType.equals(vehicleType))
					return value;
			}
			
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
	}
	
	
	
	public static void show(VehicleManagement vehicleManagement, String[] args) {
		if (args.length <= 2) {
			for (Vehicle vehicle : vehicleManagement.getAllVehicles()) {
				System.out.println(vehicle);
			}
		}
		else {
			Vehicle vehicle = vehicleManagement.getSpecificVehicle(Integer.parseInt(args[2]));
			
			if (vehicle != null)
				System.out.println(vehicle);
		}
	}
	
	public static void add(VehicleManagement vehicleManagement, String[] args) {
		VehicleType vehicleType = VehicleType.typeOfVehicle(args[2]);
		int id = Integer.parseInt(args[3]);
		String brand = args[4];
		String model = args[5];
		int yearBuilt = Integer.parseInt(args[6]);
		double basePrice = Integer.parseInt(args[7]);
		
		switch (vehicleType) {
			case TRUCK: {
				vehicleManagement.addVehicle(new Truck(id, brand, model, yearBuilt, basePrice));
				break;
			}
			
			case CAR: {
				int inspectionYear = Integer.parseInt(args[8]);
				vehicleManagement.addVehicle(new Car(id, brand, model, yearBuilt, basePrice, inspectionYear));
				break;
			}
			case BUS: {
				int inspectionYear = Integer.parseInt(args[8]);
				vehicleManagement.addVehicle(new Bus(id, brand, model, yearBuilt, basePrice, inspectionYear));
				break;
			}
		}
	}
	
	
	public static void del(VehicleManagement vehicleManagement, String[] args) {
		int id = Integer.parseInt(args[2]);
		vehicleManagement.deleteVehicle(id);
	}
	
	
	public static void count(VehicleManagement vehicleManagement, String[] args) {
		
		if (args.length <= 2)
			System.out.println(vehicleManagement.countVehicles(Vehicle.class));
		else {
			VehicleType vehicleType = VehicleType.typeOfVehicle(args[2]);
			switch (vehicleType) {
				case CAR: {
					System.out.println(vehicleManagement.countCars(Car.class));
					break;
				}
				case TRUCK: {
					System.out.println(vehicleManagement.countTrucks(Truck.class));
					break;
				}
				case BUS: {
					System.out.println(vehicleManagement.countBusses(Bus.class));
					break;
				}
				
			}
		}
	}
	
	public static void meanprice(VehicleManagement vehicleManagement) {
		System.out.println(String.format("%.2f", vehicleManagement.meanPrice()));
	}
	
	public static void oldest(VehicleManagement vehicleManagement) {
		for (int id : vehicleManagement.oldestVehicle()) {
			System.out.println("Id: " + id);
		}
	}
	
	public static void pricerange(VehicleManagement vehicleManagement, String[] args) {
		List<Vehicle> vehicles = vehicleManagement.pricerange(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		try {
			VehicleManagement vehicleManagement = new VehicleManagement(new SerializedVehicleDAO(args[0]));
			Commands Command = Commands.forCommand(args[1]);
			
			switch (Command) {
				case SHOW: {
					show(vehicleManagement, args);
					break;
				}
				
				case ADD: {
					add(vehicleManagement, args);
					break;
				}
				
				case DEL: {
					del(vehicleManagement, args);
					break;
				}
				
				case COUNT: {
					count(vehicleManagement, args);
					break;
				}
				
				case MEANPRICE: {
					meanprice(vehicleManagement);
					break;
				}
				
				case OLDEST: {
					oldest(vehicleManagement);
					break;
				}
				
				case PRICERANGE: {
					pricerange(vehicleManagement, args);
				}
			}
			
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Error: Invalid parameter.");
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}
}