package javaTeser;

public class Topic_05_Parameter {
    //Param	
	public static void main(String[] args) {
	System.out.println(showName());
	System.out.println(showName("automation API"));
	System.out.println(showName("Manual","Testing"));
	
	int number = 696969;
	System.out.println("Sumlink =" + number);
	
	}
 //Fix cứng
	//hàm ko có tham số
	public static String showName() {
		return "automation unit";
	}
//Dynamic fullname	
	//hàm có 1 tham số
	public static String showName(String fullname) {
		return fullname;
	}
//Dynamic firstname + lastname
	//hàm có 2 tham số 	
    public static String showName(String firstname,String lastname) {
    	return firstname + " " + lastname;
	}

}

