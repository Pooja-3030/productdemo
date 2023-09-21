package com.dnb.productdemo.exceptions;

public class IdNotFoundException extends Exception {
public IdNotFoundException(String msg) {
	super(msg);
	}
public String toString() {
	// TODO Auto-generated method stub
	return super.toString()+super.getMessage();
}

}