package com.drunkcode.ateam.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.drunkcode.ateam.api.VO.ErrorObject;
import com.drunkcode.ateam.api.exception.PlayerNotFoundException;

@ControllerAdvice
public class AteamApiControllerAdvice {
	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<ErrorObject> exception(PlayerNotFoundException e){
		ErrorObject error = new ErrorObject();
		error.setErrorCode("001");
		error.setErrorMessage("Player not found, mayde the id is wrong");
		return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_FOUND);
	}
}
