import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame{

 JPanel panel;  // �޴��� ȭ���� ��µǴ� �г�
 Haksa(){
  setTitle("�л�����ý���"); 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ ���α׷� ����
  JMenuBar bar=new JMenuBar();  
  
  JMenu m_student=new JMenu("�л�����");//File�޴�
  bar.add(m_student);
  JMenu m_book=new JMenu("��������");//Edit�޴�
  bar.add(m_book);
  
  JMenuItem mi_list=new JMenuItem("�л�����");
  m_student.add(mi_list);
  
  mi_list.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //���������Ʈ ����
    panel.revalidate(); //�ٽ� Ȱ��ȭ
    panel.repaint();    //�ٽ� �׸���
    panel.add(new StudentInfo()); //ȭ�� ����.
    panel.setLayout(null);//���̾ƿ��������
    
   }});
   
  JMenuItem mi_bookRent=new JMenuItem("������");
  m_book.add(mi_bookRent);
  
  mi_bookRent.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //���������Ʈ ����
    panel.revalidate(); //�ٽ� Ȱ��ȭ
    panel.repaint();    //�ٽ� �׸���
    panel.add(new BookRent()); //ȭ�� ����.
    panel.setLayout(null);//���̾ƿ��������
    
   }});
  
  panel=new JPanel();//panel����
  add(panel);//�����ӿ� �г� �߰�
  
  setJMenuBar(bar);
  setSize(670,500);
  setVisible(true);
  setLocationRelativeTo(null);
  setResizable(false);
 }
 public static void main(String[] args) {
  
  new Haksa();
 }

}