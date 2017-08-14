package com.jmc.lineserver;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineserverController {
    
	private FileMap fileMap = null;
	
	public LineserverController() {
		try {
			fileMap = new FileMap();
			fileMap.parse("src/main/resources/lines.txt");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

    @RequestMapping(value="/lines/{line}", method = RequestMethod.GET)
    public String getLine(@PathVariable("line") int lineNumber) {
    		if (fileMap == null) {
    			throw new LineNotFoundException("Input file not initialized.");
    		}
    		return fileMap.getLine(lineNumber);
    }
    
}