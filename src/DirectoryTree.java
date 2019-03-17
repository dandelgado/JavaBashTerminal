/**
 * Daniel Delgado
 * 109986180
 * Homework 5
 * CSE 214 Recitation R05
 * Sun Lin
 * Yu Wang
 * @author Daniel
 */

public class DirectoryTree {
	private DirectoryNode root = new DirectoryNode("root", false, null, null, null, null);
	private DirectoryNode cursor = root;
	
	/**
	 * Creates a new DirectoryTree object with blank parameters.
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
	 */
	public DirectoryTree() {
		
	}
	
	/**
	 * Creates a new DirectoryTree object using the given parameters.  
	 * Sets the cursor to the root node of the tree.
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
	 * @param root
	 * The root of the DirectoryTree object.
	 */
	public DirectoryTree(DirectoryNode root) {
		this.root = root;
		cursor = root;
	}
	
	/**
	 * Moves the cursor to the root node of the tree.
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The cursor now references the root node of the tree.
	 */
	public void resetCursor() {
		cursor = root;
	}
	
	/**
	 * Moves the cursor to the directory with the name indicated by name.
	 * Preconditions:
	 * 'name' references a valid directory ('name' cannot reference a file).
	 * Postconditions:
	 * The cursor now references the directory with the name indicated by name. If a child could not be found with that name, 
	 * then the user is prompted to enter a different directory name. If the name was not a directory, a NotADirectoryException 
	 * has been thrown
	 * @param name
	 * The name of the child node that the cursor reference will be moved to.
	 * @throws NotADirectoryException
	 * Thrown if the node with the indicated name is a file, as files cannot be selected by the cursor, or cannot be found.
	 */
	public void changeDirectory(String name) throws NotADirectoryException {
		if (cursor.getName() != name)
		{
			if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
				if (cursor.getLeft().getIsFile() == true)
						throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
				else
					cursor = cursor.getLeft();
			}
			else if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
				if (cursor.getMiddle().getIsFile() == true)
					throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
				else
					cursor = cursor.getMiddle();
			}
			else if (cursor.getRight() != null && cursor.getRight().getName().equals(name)) {
				if (cursor.getRight().getIsFile() == true)
					throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
				else
					cursor = cursor.getRight();
			}
			else
				System.out.println("ERROR: No such directory named '" + name + "'.");
		}
	}
	
	/**
	 * Returns a String containing the path of directory names from the root node of the tree to the cursor, 
	 * with each name separated by a forward slash "/".
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The cursor remains at the same DirectoryNode.
	 * @return
	 * The path from the root to the cursor as a string.
	 */
	public String presentWorkingDirectory() {
		String[] path = new String[50];
		path[0] = cursor.getName();
		DirectoryNode temp = cursor.getParent();
		String directoryString = "";
		int i = 1;
		while (temp != null) {
			path[i] = temp.getName();
			temp = temp.getParent();
			i++;
		}
		while (i > 0) {
			directoryString += path[i] + "/";
			i--;
		}
		directoryString += path[i];
		return directoryString.substring(5);
	}
	
	/**
	 * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The cursor remains at the same DirectoryNode.
	 * @return
	 * A formatted String of DirectoryNode names.
	 */
	public String listDirectory() {
		String list = "";
		if (cursor.getLeft() != null)
			list += cursor.getLeft().getName() + " ";
		if (cursor.getMiddle() != null)
			list += cursor.getMiddle().getName() + " ";
		if (cursor.getRight() != null)
			list += cursor.getRight().getName() + " ";
		return list;
	}
	
	/**
	 * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
	 * Preconditions:
	 * None.
	 * Postconditions:
	 * The cursor remains at the same DirectoryNode.
	 * @param count
	 * A counter that determines how many indents will be printed before the current node.
	 * @param node
	 * The node currently being printed.
	 */
	public void printDirectoryTree(int count, DirectoryNode node) {
		for (int i = 0; i < count; i++)
			System.out.print("    ");
		if (node.getIsFile() == true) {
			System.out.println("- " + node.getName());
		}
		else {
			System.out.println("|- " + node.getName());
		}
		if (node.getLeft() != null) {
			printDirectoryTree(count + 1, node.getLeft());
		}
		if (node.getMiddle() != null) {
			printDirectoryTree(count + 1, node.getMiddle());
		}
		if (node.getRight() != null) {
			printDirectoryTree(count + 1, node.getRight());
		}
		return;
	}
	
	/**
	 * Method that runs the printDirectoryTree method using a count of 0 and the cursor node as parameters.
	 */
	public void runPrintDirectoryTree() {
		printDirectoryTree(0, cursor);
	}
	
	/**
	 * Creates a directory with the indicated name and adds it to the children of the cursor node.
	 * Preconditions:
	 * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * Postconditions:
	 * A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
	 * @param name
	 * The name of the new directory to be added to the tree.
	 * @throws IllegalArgumentException
	 * Thrown if the 'name' argument is invalid.
	 * @throws FullDirectoryException
	 * Thrown if all child references of this directory are occupied.
	 * @throws NotADirectoryException
	 * Thrown if the node that the new directory is being added to is a file, as files cannot be 
	 * selected by the cursor, or cannot be found.
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
		if (name.contains("/") || name.contains(" "))
			throw new IllegalArgumentException("ERROR: Invalid directory name.");
		else {
			DirectoryNode newDirectory = new DirectoryNode(name, false, null, null, null, cursor);
			cursor.addChild(newDirectory);
			newDirectory.setParent(cursor);
		}
	}
	
	/**
	 * Creates a file with the indicated name and adds it to the children of the cursor node.
	 * Preconditions:
	 * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * Postconditions:
	 * A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
	 * @param name
	 * The name of the file to be added to the tree.
	 * @throws IllegalArgumentException
	 * Thrown if the 'name' argument is invalid.
	 * @throws FullDirectoryException
	 * Thrown if all child references of this directory are occupied.
	 * @throws NotADirectoryException
	 * Thrown if the node that the new directory is being added to is a file, as files cannot be 
	 * selected by the cursor, or cannot be found.
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		DirectoryNode file = new DirectoryNode(name, true, null, null, null, cursor);
		cursor.addChild(file);
		file.setParent(cursor);
	}
	
	/**
	 * Finds the node in the tree with the indicated name and prints the path.
	 * @param name
	 * The name of the node that the tree will be searched for.
	 * @param node
	 * The node currently being examined by the method.
	 */
	public void find(String name, DirectoryNode node) {
		cursor = node;
		if (node.getName().equals(name))
			System.out.println(presentWorkingDirectory());
		else {
			if (node.getLeft() != null) {
				find(name, node.getLeft());
			}
			if (node.getMiddle() != null) {
				find(name, node.getMiddle());
			}
			if (node.getRight() != null) {
				find(name, node.getRight());
			}
		}
	}
	
	/**
	 * Saves the cursor's current location and runs the find method starting from the root of the tree.
	 * Returns the cursor to its original position after running the find method.
	 * @param name
	 * The name of the node that will be searched for in the find method.
	 */
	public void findCursorSave(String name) {
		DirectoryNode temp = cursor;
		find(name, root);
		cursor = temp;
	}
	
	/**
	 * Moves the cursor up to its parent directory if the cursor is not located at the root.
	 * Preconditions:
	 * The cursor is not located at the root node.
	 * Postconditions:
	 * The cursor is located at the parent node of its previous position.
	 */
	public void setCursorToParent() {
		if (cursor != root)
			cursor = cursor.getParent();
		else
			System.out.println("ERROR: Already at root directory.");
	}
	
	/**
	 * Moves the cursor to the directory with the indicated path.
	 * @param path
	 * The path determined by the user as a String.
	 * @param node
	 * The node currently being examined by the method.
	 */
	public void findPath(String path, DirectoryNode node) {
		String[] nodePath = new String[50];
		nodePath[0] = node.getName();
		DirectoryNode temp = node.getParent();
		String directoryString = "";
		int i = 1;
		while (temp != null)
		{
			nodePath[i] = temp.getName();
			temp = temp.getParent();
			i++;
		}
		while (i > 0)
		{
			directoryString += nodePath[i] + "/";
			i--;
		}
		directoryString += nodePath[i];
		if (directoryString.substring(5).equals(path)) {
			cursor = node;
		}
		else {
			if (node.getLeft() != null) {
				findPath(path, node.getLeft());
			}
			if (node.getMiddle() != null) {
				findPath(path, node.getMiddle());
			}
			if (node.getRight() != null) {
				findPath(path, node.getRight());
			}
		}
	}
	
	/**
	 * Method that calls the findPath method using the user-determined path and
	 * the root of the tree as parameters.
	 * @param path
	 * The path of the node to be searched for in the tree as determined by the user.
	 */
	public void pathCursorSave(String path) {
		findPath(path, root);
	}
}
