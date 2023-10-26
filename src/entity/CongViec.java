package entity;

import java.util.Objects;

public class CongViec {
	private String maCV,tenCV,tenCM,moTaCV;
	private int soNSToiDa,soNSHT;
	public CongViec(String maCV, String tenCV, String chuyenMonCV, String moTaCV, int soNSToiDa, int soNSHT) {
		this.maCV = maCV;
		this.tenCV = tenCV;
		this.tenCM = chuyenMonCV;
		this.moTaCV = moTaCV;
		this.soNSToiDa = soNSToiDa;
		this.soNSHT = soNSHT;
	}
	public CongViec(String maCV, String tenCV, String chuyenMonCV, int soNSToiDa, int soNSHT, String moTaCV) {
		this.maCV = maCV;
		this.tenCV = tenCV;
		this.tenCM = chuyenMonCV;
		this.moTaCV = moTaCV;
		this.soNSToiDa = soNSToiDa;
		this.soNSHT = soNSHT;
	}
	public CongViec() {
		maCV="*****";
		tenCV="*****";
		tenCM="*****";
		moTaCV="*****";
		soNSHT=0;
		soNSToiDa=0;
	}
	public String getMaCV() {
		return maCV;
	}
	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}
	public String getTenCV() {
		return tenCV;
	}
	public void setTenCV(String tenCV) {
		this.tenCV = tenCV;
	}
	public String getChuyenMonCV() {
		return tenCM;
	}
	public void setChuyenMonCV(String chuyenMonCV) {
		this.tenCM = chuyenMonCV;
	}
	public String getMoTaCV() {
		return moTaCV;
	}
	public void setMoTaCV(String moTaCV) {
		this.moTaCV = moTaCV;
	}
	public int getSoNSToiDa() {
		return soNSToiDa;
	}
	public void setSoNSToiDa(int soNSToiDa) {
		this.soNSToiDa = soNSToiDa;
	}
	public int getSoNSHT() {
		return soNSHT;
	}
	public void setSoNSHT(int soNSHT) {
		this.soNSHT = soNSHT;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongViec other = (CongViec) obj;
		return Objects.equals(maCV, other.maCV);
	}
	
}
