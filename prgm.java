public class Main{
	public static void main(String[] args){
		int num = 1;
		while(num <= 10){
			System.out.println(num);
			num++;
		}
	}
}

//a) prints the numbers 1 and 10
//b) prints the number 1 forever
//c) prints the numbers 1, 2 .. 10
//d) prints the number 10

public class Main{
	public static void main(String[] args){		
		int counter = 0; 
		while (counter != 99) // != means "not equal"
		{ 
			counter = counter + 5;
			System.out.println(counter);
		}
	}
}

//a) prints the numbers 0, 5, 10, 15 ... 95
//b) prints the numbers 0, 5, 10 ... forever
//c) prints the number 99
//d) prints the numbers 0, 1, 2 .. 99


public class Main{
	public static void main(String[] args){		
		double eachLemonCost = 0.50;
		int numLemonsBought = 50;

		int numCupsSold = 30; 
		double eachCupPrice = 1.0;

		double totalSpent = eachLemonCost*numLemonsBought;
		double totalMade = numCupsSold*eachCupPrice;

		if(totalMade > totalSpent){
			System.out.println("Made Profit");
		}
		else{
			System.out.println("Had Losses");
		}
	}
}


//a) prints "Made Profit"
//b) prints "Had Losses"
//c) does nothing
//d) prints "Made Profit" then "Had Losses"


public class Main{
	public static void main(String[] args){		
		int counter = 0; 
		while (counter <= 99) // != means "not equal"
		{ 
			counter = counter + 5;
			System.out.println(counter);
		}
	}
}

//a) prints the numbers 0, 5, 10, 15 ... 95
//b) prints the numbers 0, 5, 10 ... forever
//c) prints the number 99
//d) prints the numbers 0, 1, 2 .. 99


public class Main{
	public static void main(String[] args){
		int num = 7*4*2;
		if(num % 4 == 0) //% means remainder
			System.out.println("Divisible by 4");
	}
}

//a) prints "Divisible By 4"
//b) prints nothing
//c) returns to the main program
//d) divides 8/4

public class Main{
	public static void main(String[] args){
		int num1 = 5;
		int num2 = 3;
		int num = num1*num2;

		if(num > 10){
			num = 10;
		}

		System.out.println(num);	
	}
}
//a) prints 15
//b) prints 10
//c) prints 5
//d) prints nothing

