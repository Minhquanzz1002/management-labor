package entity;

public class ThongKe {
	private int tongNhanSu, tongDuAn, nsChuaThamGiaDA, tongPhongBan, tongDAThiCong, tongDAHoanThanh;

	public ThongKe(int tongNhanSu, int tongDuAn, int nsChuaThamGiaDA, int tongPhongBan, int tongDAThiCong,
			int tongDAHoanThanh) {
		super();
		this.tongNhanSu = tongNhanSu;
		this.tongDuAn = tongDuAn;
		this.nsChuaThamGiaDA = nsChuaThamGiaDA;
		this.tongPhongBan = tongPhongBan;
		this.tongDAThiCong = tongDAThiCong;
		this.tongDAHoanThanh = tongDAHoanThanh;
	}
	
	public ThongKe() {
		super();
		this.tongNhanSu = 0;
		this.tongDuAn = 0;
		this.nsChuaThamGiaDA = 0;
		this.tongPhongBan = 0;
		this.tongDAThiCong = 0;
		this.tongDAHoanThanh = 0;
	}

	public int getTongNhanSu() {
		return tongNhanSu;
	}

	public void setTongNhanSu(int tongNhanSu) {
		this.tongNhanSu = tongNhanSu;
	}

	public int getTongDuAn() {
		return tongDuAn;
	}

	public void setTongDuAn(int tongDuAn) {
		this.tongDuAn = tongDuAn;
	}

	public int getNsChuaThamGiaDA() {
		return nsChuaThamGiaDA;
	}

	public void setNsChuaThamGiaDA(int nsChuaThamGiaDA) {
		this.nsChuaThamGiaDA = nsChuaThamGiaDA;
	}

	public int getTongPhongBan() {
		return tongPhongBan;
	}

	public void setTongPhongBan(int tongPhongBan) {
		this.tongPhongBan = tongPhongBan;
	}

	public int getTongDAThiCong() {
		return tongDAThiCong;
	}

	public void setTongDAThiCong(int tongDAThiCong) {
		this.tongDAThiCong = tongDAThiCong;
	}

	public int getTongDAHoanThanh() {
		return tongDAHoanThanh;
	}

	public void setTongDAHoanThanh(int tongDAHoanThanh) {
		this.tongDAHoanThanh = tongDAHoanThanh;
	}

}
