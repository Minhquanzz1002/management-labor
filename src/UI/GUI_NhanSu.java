package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import dao.ChuyenMon_dao;
import dao.NhanSu_dao;
import dao.PhanCong_dao;
import dao.PhongBan_dao;
import entity.ChuyenMon;
import entity.NhanSu;
import entity.PhanCong;
import entity.PhongBan;

import javax.swing.JSpinner;

public class GUI_NhanSu extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Nhân Sự

	private JTextField txtMaNS;
	private JTextField txtHoDem;
	private JTextField txtTenNs;
	private JTextField txtTim;

	private DefaultTableModel dtmNhanSu;

	private JTable tblDsNhanSu;
	private JDateChooser txtNgaySinh;
	private JComboBox<String> cboPhongBan;
	private JComboBox<String> cboChuyenMon;
	private JSpinField txtTuoi;
	private JButton btnLuuNS;
	private JButton btnXoaTextNS;
	private JButton btnSuaTTNS;
	private JButton btnXoaNS;
	private JComboBox<String> cboHienThiDS;
	private JButton btnTim;
	private JTextField txtSdt;
	private NhanSu_dao dao_NhanSu = new NhanSu_dao();
	private ChuyenMon_dao dao_ChuyenMon = new ChuyenMon_dao();
	private PhongBan_dao phongBan_dao = new PhongBan_dao();
	private PhanCong_dao phanCong_dao = new PhanCong_dao();
	private JTextField txtDiaChi;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JSpinner txtLuong;
	private ButtonGroup bGGioiTinh;
	private String maNS=null;
	private JScrollPane scrDsNhanSu;

	// Nhân Sự
	public GUI_NhanSu() {
		
	}

	@SuppressWarnings("serial")
	public Component tabNhanSu() {
		
		JPanel pnNhanSu = new JPanel();
		pnNhanSu.setLayout(null);

		JPanel pnNhapThongTin = new JPanel();
		pnNhapThongTin.setBackground(new Color(238, 232, 170));
		pnNhapThongTin.setBounds(0, 35, 979, 113);
		pnNhanSu.add(pnNhapThongTin);
		pnNhapThongTin.setLayout(null);

		JLabel lblPhongBan = new JLabel("Phòng Ban:");
		lblPhongBan.setFont(new Font("Arial", Font.BOLD, 13));
		lblPhongBan.setBounds(10, 53, 90, 20);
		pnNhapThongTin.add(lblPhongBan);

		JLabel lblMaNS = new JLabel("Mã Nhân Sự:");
		lblMaNS.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaNS.setBounds(10, 22, 90, 20);
		pnNhapThongTin.add(lblMaNS);

		txtMaNS = new JTextField();
		txtMaNS.setColumns(10);
		txtMaNS.setBounds(100, 22, 130, 20);
		pnNhapThongTin.add(txtMaNS);

		JLabel lblHoDem = new JLabel("Họ và Đệm:");
		lblHoDem.setFont(new Font("Arial", Font.BOLD, 13));
		lblHoDem.setBounds(263, 22, 90, 20);
		pnNhapThongTin.add(lblHoDem);

		txtHoDem = new JTextField();
		txtHoDem.setColumns(10);
		txtHoDem.setBounds(353, 22, 130, 20);
		pnNhapThongTin.add(txtHoDem);

		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 13));
		lblGioiTinh.setBounds(779, 22, 70, 20);
		pnNhapThongTin.add(lblGioiTinh);

		rdNam = new JRadioButton("Nam");
		rdNam.setActionCommand("Nam");
		rdNam.setBackground(new Color(238, 232, 170));
		rdNam.setFont(new Font("Arial", Font.BOLD, 13));
		rdNam.setBounds(849, 22, 60, 20);
		pnNhapThongTin.add(rdNam);

		rdNu = new JRadioButton("Nữ");
		rdNu.setFont(new Font("Arial", Font.BOLD, 13));
		rdNu.setBackground(new Color(238, 232, 170));
		rdNu.setBounds(909, 22, 60, 20);
		pnNhapThongTin.add(rdNu);

		bGGioiTinh = new ButtonGroup();
		bGGioiTinh.add(rdNam);
		bGGioiTinh.add(rdNu);
		JLabel lblTen = new JLabel("Tên Nhân Sự:");
		lblTen.setFont(new Font("Arial", Font.BOLD, 13));
		lblTen.setBounds(516, 22, 90, 20);
		pnNhapThongTin.add(lblTen);

		txtTenNs = new JTextField();
		txtTenNs.setColumns(10);
		txtTenNs.setBounds(610, 22, 130, 20);
		pnNhapThongTin.add(txtTenNs);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 13));
		lblNgaySinh.setBounds(516, 53, 70, 20);
		pnNhapThongTin.add(lblNgaySinh);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(610, 53, 130, 20);
		txtNgaySinh.setDateFormatString("yyyy-MM-dd");
		((JTextField) (txtNgaySinh.getDateEditor().getUiComponent())).setEditable(false);
		pnNhapThongTin.add(txtNgaySinh);

		cboPhongBan = new JComboBox<String>();
		cboPhongBan.setBounds(100, 53, 130, 20);
		cboPhongBan.addItem("Chọn Phòng Ban");
		cboPhongBan.addItem("Không Có");
		loadDSPB();
		pnNhapThongTin.add(cboPhongBan);

		JLabel lblChuyenMon = new JLabel("Chuyên Môn:");
		lblChuyenMon.setFont(new Font("Arial", Font.BOLD, 13));
		lblChuyenMon.setBounds(263, 53, 90, 20);
		pnNhapThongTin.add(lblChuyenMon);

		cboChuyenMon = new JComboBox<String>();
		cboChuyenMon.setBounds(353, 53, 130, 20);
		cboChuyenMon.addItem("Chọn Chuyên Môn");
		cboChuyenMon.addItem("Không Có");
		dao_ChuyenMon.getDsTenCM(cboChuyenMon);
		pnNhapThongTin.add(cboChuyenMon);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setBackground(Color.CYAN);
		pnTieuDe.setBounds(0, 0, 979, 35);
		pnNhanSu.add(pnTieuDe);
		pnTieuDe.setLayout(null);

		JLabel lblTieuDe = new JLabel("Thêm Nhân Sự Mới");
		lblTieuDe.setBounds(392, 5, 190, 25);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.BLUE);
		pnTieuDe.add(lblTieuDe);
		pnNhapThongTin
				.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Điền Thông Tin Nhân Viên."));

		JLabel lblTui = new JLabel("Tuổi:");
		lblTui.setFont(new Font("Arial", Font.BOLD, 13));
		lblTui.setBounds(779, 53, 83, 20);
		pnNhapThongTin.add(lblTui);

		txtTuoi = new JSpinField();
		txtTuoi.setMinimum(0);
		txtTuoi.setMaximum(100);
		txtTuoi.setBounds(849, 53, 93, 20);
		pnNhapThongTin.add(txtTuoi);

		JLabel lblNewLabel = new JLabel("Số Điện Thoại:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 83, 101, 20);
		pnNhapThongTin.add(lblNewLabel);

		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(110, 83, 120, 20);
		pnNhapThongTin.add(txtSdt);

		JLabel lblLuong = new JLabel("Lương Theo Ngày:");
		lblLuong.setFont(new Font("Arial", Font.BOLD, 13));
		lblLuong.setBounds(263, 84, 120, 20);
		pnNhapThongTin.add(lblLuong);

		txtLuong = new JSpinner();
		txtLuong.setBounds(383, 84, 100, 20);
		pnNhapThongTin.add(txtLuong);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Arial", Font.BOLD, 13));
		lblDiaChi.setBounds(516, 84, 70, 14);
		pnNhapThongTin.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(610, 83, 332, 20);
		pnNhapThongTin.add(txtDiaChi);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBackground(SystemColor.info);
		pnTacVu.setBounds(0, 150, 979, 71);
		pnNhanSu.add(pnTacVu);
		pnTacVu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn Tác Vụ.",
				TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		pnTacVu.setLayout(null);

		btnLuuNS = new JButton("Lưu Thông Tin");
		btnLuuNS.setFont(new Font("Arial", Font.BOLD, 13));
		btnLuuNS.setMnemonic(KeyEvent.VK_L);
		btnLuuNS.setBounds(100, 24, 135, 30);
		pnTacVu.add(btnLuuNS);

		btnXoaTextNS = new JButton("Xóa Trắng");
		btnXoaTextNS.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaTextNS.setBounds(353, 24, 135, 30);
		pnTacVu.add(btnXoaTextNS);

		btnSuaTTNS = new JButton("Sửa Thông Tin");
		btnSuaTTNS.setMnemonic(KeyEvent.VK_S);
		btnSuaTTNS.setFont(new Font("Arial", Font.BOLD, 13));
		btnSuaTTNS.setBounds(610, 24, 135, 30);
		pnTacVu.add(btnSuaTTNS);

		btnXoaNS = new JButton("Xóa Nhân Sự");
		btnXoaNS.setMnemonic(KeyEvent.VK_X);
		btnXoaNS.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaNS.setBounds(826, 24, 135, 30);
		pnTacVu.add(btnXoaNS);

		JLabel lblTim = new JLabel("Tìm Nhân Sự Theo Mã:");
		lblTim.setFont(new Font("Arial", Font.BOLD, 13));
		lblTim.setBounds(10, 236, 156, 25);
		pnNhanSu.add(lblTim);

		txtTim = new JTextField();
		txtTim.setBounds(166, 236, 260, 25);
		pnNhanSu.add(txtTim);
		txtTim.setColumns(10);

		JLabel lblhienThiDS = new JLabel("Hiển Thị Theo:");
		lblhienThiDS.setFont(new Font("Arial", Font.BOLD, 13));
		lblhienThiDS.setBounds(550, 236, 102, 25);
		pnNhanSu.add(lblhienThiDS);

		cboHienThiDS = new JComboBox<String>();
		cboHienThiDS.setBounds(662, 236, 280, 22);
		cboHienThiDS.addItem("Chọn Hiển Thị");
		cboHienThiDS.addItem("Theo Chuyên Môn");
		cboHienThiDS.addItem("Theo Phòng Ban");
		pnNhanSu.add(cboHienThiDS);

		tblDsNhanSu = new JTable(dtmNhanSu = new DefaultTableModel(
				new String[] { "M\u00E3 Nh\u00E2n S\u1EF1", "M\u00E3 Ph\u00F2ng Ban", "H\u1ECD \u0110\u00EAm",
						"T\u00EAn Nh\u00E2n S\u1EF1", "Ng\u00E0y Sinh", "Tu\u1ED5i", "L\u01B0\u01A1ng Ng\u00E0y",
						"Gi\u1EDBi T\u00EDnh", "\u0110\u1ECBa Ch\u1EC9", "SDT", "Chuy\u00EAn M\u00F4n" },
				0)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		scrDsNhanSu = new JScrollPane(tblDsNhanSu);
		tblDsNhanSu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsNhanSu.getTableHeader().setBackground(Color.CYAN);
		scrDsNhanSu.setBounds(10, 18, 959, 235);
		JPanel pnDsNhanSu = new JPanel();
		pnDsNhanSu.setBackground(SystemColor.info);
		((DefaultTableCellRenderer) tblDsNhanSu.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		pnDsNhanSu.setLayout(null);
		pnDsNhanSu.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách Nhân Sự"));
		pnDsNhanSu.setBounds(0, 272, 979, 265);
		pnDsNhanSu.add(scrDsNhanSu);
		pnNhanSu.add(pnDsNhanSu);
		
		
		txtMaNS.setText("NV0301");
		txtDiaChi.setText("Gò Vấp");
		txtHoDem.setText("Nguyễn Văn");
		txtTenNs.setText("Thắng");
		txtLuong.setValue(200000);
		txtNgaySinh.setDate(new Date("10/10/2000"));
		txtSdt.setText("0912345678");
		txtTuoi.setValue(22);
		rdNam.setSelected(true);
		cboChuyenMon.setSelectedIndex(3);
		cboPhongBan.setSelectedIndex(3);

		btnTim = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTim.setBounds(426, 236, 55, 25);
		pnNhanSu.add(btnTim);
		loadDB();
		tblDsNhanSu.addMouseListener(this); 
		btnXoaTextNS.addActionListener(this);
		btnSuaTTNS.addActionListener(this);
		btnLuuNS.addActionListener(this);
		btnXoaNS.addActionListener(this);
		txtNgaySinh.addMouseListener(this);
		btnLuuNS.setRequestFocusEnabled(false);
		btnSuaTTNS.setRequestFocusEnabled(false);
		btnTim.setRequestFocusEnabled(false);
		btnXoaNS.setRequestFocusEnabled(false);
		btnXoaTextNS.setRequestFocusEnabled(false);
		cboHienThiDS.addActionListener(this);
		btnTim.addActionListener(this);
		return pnNhanSu;
	}

	public boolean kiemTraNhap(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	

	public void addRow(NhanSu ns) {
		String gt = ns.isGioiTinh() ? "Nam" : "Nữ";
		String[] a = { ns.getMaNhanSu(), ns.getMaPB(), ns.getHoDem(), ns.getTenNhanSu(), ns.getNgaySinh() + "",
				String.valueOf(ns.getTuoi()), String.valueOf(ns.getLuongTheoNgay()), gt, ns.getDiaChi(), ns.getSdt(),
				ns.getChuyenMon() };
		dtmNhanSu.addRow(a);
	}

	public void loadDB() {
		List<NhanSu> dsNS;
		dsNS = dao_NhanSu.getListNhanSu();
		for (NhanSu nhanSu : dsNS) {
			addRow(nhanSu);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public String regexNhanSu() {
		String regexMaNs = "^NV\\d{4}$";
		String regexSdt = "^0[0-9]{9}";
		String thongBao = "";
		Double luong = Double.parseDouble(txtLuong.getValue().toString());
		if (txtMaNS.getText().trim().isEmpty() || txtHoDem.getText().trim().isEmpty() || txtTenNs.getText().trim().isEmpty() ||
			txtSdt.getText().trim().isEmpty() || txtDiaChi.getText().trim().isEmpty() || (!rdNam.isSelected() && !rdNu.isSelected()) || 
			cboChuyenMon.getSelectedItem().toString().equals("Chọn Chuyên Môn") || cboPhongBan.getSelectedItem().toString().equals("Chọn Phòng Ban")||
			txtNgaySinh.getDate()==null|| txtTuoi.getValue()==0 || luong== 0.0  ) {
			thongBao+="Phải Điền Đầy Đủ Thông Tin!!!"+"\n";
			return thongBao;
		}
		if (!kiemTraNhap(txtMaNS.getText(), regexMaNs)) {
			thongBao += "Mã Nhân sự phải bắt đầu bằng kí tự NS và 4 kí số tiếp theo\n"+"\n";
		}
		if ((new java.util.Date(System.currentTimeMillis()).getYear() - txtNgaySinh.getDate().getYear())<18 ) {
			thongBao+="Ngày sinh hông hợp lệ"+"\n";
		}
		else {
			if (txtTuoi.getValue()!= (new java.util.Date(System.currentTimeMillis()).getYear() - txtNgaySinh.getDate().getYear())) {
				thongBao+="Tuổi với ngày sinh không Hợp lệ"+"\n";
			}
		}
		if (!kiemTraNhap(txtSdt.getText(), regexSdt)) {
			thongBao += "Số điện thoại phải bắt đầu là số 0 và có tất cả 10 chữ số"+"\n";
		}
		return thongBao;
	}
	/**
	 * Xóa Trắng các JTextField
	 */
	public void xoaTrang() {
		dtmNhanSu.setRowCount(0);
		loadDB();
		maNS= null;
		txtDiaChi.setText("");
		txtMaNS.setText("");
		txtHoDem.setText("");
		txtTenNs.setText("");
		txtSdt.setText("");
		txtTuoi.setValue(0);
		txtLuong.setValue(0);
		txtNgaySinh.setDate(null);
		bGGioiTinh.clearSelection();
		tblDsNhanSu.setSelectionMode(0);
		cboChuyenMon.setSelectedItem("Chọn Chuyên Môn");
		cboPhongBan.setSelectedItem("Chọn Phòng Ban");
	}
	
	
	public void loadDSPB() {
		List<PhongBan> dsPB;
		dsPB= phongBan_dao.getDsPB();
		for (PhongBan i : dsPB) {
			cboPhongBan.addItem(i.getMaPB());
		}
	}
	
	public boolean kiemTraPhanCong(String maNV) {
		for (PhanCong i:phanCong_dao.getDsPC()) {
			if (i.getMaNV().equals(maNV)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnXoaTextNS)) {
			xoaTrang();
		}
		else if (e.getSource().equals(btnSuaTTNS)) {
			if (tblDsNhanSu.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Dòng Muốn Thay Đổi thông Tin");
			}
			else {
				String a = btnSuaTTNS.getText();
				if (a.equals("Sửa Thông Tin")) {
					btnSuaTTNS.setText("Hủy");
					btnLuuNS.setText("Lưu Sửa");
					btnXoaNS.setEnabled(false);
					btnXoaTextNS.setEnabled(false);
					cboChuyenMon.addActionListener(new ActionListener() {
					
						@Override
						public void actionPerformed(ActionEvent e) {
							if (btnSuaTTNS.getText().trim().equals("Hủy") &&kiemTraPhanCong(maNS) && !cboChuyenMon.getSelectedItem().toString().trim().equals(dtmNhanSu.getValueAt(tblDsNhanSu.getSelectedRow(), 10))) {
								JOptionPane.showMessageDialog(null, "Nhân Sự "+maNS+" Đang được phân công nên không được chỉnh sửa chuyên môn");
								cboChuyenMon.setSelectedItem(dtmNhanSu.getValueAt(tblDsNhanSu.getSelectedRow(), 10));
								cboChuyenMon.setEnabled(false);
							}
							else {
								cboChuyenMon.setEnabled(true);
							}
						}
					});
				}
				else if (a.equals("Hủy")) {
					btnSuaTTNS.setText("Sửa Thông Tin");
					btnXoaNS.setEnabled(true);
					btnXoaTextNS.setEnabled(true);
					btnLuuNS.setText("Lưu Thông Tin");
					cboChuyenMon.setEnabled(true);
					cboChuyenMon.addActionListener(null);
					}
			}
			
		}else if (e.getSource().equals(btnLuuNS)) {
			if (!regexNhanSu().equals("")) {
				JOptionPane.showMessageDialog(null, regexNhanSu());
			}
			else {
				String maCM = null;
				for (ChuyenMon i: dao_ChuyenMon.getListCM()) {
					if (i.getTenCM().equals(cboChuyenMon.getSelectedItem().toString())) {
						maCM=i.getMaCM();
					}
				}
				NhanSu ns = new NhanSu(txtMaNS.getText(), cboPhongBan.getSelectedItem().toString(), txtHoDem.getText(), txtTenNs.getText(),txtNgaySinh.getDate(),txtTuoi.getValue(), Double.parseDouble(txtLuong.getValue().toString()),  false ,txtDiaChi.getText(),txtSdt.getText(),maCM);
				
				if (btnLuuNS.getText().trim().equals("Lưu Thông Tin")) {
					if(dao_NhanSu.insertNS(ns))
					{
						JOptionPane.showMessageDialog(null, "Thêm Nhân Sự Thành Công.");
						GUI_UNGDUNG.loadDuLieuThongKe();
						dtmNhanSu.setRowCount(0);
						loadDB();
						xoaTrang();
					}
					else {
						JOptionPane.showMessageDialog(null, "Thêm Nhân Sự Không Thành Công.");
					}
				}
				else if (btnLuuNS.getText().trim().equals("Lưu Sửa")) {
					if (dao_NhanSu.updateNS(ns)) {
						JOptionPane.showMessageDialog(null, "Sửa Thông Tin Nhân Sự Thành Công.");
						dtmNhanSu.setRowCount(0);
						loadDB();
						xoaTrang();
					}
					else {
						JOptionPane.showMessageDialog(null, "Sửa Thông Tin Nhân Sự Không Thành Công.");
					}
				}
				}
			}
		else if (e.getSource().equals(btnXoaNS)) {
			if (tblDsNhanSu.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "Phải Chọn Nhân Viên Cần Xóa Trước");
			}
			else {
				if (JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa nhân sự "+dtmNhanSu.getValueAt(tblDsNhanSu.getSelectedRow(),0).toString()+" Không ?","Xóa",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					String ma = dtmNhanSu.getValueAt(tblDsNhanSu.getSelectedRow(), 0).toString();
					if (dao_NhanSu.deleteNS(ma)) {
						dtmNhanSu.removeRow(tblDsNhanSu.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Nhân Sự Đã Được Xóa Thành Công.");
						GUI_UNGDUNG.loadDuLieuThongKe();
						xoaTrang();
					}
					else {
						JOptionPane.showMessageDialog(null, "Xoá Nhân Sự Thất Bại.");
					}
				}
			}
		}
		else if (e.getSource().equals(cboHienThiDS)) {
			System.out.println(tblDsNhanSu.getRowCount());
			int index = cboHienThiDS.getSelectedIndex();
			if (index==0) {
				dtmNhanSu.setRowCount(0);
				loadDB();
			}
			else if (index==1) {
				dtmNhanSu.setRowCount(0);
				for (NhanSu i : dao_NhanSu.getListNhanSuSortTheoCM()) {
					addRow(i);
				}
			}
			else if (index==2) {
				dtmNhanSu.setRowCount(0);
				for (NhanSu i : dao_NhanSu.getListNhanSuSortTheoPB()) {
					addRow(i);
				}
			}
		}
		else if (e.getSource().equals(btnTim)) {
			
			for (int i = 0; i < tblDsNhanSu.getRowCount(); i++) {
				if (dtmNhanSu.getValueAt(i, 0).toString().equals(txtTim.getText())) {
					tblDsNhanSu.changeSelection(i, 0, true, true);
					tblDsNhanSu.setRowSelectionInterval(i, i);
					break;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int n = tblDsNhanSu.getSelectedRow();
		maNS = dtmNhanSu.getValueAt(n, 0).toString();
		String a = tblDsNhanSu.getModel().getValueAt(tblDsNhanSu.getSelectedRow(), 4).toString();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(a);
			txtNgaySinh.setDate(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtMaNS.setText(dtmNhanSu.getValueAt(n, 0).toString());
		txtHoDem.setText(dtmNhanSu.getValueAt(n, 2).toString());
		txtTenNs.setText(dtmNhanSu.getValueAt(n, 3).toString());
		txtTuoi.setValue(Integer.parseInt(dtmNhanSu.getValueAt(n, 5).toString()));
		String gt = dtmNhanSu.getValueAt(n, 7).toString();
		if (gt.equals("Nam")) {
			rdNam.setSelected(true);
		} else {
			rdNu.setSelected(true);
		}
		txtDiaChi.setText(dtmNhanSu.getValueAt(n, 8).toString());
		txtSdt.setText(dtmNhanSu.getValueAt(n, 9).toString());
		if (dtmNhanSu.getValueAt(n, 1) != null) {
			cboPhongBan.setSelectedItem(dtmNhanSu.getValueAt(n, 1).toString());
		} else {
			cboPhongBan.setSelectedItem("Không Có");
		}

		if (dtmNhanSu.getValueAt(n, 10) != null) {
			cboChuyenMon.setSelectedItem(dtmNhanSu.getValueAt(n, 10).toString());
		} else {
			cboChuyenMon.setSelectedItem("Không Có");
		}
		txtLuong.setValue(Double.parseDouble(dtmNhanSu.getValueAt(n, 6).toString()));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int n = tblDsNhanSu.getSelectedRow();
		maNS = dtmNhanSu.getValueAt(n, 0).toString();
		String a = tblDsNhanSu.getModel().getValueAt(tblDsNhanSu.getSelectedRow(), 4).toString();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(a);
			txtNgaySinh.setDate(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtMaNS.setText(dtmNhanSu.getValueAt(n, 0).toString());
		txtHoDem.setText(dtmNhanSu.getValueAt(n, 2).toString());
		txtTenNs.setText(dtmNhanSu.getValueAt(n, 3).toString());
		txtTuoi.setValue(Integer.parseInt(dtmNhanSu.getValueAt(n, 5).toString()));
		String gt = dtmNhanSu.getValueAt(n, 7).toString();
		if (gt.equals("Nam")) {
			rdNam.setSelected(true);
		} else {
			rdNu.setSelected(true);
		}
		txtDiaChi.setText(dtmNhanSu.getValueAt(n, 8).toString());
		txtSdt.setText(dtmNhanSu.getValueAt(n, 9).toString());
		if (dtmNhanSu.getValueAt(n, 1) != null) {
			cboPhongBan.setSelectedItem(dtmNhanSu.getValueAt(n, 1).toString());
		} else {
			cboPhongBan.setSelectedItem("Không Có");
		}

		if (dtmNhanSu.getValueAt(n, 10) != null) {
			cboChuyenMon.setSelectedItem(dtmNhanSu.getValueAt(n, 10).toString());
		} else {
			cboChuyenMon.setSelectedItem("Không Có");
		}
		txtLuong.setValue(Double.parseDouble(dtmNhanSu.getValueAt(n, 6).toString()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
