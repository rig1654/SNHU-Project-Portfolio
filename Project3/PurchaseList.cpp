/*
 * PurchaseList.cpp
 *
 * CS-210 Project Three
 * 
 * Author: Nate Riggs
 * 
 * Date: 8/12/23
 */




#include <iostream>
#include <sstream>
#include <fstream>
#include <string>
#include <stdio.h>
#include <string>
#include <map>


#include "PurchaseList.h"



/*
 * Function that will receive user input for a filename, read that file, create a map with item names as keys and the quantity of the item as values in the map.
 * After map creation, function creates a backup frequency.dat file with the item names and quantities as they appear in the original file.
 * Returns to menu after execution
 */

void PurchaseList::FileReadandWrite(ifstream& inFS, ofstream& outFS, map<string, int>& purchaseList) {
    string fileName; // String to enter file name for fstream

    cout << "Please enter the name of the file you would like to open and read: " << endl;
    cin >> fileName; // Test is "CS210_Project_Three_Input_File.txt" but any file name could work if file is present
    cout << endl;
    inFS.open(fileName);


    if (!inFS.is_open()) { // If Input file is unable to be opened
        cout << "Could not open file " + fileName << endl;
        cout << "Ending Program." << endl << endl;
        exit(0);
    }
    string next; // Used for map creation
    while (inFS >> next) { // Reads next line from file
        purchaseList[next]++; // Increases value in key/value pair for that item
    }
    inFS.close(); // Close file

    outFS.open("frequency.dat");

    if (!outFS.is_open()) { // If unable to open output file
        cout << "Cout not open file frequency.dat" << endl;
        exit(0);
    }

    for (auto it = purchaseList.cbegin(); it != purchaseList.cend(); ++it) {
        outFS << (*it).first << ": " << (*it).second << endl; // Writes to key and value to output file
    }
}

/*
 *  Function that outputs the list's items and quantity of those items (map's key/value pairs) in a numerical format
 * Returns to menu after execution
 */
void PurchaseList::PrintMapNumbers(std::map<string, int> const& purchaseList) {
    for (auto it = purchaseList.cbegin(); it != purchaseList.cend(); ++it) {
        cout << (*it).first << ": " << (*it).second << endl;
    }
    cout << endl;
    cout << "Returning to Menu." << endl;
}


/*
* Function that outputs the list's items and quantity of those items (map's key/value pairs) in a histogram format where
* the character * is used for each occurence of that specific item
* Function returns to menu after execution
*/
void PurchaseList::PrintMapHistogram(std::map<string, int> const& purchaseList) {
    char histogramChar; // Variable to allow user to select what character they would like for the histogram output

    cout << "Please input a character that you would like to use for the histogram: " << endl;
    cin >> histogramChar; 
    cout << "You selected '" << histogramChar << "' as the Histogram character.\nOutputting Histogram with that character." << endl << endl;

    for (auto it = purchaseList.cbegin(); it != purchaseList.cend(); ++it) {
        cout << (*it).first << " ";
        for (int i = 0; i < (*it).second; ++i) {
            cout << histogramChar;
        }
        cout << endl;
    }
    cout << endl << endl;
    cout << "Returning to Menu." << endl << endl;

}

/*
 * Function Prints Border around options for aesthetics
 */
void PurchaseList::PrintBorder() {
    char menu_char = '-';
    for (int i = 0; i < 60; ++i) {
        cout << menu_char;
    }
}

/* Function that displays menu options for the user and prompts them to make a selection
*  Input is validated if entry is less than 1 or more than 4. If either or, user is then prompted for input again
*/
void PurchaseList::MenuOptions(std::map<string, int> const& purchaseList) {
    int userInput;
    string userString;
    PrintBorder();
    cout << endl;
    cout << "Press the number for what you would like to do:" << endl << endl;
    cout << "1: Item Search" << endl;
    cout << "2: Print List of Items Purchased and Quantity Purchased (Number)" << endl;
    cout << "3: Print List of Items Purchased and Quantity Purchased (Histogram)" << endl;
    cout << "4: Quit Program" << endl;
    PrintBorder();
    cout << endl;


    cin >> userInput;

    while (!cin) { // While loop for validation to ensure user input is an integer and not a character
        char dummy; // Dummy Character to clear input and allow more user input
        cin.clear();
        cin >> dummy;
        cout << "\nYou have entered an entry that is not an integer. Please try again." << endl;
        cout << endl;
        cout << "Press the number for what you would like to do:" << endl << endl;
        cout << "1: Item Search" << endl;
        cout << "2: Print List of Items Purchased and Quantity Purchased (Number)" << endl;
        cout << "3: Print List of Items Purchased and Quantity Purchased (Histogram)" << endl;
        cout << "4: Quit Program" << endl;
        cin >> userInput;
    }

    while (!((userInput >= 1) and (userInput <= 4))) { // While loop for if userInput is less than 1 or more than 4, keep prompting user for input
        cout << "\nYou have entered an invalid entry. Please try again." << endl;
        cout << endl;
        cout << "Press the number for what you would like to do:" << endl << endl;
        cout << "1: Item Search" << endl;
        cout << "2: Print List of Items Purchased and Quantity Purchased (Number)" << endl;
        cout << "3: Print List of Items Purchased and Quantity Purchased (Histogram)" << endl;
        cout << "4: Quit Program" << endl;
        cin >> userInput;
    }

    switch (userInput) {
    case 1:
        try { // Try/Catch if user searches for item that is not in list. If item is NOT in list, throw an out_of_range exception
            cout << "You have selected to search for an item and return the quantity purchased." << endl;
            cout << "Please enter in the item that you would like to search for: " << endl;
            cin >> userString;
            if (!purchaseList.count(userString)) {
                throw out_of_range("Invalid Entry. Item is not in list."); // If item is not in map, throw out of range exception
            }
            cout << "You are searching for " << userString << endl;
            cout << "The number of " << userString << " purchased is " << purchaseList.at(userString) << endl;
            break; // Breaks loop to prevent other cases from inadvertently executing
        }
        catch (out_of_range& excpt) { // Breaks loop if out of range exception thrown by searching for item that does not exist in map, i.e. was not purchased that day
            cout << excpt.what() << endl;
            cout << "You have searched for an item that was not purchased." << endl;
            break; // Breaks loop to prevent other cases from inadvertently executing
        }

    case 2: // User inputs 2, prints key and value combinations in map
        cout << "\nYou have selected to print the list of items and the quantity purchased." << endl << endl;
        PrintMapNumbers(purchaseList);
        break; // Breaks loop to prevent other cases from inadvertently executing

    case 3: // If user enters 3, prints key and value combinations in map, in histogram format with quantity (value) being showing by a * symbol
        cout << "\nYou have selected to print the list of items and the quantity purchased in the format of a historgram." << endl << endl;;
        PrintMapHistogram(purchaseList);
        break; // Breaks loop to prevent other cases from inadvertently executing

    default: // Ends program with message to user
        cout << "\nYou have selected to quit the program." << endl;
        cout << "Goodbye." << endl;
        exit(0);
        break; // Breaks loop to prevent other cases from inadvertently executing
    }
    MenuOptions(purchaseList); // Displays menu options again after function is executed
}
