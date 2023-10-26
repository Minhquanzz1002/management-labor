package entity;

import java.util.Date;

public class NhanSu {
	private String maNhanSu,maPB,hoDem,tenNhanSu,chuyenMon, maDuAn,sdt,diaChi;
	private int tuoi,soNgayCong;
	private double luongTheoNgay;
	private Date ngaySinh; 
	private boolean gioiTinh;
	private Date ngayThamGia;
	
	
	public NhanSu(){}
	public NhanSu(String maNhanSu, String hoDem, String tenNhanSu, Date ngaySinh,boolean gioiTinh , String diaChi, String sdt) {
		super();
		this.maNhanSu = maNhanSu;
		this.hoDem = hoDem;
		this.tenNhanSu = tenNhanSu;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}
	public NhanSu(String maNhanSu, String maPB, String hoDem, String tenNhanSu,Date ngaySinh,int tuoi, Double luongTheoNgay,  boolean gioiTinh,String diaChi,String SDT, String chuyenMon) {
		super();
		this.maNhanSu = maNhanSu;
		this.maPB = maPB;
		this.diaChi=diaChi;
		this.hoDem = hoDem;
		this.tenNhanSu = tenNhanSu;
		this.chuyenMon = chuyenMon;
		this.sdt=SDT;
		this.tuoi = tuoi;
		this.luongTheoNgay = luongTheoNgay;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}
	
	

	public NhanSu(String maNhanSu, String maPB, String hoDem, String tenNhanSu, String chuyenMon, Date ngayThamGia) {
		super();
		this.maNhanSu = maNhanSu;
		this.maPB = maPB;
		this.hoDem = hoDem;
		this.tenNhanSu = tenNhanSu;
		this.chuyenMon = chuyenMon;
		this.ngayThamGia = ngayThamGia;
	}
	

	
	

	public String getMaNhanSu() {
		return maNhanSu;
	}

	public void setMaNhanSu(String maNhanSu) {
		this.maNhanSu = maNhanSu;
	}

	public String getMaPB() {
		return maPB;
	}

	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}

	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTenNhanSu() {
		return tenNhanSu;
	}

	public void setTenNhanSu(String tenNhanSu) {
		this.tenNhanSu = tenNhanSu;
	}

	public String getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}

	public String getMaDuAn() {
		return maDuAn;
	}

	public void setMaDuAn(String maDuAn) {
		this.maDuAn = maDuAn;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public int getSoNgayCong() {
		return soNgayCong;
	}

	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}

	public double getLuongTheoNgay() {
		return luongTheoNgay;
	}

	public void setLuongTheoNgay(double luongTheoNgay) {
		this.luongTheoNgay = luongTheoNgay;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgayThamGia() {
		return ngayThamGia;
	}

	public void setNgayThamGia(Date ngayThamGia) {
		this.ngayThamGia = ngayThamGia;
	}

	@Override
	public String toString() {
		return "NhanSu [maNhanSu=" + maNhanSu + ", maPB=" + maPB + ", hoDem=" + hoDem + ", tenNhanSu=" + tenNhanSu
				+ ", chuyenMon=" + chuyenMon + ", maDuAn=" + maDuAn + ", sdt=" + sdt + ", diaChi=" + diaChi + ", tuoi="
				+ tuoi + ", luongTheoNgay=" + luongTheoNgay + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", ngayThamGia=" + ngayThamGia + "]";
	}


	
	
}
