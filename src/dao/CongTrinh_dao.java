package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.CongTrinh;

public class CongTrinh_dao {
	private Connection con;
	private PreparedStatement prstmt;
	private ResultSet rs;
	private String sql;
	public CongTrinh_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * Lấy danh sách công trình
	 * @return danh sách công trình
	 */
	public List<CongTrinh> getDSCT() {
		List<CongTrinh> dsCT = new ArrayList<CongTrinh>();
		sql = "select *,'SoLuongNV'=(select count(MaNV) from PHANCONG pc\r\n"
				+ "where pc.MaCT=ct.MaCT)from CONGTRINH ct";
		try {
			prstmt = con.prepareStatement(sql);
			rs = prstmt.executeQuery();
			CongTrinh ct;
			while (rs.next()) {
				ct = new CongTrinh(rs.getString("MaCT"), rs.getString("TenCT"), rs.getString("DiaChi"),
						rs.getDate("NgayCapPhep"), rs.getDate("NgayKhoiCong"), rs.getDate("NgayHoanThanhDuKien"),
						rs.getString("TrangThai"), rs.getInt("SoLuongNV"), rs.getString("GhiChu"));
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
	}

	/**
	 *  Thêm mới 1 công trình
	 * @param ct công trình thêm
	 * @return kết quả thêm true=thành công, false=thất bại
	 */
	public boolean addCongTrinh(CongTrinh ct) {
		try {
			if ((ct.getNgayKhoiCong()+"").equals("null")) {//ngày khởi công null => ngày hoàn thành dự kiến null
				sql = "insert CONGTRINH(MaCT,TenCT,DiaChi,NgayCapPhep,TrangThai,GhiChu) values(?,?,?,?,?,?)";
				prstmt = con.prepareStatement(sql);
				prstmt.setString(5, ct.getTrangThai());
				prstmt.setString(6, ct.getGhiChu());
			} else {//ngày khởi công khác null
				if ((ct.getNgayHTDuKien()+"").equals("null"))//ngày khởi công khác null nhưng ngày hoàn thành dự kiến null
				{
					sql = "insert CONGTRINH(MaCT,TenCT,DiaChi,NgayCapPhep,NgayKhoiCong,TrangThai,GhiChu) values(?,?,?,?,?,?,?)";
					prstmt = con.prepareStatement(sql);
					prstmt.setDate(5, new java.sql.Date(ct.getNgayKhoiCong().getTime()));
					prstmt.setString(6, ct.getTrangThai());
					prstmt.setString(7, ct.getGhiChu());
				}
				else {//ngày khởi công và ngày hoàn thành dự kiến đều khác null
					sql = "insert into CONGTRINH values(?,?,?,?,?,?,?,?, getDate())";
					prstmt = con.prepareStatement(sql);
					prstmt.setDate(5, new java.sql.Date(ct.getNgayKhoiCong().getTime()));
					prstmt.setDate(6, new java.sql.Date(ct.getNgayHTDuKien().getTime()));
					prstmt.setString(7, ct.getTrangThai());
					prstmt.setString(8, ct.getGhiChu());
				}
			}
			prstmt.setString(1, ct.getMaCT());
			prstmt.setString(2, ct.getTenCT());
			prstmt.setString(3, ct.getDiaChi());
			prstmt.setDate(4, new java.sql.Date(ct.getNgayCapPhep().getTime()));
			int n = prstmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Sửa công trình trong danh sách
	 * @param ct công trình cần sửa
	 * @return kết quả sửa true=thành công, false=thất bại
	 */
	public boolean setCongTrinh(CongTrinh ct) {
		try {
			if ((ct.getNgayKhoiCong()+"").equals("null")) {//ngày khởi công null => ngày hoàn thành dự kiến null
				sql = "update CONGTRINH set TenCT=?,DiaChi=?,NgayCapPhep=?,TrangThai=?,GhiChu=? where MaCT=?";
				prstmt = con.prepareStatement(sql);
				prstmt.setString(4, ct.getTrangThai());
				prstmt.setString(5, ct.getGhiChu());
				prstmt.setString(6, ct.getMaCT());
			} else {//ngày khởi công khác null
				if ((ct.getNgayHTDuKien()+"").equals("null"))//ngày khởi công khác null nhưng ngày hoàn thành dự kiến null
				{
					sql = "update CONGTRINH set TenCT=?,DiaChi=?,NgayCapPhep=?,NgayKhoiCong=?,TrangThai=?,GhiChu=? where MaCT=?";
					prstmt = con.prepareStatement(sql);
					prstmt.setDate(4, new java.sql.Date(ct.getNgayKhoiCong().getTime()));
					prstmt.setString(5, ct.getTrangThai());
					prstmt.setString(6, ct.getGhiChu());
					prstmt.setString(7, ct.getMaCT());
				}
				else {//ngày khởi công và ngày hoàn thành dự kiến đều khác null
					sql = "update CONGTRINH set TenCT=?,DiaChi=?,NgayCapPhep=?,NgayKhoiCong=?,NgayHoanThanhDuKien=?,TrangThai=?,GhiChu=? where MaCT=?";
					prstmt = con.prepareStatement(sql);
					prstmt.setDate(4, new java.sql.Date(ct.getNgayKhoiCong().getTime()));
					prstmt.setDate(5, new java.sql.Date(ct.getNgayHTDuKien().getTime()));
					prstmt.setString(6, ct.getTrangThai());
					prstmt.setString(7, ct.getGhiChu());
					prstmt.setString(8, ct.getMaCT());
				}
			}
			prstmt.setString(1, ct.getTenCT());
			prstmt.setString(2, ct.getDiaChi());
			prstmt.setDate(3, new java.sql.Date(ct.getNgayCapPhep().getTime()));
			int n = prstmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
