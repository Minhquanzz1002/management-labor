package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.MyConnection;
import entity.ThongKe;

public class ThongKe_dao {
	private Connection connection;
	public ThongKe_dao() {
		connection = MyConnection.getInstance().getConnection();
	}
	public ThongKe getThongKe() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM Func_ThongKe()");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			ThongKe thongKe = new ThongKe(resultSet.getInt("TongNV"), resultSet.getInt("TongDuAn"), resultSet.getInt("NVChuaThamGiaDuAn"), resultSet.getInt("TongPhongBan"), resultSet.getInt("DuAnDangThiCong"), resultSet.getInt("DuAnHoanThanh"));
			return thongKe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ThongKe();
	}
}
