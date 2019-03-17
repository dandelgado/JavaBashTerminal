/**
 * Daniel Delgado
 * 109986180
 * Homework 5
 * CSE 214 Recitation R05
 * Sun Lin
 * Yu Wang
 * @author Daniel
 */

public class DirectoryNode {
	private String name;
	private boolean isFile;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private DirectoryNode parent;
	
	/**
	 * Creates a new DirectoryNode object with blank parameters.
	 */
	public DirectoryNode() {
		
	}
	
	/**
	 * Creates a new DirectoryNode object using the given parameters.
	 * @param name
	 * The name of the new DirectoryNode.
	 * @param isFile
	 * A boolean value indicating whether or not the node is a file.
	 * @param left
	 * The node's left child.
	 * @param middle
	 * The node's middle child.
	 * @param right
	 * The node's right child.
	 * @param parent
	 * The node's parent.
	 */
	public DirectoryNode(String name, boolean isFile, DirectoryNode left, DirectoryNode middle, DirectoryNode right, DirectoryNode parent) {
		this.name = name;
		this.isFile = isFile;
		this.left = left;
		this.middle = middle;
		this.right = right;
		this.parent = parent;
	}

	/**
	 * Receives the name of a DirectoryNode.
	 * @return
	 * The name of the node.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes a node's name to a new name.
	 * @param name
	 * The new name for a particular node. 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Receives the left child of the current node.
	 * @return
	 * The left child of the current node.
	 */
	public DirectoryNode getLeft() {
		return left;
	}
	
	/**
	 * Receives the middle child of the current node.
	 * @return
	 * The middle child of the current node.
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}
	
	/**
	 * Receives the right child of the current node.
	 * @return
	 * The right child of the current node.
	 */
	public DirectoryNode getRight() {
		return right;
	}
	
	/**
	 * Receives a boolean value indicating whether or not the node is a file.
	 * @return
	 * A boolean value that indicates whether or not the node is a file
	 */
	public boolean getIsFile() {
		return isFile;
	}

	/**
	 * Receives the parent node of the current node.
	 * @return
	 * The parent node of the current node.
	 */
	public DirectoryNode getParent() {
		return parent;
	}

	/**
	 * Sets the current parent of the node to a new parent DirectoryNode object.
	 * @param parent
	 * The new parent node of the current node.
	 */
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Adds newChild to any of the open child positions of this node (left, middle, or right).
	 * Preconditions:
	 * This node is not a file.
	 * There is at least one empty position in the children of this node (left, middle, or right).
	 * Postconditions:
	 * newChild has been added as a child of this node. If there is no room for a new node, throw a FullDirectoryException.
	 * @param newChild
	 * The new node to be added to the current node as a child.
	 * @throws FullDirectoryException
	 * Thrown if all child references of this directory are occupied.
	 * @throws NotADirectoryException
	 * Thrown if the current node is a file, as files cannot contain DirectoryNode references (i.e. all files are leaves).
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException {
		if (isFile == true)
			throw new NotADirectoryException("ERROR: No such directory named '" + name + "'.");
		else if (left == null)
			left = newChild;
		else if (middle == null)
			middle = newChild;
		else if (right == null)
			right = newChild;
		else
			throw new FullDirectoryException("ERROR: Present directory is full.");
	}
}
