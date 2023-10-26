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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.NhanSu_dao;
import dao.PhongBan_dao;
import entity.NhanSu;
import entity.PhongBan;

public class GUI_PhongBan extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Phòng Ban
	private JTextField txtMPB;
	private JTextField txtTenPB;
	private JTable tblPB;
	private JTable tblNhanVienPB;
	private JTextField txtTimPB;
	private DefaultTableModel dtmNhanVien;
	private PhongBan_dao phongban_Dao = new PhongBan_dao();
	private NhanSu_dao nhansu_dao = new NhanSu_dao();
	private DefaultTableModel dtmPhongBan;
	private JTextField txtmaTPB;
	private JSpinner spnSoLuong;
	private JButton btnTimPB;
	private JButton btnSuaPB;
	private JButton btnXoaPB;
	private JButton btnXoaTrangPB;
	private JButton btnThemPB;
	private JPanel pnPB;
	private JButton btnLuuPB;

	// Phòng Ban
	/**
	 * Giao Diện Phòng Ban
	 */
	public GUI_PhongBan() {
		
	}

	// tab Phòng Ban
	/**
	 * Hiển Thị Danh Sách Phòng Ban
	 */
	public Component tabPhongBan() {
		pnPB = new JPanel();
		pnPB.setLayout(null);

		JPanel pnNorth = new JPanel();
		pnNorth.setForeground(Color.BLACK);
		pnNorth.setBackground(Color.CYAN);
		pnNorth.setBounds(0, 0, 979, 35);
		pnPB.add(pnNorth);

		JLabel lblThemPhongBan = new JLabel("THÊM PHÒNG BAN");
		lblThemPhongBan.setForeground(Color.BLUE);
		pnNorth.add(lblThemPhongBan);
		lblThemPhongBan.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel pnCenter = new JPanel();
		pnCenter.setBounds(0, 35, 979, 508);
		pnPB.add(pnCenter);
		pnCenter.setLayout(null);

		JPanel pnNhapPB = new JPanel();
		pnNhapPB.setBackground(new Color(238, 232, 170));
		pnNhapPB.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thêm Phòng Ban"));
		pnNhapPB.setBounds(0, 0, 977, 90);
		pnCenter.add(pnNhapPB);
		pnNhapPB.setLayout(null);

		JLabel lblMPB = new JLabel("Mã phòng ban : ");
		lblMPB.setBounds(10, 22, 110, 20);
		pnNhapPB.add(lblMPB);
		lblMPB.setFont(new Font("Arial", Font.BOLD, 13));

		txtMPB = new JTextField();
		txtMPB.setBounds(120, 22, 87, 20);
		pnNhapPB.add(txtMPB);
		txtMPB.setColumns(10);

		JLabel lblTenPB = new JLabel("Tên phòng ban : ");
		lblTenPB.setBounds(234, 22, 110, 20);
		pnNhapPB.add(lblTenPB);
		lblTenPB.setFont(new Font("Arial", Font.BOLD, 13));

		txtTenPB = new JTextField();
		txtTenPB.setBounds(344, 22, 130, 20);
		pnNhapPB.add(txtTenPB);
		txtTenPB.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số lượng nhân sự : ");
		lblSoLuong.setBounds(502, 22, 130, 20);
		pnNhapPB.add(lblSoLuong);
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 13));

		spnSoLuong = new JSpinner();
		spnSoLuong.setBounds(640, 22, 41, 20);
		pnNhapPB.add(spnSoLuong);

		btnThemPB = new JButton("Thêm");
		btnThemPB.setBounds(211, 52, 100, 30);
		pnNhapPB.add(btnThemPB);
		btnThemPB.setFont(new Font("Arial", Font.BOLD, 13));

		btnXoaTrangPB = new JButton("Xóa Trắng");
		btnXoaTrangPB.setBounds(333, 52, 100, 30);
		pnNhapPB.add(btnXoaTrangPB);
		btnXoaTrangPB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaTrangPB.setFont(new Font("Arial", Font.BOLD, 13));

		btnXoaPB = new JButton("Xóa");
		btnXoaPB.setBounds(456, 52, 100, 30);
		pnNhapPB.add(btnXoaPB);
		btnXoaPB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaPB.setFont(new Font("Arial", Font.BOLD, 13));

		btnSuaPB = new JButton("Sửa");
		btnSuaPB.setBounds(581, 52, 100, 30);
		pnNhapPB.add(btnSuaPB);
		btnSuaPB.setFont(new Font("Arial", Font.BOLD, 13));

		JLabel lblMTPB = new JLabel("Mã trưởng phòng ban : ");
		lblMTPB.setFont(new Font("Arial", Font.BOLD, 13));
		lblMTPB.setBounds(714, 22, 153, 20);
		pnNhapPB.add(lblMTPB);

		txtmaTPB = new JTextField();
		txtmaTPB.setColumns(10);
		txtmaTPB.setBounds(877, 22, 87, 20);
		pnNhapPB.add(txtmaTPB);

		btnLuuPB = new JButton("Lưu");
		btnLuuPB.setFont(new Font("Arial", Font.BOLD, 13));
		btnLuuPB.setBounds(713, 52, 85, 30);
		pnNhapPB.add(btnLuuPB);

		moKhoaTextfields(false);
		moKhoaControls(true);
		btnLuuPB.setEnabled(false);

		JPanel pnTblPhongBan = new JPanel();
		pnTblPhongBan.setBackground(SystemColor.info);
		pnTblPhongBan.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách Phòng Ban"));
		pnTblPhongBan.setBounds(0, 136, 977, 152);
		pnCenter.add(pnTblPhongBan);
		pnTblPhongBan.setLayout(null);

		JScrollPane scrPhongBan = new JScrollPane();
		scrPhongBan.setBounds(10, 22, 957, 120);
		pnTblPhongBan.add(scrPhongBan);

		tblPB = new JTable();
		((DefaultTableCellRenderer) tblPB.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		;
		tblPB.setModel(
				dtmPhongBan = new DefaultTableModel(new String[] { "M\u00E3 Ph\u00F2ng Ban", "T\u00EAn Ph\u00F2ng Ban",
						"S\u1ED1 L\u01B0\u1EE3ng Nh\u00E2n S\u1EF1", "M\u00E3 Tr\u01B0\u1EDFng Ph\u00F2ng Ban" }, 0) {
					/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {
						return false;
					};
				});
		tblPB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPB.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblPB.getColumnModel().getColumn(1).setPreferredWidth(89);
		tblPB.getColumnModel().getColumn(2).setPreferredWidth(106);
		tblPB.getColumnModel().getColumn(3).setPreferredWidth(117);
		scrPhongBan.setViewportView(tblPB);
		tblPB.getTableHeader().setBackground(Color.CYAN);
		JPanel pnTblNhanVienPB = new JPanel();
		pnTblNhanVienPB.setBackground(SystemColor.info);
		pnTblNhanVienPB.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh Sách Nhân Viên"));
		pnTblNhanVienPB.setBounds(0, 298, 977, 199);
		pnCenter.add(pnTblNhanVienPB);
		pnTblNhanVienPB.setLayout(null);

		JScrollPane scrNhanVienPB = new JScrollPane();
		scrNhanVienPB.setBounds(10, 25, 957, 163);
		pnTblNhanVienPB.add(scrNhanVienPB);

		tblNhanVienPB = new JTable();
		scrNhanVienPB.setViewportView(tblNhanVienPB);
		tblNhanVienPB.setModel(dtmNhanVien = new DefaultTableModel(new String[] { "M\u00E3 Nh\u00E2n Vi\u00EAn",
				"H\u1ECD V\u00E0 \u0110\u1EC7m", "T\u00EAn", "Gi\u1EDBi T\u00EDnh", "Chuy\u00EAn M\u00F4n",
				"Ng\u00E0y Sinh", "Tu\u1ED5i", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		});

		JLabel lblTimKiemPB = new JLabel("Tìm Kiếm : ");
		lblTimKiemPB.setFont(new Font("Arial", Font.BOLD, 13));
		lblTimKiemPB.setBounds(10, 100, 73, 25);
		pnCenter.add(lblTimKiemPB);

		txtTimPB = new JTextField();
		txtTimPB.setBounds(83, 100, 150, 25);
		pnCenter.add(txtTimPB);
		txtTimPB.setColumns(10);

		btnTimPB = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimPB.setBounds(233, 100, 59, 25);
		pnCenter.add(btnTimPB);
		// Add data
		load();
		spnSoLuong.setEnabled(false);
		tblPB.addMouseListener(this);
		btnThemPB.addActionListener(this);
		btnXoaTrangPB.addActionListener(this);
		btnXoaPB.addActionListener(this);
		btnSuaPB.addActionListener(this);
		btnLuuPB.addActionListener(this);
		btnTimPB.addActionListener(this);
		
		
		txtMPB.setText("PB11");
		txtTenPB.setText("Phòng thi công 2");
		
//		Phím tắt
		btnThemPB.setMnemonic(KeyEvent.VK_A);
		btnXoaTrangPB.setMnemonic(KeyEvent.VK_C);
		btnXoaPB.setMnemonic(KeyEvent.VK_R);
		btnSuaPB.setMnemonic(KeyEvent.VK_F);
		btnLuuPB.setMnemonic(KeyEvent.VK_S);
		btnTimPB.setMnemonic(KeyEvent.VK_T);
		
//		set tooltiptext
		btnThemPB.setToolTipText("Alt + A, Thêm phòng ban vào danh sách");
		btnXoaTrangPB.setToolTipText("Alt + C, Thêm phòng ban vào danh sách");
		btnXoaPB.setToolTipText("Alt + R, Thêm phòng ban vào danh sách");
		btnSuaPB.setToolTipText("Alt + F, Thêm phòng ban vào danh sách");
		btnLuuPB.setToolTipText("Alt + S, Thêm phòng ban vào danh sách");
		btnTimPB.setToolTipText("Alt + T, Thêm phòng ban vào danh sách");
		//setfocus
		btnThemPB.setFocusable(false);
		btnXoaTrangPB.setFocusable(false);
		btnXoaPB.setFocusable(false);
		btnSuaPB.setFocusable(false);
		btnLuuPB.setFocusable(false);
		btnTimPB.setFocusable(false);
		return pnPB;
	}
	
	/**
	 * Tải Lại Danh Sách Phòng Ban
	 */
	public void load() {
		List<PhongBan> ds;
		ds = phongban_Dao.getDSPB();
		for (PhongBan phongban : ds) {
			addRow(phongban);
		}
	}
	
	/**
	 * Thêm Phòng Ban Vào DefaulTableModel
	 * @param pb Phòng Ban Cần Thêm
	 */
	public void addRow(PhongBan pb) {
		String[] row = { pb.getMaPB(), pb.getTenPB(), pb.getSoLuong() + "", pb.getMaTP() };
		dtmPhongBan.addRow(row);
	}

//		public void loadNV(String maPB) {
//			List<NhanSu> ds;
//			ds = phongban_Dao.getDSNV(maPB);
//			for(NhanSu nhansu : ds) {
//				addRowNV(nhansu);
//			}
//		}
//		public void addRowNV(NhanSu ns) {
//			String[] row = {ns.getMaNhanSu(), ns.getHoDem(), ns.getTenNhanSu(), ns.isGioiTinh()+"",ns.getChuyenMon(), ns.getNgaySinh()+"", ns.getTuoi()+"", ns.getSdt()};
//			dtmNhanVien.addRow(row);
//		}
	/**
	 * Tải Lại Danh Sách Phòng Ban Theo Mã Phòng Ban
	 * @param maPB Hiển Thị Phòng Ban Theo Mã
	 */
	public void loadTheoPb(String maPB) {
		dtmNhanVien.setRowCount(0);
		for (NhanSu ns : phongban_Dao.getDSNV(maPB)) {
			String[] row = { ns.getMaNhanSu(), ns.getHoDem(), ns.getTenNhanSu(), ns.isGioiTinh()?"Nam":"Nữ",
					ns.getChuyenMon(), ns.getNgaySinh() + "", ns.getTuoi() + "", ns.getSdt() };

			dtmNhanVien.addRow(row);
		}

	}

	/**
	 * Mở Khóa Các Buttons
	 * @param b Giá Trị True | False
	 */
	private void moKhoaControls(boolean b) {
		btnThemPB.setEnabled(b);
		btnLuuPB.setEnabled(b);
		btnSuaPB.setEnabled(b);
		btnXoaPB.setEnabled(b);
	}

	/**
	 * Mở Khóa Các TextFields
	 * @param b Giá Trị True | False
	 */
	private void moKhoaTextfields(boolean b) {
		txtMPB.setEditable(b);
		txtTenPB.setEditable(b);
		txtmaTPB.setEditable(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemPB)) {
			if (btnThemPB.getText().equalsIgnoreCase("Thêm")) {
				moKhoaTextfields(true);
				moKhoaControls(false);
				spnSoLuong.setValue(0);
				spnSoLuong.setEnabled(false);
				btnLuuPB.setEnabled(true);
				btnSuaPB.setEnabled(false);
				btnThemPB.setEnabled(true);
				btnThemPB.setText("Hủy");
			} else if (btnThemPB.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuuPB.setEnabled(false);
				btnThemPB.setText("Thêm");
				napPhongBanVaoTextfields();
			}
		} else if (o.equals(btnLuuPB)) {
			if (btnThemPB.getText().equals("Hủy")) {
				if (regexPhongBan()) {
					String maPB = txtMPB.getText();
					String tenPB = txtTenPB.getText();
					int soLuong = (int) spnSoLuong.getValue();
					String maTP = txtmaTPB.getText();
					try {
						PhongBan pb = new PhongBan(maPB, tenPB, soLuong, maTP);
						List<PhongBan> lstpb = phongban_Dao.getDSPB();
						if (lstpb.contains(pb))
							JOptionPane.showMessageDialog(this, "Phòng ban này đã tồn tại");
						else {
							phongban_Dao.themPhongBan(pb);
							if (pb.getMaTP().isEmpty()) {
								pb.setSoLuong(0);
							}
							else {
								pb.setSoLuong(1);
							}
							dtmPhongBan.addRow(
									new Object[] { pb.getMaPB(), pb.getTenPB(), pb.getSoLuong(), pb.getMaTP() });
							
							
							for (NhanSu tp : nhansu_dao.getListNhanSu()) {
								if(tp.getMaNhanSu().equals(txtmaTPB.getText())) {
									tp.setMaPB(txtMPB.getText());
									nhansu_dao.updateNhanSu(tp.getMaNhanSu(), tp.getMaPB());
								}
							}
							GUI_UNGDUNG.loadDuLieuThongKe();
							JOptionPane.showMessageDialog(this, "Lưu Thành Công");
							
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
					
				}
			} else if (btnThemPB.getText().equals("Thêm")) {
				boolean regex = false;
				List<NhanSu> dsNs = nhansu_dao.getListNhanSu();
				for (NhanSu ns : dsNs) {
					if (ns.getMaNhanSu().equals(txtmaTPB.getText()) && ns.getMaPB().equals(txtMPB.getText())) {
						regex = true;
					}
				}
				if (regexPhongBan() && regex) {
					String maPB = txtMPB.getText();
					String tenPB = txtTenPB.getText();
					int soLuong = (int) spnSoLuong.getValue();
					String maTP = txtmaTPB.getText();
					int row = tblPB.getSelectedRow();
					try {
						PhongBan pb = new PhongBan(maPB, tenPB, soLuong, maTP);
						dtmPhongBan.setValueAt(pb.getMaPB(), row, 0);
						dtmPhongBan.setValueAt(pb.getTenPB(), row, 1);
						dtmPhongBan.setValueAt(pb.getSoLuong(), row, 2);
						dtmPhongBan.setValueAt(pb.getMaTP(), row, 3);
						JOptionPane.showMessageDialog(this, "Sửa Thành Công");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(this, "Trưởng phòng không tồn tại trong phòng ban");
				}
			}
		} else if (o.equals(btnXoaPB)) {
			int r = tblPB.getSelectedRow();
			if (r==-1) {
					JOptionPane.showMessageDialog(null, "Xóa Thất Bại");
			}
			else {
				if(phongban_Dao.xoaPhongBan(txtMPB.getText())) {
					JOptionPane.showMessageDialog(null, "Xóa Thành Công");
					GUI_UNGDUNG.loadDuLieuThongKe();
					dtmPhongBan.removeRow(r);
				}
				else
					JOptionPane.showMessageDialog(null, "Xóa Thất Bại");			}
		} else if (o.equals(btnXoaTrangPB)) {
			txtMPB.setText("");
			txtTenPB.setText("");
			spnSoLuong.setValue(0);
			txtmaTPB.setText("");
		} else if (o.equals(btnSuaPB)) {
			if (btnSuaPB.getText().equalsIgnoreCase("Sửa")) {
				moKhoaTextfields(true);
				txtMPB.setEditable(false);
				moKhoaControls(false);
				btnLuuPB.setEnabled(true);
				btnSuaPB.setEnabled(true);
				btnSuaPB.setText("Hủy");
			} else if (btnSuaPB.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuuPB.setEnabled(false);
				btnSuaPB.setText("Sửa");
				napPhongBanVaoTextfields();
			}
		}

		else if (o.equals(btnTimPB)) {
			timPhongBanTheoMa();
		}
	}

	/**
	 * Nạp Thông Tin Phòng Ban Vào TextFileds
	 */
	private void napPhongBanVaoTextfields() {
		int row = tblPB.getSelectedRow();
		if (row >= 0) {
			txtMPB.setText((String) tblPB.getValueAt(row, 0));
			txtTenPB.setText((String) tblPB.getValueAt(row, 1));
			spnSoLuong.setValue(Integer.parseInt(tblPB.getValueAt(row, 2).toString()));
			txtmaTPB.setText((String) tblPB.getValueAt(row, 3));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String maPB;
		int rowSelected = tblPB.getSelectedRow();
		if (rowSelected >= 0) {
			maPB = dtmPhongBan.getValueAt(rowSelected, 0).toString();
			loadTheoPb(maPB);
		}
		txtMPB.setText(dtmPhongBan.getValueAt(rowSelected, 0).toString());
		txtTenPB.setText(dtmPhongBan.getValueAt(rowSelected, 1).toString());
		spnSoLuong.setValue(Integer.parseInt(dtmPhongBan.getValueAt(rowSelected, 2).toString()));
		txtmaTPB.setText(dtmPhongBan.getValueAt(rowSelected, 3) + "");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Tìm Kiếm Phòng Ban Theo Mã Phòng Ban
	 */
	private void timPhongBanTheoMa() {
		String str = txtTimPB.getText();
		if (str != null && str.trim().length() > 0) {
			try {
				PhongBan pb = phongban_Dao.getPB(str);
				if (pb != null) {
					XoaHetDuLieuTrenTableModel();
					dtmPhongBan.addRow(new Object[] { pb.getMaPB(), pb.getTenPB(), pb.getSoLuong(), pb.getMaTP() });
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
					return;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Dữ Liệu Không Hợp Lệ");
				txtTimPB.selectAll();
				txtTimPB.requestFocus();
			}
		} else {
			XoaHetDuLieuTrenTableModel();
			DocDuLieuTuArrayListVaoModel();
			tblPB.setModel(dtmPhongBan);
		}
	}

	/**
	 * Đọc Dữ Liệu Từ Danh Sách Phòng Ban Vào DefaulTabeModel
	 */
	private void DocDuLieuTuArrayListVaoModel() {
		List<PhongBan> dspb = phongban_Dao.getDSPB();
		List<PhongBan> listpb = new ArrayList<PhongBan>();
		for (PhongBan pb : dspb) {
			if (listpb.contains(pb)) {
				continue;
			} else {
				listpb.add(pb);
				dtmPhongBan.addRow(new Object[] { pb.getMaPB(), pb.getTenPB(), pb.getSoLuong(), pb.getMaTP() });
			}
		}
	}

	/**
	 * Xóa Hết Dữ Liệu Trên TableModel
	 */
	private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel xoa = (DefaultTableModel) tblPB.getModel();
		xoa.getDataVector().removeAllElements();
	}

	/**
	 * Regex Phòng Ban
	 */
	public boolean regexPhongBan() {
		String thongbao = "";
		String regexMPB = "^PB[0-9]{2}$";
		String regexMTP = "^NV[0-9]{4}$";
		if (!txtMPB.getText().matches(regexMPB)) {
			thongbao += "Mã phòng ban bắt đầu bằng 2 chữ cái PB và theo sau là 2 chữ số";
			txtMPB.selectAll();
		}
		if(!txtmaTPB.getText().isEmpty()) {
			if (txtmaTPB.getText().matches(regexMTP)) {
				if(nhansu_dao.kiemTraTruongPhong(txtmaTPB.getText())) {
					thongbao += "\nTrưởng phòng hiện đang là trưởng phòng của phòng ban khác";
					txtMPB.selectAll();
				}
			}
			else {
				thongbao += "\nMã trưởng phòng ban bắt đầu bằng 2 chữ cái NV và theo sau là 4 chữ số";
				txtMPB.selectAll();
			}
		}
//		else {
//			thongbao += "\nMã trưởng phòng ban không được rỗng";
//			txtMPB.selectAll();
//		}
		if (thongbao.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, thongbao);
			return false;
		}

	}
}
