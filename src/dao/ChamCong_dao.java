package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ChamCong;

public class ChamCong_dao {
	private Connection con;
	public ChamCong_dao() {
		con = MyConnection.getInstance().getConnection();
	}
	
	public List<ChamCong> getDSChamCong(String maPB,int thang, int nam) {
		List<ChamCong> ds= new ArrayList<ChamCong>();
		if (thang==0) {
			try {
				PreparedStatement stmt = con.prepareStatement("select CHAMCONG.*,Ten = (select HoNV+''+TenNV from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV),LuongTheoNgay = (select LuongTheoNgay from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV) from CHAMCONG where MaNV in (select MaNV from NHANVIEN where MaPB = ?) and  Nam = ?");
				stmt.setString(1, maPB);
				stmt.setInt(2, nam);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ChamCong cc = new ChamCong(rs.getString(1),rs.getString("Ten"), maPB,  thang, nam, rs.getInt("NgayLamViec"), rs.getInt("LuongTheoNgay"),rs.getBoolean("DaNhan"));
					ds.add(cc);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		else if (thang>0) {
			try {
				PreparedStatement stmt = con.prepareStatement("select CHAMCONG.*,Ten = (select HoNV+''+TenNV from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV),LuongTheoNgay = (select LuongTheoNgay from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV) from CHAMCONG where MaNV in (select MaNV from NHANVIEN where MaPB = ?) and thang = ? and Nam = ?");
				stmt.setString(1, maPB);
				stmt.setInt(2, thang);
				stmt.setInt(3, nam);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ChamCong cc = new ChamCong(rs.getString(1),rs.getString("Ten"), maPB,  thang, nam, rs.getInt("NgayLamViec"), rs.getInt("LuongTheoNgay"),rs.getBoolean("DaNhan"));
					ds.add(cc);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		} 
		return ds;
	}
	
	public List<ChamCong> getDsChamCong() {
		List<ChamCong> ds = new ArrayList<ChamCong>();
		try {
			PreparedStatement stmt = con.prepareStatement("select CHAMCONG.*,Ten = (select HoNV+''+TenNV from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV),MaPB = (select NHANVIEN.MaPB from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV),LuongTheoNgay = (select LuongTheoNgay from NHANVIEN where NHANVIEN.MaNV = CHAMCONG.MaNV) from CHAMCONG");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChamCong cc = new ChamCong(rs.getString(1),rs.getString("Ten"),rs.getString("MaPB"),  0, 0, rs.getInt("NgayLamViec"), rs.getInt("LuongTheoNgay"),rs.getBoolean("DaNhan"));
				ds.add(cc);
			}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return ds;
	}

}	
