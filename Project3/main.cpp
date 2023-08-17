/*
 * main.cpp
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


using namespace std;

#include "PurchaseList.h"

int main() {
    map<string, int> purchaseList;
    ifstream inFS;
    ofstream outFS;

    cout << "Welcome to the Corner Grocer Inventory System." << endl << endl; // Initial start of program and shows message
    PurchaseList::FileReadandWrite(inFS, outFS, purchaseList); // Reads text file, creates backup file, and creates map with different key-value pairs
    PurchaseList::MenuOptions(purchaseList); // Prints Menu Options to allow user to progress through program

    return 0;
}