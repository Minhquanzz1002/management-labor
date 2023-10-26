package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.PhanCong;

public class PhanCong_dao {
	private Connection con;
	public PhanCong_dao() {
		con = MyConnection.getInstance().getConnection();
	}
	public boolean insetPC(PhanCong pc) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into PHANCONG values(?,?,?,?,GETDATE())");
			stmt.setString(1, pc.getMaNV());
			stmt.setString(2, pc.getMaCT());
			stmt.setString(3, pc.getMaCV());
			stmt.setDate(4, new java.sql.Date(pc.getNgayThamGia().getTime()));
			int n=stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<PhanCong> getDsPC() {
		List<PhanCong> dsPC = new ArrayList<PhanCong>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from PHANCONG");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PhanCong pc = new PhanCong(rs.getString("MaNV"),rs.getString("MaCT"),rs.getString("MaCV"), rs.getDate("NgayThamGia"));
				dsPC.add(pc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPC;
	}
	
	public boolean deletePC(String manv) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete  from PHANCONG where MaNV =?");
			stmt.setString(1, manv);
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean luanChuyenNS(String maNV, String maCT, String maCV) {
		try {
			PreparedStatement stmt = con.prepareStatement("update PHANCONG set MaCT = ?, MaCV = ?, NgayThamGia=getDate() where MaNV = ?");
			stmt.setString(1, maCT);
			stmt.setString(2, maCV);
			stmt.setString(3, maNV);
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
