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

 JPanel panel;  // 메뉴별 화면이 출력되는 패널
 Haksa(){
  setTitle("학사관리시스템"); 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 프로그램 종료
  JMenuBar bar=new JMenuBar();  
  
  JMenu m_student=new JMenu("학생관리");//File메뉴
  bar.add(m_student);
  JMenu m_book=new JMenu("도서관리");//Edit메뉴
  bar.add(m_book);
  
  JMenuItem mi_list=new JMenuItem("학생정보");
  m_student.add(mi_list);
  
  mi_list.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //모든컴포넌트 삭제
    panel.revalidate(); //다시 활성화
    panel.repaint();    //다시 그리기
    panel.add(new StudentInfo()); //화면 생성.
    panel.setLayout(null);//레이아웃적용안함
    
   }});
   
  JMenuItem mi_bookRent=new JMenuItem("대출목록");
  m_book.add(mi_bookRent);
  
  mi_bookRent.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //모든컴포넌트 삭제
    panel.revalidate(); //다시 활성화
    panel.repaint();    //다시 그리기
    panel.add(new BookRent()); //화면 생성.
    panel.setLayout(null);//레이아웃적용안함
    
   }});
  
  panel=new JPanel();//panel생성
  add(panel);//프레임에 패널 추가
  
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