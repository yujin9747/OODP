package com.example.demo.jframe;

import com.example.demo.domain.Book;
import com.example.demo.domain.Student;
import com.example.demo.repository.BookRepository;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;


public class AdminManagement extends JFrame implements MouseListener,KeyListener,ListSelectionListener{

    static BookRepository bookRepository = new BookRepository();
    private JList list;				//리스트
    private JTextField inputField;	//테스트 입력 Field
    private JButton addBtn;		//추가 버튼
    private JButton delBtn;		//삭제 버튼

    private DefaultListModel model;	//JList에 보이는 실제 데이터
    private JScrollPane scrolled;

    public AdminManagement() {
        setTitle("AdminManagement");
        init();
    }

    public void init() {
        model=new DefaultListModel();
        list=new JList(model);
        inputField=new JTextField(35);
        addBtn=new JButton("추가");
        delBtn=new JButton("삭제");

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록

        inputField.addKeyListener(this);	//엔터 처리
        addBtn.addMouseListener(this);		//아이템 추가
        delBtn.addMouseListener(this);		//아이템 삭제
        list.addListSelectionListener(this);	//항목 선택시

        this.setLayout(new BorderLayout());


        JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        topPanel.add(inputField);
        topPanel.add(addBtn);
        topPanel.add(delBtn);		//위쪽 패널 [textfield]  [add] [del]
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//상, 좌, 하, 우 공백(Padding)

        scrolled=new JScrollPane(list);
        scrolled.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        this.add(topPanel,"North");
        this.add(scrolled,"Center");	//가운데 list


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);	//창 가운데 위치
        this.setVisible(true);

        List<Book> studentList = bookRepository.getBookList();
        for(int i=0; i<studentList.size(); i++){
            String inputText=studentList.get(i).getTitle();
            if(inputText==null||inputText.length()==0) return;
            model.addElement(inputText);
            inputField.setText("");		//내용 지우기
            inputField.requestFocus();	//다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == addBtn) {
            addItem();
        }
        if(e.getSource() == delBtn) {
            int selected=list.getSelectedIndex();
            removeItem(selected);
        }
    }

    public void removeItem(int index) {
        if(index<0) {
            if(model.size()==0) return;	//아무것도 저장되어 있지 않으면 return
            index=0;	//그 이상이면 가장 상위 list index
        }

        model.remove(index);
    }

    public void addItem() {
        String inputText=inputField.getText();
        if(inputText==null||inputText.length()==0) return;
        model.addElement(inputText);
        inputField.setText("");		//내용 지우기
        inputField.requestFocus();	//다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
        //가장 마지막으로 list 위치 이동
        scrolled.getVerticalScrollBar().setValue(scrolled.getVerticalScrollBar().getMaximum());
    }
    //MouseListener
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    //KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode==KeyEvent.VK_ENTER) {
            addItem();
        }
    }


    //ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {	//이거 없으면 mouse 눌릴때, 뗄때 각각 한번씩 호출되서 총 두번 호출
            System.out.println("selected :"+list.getSelectedValue());
        }
    }

        public static void main(String[] ar) {
        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
        bookRepository.getBookList().add(book);
        AdminManagement frame = new AdminManagement();
        }
}