package practise;

public class Topic_06_Replace {

	public static void main(String[] args) {
		// User nhập vào
        String inputAddress = "156 Hung Vuong\n Da Nang";
        //Sever trả về
        String outputAddress = "156 Hung Vuong Da Nang";
        
        inputAddress = inputAddress.replace("\n","");
        
        System.out.println(inputAddress);
        System.out.println(outputAddress);
	}

}