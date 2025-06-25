# Gym Membership Management System

<p align="center">
  <img src="https://via.placeholder.com/700x350.png?text=Gym+Management+System+Screenshot" alt="Application Screenshot">
  <!-- TODO: Replace the placeholder URL above with a real screenshot of your application. 
       For example: <img src="assets/main-gui.png" alt="Application Screenshot"> -->
</p>

A comprehensive, desktop-based application developed in **Java Swing** to manage gym memberships. This system provides a user-friendly graphical interface (GUI) to handle member registration, track activities, and manage different membership plans, demonstrating core Object-Oriented Programming principles.

---

## 📋 Project Overview

This project is a coursework submission for the **CS4001NI: Programming** module at London Metropolitan University. The primary goal was to design and implement a robust system using Java, focusing on OOP concepts, GUI development with Swing, and fundamental programming practices like event handling, exception handling, and file I/O.

The system distinguishes between two types of members:
* **Regular Members:** Can subscribe to basic, standard, or deluxe plans.
* **Premium Members:** Have a fixed high-tier membership with additional features.

---

## ✨ Key Features

* **Dual Member Registration:** Separate, intuitive tabs for adding **Regular** and **Premium** members.
* **Dynamic Data Entry:** User-friendly components like `JComboBox` for dates and `JRadioButton` for gender selection.
* **Membership Management:** Functionality to **activate** and **deactivate** memberships.
* **Activity Tracking:** Mark member **attendance** and automatically update **loyalty points**.
* **Plan Upgrades:** Regular members can upgrade their subscription plan based on attendance eligibility.
* **Financial Operations:** Premium members can **pay due amounts** and have **discounts calculated** upon full payment.
* **Data Persistence:** **Save** all member details to a local text file (`MemberDetails.txt`) and **read** the data back into a display window.
* **User Feedback:** Interactive dialogs (`JOptionPane`) for success, error, and warning messages.
* **Dynamic GUI:** The interface dynamically updates certain fields based on user input, such as plan prices.
* **Robust Error Handling:** Implemented `try-catch` blocks to handle common runtime errors like `NumberFormatException` and `IOException`.

---

## 🛠️ Technologies & Concepts Implemented

### Core Concepts
* **Object-Oriented Programming (OOP):**
    * **Inheritance:** A base `abstract` class `GymMember` with `RegularMember` and `PremiumMember` as subclasses.
    * **Polymorphism:** The `display()` and `markAttendance()` methods are overridden in subclasses to provide specific implementations. The `ArrayList<GymMember>` holds objects of both subclass types.
    * **Encapsulation:** `private` and `protected` access modifiers are used to protect data. Public getter methods provide controlled access.
    * **Abstraction:** `GymMember` is an `abstract` class, defining a contract that all member types must follow.

* **Java Swing & AWT:**
    * A rich GUI built with components like `JFrame`, `JPanel`, `JTabbedPane`, `JButton`, and `JTextField`.
    * **Absolute Positioning** (`setLayout(null)`) used for precise control over the component layout.
    * **Look and Feel** set to the native operating system's theme for a better user experience.

* **Event-Driven Programming:**
    * `ActionListener` interface implemented to handle all user interactions with buttons and combo boxes.
    * A central `actionPerformed` method uses `ae.getSource()` to delegate tasks.

* **File Handling (Java I/O):**
    * `FileWriter` and `BufferedWriter` for efficient, buffered writing of member data to a text file.
    * `FileReader` and `BufferedReader` for efficient, buffered reading from the text file.
    * `try-with-resources` statements used for safe and automatic closing of file streams.

### Data Structures
* **`ArrayList<GymMember>`:** A dynamic array used as the primary in-memory database to store and manage all member objects.

---

## 📸 Application Screenshots

<!-- TODO: Add more screenshots or even GIFs here to showcase different features! -->
<!-- Create a folder named 'assets' in your repository and place your images there. -->

<p align="center">
  <img src="https://via.placeholder.com/400x250.png?text=Add+Regular+Member" alt="Add Regular Member" style="margin: 10px;"/>
  <img src="https://via.placeholder.com/400x250.png?text=Display+All+Members" alt="Display Members Window" style="margin: 10px;"/>
  <!-- Example: <img src="assets/add-member.gif" alt="Adding a new member"/> -->
</p>

---

## 🚀 Getting Started

To run this project, you will need to have the **Java Development Kit (JDK)** installed on your system.

### Prerequisites
* Java Development Kit (JDK) 8 or higher.

### Running the Application
1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git)
    ```

2.  **Navigate to the source directory:**
    ```sh
    cd YOUR_REPOSITORY_NAME
    ```
    *(Assuming your `.java` files are in the root of the repository. If they are in a `src` folder, `cd src`)*

3.  **Compile the Java files:**
    ```sh
    javac *.java
    ```

4.  **Run the main GUI class:**
    ```sh
    java GymGui
    ```
The application window should now appear.

---

## 🧑‍💻 Author

**[Your Name]**

* GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)
* LinkedIn: [Your LinkedIn Profile URL](https://linkedin.com/in/your-profile)

---

## 🙏 Acknowledgements

* A special thanks to **Mr. Mohit Sharma** and the faculty at **London Metropolitan University** and **Islington College** for their guidance and for providing the framework for this engaging project.