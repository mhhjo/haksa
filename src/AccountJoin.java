import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AccountJoin extends JFrame {
	
	Font f1;
	Font f3;
	
	AccountJoin(){
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	f1 = new Font("SanSerif", Font.BOLD,13);	
	f3 = new Font("SanSerif", Font.BOLD,12);
	
	JDialog jf2 = new JDialog();
	
	JLabel lb_id = new JLabel("���̵�");
	lb_id.setBounds(35,20,100,20);
	lb_id.setFont(f1);
		
	JTextField id_tf = new JTextField();
	id_tf.setBounds(35,45,250,30);
	id_tf.setFont(f1);
	
	JLabel lb_pw = new JLabel("�н�����");
	lb_pw.setBounds(35,80,100,20);
	lb_pw.setFont(f1);
		
	JPasswordField pw_pf = new JPasswordField();
	pw_pf.setBounds(35,105,250,30);
	pw_pf.setFont(f1);
	
	JLabel lb_name = new JLabel("����");
	lb_name.setBounds(35,140,100,20);
	lb_name.setFont(f1);
	
	JTextField name_tf = new JTextField();
	name_tf.setBounds(35,165,250,30);
	name_tf.setFont(f1);
	
	JLabel lb_mail = new JLabel("E-mail");
	lb_mail.setBounds(35,200,100,20);
	lb_mail.setFont(f1);
	
	JTextField mail_tf = new JTextField();
	mail_tf.setBounds(35,225,250,30);
	mail_tf.setFont(f1);
	
	JLabel lb_text = new JLabel("");
	lb_text.setBounds(30,278,260,25);
	TitledBorder b1 = new TitledBorder(new LineBorder(new Color(155,155,155)));
	lb_text.setBorder(b1);
	
	JButton confirm_btn = new JButton("Ȯ��");
	confirm_btn.setFont(f3);
	confirm_btn.setBounds(20,320,130,30);
	confirm_btn.setForeground(Color.WHITE);
	confirm_btn.setBackground(new Color(45,45,45));
	confirm_btn.setFocusPainted(false);
	
	confirm_btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(id_tf.getText().equals("") || pw_pf.getText().equals("") || name_tf.getText().equals("") || mail_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ȸ�� ������ ��� �Է����ּ���!");
				}else{
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");
					
					String sql = "SELECT * FROM DB_Address WHERE userID='"+id_tf.getText()+"'";
					
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs_account = ps.executeQuery();
				
					if(rs_account.next()) {
						JOptionPane.showMessageDialog(null, "���̵� �ߺ��˴ϴ�!");
					}else {
						int resultConfirm;
						resultConfirm = JOptionPane.showConfirmDialog(null, "ȸ������ �Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
						if(resultConfirm == 0) {
							JOptionPane.showMessageDialog(null, name_tf.getText()+"���� ȸ�����Կ� �����Ͽ����ϴ�!");
							
							Statement stmt=conn.createStatement();					
							stmt.executeUpdate("insert into DB_Address values('"+id_tf.getText()+"','"+pw_pf.getText()+"','"+name_tf.getText()+"','"+mail_tf.getText()+"')");
							jf2.dispose();
							stmt.close();
						}
					}
					rs_account.close();
					conn.close();
					
				}
				
				
			}catch(Exception e1){
				e1.printStackTrace();
			}
			
		}
		
	});
	
	JButton cancel_btn= new JButton("���");
	cancel_btn.setFont(f3);
	cancel_btn.setBounds(170,320,130,30);
	cancel_btn.setForeground(Color.WHITE);
	cancel_btn.setBackground(new Color(45,45,45));
	cancel_btn.setFocusable(false);
	
	cancel_btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			jf2.setVisible(false);
			
		}
		
	});
	
	jf2.add(confirm_btn);
	jf2.add(cancel_btn);
	jf2.add(lb_id);
	jf2.add(id_tf);
	jf2.add(lb_pw);
	jf2.add(pw_pf);
	jf2.add(lb_name);
	jf2.add(name_tf);
	jf2.add(lb_mail);
	jf2.add(mail_tf);
	jf2.add(lb_text); // ���� �޼��� ��µǴ� ĭ
	
	jf2.getContentPane().setBackground(new Color(235,235,255));
	jf2.setTitle("���� ����");
	jf2.setSize(340,420);
	jf2.setLayout(null);	
	jf2.setLocationRelativeTo(null);
	jf2.setResizable(false);
	jf2.setModal(true); // Modal�� setVisible�� �������̿� ���� ����� �ٸ���??
	jf2.setVisible(true);
	
	
	
	
	
	}
}
