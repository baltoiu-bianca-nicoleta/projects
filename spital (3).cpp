#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <conio.h>
#include <fstream>
#include <iomanip>
#include <cstring> // for strcmp

using namespace std;

/// Structure for storing patient names
struct stud {
    char fName[15];
    char lName[15];
};

/// Class Patient
class Patient {
public:
    string name;
    int age;
    string disease;
    int urgency;

    Patient(string n, int a, string d, int u) : name(n), age(a), disease(d), urgency(u) {}

    void display() const {
        cout << "Name: " << name << ", Age: " << age << ", Disease: " << disease << ", Urgency: " << urgency << endl;
    }
};

/// Class Ward
class Ward {
public:
    string domain;
    int capacity;

    Ward(string d, int c) : domain(d), capacity(c) {}

    void display() const {
        cout << "Domain: " << domain << ", Capacity: " << capacity << endl;
    }
};

/// Class WaitingList
class WaitingList {
private:
    vector<Patient> list;

public:
    void addPatient(const Patient& patient) {
        list.push_back(patient);
        sort(list.begin(), list.end(), [](const Patient& a, const Patient& b) {
            return a.urgency > b.urgency;
            });
    }

    void display() const {
        for (const auto& patient : list) {
            patient.display();
        }
    }
};

/// Class Hospital
class Hospital {
private:
    vector<Ward> wards;
    WaitingList waitingList;
    vector<Patient> patients;

public:
    void registerWard(const Ward& ward) {
        wards.push_back(ward);
    }

    void registerPatient(const Patient& patient) {
        patients.push_back(patient);
        waitingList.addPatient(patient);
    }

    void displayWards() const {
        for (const auto& ward : wards) {
            ward.display();
        }
    }

    void displayPatients() const {
        for (const auto& patient : patients) {
            patient.display();
        }
    }

    void displayWaitingList() const {
        waitingList.display();
    }

    void searchPatient(const string& name) const {
        for (const auto& patient : patients) {
            if (patient.name == name) {
                patient.display();
                return;
            }
        }
        cout << "Patient not found." << endl;
    }

    void statistics() const {
        cout << "Number of wards: " << wards.size() << endl;
        cout << "Number of patients: " << patients.size() << endl;
        /// Add other relevant statistics here
    }

    void saveData() const {
        ofstream outfile("patients.txt");
        if (outfile.is_open()) {
            for (const auto& patient : patients) {
                outfile << patient.name << " " << patient.age << " " << patient.disease << " " << patient.urgency << endl;
            }
            outfile.close();
        }
        else {
            cout << "Error opening file for writing!" << endl;
        }

        cout << " \t\t\t\t\tSaving everything... Check file! ";
    }
};

/// Function for the help section
void helpSection() {
    string question;
    cout << "Welcome to the Help Section! You can ask me anything.\n";
    cout << "Type 'exit' to return to the main menu.\n";

    while (true) {
        cout << "\nYour question: ";
        getline(cin, question);

        if (question == "exit") {
            break;
        }

        if (question == "How to register a ward?") {
            cout << "To register a ward, choose option 1 from the menu and enter the domain and capacity of the ward.";
        }
        else if (question == "How to register a patient?") {
            cout << "To register a patient, choose option 2 from the menu and enter the patient's name, age, disease, and urgency level.";
        }
        else if (question == "How to display wards?") {
            cout << "To display all wards, choose option 3 from the menu.";
        }
        else if (question == "How to display patients?") {
            cout << "To display all patients, choose option 4 from the menu.";
        }
        else if (question == "How to display the waiting list?") {
            cout << "To display the waiting list, choose option 5 from the menu.";
        }
        else if (question == "How to search for a patient?") {
            cout << "To search for a patient, choose option 6 from the menu and enter the patient's name.";
        }
        else if (question == "How to view statistics?") {
            cout << "To view hospital statistics, choose option 7 from the menu.";
        }
        else if (question == "How to save data?") {
            cout << "To save data, choose option 8 from the menu.";
        }
        else {
            cout << "Sorry, I don't understand that question. Please try again.";
        }
    }
}

/// Function for displaying the menu and managing options
void menu(Hospital& hospital) {
    int option;
    do {
        system("cls");
        cout << "\nMenu:\n";
        cout << "1. Register Ward\n";
        cout << "2. Register Patient\n";
        cout << "3. Display Wards\n";
        cout << "4. Display Patients\n";
        cout << "5. Display Waiting List\n";
        cout << "6. Search Patient\n";
        cout << "7. Statistics\n";
        cout << "8. Save Data\n";
        cout << "9. Help\n";
        cout << "10. Exit\n";
        cout << "Choose an option: ";
        cin >> option;
        cin.ignore(); // to clear the newline left by 'cin'

        switch (option) {
        case 1: {
            system("cls");
            string domain;
            int capacity;
            cout << "Domain: ";
            getline(cin, domain);
            cout << "Capacity: ";
            cin >> capacity;
            cin.ignore();
            hospital.registerWard(Ward(domain, capacity));
            _getch();
            break;
        }
        case 2: {
            system("cls");
            string name, disease;
            int age, urgency;
            cout << "Name: ";
            getline(cin, name);
            cout << "Age: ";
            cin >> age;
            cin.ignore(); // to clear the newline left by `cin`
            cout << "Disease: ";
            getline(cin, disease);
            cout << "Urgency (1-10): ";
            cin >> urgency;
            hospital.registerPatient(Patient(name, age, disease, urgency));
            _getch();
            break;
        }
        case 3:
            system("cls");
            hospital.displayWards();
            _getch();
            break;
        case 4:
            system("cls");
            hospital.displayPatients();
            _getch();
            break;
        case 5:
            system("cls");
            hospital.displayWaitingList();
            _getch();
            break;
        case 6: {
            system("cls");
            string name;
            cout << "Patient name: ";
            getline(cin, name);
            hospital.searchPatient(name);
            _getch();
            break;
        }
        case 7:
            system("cls");
            hospital.statistics();
            _getch();
            break;
        case 8:
            system("cls");
            hospital.saveData();
            _getch();
            break;
        case 9:
            system("cls");
            helpSection();
            break;
        case 10:
            system("cls");
            cout << "Exiting..." << endl;
            _getch();
            break;
        default:
            cout << "Invalid option!" << endl;
        }
    } while (option != 10);
}

// Main function for authentication and starting the program
int main() {
    system("color 3D");
    char password[20], my_password[20] = "spital";
    int i = 0;
    char ch;

    system("cls");
    cout << "PASSWORD: ";

    ch = _getch();
    while (ch != 13) {
        if (ch == 8) {
            if (i > 0) {
                cout << "\b \b";
                i--;
            }
        }
        else {
            if (i < 19) {
                password[i] = ch;
                cout << "*";
                i++;
            }
        }
        ch = _getch();
    }
    password[i] = '\0';

    if (strcmp(password, my_password) != 0) { // verify password
        cout << "\n\nIncorrect password !!!" << endl;
        cout << "You typed: " << password << endl;
        cout << "The correct password is: " << my_password << endl;
        _getch();
        return 1; // if the password is incorrect it will exit the program
    }

    cout << "\n\nThe password is correct so the program is executed!" << endl;
    _getch();

    Hospital hospital;
    menu(hospital);

    return 0;
}
