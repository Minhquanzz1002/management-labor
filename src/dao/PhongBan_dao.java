package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.NhanSu;
import entity.PhongBan;

public class PhongBan_dao {
	private Connection con;
	private PreparedStatement prstmt;
	private ResultSet rs;
	private String sql;
	private PhongBan pb;

	public PhongBan_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	public List<PhongBan> getDsPB() {
		List<PhongBan> dsPB = new ArrayList<PhongBan>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"select MaPB,TenPB,MaTruongPhong,soLuong= (select COUNT(*) from NHANVIEN where NHANVIEN.MaPB = PHONGBAN.MaPB) from PHONGBAN");
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PhongBan pb = new PhongBan(res.getString("MaPB"), res.getString("TenPB"), res.getInt("soLuong"),
						res.getString("MaTruongPhong"));
				dsPB.add(pb);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsPB;
	}
	
	/**
	 * Lấy Danh Sách Phòng Ban Hiện Có
	 * 
	 * @return dsPB
	 */
	public List<PhongBan> getDSPB() {
		List<PhongBan> dsPB = new ArrayList<PhongBan>();
		sql = "select * ,soLuong= (select COUNT(*) from NHANVIEN where NHANVIEN.MaPB = PHONGBAN.MaPB) from PHONGBAN";
		try {
			prstmt = con.prepareStatement(sql);
			rs = prstmt.executeQuery();

			while (rs.next()) {
				pb = new PhongBan(rs.getString("MaPB"), rs.getString("TenPB"), rs.getInt("soLuong"),
						rs.getString("MaTruongPhong"));
				dsPB.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPB;
	}

	/**
	 * Lấy Phòng Ban Theo Mã Phòng Ban
	 * 
	 * @param mapb
	 * @return pb
	 * @throws SQLException
	 */
	public PhongBan getPB(String mapb) throws SQLException {
		PhongBan pb = null;
		try {
			PreparedStatement stmt = con.prepareStatement(
					" select *, 'soLuong' = (select count(*) from NHANVIEN where MaPB= ?) from PHONGBAN where MaPB=?");
			stmt.setString(1, mapb);
			stmt.setString(2, mapb);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pb = new PhongBan(rs.getString("MaPB"), rs.getString("TenPB"), rs.getInt("soLuong"),
						rs.getString("MaTruongPhong"));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		return pb;
	}

	/**
		 * Thêm Phòng Ban Mới Vào Danh Sách Phòng Ban
		 * @param pb
		 * @return true = Thêm Thành Công, false = Thêm Thất Bại
		 * @throws SQLException
		 */
		public boolean themPhongBan(PhongBan pb) throws SQLException {
			if (!pb.getMaTP().isEmpty()) {
				try {
					PreparedStatement stmt = con.prepareStatement("insert into PHONGBAN (MaPB,TenPB,MaTruongPhong) values(?,?,?)");
					stmt.setString(1, pb.getMaPB());
					stmt.setString(2, pb.getTenPB());
					stmt.setString(3, pb.getMaTP());
					int n = stmt.executeUpdate();
					if (n > 0)
						return true;
			}catch (SQLException e) {
				throw new SQLException(e);
			}
			}else {
				try {
					PreparedStatement stmt = con.prepareStatement("insert into PHONGBAN (MaPB,TenPB) values(?,?)");
					stmt.setString(1, pb.getMaPB());
					stmt.setString(2, pb.getTenPB());
					int n = stmt.executeUpdate();
					if (n > 0)
						return true;
				} catch (SQLException e) {
					throw new SQLException(e);
				}
			}
			return false;
		}

	/**
	 * Xóa Phòng Ban Khỏi Danh Sách
	 * 
	 * @param maPB
	 * @return true = Xóa Thành Công, false = Xóa Thất Bại
	 */
	public boolean xoaPhongBan(String maPB) {
		int n = 0;
		try {
			String sql = "update NHANVIEN set MaPB = null where MaPB = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, maPB);
				n = stmt.executeUpdate();
				if (n<0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			PreparedStatement stmt = con.prepareStatement("delete from PHONGBAN where MaPB = ?");
			stmt.setString(1, maPB);
			int n2 = stmt.executeUpdate();
			if (n2 > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Lấy Danh Sách Nhân Sự Theo Mã Phòng Ban
	 * 
	 * @param maPB
	 * @return dsNSPB
	 */
	public List<NhanSu> getDSNV(String maPB) {
		NhanSu_dao nhansu = new NhanSu_dao();
		List<NhanSu> dsNS = nhansu.getListNhanSu();
		List<NhanSu> dsNSPB = new ArrayList<NhanSu>();
		for (NhanSu nhanSu2 : dsNS) {
			if (nhanSu2.getMaPB() != null) {
				if (nhanSu2.getMaPB().equals(maPB))
					dsNSPB.add(nhanSu2);
			}
		}
		return dsNSPB;
	}

}
