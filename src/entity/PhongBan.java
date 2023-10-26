package entity;


public class PhongBan {
	private String maPB;
	private String tenPB;
	private int soLuong;
	private String maTP;
	public PhongBan(String maPB, String tenPB, int soLuong, String maTP) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
		this.soLuong = soLuong;
		this.maTP = maTP;
	}
	public PhongBan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}
	public String getTenPB() {
		return tenPB;
	}
	public void setTenPB(String tenPB) {
		this.tenPB = tenPB;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getMaTP() {
		return maTP;
	}
	public void setMaTP(String maTP) {
		this.maTP = maTP;
	}
	@Override
	public String toString() {
		return "PhongBan [maPB=" + maPB + ", tenPB=" + tenPB + ", soLuong=" + soLuong + ", maTP=" + maTP + "]";
	}
	
	
	
}
