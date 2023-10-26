package UI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;


import dao.NhanSu_dao;
import dao.TaiKhoan_dao;
import entity.NhanSu;
import entity.TaiKhoan;

import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;

@SuppressWarnings("serial")
public class GUI_NguoiDung extends JFrame implements ActionListener {
	private JTextField txtHo, txtMaNV, txtDiaChi, txtSDT, txtTen;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private NhanSu_dao nhanSu_dao = new NhanSu_dao();
	private TaiKhoan taiKhoan;
	private JRadioButton radNam, radNu;
	private JDateChooser dateNgaySinh;
	private JButton btnLuuThongTin, btnDoiMatKhau;
	private NhanSu nhanSu;
	private JPasswordField currentPassword;
	private JPasswordField newPassword;
	private JPasswordField confirmPassword;

	public GUI_NguoiDung(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
		}

	public Component tabNguoiDung() {
		
		this.nhanSu = nhanSu_dao.getNhanSu(taiKhoan);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 550);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setBackground(Color.CYAN);
		pnTieuDe.setForeground(Color.WHITE);
		getContentPane().add(pnTieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Thông tin người dùng");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 13));
		lblTieuDe.setForeground(Color.BLUE);
		pnTieuDe.add(lblTieuDe);

		JPanel pnDuLieu = new JPanel();
		getContentPane().add(pnDuLieu, BorderLayout.CENTER);
		pnDuLieu.setLayout(null);

		JPanel pnButton = new JPanel();
		pnButton.setBounds(89, 194, 181, 239);
		pnDuLieu.add(pnButton);
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		btnLuuThongTin = new JButton("Lưu thông tin");
		btnLuuThongTin.setFocusable(false);
		pnButton.add(btnLuuThongTin);

		btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setFocusable(false);
		pnButton.add(btnDoiMatKhau);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.GRAY, null));
		pnThongTin.setBounds(270, 0, 602, 433);
		pnDuLieu.add(pnThongTin);
		pnThongTin.setLayout(null);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setBounds(90, 47, 502, 20);
		pnThongTin.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtHo = new JTextField();
		txtHo.setBounds(90, 114, 215, 20);
		pnThongTin.add(txtHo);
		txtHo.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(90, 248, 502, 20);
		pnThongTin.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(90, 315, 502, 20);
		pnThongTin.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setBounds(10, 52, 84, 14);
		pnThongTin.add(lblMaNV);

		JLabel lblHo = new JLabel("Họ");
		lblHo.setLabelFor(txtHo);
		lblHo.setBounds(10, 117, 84, 14);
		pnThongTin.add(lblHo);

		JLabel lblTen = new JLabel("Tên");
		lblTen.setBounds(335, 117, 46, 14);
		pnThongTin.add(lblTen);

		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(377, 114, 215, 20);
		pnThongTin.add(txtTen);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(335, 187, 64, 14);
		pnThongTin.add(lblGioiTinh);

		radNam = new JRadioButton("Nam");
		buttonGroup.add(radNam);
		radNam.setFocusable(false);
		radNam.setBounds(405, 183, 65, 23);
		pnThongTin.add(radNam);

		radNu = new JRadioButton("Nữ");
		buttonGroup.add(radNu);
		radNu.setFocusable(false);
		radNu.setBounds(472, 183, 64, 23);
		pnThongTin.add(radNu);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setBounds(10, 183, 84, 14);
		pnThongTin.add(lblNgaySinh);

		dateNgaySinh = new JDateChooser();
		dateNgaySinh.setBounds(90, 181, 215, 20);
		pnThongTin.add(dateNgaySinh);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(10, 251, 84, 14);
		pnThongTin.add(lblDiaChi);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(10, 318, 84, 14);
		pnThongTin.add(lblSDT);

		JLabel lblImgAvt = new JLabel("");
		lblImgAvt.setForeground(Color.WHITE);
		lblImgAvt.setEnabled(false);
		lblImgAvt.setIcon(new ImageIcon("img\\avt.jpg"));
		lblImgAvt.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgAvt.setBounds(89, 0, 181, 194);
		pnDuLieu.add(lblImgAvt);

		JPanel pnColor = new JPanel();
		pnColor.setBackground(Color.PINK);
		getContentPane().add(pnColor, BorderLayout.SOUTH);

		JLabel lblNewLabel_8 = new JLabel("<html>\r\n\t<br>\r\n\t<br><br>\r\n</html>");
		pnColor.add(lblNewLabel_8);
		loadDataToTextField();
		btnLuuThongTin.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);

		return getContentPane();
	}

	/**
	 * Load dữ liệu lên các JJTextField, JRadioButton và JDateChooer
	 */
	public void loadDataToTextField() {
		txtMaNV.setText(nhanSu.getMaNhanSu());
		txtHo.setText(nhanSu.getHoDem());
		txtTen.setText(nhanSu.getTenNhanSu());
		txtDiaChi.setText(nhanSu.getDiaChi());
		txtSDT.setText(nhanSu.getSdt());
		boolean gioiTinh = nhanSu.isGioiTinh();
		if (gioiTinh) {
			radNam.setSelected(true);
		} else {
			radNu.setSelected(true);
		}
		dateNgaySinh.setDate(nhanSu.getNgaySinh());
	}

	public NhanSu taoNhanSuTuTextFiled() {
		String maNV, ho, ten, diaChi, sdt;
		boolean gioiTinh;
		Date ngaySinh;
		maNV = txtMaNV.getText();
		ho = txtHo.getText();
		ten = txtTen.getText();
		ngaySinh = dateNgaySinh.getDate();
		if (radNam.isSelected()) {
			gioiTinh = true;
		} else {
			gioiTinh = false;
		}
		diaChi = txtDiaChi.getText();
		sdt = txtSDT.getText();
		return new NhanSu(maNV, ho, ten, ngaySinh, gioiTinh, diaChi, sdt);
	}

	/**
	 * show thông báo lỗi dữ liệu và focus lại trường lỗi
	 * 
	 * @param textField trường bị lỗi
	 * @param mess      thông báo lỗi
	 */
	public void showMessenger(JTextField textField, String mess) {
		JOptionPane.showMessageDialog(this, mess);
		textField.selectAll();
		textField.requestFocus();
	}

	/**
	 * Kiểm tra tính hợp lệ của dữ liệu
	 * 
	 * @return kết quả
	 */
	public boolean valid() {
		String hoNV = txtHo.getText();
		if (!hoNV.matches("^[A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữự]+(\s[A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữự]+)*$")) {
			showMessenger(txtSDT, "Họ đệm bắt đầu bằng ký tự hoa, mỗi chữ ngăn cách bởi khoảng trắng");
			return false;
		}
		String tenNV = txtTen.getText();
		if (!tenNV.matches("^[A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữự]+(\s[A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữự]+)*$")) {
			showMessenger(txtSDT, "Tên bắt đầu bằng ký tự hoa, mỗi chữ ngăn cách bởi khoảng trắng");
			return false;
		}
		String sdt = txtSDT.getText();
		if (!sdt.matches("^0[0-9]{9}$")) {
			showMessenger(txtSDT, "Số điện thoại gồm 10 số và bắt đầu bằng số 0");
			return false;
		}
		return true;
	}

	/**
	 * Form đổi mật khẩu. Trả về Current, New và Confirm Password
	 * 
	 * @param frame giao diên hiênt tại
	 * @return mật khẩu hoặc null
	 */
	public Hashtable<String, String> login(JFrame frame) {
		Hashtable<String, String> password = new Hashtable<String, String>();

		JPanel pnTong = new JPanel(new BorderLayout(5, 5));
		JPanel pnLabel = new JPanel(new GridLayout(0, 1, 2, 2));
		pnLabel.add(new JLabel("Mật khẩu cũ", SwingConstants.RIGHT));
		pnLabel.add(new JLabel("Mật khẩu mới", SwingConstants.RIGHT));
		pnLabel.add(new JLabel("Nhập lại mật khẩu", SwingConstants.RIGHT));
		pnTong.add(pnLabel, BorderLayout.WEST);

		JPanel pnControls = new JPanel(new GridLayout(0, 1, 2, 2));
		currentPassword = new JPasswordField(15);
		pnControls.add(currentPassword);
		newPassword = new JPasswordField(15);
		pnControls.add(newPassword);
		confirmPassword = new JPasswordField(15);
		pnControls.add(confirmPassword);
		pnTong.add(pnControls, BorderLayout.CENTER);
		int response;
		String strCurrentPassword, strNewPassword, strConfirmPassword;
		do {
			response = JOptionPane.showConfirmDialog(frame, pnTong, "Đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION);
			strCurrentPassword = new String(currentPassword.getPassword());
			strNewPassword = new String(newPassword.getPassword());
			strConfirmPassword = new String(confirmPassword.getPassword());
		} while (response == JOptionPane.OK_OPTION
				&& !checkResetPassword(strCurrentPassword, strNewPassword, strConfirmPassword));

		if (response == JOptionPane.OK_OPTION) {
			password.put("Current Password", new String(currentPassword.getPassword()));
			password.put("New Password", new String(newPassword.getPassword()));
			password.put("Confirm Password", new String(confirmPassword.getPassword()));
			return password;
		} else {
			return null;
		}
	}

	/**
	 * Kiểm tra dữ liệu mật khẩu nhập vào
	 * @param strCurrentPassword mật khẩu hiệ tại
	 * @param strNewPassword mật khẩu mới
	 * @param strConfirmPassword nhập lại mật khẩu mới
	 * @return kết quả kiểm tra
	 */
	public boolean checkResetPassword(String strCurrentPassword, String strNewPassword, String strConfirmPassword) {
		if (strCurrentPassword.equals(strNewPassword)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới không được trùng mật khẩu cũ");
			return false;
		}
		if (!strNewPassword.equals(strConfirmPassword)) {
			JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không khớp");
			return false;
		}
		if (!strCurrentPassword.equals(taiKhoan.getMatKhau())) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không khớp");
			return false;
		}
		if (!strNewPassword.matches("^[^\s]{6,}$")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu ít nhất 6 ký tự và không chứa khoảng trắng");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		if (action == btnLuuThongTin) {
			if (valid()) {
				NhanSu nhanSuMoi = taoNhanSuTuTextFiled();
				if (nhanSu.equals(nhanSuMoi)) {
					JOptionPane.showMessageDialog(this, "Không có dữ liệu nào được thay đổi");
				} else {
					if (nhanSu_dao.suaNhanSuTuNguoiDung(nhanSuMoi)) {
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin không thành công");
					}
				}
			}
		} else {
			Hashtable<String, String> password = login(this);
			if (password != null) {
				TaiKhoan taiKhoanMoi = new TaiKhoan(taiKhoan.getTenTK(), password.get("New Password"));
				TaiKhoan_dao daoTaiKhoan = new TaiKhoan_dao();
				if (daoTaiKhoan.suaTaiKhoan(taiKhoanMoi)) {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu không thành công");
				}
			}
		}
	}
}
