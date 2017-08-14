package com.jmc.lineserver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.PAYLOAD_TOO_LARGE, reason="Line beyond end-of-file")  // 404
public class LineNotFoundException extends RuntimeException {
	public LineNotFoundException(String msg) {
		super(msg);
	}
}