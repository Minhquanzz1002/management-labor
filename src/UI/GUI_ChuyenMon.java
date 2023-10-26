package UI;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenMon_dao;
import entity.ChuyenMon;

public class GUI_ChuyenMon extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChuyenMon_dao daoChuyenMon;
	private DefaultTableModel dtmChuyenMon;
	private static String[] cols = { "M\u00E3 CM", "T\u00EAn CM", "M\u00F4 t\u1EA3" };
	private JPanel pnCha;
	private JTextField txtMaCM, txtTenCM, txtMoTa;
	private JTable tblChuyenMon;
	private JButton btnThem, btnSua, btnXoa;


	/**
	 * Create the frame.
	 */
	public GUI_ChuyenMon() {
		
	}

	public Component tabChuyenMon() {
		daoChuyenMon = new ChuyenMon_dao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 550);
		pnCha = new JPanel();
		pnCha.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnCha);
		pnCha.setBounds(0, 0, 979, 554);
		pnCha.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.CYAN);
		pnTitle.setBounds(0, 0, 981, 35);
		pnCha.add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Chuyên Môn");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(0, 0, 981, 35);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		pnTitle.add(lblTitle);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setLayout(null);
		pnThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin chuyên môn"));
		pnThongTin.setBackground(new Color(238, 232, 170));
		pnThongTin.setBounds(0, 34, 981, 108);
		pnCha.add(pnThongTin);

		JLabel lblMaCM = new JLabel("Mã chuyên môn: ");
		lblMaCM.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaCM.setBounds(10, 19, 122, 28);
		pnThongTin.add(lblMaCM);

		txtMaCM = new JTextField();
		txtMaCM.setColumns(10);
		txtMaCM.setBounds(133, 20, 340, 23);
		pnThongTin.add(txtMaCM);

		JLabel lblTenCM = new JLabel("Tên chuyên môn: ");
		lblTenCM.setFont(new Font("Arial", Font.BOLD, 13));
		lblTenCM.setBounds(483, 19, 122, 28);
		pnThongTin.add(lblTenCM);

		txtTenCM = new JTextField();
		txtTenCM.setColumns(10);
		txtTenCM.setBounds(615, 22, 340, 23);
		pnThongTin.add(txtTenCM);

		JLabel lblMoTa = new JLabel("Mô tả: ");
		lblMoTa.setFont(new Font("Arial", Font.BOLD, 13));
		lblMoTa.setBounds(10, 66, 122, 23);
		pnThongTin.add(lblMoTa);

		txtMoTa = new JTextField();
		txtMoTa.setHorizontalAlignment(SwingConstants.CENTER);
		txtMoTa.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(133, 63, 822, 23);
		pnThongTin.add(txtMoTa);

		JPanel pnButton = new JPanel();
		pnButton.setBounds(0, 144, 981, 54);
		pnCha.add(pnButton);
		pnButton.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chọn tác vụ"));
		pnButton.setLayout(null);
		pnButton.setBackground(SystemColor.info);

		btnThem = new JButton("Thêm");
		btnThem.setMnemonic(KeyEvent.VK_T);
		btnThem.setFont(new Font("Arial", Font.BOLD, 13));
		btnThem.setBounds(173, 14, 90, 32);
		btnThem.setFocusable(false);
		pnButton.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setMnemonic(KeyEvent.VK_S);
		btnSua.setFont(new Font("Arial", Font.BOLD, 13));
		btnSua.setBounds(436, 14, 90, 32);
		btnSua.setFocusable(false);
		pnButton.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setMnemonic(KeyEvent.VK_X);
		btnXoa.setToolTipText("Xóa chuyên môn được chọn");
		btnXoa.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoa.setBounds(699, 14, 90, 32);
		btnXoa.setFocusable(false);
		pnButton.add(btnXoa);

		JPanel pnTable = new JPanel();
		pnTable.setBounds(0, 200, 981, 330);
		pnCha.add(pnTable);
		pnTable.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách chuyên môn"));
		pnTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 961, 297);
		pnTable.add(scrollPane);
		pnTable.setBackground(SystemColor.info);

		tblChuyenMon = new JTable();
		tblChuyenMon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblChuyenMon);
		tblChuyenMon.getTableHeader().setBackground(Color.CYAN);

		loadDataToTable();
		
		txtMaCM.setText("CM0030");
		txtTenCM.setText("Lái xe");

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		tblChuyenMon.addMouseListener(this);
		return pnCha;
	}

	/**
	 * load dữ liệu lên table chuyên môn
	 */
	public void loadDataToTable() {
		dtmChuyenMon = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblChuyenMon.setModel(dtmChuyenMon);
		List<ChuyenMon> dscm = daoChuyenMon.getDanhSachChuyenMon();
		for (ChuyenMon chuyenMon : dscm) {
			dtmChuyenMon.addRow(chuyenMon.toRowTable());
		}
	}

	/**
	 * Kiểm tra tính hợp lệ của dữ liệu
	 * 
	 * @return hợp lệ (true)
	 */
	public boolean valid() {
		String maCM, tenCM;
		maCM = txtMaCM.getText();
		tenCM = txtTenCM.getText();
		if (!maCM.matches("^CM[0-9]{4}")) {
			showMessenger(txtMaCM, "Mã chuyên môn bắt đầu bằng \"CM\" và theo sau là 4 số");
			return false;
		}
		if (tenCM.trim().isEmpty()) {
			showMessenger(txtTenCM, "Tên chuyên môn không được rỗng");
			return false;
		}
		if (!tenCM.matches("^[^0-9]+$")) {
			showMessenger(txtTenCM, "Tên chuyên môn không chứa số");
			return false;
		}
		return true;
	}

	/**
	 * Show các thông báo lỗi và focus vào trường lỗi
	 * 
	 * @param textField trường lỗi
	 * @param mess      thông báo
	 */
	public void showMessenger(JTextField textField, String mess) {
		JOptionPane.showMessageDialog(this, mess);
		textField.selectAll();
		textField.requestFocus();
	}

	public ChuyenMon taoChuyenMonTuTextField() {
		String maCM, tenCM, moTa;
		maCM = txtMaCM.getText();
		tenCM = txtTenCM.getText();
		moTa = txtMoTa.getText();
		return new ChuyenMon(maCM, tenCM, moTa);
	}

	/**
	 * Load dữ liệu từ dòng lên JTextFiled
	 */
	public void loadRowToTextFiled(ChuyenMon chuyenMon) {
		txtMaCM.setText(chuyenMon.getMaCM());
		txtTenCM.setText(chuyenMon.getTenCM());
		txtMoTa.setText(chuyenMon.getMoTa());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		/* thêm */
		if (action == btnThem) {
			if (valid()) {
				ChuyenMon chuyenMon = taoChuyenMonTuTextField();
				if (daoChuyenMon.themChuyenMon(chuyenMon)) {
					dtmChuyenMon.addRow(chuyenMon.toRowTable());
					JOptionPane.showMessageDialog(this, "Thêm chuyên môn thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Thêm chuyên môn không thành công");
				}
			}
		}

		if (action == btnSua) {
			int rowIndex = tblChuyenMon.getSelectedRow();
			if (rowIndex > 0) {
				ChuyenMon chuyenMonMoi = taoChuyenMonTuTextField();
				ChuyenMon chuyenMonCu = daoChuyenMon.getDanhSachChuyenMon().get(rowIndex);
				if (JOptionPane.showConfirmDialog(this,
						"Bạn chắc chắn muốn sửa chuyên môn " + chuyenMonCu.getMaCM() + " ?", "Xác nhận sửa",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (chuyenMonCu.getMaCM().equals(chuyenMonMoi.getMaCM())) {
						if (daoChuyenMon.suaChuyenMon(chuyenMonMoi)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "Sửa chuyên môn thành công");
						} else {
							JOptionPane.showMessageDialog(this, "Sửa chuyên môn không thành công");
						}
					} else {
						JOptionPane.showMessageDialog(this, "Không được sửa mã chuyên môn");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn chuyên môn muốn sửa trước");
			}
		}
		/* Xóa chuyên môn */
		if (action == btnXoa) {
			int rowIndex = tblChuyenMon.getSelectedRow();
			if (rowIndex > 0) {
				ChuyenMon chuyenMon = daoChuyenMon.getDanhSachChuyenMon().get(rowIndex);
				if (JOptionPane.showConfirmDialog(this,
						"Bạn chắc chắn muốn xóa chuyên môn " + chuyenMon.getMaCM() + " ?", "Xác nhận xóa",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (daoChuyenMon.xoaChuyenMon(chuyenMon)) {
						dtmChuyenMon.removeRow(rowIndex);
						JOptionPane.showMessageDialog(this, "Xóa chuyên môn thành công");
					} else {
						JOptionPane.showMessageDialog(this, "Xóa chuyên môn không thành công");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn chuyên môn muốn xóa trước");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int rowIndex = tblChuyenMon.getSelectedRow();
		ChuyenMon chuyenMon = daoChuyenMon.getDanhSachChuyenMon().get(rowIndex);
		loadRowToTextFiled(chuyenMon);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int rowIndex = tblChuyenMon.getSelectedRow();
		ChuyenMon chuyenMon = daoChuyenMon.getDanhSachChuyenMon().get(rowIndex);
		loadRowToTextFiled(chuyenMon);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int rowIndex = tblChuyenMon.getSelectedRow();
		ChuyenMon chuyenMon = daoChuyenMon.getDanhSachChuyenMon().get(rowIndex);
		loadRowToTextFiled(chuyenMon);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
