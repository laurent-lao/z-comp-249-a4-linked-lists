// -----------------------------------------------------
// Assignment 4
// Part: 2
// Written by: Laurent (40020483)
// This assignment is meant practice the use of LinkedList
// Read through a list of cellphone information and create
// a LinkedList with that information
// -----------------------------------------------------

import java.util.NoSuchElementException;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 8 2019
 * CellList Class
 */

public class CellList {

	/**
	 * inner CellNode Class
	 * Privacy leak: CellNode class is available for direct use anywhere in the CellList class, but not outside that class.
	 */
	private class CellNode {

		CellPhone cellphone;
		CellNode  nextNode;

		/**
		 * Default constructor
		 */
		public CellNode() {
			cellphone = null;
			nextNode = null;
		}

		/**
		 * Parameterized constructor
		 *
		 * @param cellphone object
		 * @param nextNode  object
		 */
		public CellNode(CellPhone cellphone, CellNode nextNode) {
			this.cellphone = cellphone;
			this.nextNode = nextNode;
		}

		/**
		 * Copy Constructor
		 *
		 * @param cellNode object to be copied
		 */
		public CellNode(CellNode cellNode) {
			this.cellphone = cellNode.cellphone;
			this.nextNode = cellNode.nextNode;
		}

		/**
		 * Clone method
		 *
		 * @return cloned CellNode
		 */
		public CellNode clone() {

			// Grabs the current cellphone's serial number and make a copy of the cellphone using that SN
			return new CellNode(new CellPhone(this.cellphone, this.cellphone.getSerialNumber()), this.nextNode);
		}

		public boolean equals(Object o)
		{
			if (this == o)
			{
				return true;
			}
			if (o == null || getClass() != o.getClass())
			{
				return false;
			}
			CellNode cellNode = (CellNode) o;
			if (nextNode == null || cellNode.nextNode == null)
			{
				return nextNode == cellNode.nextNode;
			}
			else
			{
				return cellphone.equals(cellNode.cellphone) && nextNode.equals(((CellNode) o).nextNode);
			}
		}
	}

	private CellNode head;
	private int      size = 0;

	/**
	 *  CellList Default constructor
	 */
	public CellList() {
		head = null;
	}

	/**
	 * CellList copy constructor
	 * @param cellList cellList to be copied
	 */
	public CellList(CellList cellList)
	{
		// Initialize a pointer to the list to be copied's head
		CellNode nodeToBeCopied = cellList.head;

		for (int i = 0; i < cellList.size; i++)
		{
			if (nodeToBeCopied != null)
			{
				add(nodeToBeCopied.cellphone);
				nodeToBeCopied = nodeToBeCopied.nextNode;
			}
		}
	}

	/**
	 * Add a cellphone to the start of the Linked list
	 * @param cellPhone Cellphone object to be added
	 */
	public void addToStart(CellPhone cellPhone) {
		// Add the new node to the head, assign previous head as the nextNode
		head = new CellNode(cellPhone, head);
		size++;

	}

	/**
	 * Insert the cell phone at an index
	 * @param cellPhone Cellphone object to be added
	 * @param index the index where to add the cellphone
	 */
	public void insertAtIndex(CellPhone cellPhone, int index) {
		if (size == 0)
		{
			addToStart(cellPhone);
		}
		else
		{
			// Get related nodes
			CellNode nodeBeforeIndex = nodeAtIndex(index - 1, index);
			CellNode nodeAtIndex     = nodeBeforeIndex.nextNode;

			// Create new node with nodeAtIndex as its nextNode
			CellNode nodeToInsert = new CellNode(cellPhone, nodeAtIndex);
			size++;

			// Point previous node to new node
			nodeBeforeIndex.nextNode = nodeToInsert;

			// Prevent leaks
			nodeBeforeIndex = null;
			nodeAtIndex = null;
			nodeToInsert = null;

			return;
		}
	}

	/**
	 * Deletes the node at a certain index
	 * @param index an int representing the index of the node to delete
	 */
	public void deleteFromIndex(int index) {
		try
		{
			// Checks if there are any elements to delete
			if (size == 0)
			{
				throw new NullPointerException();
			}

			// Get the node right before the one at the index
			CellNode nodeBeforeIndex = nodeAtIndex(index - 1, index);

			// Skip the next node and go to the one after; assign it to that previous node
			nodeBeforeIndex.nextNode = nodeBeforeIndex.nextNode.nextNode;
			size--;

			// Preventing privacy leaks
			nodeBeforeIndex = null;
			return;

		} catch (NullPointerException e)
		{
			System.out.println("Error: the list is of size 0, can't delete element.");
			System.exit(0);
		}
	}

	/**
	 * Deletes a node from the start
	 */
	public void deleteFromStart() {
		if (size > 1)
		{
			head = head.nextNode;
			size--;
		}
		else if (size == 1)
		{
			head = null;
			size = 1;
		}
		else
		{
			System.out.println("No more elements to delete");
		}
	}

