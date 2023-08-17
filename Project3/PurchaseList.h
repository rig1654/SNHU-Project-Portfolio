/*
 * PurchaseList.h
 *
 * CS-210 Project Three
 *
 * Author: Nate Riggs
 *
 * Date: 8/12/23
 */



#ifndef PROJECTTHREE_PURCHASELIST_H_
#define PROJECTTHREE_PURCHASELIST_H_


#include <string.h>
#include <map>
using namespace std;

class PurchaseList {

public:

    static void FileReadandWrite(ifstream& inFS, ofstream& outFS, std::map<string, int>& purchaseList);
    static void MenuOptions(std::map<string, int> const& purchaseList);
    static void PrintMapNumbers(std::map<string, int> const& purchaseList);
    static void PrintMapHistogram(std::map<string, int> const& purchaseList);
    static void PrintBorder();

private:
    static std::map<std::string, int> const& purchaseList;
    static std::ifstream inFS;
    static std::ofstream outFS;

}; // PROJECTTHREE_PURCHASELIST_H_

#endif