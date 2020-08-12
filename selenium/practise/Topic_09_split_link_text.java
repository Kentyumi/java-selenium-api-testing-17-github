package practise;

public class Topic_09_split_link_text {

	public static void main(String[] args) {
		String linkText = "http://the-internet.herokuapp.com/basic_auth";
		String splitLink[] = linkText.split("//");
		System.out.println(splitLink[0]);
		System.out.println(splitLink[1]);
		linkText = splitLink[0] + "//" + "admin" + ":" + "admin" + "@" + splitLink[1];
		System.out.println(linkText);
		
		

	}

}
