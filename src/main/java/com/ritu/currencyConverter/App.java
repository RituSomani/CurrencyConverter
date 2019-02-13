package com.ritu.currencyConverter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

	// mapcurrencyValue.put("AUDUSD", 0.8371);
	public static void main(String[] args) {
		String currency1 = "AUD";
		String currency2 = "JPY";

		Map<String, BigDecimal> mapcurrencyValue = new HashMap<String, BigDecimal>();
		MathContext precision = new MathContext(8, RoundingMode.DOWN); 
		mapcurrencyValue.put("AUDUSD", new BigDecimal(0.8371, precision ));
		mapcurrencyValue.put("CADUSD", new BigDecimal(0.8711, precision));
		mapcurrencyValue.put("USDCNY", new BigDecimal(6.1715, precision));
		mapcurrencyValue.put("EURUSD", new BigDecimal(1.2315, precision));
		mapcurrencyValue.put("GBPUSD", new BigDecimal(1.5683, precision));
		mapcurrencyValue.put("NZDUSD", new BigDecimal(0.7750, precision));
		mapcurrencyValue.put("USDJPY", new BigDecimal(119.95, precision));
		mapcurrencyValue.put("EURCZK", new BigDecimal(27.6028, precision));
		mapcurrencyValue.put("EURDKK", new BigDecimal(7.4405, precision));
		mapcurrencyValue.put("EURNOK", new BigDecimal(8.6651, precision));

		List<String> twoDecimalPlaces = Arrays.asList("AUD", "CAD", "CNY", "CZK", "DKK", "EUR", "GBP", "NOK", "NZD",
				"USD");

		
		StringBuilder sb = new StringBuilder("");
		sb.append(currency1 + "USD");
		
		
		BigDecimal conversionCurrency1toUSD = currencyConversionToUSorEUR("USD",mapcurrencyValue , currency1 ); 
		BigDecimal conversionCurrency1toEUR= currencyConversionToUSorEUR("EUR",mapcurrencyValue , currency1 ); 
		BigDecimal conversionCurrency2toUSD = currencyConversionToUSorEUR("USD",mapcurrencyValue , currency2 ); 
		BigDecimal conversionCurrency2toEUR = currencyConversionToUSorEUR("EUR",mapcurrencyValue , currency2 ); 

		if ((mapcurrencyValue.get(currency1 + currency2) != null)) {
			System.out.println(mapcurrencyValue.get(currency1 + currency2));
		}
		else if (mapcurrencyValue.get(currency2 + currency1) != null) {
			System.out.println(new BigDecimal(1).divide(mapcurrencyValue.get(currency2 + currency1)));

		}

		else if(conversionCurrency1toUSD!=null && conversionCurrency2toUSD!=null)
		{
			System.out.println(new BigDecimal(conversionCurrency2toUSD.toString(), precision).divide(conversionCurrency1toUSD , precision).setScale(2, RoundingMode.DOWN));
		}
		
		else if(conversionCurrency1toEUR!=null && conversionCurrency2toEUR!=null)
		{
			System.out.println(conversionCurrency2toEUR.divide(conversionCurrency1toEUR).setScale(2));
		}

		else if(conversionCurrency1toUSD !=null && conversionCurrency2toEUR!=null )
		{
			System.out.println((conversionCurrency1toUSD.multiply(mapcurrencyValue.get("EURUSD"))).divide(conversionCurrency2toEUR).setScale(2));
		}
		else
		{
			System.out.println((conversionCurrency2toEUR.divide(mapcurrencyValue.get("EURUSD"))).divide(conversionCurrency1toUSD).setScale(2));
		}
		
		
		
	}

	private static BigDecimal currencyConversionToUSorEUR(String curr, Map<String, BigDecimal> mapcurrencyValue,
			String currency) {
		//MathContext mc = new MathContext(4);

		MathContext precision = new MathContext(8, RoundingMode.DOWN); 
		BigDecimal conversionValue = new BigDecimal(0, precision);
		if (mapcurrencyValue.get(curr + currency) != null) {
			conversionValue = mapcurrencyValue.get(curr + currency);
			
		}

		if (mapcurrencyValue.get(currency + curr) != null) {
			conversionValue = new BigDecimal(1, precision).divide(mapcurrencyValue.get(currency + curr), precision);
		}
		
		return conversionValue;
	}

	
}
