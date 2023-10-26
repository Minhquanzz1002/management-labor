package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ChuyenMon;
import entity.CongViec;
import entity.PhanCong;

public class CongViec_dao {
	private Connection con;
	private PreparedStatement prstmt;
	private ResultSet rs;
	private String sql;
	private ChuyenMon_dao chuyenMon;

	/**
	 * Constructor kết nối công việc - database
	 */
	public CongViec_dao() {
		con = MyConnection.getInstance().getConnection();
	}


	/**
	 * Lấy danh sách công việc từ database
	 * @param maCT
	 * @return danh sách công việc
	 */
	public List<CongViec> getDSCV(String maCT) {
		List<CongViec> dsCV = new ArrayList<CongViec>();
		try {
			if (maCT.equalsIgnoreCase("Tất cả")) {
				sql = "select MaCV,TenCV,MoTa,ChuyenMon=(select TenCM from CHUYENMON cm where cm.MaCM=cv.MaCM) from CONGVIEC cv";
				prstmt = con.prepareStatement(sql);
				rs = prstmt.executeQuery();
				CongViec cv;
				while (rs.next()) {
					cv = new CongViec(rs.getString("MaCV"), rs.getString("TenCV") , rs.getString("ChuyenMon"), 0, 0, rs.getString("MoTa"));
					dsCV.add(cv);
				}
			} else {
				sql = "select cv.MaCV, TenCV, MoTa, soLuongToiDa, \r\n"
						+ "	ChuyenMon=(select TenCM from CHUYENMON cm where cm.MaCM=cv.MaCM),\r\n"
						+ "	SoNSHienTai=(select count(MaNV) from PHANCONG\r\n"
						+ "	where MaCT=cc.MaCT and MaCV=cv.MaCV)\r\n"
						+ "from CONGVIEC cv inner join CONGTRINH_CONGVIEC cc on cv.MaCV=cc.MaCV \r\n"
						+ "where cc.MaCT=?";
				prstmt = con.prepareStatement(sql);
				prstmt.setString(1, maCT);
				rs = prstmt.executeQuery();
				CongViec cv;
				while (rs.next()) {
					cv = new CongViec(rs.getString("MaCV"), rs.getString("TenCV"), rs.getString("ChuyenMon"),
							rs.getInt("soLuongToiDa"), rs.getInt("SoNSHienTai"), rs.getString("MoTa"));
					dsCV.add(cv);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCV;
	}

	/**
	 * Thêm công việc mới vào công trình chỉ định, nếu công trình đang chế độ tất cả thì thêm mới công việc vào danh sách công việc
	 * @param maCT mã công trình thêm
	 * @param cv công việc cần thêm
	 * @return kết quả thêm true=thành công, false=thất bại
	 */
	public boolean addCongViec(String maCT, CongViec cv) {
		try {
			if (maCT.equals("Tất cả")) {
				sql = "insert into CONGVIEC values(?,?,?,?,getDate())";
				prstmt = con.prepareStatement(sql);
				prstmt.setString(1, cv.getMaCV());
				prstmt.setString(2, cv.getTenCV());
				prstmt.setString(3, cv.getMoTaCV());
				prstmt.setString(4, cv.getChuyenMonCV());
				int n = prstmt.executeUpdate();
				if (n <= 0)
					return false;
			}
			else {
				sql = "insert into CONGTRINH_CONGVIEC values(?,?,?)";
				prstmt = con.prepareStatement(sql);
				prstmt.setString(1, maCT);
				prstmt.setString(2, cv.getMaCV());
				prstmt.setInt(3, cv.getSoNSToiDa());
				int n2 = prstmt.executeUpdate();
				if (n2 <= 0)
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Sửa công việc được chỉ định
	 * @param cv công việc cần sửa
	 * @return kết quả sửa true=thành công, false=thất bại
	 */
	public boolean setCongViec(String maCT, CongViec cv) {
		if(maCT.equals("Tất cả")) {
			sql = "update CONGVIEC set TenCV=?, Mota=?,MaCM=? where MaCV=?";
			try {
				prstmt = con.prepareStatement(sql);
				prstmt.setString(1, cv.getTenCV());
				prstmt.setString(2, cv.getMoTaCV());
				prstmt.setString(3, cv.getChuyenMonCV());
				prstmt.setString(4, cv.getMaCV());
				int n = prstmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		else {
			sql = "update CONGTRINH_CONGVIEC set soLuongToiDa=? where MaCT=? and  MaCV=?";
			try {
				prstmt = con.prepareStatement(sql);
				prstmt.setInt(1, cv.getSoNSToiDa());
				prstmt.setString(2, maCT);
				prstmt.setString(3, cv.getMaCV());
				int n = prstmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Xóa công việc trong danh sách công việc
	 * @param maCV mã công việc muốn xóa
	 * @return kết quả xóa true=thành công, false=thất bại
	 */
	public boolean deleteCongViec(String maCV) {
		sql = "delete from CONGVIEC where MaCV=? and MaCV not in (select MaCV from CONGTRINH_CONGVIEC)";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, maCV);
			int n = prstmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Xóa 1 công việc trong công trình chỉ định
	 * @param maCT mã công trình muốn xóa bỏ công việc
	 * @param maCV mã công việc muốn xóa
	 * @return kết quả xóa true=thành công, false=thất bại
	 */
	public boolean deleteCongViecInCongTrinh(String maCT, String maCV) {
		sql = "delete from CONGTRINH_CONGVIEC where MaCT=? and MaCV = ?";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, maCT);
			prstmt.setString(2, maCV);
			int n = prstmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Lấy tổng số lượng nhân viên của công việc thuộc tất cả công trình
	 * @param maCV mã công việc cần biết số lượng
	 * @return số lượng nhân viên
	 */
	public int getSLNL(String maCV) {
		int sl = 0;
		sql = "select 'SLNS'=count(*) from PHANCONG where MaCV=?";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, maCV);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				sl = rs.getInt("SLNS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sl;
	}

	/**
	 * Lấy danh sahcs phân công của công việc nhất định
	 * @param maCV mã công việc muốn lấy danh sách phân công
	 * @return danh sách phân công
	 */
	public List<PhanCong> getDSPC(String maCV) {
		List<PhanCong> lst = new ArrayList<PhanCong>();
		sql = "select MaCT, MaNV from PHANCONG where MaCV=?";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, maCV);
			PhanCong pc;
			rs = prstmt.executeQuery();
			while (rs.next()) {
				pc = new PhanCong(rs.getString("MaNV"), rs.getString("MaCT"), "", null);
				lst.add(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 * Lấy danh sách công trình có chứa công việc truyền vào
	 * @param maCV mã công việc muốn lấy danh sách công trình
	 * @return danh sách công trình có công việc đó
	 */
	public List<String> getDSCT(String maCV) {
		List<String> lst = new ArrayList<String>();
		sql = "select MaCT from CONGTRINH_CONGVIEC where MaCV=?";
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setString(1, maCV);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				lst.add(rs.getString("MaCT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 * Lấy danh sách công việc mà công trình này không có
	 * @param maCT mã công trình
	 * @return danh sách công việc chưa có trong công trình
	 */
	public List<CongViec> getDSCVChuaCo(String maCT) {
		List<CongViec> dsCVChuaCo = new ArrayList<CongViec>();
		List<CongViec> dsCVDaCo = getDSCV(maCT);
		for (CongViec congViec : getDSCV("Tất cả")) {
			if (!dsCVDaCo.contains(congViec))
				dsCVChuaCo.add(congViec);
		}
		return dsCVChuaCo;
	}

	/**
	 * Chuyển đổi tên chuyên môn thành mã chuyên môn
	 * @param tenCM tên chuyên môn
	 * @return mã chuyên môn
	 */
	public String getMaCM(String tenCM) {
		chuyenMon = new ChuyenMon_dao();
		for (ChuyenMon cm : chuyenMon.getListCM()) {
			if (cm.getTenCM().equals(tenCM))
				return cm.getMaCM();
		}
		return null;
	}

	/**
	 * Lấy 1 công việc trong danh sách từ 1 mã công việc
	 * @param maCV
	 * @return
	 */
	public CongViec getCongViecTheoMa(String maCV) {
		for (CongViec cv : getDSCV("Tất cả")) {
			if(cv.getMaCV().equals(maCV))
				return cv;
		}
		return null;
	}
	/**
	 * Lấy danh sách công việc
	 * @param maCT là mã công trình
	 * @return list danh sách công việc
	 */
	public List<CongViec> getCongViec(String maCT) {
		List<CongViec> ds = new ArrayList<CongViec>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT     CONGVIEC.*,CONGTRINH_CONGVIEC.soLuongToiDa,soLuongHt =(select COUNT(MaNV) from PHANCONG where PHANCONG.MaCV = CONGVIEC.MaCV and PHANCONG.MaCT = ?),chuyneMOn = (select CHUYENMON.TenCM from CHUYENMON where CONGVIEC.MaCM = CHUYENMON.MaCM)"
							+ "FROM   CONGVIEC INNER JOIN CONGTRINH_CONGVIEC ON CONGVIEC.MaCV = CONGTRINH_CONGVIEC.MaCV where CONGTRINH_CONGVIEC.MaCT = ?");
			stmt.setString(1, maCT);
			stmt.setString(2, maCT);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				CongViec cv = new CongViec(res.getString("MaCV"), res.getString("TenCV"), res.getString("chuyneMOn"),
						res.getString("MoTa"), Integer.parseInt(res.getString("soLuongHt").toString()),
						Integer.parseInt(res.getString("soLuongToiDa")));
				ds.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách công việc đã đủ người
	 * 
	 * @param maCT là mã công trình
	 * @return list danh sách công việc
	 */
	public List<CongViec> getCongViecDaDay(String maCT) {
		List<CongViec> ds = new ArrayList<CongViec>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT     CONGVIEC.*,CONGTRINH_CONGVIEC.soLuongToiDa,soLuongHt =(select COUNT(MaNV) from PHANCONG where PHANCONG.MaCV = CONGVIEC.MaCV ),chuyneMOn = (select CHUYENMON.TenCM from CHUYENMON where CONGVIEC.MaCM = CHUYENMON.MaCM)"
							+ "FROM   CONGVIEC INNER JOIN CONGTRINH_CONGVIEC ON CONGVIEC.MaCV = CONGTRINH_CONGVIEC.MaCV where CONGTRINH_CONGVIEC.MaCT = ? and soLuongToiDa = (select COUNT(MaNV) from PHANCONG where PHANCONG.MaCV = CONGVIEC.MaCV )");
			stmt.setString(1, maCT);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				CongViec cv = new CongViec(res.getString("MaCV"), res.getString("TenCV"), res.getString("chuyneMOn"),
						res.getString("MoTa"), Integer.parseInt(res.getString("soLuongHt").toString()),
						Integer.parseInt(res.getString("soLuongToiDa")));
				ds.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách công việc chưa đủ người
	 * 
	 * @param maCT là mã công trình
	 * @return list danh sách công việc
	 */
	public List<CongViec> getCongViecChuaDay(String maCT) {
		List<CongViec> ds = new ArrayList<CongViec>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT     CONGVIEC.*,CONGTRINH_CONGVIEC.soLuongToiDa,soLuongHt =(select COUNT(MaNV) from PHANCONG where PHANCONG.MaCV = CONGVIEC.MaCV ),chuyneMOn = (select CHUYENMON.TenCM from CHUYENMON where CONGVIEC.MaCM = CHUYENMON.MaCM)"
							+ "FROM   CONGVIEC INNER JOIN CONGTRINH_CONGVIEC ON CONGVIEC.MaCV = CONGTRINH_CONGVIEC.MaCV where CONGTRINH_CONGVIEC.MaCT = ? and soLuongToiDa != (select COUNT(MaNV) from PHANCONG where PHANCONG.MaCV = CONGVIEC.MaCV )");
			stmt.setString(1, maCT);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				CongViec cv = new CongViec(res.getString("MaCV"), res.getString("TenCV"), res.getString("chuyneMOn"),
						res.getString("MoTa"), Integer.parseInt(res.getString("soLuongHt").toString()),
						Integer.parseInt(res.getString("soLuongToiDa")));
				ds.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	/**
	 * Lấy mã chuyên môn từ mã công việc
	 * @param maCV mã công việc
	 * @return mã chuyên môn
	 */
	public String getMaCMFromMaCV(String maCV) {
		String maCM=null;
		sql = "select MaCM from CONGVIEC where MaCV = ?";
		try {
			prstmt=con.prepareStatement(sql);
			prstmt.setString(1, maCV);
			rs = prstmt.executeQuery();
			while(rs.next()) {
				maCM = rs.getString("MaCM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maCM;
	}
}