	/**
	 * Replace the cellphone at a particular index
	 * @param cellPhone Cellphone object to replace with
	 * @param index int representing the index at which the node is replaced
	 */
	public void replaceAtIndex(CellPhone cellPhone, int index)
	{
		if (index < 0 || index >= size)
		{
			System.out.println("No element to replace.");
			return;
		}
		else
		{
			// Navigate to node at index
			CellNode nodeAtIndex = nodeAtIndex(index -1 , index);

			// Replace that node
			nodeAtIndex.nextNode = new CellNode(cellPhone, nodeAtIndex.nextNode);

		}
	}

	/**
	 * This method does not have a privacy leak because it is private.
	 * The method is used by both searchFor as well as contains.
	 * @param serialNumber to search for
	 * @return
	 */
	private CellNode find(long serialNumber) {
		int      searchCounter = 1;
		CellNode nodeToCheck   = head;

		while (nodeToCheck != null)
		{
			System.out.println("Search iteration #" + searchCounter);
			// Check if it is equal, return the nodeToCheck if true
			if (nodeToCheck.cellphone.getSerialNumber() == serialNumber)
			{
				return nodeToCheck;
			}
			else
			{
				// Go to next node
				nodeToCheck = nodeToCheck.nextNode;
				searchCounter++;
			}
		}

		// If it hits a null node (the end)
		return null;
	}

	/**
	 * Prints the information of the cellphone if it is found
	 * @param serialNumber The serial number to look for
	 */
	public void searchFor(long serialNumber) {
		System.out.println("\nSearching for SN# " + serialNumber + " in list.");
		CellNode node = find(serialNumber);

		if (node != null)
		{
			System.out.println("Found matching serial number.\nHere's the information of the cellphone:");
			System.out.println("\n" + node.cellphone);
		}
		else
		{
			System.out.println("No matching serial number found.\n");
		}
	}

	/**
	 *
	 * @param serialNumber
	 * @return
	 */
	public boolean contains(long serialNumber) {
		if (find(serialNumber) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Show the content of the list
	 */
	public void showContents() {

		System.out.println("\nPrinting CellList...");
		if (head != null)
		{
			int      nodeCounter = 0;
			CellNode currentNode = head;

			// Printing header
			String message = "The current list is " + size + ". Here are the contents of the list";
			System.out.print("\n" + message + "\n");
			for (int i = 0; i < message.length(); i++)
			{
				System.out.print("=");
			}
			System.out.print("\n");

			// Printing list
			while (currentNode != null)
			{

				if (nodeCounter % 3 == 0 && nodeCounter != 0)
				{
					// Insert return
					System.out.print("\n");
				}

				System.out.print(currentNode.cellphone);
				currentNode = currentNode.nextNode;
				System.out.print(" ---> ");
				nodeCounter++;
			}
			System.out.println(" X");
		}
		else
		{
			System.out.println("No nodes to print.");
		}
	}

	/**
	 * This method has no privacy leak since it is private
	 * Returns the node at a certain index
	 * @param indexToCheck
	 * @param indexParamOfCallingMethod
	 * @return
	 */
	private CellNode nodeAtIndex(int indexToCheck, int indexParamOfCallingMethod) {
		try
		{
			// Check if it's a valid index
			if ((indexToCheck < 0 || indexToCheck >= size) || (indexParamOfCallingMethod < 0 && indexParamOfCallingMethod >= size))
			{
				throw new NoSuchElementException();
			}
			else
			{
				// Return the node at the specified index
				CellNode node = head;
				for (int i = 0; i < indexToCheck; i++)
				{
					if (node != null)
					{
						node = node.nextNode;
					}
					else
					{
						throw new NullPointerException();
					}
				}

				return node;
			}
		} catch (NoSuchElementException e)
		{
			System.out.println("Error: " + indexParamOfCallingMethod + " is an invalid index: " + e.getMessage());
			System.exit(0);
		} catch (NullPointerException e)
		{
			System.out.println("Error: unexpected null node in the middle of the linked list.");
			System.exit(0);
		}

		// Keeps compiler happy
		return null;
	}

	/**
	 * Adds cellphone at the end
	 * @param cellPhone cellphone to be added at the end of the list
	 */
	public void add(CellPhone cellPhone) {
		if (size != 0)
		{
			// Adding cellPhone at the end
			System.out.println("Current size: " + size);
			System.out.println("Adding CellPhone (SN# " + cellPhone.getSerialNumber() + ") to list");
			CellNode lastNode = nodeAtIndex(size - 1, size - 1);
			lastNode.nextNode = new CellNode(cellPhone, null);
			size++;
			System.out.println("The list is now size " + size);
		}
		else
		{
			addToStart(cellPhone);
		}
	}

	/**
	 * Checks equality of celllists
	 * @param o another list to compare to
	 * @return whether the list is equal or not
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		CellList cellList = (CellList) o;
		return size == cellList.size && checkIfSameElements(cellList);
	}

	/**
	 * Checks whether the nodes contains same elements in subsequent nodes or not
	 * @param list starting node to check
	 * @return
	 */
	public boolean checkIfSameElements(CellList list) {
		CellNode list1Head = head;
		CellNode list2Head = list.head;

			if (list1Head == null || list2Head == null)
			{
				if (list1Head == null && list2Head == null)
					return true;
				else
					return false;
			}
			else
			{
				return list1Head.equals(list2Head);
			}
	}
}
