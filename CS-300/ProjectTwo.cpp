//============================================================================
// Name        : Project Two.cpp
// Author      : Nate Riggs
// Version     : 1.0
// Copyright   : Copyright � 2023 SNHU COCE
// Description : CS 300 Project Two CPP file using Binary Search Tree
//============================================================================

#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <sstream>
#include <cstring>
#include <algorithm> // for transform method
#include <cctype> // for tolower() method
#include <unordered_set> // for courseIds in the loadData() function

using namespace std;


/*
* Structure to be used to store course information
*/
struct Course {
    string courseId;
    string courseName;
    vector<string> prereqList;
};

class BinarySearchTree {

private:
    // Define structures to hold courses
    struct Node {
        Course course;
        Node* right;
        Node* left;

        // default constructor
        Node() {
            left = nullptr;
            right = nullptr;
        }

        // initialize with a course
        Node(Course newCourse) {
            course = newCourse;
            left = nullptr;
            right = nullptr;
        }
    };

    Node* root;
    void inOrder(Node* node);
    int size = 0;

public:
    BinarySearchTree();
    void InOrder();
    void Insert(Course newCourse);
    Course Search(string courseId);
    int Size();
};

BinarySearchTree::BinarySearchTree() {
    this->root = nullptr;
}

void BinarySearchTree::InOrder() {

    //Traverse tree in order

    inOrder(root);
}

void BinarySearchTree::Insert(Course aCourse) {

    Node* curNode = root; //set curNode as root

    if (root == NULL) { //if BST is empty

        root = new Node(aCourse); //create root using course provided

    }

    else { //root already exists

        while (curNode != NULL) { //until an empty node is found

            if (aCourse.courseId < curNode->course.courseId) { //courseId of passsed course is less than courseId at curNode

                if (curNode->left == nullptr) { //left pointer is empty

                    curNode->left = new Node(aCourse); //set new Node at left pointer
                    curNode = NULL; //set as Null and end loop
                }
                else {

                    curNode = curNode->left; // set curNode as left pointer to continue loop
                }
            }
            else {

                if (curNode->right == nullptr) { //right pointer is empty

                    curNode->right = new Node(aCourse); //set new Node at right pointer
                    curNode = NULL; //set as Null and end loop
                }
                else {

                    curNode = curNode->right; //Set curNode as right pointer to continue loop
                }
            }
        }
    }
    size++;
}

/*
* Function to convert a string all to lower case 
* 
* Source for transform() function: https://cplusplus.com/reference/algorithm/transform/
*/
string toLowerCase(const string& str) {
    string lowerStr = str;
    transform(lowerStr.begin(), lowerStr.end(), lowerStr.begin(), ::tolower); // Convert each character to lowercase
    return lowerStr;
}

/*
* Function to search for a single course
*/
Course BinarySearchTree::Search(string courseId) {
    Course aCourse;

    Node* curNode = root; //set curNode pointer to root for traversal

    string lowerCourseId = toLowerCase(courseId); //convert to lower case for comparison

    while (curNode != NULL) {

        // Convert the courseId of the current node to lowercase for comparison
        string lowerCurCourseId = toLowerCase(curNode->course.courseId);

        if (lowerCurCourseId == lowerCourseId) { //courseId is found
            return curNode->course;
        }
        else if (lowerCourseId < lowerCurCourseId) { //courseId passed is less than curNode's courseId
            curNode = curNode->left; //set curNode as left pointer to continue traversal
        }
        else {
            curNode = curNode->right; //set curNode as right pointer to continue traversal
        }
    }
    return aCourse;
}

/*
* Function to display a specific course and its information/prerequisites to the screen
*/
void printCourse(Course aCourse) {
    cout << "Course ID: " << aCourse.courseId << endl;
    cout << "Course Name: " << aCourse.courseName << endl;

    cout << "Prerequisites: ";

    if (aCourse.prereqList.empty()) { //No prerequisites
        cout << "None" << endl;
    }
    else { //Prerequisites exist
        for (int i = 0; i < aCourse.prereqList.size(); ++i) {
            cout << aCourse.prereqList[i]; //Output prerequisite to screen
            if (i != aCourse.prereqList.size() - 1) {
                cout << ", "; //used for presentation
            }
        }
    }
}

/*
* Function to display courses in BinarySearchTree sorted alphanumerically by the course Id. Startng at courseId with lowest value and increasing
*/
void BinarySearchTree::inOrder(Node* node) {


    //Return if node is null
    if (node == nullptr) { 
        return;
    }

    //Print all courses sorted alphanumerically by courseId
    inOrder(node->left);

    cout << "Course Id: " << node->course.courseId << "\n";
    cout << "Course Name: " << node->course.courseName << "\n";
    cout << "Prerequisites: ";

    if (node->course.prereqList.empty()) { // Course has no prerequisites
        cout << "None";
    }
    else { // Course has prerequisites
        for (int i = 0; i < node->course.prereqList.size(); ++i) {
            cout << node->course.prereqList[i];
            if (i != node->course.prereqList.size() - 1) { // Outputs prerequisite with a comma after until the last prerequisite is printed
                cout << ", ";
            }
        }
    }

    cout << "\n" << endl;
    inOrder(node->right); // Recursively call in Order on the right side of the tree
}

