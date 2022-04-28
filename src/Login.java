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
		f4 = new Font("휴먼둥근헤드라인", Font.PLAIN,18);
		
		JFrame jf = new JFrame();
		jf.setTitle("학사관리프로그램");
		jf.getContentPane().setBackground(new Color(225,225,245));
		jf.setResizable(false);			
		
		JLabel lb1 = new JLabel("HAKSA");
		lb1.setBounds(80,115,300,100); // setBounds(x좌표, y좌표, x_width, y_height)
		lb1.setFont(f0);
		
		JLabel lb1_2 = new JLabel("학사관리시스템");
		lb1_2.setBounds(135, 155, 150, 100);
		lb1_2.setFont(f4);
		
		// 로고 이미지 삽입
		Image main_img = Toolkit.getDefaultToolkit().getImage("img/haksamo.png");
		Icon main_icon = new ImageIcon(main_img);
		JLabel lg_img = new JLabel(main_icon);
		lg_img.setBounds(120,0,150,150);
		
		
		JLabel lb2 = new JLabel("아이디");
		lb2.setBounds(20,300,80,30);
		lb2.setFont(f1);
		
		JTextField tf_1 = new JTextField();
		tf_1.setBounds(100,300,200,30);
		tf_1.setFont(f2);
		
		JLabel lb3 = new JLabel("비밀번호");
		lb3.setBounds(20,350,80,30);
		lb3.setFont(f1);
				
		JPasswordField pw_1 = new JPasswordField();
		pw_1.setBounds(100,350,200,30);
		pw_1.setFont(f2);
		
		// 로그인 버튼 관련
		JButton logBtn = new JButton("로그인");
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
					 //연결
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");
					//문자열 sql
					String sql = "SELECT * FROM DB_Address WHERE userID='"+tf_1.getText()+"' and password= '"+pw_1.getText()+"'";
					
					//preparedStatement, sql 구문을 실행시키는 기능을 가진 객체 ps
					PreparedStatement ps = conn.prepareStatement(sql);
					//ResultSet, select의 결과값을 저장하는 객체 rs
					ResultSet rs = ps.executeQuery();
					//if 문을 통해 rs의 결과값이 null에서 다음 값으로 이동하였을 때를 의미함
					//즉 rs 값의 다음 row 값이 존재할 때 true값을 반환함
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다!");
						jf.dispose();
						new Haksa();
					}else {
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요!");						
					}
					
					rs.close();
					conn.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			
		});
		
		
		// 계정 생성 버튼
		JButton join = new JButton("계정 생성");
		join.setBounds(100,400,200,30);
		join.setFont(f3);
		join.setForeground(Color.WHITE);
		join.setBackground(new Color(45,45,45));
		join.setFocusPainted(false);
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
		
		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountJoin aj = new AccountJoin(); // 가입 화면 불러오기
				
				
			}
			
		});
		
		
		// JFrame 전체적인 세팅과 각 JLabel 및 JButton 삽입
		jf.add(lb1);
		jf.add(lb2);
		jf.add(lb3);
		jf.add(lg_img);
		jf.add(lb1_2);
		jf.add(tf_1);
		jf.add(pw_1);
		jf.add(logBtn);
		jf.add(join);
		setTitle("로그인 화면 예제");
		jf.setSize(420,520);
		jf.setLayout(null);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		
		
		
	}
	
	public static void main(String[] args) {
		new Login();

	}

}