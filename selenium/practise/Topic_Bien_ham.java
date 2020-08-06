package practise;

public class Topic_Bien_ham {
	// Biến = thuộc tính = Field
	// Biến toàn cục(global) = sử dụng trong toàn bộ class
	private String fullName = "Nguyễn Ngọc Bảo Hân";

	// Hàm = phương thức = Method/Function
	// Biến cục bộ (local): chỉ sử dụng trong Method
	public void SetfullName(String fullName) {
	//this để gán biến vì biến toàn cục trùng với biến cục bộ
		this.fullName = fullName;
		

	}
	//
	public String getfullName() {
		return fullName;
	}
}
