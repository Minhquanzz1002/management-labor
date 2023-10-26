package entity;

import java.util.Date;
import java.util.Objects;

public class CongTrinh {
	private String maCT,tenCT,diaChi,trangThai,ghiChu;
	private Date ngayKhoiCong,ngayCapPhep,ngayThanhDuKien;
	private int soLuongNS;
	
	public CongTrinh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CongTrinh(String maCT, String tenCT, String diaChi,Date ngayCapPhep,  Date ngayKhoiCong,Date ngayThanhDuKien,String trangThai, int soLuongNS, String ghiChu) {
		super();
		this.maCT = maCT;
		this.tenCT = tenCT;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayCapPhep = ngayCapPhep;
		this.ngayThanhDuKien = ngayThanhDuKien;
		this.soLuongNS=soLuongNS;
	}

	
//	public CongTrinh(String maCT, String tenCT, String diaDiemCT, Date ngayCapPhep, Date ngayKhoiCong,
//			Date ngayHTDuKien, String trangThai, int soLuongNhanSu, String ghiChu) {
//		this.maCT = maCT;
//		this.tenCT = tenCT;
//		this.diaChi = diaDiemCT;
//		this.ngayKhoiCong = ngayKhoiCong;
//		this.ngayCapPhep = ngayCapPhep;
//		this.ngayThanhDuKien = ngayHTDuKien;
//		this.trangThai = trangThai;
//		this.soLuongNS = soLuongNhanSu;
//		this.ghiChu = ghiChu;
//	}
	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Date getNgayKhoiCong() {
		return ngayKhoiCong;
	}

	public void setNgayKhoiCong(Date ngayKhoiCong) {
		this.ngayKhoiCong = ngayKhoiCong;
	}

	public Date getNgayCapPhep() {
		return ngayCapPhep;
	}

	public void setNgayCapPhep(Date ngayCapPhep) {
		this.ngayCapPhep = ngayCapPhep;
	}

	public Date getNgayHTDuKien() {
		return ngayThanhDuKien;
	}

	public void setNgayHTDuKien(Date ngayThanhDuKien) {
		this.ngayThanhDuKien = ngayThanhDuKien;
	}

	public int getSoLuongNS() {
		return soLuongNS;
	}

	public void setSoLuongNS(int soLuongNS) {
		this.soLuongNS = soLuongNS;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(maCT);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongTrinh other = (CongTrinh) obj;
		return Objects.equals(maCT, other.maCT);
	}

	@Override
	public String toString() {
		return "CongTrinh [maCT=" + maCT + ", tenCT=" + tenCT + ", diaChi=" + diaChi + ", trangThai=" + trangThai
				+ ", ghiChu=" + ghiChu + ", ngayKhoiCong=" + ngayKhoiCong + ", ngayCapPhep=" + ngayCapPhep
				+ ", ngayThanhDuKien=" + ngayThanhDuKien + ", soLuongNS=" + soLuongNS + "]";
	}
	
	
	
}
