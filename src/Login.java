import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	Font f0;
	Font f1;
	Font f2;
	Font f3;
	Font f4;
	
			
	Login(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f0 = new Font("Bauhaus 93", Font.BOLD,80);
		f1 = new Font("SanSerif", Font.BOLD,13);
		f2 = new Font("SanSerif", Font.PLAIN,13);
		f3 = new Font("SanSerif", Font.BOLD,12);
		f4 = new Font("�޸յձ�������", Font.PLAIN,18);
		
		JFrame jf = new JFrame();
		jf.setTitle("�л�������α׷�");
		jf.getContentPane().setBackground(new Color(225,225,245));
		jf.setResizable(false);			
		
		JLabel lb1 = new JLabel("HAKSA");
		lb1.setBounds(80,115,300,100); // setBounds(x��ǥ, y��ǥ, x_width, y_height)
		lb1.setFont(f0);
		
		JLabel lb1_2 = new JLabel("�л�����ý���");
		lb1_2.setBounds(135, 155, 150, 100);
		lb1_2.setFont(f4);
		
		// �ΰ� �̹��� ����
		Image main_img = Toolkit.getDefaultToolkit().getImage("img/haksamo.png");
		Icon main_icon = new ImageIcon(main_img);
		JLabel lg_img = new JLabel(main_icon);
		lg_img.setBounds(120,0,150,150);
		
		
		JLabel lb2 = new JLabel("���̵�");
		lb2.setBounds(20,300,80,30);
		lb2.setFont(f1);
		
		JTextField tf_1 = new JTextField();
		tf_1.setBounds(100,300,200,30);
		tf_1.setFont(f2);
		
		JLabel lb3 = new JLabel("��й�ȣ");
		lb3.setBounds(20,350,80,30);
		lb3.setFont(f1);
				
		JPasswordField pw_1 = new JPasswordField();
		pw_1.setBounds(100,350,200,30);
		pw_1.setFont(f2);
		
		// �α��� ��ư ����
		JButton logBtn = new JButton("�α���");
		logBtn.setBounds(310,300,80,80);
		logBtn.setFont(f1);
		logBtn.setForeground(Color.white);
		logBtn.setBorderPainted(true);
		logBtn.setBackground(new Color(45,45,45));
		logBtn.setFocusPainted(false);
		logBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logBtn.addActionListener(new ActionListener() {				
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					 //����
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");
					//���ڿ� sql
					String sql = "SELECT * FROM DB_Address WHERE userID='"+tf_1.getText()+"' and password= '"+pw_1.getText()+"'";
					
					//preparedStatement, sql ������ �����Ű�� ����� ���� ��ü ps
					PreparedStatement ps = conn.prepareStatement(sql);
					//ResultSet, select�� ������� �����ϴ� ��ü rs
					ResultSet rs = ps.executeQuery();
					//if ���� ���� rs�� ������� null���� ���� ������ �̵��Ͽ��� ���� �ǹ���
					//�� rs ���� ���� row ���� ������ �� true���� ��ȯ��
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "�α��ο� �����ϼ̽��ϴ�!");
						jf.dispose();
						new Haksa();
					}else {
						JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ�����ּ���!");						
					}
					
					rs.close();
					conn.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			
		});
		
		
		// ���� ���� ��ư
		JButton join = new JButton("���� ����");
		join.setBounds(100,400,200,30);
		join.setFont(f3);
		join.setForeground(Color.WHITE);
		join.setBackground(new Color(45,45,45));
		join.setFocusPainted(false);
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
		
		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountJoin aj = new AccountJoin(); // ���� ȭ�� �ҷ�����
				
				
			}
			
		});
		
		
		// JFrame ��ü���� ���ð� �� JLabel �� JButton ����
		jf.add(lb1);
		jf.add(lb2);
		jf.add(lb3);
		jf.add(lg_img);
		jf.add(lb1_2);
		jf.add(tf_1);
		jf.add(pw_1);
		jf.add(logBtn);
		jf.add(join);
		setTitle("�α��� ȭ�� ����");
		jf.setSize(420,520);
		jf.setLayout(null);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		
		
		
	}
	
	public static void main(String[] args) {
		new Login();

	}

}