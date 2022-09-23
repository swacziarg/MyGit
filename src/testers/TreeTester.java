package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.TreeObject;

class TreeTester {


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//create test folder, objects folder in test, three txt files
	
		File objFolder = new File("objects");
		objFolder.mkdirs();
		PrintWriter writer = new PrintWriter("testFile.txt", "UTF-8");
		writer.print("test 1");
		writer.close();
		PrintWriter writer1 = new PrintWriter("testFile1.txt", "UTF-8");
		writer1.print("test 2");
		writer1.close();
		PrintWriter writer2 = new PrintWriter("testFile2.txt", "UTF-8");
		writer2.print("test 3");
		writer2.close();
	}
//	File file = new File("something.txt");
//	PrintWriter something = new PrintWriter(file);
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
				//deleting all testTXT files
		File f = new File("testFile.txt");
		f.delete();
		File f1 = new File("testFile1.txt");
		f1.delete();
		File f2 = new File("testFile2.txt");
		f2.delete();
		//delete index file
		File index = new File("index");
		index.delete();
		//deleting objects folder w/ all contents  inside
		File objFolder = new File("objects");
		objFolder.delete(); 

	}	
	@Test
	void treeObjTest() throws IOException {
		//create arraylist, add elements (blobs and trees)
		ArrayList<String> list = new ArrayList<String>();
		list.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f filename1.txt");
		list.add("blob : 01d82591292494afd1602d175e165f94992f6f5f someOtherFile.jpg");
		list.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 anotherFileWhichDoesntExist.txt");
		list.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b file4.txt");
		list.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976 makeupThisName.txt");
		
		TreeObject theTree = new TreeObject(list);
		File file = new File("objects\\ed0786deb4cfc3b2f44f2f2cc379e0270efa8e44");
		assertTrue(file.exists());
		
		//Check if all lines exist and are correct 
		Scanner scanner = new Scanner(file);
		assertEquals(scanner.nextLine(), "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f filename1.txt");
		assertEquals(scanner.nextLine(), "blob : 01d82591292494afd1602d175e165f94992f6f5f someOtherFile.jpg");
		assertEquals(scanner.nextLine(), "blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 anotherFileWhichDoesntExist.txt");
		assertEquals(scanner.nextLine(), "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b file4.txt");
		assertEquals(scanner.nextLine(), "tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976 makeupThisName.txt");
		file.delete();
	}

}
