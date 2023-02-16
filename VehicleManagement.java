package PLC;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleManagement {
	private VehicleDAO vehicleDAO;
	
	public VehicleManagement(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	
	public List<Vehicle> getAllVehicles() {
		return vehicleDAO.getVehicleList();
	}
	
	public Vehicle getSpecificVehicle(int id) {
		return vehicleDAO.getVehicle(id);
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicleDAO.saveVehicle(vehicle);
	}
	
	public void deleteVehicle(int id) {
		vehicleDAO.deleteVehicle(id);
	}
	
	public long countVehicles(Class<?> vehicles) {
		return vehicleDAO.getVehicleList().stream().filter(vehicles::isInstance).count();
	}
	
	public long countCars(Class<?> cars) {		
		return vehicleDAO.getVehicleList().stream().filter(cars::isInstance).count();
	}
	
	public long countTrucks(Class<?> trucks) {		
		return vehicleDAO.getVehicleList().stream().filter(trucks::isInstance).count();
	}
	
	public long countBusses(Class<?> trucks) {		
		return vehicleDAO.getVehicleList().stream().filter(trucks::isInstance).count();
	}

	
	public double meanPrice() {
		List<Vehicle> vehicles = vehicleDAO.getVehicleList();
		double sum = 0;
		
		for (Vehicle vehicle : vehicles) {
			sum += vehicle.getPrice();
		}
		
		return sum/vehicles.size();
	}
	
	public List<Integer> oldestVehicle() {
		List<Vehicle> vehicles = vehicleDAO.getVehicleList();
		
		if(vehicles.isEmpty())
			return new ArrayList<>();
		
		Integer oldYear = vehicles.stream().min(Comparator.comparing(Vehicle::getYearBuilt)).orElseThrow(() -> new AssertionError("unreachable")).getYearBuilt();
		
		return vehicles.stream().sorted(Comparator.comparing(Vehicle::getYearBuilt)).filter(v -> oldYear.equals(v.getYearBuilt())).map(Vehicle::getId).collect(Collectors.toList());
	}
	
	public List<Vehicle> pricerange(int min, int max) {
		List<Vehicle> vehicles = vehicleDAO.getVehicleList();
		List<Vehicle> tmp = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getPrice() >= min && vehicle.getPrice() <= max)
				tmp.add(vehicle);
		}
		
		return tmp;
	}
	
	
	
}