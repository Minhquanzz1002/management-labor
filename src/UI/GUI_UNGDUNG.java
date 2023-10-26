 package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ThongKe_dao;
import entity.TaiKhoan;
import entity.ThongKe;



@SuppressWarnings("serial")
public class GUI_UNGDUNG extends JFrame implements MouseListener, ActionListener {
	// thống kê
	private static JTextField txtTongDA;
	private static JTextField txtNSKhongTG;
	private static JTextField txtPhongBan;
	private static JTextField txtNSDaTG;
	private static JTextField txtDAHoanThanh;
	private static ThongKe_dao thongKe_dao = new ThongKe_dao();
	// thống kê
	private TaiKhoan tk;
	private JTabbedPane jTabQuanLy;
	private static JTextField txtTongNS;
	
	private JMenu mnHelp;
	private JMenuItem mntmClose, mntmDangXuat, mntmAbout;


	public GUI_UNGDUNG() {
	}
	public GUI_UNGDUNG( TaiKhoan taiKhoan) {
		tk= taiKhoan;
		setSize(1200, 720);
		setTitle("ỨNG DỤNG QUẢN LÝ LAO ĐỘNG.");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		addControl();
	}

	public void addControl() {
		// NOURTH
		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setBackground(new Color(255, 204, 255));
		pnTieuDe.setPreferredSize(new Dimension(0, 90));
		pnTieuDe.setLayout(new BoxLayout(pnTieuDe, BoxLayout.X_AXIS));
		JLabel lblTieuDe = new JLabel("Ứng Dụng Quản Lý Lao Động Công Ty TNHH FiveMan");
		lblTieuDe.setFont(new Font(getName(), Font.BOLD | Font.ITALIC, 25));
		lblTieuDe.setForeground(new Color(0, 117, 253));
		ImageIcon img = new ImageIcon("img\\logo-removebg-preview (1).png");
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		JLabel lblLogo = new JLabel(img);

		pnTieuDe.add(Box.createHorizontalStrut(50));
		pnTieuDe.add(lblLogo);
		pnTieuDe.add(Box.createRigidArea(new Dimension(150, 0)));
		pnTieuDe.add(lblTieuDe);
		getContentPane().add(pnTieuDe, BorderLayout.NORTH);

		// NOURTH
		// WEST
		JPanel pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		JLabel lblTDThongKe = new JLabel("Thống Kê");
		lblTDThongKe.setFont(new Font(getName(), Font.BOLD, 20));
		lblTDThongKe.setForeground(Color.WHITE);
		lblTDThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		pnWest.add(lblTDThongKe, BorderLayout.NORTH);
		pnWest.setBackground(Color.BLUE);
		JPanel pnThongKe = new JPanel();
		pnThongKe.setLayout(new GridLayout(6, 1));
		pnWest.setPreferredSize(new Dimension(200, 0));
		Font ft = new Font("arial", Font.BOLD, 12);

		// tổng số nhân sự
		JPanel pnTongNS = new JPanel();
		pnTongNS.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnTongNS.setBackground(Color.CYAN);
		JLabel lblTongNS = new JLabel("Tổng Số Nhân Sự");
		lblTongNS.setFont(ft);
		lblTongNS.setHorizontalAlignment(SwingConstants.LEFT);
		txtTongNS = new JTextField(12);
		txtTongNS.setEditable(false);
		pnTongNS.add(lblTongNS);
		pnTongNS.add(txtTongNS);

		// Tổng Số Dự Án
		JPanel pnTongDA = new JPanel();
		pnTongDA.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnTongDA.setBackground(Color.CYAN);
		JLabel lblTongDA = new JLabel("Tổng Số Dự Án");
		lblTongDA.setFont(ft);
		lblTongDA.setHorizontalAlignment(SwingConstants.LEFT);
		txtTongDA = new JTextField(12);
		txtTongDA.setEditable(false);
		pnTongDA.add(lblTongDA);
		pnTongDA.add(txtTongDA);

		// NS Chưa Tham Gia Dự Án
		JPanel pnNSKhongTG = new JPanel();
		pnNSKhongTG.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnNSKhongTG.setBackground(Color.CYAN);
		JLabel lblNSKhongTG = new JLabel("NS Chưa Tham Gia Dự Án");
		lblNSKhongTG.setFont(ft);
		lblTongNS.setHorizontalAlignment(SwingConstants.LEFT);
		txtNSKhongTG = new JTextField(12);
		txtNSKhongTG.setEditable(false);
		pnNSKhongTG.add(lblNSKhongTG);
		pnNSKhongTG.add(txtNSKhongTG);

		// Số Phòng Ban
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnPhongBan.setBackground(Color.CYAN);
		JLabel lblPhongBan = new JLabel("Tổng Số Phòng Ban");
		lblPhongBan.setFont(ft);
		lblPhongBan.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhongBan = new JTextField(12);
		txtPhongBan.setEditable(false);
		pnPhongBan.add(lblPhongBan);
		pnPhongBan.add(txtPhongBan);

		// NS Tham Gia Dự Án
		JPanel pnNSDaTG = new JPanel();
		pnNSDaTG.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnNSDaTG.setBackground(Color.CYAN);
		JLabel lblNSDaTG = new JLabel("Tổng Dự Án Đang Thi Công");
		lblNSDaTG.setFont(ft);
		lblNSDaTG.setHorizontalAlignment(SwingConstants.LEFT);
		txtNSDaTG = new JTextField(12);
		txtNSDaTG.setEditable(false);
		pnNSDaTG.add(lblNSDaTG);
		pnNSDaTG.add(txtNSDaTG);

		// Dự Án Đã Hoàn Thành
		JPanel pnDAHoanThanh = new JPanel();
		pnDAHoanThanh.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		pnDAHoanThanh.setBackground(Color.CYAN);
		JLabel lblDAHoanThanh = new JLabel("Dự Án Đã Hoàn Thành");
		lblDAHoanThanh.setFont(ft);
		lblDAHoanThanh.setHorizontalAlignment(SwingConstants.LEFT);
		txtDAHoanThanh = new JTextField(12);
		txtDAHoanThanh.setEditable(false);
		pnDAHoanThanh.add(lblDAHoanThanh);
		pnDAHoanThanh.add(txtDAHoanThanh);

		pnThongKe.add(pnTongNS);
		pnThongKe.add(pnTongDA);
		pnThongKe.add(pnNSKhongTG);
		pnThongKe.add(pnPhongBan);
		pnThongKe.add(pnNSDaTG);
		pnThongKe.add(pnDAHoanThanh);
		pnThongKe.setBackground(Color.cyan);
		pnWest.add(pnThongKe, BorderLayout.CENTER);
		getContentPane().add(pnWest, BorderLayout.WEST);
		// West
		// Center
		
		loadDuLieuThongKe();
		jTabQuanLy = new JTabbedPane();
		jTabQuanLy.addTab("Công Trình", new ImageIcon("img\\icon\\nhansu.png"), new GUI_CongTrinh().tabCongTrinh());
		jTabQuanLy.addTab("Nhân Sự", new ImageIcon("img\\icon\\duan.png"), new GUI_NhanSu().tabNhanSu());
		jTabQuanLy.addTab("Phòng Ban",new ImageIcon("img\\icon\\phongban.png"),new GUI_PhongBan().tabPhongBan());
		jTabQuanLy.addTab("Chuyên Môn",new ImageIcon("img\\icon\\ChuyenMon.jpg"),new GUI_ChuyenMon().tabChuyenMon());
		jTabQuanLy.addTab("Phân Công", new ImageIcon("img\\icon\\phancong.png") ,new GUI_PhanCong().addControl());
		jTabQuanLy.addTab("Chấm Công", new ImageIcon("img\\icon\\chamcong.png"),new GUI_ChamCong().addConTrol());
		jTabQuanLy.addTab("Luân Chuyển",new ImageIcon("img\\icon\\luanchuyen.png"),new GUI_LuanChuyen().tabLuanChuyen());
		jTabQuanLy.addTab("Người Dùng",new ImageIcon("img\\icon\\user.png"),new GUI_NguoiDung(tk).tabNguoiDung());
		jTabQuanLy.setSelectedIndex(-1);
		getContentPane().add(jTabQuanLy, BorderLayout.CENTER);
		
		jTabQuanLy.addMouseListener(this);
		// Center
		
		controlMenu();
		
	}
	
	
	/**
	 * Add giao diện menu
	 */
	public void controlMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic('F');
		menuBar.add(mnNewMenu);
		
		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setIcon(new ImageIcon("img\\icon\\logout.jpg"));
		mnNewMenu.add(mntmDangXuat);
		
