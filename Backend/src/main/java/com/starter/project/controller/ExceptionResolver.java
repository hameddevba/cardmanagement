package com.starter.project.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request){
        e.printStackTrace();

        return ResponseEntity.status(401).body(HttpStatus.BAD_REQUEST.toString());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
        e.printStackTrace();

        return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED.toString());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleTokenException(ExpiredJwtException e, HttpServletRequest request){
        e.printStackTrace();

        return ResponseEntity.status(403).body(HttpStatus.UNAUTHORIZED.toString());
    }
    @ExceptionHandler
    public ResponseEntity<String> internalError(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return ResponseEntity.status(500).body(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
