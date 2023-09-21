package com.dnb.productdemo.exceptions;

public class UniqueProductNameExpection extends Exception {
	public UniqueProductNameExpection(String msg) {
		super(msg);
		}
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+super.getMessage();
	}

}
