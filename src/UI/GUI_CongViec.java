package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenMon_dao;
import dao.CongTrinh_dao;
import dao.CongViec_dao;
import entity.ChuyenMon;
import entity.CongTrinh;
import entity.CongViec;
import entity.PhanCong;

public class GUI_CongViec extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaCV;
	private JTable tblCV;
	private JTextField txtTimCV;
	private JTextField txtTenCV;
	private CongViec_dao congViec_DAO;
	private JComboBox<String> cboCT_CV;
	private CongTrinh_dao congTrinh_DAO;
	private String maCT;
	private DefaultTableModel dtm;
	private JTextArea txtMoTa;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoaCV;
	private JSpinner txtSLnsMax;
	private JTextField txtSLnsPresent;
	private JButton btnTimCV;
	private JComboBox<String> cboChuyenMonCV;
	private int rowSelected;
	private ChuyenMon_dao chuyenMon;

	/**
	 * Công việc trong công trình
	 * 
	 * @param maCT mã công trình muốn hiển thị danh sách công việc
	 */
	public GUI_CongViec(String maCT) {
		congViec_DAO = new CongViec_dao();
		congTrinh_DAO = new CongTrinh_dao();
		this.maCT = maCT;
	}

	/**
	 * Danh sách công việc trong công trình
	 * 
	 * @return component công việc
	 */
	public Component xemCongViec() {
		JPanel pnCV = new JPanel();
		pnCV.setPreferredSize(new Dimension(790, 454));
		pnCV.setLayout(null);

		JPanel pnThemCV = new JPanel();
		pnThemCV.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thêm công việc"));
		pnThemCV.setBackground(new Color(238, 232, 170));
		pnThemCV.setBounds(5, 45, 780, 155);
		pnCV.add(pnThemCV);
		pnThemCV.setLayout(null);

		JLabel lblMaCV = new JLabel("Mã công việc:");
		lblMaCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaCV.setBounds(10, 22, 93, 20);
		pnThemCV.add(lblMaCV);

		txtMaCV = new JTextField();
		txtMaCV.setBounds(110, 22, 160, 20);
		txtMaCV.setColumns(10);
		pnThemCV.add(txtMaCV);

		JLabel lblChuyenMonCV = new JLabel("Chuyên môn:");
		lblChuyenMonCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblChuyenMonCV.setBounds(10, 54, 86, 20);
		pnThemCV.add(lblChuyenMonCV);

		cboChuyenMonCV = new JComboBox<String>();
		chuyenMon = new ChuyenMon_dao();
		for (ChuyenMon cm : chuyenMon.getListCM()) {
			cboChuyenMonCV.addItem(cm.getTenCM());
		}
		cboChuyenMonCV.setBounds(110, 54, 160, 20);
		pnThemCV.add(cboChuyenMonCV);

		JLabel lblTenCV = new JLabel("Tên công việc:");
		lblTenCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblTenCV.setBounds(10, 86, 93, 20);
		pnThemCV.add(lblTenCV);

		txtTenCV = new JTextField();
		txtTenCV.setBounds(110, 86, 160, 20);
		txtTenCV.setColumns(10);
		pnThemCV.add(txtTenCV);

		JLabel lblMoTaCV = new JLabel("Mô tả công việc:");
		lblMoTaCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblMoTaCV.setBounds(300, 54, 113, 20);
		pnThemCV.add(lblMoTaCV);

		txtMoTa = new JTextArea();
		txtMoTa.setLineWrap(true);
		txtMoTa.setColumns(5);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setRows(3);

		txtMoTa.setBounds(300, 76, 173, 60);
		pnThemCV.add(txtMoTa);

		JLabel lblNSToiDaCV = new JLabel("Số nhân sự tối đa:");
		lblNSToiDaCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblNSToiDaCV.setBounds(10, 118, 123, 20);
		pnThemCV.add(lblNSToiDaCV);

		JLabel lblNSHienTai = new JLabel("Số nhân sự hiện tại:");
		lblNSHienTai.setFont(new Font("Arial", Font.BOLD, 13));
		lblNSHienTai.setBounds(300, 22, 136, 20);
		pnThemCV.add(lblNSHienTai);

		JPanel pnButCV = new JPanel();
		pnButCV.setBackground(new Color(238, 232, 170));
		pnButCV.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnButCV.setBounds(490, 17, 276, 123);
		pnThemCV.add(pnButCV);
		pnButCV.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Arial", Font.BOLD, 12));
		btnThem.setBounds(15, 25, 120, 30);
		pnButCV.add(btnThem);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Arial", Font.BOLD, 12));
		btnLuu.setBounds(145, 25, 120, 30);
		pnButCV.add(btnLuu);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Arial", Font.BOLD, 12));
		btnSua.setBounds(15, 72, 120, 30);
		pnButCV.add(btnSua);

		btnXoaCV = new JButton("Xóa");
		btnXoaCV.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoaCV.setBounds(145, 72, 120, 30);
		pnButCV.add(btnXoaCV);

		txtSLnsMax = new JSpinner();
		txtSLnsMax.setBounds(133, 117, 137, 20);
		pnThemCV.add(txtSLnsMax);

		txtSLnsPresent = new JTextField();
		txtSLnsPresent.setEditable(false);
		txtSLnsPresent.setBounds(433, 22, 40, 20);
		pnThemCV.add(txtSLnsPresent);

		JPanel pnTblCV = new JPanel();
		pnTblCV.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách công việc"));
		pnTblCV.setBounds(5, 239, 780, 215);
		pnCV.add(pnTblCV);
		pnTblCV.setLayout(null);

		JScrollPane scrCV = new JScrollPane();
		scrCV.setBounds(10, 18, 760, 186);
		pnTblCV.add(scrCV);

		tblCV = new JTable();
		tblCV.setModel(dtm = new DefaultTableModel(new String[] { "M\u00E3 C\u00F4ng Vi\u1EC7c",
				"T\u00EAn C\u00F4ng Vi\u1EC7c", "Chuy\u00EAn M\u00F4n", "S\u1ED1 Nh\u00E2n S\u1EF1 T\u1ED1i \u0110a",
				"S\u1ED1 Nh\u00E2n S\u1EF1 Hi\u1EC7n T\u1EA1i", "M\u00F4 T\u1EA3" }, 0) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		});
		tblCV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		((DefaultTableCellRenderer) tblCV.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tblCV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowSelected = tblCV.getSelectedRow();
				reviewCongViec(rowSelected);
				if (btnThem.getText().equals("Thêm")) {
					btnSua.setEnabled(true);
					if (btnSua.getText().equals("Sửa"))
						btnXoaCV.setEnabled(true);
				}
				if (cboCT_CV.getSelectedItem().toString().equalsIgnoreCase("Tất cả")
						&& btnSua.getText().equals("Hủy")) {
					if (congViec_DAO.getSLNL(txtMaCV.getText()) > 0)
						cboChuyenMonCV.setEnabled(false);
					else
						cboChuyenMonCV.setEnabled(true);
				}
			}
		});

		scrCV.setViewportView(tblCV);
		tblCV.getTableHeader().setBackground(Color.CYAN);
		JLabel lblCT_CV = new JLabel("Mã Công Trình:");
		lblCT_CV.setFont(new Font("Arial", Font.BOLD, 13));
		lblCT_CV.setBounds(10, 14, 102, 20);
		pnCV.add(lblCT_CV);

		cboCT_CV = new JComboBox<String>();
		cboCT_CV.addItem("Tất cả");
		cboCT_CV.setBounds(110, 14, 100, 22);
		pnCV.add(cboCT_CV);

		JLabel lblTimCV = new JLabel("Tìm kiếm công việc:");
		lblTimCV.setBounds(15, 211, 135, 20);
		pnCV.add(lblTimCV);
		lblTimCV.setFont(new Font("Arial", Font.BOLD, 13));

		txtTimCV = new JTextField();
		txtTimCV.setBounds(150, 211, 75, 20);
		pnCV.add(txtTimCV);
		txtTimCV.setColumns(10);

		btnTimCV = new JButton("Tìm");
		btnTimCV.setBounds(225, 211, 70, 20);
		pnCV.add(btnTimCV);

		txtMaCV.setText("CV012");
		txtTenCV.setText("Không biết tên");
		txtSLnsPresent.setText("0");
		txtSLnsMax.setValue(10);
		txtMoTa.setText("Khum có mô tả bé ơi!");

		moKhoaThaoTac(false);
		btnSua.setEnabled(false);
		btnXoaCV.setEnabled(false);

		// loadData
		load();

		// phím tắt
		btnLuu.setMnemonic(KeyEvent.VK_L);
		btnThem.setMnemonic(KeyEvent.VK_T);
		btnSua.setMnemonic(KeyEvent.VK_S);
		btnXoaCV.setMnemonic(KeyEvent.VK_X);
		btnTimCV.setMnemonic(KeyEvent.VK_ENTER);

		// set ToolTipText
		cboCT_CV.setToolTipText("Chọn mã công trình để hiển thị danh sách công việc");
		txtMaCV.setToolTipText("Mã công việc bắt đàu bằng CV theo sau là 3 số");
		cboChuyenMonCV.setToolTipText("Chọn chuyên môn cho công việc");
		txtSLnsMax.setToolTipText("Số lượng nhân sự tối đa cho công việc của 1 công trình nhất định");
		txtSLnsPresent.setToolTipText("Số lượng nhân sự hiện tại làm công việc trong công trình");
		btnThem.setToolTipText("Alt+T Thêm công việc và công trình hoặc thêm mới công việc vào danh sách công việc trên database");
		btnSua.setToolTipText("Alt+S Sửa công việc trong công trình");
		btnLuu.setToolTipText("Alt+L Lưu dữ liệu sau khi thực hiện thêm hoặc sửa thông tin công việc");
		btnXoaCV.setToolTipText("Alt+X Xóa công việc trong công trình nhất định hoặc xóa công việc trên database");
		txtTimCV.setToolTipText(txtMaCV.getToolTipText());
		btnTimCV.setToolTipText("ALt+Enter Tìm kiếm công việc trong công trình theo mã");
		
		// set focus button
		btnThem.setFocusable(false);
		btnLuu.setFocusable(false);
		btnSua.setFocusable(false);
		btnXoaCV.setFocusable(false);
		btnTimCV.setFocusable(false);

		// event
		cboCT_CV.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTimCV.addActionListener(this);
		btnXoaCV.addActionListener(this);
		cboChuyenMonCV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cboCT_CV.getSelectedItem().toString().equalsIgnoreCase("Tất cả")
						&& btnSua.getText().equals("Hủy")) {
					// chế độ sửa đang thực hiện cho tất cả công việc trong hệ thống
					int slns = congViec_DAO.getSLNL(txtMaCV.getText());
					if (slns > 0) {
						String[] headerOfJOption = { "Mã công trình", "Mã nhân sự" };
						DefaultTableModel dtmOfJOption = new DefaultTableModel(headerOfJOption, 0);
						JTable tblOfJOption = new JTable(dtmOfJOption);
						JScrollPane scrOfJOption = new JScrollPane(tblOfJOption);
						scrOfJOption.setPreferredSize(new Dimension(470, 170));
						JPanel pnOfJOption = new JPanel();
						pnOfJOption.setPreferredSize(new Dimension(480, 200));
						JLabel lblOfJOption = new JLabel("Công việc\t này đang được " + slns
								+ " nhân sự đảm nhiệm nên không thể thay đổi chuyên môn!");
						pnOfJOption.add(lblOfJOption);
						pnOfJOption.add(scrOfJOption);
						for (PhanCong pc : congViec_DAO.getDSPC(txtMaCV.getText())) {
							String[] row = { pc.getMaCT(), pc.getMaNV() };
							dtmOfJOption.addRow(row);
						}
						JOptionPane.showConfirmDialog(null, pnOfJOption, "Thông báo", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		return pnCV;
	}

	/**
	 * Load dữ liệu từ database lên table
	 */
	public void load() {
		List<CongTrinh> dsCT = congTrinh_DAO.getDSCT();
		for (CongTrinh congTrinh : dsCT) {
			cboCT_CV.addItem(congTrinh.getMaCT());
		}
		cboCT_CV.setSelectedItem(maCT);
		for (CongViec cv : congViec_DAO.getDSCV(maCT)) {
			addRow(cv);
		}
	}

	/**
	 * Thêm 1 dòng dữ liệu vào table
	 * 
	 * @param cv công việc cần thêm
	 */
	public void addRow(CongViec cv) {
		String[] row = { cv.getMaCV(), cv.getTenCV(), cv.getChuyenMonCV(), cv.getSoNSToiDa() + "", cv.getSoNSHT() + "",
				cv.getMoTaCV() };
		dtm.addRow(row);
	}

	/**
	 * Đưa dữ liệu từ table lên các textFiel và chọn(bôi đen) dòng trên table
	 * 
	 * @param rowSelected dòng cần chọn
	 */
	public void reviewCongViec(int rowSelected) {
		txtMaCV.setText(tblCV.getValueAt(rowSelected, 0) + "");
		txtTenCV.setText(tblCV.getValueAt(rowSelected, 1) + "");
		cboChuyenMonCV.setSelectedItem(tblCV.getValueAt(rowSelected, 2) + "");
		txtSLnsMax.setValue(Integer.parseInt(tblCV.getValueAt(rowSelected, 3) + ""));
		txtSLnsPresent.setText(tblCV.getValueAt(rowSelected, 4) + "");
		txtMoTa.setText(tblCV.getValueAt(rowSelected, 5) + "");
		tblCV.setRowSelectionInterval(rowSelected, rowSelected);
	}

	/**
	 * Mở khóa button và các textField thực hiện thao tác thêm hoặc sửa
	 * 
	 * @param b true=mở, false=tắt
	 */
	public void moKhoaThaoTac(boolean b) {
		txtMaCV.setEditable(b);
		txtTenCV.setEditable(b);
		txtSLnsMax.setEnabled(b);
		txtMoTa.setEditable(b);
		txtTimCV.setEditable(!b);
		btnLuu.setEnabled(b);
		btnThem.setEnabled(!b);
		btnSua.setEnabled(!b);
		btnXoaCV.setEnabled(!b);
		btnTimCV.setEnabled(!b);
		cboCT_CV.setEnabled(!b);
	}

	/**
	 * Kiểm tra ràng buộc dữ liệu vào
	 * 
	 * @return true=đúng, false=sai
	 */
	public boolean regexCongViec() {
		if (txtMaCV.getText().trim().isEmpty()) {
			showMess("Mã công việc không được để trống");
			txtMaCV.requestFocus();
			return false;
		}
		if (txtTenCV.getText().trim().isEmpty()) {
			showMess("Tên công việc không được để trống");
			txtTenCV.requestFocus();
			return false;
		}
		if (!txtMaCV.getText().trim().toUpperCase().matches("^CV[0-9]{3}$")) {// mã công việc bắt đầu bằng CV theo sau
																				// là 3 số
			showMess("Mã công việc không hợp lệ");
			txtMaCV.requestFocus();
			return false;
		}
		if (Integer.parseInt((txtSLnsMax.getValue() + "")) < Integer.parseInt((txtSLnsPresent.getText()))) {
			showMess("Số lượng nhân sự tối đa phải lớn hơn hoặc bằng số nhân sự hiện tại!");
			return false;
		}
		return true;
	}

	/**
	 * Hiển thị thông báo
	 * 
	 * @param tb thông báo cần hiển thị
	 */
	public void showMess(String tb) {
		JOptionPane.showMessageDialog(null, tb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cboCT_CV)) {
			dtm.setRowCount(0);
			for (CongViec cv : congViec_DAO.getDSCV(cboCT_CV.getSelectedItem().toString())) {// lấy danh sách công việc
																								// từ database thuôc
																								// công trình vào table
				addRow(cv);
			}
			if (dtm.getRowCount() > 0) {
				reviewCongViec(0);
				btnSua.setEnabled(true);
				btnXoaCV.setEnabled(true);
			}
		} else if (e.getSource().equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				if (cboCT_CV.getSelectedItem().equals("Tất cả")) {// nếu mã công trình đang chọn là tất cả thì thực hiện
																	// thêm mới trực tiếp trên field vào database
					moKhoaThaoTac(true);
					txtSLnsMax.setEnabled(false);
					btnThem.setEnabled(true);
					btnThem.setText("Hủy");
				} else {// ngược lại nếu đang chọn 1 công trình cố định thì thêm công việc từ database
						// sẵn có vào công trình
					String[] headerAddCV = { "Mã công việc", "Tên công việc", "Chuyên môn" };
					DefaultTableModel dtmAddCV = new DefaultTableModel(headerAddCV, 0);
					JTable tblAdd = new JTable(dtmAddCV) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					tblAdd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					for (CongViec cv : congViec_DAO.getDSCVChuaCo(cboCT_CV.getSelectedItem().toString())) {
						String[] rowAdd = { cv.getMaCV(), cv.getTenCV(), cv.getChuyenMonCV() };
						dtmAddCV.addRow(rowAdd);
					}
					if (dtmAddCV.getRowCount() > 0)
						tblAdd.setRowSelectionInterval(0, 0);
					JScrollPane scrAdd = new JScrollPane(tblAdd);
					scrAdd.setPreferredSize(new Dimension(300, 150));
					int reg = JOptionPane.showConfirmDialog(null, scrAdd, "Chọn công việc",
							JOptionPane.OK_CANCEL_OPTION);
					if (reg == JOptionPane.OK_OPTION) {
						rowSelected = tblAdd.getSelectedRow();
						if (rowSelected >= 0) {
							String maCT = cboCT_CV.getSelectedItem().toString();
							String maCV = dtmAddCV.getValueAt(rowSelected, 0).toString();
							CongViec cv = congViec_DAO.getCongViecTheoMa(maCV);
							if (congViec_DAO.addCongViec(maCT, cv)) {
								showMess("Thêm công việc " + maCV + " vào công trình " + maCT + " thành công!");
								addRow(cv);
								reviewCongViec(dtm.getRowCount() - 1);
							} else
								showMess("Thêm công việc vào công trình không thành công!");
						}
					}
				}
			} else {
				moKhoaThaoTac(false);
				btnThem.setText("Thêm");
				if (tblCV.getSelectedRow() < 0) {
					btnSua.setEnabled(false);
					btnXoaCV.setEnabled(false);
				}
			}
		} else if (e.getSource().equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				moKhoaThaoTac(true);
				btnSua.setEnabled(true);
				txtMaCV.setEditable(false);
				btnSua.setText("Hủy");
				if ((cboCT_CV.getSelectedItem() + "").equalsIgnoreCase("Tất cả")) {
					txtTenCV.setEditable(true);// nếu đang chế dộ tất cả công trinhg thì cho phép sửa tên nhưng không
												// cho phép sửa số nhân sự tối đa
					txtSLnsMax.setEnabled(false);
					int slns = congViec_DAO.getSLNL(txtMaCV.getText());
					if (slns > 0) {
						cboChuyenMonCV.setEnabled(false);// nếu số lượng nhân sự hiện tại lớn hơn 0 thì không cho sửa
															// chuyên môn
					} else
						cboChuyenMonCV.setEnabled(true);
				} else {
					txtTenCV.setEditable(false);// nếu đang chế độ chọn 1 công trình cố định thì không cho phép sửa tên
												// công trình và chuyên môn nhưng cho phép sửa số nhân sự tối đa
					txtSLnsMax.setEnabled(true);
					cboChuyenMonCV.setEnabled(false);
				}
			} else {
				moKhoaThaoTac(false);
				btnSua.setText("Sửa");
				cboChuyenMonCV.setEnabled(true);
			}
		} else if (e.getSource().equals(btnLuu)) {
			if (regexCongViec()) {
				if (btnThem.getText().equals("Hủy")) {// Chức năng thêm đang được thực hiện
					String[] r = { txtMaCV.getText().trim(), txtTenCV.getText().trim(), txtMoTa.getText().trim(),
							congViec_DAO.getMaCM(cboChuyenMonCV.getSelectedItem() + ""), txtSLnsMax.getValue() + "" };
					CongViec cv = new CongViec(r[0], r[1], r[3], Integer.parseInt(r[4]), 0, r[2]);
					if (congViec_DAO.getDSCV("Tất cả").contains(cv)) {
						JOptionPane.showMessageDialog(null, "Mã công việc đã tồn tại");
					} else {
						if (congViec_DAO.addCongViec("Tất cả", cv)) {
							dtm.addRow(r);
							tblCV.setRowSelectionInterval(tblCV.getRowCount() - 1, tblCV.getRowCount() - 1);
							GUI_UNGDUNG.loadDuLieuThongKe();
							JOptionPane.showMessageDialog(null, "Thêm công việc thành công!");
						} else
							JOptionPane.showMessageDialog(null, "Không thể thêm công việc!");
					}
				} else {// Chức năng sửa đang được thực hiện
					rowSelected = tblCV.getSelectedRow();
					String[] r = { txtMaCV.getText().trim(), txtTenCV.getText().trim(), txtMoTa.getText().trim(),
							congViec_DAO.getMaCM(cboChuyenMonCV.getSelectedItem() + ""), txtSLnsMax.getValue() + "" };
					CongViec cv = new CongViec(r[0], r[1], r[3], Integer.parseInt(r[4]), 0, r[2]);
					if (congViec_DAO.setCongViec(cboCT_CV.getSelectedItem().toString(),cv)) {
						dtm.setValueAt(r[1], rowSelected, 1);
						dtm.setValueAt(cboChuyenMonCV.getSelectedItem() + "", rowSelected, 2);
						dtm.setValueAt(r[4], rowSelected, 3);
						dtm.setValueAt(r[2], rowSelected, 5);
						JOptionPane.showMessageDialog(this, "Sửa công việc thành công!");
					} else
						JOptionPane.showMessageDialog(this, "Không thể sửa công việc!");
				}
			}
		} else if (e.getSource().equals(btnXoaCV)) {
			if (cboCT_CV.getSelectedItem().toString().equals("Tất cả")) {
				if (congViec_DAO.deleteCongViec(txtMaCV.getText())) {
					JOptionPane.showMessageDialog(null, "Xóa công việc " + txtMaCV.getText() + " thành công!");
					dtm.removeRow(tblCV.getSelectedRow());
				} else {
					List<String> lstCT = congViec_DAO.getDSCT(txtMaCV.getText());
					String chuoi = "";
					for (String string : lstCT) {
						chuoi += string + " ";
					}
					JOptionPane.showMessageDialog(null, "Không thể xóa vì công việc đang thuộc công trình " + chuoi);
				}
			} else {
				if (!txtSLnsPresent.getText().trim().equals("0"))
					JOptionPane.showMessageDialog(null, "Công việc đang được thực hiện nên không thể xóa!");
				else {
					int reg = JOptionPane.showConfirmDialog(null,
							"Xác nhận xóa công việc " + txtMaCV.getText() + " khỏi công trình "
									+ cboCT_CV.getSelectedItem().toString(),
							"Xóa công việc", JOptionPane.YES_NO_OPTION);
					if (reg == JOptionPane.YES_OPTION) {
						if (congViec_DAO.deleteCongViecInCongTrinh(cboCT_CV.getSelectedItem().toString(),
								txtMaCV.getText())) {
							dtm.removeRow(tblCV.getSelectedRow());
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
							GUI_UNGDUNG.loadDuLieuThongKe();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa không thành công!");
						}
					}
				}
			}
		} else if (e.getSource().equals(btnTimCV)) {
			dtm.setRowCount(0);
			if (!txtTimCV.getText().isEmpty()) {
				for (CongViec cv : congViec_DAO.getDSCV(cboCT_CV.getSelectedItem() + "")) {
					if (cv.getMaCV().equalsIgnoreCase(txtTimCV.getText())) {
						addRow(cv);
						reviewCongViec(0);
						break;
					}
				}
			} else {
				for (CongViec cv : congViec_DAO.getDSCV(cboCT_CV.getSelectedItem() + "")) {
					addRow(cv);
				}
			}
		}
	}
}
