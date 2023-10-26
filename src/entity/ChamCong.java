package entity;


public class ChamCong {
	private String maNV;
	private String tenNV;
	private String maPB;
	private int thang;
	private int nam;
	private int soNgayLam;
	private double luongTheoNgay;
	private boolean trangThai;
	public ChamCong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChamCong(String maNV, String tenNV, String maPB, int thang, int nam, int soNgayLam, double luongTheoNgay,boolean TT) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.maPB = maPB;
		this.thang = thang;
		this.nam = nam;
		this.soNgayLam = soNgayLam;
		this.trangThai = TT;
		this.luongTheoNgay = luongTheoNgay;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public int getSoNgayLam() {
		return soNgayLam;
	}
	public void setSoNgayLam(int soNgayLam) {
		this.soNgayLam = soNgayLam;
	}
	public double getLuongTheoNgay() {
		return luongTheoNgay;
	}
	public void setLuongTheoNgay(double luongTheoNgay) {
		this.luongTheoNgay = luongTheoNgay;
	}
	
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "ChamCong [maNV=" + maNV + ", tenNV=" + tenNV + ", maPB=" + maPB + ", thang=" + thang + ", nam=" + nam
				+ ", soNgayLam=" + soNgayLam + ", luongTheoNgay=" + luongTheoNgay + ", trangThai=" + trangThai + "]";
	}
	
	
	
	
}
