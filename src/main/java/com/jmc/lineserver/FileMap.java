package com.jmc.lineserver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class FileMap {
	
	private HashMap<Integer, Integer> map = new HashMap<>();
	private int totalBytes = 0;
	private String path;
	
	/**
	 * Parse the given input file and build a map of its contents.
	 * @param path The file to serve.
	 * @throws IOException
	 */
	public void parse(String path) throws IOException {
		
		this.path = path;
		this.totalBytes = 0;

		File f = new File(path);
		int currLineNum = 0;
		
		try (LineNumberReader lnr = new LineNumberReader(new FileReader(f))) {
			
			String line = lnr.readLine();
			int offset = 0;

			while (line != null) {
				if (currLineNum == 0) {
					map.put(currLineNum, 0);
					offset = line.length() + 1; // Line plus newline
				}
				else {
					map.put(currLineNum, offset);
					offset = offset + line.length() + 1;
				}
				totalBytes += line.length() + 1;

				line = lnr.readLine();	
				currLineNum++;
			}
		}
	}

	/**
	 * Get a line from the file, given a zero-based line index.
	 * @param lineNum Line number (zero-based) to return.
	 * @return The text from the requested line
	 * @throws LineNotFoundException If the line number exceeds the total number of lines in the file.
	 */
	
	public String getLine(int lineNum) throws LineNotFoundException {
		
		if (lineNum >= map.size()) {
			throw new LineNotFoundException("Line number " + lineNum + " is too big.");
		}
		
		int offset;
		int bytesToRead;
		
		if (lineNum == 0) {
			offset = 0;
			bytesToRead = map.get(1) - 1;
		}
		else if (lineNum == map.size() -1 ) {
			offset = map.get(lineNum);
			bytesToRead = totalBytes - offset - 1;
		}
		else {
			offset = map.get(lineNum);
			bytesToRead = map.get(lineNum + 1) - offset - 1;
		}
					
		try (RandomAccessFile datafile = new RandomAccessFile(path, "r")) {
			byte[] bytes = new byte[bytesToRead];
			datafile.seek(offset);
			datafile.read(bytes);
			return new String (bytes);
		}
		catch (IOException e) {
			throw new LineNotFoundException("Could not read file: path=" + path);
		}
	}

}
