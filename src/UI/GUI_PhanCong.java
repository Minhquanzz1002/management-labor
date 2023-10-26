package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import dao.ChuyenMon_dao;
import dao.CongTrinh_dao;
import dao.CongViec_dao;
import dao.NhanSu_dao;
import dao.PhanCong_dao;
import entity.ChuyenMon;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanSu;
import entity.PhanCong;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GUI_PhanCong extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNS;
	private JTextField txtMaCV;
	private JTextField txtMaCT;
	private JTextField txtTimCT;
	private JTable tblDsDaPC;
	private JTable tblDsCvPC;
	private JTextField txtTimNSPC;
	private JTable tblDsNsDaTG;
	private JTextField txtTimNsChuaTG;
	private JTable tblDsNsChuaTG;
	private DefaultTableModel dtmDsCvPC;
	private DefaultTableModel dtmDsNsChuaTG;
	private DefaultTableModel dtmDsNsDaTG;
	private DefaultTableModel dtmDsDaPC;
	private JButton btnPhanThuCong;
	private JComboBox<String> cboHienThiCVPC;
	private CongTrinh_dao dao = new CongTrinh_dao();
	private CongViec_dao congViec_dao = new CongViec_dao();
	private NhanSu_dao nhanSu_dao = new NhanSu_dao();
	private ChuyenMon_dao chuyenMon_dao = new ChuyenMon_dao();
	private PhanCong_dao phanCong_dao = new PhanCong_dao();
	private JDateChooser txtNgatTG;
	private JButton btnPhanCongTD;
	private JButton btnXoaTrang;
	private JButton btnTimNsDaTG;
	private JButton btnTimNsChuaTG;
	private JButton btnTimCT;

	public GUI_PhanCong() {
		getContentPane().add(addControl());

	}

	/**
	 * Tạo Giao Diện tab Phân Công
	 * 
	 * @return componemt
	 */
	public Component addControl() {
		JPanel pnPhanCong = new JPanel();
		pnPhanCong.setBounds(0, 0, 979, 554);

		pnPhanCong.setLayout(null);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setBackground(Color.CYAN);
		pnTieuDe.setBounds(0, 0, 979, 35);
		pnPhanCong.add(pnTieuDe);

		JLabel lblNewLabel_1 = new JLabel("Phân Công");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		pnTieuDe.add(lblNewLabel_1);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBackground(new Color(238, 232, 170));
		pnTacVu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"\u0110i\u1EC1n Th\u00F4ng Tin Ph\u00E2n C\u00F4ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		pnTacVu.setBounds(0, 37, 979, 86);
		pnPhanCong.add(pnTacVu);
		pnTacVu.setLayout(null);

		JLabel lblMaNS = new JLabel("Mã Nhân Sự:");
		lblMaNS.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaNS.setBounds(10, 22, 100, 20);
		pnTacVu.add(lblMaNS);

		txtMaNS = new JTextField();
		txtMaNS.setEditable(false);
		txtMaNS.setColumns(10);
		txtMaNS.setBounds(110, 22, 150, 20);
		pnTacVu.add(txtMaNS);

		txtMaCV = new JTextField();
		txtMaCV.setEditable(false);
		txtMaCV.setColumns(10);
		txtMaCV.setBounds(400, 22, 150, 20);
		pnTacVu.add(txtMaCV);

		JLabel lblMaCV = new JLabel("Mã Công Việc:");
		lblMaCV.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaCV.setBounds(300, 22, 100, 20);
		pnTacVu.add(lblMaCV);

		txtMaCT = new JTextField();
		txtMaCT.setEditable(false);
		txtMaCT.setColumns(10);
		txtMaCT.setBounds(110, 53, 150, 20);
		pnTacVu.add(txtMaCT);

		JLabel lblMaCT = new JLabel("Mã Công Trình:");
		lblMaCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaCT.setBounds(10, 53, 100, 20);
		pnTacVu.add(lblMaCT);

		JLabel lblNgayTG = new JLabel("Ngày Tham Gia:");
		lblNgayTG.setFont(new Font("Arial", Font.BOLD, 13));
		lblNgayTG.setBounds(300, 53, 105, 20);
		pnTacVu.add(lblNgayTG);

		txtNgatTG = new JDateChooser();
		txtNgatTG.setBounds(405, 53, 145, 20);
		txtNgatTG.setDateFormatString("yyyy-MM-dd");
		pnTacVu.add(txtNgatTG);

		btnPhanThuCong = new JButton("Phân Công Thủ Công");
		btnPhanThuCong.setFont(new Font("Arial", Font.BOLD, 13));
		btnPhanThuCong.setBounds(622, 53, 200, 25);
		btnPhanThuCong.setFocusable(false);
		pnTacVu.add(btnPhanThuCong);

		btnPhanCongTD = new JButton("Phân Công Tự Động");
		btnPhanCongTD.setFont(new Font("Arial", Font.BOLD, 13));
		btnPhanCongTD.setBounds(622, 22, 200, 25);
		btnPhanCongTD.setFocusable(false);
		pnTacVu.add(btnPhanCongTD);

		btnXoaTrang = new JButton("Hủy Phân Công");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaTrang.setBounds(832, 22, 136, 54);
		btnXoaTrang.setFocusable(false);
		pnTacVu.add(btnXoaTrang);

		JPanel pndanhSach = new JPanel();
		pndanhSach.setBounds(0, 122, 979, 432);
		pnPhanCong.add(pndanhSach);
		pndanhSach.setLayout(null);

		JPanel pnDaPc = new JPanel();
		pnDaPc.setBackground(SystemColor.info);
		pnDaPc.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "D\u1EF1 \u00C1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		pnDaPc.setBounds(5, 0, 475, 200);
		pndanhSach.add(pnDaPc);
		pnDaPc.setLayout(null);

		JLabel lblTimCT = new JLabel("Tìm Kiếm Công Trình:");
		lblTimCT.setFont(new Font("Arial", Font.BOLD, 13));
		lblTimCT.setBounds(10, 20, 150, 20);
		pnDaPc.add(lblTimCT);

		txtTimCT = new JTextField();
		txtTimCT.setColumns(10);
		txtTimCT.setBounds(160, 20, 150, 20);
		pnDaPc.add(txtTimCT);

		JScrollPane scrDaPc = new JScrollPane();
		scrDaPc.setBounds(10, 50, 460, 140);
		pnDaPc.add(scrDaPc);

		tblDsDaPC = new JTable();
		tblDsDaPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsDaPC.setModel(
				dtmDsDaPC = new DefaultTableModel(new String[] { "M\u00E3 D\u1EF1 \u00C1n", "T\u00EAn D\u1EF1 \u00C1n",
						"Ng\u00E0y B\u1EAFt \u0110\u1EA7u", "S\u1ED1 L\u01B0\u1EE3ng ", "Tr\u1EA1ng Th\u00E1i" }, 0) {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		scrDaPc.setViewportView(tblDsDaPC);

		btnTimCT = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimCT.setBounds(310, 19, 50, 23);
		btnTimCT.setFocusable(false);
		pnDaPc.add(btnTimCT);
		((DefaultTableCellRenderer) tblDsDaPC.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		tblDsDaPC.getTableHeader().setBackground(Color.CYAN);

		JPanel pnCvPC = new JPanel();
		pnCvPC.setBackground(SystemColor.info);
		pnCvPC.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "C\u00F4ng Vi\u1EC7c",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCvPC.setBounds(485, 0, 490, 200);
		pndanhSach.add(pnCvPC);
		pnCvPC.setLayout(null);

		JLabel lblTimCVPC = new JLabel("Hiển Thị Công Việc:");
		lblTimCVPC.setFont(new Font("Arial", Font.BOLD, 13));
		lblTimCVPC.setBounds(10, 20, 150, 20);
		pnCvPC.add(lblTimCVPC);

		cboHienThiCVPC = new JComboBox<String>();
		cboHienThiCVPC.setBounds(160, 20, 150, 20);
		cboHienThiCVPC.addItem("Tất Cả");
		cboHienThiCVPC.addItem("Cần Được Phân Công");
		cboHienThiCVPC.addItem("Đã Đủ Người");
		pnCvPC.add(cboHienThiCVPC);

		JScrollPane scrCvPC = new JScrollPane();
		scrCvPC.setBounds(10, 50, 475, 140);
		pnCvPC.add(scrCvPC);

		tblDsCvPC = new JTable();
		tblDsCvPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsCvPC.setModel(dtmDsCvPC = new DefaultTableModel(new String[] { "M\u00E3 C\u00F4ng Vi\u1EC7c",
				"T\u00EAn C\u00F4ng Vi\u1EC7c", "S\u1ED1 L\u01B0\u1EE3ng Hi\u1EC7n T\u1EA1i",
				"S\u1ED1 L\u01B0\u1EE3ng T\u1ED1i \u0110a", "chuy\u00EAn m\u00F4n" }, 0) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrCvPC.setViewportView(tblDsCvPC);
		((DefaultTableCellRenderer) tblDsCvPC.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		tblDsCvPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsCvPC.getTableHeader().setBackground(Color.CYAN);

		JPanel pnNSDaTG = new JPanel();
		pnNSDaTG.setBackground(SystemColor.info);
		pnNSDaTG.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Danh Sách Nh\u00E2n S\u1EF1 C\u1EE7a D\u1EF1 \u00C1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnNSDaTG.setBounds(5, 210, 475, 200);
		pndanhSach.add(pnNSDaTG);
		pnNSDaTG.setLayout(null);

		JLabel lblTmNSPC = new JLabel("Tìm Kiếm Nhân Sự:");
		lblTmNSPC.setFont(new Font("Arial", Font.BOLD, 13));
		lblTmNSPC.setBounds(10, 20, 150, 20);
		pnNSDaTG.add(lblTmNSPC);

		txtTimNSPC = new JTextField();
		txtTimNSPC.setColumns(10);
		txtTimNSPC.setBounds(160, 20, 150, 20);
		pnNSDaTG.add(txtTimNSPC);

		JScrollPane scrNSDATG = new JScrollPane();
		scrNSDATG.setBounds(10, 50, 460, 140);
		pnNSDaTG.add(scrNSDATG);

		tblDsNsDaTG = new JTable();
		tblDsNsDaTG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsNsDaTG.setModel(dtmDsNsDaTG = new DefaultTableModel(new String[] { "M\u00E3 Nh\u00E2n S\u1EF1",
				"H\u1ECD T\u00EAn", "Chuy\u00EAn M\u00F4n", "Ph\u00F2ng Ban", "Ng\u00E0y Tham Gia" }, 0) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		});
		scrNSDATG.setViewportView(tblDsNsDaTG);

		btnTimNsDaTG = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimNsDaTG.setBounds(310, 19, 50, 23);
		btnTimNsDaTG.setFocusable(false);
		pnNSDaTG.add(btnTimNsDaTG);
		((DefaultTableCellRenderer) tblDsNsDaTG.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		tblDsNsDaTG.getTableHeader().setBackground(Color.CYAN);

		JPanel pnNSChuaTG = new JPanel();
		pnNSChuaTG.setBackground(SystemColor.info);
		pnNSChuaTG.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Danh S\u00E1ch Nh\u00E2n S\u1EF1 Ch\u01B0a \u0110\u01B0\u1EE3c Ph\u00E2n C\u00F4ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnNSChuaTG.setBounds(485, 210, 490, 200);
		pndanhSach.add(pnNSChuaTG);
		pnNSChuaTG.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm Kiếm Nhân Sự:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 20, 150, 20);
		pnNSChuaTG.add(lblNewLabel);

		txtTimNsChuaTG = new JTextField();
		txtTimNsChuaTG.setColumns(10);
		txtTimNsChuaTG.setBounds(160, 20, 150, 20);
		pnNSChuaTG.add(txtTimNsChuaTG);

		JScrollPane scrNSChuaTG = new JScrollPane();
		scrNSChuaTG.setBounds(10, 50, 475, 140);
		pnNSChuaTG.add(scrNSChuaTG);

		tblDsNsChuaTG = new JTable();
		tblDsNsChuaTG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsNsChuaTG.setModel(dtmDsNsChuaTG = new DefaultTableModel(new String[] { "M\u00E3 Nh\u00E2n S\u1EF1",
				"H\u1ECD T\u00EAn", "Chuy\u00EAn M\u00F4n", "Ph\u00F2ng ban" }, 0) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrNSChuaTG.setViewportView(tblDsNsChuaTG);

		btnTimNsChuaTG = new JButton(new ImageIcon("img\\icon\\search.png"));
		btnTimNsChuaTG.setBounds(310, 19, 50, 23);
		btnTimNsChuaTG.setFocusable(false);
		pnNSChuaTG.add(btnTimNsChuaTG);
		((DefaultTableCellRenderer) tblDsNsChuaTG.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		tblDsNsChuaTG.getTableHeader().setBackground(Color.CYAN);
		cboHienThiCVPC.setEnabled(false);
		btnPhanCongTD.setEnabled(false);
		btnPhanThuCong.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		btnPhanThuCong.addActionListener(this);
		tblDsDaPC.addMouseListener(this);
		tblDsCvPC.addMouseListener(eventCV());
		tblDsNsDaTG.addMouseListener(eventTblDsNsDaTG());
		tblDsNsChuaTG.addMouseListener(eventTblDsNsChuaTG());
		cboHienThiCVPC.addActionListener(this);
		btnPhanCongTD.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTimCT.addActionListener(this);
		btnTimNsDaTG.addActionListener(this);
		btnTimNsChuaTG.addActionListener(this);
		cboHienThiCVPC.addActionListener(this);
		loadDbCt();
		loadNSChuaCT();
		taoPhimTat();

		return pnPhanCong;
	}

	/**
	 * sự kiện MouseListener của bản Công Việc( tblDsCvPC )
	 * 
	 * @return MouseListener
	 */
	public MouseListener eventCV() {
		MouseListener cv = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int soLHT1 = Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 2).toString());
				int soTD1 = Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 3).toString());
				if (dtmDsCvPC.getRowCount() == 0) {
					btnPhanCongTD.setEnabled(false);
					btnPhanThuCong.setEnabled(false);
				}
				if (soLHT1 < soTD1) {
					btnPhanCongTD.setEnabled(true);
					btnPhanThuCong.setEnabled(true);
				} else {
					btnPhanCongTD.setEnabled(false);
					btnPhanThuCong.setEnabled(false);
				}
				txtMaCV.setText(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 0).toString());
				loadNSChuaCTTheoCM((dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 4).toString()));
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int soLHT1 = Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 2).toString());
				int soTD1 = Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 3).toString());
				if (dtmDsCvPC.getRowCount() == 0) {
					btnPhanCongTD.setEnabled(false);
					btnPhanThuCong.setEnabled(false);
				}
				if (soLHT1 < soTD1) {
					btnPhanCongTD.setEnabled(true);
					btnPhanThuCong.setEnabled(true);
				} else {
					btnPhanCongTD.setEnabled(false);
					btnPhanThuCong.setEnabled(false);
				}
				txtMaCV.setText(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 0).toString());
				loadNSChuaCTTheoCM((dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 4).toString()));

			}
		};
		return cv;
	}

	public void taoPhimTat() {
		btnPhanCongTD.setMnemonic(KeyEvent.VK_S);
		btnPhanCongTD.setToolTipText("phím tắt Phân Công Tự Động là ALT + S");
		btnPhanThuCong.setMnemonic(KeyEvent.VK_D);
		btnPhanThuCong.setToolTipText("phím tắt Phân Công Thủ Công ALT + D");
		btnXoaTrang.setMnemonic(KeyEvent.VK_F);
		btnXoaTrang.setToolTipText("phím tắt Hủy Phân Công là ALT + F");

		btnTimCT.setMnemonic(KeyEvent.VK_C);
		btnTimCT.setToolTipText("phím tắt Tìm Công Trình là ALT + C");
		btnTimNsDaTG.setMnemonic(KeyEvent.VK_V);
		btnTimNsDaTG.setToolTipText("phím tắt Tìm Công Trình là ALT + V");
		btnTimNsChuaTG.setMnemonic(KeyEvent.VK_B);
		btnTimNsChuaTG.setToolTipText("phím tắt Tìm Công Trình là ALT + B");
	}

	/**
	 * sự kiện MouseListener của bản nhân sự chưa phân công( tblDsNsChuaTG )
	 * 
	 * @return MouseListener
	 */
	public MouseListener eventTblDsNsChuaTG() {
		MouseListener cv = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				txtMaNS.setText(dtmDsNsChuaTG.getValueAt(tblDsNsChuaTG.getSelectedRow(), 0).toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaNS.setText(dtmDsNsChuaTG.getValueAt(tblDsNsChuaTG.getSelectedRow(), 0).toString());
			}
		};
		return cv;
	}

	/**
	 * sự kiện MouseListener của bản nhân sự đã phân công( tblDsNsChuaTG )
	 * 
	 * @return MouseListener
	 */
	public MouseListener eventTblDsNsDaTG() {
		MouseListener cv = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				btnXoaTrang.setEnabled(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnXoaTrang.setEnabled(true);
			}
		};
		return cv;
	}

	/**
	 * Đưa toàn bộ các công trình có lên bản
	 */
	public void loadDbCt() {
		List<CongTrinh> dsCT = dao.getDSCT();
		for (CongTrinh i : dsCT) {
			String[] a = { i.getMaCT(), i.getTenCT(), i.getNgayKhoiCong() + "", i.getSoLuongNS() + "",
					i.getTrangThai() };
			dtmDsDaPC.addRow(a);
		}
	}

	/**
	 * đưa danh sách nhân sự chưa có công trình vào bản
	 */
	public void loadNSChuaCT() {
		for (NhanSu i : nhanSu_dao.getNSChuaCT()) {
			String a[] = { i.getMaNhanSu(), i.getHoDem() + " " + i.getTenNhanSu(), i.getChuyenMon(), i.getMaPB() };
			dtmDsNsChuaTG.addRow(a);
		}
	}

	/**
	 * đưa danh sách nhân sự chưa có công trình theo mã chuyên môn vào bản
	 * 
	 * @param cm là mã chuyên môn
	 */
	public void loadNSChuaCTTheoCM(String cm) {
		String macm = null;
		for (ChuyenMon i : chuyenMon_dao.getListCM()) {
			if (i.getTenCM().equals(cm)) {
				macm = i.getMaCM();
			}
		}
		dtmDsNsChuaTG.setRowCount(0);
		for (NhanSu i : nhanSu_dao.getNSChuaCTTheoCM(macm)) {
			String a[] = { i.getMaNhanSu(), i.getHoDem() + " " + i.getTenNhanSu(), i.getChuyenMon(), i.getMaPB() };

			dtmDsNsChuaTG.addRow(a);
		}
	}

	/**
	 * Đưa danh sách công việc của 1 công trình vào bàn bản công việc
	 * 
	 * @param ct là mã công trình
	 * @return
	 */
	public void loadDsCongViec(String ct) {
		List<CongViec> ds = congViec_dao.getCongViec(ct);
		for (CongViec i : ds) {
			String a[] = { i.getMaCV(), i.getTenCV(), i.getSoNSToiDa() + "", i.getSoNSHT() + "", i.getChuyenMonCV() };
			dtmDsCvPC.addRow(a);
		}
	}

	/**
	 * đưa danh sách nhân sự đang thực hiện công trình vào bản tblDsNsDaTG
	 * 
	 * @param ct là mã công trình
	 */
	public void loadNSTheoCT(String ct) {
		for (NhanSu i : nhanSu_dao.getNsCT(ct)) {
			String a[] = { i.getMaNhanSu(), i.getHoDem() + " " + i.getTenNhanSu(), i.getChuyenMon(), i.getMaPB(),
					i.getNgayThamGia() + "" };
			dtmDsNsDaTG.addRow(a);
		}
	}

	/**
	 * lấy danh sách ngẩu nhiên từ 0 đến max -1 với số lượng bằng soLuong
	 * 
	 * @param min     là giá trị nhỏ nhất
	 * @param max     là giá trị lớn nhất
	 * @param soLuong số lượng cần lấy
	 * @return list
	 */
	public List<Integer> layNsNgauNhien(int min, int max, int soLuong) {
		List<Integer> ds = new ArrayList<Integer>();
		Random random = new Random();
		while (ds.size() < soLuong) {
			int i = random.nextInt((max - min) + 1) + min;
			if (!ds.contains(i)) {
				ds.add(i);
			}
		}
		return ds;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnPhanThuCong)) {
			if (txtNgatTG.getDate() == null || txtMaCT.getText().equals("") || txtMaNS.getText().equals("")
					|| txtMaCV.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Chưa Điền Đủ Thông Tin!!");
			} else {
				if (txtNgatTG.getDate().before(new Date())) {
					JOptionPane.showMessageDialog(null, "Ngày tham gia phải lớn hơn ngày hiện tại!!!");
				} else {
					PhanCong pc = new PhanCong(txtMaNS.getText(), txtMaCT.getText(), txtMaCV.getText(),
							txtNgatTG.getDate());
					if (phanCong_dao.insetPC(pc)) {
						int n = tblDsDaPC.getSelectedRow();
						JOptionPane.showMessageDialog(null,
								"Phân Công nhân sự " + pc.getMaNV() + " Vào Công việc "
										+ dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 4) + " Của Công Trình "
										+ pc.getMaCT() + " Thành công");
						dtmDsDaPC.setRowCount(0);
						dtmDsCvPC.setRowCount(0);
						dtmDsNsDaTG.setRowCount(0);
						dtmDsNsChuaTG.setRowCount(0);
						loadDbCt();
						tblDsDaPC.setRowSelectionInterval(n, n);
						loadDsCongViec(dtmDsDaPC.getValueAt(n, 0).toString());
						loadNSTheoCT(dtmDsDaPC.getValueAt(n, 0).toString());
						loadNSChuaCT();
					} else {
						JOptionPane.showMessageDialog(null,
								"Phân Công nhân sự " + pc.getMaNV() + " Vào Công việc "
										+ dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 4) + " Của Công Trình "
										+ pc.getMaCT() + " Không Thành công");
					}
				}
			}
		} else if (e.getSource().equals(btnPhanCongTD)) {
			if (tblDsCvPC.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Công Việc Để Phân Công");
			} else {
				int n = tblDsDaPC.getSelectedRow();

				int max = dtmDsNsChuaTG.getRowCount();

				JPanel pnPC = new JPanel();
				JLabel lblNhapSoLuong = new JLabel("Nhập số Lượng Phân Công:");
				lblNhapSoLuong.setFont(new Font("Arial", Font.BOLD, 13));
				pnPC.add(lblNhapSoLuong);
				JSpinField txtNhapSoLuong = new JSpinField();
				txtNhapSoLuong.setPreferredSize(new Dimension(100, 25));
				txtNhapSoLuong.setValue(1);
				txtNhapSoLuong.setMinimum(1);
				int sl = Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 3).toString())
						- Integer.parseInt(dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 2).toString());
				if (sl >= tblDsNsChuaTG.getRowCount()) {
					txtNhapSoLuong.setMaximum(tblDsNsChuaTG.getRowCount());
				} else {
					txtNhapSoLuong.setMaximum(sl);
				}
				pnPC.add(txtNhapSoLuong);

				if (tblDsNsChuaTG.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không Còn Nhân Sự");
				} else {
					int chon = JOptionPane.showConfirmDialog(null, pnPC, "Nhập Số Lượng Phân Công",
							JOptionPane.OK_CANCEL_OPTION);
					if (chon == JOptionPane.OK_OPTION) {
						List<PhanCong> ds = new ArrayList<PhanCong>();
						PhanCong pc = null;
						String s = "";
						int soLuong = txtNhapSoLuong.getValue();
						for (Integer i : layNsNgauNhien(0, max - 1, soLuong)) {
							s += "+ " + dtmDsNsChuaTG.getValueAt(i, 0).toString() + "\n";
							pc = new PhanCong(dtmDsNsChuaTG.getValueAt(i, 0).toString(), txtMaCT.getText(),
									txtMaCV.getText(), new Date());
							ds.add(pc);
						}
						Object[] tacVu = { "Đồng ý", "Hủy" };
						int jop = JOptionPane.showOptionDialog(null,
								"Bạn có muốn phân công danh sách các nhân sự:\n" + s + "vào công trình "
										+ dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString()
										+ " với công việc " + dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 1),
								"Phân Công Tự động", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								tacVu, null);
						if (jop == JOptionPane.OK_OPTION) {
							boolean flag = true;
							for (PhanCong i : ds) {
								flag = phanCong_dao.insetPC(i);
							}
							if (flag) {
								JOptionPane.showMessageDialog(null,
										"Phân công danh sách các nhân sự:\n" + s + "vào công trình "
												+ dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString()
												+ " với công việc "
												+ dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 1) + "\nTHÀNH CÔNG",
										"Phân Công Thành Công", JOptionPane.INFORMATION_MESSAGE);
								GUI_UNGDUNG.loadDuLieuThongKe();
								dtmDsDaPC.setRowCount(0);
								dtmDsCvPC.setRowCount(0);
								dtmDsNsDaTG.setRowCount(0);
								dtmDsNsChuaTG.setRowCount(0);
								loadDbCt();
								tblDsDaPC.setRowSelectionInterval(n, n);
								loadDsCongViec(dtmDsDaPC.getValueAt(n, 0).toString());
								loadNSTheoCT(dtmDsDaPC.getValueAt(n, 0).toString());
								loadNSChuaCT();
								btnPhanCongTD.setEnabled(false);
								btnPhanThuCong.setEnabled(false);
							} else {
								JOptionPane.showMessageDialog(null,
										"Phân công danh sách các nhân sự:\n" + s + "vào công trình "
												+ dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString()
												+ " với công việc "
												+ dtmDsCvPC.getValueAt(tblDsCvPC.getSelectedRow(), 1) + "\nTHẤT BẠI!!!",
										"Phân Công Thất Bại", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}

		}

		else if (e.getSource().equals(btnXoaTrang)) {
			if (tblDsNsDaTG.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Nhân Viên Muốn Hủy Phân Công !!");
			} else {
				int n = tblDsDaPC.getSelectedRow();
				String a = dtmDsNsDaTG.getValueAt(tblDsNsDaTG.getSelectedRow(), 0).toString();
				int jop = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc muốn hủy phân công cho nhân sự " + a + " ở công trình "
								+ dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0),
						"HỦY PHÂN CÔNG", JOptionPane.YES_NO_OPTION);
				if (jop == JOptionPane.YES_OPTION) {
					if (phanCong_dao.deletePC(a)) {
						JOptionPane.showMessageDialog(null, "Đã Hủy Phân Công Cho Nhân Sự " + a + " Thành Công");
						GUI_UNGDUNG.loadDuLieuThongKe();
						dtmDsDaPC.setRowCount(0);
						dtmDsCvPC.setRowCount(0);
						dtmDsNsDaTG.setRowCount(0);
						dtmDsNsChuaTG.setRowCount(0);
						loadDbCt();
						tblDsDaPC.setRowSelectionInterval(n, n);
						loadDsCongViec(dtmDsDaPC.getValueAt(n, 0).toString());
						loadNSTheoCT(dtmDsDaPC.getValueAt(n, 0).toString());
						loadNSChuaCT();
						btnXoaTrang.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Đã Hủy Phân Công Cho Nhân Sự " + a + " Thất Bại");
					}
				}
			}
		} else if (e.getSource().equals(btnTimCT)) {
			if (!txtTimCT.getText().trim().isEmpty()) {
				for (int i = 0; i < tblDsDaPC.getRowCount(); i++) {
					if (txtTimCT.getText().toString().equals(dtmDsDaPC.getValueAt(i, 0))) {
						tblDsDaPC.changeSelection(i, 0, true, true);
						tblDsDaPC.setRowSelectionInterval(i, i);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thông tin chưa được nhập hoặc nhập sai cú pháp");
				txtTimCT.requestFocus();
				tblDsDaPC.setSelectionMode(0);
			}
		} else if (e.getSource().equals(btnTimNsDaTG)) {
			if (!txtTimNSPC.getText().trim().isEmpty()) {
				for (int i = 0; i < tblDsNsDaTG.getRowCount(); i++) {
					if (txtTimNSPC.getText().toString().equals(dtmDsNsDaTG.getValueAt(i, 0))) {
						tblDsNsDaTG.changeSelection(i, 0, true, true);
						tblDsNsDaTG.setRowSelectionInterval(i, i);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thông tin chưa được nhập hoặc nhập sai cú pháp");
				txtTimNSPC.requestFocus();
				tblDsNsDaTG.setSelectionMode(0);
			}
		} else if (e.getSource().equals(btnTimNsChuaTG)) {
			if (!txtTimNsChuaTG.getText().trim().isEmpty()) {
				for (int i = 0; i < tblDsNsChuaTG.getRowCount(); i++) {
					if (txtTimNsChuaTG.getText().toString().equals(dtmDsNsChuaTG.getValueAt(i, 0))) {
						tblDsNsChuaTG.changeSelection(i, 0, true, true);
						tblDsNsChuaTG.setRowSelectionInterval(i, i);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thông tin chưa được nhập hoặc nhập sai cú pháp");
				txtTimNsChuaTG.requestFocus();
				tblDsNsChuaTG.setSelectionMode(0);
			}
		} else if (e.getSource().equals(cboHienThiCVPC)) {
			int index = cboHienThiCVPC.getSelectedIndex();
			if (index == 2) {
				dtmDsCvPC.setRowCount(0);
				for (CongViec i : congViec_dao
						.getCongViecDaDay(dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString())) {
					String a[] = { i.getMaCV(), i.getTenCV(), i.getSoNSToiDa() + "", i.getSoNSHT() + "",
							i.getChuyenMonCV() };
					dtmDsCvPC.addRow(a);
				}
			} else if (index == 1) {
				dtmDsCvPC.setRowCount(0);
				for (CongViec i : congViec_dao
						.getCongViecChuaDay(dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString())) {
					String a[] = { i.getMaCV(), i.getTenCV(), i.getSoNSToiDa() + "", i.getSoNSHT() + "",
							i.getChuyenMonCV() };
					dtmDsCvPC.addRow(a);
				}
			} else if (index == 0) {
				dtmDsCvPC.setRowCount(0);
				for (CongViec i : congViec_dao
						.getCongViec(dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString())) {
					String a[] = { i.getMaCV(), i.getTenCV(), i.getSoNSToiDa() + "", i.getSoNSHT() + "",
							i.getChuyenMonCV() };
					dtmDsCvPC.addRow(a);
				}
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		btnPhanCongTD.setEnabled(false);
		btnPhanThuCong.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		cboHienThiCVPC.setSelectedIndex(0);
		if (dtmDsCvPC.getRowCount() > 0) {
			cboHienThiCVPC.setEnabled(true);
		} else {
			cboHienThiCVPC.setEnabled(false);
		}
		String s = dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString();
		dtmDsCvPC.setRowCount(0);
		loadDsCongViec(s);
		dtmDsNsDaTG.setRowCount(0);
		loadNSTheoCT(s);
		txtMaCT.setText(dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		btnPhanCongTD.setEnabled(false);
		btnPhanThuCong.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		cboHienThiCVPC.setSelectedIndex(0);
		if (dtmDsCvPC.getRowCount() > 0) {
			cboHienThiCVPC.setEnabled(true);
		} else {
			cboHienThiCVPC.setEnabled(false);
		}
		String s = dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString();
		dtmDsCvPC.setRowCount(0);
		loadDsCongViec(s);
		dtmDsNsDaTG.setRowCount(0);
		loadNSTheoCT(s);
		txtMaCT.setText(dtmDsDaPC.getValueAt(tblDsDaPC.getSelectedRow(), 0).toString());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
