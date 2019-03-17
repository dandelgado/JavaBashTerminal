/**
 * Daniel Delgado
 * 109986180
 * Homework 5
 * CSE 214 Recitation R05
 * Sun Lin
 * Yu Wang
 * @author Daniel
 */

import java.util.Scanner;

/**
 * Runs a program which takes user input and builds a DirectoryTree using certain commands.
 */
public class BashTerminal {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		DirectoryTree tree = new DirectoryTree();
		String input = "";
		while (!(input.equalsIgnoreCase("exit"))) {
			System.out.print("[ddelgado@host]: $ ");
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("pwd")) {
				System.out.println(tree.presentWorkingDirectory());
			}
			else if (input.equalsIgnoreCase("ls")) {
				System.out.println(tree.listDirectory());
			}
			else if (input.equalsIgnoreCase("ls -R")) {
				tree.runPrintDirectoryTree();
			}
			else if (input.length() > 3 && input.substring(0, 3).equals("cd ")) {
				if (input.charAt(3) == '/')
					tree.resetCursor();
				else if (input.length() == 5 && input.substring(3, 5).equals(".."))
					tree.setCursorToParent();
				else if (input.length() >= 7 && input.substring(3) != null && input.substring(3, 7).equals("root")) {
					String path = input.substring(3);
					tree.pathCursorSave(path);
				}
				else if (input.substring(3) != null && !(input.substring(3).contains(" ")) && !(input.substring(3) == ".."))
				{
					String name = input.substring(3);
					try {
						tree.changeDirectory(name);
					} catch (NotADirectoryException e) {
						System.out.println(e.getMessage());
					}
				}
				else
					System.out.println("Invalid name: Name must not contain any spaces.");
			}
			else if (input.length() > 6 && input.substring(0, 6).equals("mkdir "))
			{
				if (input.substring(6) != null && !(input.substring(6).contains(" ")) && !(input.substring(6).contains("/"))) {
					String name = input.substring(6);
					try {
						tree.makeDirectory(name);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (FullDirectoryException e) {
						System.out.println(e.getMessage());
					} catch (NotADirectoryException e) {
						System.out.println(e.getMessage());
					}
				}
				else
					System.out.println("ERROR: Name must not contain any spaces or forward slashes(/).");
			}
			else if (input.length() > 6 && input.substring(0, 6).equals("touch ")) {
				if (input.substring(6) != null) {
					String name = input.substring(6);
					try {
						tree.makeFile(name);
					} catch (IllegalArgumentException | FullDirectoryException | NotADirectoryException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			else if (input.length() > 5 && input.substring(0, 5).equals("find ")) {
				if (input.substring(5) != null) {
					String name = input.substring(5);
					tree.findCursorSave(name);
				}
				else
					System.out.println("ERROR: Name must not contain any spaces.");
			}
			else if (!(input.equalsIgnoreCase("exit")))
				System.out.println("ERROR: Please enter a valid input.");
		}
		System.out.println("Program terminating normally");
	}
}
