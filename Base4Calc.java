public class Base4Calc {
	private int result;

	Base4Calc() { result = 0; }
	
	void clear() { result = 0; }
	
	/**
	 * method to convert between bases
	 * convert the parameter string to an int and convert to a base 10 string
	 * if parameter baseInt (base to be converted to) is 10, return new base 10 string
	 * else convert base 10 string to an int (numNewBase)
	 * convert numNewBase to baseInt string (newNum) 
	 * @param oldNum - (string) number in old base to be converted
	 * @param baseInt - base to be converted to
	 * @return newNum
	 */
	String convert(String oldNum, int baseInt) {
		
		//if old num is null, no calculation needed
		if(oldNum == "") {
			return oldNum;
		}
		
		//convert oldNum string to numBase10 int, and convert int to a base 10 string
		int numBase10 = Integer.parseInt(oldNum);
		String base10 = Integer.toString(numBase10, 10);
		
		if (baseInt == 10) {
			return base10;
		}
		
		//convert base10 string to numNewBase int, and convert int to baseInt string newNum
		int numNewBase = Integer.parseInt(base10);
		String newNum = Integer.toString(numNewBase, baseInt);
		
		//newNum is now the oldNum converted to a baseInt number
		return newNum;
	}
	
	/**
	 * math calculation method
	 * loop through parameter string nums
	 * if char c is a number, add to new string val
	 * if c is operator, set op equal to c, and convert val to int (int1)
	 * empty val, then recreate with rest of nums in string
	 * at end of string, convert val to int (int2)
	 * perform result calculation depending on op
	 * clear parameter string nums, convert result to string and add to nums
	 * return nums
	 * @param nums - (string) to be calculated - two numbers and an operand
	 * @return nums
	 */
	String calculate(String nums) {
		int int1 = 0, int2 = 0;
		char op = '+', c;
		String val = new String("");
		
		//loop through string nums and separate based on digit or operator
		for(int i = 0; i < nums.length(); i++) {
			c = nums.charAt(i);
			if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
				Character.toString(c);
				val += c;
			}
			
			//if c is an operator, val is converted to int1 and emptied
			if(c == '+' || c == '-' || c == '*' || c == '/') {
				op = c;
				int1 = Integer.parseInt(val);
				val = "";
			}
		}
		
		//if calculation is happening because of base switch, val may be empty after int1
		if(!val.isEmpty()) {
			int2 = Integer.parseInt(val);
		}
		
		if(op == '+') {
			result = int1 + int2;
		}
		else if(op == '-') {
			result = int1 - int2;
		}
		else if(op == '*') {
			result = int1 * int2;
		}
		else if(op == '/') {
			result = int1 / int2;
		}
		
		//clear nums, convert result to a string and add to nums
		nums = "";
		Integer.toString(result);
		nums += result;
		
		return nums;
	}
}
