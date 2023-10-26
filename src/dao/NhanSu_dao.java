package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.NhanSu;
import entity.PhongBan;
import entity.TaiKhoan;

public class NhanSu_dao {
	private Connection con;

	public NhanSu_dao() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * Kiểm Tra Trưởng Phòng Có Đang Là Trưởng Phòng Của Phòng Ban Khác Không
	 * @param matp
	 * @return true = Trưởng Phòng Đang Là Trưởng Phòng Ban Khác, false = Trưởng Phòng Không Là Trưởng Phòng Ban Khác
	 */
	public boolean kiemTraTruongPhong(String matp) {
		PhongBan_dao phongban_dao = new PhongBan_dao();
		for (PhongBan pb : phongban_dao.getDsPB()) {
			if (!(pb.getMaTP()+"").equals("null"))
				if (pb.getMaTP().equals(matp))
					return true;
		}
		return false;
	}
	
	/**
	 * lấy danh sách phòng nhân sự từ database Được sắp xếp theo mã chuyên môn
	 * @return list danh sách các nhân sự
	 */
	public List<NhanSu> getListNhanSuSortTheoTieuChi(String maCT, String maCV,String tieuChi) {
		if(tieuChi.equals("Mã"))
			return getNsCT(maCT, maCV);
		List<NhanSu> ds = new ArrayList<NhanSu>();
		try {
			String sql ="SELECT  NHANVIEN.*,"
					+ "chuyenMon =(select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM),"
					+ "PHANCONG.NgayThamGia FROM NHANVIEN INNER JOIN PHANCONG ON NHANVIEN.MaNV = PHANCONG.MaNV "
					+ "where PHANCONG.MaCT = ? and PHANCONG.MaCV = ?";
			if(tieuChi.equals("Tên"))
				sql+= " order by TenNV";
			else//sắp xếp theo ngày tham gia
				sql+= " order by NgayThamGia";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maCT);
			stmt.setString(2, maCV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanSu ns = new NhanSu(rs.getString("MaNV"),rs.getString("MaPB"), rs.getString("HoNV"),rs.getString("TenNV"),rs.getString("chuyenMon"),rs.getDate("NgayThamGia") );
				ds.add(ns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	/**
	 * Lấy danh sách nhân sự thuộc công trình và đảm nhiệm công việc trong công trình
	 * @param ct mã công trình
	 * @param maCV mã công việc
	 * @return danh sách nhân sự
	 */
	public List<NhanSu> getNsCT(String ct, String maCV) {
		List<NhanSu> ds= new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT  NHANVIEN.*,chuyenMon =(select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM),PHANCONG.NgayThamGia FROM NHANVIEN INNER JOIN PHANCONG ON NHANVIEN.MaNV = PHANCONG.MaNV where PHANCONG.MaCT = ? and PHANCONG.MaCV = ?");
			stmt.setString(1, ct);
			stmt.setString(2, maCV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				
				NhanSu ns = new NhanSu(rs.getString("MaNV"),rs.getString("MaPB"), rs.getString("HoNV"),rs.getString("TenNV"),rs.getString("chuyenMon"),rs.getDate("NgayThamGia") );
				ds.add(ns);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ds;
	}
	
	/**
	 * lấy danh sách phòng nhân sự từ database
	 * @return list danh sách các nhân sự
	 */
	public List<NhanSu> getListNhanSu() {
		List<NhanSu> dsNs = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT NHANVIEN.MaNV, NHANVIEN.MaPB, NHANVIEN.HoNV, NHANVIEN.TenNV, NHANVIEN.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( NHANVIEN.NgaySinh), NHANVIEN.LuongTheoNgay, NHANVIEN.GioiTinh, NHANVIEN.DiaChi, NHANVIEN.SDT,chuyenMon = (select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM)FROM  NHANVIEN ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanSu = rs.getString("MaNV");
				String maPB = rs.getString("MaPB");
				String hoDem = rs.getString("HoNV");
				String tenNhanSu = rs.getString("TenNV");
				String chuyenMon = rs.getString("chuyenMon");
				String sdt = rs.getString("SDT");
				int tuoi = rs.getInt("tuoi");
				Double luongTheoNgay = rs.getDouble("LuongTheoNgay");
				Date ngaySinh = rs.getDate("NgaySinh");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				NhanSu nhanSu = new NhanSu(maNhanSu, maPB, hoDem, tenNhanSu, ngaySinh, tuoi, luongTheoNgay, gioiTinh,
						diaChi, sdt, chuyenMon);
				dsNs.add(nhanSu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNs;
	}

	/**
	 * lấy danh sách phòng nhân sự từ database Được sắp xếp theo mã chuyên môn
	 * @return list danh sách các nhân sự
	 */
	public List<NhanSu> getListNhanSuSortTheoCM() {
		List<NhanSu> dsNs = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT NHANVIEN.MaNV, NHANVIEN.MaPB, NHANVIEN.HoNV, NHANVIEN.TenNV, NHANVIEN.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( NHANVIEN.NgaySinh), NHANVIEN.LuongTheoNgay, NHANVIEN.GioiTinh, NHANVIEN.DiaChi, NHANVIEN.SDT,chuyenMon = (select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM)FROM  NHANVIEN order by MaCM");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanSu = rs.getString("MaNV");
				String maPB = rs.getString("MaPB");
				String hoDem = rs.getString("HoNV");
				String tenNhanSu = rs.getString("TenNV");
				String chuyenMon = rs.getString("chuyenMon");
				String sdt = rs.getString("SDT");
				int tuoi = rs.getInt("tuoi");
				Double luongTheoNgay = rs.getDouble("LuongTheoNgay");
				Date ngaySinh = rs.getDate("NgaySinh");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				NhanSu nhanSu = new NhanSu(maNhanSu, maPB, hoDem, tenNhanSu, ngaySinh, tuoi, luongTheoNgay, gioiTinh,
						diaChi, sdt, chuyenMon);
				dsNs.add(nhanSu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNs;
	}
	
	/**
	 * lấy danh sách phòng nhân sự từ database Được sắp xếp theo mã Phòng ban
	 * @return list danh sách các nhân sự
	 */
	public List<NhanSu> getListNhanSuSortTheoPB() {
		List<NhanSu> dsNs = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT NHANVIEN.MaNV, NHANVIEN.MaPB, NHANVIEN.HoNV, NHANVIEN.TenNV, NHANVIEN.NgaySinh,tuoi=YEAR(GETDATE())- YEAR( NHANVIEN.NgaySinh), NHANVIEN.LuongTheoNgay, NHANVIEN.GioiTinh, NHANVIEN.DiaChi, NHANVIEN.SDT,chuyenMon = (select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM)FROM  NHANVIEN order by MaPB");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanSu = rs.getString("MaNV");
				String maPB = rs.getString("MaPB");
				String hoDem = rs.getString("HoNV");
				String tenNhanSu = rs.getString("TenNV");
				String chuyenMon = rs.getString("chuyenMon");
				String sdt = rs.getString("SDT");
				int tuoi = rs.getInt("tuoi");
				Double luongTheoNgay = rs.getDouble("LuongTheoNgay");
				Date ngaySinh = rs.getDate("NgaySinh");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				NhanSu nhanSu = new NhanSu(maNhanSu, maPB, hoDem, tenNhanSu, ngaySinh, tuoi, luongTheoNgay, gioiTinh,
						diaChi, sdt, chuyenMon);
				dsNs.add(nhanSu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNs;
	}
	/**
	 * thêm 1 nhân sự xuống datadase
	 * @param nhân sự
	 * @return boolean
	 */
	public boolean insertNS(NhanSu i) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into NHANVIEN values (?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, i.getMaNhanSu());
			stmt.setString(2, i.getMaPB());
			stmt.setString(3, i.getChuyenMon());
			stmt.setString(4, i.getHoDem());
			stmt.setString(5, i.getTenNhanSu());
			stmt.setBoolean(6, i.isGioiTinh());
			stmt.setDate(7, new java.sql.Date(i.getNgaySinh().getTime()));
			stmt.setString(8, i.getDiaChi());
			stmt.setString(9, i.getSdt());
			stmt.setDouble(10, i.getLuongTheoNgay());
			stmt.setDate(11, new java.sql.Date(System.currentTimeMillis()));
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * lấy danh các nhân sự thuộc phòng ban được truyền vào
	 * @param mã phòng ban
	 * @return list danh sách nhân sự
	 * @throws SQLException
	 */
	public List<NhanSu> getNStheoPB(String PB) throws SQLException {
		List<NhanSu> daNS = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("select MaNV,MaPB,MaCM,HoNV,TenNV,GioiTinh,NgaySinh,DiaChi,SDT,LuongTheoNgay,tuoi=YEAR(GETDATE())-YEAR(NgaySinh) from NHANVIEN where MaPB =?");
			stmt.setString(1, PB);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanSu = rs.getString("MaNV");
				String maPB = rs.getString("MaPB");
				String hoDem = rs.getString("HoNV");
				String tenNhanSu = rs.getString("TenNV");
				String chuyenMon = rs.getString("MaCM");
				String sdt = rs.getString("SDT");
				int tuoi = rs.getInt("tuoi");
				Double luongTheoNgay = rs.getDouble("LuongTheoNgay");
				Date ngaySinh = rs.getDate("NgaySinh");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				NhanSu nhanSu = new NhanSu(maNhanSu, maPB, hoDem, tenNhanSu, ngaySinh, tuoi, luongTheoNgay, gioiTinh,
						diaChi, sdt, chuyenMon);
				daNS.add(nhanSu);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return daNS;
	}
	
	/**
	 * Lấy danh sách nhân sự thuộc công trình với mã công trình được truyền vòa
	 * @param ct là mã công trình
	 * @return list danh sách nhân sự
	 */
	public List<NhanSu> getNsCT(String ct) {
		List<NhanSu> ds= new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT  NHANVIEN.*,chuyenMon =(select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM),PHANCONG.NgayThamGia FROM NHANVIEN INNER JOIN PHANCONG ON NHANVIEN.MaNV = PHANCONG.MaNV where PHANCONG.MaCT = ?");
			stmt.setString(1, ct);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				
				NhanSu ns = new NhanSu(rs.getString("MaNV"),rs.getString("MaPB"), rs.getString("HoNV"),rs.getString("TenNV"),rs.getString("chuyenMon"),rs.getDate("NgayThamGia") );
				ds.add(ns);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ds;
	}
	
	/**
	 * lấy danh sách các nhân sự chưa được phân công vào công trình
	 * @return list danh sách nhân sự
	 */
	public List<NhanSu> getNSChuaCT() {
		List<NhanSu> ds = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("select NHANVIEN.*,chuyenMon =(select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM) from NHANVIEN where MaNV not in (select MaNV from PHANCONG)");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanSu ns = new NhanSu(rs.getString("MaNV"),rs.getString("MaPB"), rs.getString("HoNV"),rs.getString("TenNV"),rs.getString("chuyenMon"),null );
				ds.add(ns);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	/**
	 * lấy danh sách nhân sự chưa tham vào công trình nào theo chuyên môn
	 * @param maCM là mã chuyên môn
	 * @return list danh sách nhân sự
	 */
	public List<NhanSu> getNSChuaCTTheoCM(String maCM) {
		List<NhanSu> ds = new ArrayList<NhanSu>();
		try {
			PreparedStatement stmt = con.prepareStatement("select NHANVIEN.*,chuyenMon =(select CHUYENMON.TenCM from CHUYENMON where CHUYENMON.MaCM = NHANVIEN.MaCM) from NHANVIEN where MaNV not in (select MaNV from PHANCONG) and MaCM =?");
			stmt.setString(1, maCM);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanSu ns = new NhanSu(rs.getString("MaNV"),rs.getString("MaPB"), rs.getString("HoNV"),rs.getString("TenNV"),rs.getString("chuyenMon"),null );
				ds.add(ns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	/**
	 * Xóa 1 nhân sự 
	 * @param mans là mã nhân sự
	 * @return boolean
	 */
	public boolean deleteNS(String mans) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from NHANVIEN where MaNV = ?");
			stmt.setString(1, mans);
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	/**
	 * Sửa Thông Tin của 1 nhân sự
	 * @param i là nhân sự
	 * @return boolean
	 */
	public boolean updateNS(NhanSu i) {
		try {
			PreparedStatement stmt = con.prepareStatement("update NHANVIEN set MaPB=? , MaCM=?,HoNV=?,TenNV=?, GioiTinh=? ,NgaySinh=? , DiaChi=?, SDT=?,LuongTheoNgay=? , NgaySuaDoi= GETDATE() where MaNV = ?");
			stmt.setString(1, i.getMaPB());
			stmt.setString(2, i.getChuyenMon());
			stmt.setString(3, i.getHoDem());
			stmt.setString(4, i.getTenNhanSu());
			stmt.setBoolean(5, i.isGioiTinh());
			stmt.setDate(6, new java.sql.Date(i.getNgaySinh().getTime()));
			stmt.setString(7, i.getDiaChi());
			stmt.setString(8, i.getSdt());
			stmt.setDouble(9, i.getLuongTheoNgay());
			stmt.setString(10, i.getMaNhanSu());
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Lấy thông tin nhân viên dựa theo mã nhân viên
	 * @param taiKhoan
	 * @return thông tin của nhân viên/null
	 */
	public NhanSu getNhanSu(TaiKhoan taiKhoan) {
		 
		try {
			
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM View_NhanVien WHERE MaNV = ?");
			preparedStatement.setString(1,taiKhoan.getTenTK());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				NhanSu ns = new NhanSu(rs.getString("MaNV"), rs.getString("MaPB"), rs.getString("HoNV"),
						rs.getString("TenNV"), rs.getDate("NgaySinh"), rs.getInt("Tuoi"), rs.getDouble("LuongTheoNgay"),
						rs.getBoolean("GioiTinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("MaCM"));
				return ns;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean suaNhanSuTuNguoiDung(NhanSu nhanSu) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("UPDATE NHANVIEN SET HoNV = ?, TenNV = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ? WHERE MaNV = ?");
			preparedStatement.setString(1, nhanSu.getHoDem());
			preparedStatement.setString(2, nhanSu.getTenNhanSu());
			preparedStatement.setBoolean(3, nhanSu.isGioiTinh());
			preparedStatement.setDate(4, new java.sql.Date(nhanSu.getNgaySinh().getTime()));
			preparedStatement.setString(5, nhanSu.getDiaChi());
			preparedStatement.setString(6, nhanSu.getSdt());
			preparedStatement.setString(7, nhanSu.getMaNhanSu());
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
	 * Cập Nhật Mã Phòng Ban Của Nhân Sự Theo Mã Nhân Sự
	 * @param manv
	 * @param maPB
	 * @return true = Cập Nhật Thành Công, false = Cập Nhật Thất Bại
	 */
	public boolean updateNhanSu(String manv, String maPB) {
        String sql = "update NHANVIEN set MaPB = ? where MaNV = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPB);
            stmt.setString(2, manv);
            
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
	
	/**
	 * Lấy tên công việc của nhân viên
	 * @param maNV mã nhân viên
	 * @return công việc của nhân viên
	 */
	public String layCongViec(String maNV) {
		String tenCV=null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("select TenCV from CONGVIEC cv inner join PHANCONG pc on cv.MaCV = pc.MaCV where pc.MaNV=?");
			preparedStatement.setString(1, maNV);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				tenCV=rs.getString("TenCV");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenCV;
	}
	
	/**
	 * Lấy mã công trình của nhân viên
	 * @param maNV mã nhân viên
	 * @return mã công trình của nhân viên
	 */
	public String layCongTrinh(String maNV) {
		String maCT=null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("select MaCT from CONGTRINH ct inner join PHANCONG pc on ct.MaCT = pc.MaCT where pc.MaNV=?");
			preparedStatement.setString(1, maNV);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				maCT=rs.getString("TenCV");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maCT;
	}
	
	/**
	 * Kiểm tra nhân viên có thuộc chuyên môn truyền vào hay không
	 * @return kết quả kiểm tra
	 */
	public boolean kiemTraNhanVien_ChuyenMon(String maNV, String maCM) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement("select MaCM from NHANVIEN where MaNV=?");
			preparedStatement.setString(1, maNV);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				if(rs.getString("MaCM").equals(maCM))
					return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

