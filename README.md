# Linked Lists (COMP 249 Assignment 4 - Part 2)

## Assignment Description

This program manipulates a set of records of cell phones and performs some operations on these records using linked lists.

## Requirements

* CellListUtilization (Driver) must be created
    * main()
        * Creates at least two empty lists from the CellList class
        * Open the Cell_Info.txt, read contents line by line. Use these records to initialize one of the CellList objects you created above.
            * Can simply use addToStart() method to insert the read objects into the list
            * However, the list should not have any duplicate records, so if the input file has duplicated entries, which is the case in the file provided with the assignment for instance, your code must handle this case so that each record is inserted in the list only once.
            * Show the contents of the list you just initialized
            * Prompt the user to enter a few serial numbers and search the list that you created from the input file for these values; make sure to display the number of iterations performed
            * Create enough objects to test each of the constructors/methods of your classes

* A CellPhone Class must be created
    * Attributes
        * long serialNum
        * String brand
        * int year
        * double price
    * Associated methods
        * Usual mutators and accessors
        * Parameterized constructor that initializes the four attributes
        * Copy constructor with two parameters: CellPhone object and a long value (new serial number), it is assumed that this value will correspond to the unique serial number rule
        * A clone(), method (will prompt the user to enter a new serial number)
        * toString and equals() (although serial numbers can be the same)
* Brand names is always recorded as a single word
* No two cell phones may have the same serial number
* The program shall handle a text file Cell_Info.txt which has the information of the various cell phone objects. (Has to handle zero or more records.)
* A CellList class must be created
    * Inner class called CellNode
        * Attributes
            * Cellphone object
            * CellNode node object
        * Associated methods
            * Default constructor (both is null)
            * Parameterized constructor that initializes the two attributes
            * A copy constructor (which accepts CellNode object)
            * A clone() method
    * Attributes
        * head (points to the first node in this list oject)
        * size (always indicates the current size of the list (how many nodes))
    * Associated methods
        * Default constructor
        * Copy Constructor (which accepts CellList object)
        * addToStart(CellPhone object)
            * Creates a note with that passed object and inserts this node at the head of the list
        * insertAtIndex(Cellphone object, int index)
            * If the index is not valid (value between 0 and size-1), method must throw a NoSuchElementException and terminate the program. If the index is valid, the method creates a node with the passed CellPhone object and inserts this node at the give index (method must properly handle ALL special cases)
        * deleteFromIndex(int index) throws NoSuchElementException; deletes the node pointed by that index is deleted from the list (method must properly handle ALL special cases)
        * deleteFromStart(), which deletes the first node in the list (the one pointed by head) (handle special cases)
        * find(long serialNumber); searches the list for a node with a cell phone with that serial number. If such an object is found, method returns a pointer to that node, otherwise, return null. Method must keep track of how many interations were made before the search finally finds the phone or conlcudes that it is not in the list
        * contains(long serialNumber); returns true if an object with that serial number is in the list, otherwise returns false
        * showContents(); displays the contents of the lists, in a specific formatting
        * equals(CellList object); returns true if two lists contain similar objects, otherwise returns false
* Some rules:
    * Whenever a node is added or deleted, the list size must be adjusted accordingly
    * All special cases must be handled, whether or not the method description explicitly states that
    * All clone() and copy constructors must perform a deep copy
    * If any of your methods allows a privacy leak, you must clearly place a comment at the beginning of the method
        * Indicating that this method may result in a privacy leak
        * Explaining the reason behind the privacy leak





