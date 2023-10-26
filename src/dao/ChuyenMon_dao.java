package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import connectDB.MyConnection;
import entity.ChuyenMon;

public class ChuyenMon_dao {
	private Connection con;
	
	public ChuyenMon_dao() {
		con = MyConnection.getInstance().getConnection();
	}
	public void getDsTenCM(JComboBox<String> a ) {
		try {
			PreparedStatement stmt = con.prepareStatement("select TenCM from CHUYENMON");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				a.addItem(rs.getString("TenCM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ChuyenMon> getListCM() {
		List<ChuyenMon> ds = new ArrayList<ChuyenMon>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from CHUYENMON");
			ResultSet rs = stmt.executeQuery();
			ChuyenMon cm;
			while (rs.next()) {
				cm = new ChuyenMon(rs.getString("MaCM"), rs.getString("TenCM"),rs.getString("MoTa"));
				ds.add(cm);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	/**
	 * Lấy toàn bộ danh sách chuyên môn
	 * 
	 * @return danh sách chuyên môn
	 */
	public List<ChuyenMon> getDanhSachChuyenMon() {
		List<ChuyenMon> dscm = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM CHUYENMON");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ChuyenMon chuyenMon = new ChuyenMon(resultSet.getString("MaCM"), resultSet.getString("TenCM"),
						resultSet.getString("MoTa"));
				dscm.add(chuyenMon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dscm;
	}

	/**
	 * Thêm chuyên môn vào database
	 * 
	 * @return kết quả thêm
	 */
	public boolean themChuyenMon(ChuyenMon chuyenMon) {
		PreparedStatement preparedStatement = null;
		try {
			if (chuyenMon.getMoTa().isEmpty()) {
				preparedStatement = con.prepareStatement("INSERT INTO CHUYENMON(MaCM, TenCM) VALUES (?, ?)");
				preparedStatement.setString(1, chuyenMon.getMaCM());
				preparedStatement.setString(2, chuyenMon.getTenCM());
			} else {
				preparedStatement = con.prepareStatement("INSERT INTO CHUYENMON(MaCM, TenCM, [MoTa]) VALUES (?, ?, ?)");
				preparedStatement.setString(1, chuyenMon.getMaCM());
				preparedStatement.setString(2, chuyenMon.getTenCM());
				preparedStatement.setString(3, chuyenMon.getMoTa());
			}
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Hàm sửa chuyên môn trong database
	 * @param chuyenMonMoi chuyên môn mới với mã chuyên môn cần sửa
	 * @return kết quả sửa
	 */
	public boolean suaChuyenMon(ChuyenMon chuyenMonMoi) {
		PreparedStatement preparedStatement = null;
		try {
			if (chuyenMonMoi.getMoTa().isEmpty()) {
				preparedStatement = con.prepareStatement("UPDATE CHUYENMON SET TenCM = ? WHERE MaCM = ?");
				preparedStatement.setString(1, chuyenMonMoi.getTenCM());
				preparedStatement.setString(2, chuyenMonMoi.getMaCM());
			} else {
				preparedStatement = con.prepareStatement("UPDATE CHUYENMON SET TenCM = ?, MoTa = ? WHERE MaCM = ?");
				preparedStatement.setString(1, chuyenMonMoi.getTenCM());
				preparedStatement.setString(2, chuyenMonMoi.getMoTa());
				preparedStatement.setString(3, chuyenMonMoi.getMaCM());
			}
			int resultExecute = preparedStatement.executeUpdate();
			if (resultExecute > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Hàm xóa chuyên môn trong database
	 * @param chuyenMon chuyên môn cần xóa
	 * @return kết quả xóa
	 */
	public boolean xoaChuyenMon(ChuyenMon chuyenMon) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("DELETE CHUYENMON WHERE MaCM = ?");
			preparedStatement.setString(1, chuyenMon.getMaCM());
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
