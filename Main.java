package ekinoks;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.*;

public class Main  
{ 
    public static void main(String[] args) throws IOException  
    { 
    	BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in));
    	HashMap<String, Integer> rakamMap = new HashMap<String, Integer>();
    	HashMap<String, Integer> onlukMap = new HashMap<String, Integer>();    	
    	HashMap<String, Integer> basamakMap = new HashMap<String, Integer>();
    	createRakamMap(rakamMap);
    	createOnlukMap(onlukMap); 
    	createBasamakMap(basamakMap);
    	
    	HashMap<Integer, String> reverseRakamMap = new HashMap<Integer, String>();
    	HashMap<Integer, String> reverseOnlukMap = new HashMap<Integer, String>();
    	createReverseRakamMap(reverseRakamMap);
    	createReverseOnlukMap(reverseOnlukMap);
    	
    	int control = 1;
        int operation = 0;
        int firstNumber, secondNumber, result;
    	while(control==1)
    	{
    		System.out.println("Program� ba�latmak i�in 1 yaz�p enter tu�una bas�n�z.");
    		System.out.println("Program� sonland�rmak i�in 0 yaz�p enter tu�una bas�n�z.");
    		String temp = reader.readLine();
    		if(!(temp.equals("1") || temp.equals("0")))
    		{
    			System.out.println("L�tfen 1 ya da 0 giriniz");
    			System.out.println();
    			System.out.println();
    			String [] refresh = null;
    			main(refresh);
    			System.exit(0);
    		}
    		control = Integer.parseInt(temp);    		
    		if(control == 0)
    		{
    			System.out.println("�yi g�nler..");
    			break;
    		}
    			
    		System.out.println("L�tfen 1 ile 999.999.999 aras�nda bir say�y� kelimeler aras�nda bo�luk olacak �ekilde yaz�n�z.");
    		String number1 = reader.readLine();
    		firstNumber = getNumber(number1, rakamMap, onlukMap, basamakMap);
    		System.out.println("L�tfen 1 ile 999.999.999 aras�nda bir say�y� kelimeler aras�nda bo�luk olacak �ekilde yaz�n�z.");
    		String number2 = reader.readLine();
    		secondNumber =  getNumber(number2, rakamMap, onlukMap, basamakMap);
    		
    		
    		
    		System.out.println("B�lme i�lemi i�in 1");
    		System.out.println("�arpma i�lemi i�in 2");
    		System.out.println("Toplama i�lemi i�in 3");
    		System.out.println("��karma i�lemi i�in 4 giriniz.");
    		System.out.println("******************");
    		System.out.println("******************");
    		String temp2 = reader.readLine();
    		operation = Integer.parseInt(temp2);
    		result  = calculate(firstNumber, secondNumber, operation);
    		convertToText(result, reverseRakamMap, reverseOnlukMap);
    		
    		System.out.println();
    		System.out.println();
    		System.out.println();
    		
    		rakamMap.clear();
    		onlukMap.clear();
    		basamakMap.clear();
    		reverseOnlukMap.clear();
    		reverseRakamMap.clear();
    	}
         
        
    } 
    public static void convertToText(int result, HashMap<Integer, String> reverseRakamMap, HashMap<Integer, String> reverseOnlukMap)
    {
    	/*
    	 * Bu method i�lem sonucunda bulunan say�n�n tekrar text haline d�nmesini sa�lar.
    	 */
    	if(result>999999999)
    	{
    		System.out.println("Sonu� s�n�rlar�n d���nda.");
    		return;
    	}
    	if(result <0)
    	{
    		System.out.print("eksi ");
    		result *= -1;
    	}
    	if(result==0)
    		System.out.println(reverseRakamMap.get(result));
    	String value = String.valueOf(result);
    	int size = value.length();
    	if(size<=3)
    	{
    		y�zl�kYazd�r(value, reverseRakamMap, reverseOnlukMap);
    	}
    	else if (size >3 && size <7)
    	{
    		if(size == 6)
    		{
    			String temp = value.substring(0, 3);
        		y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
        		System.out.print("bin ");
        		temp = value.substring(3, 6);
        		y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else if(size ==5)
    		{
    			String temp = value.substring(0, 2);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("bin ");
    			temp = value.substring(2, 5);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else
    		{
    			String temp = value.substring(0, 1);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("bin ");
    			temp = value.substring(1, 4);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}    		
    	}
    	else
    	{
    		if(size == 9)
    		{
    			String temp = value.substring(0, 3);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(3, 6);
    			if(!temp.equals("000"))
    			{
    				y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(6, 9);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else if (size ==8)
    		{
    			String temp = value.substring(0, 2);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(2, 5);
    			if(!temp.equals("000"))
    			{
    				y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(5, 8);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else
    		{
    			String temp = value.substring(0, 1);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(1, 4);
    			if(!temp.equals("000"))
    			{
    				y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(4, 7);
    			y�zl�kYazd�r(temp, reverseRakamMap, reverseOnlukMap);
    		}
    	}
    		
    	
    	    	
    	
    }    
    public static int calculate(int firstNumber, int secondNumber, int operation ) throws IOException
    {
    	/*
    	 * Bu method kullan�c�n�n iste�ine g�re hangi i�lemin yap�laca��na karar verir ve i�lemin sonucunu d�nd�r�r.
    	 */
    	int result = 0;
    	if(operation == 1)
    	{
    		if(secondNumber == 0)
    		{
    			System.out.println("S�f�ra b�l�nme hatas�!!!");
    			String [] refresh = null;
    			main(refresh);
    			System.exit(0);
    		}
    		if(firstNumber>secondNumber && secondNumber!=0)
    			result = firstNumber/secondNumber;
    	}
    	else if(operation ==2)
    	{
    		result = firstNumber * secondNumber;
    	}
    	else if (operation ==3)
    	{
    		result = firstNumber + secondNumber;
    	}
    	else if( operation == 4)
    	{
    		result = firstNumber - secondNumber;
    	}
    	else
    	{
    		System.out.println("Wrong operation command!!");
    		String [] refresh = null;
			main(refresh);
			System.exit(0);
    	}
    	 
    	return result;
    	
    	
    	
    	
    	
    }
    public static int getNumber(String number, HashMap<String, Integer> rakamMap, HashMap<String, Integer> onlukMap,
    		HashMap<String, Integer> basamakMap) throws IOException
    {
    	/*
    	 * Bu method girilen textin say� de�erini d�nd�r�r.
    	 */
    	ArrayList<Integer> results = new ArrayList<Integer>();
    	int i;
    	int result = 0;
    	String [] tokens = number.split(" ");
    	int size = tokens.length;
    	
    	for(i=0;i<size;i++)
    	{
    		if(rakamMap.containsKey(tokens[i]))
    		{
    			result+= rakamMap.get(tokens[i]);
    		}
    		else if(onlukMap.containsKey(tokens[i]))
    		{
    			result += onlukMap.get(tokens[i]);
    		}
    		else if(basamakMap.containsKey(tokens[i]))
    		{
    			if(result ==0)
    				result =1;
    			if(tokens[i].equals("y�z"))
    			{
    				result *= basamakMap.get(tokens[i]);
    			}
    			else
    			{
    				result*= basamakMap.get(tokens[i]);
    				results.add(result);
    				result = 0;
    			}
    		}
    		else
    		{
    			System.out.println("Yaln�� d�zende veri girildi.!!!");
    			String [] refresh = null;
    			main(refresh);
    			System.exit(0);
    		}
    		
    	}
    	
    	int total=0, j;
    	if(results.size()!=0)
    	{
    		for(j=0;j<results.size();j++)
        	{
        		total+= results.get(j);
        	}
    		results.clear();
        	return total+result;
    	}
    	else
    	{
    		results.clear();
    		return result;
    	}
    		
    	
    	
    }
    public static void createBasamakMap(HashMap<String, Integer> basamakMap)
    {
    	/*
    	 * Bu method milyona kadar dahil olan yaz�lar� say�ya �evirmekte kullan�lacak olan say�sal de�erleri tutan HashMap'in elemanlar�n� ekler.
    	 */
    
    	basamakMap.put("y�z", 100);
    	basamakMap.put("bin", 1000);
    	basamakMap.put("milyon", 1000000);
    }
    public static void createOnlukMap(HashMap<String, Integer> onlukMap)
    {
    	/*
    	 * Bu method 90 a kadar olan onun katlar�n�n say� kar��l���n� alabilmek i�in olu�turalan HashMap'in elemanlar�n� ekler.
    	 */
    	onlukMap.put("on", 10);
    	onlukMap.put("yirmi", 20);
    	onlukMap.put("otuz", 30);
    	onlukMap.put("k�rk", 40);
    	onlukMap.put("elli", 50);
    	onlukMap.put("altm��", 60);
    	onlukMap.put("yetmi�", 70);
    	onlukMap.put("seksen", 80);
    	onlukMap.put("doksan", 90);
    	
    }
    public static void createRakamMap(HashMap<String, Integer> rakamMap)
    {
    	/*
    	 * Bu method rakamlar�n say� kar��l���n� alabilmek i�in olu�turalan HashMap'in elemanlar�n� ekler.
    	 */
    	rakamMap.put("s�f�r", 0);
    	rakamMap.put("bir", 1);
    	rakamMap.put("iki", 2);
    	rakamMap.put("��", 3);
    	rakamMap.put("d�rt", 4);
    	rakamMap.put("be�", 5);
    	rakamMap.put("alt�", 6);
    	rakamMap.put("yedi", 7);
    	rakamMap.put("sekiz", 8);
    	rakamMap.put("dokuz", 9);
    }     
    public static void y�zl�kYazd�r(String result, HashMap<Integer, String> reverseRakamMap, HashMap<Integer, String> reverseOnlukMap)
    {
    	/*
    	 * Bu method 3 basamakl� olarak gelen say�n�n text olarak yaz�lmas�n� sa�lar.
    	 */
    	if(result.length()==3)
    	{
    		int temp = Character.getNumericValue(result.charAt(0));
    		if(temp ==1)
    		{
    			System.out.print("y�z "); 			
    		}
    		else
    		{
    			if(reverseRakamMap.containsKey(temp)&& temp!=0)
    			{
    				System.out.print(reverseRakamMap.get(temp)+ " y�z ");
    			}
    		}
    		
    		temp = Character.getNumericValue(result.charAt(1)) *10 ;
    		if (reverseOnlukMap.containsKey(temp))
    		{
    			System.out.print(reverseOnlukMap.get(temp)+ " ");
    		}
    		temp = Character.getNumericValue(result.charAt(2));
    		
    		if(reverseRakamMap.containsKey(temp)&& temp !=0)
			{
				System.out.print(reverseRakamMap.get(temp)+ " ");
			}    		
    	}
    	else if(result.length()==2 )
    	{
    		int temp = Character.getNumericValue(result.charAt(0)) *10;
    		if (reverseOnlukMap.containsKey(temp))
    		{
    			System.out.print(reverseOnlukMap.get(temp)+ " ");
    		}
    		temp = Character.getNumericValue(result.charAt(1));
    		if(reverseRakamMap.containsKey(temp)&& temp !=0)
			{
				System.out.print(reverseRakamMap.get(temp)+ " ");
			}    		
    	}
    	else
    	{
    		int temp = Character.getNumericValue(result.charAt(0));
    		if(reverseRakamMap.containsKey(temp)&& temp !=0)
			{
				System.out.print(reverseRakamMap.get(temp)+ " ");
			}
    	}
    }
    public static void createReverseOnlukMap(HashMap<Integer, String> reverseOnlukMap)
    {
    	/*
    	 * Bu method 90 a kadar olan onun katlar�n�n yaz� kar��l���n� alabilmek i�in olu�turalan HashMap'in elemanlar�n� ekler.
    	 */
    	reverseOnlukMap.put(10, "on");
    	reverseOnlukMap.put(20, "yirmi");
    	reverseOnlukMap.put(30, "otuz");
    	reverseOnlukMap.put(40, "k�rk");
    	reverseOnlukMap.put(50, "elli");
    	reverseOnlukMap.put(60, "altm��");
    	reverseOnlukMap.put(70, "yetmi�");
    	reverseOnlukMap.put(80, "seksen");
    	reverseOnlukMap.put(90, "doksan");    	
    }
    public static void createReverseRakamMap(HashMap<Integer, String> reverseRakamMap)
    {
    	/*
    	 * Bu method rakamlar�n yaz� kar��l���n� alabilmek i�in olu�turalan HashMap'in elemanlar�n� ekler.
    	 */
    	reverseRakamMap.put(0, "s�f�r");
    	reverseRakamMap.put(1, "bir");
    	reverseRakamMap.put(2, "iki");
    	reverseRakamMap.put(3, "��");
    	reverseRakamMap.put(4, "d�rt");
    	reverseRakamMap.put(5, "be�");
    	reverseRakamMap.put(6, "alt�");
    	reverseRakamMap.put(7, "yedi");
    	reverseRakamMap.put(8, "sekiz");
    	reverseRakamMap.put(9, "dokuz");
    }
} 
