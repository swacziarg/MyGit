package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Blob;
import Git.Index;

class IndexTester {
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
	void testAllIndex() throws IOException {
				//initiate and test if index file exists
				Index index = new Index();
				index.init();
				File testIndex = new File("index");
				assertTrue(testIndex.exists());
				
				
				index.add("testFile2.txt");
				index.add("testFile.txt");
				index.add("testFile1.txt");
				
				//Check if new files in objects folder are created with correct sha1
				assertTrue(new File("objects/2f3c6b82e94acbefbdcc4ac1d00fcfb416892355").exists());
				assertTrue(new File("objects/ccf587c77d3c946812e21674ed3b95cb47ab0d6d").exists());
				assertTrue(new File("objects/8c7e89edf024b4964238977b4a3acd23b42271bc").exists());
				
				//Create scanner for index.txt 
				File f = new File("index");
				Scanner s = new Scanner(f);
				
//				//check index contents
//				System.out.println(s.nextLine().compareTo("testFile.txt : 2f3c6b82e94acbefbdcc4ac1d00fcfb416892355"));
//				System.out.println(s.nextLine().compareTo("testFile1.txt : ccf587c77d3c946812e21674ed3b95cb47ab0d6d"));
//				System.out.println("NEXT" + s.nextLine().equals("testFile.txt : 2f3c6b82e94acbefbdcc4ac1d00fcfb416892355\n"));
//				System.out.println("NEXT" + s.nextLine());
				assertEquals(s.nextLine(), "testFile.txt : 2f3c6b82e94acbefbdcc4ac1d00fcfb416892355");
				assertEquals(s.nextLine(), "testFile1.txt : ccf587c77d3c946812e21674ed3b95cb47ab0d6d");
				assertEquals(s.nextLine(), "testFile2.txt : 8c7e89edf024b4964238977b4a3acd23b42271bc");
				
				//remove from index
				index.remove("testFile.txt");
				index.remove("testFile1.txt");
				index.remove("testFile2.txt");
				
				//test if file was removed
				assertFalse(new File("objects/2f3c6b82e94acbefbdcc4ac1d00fcfb416892355").exists());
				assertFalse(new File("objects/ccf587c77d3c946812e21674ed3b95cb47ab0d6d").exists());
				assertFalse(new File("objects/8c7e89edf024b4964238977b4a3acd23b42271bc").exists());
				
				//Check removal from index.txt
				assertFalse(s.hasNext());//if removed all, would be empty
				
	}

}
