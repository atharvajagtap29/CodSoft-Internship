package SMS;

import java.util.Scanner;

public class StudentManagementSystem {
	private Connect databaseConnection;

	public StudentManagementSystem() {
		databaseConnection = new Connect();
	}

	// Method to add a new student to the database
	public void addStudent(Student student) {
		// Perform the database INSERT operation here using the databaseConnection
		String query = "INSERT INTO students (rollNo, name, grade, major) VALUES (" + student.getRollNo() + ", '"
				+ student.getName() + "', '" + student.getGrade() + "', '" + student.getMajor() + "')";
		try {
			databaseConnection.getStatement().executeUpdate(query);
			System.out.println("Student added successfully.");
		} catch (Exception e) {
			System.out.println("Error adding student to the database.");
			e.printStackTrace();
		}
	}

	// Method to retrieve a student by roll number from the db
	// roll number is the primary key in db.
	public Student getStudentByRollNo(int rollNo) {
		// Perform the database SELECT operation here using the databaseConnection
		String query = "SELECT * FROM students WHERE rollNo = " + rollNo;
		try {
			var resultSet = databaseConnection.getStatement().executeQuery(query);
			if (resultSet.next()) {
				Student student = new Student();
				student.setRollNo(resultSet.getInt("rollNo"));
				student.setName(resultSet.getString("name"));
				student.setGrade(resultSet.getString("grade"));
				student.setMajor(resultSet.getString("major"));
				return student;
			}
		} catch (Exception e) {
			System.out.println("Error retrieving student from the database.");
			e.printStackTrace();
		}
		return null;
	}

	// Method to update a student's information in the database
	public void updateStudent(Student student) {
		// Perform the database UPDATE operation here using the databaseConnection
		String query = "UPDATE students SET name = '" + student.getName() + "', grade = '" + student.getGrade()
				+ "', major = '" + student.getMajor() + "' WHERE rollNo = " + student.getRollNo();
		try {
			databaseConnection.getStatement().executeUpdate(query);
			System.out.println("Student information updated successfully.");
		} catch (Exception e) {
			System.out.println("Error updating student information in the database.");
			e.printStackTrace();
		}
	}

	// Method to delete a student from the database
	public void deleteStudent(int rollNo) {
		// Perform the database DELETE operation here using the databaseConnection
		String query = "DELETE FROM students WHERE rollNo = " + rollNo;
		try {
			databaseConnection.getStatement().executeUpdate(query);
			System.out.println("Student deleted successfully.");
		} catch (Exception e) {
			System.out.println("Error deleting student from the database.");
			e.printStackTrace();
		}
	}

	// Method to display all students from the database
	public void displayAllStudents() {
		// Perform the database SELECT operation to get all students using the
		// databaseConnection
		String query = "SELECT * FROM students";
		try {
			var resultSet = databaseConnection.getStatement().executeQuery(query);
			while (resultSet.next()) {
				System.out.println("Roll No: " + resultSet.getInt("rollNo") + ", Name: " + resultSet.getString("name")
						+ ", Grade: " + resultSet.getString("grade") + ", Major: " + resultSet.getString("major"));
			}
		} catch (Exception e) {
			System.out.println("Error retrieving students from the database.");
			e.printStackTrace();
		}
	}

	// Method to close the database connection
	public void closeConnection() {
		try {
			databaseConnection.getStatement().close();
			databaseConnection.getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Console-based user interface
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentManagementSystem sms = new StudentManagementSystem();

		while (true) {
			System.out.println("\n--- Student Management System ---");
			System.out.println("1. Add a new student");
			System.out.println("2. Search for a student");
			System.out.println("3. Update student information");
			System.out.println("4. Delete a student");
			System.out.println("5. Display all students");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = 0; // initialize with invalid choice
			choice = scanner.nextInt();
			
			scanner.nextLine(); // Consume the newline character after reading the integer

			// validation block
			if (choice >= 1 && choice <= 6) {

				switch (choice) {
				case 1:
					System.out.print("Enter name: ");
					String name = scanner.nextLine();
					System.out.print("Enter roll number: ");
					int rollNumber = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character after reading the integer
					System.out.print("Enter grade: ");
					String grade = scanner.nextLine();
					System.out.print("Enter major: ");
					String major = scanner.nextLine();

					Student newStudent = new Student();
					newStudent.setRollNo(rollNumber);
					newStudent.setName(name);
					newStudent.setGrade(grade);
					newStudent.setMajor(major);

					sms.addStudent(newStudent);
					break;

				case 2:
					System.out.print("Enter the roll number of the student to search: ");
					int rollNumberToSearch = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character after reading the integer

					Student searchedStudent = sms.getStudentByRollNo(rollNumberToSearch);
					if (searchedStudent != null) {
						System.out.println("Student found: Roll No: " + searchedStudent.getRollNo() + ", Name: "
								+ searchedStudent.getName() + ", Grade: " + searchedStudent.getGrade() + ", Major: "
								+ searchedStudent.getMajor());
					} else {
						System.out.println("Student with the given roll number not found.");
					}
					break;

				case 3:
					System.out.print("Enter the roll number of the student to update: ");
					int rollNumberToUpdate = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character after reading the integer

					Student studentToUpdate = sms.getStudentByRollNo(rollNumberToUpdate);
					if (studentToUpdate != null) {
						System.out.print("Enter new name: ");
						String newName = scanner.nextLine();
						System.out.print("Enter new grade: ");
						String newGrade = scanner.nextLine();
						System.out.print("Enter new major: ");
						String newMajor = scanner.nextLine();

						studentToUpdate.setName(newName);
						studentToUpdate.setGrade(newGrade);
						studentToUpdate.setMajor(newMajor);

						sms.updateStudent(studentToUpdate);
					} else {
						System.out.println("Student with the given roll number not found.");
					}
					break;

				case 4:
					System.out.print("Enter the roll number of the student to delete: ");
					int rollNumberToDelete = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character after reading the integer

					sms.deleteStudent(rollNumberToDelete);
					break;

				case 5:
					sms.displayAllStudents();
					break;

				case 6:
					sms.closeConnection();
					System.out.println("Exiting the application.");
					scanner.close();
					System.exit(0);

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} else {
				System.out.println("Sorry! You have entered an invalid choice");
			}
		}
	}
}
