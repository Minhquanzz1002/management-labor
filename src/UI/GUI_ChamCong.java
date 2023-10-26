package UI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import com.toedter.calendar.JYearChooser;

import Export.XuatFileExcel;
import dao.ChamCong_dao;
import dao.PhongBan_dao;
import entity.ChamCong;
import entity.PhongBan;
import javax.swing.border.LineBorder;


public class GUI_ChamCong extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblCC;
	private DefaultTableModel dtmChamCong;
	private JButton btnTinhCC;
	private PhongBan_dao phongBan_dao = new PhongBan_dao();
	private ChamCong_dao chamCong_dao = new ChamCong_dao();
	private JComboBox<String> cboThang;
	private JYearChooser txtYear;
	private JComboBox<String> cboPhongBan;
	private JButton btnXuat;

	public GUI_ChamCong() {
		setSize(1000, 1000);
		getContentPane().add(addConTrol());
	}

	public Component addConTrol() {
		JPanel pnCC = new JPanel();
		pnCC.setLayout(null);

		JPanel pnForm = new JPanel();
		pnForm.setBackground(new Color(255, 204, 102));
		pnForm.setBounds(0, 35, 968, 60);
		pnForm.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chấm Công"));
		pnCC.add(pnForm);
		pnForm.setLayout(null);

		JLabel lblDate = new JLabel("Month/Year:");
		lblDate.setBounds(83, 20, 83, 25);
		pnForm.add(lblDate);
		lblDate.setFont(new Font("Arial", Font.BOLD, 13));

		JLabel lblPB = new JLabel("Phòng Ban:");
		lblPB.setBounds(348, 20, 83, 25);
		pnForm.add(lblPB);
		lblPB.setFont(new Font("Arial", Font.BOLD, 13));

		btnTinhCC = new JButton("Tính");
		btnTinhCC.setFont(new Font("Arial", Font.BOLD, 13));
		btnTinhCC.setBounds(700, 20, 85, 25);
		pnForm.add(btnTinhCC);
		
		txtYear = new JYearChooser();
		txtYear.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtYear.setStartYear(2022);
		txtYear.setValue(0);
		txtYear.getSpinner().setFont(new Font("Arial", Font.BOLD, 13));
		txtYear.setBounds(245, 20, 68, 25);
		pnForm.add(txtYear);

		cboThang = new JComboBox<String>();
		cboThang.addItem("Tất Cả");
		cboThang.addItem("Tháng 1");
		cboThang.addItem("Tháng 2");
		cboThang.addItem("Tháng 3");
		cboThang.addItem("Tháng 4");
		cboThang.addItem("Tháng 5");
		cboThang.addItem("Tháng 6");
		cboThang.addItem("Tháng 7");
		cboThang.addItem("Tháng 8");
		cboThang.addItem("Tháng 9");
		cboThang.addItem("Tháng 10");
		cboThang.addItem("Tháng 11");
		cboThang.addItem("Tháng 12");
		cboThang.setBounds(164, 20, 80, 25);
		pnForm.add(cboThang);
		
		cboPhongBan = new JComboBox<String>();
		cboPhongBan.setBounds(426, 20, 253, 25);
		cboPhongBan.addItem("Tất Cả");
		
		pnForm.add(cboPhongBan);
		
		btnXuat = new JButton("Xuất Excel");
		btnXuat.setFont(new Font("Arial", Font.BOLD, 13));
		btnXuat.setBounds(789, 20, 109, 25);
		pnForm.add(btnXuat);

		JPanel pnHeader = new JPanel();
		pnHeader.setBackground(Color.CYAN);
		pnHeader.setBounds(0, 0, 979, 35);
		pnCC.add(pnHeader);
		pnHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCC = new JLabel("Chấm Công");
		pnHeader.add(lblCC);
		lblCC.setForeground(Color.BLUE);
		lblCC.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel pnTblNV = new JPanel();
		pnTblNV.setBackground(SystemColor.info);
		pnTblNV.setBounds(0, 95, 968, 455);
		pnTblNV.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách Nhân Viên"));
		pnCC.add(pnTblNV);
		pnTblNV.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 948, 423);
		pnTblNV.add(scrollPane);

		tblCC = new JTable();
		tblCC.setModel(dtmChamCong = new DefaultTableModel(
				new String[] { "MaNV", "TenNV", "MaPB", "L\u01B0\u01A1ng", "\u0110\u00E3 Nh\u1EADn" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblCC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblCC);
		loatCBOPhongBan();
		btnTinhCC.setFocusable(false);
		btnXuat.setFocusable(false);
		btnTinhCC.setMnemonic(KeyEvent.VK_ENTER);
		btnXuat.setMnemonic(KeyEvent.VK_E);
		btnTinhCC.setToolTipText("Phím Tắt là ALT + ENTER");
		btnXuat.setToolTipText("Phím Tắt là ALT + E");
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		for (ChamCong i :chamCong_dao.getDsChamCong()) {
			Object a[] = { i.getMaNV(), i.getTenNV(), i.getMaPB(),  formatter.format(i.getLuongTheoNgay()*i.getSoNgayLam())+" VNĐ",
					i.isTrangThai() };
			dtmChamCong.addRow(a);
		}
		cboPhongBan.setSelectedIndex(3);
		btnXuat.setFocusable(false);
		btnTinhCC.addActionListener(this);
		btnXuat.addActionListener(this);
		return pnCC;
	}

	public void loatCBOPhongBan() {
		for (PhongBan i : phongBan_dao.getDsPB()) {
			cboPhongBan.addItem(i.getTenPB());
		}
	}

	public void loadDsNhanSu() {
		int nam = txtYear.getValue();
		int thang = cboThang.getSelectedIndex();
		String maPb = null;
		for (PhongBan i : phongBan_dao.getDsPB()) {
			if (i.getTenPB().equals(cboPhongBan.getSelectedItem().toString())) {
				maPb = i.getMaPB();
			}
		}DecimalFormat formatter = new DecimalFormat("###,###,###");
		for (ChamCong i : chamCong_dao.getDSChamCong(maPb, thang, nam)) {
			
			Object a[] = { i.getMaNV(), i.getTenNV(), i.getMaPB(),  formatter.format(i.getLuongTheoNgay()*i.getSoNgayLam())+" VNĐ",
					i.isTrangThai() };
			dtmChamCong.addRow(a);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTinhCC)) {
			if (cboPhongBan.getSelectedIndex()==0) {
				dtmChamCong.setRowCount(0);
				DecimalFormat formatter = new DecimalFormat("###,###,###");
				for (ChamCong i :chamCong_dao.getDsChamCong()) {
					Object a[] = { i.getMaNV(), i.getTenNV(), i.getMaPB(),  formatter.format(i.getLuongTheoNgay()*i.getSoNgayLam())+" VNĐ",
							i.isTrangThai() };
					dtmChamCong.addRow(a);
				}
			}
			else {
				dtmChamCong.setRowCount(0);
				loadDsNhanSu();
			}
		}
		else if (e.getSource().equals(btnXuat)) {
			if (tblCC.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Không Có Dữ Liệu Để Xuất");
			}
			else {
				JFileChooser file = new JFileChooser();
				file.showSaveDialog(this);
				if (file.getSelectedFile()==null) {
					JOptionPane.showMessageDialog(null, "Xuất Dữ Liệu Ra File Excle Thất Bại");
				}
				else {
				XuatFileExcel xf = new XuatFileExcel();
				xf.pt_XuatFileExcel(tblCC, file.getSelectedFile(),"Danh Sachs");
				}
			}

			
			
		}

	}
}
