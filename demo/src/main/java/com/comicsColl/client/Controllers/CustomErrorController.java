package com.comicsColl.client.Controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleError(HttpServletRequest request) {
	    Map<String, Object> response = new HashMap<>();
	    response.put("timestamp", LocalDateTime.now());
	    response.put("status", HttpStatus.NOT_FOUND.value());
	    response.put("error", "Not Found");
	    response.put("message", "The requested resource is not available.");
	    // You can add more details here
	    return response;
	}


    public String getErrorPath() {
        return "/error";
    }
}
