package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.TaiKhoan;

public class TaiKhoan_dao {
	private Connection con;
	public TaiKhoan_dao() {
		con = MyConnection.getInstance().getConnection();
	}
	
	public List<TaiKhoan> getDsTK() {
		List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from TAIKHOAN");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TaiKhoan a =new TaiKhoan(rs.getString("TenTK"), rs.getString("MatKhau"));
				ds.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean suaTaiKhoan(TaiKhoan taiKhoan) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("UPDATE TAIKHOAN SET MatKhau = ? WHERE TenTK = ?");
			preparedStatement.setString(1, taiKhoan.getMatKhau());
			preparedStatement.setString(2, taiKhoan.getTenTK());
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
