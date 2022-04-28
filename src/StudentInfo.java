import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentInfo extends JPanel {
	Font f1;
	Font f2;
	
	DefaultTableModel model=null; // 테이블에 들어가는 데이터
	JTable table=null; // 테이블
	
	public StudentInfo() {
		f1 = new Font("SanSerif", Font.BOLD,13);	
		f2 = new Font("SanSerif", Font.BOLD,12);
		
		
		this.setLayout(null);;
		this.setPreferredSize(new Dimension(700,550));		
		JTable jt = new JTable();
		
		JLabel student_id = new JLabel("학번");
		student_id.setBounds(40, 9, 40, 40);
		student_id.setFont(f1);
		this.add(student_id);
		
		JTextField id_tf = new JTextField(15);
		id_tf.setBounds(75,18,150,25);
		this.add(id_tf);
		
		JLabel student_name = new JLabel("성명");
		student_name.setBounds(265, 9, 40, 40);
		student_name.setFont(f1);
		this.add(student_name);
		
		JTextField name_tf = new JTextField(15);
		name_tf.setBounds(300,18,150,25);
		this.add(name_tf);
		
		JLabel student_dept = new JLabel("학과");
		student_dept.setBounds(40, 49, 40, 40);
		student_dept.setFont(f1);
		this.add(student_dept);
		
		JTextField dept_tf = new JTextField(15);
		dept_tf.setBounds(75,58,150,25);
		this.add(dept_tf);
		
		JLabel student_address = new JLabel("주소");
		student_address.setBounds(265, 49, 40, 40);
		student_address.setFont(f1);
		this.add(student_address);
		
		JTextField address_tf = new JTextField(15);
		address_tf.setBounds(300,58,150,25);
		this.add(address_tf);
		
		JButton search_btn = new JButton("검색");
		search_btn.setBounds(510,18,100,64);
		search_btn.setFont(f1);
		search_btn.setForeground(Color.WHITE);
		search_btn.setBackground(new Color(45,45,45));
		search_btn.setFocusable(false);
		this.add(search_btn);
		
		search_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(id_tf.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "학번을 입력해주세요!");
				}else {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver"); // oracle driver
						//Connection
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");
						System.out.println("연결 완료");
						Statement stmt=conn.createStatement();		
						
						ResultSet rs=stmt.executeQuery("SELECT * FROM student_test WHERE id LIKE '"+id_tf.getText()+"%'");
						
						model.setRowCount(0);//목록초기화
						while(rs.next()) {
							String[] row=new String[4];//컬럼의 갯수가 4
							row[0]=rs.getString("id");
							row[1]=rs.getString("name");
							row[2]=rs.getString("dept");
							row[3]=rs.getString("address");
	
							model.addRow(row);
							
							id_tf.setText(rs.getString("id"));
							name_tf.setText(rs.getString("name"));
							dept_tf.setText(rs.getString("dept"));
							address_tf.setText(rs.getString("address"));
						}
						rs.close();
						stmt.close();
						conn.close();
						
						
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		String col_name[] = {"학번","성명","학과","주소"};
		model = new DefaultTableModel(col_name,0);
		table = new JTable(model);
		table.setBounds(40,100,570,250);
		table.setPreferredScrollableViewportSize(new Dimension(550,200));		
		JScrollPane list_scroll = new JScrollPane(table);
		list_scroll.setBounds(40,100,570,250);
		this.add(list_scroll);
		this.table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {			
				 table=(JTable)e.getComponent();//클릭한 테이블 구하기
				 model=(DefaultTableModel)table.getModel(); //테이블의 모델 구하기
				 String no=(String)model.getValueAt(table.getSelectedRow(), 0);//학번
 			     id_tf.setText(no);
 			     String name=(String)model.getValueAt(table.getSelectedRow(), 1);//이름
 			     name_tf.setText(name);
 			     String dept=(String)model.getValueAt(table.getSelectedRow(), 2);//학과
			     dept_tf.setText(dept);
			     String address=(String)model.getValueAt(table.getSelectedRow(), 3);
			     address_tf.setText(address);
			     
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		JButton insert_btn = new JButton("등록");
		insert_btn.setBounds(100,367,100,45);
		insert_btn.setFont(f2);
		insert_btn.setForeground(Color.WHITE);
		insert_btn.setBackground(new Color(45,45,45));
		insert_btn.setFocusable(false);
		this.add(insert_btn);
		
		insert_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(id_tf.getText().equals("")||name_tf.getText().equals("")||dept_tf.getText().equals("")||address_tf.getText().equals("")) {
					System.out.println("필요 정보 미입력");
					JOptionPane.showMessageDialog(null, "항목을 모두 입력해 주세요","알림",JOptionPane.WARNING_MESSAGE);
				}else {
					int resultConfirm;
					resultConfirm = JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "등록 확인", JOptionPane.YES_NO_OPTION);
					if(resultConfirm == 0) {
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
							//Connection
							Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
							System.out.println("연결완료");
							
							Statement stmt=conn.createStatement();
							
							stmt.executeUpdate("insert into student_test values('"+id_tf.getText()+"','"+name_tf.getText()+"','"+dept_tf.getText()+"','"+address_tf.getText()+"')"); //영향을 받은 행이 수가 리턴됨
												
							ResultSet rs=stmt.executeQuery("select*from student_test");
							model.setRowCount(0);
							
							while(rs.next()) {
								String[] row=new String[4];//컬럼의 갯수가 4
								row[0]=rs.getString("id");
								row[1]=rs.getString("name");
								row[2]=rs.getString("dept");
								row[3]=rs.getString("address");
	
								model.addRow(row);
							}
							rs.close();
							stmt.close();
							conn.close();
						}catch(Exception e1){
							e1.printStackTrace();
						}
					}
				}
				
			}
			
		});
		
		JButton list_btn = new JButton("목록");
		list_btn.setBounds(210,367,100,45);
		list_btn.setFont(f2);
		list_btn.setForeground(Color.WHITE);
		list_btn.setBackground(new Color(45,45,45));
		list_btn.setFocusable(false);
		this.add(list_btn);
		
		list_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//oracle jdbc드라이버 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt = conn.createStatement();
					
