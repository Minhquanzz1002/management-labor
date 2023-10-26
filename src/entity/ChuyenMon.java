package entity;

public class ChuyenMon {
	private String maCM, tenCM, moTa;

	public ChuyenMon(String maCM, String tenCM, String moTa) {
		super();
		this.maCM = maCM;
		this.tenCM = tenCM;
		this.moTa = moTa;
	}

	public String getMaCM() {
		return maCM;
	}

	public void setMaCM(String maCM) {
		this.maCM = maCM;
	}

	public String getTenCM() {
		return tenCM;
	}

	public void setTenCM(String tenCM) {
		this.tenCM = tenCM;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "ChuyenMon [maCM=" + maCM + ", tenCM=" + tenCM + ", moTa=" + moTa + "]";
	}
	
	public String[] toRowTable() {
		return new String[] {maCM, tenCM, moTa};
	}
}
