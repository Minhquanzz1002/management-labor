package entity;

import java.util.Date;

public class PhanCong {
	private String maNV, maCT, maCV;
	private Date ngayThamGia;
	public PhanCong(String maNV, String maCT, String maCV, Date ngayThamGia) {
		super();
		this.maNV = maNV;
		this.maCT = maCT;
		this.maCV = maCV;
		this.ngayThamGia = ngayThamGia;
	}
	public PhanCong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaCT() {
		return maCT;
	}
	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}
	public String getMaCV() {
		return maCV;
	}
	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}
	public Date getNgayThamGia() {
		return ngayThamGia;
	}
	public void setNgayThamGia(Date ngayThamGia) {
		this.ngayThamGia = ngayThamGia;
	}
	@Override
	public String toString() {
		return "PhanCong [maNV=" + maNV + ", maCT=" + maCT + ", maCV=" + maCV + ", ngayThamGia=" + ngayThamGia + "]";
	}

}