//					stmt.executeUpdate("insert into student values(1234567,'홍길동','철학과')"); //영향을 받은 행의 수가 리턴됨
//					stmt.executeUpdate("update student set dept='전자공학' where id = 1234567");
//					stmt.executeUpdate("delete from student where id = 1234567");
					
					ResultSet rs = stmt.executeQuery("select * from student_test");					
					model.setRowCount(0);//목록초기화
					while(rs.next()) {						
											
						String[] row=new String[4];//컬럼의 갯수가 4
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						row[3]=rs.getString("address");

						model.addRow(row);
					}
					rs.close();
					stmt.close();
					conn.close();
				}
				catch(Exception e1) {
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		JButton modify_btn = new JButton("수정");
		modify_btn.setBounds(320,367,100,45);
		modify_btn.setFont(f2);
		modify_btn.setForeground(Color.WHITE);
		modify_btn.setBackground(new Color(45,45,45));
		modify_btn.setFocusable(false);
		this.add(modify_btn);
		
		modify_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(id_tf.getText().equals("")||name_tf.getText().equals("")||dept_tf.getText().equals("")||address_tf.getText().equals("")) {
					System.out.println("필요 정보 미입력");
					JOptionPane.showMessageDialog(null, "항목을 모두 입력해 주세요","알림",JOptionPane.WARNING_MESSAGE);
				}else {
					int resultConfirm;
					resultConfirm = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION);
					if(resultConfirm == 0) {
						try {
							//oracle jdbc드라이버 로드
							Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
							//Connection
							Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
							System.out.println("연결완료");
							
							Statement stmt = conn.createStatement();					
		
							stmt.executeUpdate("update student_test set name='"+name_tf.getText()+"',dept='"+dept_tf.getText()+"', address='"+address_tf.getText()+"' where id='"+id_tf.getText()+"'");
		
							
							ResultSet rs = stmt.executeQuery("select * from student_test");					
							
							model.setRowCount(0);//목록초기화
							while(rs.next()) {
								String[] row=new String[4];//컬럼의 갯수가 4
								row[0]=rs.getString("id");
								row[1]=rs.getString("name");
								row[2]=rs.getString("dept");
								row[3]=rs.getString("address");
								
								model.addRow(row);
								
							}
							rs.close();
							stmt.close();
							conn.close();
						}
						catch(Exception e1) {
							e1.printStackTrace();
							
						}
					}
				}
			}
			
		});
		
		JButton delete_btn = new JButton("삭제");
		delete_btn.setBounds(430,367,100,45);
		delete_btn.setFont(f2);
		delete_btn.setForeground(Color.WHITE);
		delete_btn.setBackground(new Color(45,45,45));
		delete_btn.setFocusable(false);
		this.add(delete_btn);
		
		delete_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					try {
						//oracle jdbc드라이버 로드
						Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
						//Connection
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
						System.out.println("연결완료");
						
						Statement stmt=conn.createStatement();						
						stmt.executeUpdate("delete from student_test where id='"+id_tf.getText()+"'");
						
						ResultSet rs=stmt.executeQuery("select * from student_test");						
						model.setRowCount(0);//목록초기화
						while(rs.next()) {
							String[] row=new String[4];//컬럼의 갯수가 4
							row[0]=rs.getString("id");
							row[1]=rs.getString("name");
							row[2]=rs.getString("dept");
							row[3]=rs.getString("address");
							
							model.addRow(row);
						}
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e1) {
						e1.printStackTrace();
					   }finally {}
				}
				
			}
			
		});
		
		this.setSize(680,550);
		this.setVisible(true);
	}
}