/*
* Function to open CSV file and load the data into the BinarySearchTree
*/

void loadData(string csvPath, BinarySearchTree* courseList) {

    unordered_set<string> courseIds; //used for validating prerequisites
    ifstream infile(csvPath);
    string line;

    if (infile.is_open()) { // check to see if file is open
        while (getline(infile, line)) { //while there are lines in the file
            stringstream ss(line); // used for parsing line
            string courseId;

            getline(ss, courseId, ',');
            courseIds.insert(courseId); //adds courseid to courseIds list to validate prerequisites
        }
        infile.close();
    }
    else { //File is unable to open
        cerr << "Unable to open file. Please validate file name and run program again.\n" << endl;
        exit(0); //Exit program
    }


    cout << "\nLoading courses into program..." << endl;


    infile.open(csvPath); //attempt to open file

    if (infile.is_open()) {
        while (getline(infile, line)) {
            stringstream ss(line);
            string courseId; //to store courseId
            string courseName; //to store courseName

            getline(ss, courseId, ','); //assign courseId with courseId found
            getline(ss, courseName, ','); //assign course name with courseName found

            if ((courseId.empty()) || (courseName.empty())) { //Either courseId or courseName is blank
                cout << "Invalid entry. Skipping line..." << endl;;
                continue;
            }

            Course newCourse; //Create new course struct
            newCourse.courseId = courseId; //Assign courseId in struct with courseId found
            newCourse.courseName = courseName; //Assign course name in struct with course name found

            string prereq; //Used to store prerequisite
            while (getline(ss, prereq, ',')) {// while a prerequisite exists
                if (!prereq.empty() && courseIds.find(prereq) != courseIds.end()) { //Prerequisite found and is valid
                    newCourse.prereqList.push_back(prereq);
                }

                else if (prereq.empty()) { //if there are no prerequisites
                    continue;
                }
                else { //Prerequisite found but not found as a regular course in file
                    cout << "Prerequisite" << prereq << " for " << courseId << " is not valid. Skipping prerequisite." << endl;
                    continue;
                }
            }
            courseList->Insert(newCourse); //Insert new course struct into bst
        }
    }
    infile.close(); //Close file when done
}

/*
* Fuction to return size of BST
*/
int BinarySearchTree::Size() {

    return size;
}

int main() {

   string csvPath; //Initialize variable to store filename
   string userSearch; //Initialize variable used for user search for Search() function

   cout << "Welcome to the course planner.\n" << endl;

   cout << "Please enter the name of the file that contains the course data:" << endl;

   getline(cin, csvPath);

   BinarySearchTree* courseList;
   courseList = new BinarySearchTree();
   Course course;
   int userChoice = 0;
   bool validInput = true; //boolean for detecting if user input is valid

    while (userChoice != 9) {
        cout << "Menu:" << endl;
        cout << "  1. Load Data Structure." << endl;
        cout << "  2. Print Course List." << endl;
        cout << "  3. Print Course." << endl;
        cout << "  9. Exit\n" << endl;
        cout << "What would you like to do? ";

             try { //try-catch to check for invalid input
            cin >> userChoice;

            if ((userChoice > 0 && userChoice < 4) || (userChoice == 9)) { //user enters valid input
                validInput = true;
            }

            else { //user enters invalid input
                validInput = false;
                throw 1;
            }
            switch (userChoice) {

            case 1: //Load data into BST

                      
                //Load courses into the BST
                loadData(csvPath, courseList);

                cout << courseList->Size() << " courses loaded.\n" << endl;

                cout << "Returning to menu...\n" << endl;
                break;

            case 2: //Display all courses
   
                cout << "\nHere is a sample schedule:\n" << endl;
                courseList->InOrder(); // Display all courses sorted alphabetically

                cout << "Returning to menu...\n" << endl;
                break;

            case 3: //Display specific course
                cout << "What course do you want to know about? ";
                cin >> userSearch; //Ask for user input for specific course

                cout << "\n";

               course = courseList->Search(userSearch);

               if (!course.courseId.empty()) { //CourseId is found
                   printCourse(course);
               }
               else { //CourseId is not found
                   cout << "\nCourse ID " << userSearch << " is not found." << endl;
               }
                cout << "\n\nReturning to menu...\n" << endl;

                break;

            default:
                cout << "\nThank you for using the course planner!" << endl;
                exit(0);

            }


        }
        catch (int error) {
            cout << "\nYou have entered an invalid input. Please check your input...\n" << endl;
        }

       
    }
    return 0;
}