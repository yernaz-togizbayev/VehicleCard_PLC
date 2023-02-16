package PLC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO{
	String filename;
	
	public SerializedVehicleDAO(String filename) {
		if (filename == null || filename.isEmpty())
			throw new IllegalArgumentException("Error: Invalid parameter.");
		this.filename = filename;
		
		
	}
	
	private void serializeVehicles(List<Vehicle> vehicles) {
		File file = new File(filename);
		
		if (file.exists())
			file.delete();
		
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename, true))) {
            writer.writeObject(vehicles);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e);
            System.exit(1);
        }
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehicleList() {
		File file = new File(filename);
		
		List<Vehicle> vehicles = null;
		if (!file.exists())
			return new ArrayList<>();
		try  {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename));
			vehicles = (List<Vehicle>) reader.readObject();
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e);
            System.exit(1);
        }
		return vehicles;
	}

	@Override
	public Vehicle getVehicle(int id) {
		List<Vehicle> deserialized = getVehicleList();
		return deserialized.stream().filter(vehicle -> vehicle.getId() == id).findAny().orElse(null);
	}

	@Override
	public void saveVehicle(Vehicle vehicle) {
		List<Vehicle> deserialized = getVehicleList();
		
		if (deserialized.stream().anyMatch(v -> vehicle.getId() == v.getId())) {
			throw new IllegalArgumentException("Error: Vehicle already exists. (id=" + vehicle.getId() + ")");
		}
		deserialized.add(vehicle);
		serializeVehicles(deserialized);
	}

	@Override
	public void deleteVehicle(int id) {
		List<Vehicle> deserialized = getVehicleList();
		if (!deserialized.removeIf(vehicle -> id == vehicle.getId())) {
			throw new IllegalArgumentException("Error: Vehicle not found. (id=" + id + ")");
		}
		serializeVehicles(deserialized);
	}

}