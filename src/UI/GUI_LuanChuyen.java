package UI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.CongTrinh_dao;
import dao.CongViec_dao;
import dao.NhanSu_dao;
import dao.PhanCong_dao;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanSu;
import entity.PhanCong;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;

public class GUI_LuanChuyen extends JFrame implements ActionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaCTCu;
	private JTextField txtMaNSLuanChuyen;
	private JTextField txtMaCVCu;
	private JTextField txtTimCT_LC;
	private JTable tblCT_LC;
	private JTable tblCV_LC;
	private JTextField txtTimNSLuanChuyen;
	private JTable tblNhanSuLuanChuyen;
	private JButton btnLuanChuyen;
	private JComboBox<String> cboCongViec_LC;
	private JComboBox<String> cboNhanSuLuanChuyen;
	private DefaultTableModel dftNS_LC;
	private DefaultTableModel dftCV_LC;
	private DefaultTableModel dftCT_LC;
	private CongTrinh_dao congTrinhDao;
	private NhanSu_dao nhanSu_dao = new NhanSu_dao();
	private CongViec_dao congViec_dao = new CongViec_dao();
	private PhanCong_dao phanCong_dao = new PhanCong_dao();
	private JButton btnTimCT;
	private JButton btnTimNS;
	
	/**
	 * Giao diện luân chuyển
	 */
	public GUI_LuanChuyen() {
		congTrinhDao = new CongTrinh_dao();
	}
	/**
	 * Giao diện thực hiện việc luân chuyển
	 * @return conponent luân chuyển
	 */
	public Component tabLuanChuyen() {
		JPanel pnLC = new JPanel();
		pnLC.setBounds(0, 0, 979, 554);
		pnLC.setLayout(null);
		
		JPanel pnCenterLuanChuyen = new JPanel();
		pnCenterLuanChuyen.setBounds(0, 35, 979, 519);
		pnLC.add(pnCenterLuanChuyen);
		pnCenterLuanChuyen.setLayout(null);
		
		JPanel pnNhanSuTrongCT_LC = new JPanel();
		pnNhanSuTrongCT_LC.setBackground(new Color(238, 232, 170));
		pnNhanSuTrongCT_LC.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Chọn nhân sự trong công trình"));
		pnNhanSuTrongCT_LC.setBounds(5, 0, 969, 60);
		pnCenterLuanChuyen.add(pnNhanSuTrongCT_LC);
		pnNhanSuTrongCT_LC.setLayout(null);
		
		JLabel lblMaCTCu = new JLabel("Mã công trình cũ:");
		lblMaCTCu.setBounds(10, 22, 115, 20);
		pnNhanSuTrongCT_LC.add(lblMaCTCu);
		lblMaCTCu.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtMaCTCu = new JTextField();
		txtMaCTCu.setBounds(135, 22, 100, 20);
		pnNhanSuTrongCT_LC.add(txtMaCTCu);
		txtMaCTCu.setColumns(10);
		
		JLabel lblMaNSLuanChuyen = new JLabel("Mã nhân sự:");
		lblMaNSLuanChuyen.setBounds(477, 22, 78, 20);
		pnNhanSuTrongCT_LC.add(lblMaNSLuanChuyen);
		lblMaNSLuanChuyen.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtMaNSLuanChuyen = new JTextField();
		txtMaNSLuanChuyen.setBounds(565, 22, 100, 20);
		pnNhanSuTrongCT_LC.add(txtMaNSLuanChuyen);
		txtMaNSLuanChuyen.setText("");
		txtMaNSLuanChuyen.setColumns(10);
		
		JLabel lblMaCVCu = new JLabel("Mã công việc cũ:");
		lblMaCVCu.setBounds(250, 22, 107, 20);
		pnNhanSuTrongCT_LC.add(lblMaCVCu);
		lblMaCVCu.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtMaCVCu = new JTextField();
		txtMaCVCu.setBounds(367, 22, 100, 20);
		pnNhanSuTrongCT_LC.add(txtMaCVCu);
		txtMaCVCu.setColumns(10);
		
		btnLuanChuyen = new JButton("Luân chuyển");
		btnLuanChuyen.setBounds(720, 17, 135, 30);
		pnNhanSuTrongCT_LC.add(btnLuanChuyen);
		btnLuanChuyen.setFont(new Font("Arial", Font.BOLD, 13));
		
		JPanel pnCT_LC = new JPanel();
		pnCT_LC.setBackground(SystemColor.info);
		pnCT_LC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnCT_LC.setBounds(5, 60, 480, 225);
		pnCenterLuanChuyen.add(pnCT_LC);
		pnCT_LC.setLayout(null);
		
		JLabel lblTimKiemCT_LC = new JLabel("Tìm kiếm công trình:");
		lblTimKiemCT_LC.setBounds(10, 10, 131, 25);
		pnCT_LC.add(lblTimKiemCT_LC);
		lblTimKiemCT_LC.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtTimCT_LC = new JTextField();
		txtTimCT_LC.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTimCT_LC.setBounds(142, 10, 140, 25);
		pnCT_LC.add(txtTimCT_LC);
		txtTimCT_LC.setColumns(10);
		
		JPanel pnDSCT_LC = new JPanel();
		pnDSCT_LC.setBackground(SystemColor.info);
		pnDSCT_LC.setBounds(5, 42, 470, 178);
		pnCT_LC.add(pnDSCT_LC);
		pnDSCT_LC.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách công trình"));
		pnDSCT_LC.setLayout(null);
		
		JScrollPane scrCT_LC = new JScrollPane();
		scrCT_LC.setBounds(10, 20, 450, 147);
		pnDSCT_LC.add(scrCT_LC);
		
		tblCT_LC = new JTable();
		((DefaultTableCellRenderer)tblCT_LC.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tblCT_LC.getTableHeader().setBackground(Color.CYAN);
		tblCT_LC.setFont(new Font("Arial", Font.PLAIN, 13));
		tblCT_LC.setModel(dftCT_LC=new DefaultTableModel(
			new String[] {
				"M\u00E3 d\u1EF1 \u00E1n", "T\u00EAn d\u1EF1 \u00E1n", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "S\u1ED1 nh\u00E2n s\u1EF1", "Tr\u1EA1ng th\u00E1i"
			},0) {
			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblCT_LC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCT_LC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reviewCongTrinh(tblCT_LC.getSelectedRow());
				cboCongViec_LC.setSelectedIndex(0);
				cboCongViec_LC.setEnabled(true);
				cboNhanSuLuanChuyen.setSelectedIndex(0);
				cboNhanSuLuanChuyen.setEnabled(true);
			}
		});
		scrCT_LC.setViewportView(tblCT_LC);
		
		btnTimCT = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimCT.setBounds(282, 10, 60, 25);
		pnCT_LC.add(btnTimCT);
		
		JPanel pnCV_LC = new JPanel();
		pnCV_LC.setBackground(SystemColor.info);
		pnCV_LC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnCV_LC.setBounds(494, 60, 480, 225);
		pnCenterLuanChuyen.add(pnCV_LC);
		pnCV_LC.setLayout(null);
		
		JLabel lblTimKiemCV_LC = new JLabel("Hiển thị theo:");
		lblTimKiemCV_LC.setBounds(10, 10, 135, 20);
		pnCV_LC.add(lblTimKiemCV_LC);
		lblTimKiemCV_LC.setFont(new Font("Arial", Font.BOLD, 13));
		
		JPanel pnDSCV_LC = new JPanel();
		pnDSCV_LC.setBackground(SystemColor.info);
		pnDSCV_LC.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Danh sách công việc trong công trình"));
		pnDSCV_LC.setBounds(5, 41, 470, 179);
		pnCV_LC.add(pnDSCV_LC);
		pnDSCV_LC.setLayout(null);
		
		JScrollPane scrCV_LC = new JScrollPane();
		scrCV_LC.setBounds(10, 20, 450, 148);
		pnDSCV_LC.add(scrCV_LC);
		
		tblCV_LC = new JTable();
		((DefaultTableCellRenderer)tblCV_LC.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tblCV_LC.getTableHeader().setBackground(Color.CYAN);
		tblCV_LC.setModel(dftCV_LC=new DefaultTableModel(
			new String[] {
				"M\u00E3 c\u00F4ng vi\u1EC7c", "T\u00EAn c\u00F4ng vi\u1EC7c", "S\u1ED1 NS hi\u1EC7n t\u1EA1i", "S\u1ED1 NS t\u1ED1i \u0111a", "Chuy\u00EAn M\u00F4n"
			},0) {
			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblCV_LC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaCVCu.setText(dftCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				dftNS_LC.setRowCount(0);
				loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString(), dftCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				cboNhanSuLuanChuyen.setSelectedIndex(0);
				if(dftNS_LC.getRowCount()>0)
					tblNhanSuLuanChuyen.setRowSelectionInterval(0, 0);
			}
		});
		tblCV_LC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCV_LC.setFont(new Font("Arial", Font.PLAIN, 13));
		scrCV_LC.setViewportView(tblCV_LC);
		
		cboCongViec_LC = new JComboBox<String>();
		cboCongViec_LC.addItem("Chọn chế độ hiển thị");
		cboCongViec_LC.addItem("Hiển thị công việc đã đủ nhân sự");
		cboCongViec_LC.addItem("Hiển thị công việc còn thiếu nhân sự");
		cboCongViec_LC.setBounds(130, 10, 240, 20);
		pnCV_LC.add(cboCongViec_LC);
		
		JPanel pnNS_LC = new JPanel();
		pnNS_LC.setBackground(SystemColor.info);
		pnNS_LC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnNS_LC.setBounds(5, 290, 969, 218);
		pnCenterLuanChuyen.add(pnNS_LC);
		pnNS_LC.setLayout(null);
		
		JLabel lblTimKiemNSLuanChuyen = new JLabel("Tìm kiếm nhân sự:");
		lblTimKiemNSLuanChuyen.setBounds(10, 10, 125, 25);
		pnNS_LC.add(lblTimKiemNSLuanChuyen);
		lblTimKiemNSLuanChuyen.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtTimNSLuanChuyen = new JTextField();
		txtTimNSLuanChuyen.setBounds(141, 10, 130, 25);
		pnNS_LC.add(txtTimNSLuanChuyen);
		txtTimNSLuanChuyen.setColumns(10);
		
		JPanel pnDSNS_LC = new JPanel();
		pnDSNS_LC.setBackground(SystemColor.info);
		pnDSNS_LC.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Danh sách nhân sự trong công trình đảm nhiệm công việc"));
		pnDSNS_LC.setBounds(5, 40, 959, 172);
		pnNS_LC.add(pnDSNS_LC);
		pnDSNS_LC.setLayout(null);
		
		JScrollPane scrNSLuanChuyen = new JScrollPane();
		scrNSLuanChuyen.setBounds(10, 20, 939, 141);
		pnDSNS_LC.add(scrNSLuanChuyen);
		
		tblNhanSuLuanChuyen = new JTable();
		tblNhanSuLuanChuyen.setFont(new Font("Arial", Font.PLAIN, 13));
		((DefaultTableCellRenderer)tblNhanSuLuanChuyen.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tblNhanSuLuanChuyen.getTableHeader().setBackground(Color.CYAN);
		tblNhanSuLuanChuyen.setModel(dftNS_LC=new DefaultTableModel(
			new String[] {
				"M\u00E3 nh\u00E2n s\u1EF1", "H\u1ECD \u0111\u1EC7m", "T\u00EAn", "Ng\u00E0y tham gia", "Chuy\u00EAn m\u00F4n", "M\u00E3 c\u00F4ng tr\u00ECnh", " C\u00F4ng vi\u1EC7c"
			},0	)
				{
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
				});
		tblNhanSuLuanChuyen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaNSLuanChuyen.setText(dftNS_LC.getValueAt(tblNhanSuLuanChuyen.getSelectedRow(), 0).toString());
			}
		});
		tblNhanSuLuanChuyen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrNSLuanChuyen.setViewportView(tblNhanSuLuanChuyen);
		
		JLabel lblHienThiTheoNSLuanChuyen = new JLabel("Sắp xếp theo:");
		lblHienThiTheoNSLuanChuyen.setBounds(497, 10, 100, 25);
		pnNS_LC.add(lblHienThiTheoNSLuanChuyen);
		lblHienThiTheoNSLuanChuyen.setFont(new Font("Arial", Font.BOLD, 13));
		
		cboNhanSuLuanChuyen = new JComboBox<String>();
		cboNhanSuLuanChuyen.addItem("Sắp xếp theo mã");
		cboNhanSuLuanChuyen.addItem("Sắp xếp theo tên");
		cboNhanSuLuanChuyen.addItem("Sắp xếp theo ngày tham gia");
		cboNhanSuLuanChuyen.setBounds(600, 10, 200, 25);
		pnNS_LC.add(cboNhanSuLuanChuyen);
		
		btnTimNS = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimNS.setBounds(270, 10, 60, 25);
		pnNS_LC.add(btnTimNS);
		
		JPanel pnNorthLuanChuyen = new JPanel();
		pnNorthLuanChuyen.setBounds(0, 0, 979, 35);
		pnLC.add(pnNorthLuanChuyen);
		pnNorthLuanChuyen.setBackground(Color.CYAN);
		
		JLabel lblLuanChuyenNhanSu = new JLabel("LUÂN CHUYỂN NHÂN SỰ");
		lblLuanChuyenNhanSu.setForeground(Color.BLUE);
		lblLuanChuyenNhanSu.setFont(new Font("Arial", Font.BOLD, 25));
		pnNorthLuanChuyen.add(lblLuanChuyenNhanSu);
		loadDbCt();
		cboCongViec_LC.setEnabled(false);
		cboNhanSuLuanChuyen.setEnabled(false);
		
		txtMaCTCu.setEditable(false);
		txtMaCVCu.setEditable(false);
		txtMaNSLuanChuyen.setEditable(false);
		cboNhanSuLuanChuyen.setSelectedIndex(0);
		
		//event
		btnTimCT.addActionListener(this);
		btnTimNS.addActionListener(this);
		cboCongViec_LC.addActionListener(this);
		cboNhanSuLuanChuyen.addActionListener(this);
		btnLuanChuyen.addActionListener(this);
		
		//set focus
		btnLuanChuyen.setFocusable(false);
		btnTimCT.setFocusable(false);
		btnTimNS.setFocusable(false);
		
		//phím tắt
		btnLuanChuyen.setMnemonic(KeyEvent.VK_L);
		btnTimCT.setMnemonic(KeyEvent.VK_ENTER);
		btnTimNS.setMnemonic(KeyEvent.VK_T);
		
		//set ToolTipText
		btnLuanChuyen.setToolTipText("Alt+L Luân chuyển nhân sự sang công trình khác");
		txtTimCT_LC.setToolTipText("Mã công trình bắt đầu bằng CT theo sau là 3 số");
		btnTimCT.setToolTipText("Alt+Enter Tìm kiếm công trình theo mã");
		txtTimNSLuanChuyen.setToolTipText("Mã nhân sự bắt đầu bằng NV theo sau là 4 số");
		btnTimNS.setToolTipText("Alt+T Tìm kiếm nhân sự theo mã");
		cboCongViec_LC.setToolTipText("Chọn chế độ hiển thị các công việc");
		cboNhanSuLuanChuyen.setToolTipText("Sắp xếp danh sách nhân sự theo 1 tiêu chí");
		
		return pnLC;
	}
	
	/**
	 * Đưa toàn bộ các công trình có lên bản
	 */
	public void loadDbCt() {
		List<CongTrinh> dsCT = congTrinhDao.getDSCT();
		for (CongTrinh i : dsCT) {
			addRowCT(i);
		}
	}
	
	/**
	 * Đưa danh sách công việc của 1 công trình vào bàn bản công việc
	 * 
	 * @param ct là mã công trình
	 * @return
	 */
	public void loadDsCongViec(String ct) {
		List<CongViec> ds = congViec_dao.getDSCV(ct);
		for (CongViec i : ds) {
			addRowCV(i);
		}
	}
	
	/**
	 * đưa danh sách nhân sự đang thực hiện công trình vào bản tblDsNsDaTG
	 * 
	 * @param ct là mã công trình
	 */
	public void loadNSTheoCT(String ct, String maCV) {
		String tenCv = null;
		for (NhanSu i : nhanSu_dao.getNsCT(ct, maCV)) {
			for (PhanCong j: phanCong_dao.getDsPC()) {
				if (j.getMaNV().trim().equals(j.getMaNV())) {
					for (CongViec cv : congViec_dao.getCongViec(ct)) {
						if (j.getMaCV().trim().equals(cv.getMaCV())) {
							tenCv=cv.getTenCV();
						}
					}
				}
			}
			String a[] = { i.getMaNhanSu(), i.getHoDem(), i.getTenNhanSu(),i.getNgayThamGia() + "", i.getChuyenMon(),ct,tenCv};
			dftNS_LC.addRow(a);
		}
	}
	/**
	 * Sắp xếp nhân sự trên table
	 * @param tieuChi
	 */
	public void loadNSCoSapXep(String maCT, String maCV, String tieuChi) {
		String tenCv = null;
		for (NhanSu i : nhanSu_dao.getListNhanSuSortTheoTieuChi(maCT, maCV, tieuChi)) {
			for (PhanCong j: phanCong_dao.getDsPC()) {
				if (j.getMaNV().trim().equals(j.getMaNV())) {
					for (CongViec cv : congViec_dao.getCongViec(maCT)) {
						if (j.getMaCV().trim().equals(cv.getMaCV())) {
							tenCv=cv.getTenCV();
						}
					}
				}
			}
			String a[] = { i.getMaNhanSu(), i.getHoDem(), i.getTenNhanSu(),i.getNgayThamGia() + "", i.getChuyenMon(),maCT,tenCv};
			dftNS_LC.addRow(a);
		}
	}
	/**
	 * Thêm 1 công trình vào table
	 * @param i công trình cần thêm
	 */
	public void addRowCT(CongTrinh i) {
		String[] a = { i.getMaCT(), i.getTenCT(), i.getNgayKhoiCong() + "", i.getSoLuongNS() + "",
				i.getTrangThai() };
		dftCT_LC.addRow(a);
	}
	/**
	 * Thêm 1 công việc vào table
	 * @param i công việc cần thêm
	 */
	public void addRowCV(CongViec i) {
		String a[] = { i.getMaCV(), i.getTenCV(), i.getSoNSHT() + "", i.getSoNSToiDa() + "", i.getChuyenMonCV() };
		dftCV_LC.addRow(a);
	}
	/**
	 * Hiển thị thông báo
	 * @param thongBao
	 */
	public void showMess(String thongBao) {
		JOptionPane.showMessageDialog(null, thongBao);
	}
	
	/**
	 * Đưa dữ liệu từ table vào text và chọn dòng(bôi đen) dòng table
	 * @param row dòng cần thực hiện
	 */
	public void reviewCongTrinh(int row) {
		tblCT_LC.setRowSelectionInterval(row, row);
		txtMaCTCu.setText(dftCT_LC.getValueAt(row, 0).toString());
		dftCV_LC.setRowCount(0);
		String s =dftCT_LC.getValueAt(row, 0).toString();
		loadDsCongViec(s);
		if(dftCV_LC.getRowCount()>0)
		{
			tblCV_LC.setRowSelectionInterval(0, 0);
			txtMaCVCu.setText(dftCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
			dftNS_LC.setRowCount(0);
			loadNSTheoCT(dftCT_LC.getValueAt(row,0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
			if(dftNS_LC.getRowCount()>0) {
				tblNhanSuLuanChuyen.setRowSelectionInterval(0, 0);
				txtMaNSLuanChuyen.setText(dftNS_LC.getValueAt(tblNhanSuLuanChuyen.getSelectedRow(), 0).toString());
			}
			else
				txtMaNSLuanChuyen.setText("");
		}
		else
			txtMaCVCu.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnTimCT)) {
			String maCTTim = txtTimCT_LC.getText();
				if (!maCTTim.isEmpty()) {
					if (!(txtTimCT_LC.getText().toUpperCase() + "").matches("^CT[0-9]{3}"))// mã công trình bắt đầu bằng CT vào theo sau là 3 số
					{
						showMess("Mã công trình không hợp lệ!");
						txtTimCT_LC.requestFocus();
						txtTimCT_LC.selectAll();
					}
					else {
					dftCT_LC.setRowCount(0);
					for (CongTrinh congTrinh : congTrinhDao.getDSCT()) {
						if (congTrinh.getMaCT().equalsIgnoreCase(maCTTim))
							addRowCT(congTrinh);
					}
					if (tblCT_LC.getRowCount() > 0)
						reviewCongTrinh(0);
					else {
						dftCV_LC.setRowCount(0);
						dftNS_LC.setRowCount(0);
					}
					}
				} else {
					dftCT_LC.setRowCount(0);
					loadDbCt();
					if(dftCT_LC.getRowCount()>0)
						reviewCongTrinh(0);
				}
		}
		else if(e.getSource().equals(btnTimNS)) {
			if(tblCT_LC.getSelectedRow()<0) {
				showMess("Chọn công trình!");
			}
			else {
			String maNS = txtTimNSLuanChuyen.getText();
			if (!maNS.isEmpty()) {
				if (!(txtTimNSLuanChuyen.getText().toUpperCase() + "").matches("^NV[0-9]{4}"))// mã công trình bắt đầu bằng CT vào theo sau là 3 số
				{
					showMess("Mã nhân sự không hợp lệ!");
					txtTimNSLuanChuyen.requestFocus();
					txtTimNSLuanChuyen.selectAll();
				}
				else {
				int i;
				for (i = 0; i < dftNS_LC.getRowCount(); i++) {
					if(dftNS_LC.getValueAt(i, 0).toString().equals(txtTimNSLuanChuyen.getText()))
					{
						tblNhanSuLuanChuyen.changeSelection(i, i, true, true);
						tblNhanSuLuanChuyen.setRowSelectionInterval(i, i);
						break;
					}
				}
				if(i==dftNS_LC.getRowCount()) {
					showMess("Không tìm thấy nhân sự!");
				}
				}
			} else {
				dftNS_LC.setRowCount(0);
				loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				if(dftNS_LC.getRowCount()>0)
				{
					tblNhanSuLuanChuyen.changeSelection(0, 0, true, true);
					tblNhanSuLuanChuyen.setRowSelectionInterval(0, 0);
				}
			}
		}}
		else if(e.getSource().equals(cboCongViec_LC)) {
			dftCV_LC.setRowCount(0);
			dftNS_LC.setRowCount(0);
			if(cboCongViec_LC.getSelectedItem().toString().equalsIgnoreCase("Chọn chế độ hiển thị")) {
				loadDsCongViec(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString());
				if(dftCV_LC.getRowCount()>0)
				{
					tblCV_LC.setRowSelectionInterval(0, 0);
					loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				}
			}
			else if(cboCongViec_LC.getSelectedItem().toString().equalsIgnoreCase("Hiển thị công việc đã đủ nhân sự")) {
				List<CongViec> ds = congViec_dao.getDSCV(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString());
				for (CongViec i : ds) {
					if(i.getSoNSHT()==i.getSoNSToiDa())
						addRowCV(i);
				}
				if(dftCV_LC.getRowCount()>0)
				{
					tblCV_LC.setRowSelectionInterval(0, 0);
					loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				}
			}
			else {
				List<CongViec> ds = congViec_dao.getDSCV(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString());
				for (CongViec i : ds) {
					if(i.getSoNSHT()!=i.getSoNSToiDa())
						addRowCV(i);
				}
				if(dftCV_LC.getRowCount()>0)
				{
					tblCV_LC.setRowSelectionInterval(0, 0);
					loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
				}
			}
		}
		else if(e.getSource().equals(cboNhanSuLuanChuyen)) {
			String maCT = dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString();
			if(tblCV_LC.getSelectedRow()>0) {
			String maCV = dftCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString();
			if(cboNhanSuLuanChuyen.getSelectedItem().toString().equalsIgnoreCase("Sắp xếp theo tên")) {
					dftNS_LC.setRowCount(0);
					loadNSCoSapXep(maCT, maCV, "Tên");
				}
			else if(cboNhanSuLuanChuyen.getSelectedItem().toString().equalsIgnoreCase("Sắp xếp theo ngày tham gia")) {
				dftNS_LC.setRowCount(0);
				loadNSCoSapXep(maCT, maCV, "Ngày Tham Gia");
			}
			if(dftNS_LC.getRowCount()>0) {
				tblNhanSuLuanChuyen.setRowSelectionInterval(0, 0);
				txtMaNSLuanChuyen.setText(dftNS_LC.getValueAt(0, 0).toString());
			}
			else
				txtMaNSLuanChuyen.setText("");
		}
		}
		else if(e.getSource().equals(btnLuanChuyen)) {
			int row = tblNhanSuLuanChuyen.getSelectedRow();
			if(row >= 0) {
				JPanel pnLC = new JPanel();
				pnLC.setPreferredSize(new Dimension(400, 50));
				JLabel lblMaCTMoi = new JLabel("Mã công trình mới");
				pnLC.add(lblMaCTMoi);
				JComboBox<String> cboMaCTMoi = new JComboBox<String>();
				for (CongTrinh ct : congTrinhDao.getDSCT()) {
					cboMaCTMoi.addItem(ct.getMaCT());
				}
				pnLC.add(cboMaCTMoi);
				JLabel lblMaCVMoi = new JLabel("Mã công việc mới");
				pnLC.add(lblMaCVMoi);
				JComboBox<String> cboMaCVMoi = new JComboBox<String>();
				for (CongViec cv : congViec_dao.getDSCV(cboMaCTMoi.getSelectedItem()+"")) {
					if(cv.getSoNSHT()<cv.getSoNSToiDa())
						cboMaCVMoi.addItem(cv.getMaCV());
				}
				cboMaCTMoi.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cboMaCVMoi.removeAllItems();
						for (CongViec cv : congViec_dao.getDSCV(cboMaCTMoi.getSelectedItem()+"")) {
							if(cv.getSoNSHT()<cv.getSoNSToiDa())
								cboMaCVMoi.addItem(cv.getMaCV());
						}
					}
				});
				pnLC.add(cboMaCVMoi);
				int reg = JOptionPane.showConfirmDialog(null, pnLC, "Chọn nơi luân chuyển", JOptionPane.OK_CANCEL_OPTION);
				if(reg == JOptionPane.OK_OPTION) {
					if((cboMaCVMoi.getSelectedItem()+"").equals("null")) {
						showMess("Chọn mã công việc!");
					}
					String maCV = cboMaCVMoi.getSelectedItem()+"";
					String maCM = congViec_dao.getMaCMFromMaCV(maCV);
					String maNV = dftNS_LC.getValueAt(tblNhanSuLuanChuyen.getSelectedRow(), 0).toString();
					if(nhanSu_dao.kiemTraNhanVien_ChuyenMon(maNV, maCM)) {
						if(phanCong_dao.luanChuyenNS(maNV, cboMaCTMoi.getSelectedItem()+"", maCV))
							showMess("Luân chuyển nhân sự "+maNV+" sang công trình "+cboMaCTMoi.getSelectedItem()+""+" thành công!");
					}
					else
						showMess("Chuyên môn nhân viên không phù hợp với công việc này!");
				}
			}
			else
				showMess("Chưa chọn nhân sự!");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		dftCV_LC.setRowCount(0);
		String s =dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(), 0).toString();
		loadDsCongViec(s);
		dftNS_LC.setRowCount(0);
		loadNSTheoCT(dftCT_LC.getValueAt(tblCT_LC.getSelectedRow(),0).toString(),tblCV_LC.getValueAt(tblCV_LC.getSelectedRow(), 0).toString());
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
}