		mntmClose = new JMenuItem("Đóng");
		mntmClose.setIcon(new ImageIcon("img\\icon\\close.jpg"));
		mnNewMenu.add(mntmClose);
		
		mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon("img\\icon\\about.jpg"));
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(this);
		mntmDangXuat.addActionListener(this);
		mntmClose.addActionListener(this);
	}
	
	/**
	 * show thông tin app
	 */
	public void showAbout() {
		JPanel pnAbout = new JPanel();
		getContentPane().add(pnAbout, BorderLayout.CENTER);
		pnAbout.setLayout(new BorderLayout(0, 0));
		
		JPanel pnVersion = new JPanel();
		pnAbout.add(pnVersion, BorderLayout.WEST);
		pnVersion.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVersion1 = new JLabel("Phiên bản:    ");
		pnVersion.add(lblVersion1, BorderLayout.NORTH);
		
		JLabel lblAuthor1 = new JLabel("Tác giả:");
		lblAuthor1.setVerticalAlignment(SwingConstants.TOP);
		pnVersion.add(lblAuthor1);
		
		JPanel pnAuthor = new JPanel();
		pnAbout.add(pnAuthor, BorderLayout.CENTER);
		pnAuthor.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVersion2 = new JLabel("v1.0.0");
		pnAuthor.add(lblVersion2, BorderLayout.NORTH);
		
		JLabel lblAuthor2 = new JLabel("<html>\r\n• Nguyễn Minh Qu&acirc;n<br>\r\n• Huỳnh Hữu Phước <br>\r\n• Phạm Thanh Sơn <br>\r\n• Nguyễn Thanh Sơn <br>\r\n• L&ecirc; Thanh To&agrave;n\r\n</html>");
		lblAuthor2.setVerticalAlignment(SwingConstants.TOP);
		pnAuthor.add(lblAuthor2);
		
		JOptionPane.showOptionDialog(this, pnAbout, "About", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Ok"}, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		if (action == mntmAbout) {
			showAbout();
		}
		if (action == mntmDangXuat) {
			setVisible(false);
			dispose();
			new GUI_DangNhap().setVisible(true);
		}
		if (action == mntmClose) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	public static void loadDuLieuThongKe() {
		ThongKe thongKe = thongKe_dao.getThongKe();
		txtTongNS.setText(String.valueOf(thongKe.getTongNhanSu()));
		txtTongDA.setText(String.valueOf(thongKe.getTongDuAn()));
		txtNSKhongTG.setText(String.valueOf(thongKe.getNsChuaThamGiaDA()));
		txtPhongBan.setText(String.valueOf(thongKe.getTongPhongBan()));
		txtNSDaTG.setText(String.valueOf(thongKe.getTongDAThiCong()));
		txtDAHoanThanh.setText(String.valueOf(thongKe.getTongDAHoanThanh()));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = jTabQuanLy.getSelectedIndex();
		jTabQuanLy.removeAll();
		loadDuLieuThongKe();
		jTabQuanLy.addTab("Công Trình", new ImageIcon("img\\icon\\nhansu.png"), new GUI_CongTrinh().tabCongTrinh());
		jTabQuanLy.addTab("Nhân Sự", new ImageIcon("img\\icon\\duan.png"), new GUI_NhanSu().tabNhanSu());
		jTabQuanLy.addTab("Phòng Ban",new ImageIcon("img\\icon\\phongban.png"),new GUI_PhongBan().tabPhongBan());
		jTabQuanLy.addTab("Chuyên Môn",new ImageIcon("img\\icon\\ChuyenMon.jpg"),new GUI_ChuyenMon().tabChuyenMon());
		jTabQuanLy.addTab("Phân Công", new ImageIcon("img\\icon\\phancong.png") ,new GUI_PhanCong().addControl());
		jTabQuanLy.addTab("Chấm Công", new ImageIcon("img\\icon\\chamcong.png"),new GUI_ChamCong().addConTrol());
		jTabQuanLy.addTab("Luân Chuyển",new ImageIcon("img\\icon\\luanchuyen.png"),new GUI_LuanChuyen().tabLuanChuyen());
		jTabQuanLy.addTab("Người Dùng",new ImageIcon("img\\icon\\user.png"), new GUI_NguoiDung(tk).tabNguoiDung());		
		jTabQuanLy.setSelectedIndex(index);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
