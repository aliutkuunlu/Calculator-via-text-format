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
    		System.out.println("Programý baþlatmak için 1 yazýp enter tuþuna basýnýz.");
    		System.out.println("Programý sonlandýrmak için 0 yazýp enter tuþuna basýnýz.");
    		String temp = reader.readLine();
    		if(!(temp.equals("1") || temp.equals("0")))
    		{
    			System.out.println("Lütfen 1 ya da 0 giriniz");
    			System.out.println();
    			System.out.println();
    			String [] refresh = null;
    			main(refresh);
    			System.exit(0);
    		}
    		control = Integer.parseInt(temp);    		
    		if(control == 0)
    		{
    			System.out.println("Ýyi günler..");
    			break;
    		}
    			
    		System.out.println("Lütfen 1 ile 999.999.999 arasýnda bir sayýyý kelimeler arasýnda boþluk olacak þekilde yazýnýz.");
    		String number1 = reader.readLine();
    		firstNumber = getNumber(number1, rakamMap, onlukMap, basamakMap);
    		System.out.println("Lütfen 1 ile 999.999.999 arasýnda bir sayýyý kelimeler arasýnda boþluk olacak þekilde yazýnýz.");
    		String number2 = reader.readLine();
    		secondNumber =  getNumber(number2, rakamMap, onlukMap, basamakMap);
    		
    		
    		
    		System.out.println("Bölme iþlemi için 1");
    		System.out.println("Çarpma iþlemi için 2");
    		System.out.println("Toplama iþlemi için 3");
    		System.out.println("çýkarma iþlemi için 4 giriniz.");
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
    	 * Bu method iþlem sonucunda bulunan sayýnýn tekrar text haline dönmesini saðlar.
    	 */
    	if(result>999999999)
    	{
    		System.out.println("Sonuç sýnýrlarýn dýþýnda.");
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
    		yüzlükYazdýr(value, reverseRakamMap, reverseOnlukMap);
    	}
    	else if (size >3 && size <7)
    	{
    		if(size == 6)
    		{
    			String temp = value.substring(0, 3);
        		yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
        		System.out.print("bin ");
        		temp = value.substring(3, 6);
        		yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else if(size ==5)
    		{
    			String temp = value.substring(0, 2);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("bin ");
    			temp = value.substring(2, 5);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else
    		{
    			String temp = value.substring(0, 1);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("bin ");
    			temp = value.substring(1, 4);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}    		
    	}
    	else
    	{
    		if(size == 9)
    		{
    			String temp = value.substring(0, 3);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(3, 6);
    			if(!temp.equals("000"))
    			{
    				yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(6, 9);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else if (size ==8)
    		{
    			String temp = value.substring(0, 2);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(2, 5);
    			if(!temp.equals("000"))
    			{
    				yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(5, 8);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}
    		else
    		{
    			String temp = value.substring(0, 1);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    			System.out.print("milyon ");
    			temp = value.substring(1, 4);
    			if(!temp.equals("000"))
    			{
    				yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    				System.out.print("bin ");
    			}
    			temp = value.substring(4, 7);
    			yüzlükYazdýr(temp, reverseRakamMap, reverseOnlukMap);
    		}
    	}
    		
    	
    	    	
    	
    }    
    public static int calculate(int firstNumber, int secondNumber, int operation ) throws IOException
    {
    	/*
    	 * Bu method kullanýcýnýn isteðine göre hangi iþlemin yapýlacaðýna karar verir ve iþlemin sonucunu döndürür.
    	 */
    	int result = 0;
    	if(operation == 1)
    	{
    		if(secondNumber == 0)
    		{
    			System.out.println("Sýfýra bölünme hatasý!!!");
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
    	 * Bu method girilen textin sayý deðerini döndürür.
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
    			if(tokens[i].equals("yüz"))
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
    			System.out.println("Yalnýþ düzende veri girildi.!!!");
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
    	 * Bu method milyona kadar dahil olan yazýlarý sayýya çevirmekte kullanýlacak olan sayýsal deðerleri tutan HashMap'in elemanlarýný ekler.
    	 */
    
    	basamakMap.put("yüz", 100);
    	basamakMap.put("bin", 1000);
    	basamakMap.put("milyon", 1000000);
    }
    public static void createOnlukMap(HashMap<String, Integer> onlukMap)
    {
    	/*
    	 * Bu method 90 a kadar olan onun katlarýnýn sayý karþýlýðýný alabilmek için oluþturalan HashMap'in elemanlarýný ekler.
    	 */
    	onlukMap.put("on", 10);
    	onlukMap.put("yirmi", 20);
    	onlukMap.put("otuz", 30);
    	onlukMap.put("kýrk", 40);
    	onlukMap.put("elli", 50);
    	onlukMap.put("altmýþ", 60);
    	onlukMap.put("yetmiþ", 70);
    	onlukMap.put("seksen", 80);
    	onlukMap.put("doksan", 90);
    	
    }
    public static void createRakamMap(HashMap<String, Integer> rakamMap)
    {
    	/*
    	 * Bu method rakamlarýn sayý karþýlýðýný alabilmek için oluþturalan HashMap'in elemanlarýný ekler.
    	 */
    	rakamMap.put("sýfýr", 0);
    	rakamMap.put("bir", 1);
    	rakamMap.put("iki", 2);
    	rakamMap.put("üç", 3);
    	rakamMap.put("dört", 4);
    	rakamMap.put("beþ", 5);
    	rakamMap.put("altý", 6);
    	rakamMap.put("yedi", 7);
    	rakamMap.put("sekiz", 8);
    	rakamMap.put("dokuz", 9);
    }     
    public static void yüzlükYazdýr(String result, HashMap<Integer, String> reverseRakamMap, HashMap<Integer, String> reverseOnlukMap)
    {
    	/*
    	 * Bu method 3 basamaklý olarak gelen sayýnýn text olarak yazýlmasýný saðlar.
    	 */
    	if(result.length()==3)
    	{
    		int temp = Character.getNumericValue(result.charAt(0));
    		if(temp ==1)
    		{
    			System.out.print("yüz "); 			
    		}
    		else
    		{
    			if(reverseRakamMap.containsKey(temp)&& temp!=0)
    			{
    				System.out.print(reverseRakamMap.get(temp)+ " yüz ");
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
    	 * Bu method 90 a kadar olan onun katlarýnýn yazý karþýlýðýný alabilmek için oluþturalan HashMap'in elemanlarýný ekler.
    	 */
    	reverseOnlukMap.put(10, "on");
    	reverseOnlukMap.put(20, "yirmi");
    	reverseOnlukMap.put(30, "otuz");
    	reverseOnlukMap.put(40, "kýrk");
    	reverseOnlukMap.put(50, "elli");
    	reverseOnlukMap.put(60, "altmýþ");
    	reverseOnlukMap.put(70, "yetmiþ");
    	reverseOnlukMap.put(80, "seksen");
    	reverseOnlukMap.put(90, "doksan");    	
    }
    public static void createReverseRakamMap(HashMap<Integer, String> reverseRakamMap)
    {
    	/*
    	 * Bu method rakamlarýn yazý karþýlýðýný alabilmek için oluþturalan HashMap'in elemanlarýný ekler.
    	 */
    	reverseRakamMap.put(0, "sýfýr");
    	reverseRakamMap.put(1, "bir");
    	reverseRakamMap.put(2, "iki");
    	reverseRakamMap.put(3, "üç");
    	reverseRakamMap.put(4, "dört");
    	reverseRakamMap.put(5, "beþ");
    	reverseRakamMap.put(6, "altý");
    	reverseRakamMap.put(7, "yedi");
    	reverseRakamMap.put(8, "sekiz");
    	reverseRakamMap.put(9, "dokuz");
    }
} 
