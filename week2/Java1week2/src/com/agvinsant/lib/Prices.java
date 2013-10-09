/*
 *  project Java1week2
 * 
 * package com.agvinsant.lib
 * 
 * @author Adam Vinsant
 * 
 * date Oct 8, 2013
 * 
 */
package com.agvinsant.lib;

public enum Prices {
	MBAIR("Macbook Air", "999"),
	MBPRO("Macbook Pro", "1199"),
	MBPRETINA("Macbook Pro Retina", "1499"),
	IMAC("iMac", "1299"),
	MINI("Mac Mini", "599"),
	PRO("Mac Pro", "2599");
	
	private final String computerType;
	private final String computerValue;
	
	// sets the computer type and price. 
	private Prices(String computerType, String computerValue) {
		this.computerType = computerType;
		this.computerValue = computerValue;
	}
	// sets the computer data object
	public String setComputerType(){
		
		return computerType;
	}
	// sets the price data object
	public String setComputerValue(){
		
		return computerValue;
	}
}
