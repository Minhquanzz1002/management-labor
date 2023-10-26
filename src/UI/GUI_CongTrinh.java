package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.CongTrinh_dao;
import entity.CongTrinh;

public class GUI_CongTrinh extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiemCT;
	private JTable tblCT;
	private JTextField txtMaCT;
	private JTextField txtTenCT;
	private JTextField txtDiaDiemCT;
	private JTextField txtSoLuongNhanSuCT;
	private JButton btnXemCV;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private JTextField txtGhiChuCT;

	private DefaultTableModel dtmCongTrinh;
	private CongTrinh_dao congTrinh_DAO = new CongTrinh_dao();
	private JDateChooser dcNgayHoanThanh;
	private JDateChooser dCNgayKhoiCong;
	private JDateChooser dCNgayCapPhep;
	private JComboBox<String> cboTrangThai;
	private JButton btnTimCT;
	private JComboBox<String> cboHienThiCT;
	private JPanel pnCha;
	private JButton btnSua;
	private int rowSelected;

	/**
	 * Giao diện các công trình
	 */
	public GUI_CongTrinh() {
	}

	/**
	 * Tab hiển thị danh sách công trình
	 * 
	 * @return tab hiển thị danh sách công trình
	 */
	@SuppressWarnings("deprecation")
	public Component tabCongTrinh() {
		pnCha = new JPanel();
		pnCha.setBounds(0, 0, 979, 554);
		pnCha.setLayout(new BorderLayout(0, 0));

		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.CYAN);
		pnCha.add(pnNorth, BorderLayout.NORTH);

		JLabel lblThemCT = new JLabel("Thêm Công Trình");
		lblThemCT.setForeground(Color.BLUE);
		lblThemCT.setFont(new Font("Arial", Font.BOLD, 20));
		pnNorth.add(lblThemCT);

		JPanel pnCenTer = new JPanel();
		pnCha.add(pnCenTer, BorderLayout.CENTER);
		pnCenTer.setLayout(null);

		JPanel pnNhapCT = new JPanel();
		pnNhapCT.setBackground(new Color(238, 232, 170));
		pnNhapCT.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thêm công trình"));
		pnNhapCT.setBounds(0, 0, 979, 152);
		pnNhapCT.setLayout(null);

		JLabel lblMaDCT = new JLabel("Mã công trình:");
		lblMaDCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaDCT.setBounds(10, 22, 95, 20);
		pnNhapCT.add(lblMaDCT);

		txtMaCT = new JTextField();
		txtMaCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMaCT.setColumns(10);
		txtMaCT.setBounds(116, 22, 103, 20);
		pnNhapCT.add(txtMaCT);

		JLabel lblTenCT = new JLabel("Tên công trình:");
		lblTenCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblTenCT.setBounds(233, 22, 100, 20);
		pnNhapCT.add(lblTenCT);

		txtTenCT = new JTextField();
		txtTenCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTenCT.setColumns(10);
		txtTenCT.setBounds(337, 22, 135, 20);
		pnNhapCT.add(txtTenCT);

		JLabel lblDiaDiemCT = new JLabel("Địa điểm thi công:");
		lblDiaDiemCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblDiaDiemCT.setBounds(493, 22, 116, 20);
		pnNhapCT.add(lblDiaDiemCT);

		JLabel lblNgayCapPhep = new JLabel("Ngày cấp phép:");
		lblNgayCapPhep.setFont(new Font("Arial", Font.BOLD, 13));
		lblNgayCapPhep.setBounds(10, 50, 99, 20);
		pnNhapCT.add(lblNgayCapPhep);

		dCNgayCapPhep = new JDateChooser();
		dCNgayCapPhep.setDateFormatString("yyyy-MM-dd");
		((JTextField) dCNgayCapPhep.getDateEditor().getUiComponent()).setEditable(false);
		dCNgayCapPhep.setBounds(116, 50, 103, 20);
		pnNhapCT.add(dCNgayCapPhep);

		txtDiaDiemCT = new JTextField();
		txtDiaDiemCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDiaDiemCT.setColumns(10);
		txtDiaDiemCT.setBounds(618, 22, 135, 20);
		pnNhapCT.add(txtDiaDiemCT);

		JLabel lblNgayKhoiCong = new JLabel("Ngày khởi công:");
		lblNgayKhoiCong.setFont(new Font("Arial", Font.BOLD, 13));
		lblNgayKhoiCong.setBounds(228, 50, 105, 20);
		pnNhapCT.add(lblNgayKhoiCong);

		dCNgayKhoiCong = new JDateChooser();
		dCNgayKhoiCong.setDateFormatString("yyyy-MM-dd");
		((JTextField) dCNgayKhoiCong.getDateEditor().getUiComponent()).setEditable(false);
		dCNgayKhoiCong.setBounds(337, 50, 103, 20);
		pnNhapCT.add(dCNgayKhoiCong);

		JLabel lblNgayHoanThanh = new JLabel("Ngày hoàn thành dự kiến:");
		lblNgayHoanThanh.setFont(new Font("Arial", Font.BOLD, 13));
		lblNgayHoanThanh.setBounds(448, 50, 169, 20);
		pnNhapCT.add(lblNgayHoanThanh);

		dcNgayHoanThanh = new JDateChooser();
		dcNgayHoanThanh.setDateFormatString("yyyy-MM-dd");
		((JTextField) dcNgayHoanThanh.getDateEditor().getUiComponent()).setEditable(false);
		dcNgayHoanThanh.setBounds(618, 50, 103, 20);
		pnNhapCT.add(dcNgayHoanThanh);

		JLabel lblTrangThaiCT = new JLabel("Trạng thái:");
		lblTrangThaiCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblTrangThaiCT.setBounds(774, 22, 74, 20);
		pnNhapCT.add(lblTrangThaiCT);

		JLabel lblSLNhanSuCT = new JLabel("Số lượng nhân sự:");
		lblSLNhanSuCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblSLNhanSuCT.setBounds(725, 50, 123, 20);
		pnNhapCT.add(lblSLNhanSuCT);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.addItem("Chờ khởi công");
		cboTrangThai.addItem("Thi công");
		cboTrangThai.addItem("Tạm hoãn");
		cboTrangThai.addItem("Đình chỉ");
		cboTrangThai.addItem("Hoàn thành");
		cboTrangThai.setFont(new Font("Arial", Font.PLAIN, 13));
		cboTrangThai.setBounds(849, 22, 110, 20);
		pnNhapCT.add(cboTrangThai);

		txtSoLuongNhanSuCT = new JTextField();
		txtSoLuongNhanSuCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtSoLuongNhanSuCT.setEditable(false);
		txtSoLuongNhanSuCT.setColumns(10);
		txtSoLuongNhanSuCT.setBounds(849, 50, 110, 20);
		pnNhapCT.add(txtSoLuongNhanSuCT);

		btnXemCV = new JButton("Xem Công Việc");
		btnXemCV.setFont(new Font("Arial", Font.BOLD, 13));
		btnXemCV.setBounds(116, 106, 135, 30);
		pnNhapCT.add(btnXemCV);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Arial", Font.BOLD, 13));
		btnThem.setBounds(269, 106, 135, 30);
		pnNhapCT.add(btnThem);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Arial", Font.BOLD, 13));
		btnLuu.setBounds(422, 106, 135, 30);
		pnNhapCT.add(btnLuu);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaTrang.setBounds(728, 106, 135, 30);
		pnNhapCT.add(btnXoaTrang);

		JLabel lblGhiChuCT = new JLabel("Ghi Chú:");
		lblGhiChuCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblGhiChuCT.setBounds(10, 78, 74, 20);
		pnNhapCT.add(lblGhiChuCT);

		txtGhiChuCT = new JTextField();
		txtGhiChuCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtGhiChuCT.setColumns(10);
		txtGhiChuCT.setBounds(116, 78, 844, 20);
		pnNhapCT.add(txtGhiChuCT);
		pnCenTer.add(pnNhapCT);
		JPanel pnTableCT = new JPanel();
		pnTableCT.setBackground(SystemColor.info);
		pnTableCT.setBounds(0, 193, 976, 305);
		pnTableCT.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách công trình"));
		pnCenTer.add(pnTableCT);
		pnTableCT.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 956, 274);
		pnTableCT.add(scrollPane);

		tblCT = new JTable();
		tblCT.setModel(
				dtmCongTrinh = new DefaultTableModel(
						new String[] { "M\u00E3 C\u00F4ng Tr\u00ECnh", "T\u00EAn C\u00F4ng Tr\u00ECnh",
								"\u0110\u1ECBa \u0110i\u1EC3m Thi C\u00F4ng", "Ng\u00E0y C\u1EA5p Ph\u00E9p",
								"Ng\u00E0y Kh\u1EDFi C\u00F4ng", "Ng\u00E0y Ho\u00E0n Th\u00E0nh D\u1EF1 Ki\u1EBFn",
								"Tr\u1EA1ng Th\u00E1i", "S\u1ED1 L\u01B0\u1EE3ng Nh\u00E2n S\u1EF1", "Ghi Ch\u00FA" },
						0) {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {
						return false;

					};
				});
		tblCT.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblCT.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblCT.getColumnModel().getColumn(7).setPreferredWidth(84);
		tblCT.getColumnModel().getColumn(8).setPreferredWidth(66);
		scrollPane.setViewportView(tblCT);
		tblCT.getTableHeader().setBackground(Color.CYAN);
		tblCT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowSelected = tblCT.getSelectedRow();
				if (rowSelected >= 0) {
					reviewRowSelected(rowSelected);
					if (btnThem.getText().equals("Thêm"))
						btnSua.setEnabled(true);
				}
			}
		});
		JPanel pnTim = new JPanel();
		pnTim.setBounds(0, 145, 976, 40);
		pnCenTer.add(pnTim);
		pnTim.setLayout(null);

		JLabel lblTimCT = new JLabel("Tìm công trình theo mã:");
		lblTimCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblTimCT.setBounds(116, 14, 160, 25);
		pnTim.add(lblTimCT);

		txtTimKiemCT = new JTextField();
		txtTimKiemCT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTimKiemCT.setBounds(280, 14, 170, 25);
		pnTim.add(txtTimKiemCT);
		txtTimKiemCT.setColumns(10);

		JLabel lblHienThiCT = new JLabel("Hiển thị theo:");
		lblHienThiCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblHienThiCT.setBounds(558, 14, 90, 25);
		pnTim.add(lblHienThiCT);

		cboHienThiCT = new JComboBox<String>();
		cboHienThiCT.addItem("Tất cả");
		cboHienThiCT.addItem("Trạng thái thi công");
		cboHienThiCT.addItem("Trạng thái chờ khởi công");
		cboHienThiCT.addItem("Trạng thái tạm hoãn");
		cboHienThiCT.addItem("Trạng thái đình chỉ");
		cboHienThiCT.addItem("Trạng thái đã hoàn thành");
		cboHienThiCT.setBounds(653, 14, 210, 25);
		pnTim.add(cboHienThiCT);

		btnTimCT = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimCT.setBounds(450, 14, 55, 25);
		pnTim.add(btnTimCT);

		btnLuu.setFocusable(false);
		btnThem.setFocusable(false);
		btnXoaTrang.setFocusable(false);
		btnXemCV.setFocusable(false);
		btnTimCT.setFocusable(false);

		// themData
		load();

		// settext
		txtMaCT.setText("CT011");
		txtTenCT.setText("Công trình nhà cấp 4");
		txtDiaDiemCT.setText("Quận 1");
		dCNgayCapPhep.setDate(new Date("05/07/2022"));
		dCNgayKhoiCong.setDate(new Date("05/10/2022"));
		dcNgayHoanThanh.setDate(new Date("05/11/2022"));
		txtSoLuongNhanSuCT.setText("0");
		txtGhiChuCT.setText("");

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Arial", Font.BOLD, 13));
		btnSua.setFocusable(false);
		btnSua.setBounds(575, 106, 135, 30);
		pnNhapCT.add(btnSua);

		moKhoaThaoTac(false);
		btnSua.setEnabled(false);

		// event
		btnXemCV.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTimCT.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		cboHienThiCT.addActionListener(this);
		
		btnThem.setMnemonic(KeyEvent.VK_T);
		btnLuu.setMnemonic(KeyEvent.VK_L);
		btnSua.setMnemonic(KeyEvent.VK_S);
		btnXoaTrang.setMnemonic(KeyEvent.VK_X);
		btnXemCV.setMnemonic(KeyEvent.VK_C);
		btnTimCT.setMnemonic(KeyEvent.VK_ENTER);
		
		//set ToolTipText
		btnXemCV.setToolTipText("Alt+C Xem danh sách công việc trong công trình đang được chọn");
		btnThem.setToolTipText("Alt+T Thêm công trình mới vào danh sách");
		btnLuu.setToolTipText("Alt+L Lưu lại dữ liệu thực hiện việc thêm hoặc sửa công trình");
		btnSua.setToolTipText("Alt+S Sửa công trình trong danh sách");
		btnXoaTrang.setToolTipText("Alt+X Clear tất cả dữ liệu nhập");
		btnTimCT.setToolTipText("Alt+Enter Tìm kiếm công trình theo mã");

		txtMaCT.setToolTipText("Mã công trình bắt đầu bằng CT theo sau là 3 số");
		((JTextField)dCNgayCapPhep.getDateEditor().getUiComponent()).setToolTipText("Ngày cấp phép phải trước ngày hiện tại!");
		((JTextField)dCNgayKhoiCong.getDateEditor().getUiComponent()).setToolTipText("Ngày khởi công phải sau ngày cấp phép!");
		((JTextField)dcNgayHoanThanh.getDateEditor().getUiComponent()).setToolTipText("Ngày hoàn thành dự kiến phải sau ngày khởi công!");
		txtSoLuongNhanSuCT.setToolTipText("Số lượng nhân sự trong công trình");
		cboTrangThai.setToolTipText("Trạng thái công trình");
		cboHienThiCT.setToolTipText("Hiển thị danh sách công trình theo trạng thái");
		txtTimKiemCT.setToolTipText("Mã công trình bắt đầu bằng CT theo sau là 3 số");
		
		return pnCha;

	}

	/**
	 * Load dữ liệu từ database lên table chính
	 */
	public void load() {
		List<CongTrinh> ds;
		ds = congTrinh_DAO.getDSCT();
		for (CongTrinh congTrinh : ds) {
			addRow(congTrinh);
		}
	}

	/**
	 * Thêm 1 dòng vào table chính
	 * 
	 * @param ct công trình cần thêm vào table
	 */
	public void addRow(CongTrinh ct) {
		String[] row = { ct.getMaCT(), ct.getTenCT(), ct.getDiaChi(), ct.getNgayCapPhep() + "",
				ct.getNgayKhoiCong() + "", ct.getNgayHTDuKien() + "", ct.getTrangThai(), ct.getSoLuongNS() + "",
				ct.getGhiChu() };
		dtmCongTrinh.addRow(row);
	}

	/**
	 * Mở khóa các button và textField thực hiện việc thêm hoặc sửa dữ liệu,
	 * 
	 * 
	 * @param b true=mở, false=tắt
	 */
	public void moKhoaThaoTac(boolean b) {
		btnLuu.setEnabled(b);
		btnXoaTrang.setEnabled(b);
		btnXemCV.setEnabled(!b);
		btnTimCT.setEnabled(!b);
		btnThem.setEnabled(!b);
		btnSua.setEnabled(!b);
		txtTimKiemCT.setEditable(!b);
		txtMaCT.setEditable(b);
		txtTenCT.setEditable(b);
		txtDiaDiemCT.setEditable(b);
		txtGhiChuCT.setEditable(b);
		cboTrangThai.setEnabled(b);
		dCNgayCapPhep.setEnabled(b);
		dCNgayKhoiCong.setEnabled(b);
		dcNgayHoanThanh.setEnabled(b);
	}

	/**
	 * Đưa dữ liệu từ table chính lên các textField và chọn dòng(bôi đen) trên table
	 * từ vị trí truyền vào
	 * 
	 * @param rowselected vị trí truyền vào
	 */
	public void reviewRowSelected(int rowSelected) {
		txtMaCT.setText(tblCT.getValueAt(rowSelected, 0) + "");
		txtTenCT.setText(tblCT.getValueAt(rowSelected, 1) + "");
		txtDiaDiemCT.setText(tblCT.getValueAt(rowSelected, 2) + "");
		try {
			if (tblCT.getModel().getValueAt(rowSelected, 3).equals("null"))
				dCNgayCapPhep.setDate(null);
			else
				dCNgayCapPhep.setDate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(tblCT.getModel().getValueAt(rowSelected, 3).toString()));
			if (tblCT.getModel().getValueAt(rowSelected, 4).equals("null"))
				dCNgayKhoiCong.setDate(null);
			else
				dCNgayKhoiCong.setDate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(tblCT.getModel().getValueAt(rowSelected, 4).toString()));
			if (tblCT.getModel().getValueAt(rowSelected, 5).equals("null"))
				dcNgayHoanThanh.setDate(null);
			else
				dcNgayHoanThanh.setDate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(tblCT.getModel().getValueAt(rowSelected, 5).toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cboTrangThai.setSelectedItem(tblCT.getValueAt(rowSelected, 6) + "");
		txtSoLuongNhanSuCT.setText(tblCT.getValueAt(rowSelected, 7) + "");
		txtGhiChuCT.setText(tblCT.getValueAt(rowSelected, 8) + "");
		tblCT.setRowSelectionInterval(rowSelected, rowSelected);
	}

	/**
	 * Kiểm tra ràng buộc dữ liệu trên các textField
	 * 
	 * @return true=đúng, false=sai
	 */
	public boolean regexCongTrinh() {
		if (txtMaCT.getText().trim().isEmpty()) {
			showMess("Mã công trình không được để trống");
			txtMaCT.requestFocus();
			return false;
		}
		if (txtTenCT.getText().trim().isEmpty()) {
			showMess("Tên công trình không được để trống");
			txtTenCT.requestFocus();
			return false;
		}
		if (txtDiaDiemCT.getText().trim().isEmpty()) {
			showMess("Địa điểm thi công không được để trống");
			txtDiaDiemCT.requestFocus();
			return false;
		}
		if ((dCNgayCapPhep.getDate() + "").equals("null")) {
			showMess("Ngày cấp phép không được để trống");
			return false;
		}
		if (!txtMaCT.getText().toUpperCase().matches("(CT)[0-9]{3}")) {//mã công trình phải bắt đầu bằng Ct và theo sau là 3 số
			showMess("Mã công trình không hợp lệ");
			txtMaCT.requestFocus();
			return false;
		}
		if (dCNgayCapPhep.getDate().after(new Date())) {
			showMess("Ngày cấp phép phải trước ngày hiện tại!");
			return false;
		}
		if (!(dCNgayKhoiCong.getDate()+"").equals("null")
				&& dCNgayKhoiCong.getDate().before(dCNgayCapPhep.getDate())) {
			showMess("Ngày khởi công phải sau ngày cấp phép!");
			return false;
		}
		if (!(dcNgayHoanThanh.getDate()+"").equals("null")
				&& dcNgayHoanThanh.getDate().before(dCNgayKhoiCong.getDate())) {
			showMess("Ngày hoàn thành phải sau ngày khởi công!");
			return false;
		}
		return true;
	}

	/**
	 * Hiển thị thông báo dạng Joption.showMessageDialog
	 * 
	 * @param thongBao thông báo cần hiển thị
	 */
	public void showMess(String thongBao) {
		JOptionPane.showMessageDialog(null, thongBao);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnXemCV)) {
			Component pnCV;// tạo component để hiển thị trên Joption
			rowSelected = tblCT.getSelectedRow();
			Object[] r = { "Thoát" };
			if (rowSelected >= 0)
				pnCV = new GUI_CongViec(dtmCongTrinh.getValueAt(rowSelected, 0).toString()).xemCongViec();
			else
				pnCV = new GUI_CongViec("Tất cả").xemCongViec();
			JOptionPane.showOptionDialog(null, pnCV, "", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null,
					r, null);// hiển thị option với combonent chứa dánh sách công việc truyền vào
		} else if (e.getSource().equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				moKhoaThaoTac(true);
				btnThem.setText("Hủy");
				btnThem.setEnabled(true);
			} else {
				moKhoaThaoTac(false);
				btnThem.setText("Thêm");
				if (tblCT.getSelectedRow() < 0)// nếu dòng trên table chưa được chọn thì button sửa sẽ không được thực
												// hiện
					btnSua.setEnabled(false);
			}

		} else if (e.getSource().equals(btnXoaTrang)) {
			txtTenCT.setText("");
			txtDiaDiemCT.setText("");
			cboTrangThai.setSelectedItem("Chờ khởi công");
			dCNgayCapPhep.setDate(null);
			dCNgayKhoiCong.setDate(null);
			dcNgayHoanThanh.setDate(null);
			txtSoLuongNhanSuCT.setText("0");
			txtGhiChuCT.setText("");
			if (btnSua.getText().equals("Sửa"))// chức năng sửa đang không được thực hiện
				txtMaCT.setText("");
		} else if (e.getSource().equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				moKhoaThaoTac(true);
				txtMaCT.setEditable(false);
				btnSua.setText("Hủy");
				btnSua.setEnabled(true);
			} else {
				moKhoaThaoTac(false);
				btnSua.setText("Sửa");
			}

		} else if (e.getSource().equals(btnLuu)) {
			if (regexCongTrinh()) {// kiểm tra ràng buộc dữ liệu đúng
				if (btnThem.getText().equals("Hủy")) {// Chức năng thêm đang được thực hiện
					SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
					String[] r = { txtMaCT.getText().trim(), txtTenCT.getText().trim(), txtDiaDiemCT.getText().trim(),
							sdt.format(dCNgayCapPhep.getDate()), "", "", cboTrangThai.getSelectedItem() + "", "0",
							txtGhiChuCT.getText().trim() };
					if (!(dCNgayKhoiCong.getDate() + "").equals("null"))// ngày khởi công khác null
						r[4] = sdt.format(dCNgayKhoiCong.getDate());
					if (!(dcNgayHoanThanh.getDate() + "").equals("null"))// ngày hoàn thành khác null
						r[5] = sdt.format(dCNgayKhoiCong.getDate());
					CongTrinh ct = new CongTrinh(r[0], r[1], r[2], dCNgayCapPhep.getDate(), dCNgayKhoiCong.getDate(),
							dcNgayHoanThanh.getDate(), r[6], Integer.parseInt(r[7]), r[8]);
					if (congTrinh_DAO.getDSCT().contains(ct)) {
						showMess("Mã công trình đã tồn tại!");
					} else {
						if (congTrinh_DAO.addCongTrinh(ct)) {
							dtmCongTrinh.addRow(r);
							tblCT.setRowSelectionInterval(tblCT.getRowCount() - 1, tblCT.getRowCount() - 1);
							showMess("Thêm công trình thành công!");
							GUI_UNGDUNG.loadDuLieuThongKe();
						} else
							showMess("Không thể thêm công trình!");
					}
				} else {// Chức năng sửa đang được thực hiện
					rowSelected = tblCT.getSelectedRow();
					SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
					String[] r = { txtMaCT.getText().trim(), txtTenCT.getText().trim(), txtDiaDiemCT.getText().trim(),
							sdt.format(dCNgayCapPhep.getDate()), "", "", cboTrangThai.getSelectedItem() + "", "0",
							txtGhiChuCT.getText().trim() };
					if (!(dCNgayKhoiCong.getDate() + "").equals("null"))// ngày khởi công khác null sẽ thực hiện việc ép
																		// kiểu date
						r[4] = sdt.format(dCNgayKhoiCong.getDate());
					if (!(dcNgayHoanThanh.getDate() + "").equals("null"))// ngày hoàn thành khác null sẽ thực hiện việc
																			// ép kiểu date
						r[5] = sdt.format(dCNgayKhoiCong.getDate());
					CongTrinh ct = new CongTrinh(r[0], r[1], r[2], dCNgayCapPhep.getDate(), dCNgayKhoiCong.getDate(), dcNgayHoanThanh.getDate(), r[6],Integer.parseInt(r[7]),r[8]);
					if (congTrinh_DAO.setCongTrinh(ct)) {
						dtmCongTrinh.setValueAt(r[1], rowSelected, 1);
						dtmCongTrinh.setValueAt(r[2], rowSelected, 2);
						dtmCongTrinh.setValueAt(r[3], rowSelected, 3);
						dtmCongTrinh.setValueAt(r[4], rowSelected, 4);
						dtmCongTrinh.setValueAt(r[5], rowSelected, 5);
						dtmCongTrinh.setValueAt(r[6], rowSelected, 6);
						dtmCongTrinh.setValueAt(r[8], rowSelected, 8);
						showMess("Sửa công trình thành công!");
						GUI_UNGDUNG.loadDuLieuThongKe();
					} else
						showMess("Không thể sửa công trình!");
				}
			}
		} else if (e.getSource().equals(btnTimCT)) {
			String maCTTim = txtTimKiemCT.getText();
			cboHienThiCT.setSelectedItem("Tất cả");
				if (!maCTTim.isEmpty()) {
					if (!(txtTimKiemCT.getText().toUpperCase() + "").matches("^CT[0-9]{3}"))// mã công trình bắt đầu bằng CT vào theo sau là 3 số
						showMess("Mã công trình không hợp lệ!");
					else {
					dtmCongTrinh.setRowCount(0);
					cboHienThiCT.setEnabled(false);
					for (CongTrinh congTrinh : congTrinh_DAO.getDSCT()) {
						if (congTrinh.getMaCT().equalsIgnoreCase(maCTTim))
							addRow(congTrinh);
						if (tblCT.getRowCount() > 0)
							reviewRowSelected(0);
					}
					}
				} else {
					cboHienThiCT.setEnabled(true);
					dtmCongTrinh.setRowCount(0);
					load();
				}
		} else// combobox Hiển thị
		{
			String valueSelected = (String) cboHienThiCT.getSelectedItem();
			dtmCongTrinh.setRowCount(0);
			if (valueSelected.equals("Tất cả"))
				load();
			else {
				String trangThaiSelected = valueSelected.substring(11, valueSelected.length());
				for (CongTrinh congTrinh : congTrinh_DAO.getDSCT()) {
					if (congTrinh.getTrangThai().equalsIgnoreCase(trangThaiSelected))
						addRow(congTrinh);
				}
			}
		}
	}
}

