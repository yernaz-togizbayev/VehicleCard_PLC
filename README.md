# 🚗 Vehicle Management System – Java OOP Project

This project is a **vehicle management system** implemented in **Java** as part of a Programming Languages & Compilers course.  
It demonstrates **object-oriented programming principles**, **interfaces**, and **data persistence with serialization**.

---

## 📚 Project Overview

- Defines a **class hierarchy** for different types of vehicles
- Provides a **command-line interface (CLI)** to interact with the system
- Uses **DAO (Data Access Object)** pattern for data management
- Stores and retrieves vehicles through **Java serialization**

---

## ✨ Features

- Manage multiple vehicle types:
  - 🚗 Car
  - 🚌 Bus
  - 🚚 Truck
- Add, view, and remove vehicles
- Save and load data using serialization
- Extensible design for adding new vehicle types
- Separation of concerns via DAO pattern


---

## 🧩 Class Overview

- **Vehicle (abstract)**  
  Base class for all vehicles. Encapsulates common attributes such as license plate, model, etc.

- **Car, Bus, Truck**  
  Specific vehicle subclasses with extended properties.

- **VehicleDAO (interface)**  
  Defines operations for adding, removing, and listing vehicles.

- **SerializedVehicleDAO (implementation)**  
  Handles storage of vehicle objects via file serialization.

- **VehicleManagement**  
  Provides the main management logic.

- **VehicleCLI**  
  Text-based interface to interact with the user.

---

## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 11+  
- Any IDE (Eclipse, IntelliJ, VS Code)

### 2️⃣ Compile & Run
To compile:
```bash
javac *.java
```

To run:
```
java VehicleCLI
```


## 📖 Learning Outcomes

- Inheritance and polymorphism
- Interface design
- Data Access Object pattern
- Java serialization for persistence
- Command-line application design

## 📄 License

This project is for educational purposes only.

