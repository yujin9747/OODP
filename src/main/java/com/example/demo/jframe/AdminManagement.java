package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.LibraryService;
import java.util.Optional;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;


public class AdminManagement extends JFrame implements MouseListener,KeyListener,ListSelectionListener{

    private final BookService bookService;
    private final MemberService memberService;
    private final LibraryService libraryService;
    private JList list;				//리스트
    private JTextField titleInputField;
    private JTextField isbnInputField;
    private JTextField positionInputField;
    private JTextField publisherInputField;
    private JButton addBtn;		//추가 버튼
    private JButton delBtn;		//삭제 버튼
    private Button userManageBTN;
    private Button backBTN;

    private DefaultListModel model;	//JList에 보이는 실제 데이터
    private JScrollPane scrolled;
    private Member loginedMember;

    public AdminManagement(Member loginedMember) {
        this.bookService = BeanUtil.get(BookService.class);
        this.memberService = BeanUtil.get(MemberService.class);
        this.libraryService = BeanUtil.get(LibraryService.class);
        this.loginedMember = loginedMember;
        setTitle("AdminManagement");
        init();
    }

    public void init() {
        model=new DefaultListModel();
        list=new JList(model);
        titleInputField =new JTextField(20);
        isbnInputField =new JTextField(20);
        positionInputField =new JTextField(20);
        publisherInputField =new JTextField(20);
        addBtn=new JButton("추가");
        delBtn=new JButton("삭제");
        userManageBTN = new Button("학생 정보 관리");

        backBTN = new Button("<"); //뒤로가기 버튼
        backBTN.addActionListener(new AdminManagement.backActionListener());

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록

        titleInputField.addKeyListener(this);
        isbnInputField.addKeyListener(this);
        positionInputField.addKeyListener(this);
        publisherInputField.addKeyListener(this);
        addBtn.addMouseListener(this);		//아이템 추가
        delBtn.addMouseListener(this);		//아이템 삭제
        userManageBTN.addMouseListener(this);
        list.addListSelectionListener(this);	//항목 선택시

        this.setLayout(new BorderLayout());

        JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        topPanel.add(backBTN);
        topPanel.add(titleInputField);
        topPanel.add(addBtn);
        topPanel.add(delBtn);		//위쪽 패널 [textfield]  [add] [del]
        topPanel.add(userManageBTN);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//상, 좌, 하, 우 공백(Padding)

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel title = new JLabel();
        JLabel isbn = new JLabel();
        JLabel position = new JLabel();
        JLabel publisher = new JLabel();
        title.setText("Title: ");
        isbn.setText("ISBN: ");
        position.setText("Position: ");
        publisher.setText("Publisher: ");
        inputPanel.add(title);
        inputPanel.add(titleInputField);
        inputPanel.add(isbn);
        inputPanel.add(isbnInputField);
        inputPanel.add(position);
        inputPanel.add(positionInputField);
        inputPanel.add(publisher);
        inputPanel.add(publisherInputField);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrolled=new JScrollPane(list);
        scrolled.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        this.add(topPanel,"North");
        this.add(inputPanel, "Center");
        this.add(scrolled,"South");	//가운데 list


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);	//창 가운데 위치
        this.setVisible(true);

        List<Book> bookList = bookService.findBooks();
        for(int i=0; i<bookList.size(); i++){
            String inputText=bookList.get(i).getTitle();
            if(inputText==null||inputText.length()==0) return;
            model.addElement(inputText);
            titleInputField.setText("");		//내용 지우기
            titleInputField.requestFocus();	//다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
        }

    }

    private class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MainWindow(loginedMember);
            setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == addBtn) {
            int idx = addItem();
            if(idx == 0){
                inputFieldSetting(titleInputField);
                inputFieldSetting(isbnInputField);
                inputFieldSetting(positionInputField);
                inputFieldSetting(publisherInputField);
            }
            else{
                switch (idx) {
                    case 1:
                        titleInputField.requestFocus();
                        break;
                    case 2:
                        isbnInputField.requestFocus();
                        break;
                    case 3:
                        positionInputField.requestFocus();
                        break;
                    case 4:
                        publisherInputField.requestFocus();
                }
            }
            //가장 마지막으로 list 위치 이동
            scrolled.getVerticalScrollBar().setValue(scrolled.getVerticalScrollBar().getMaximum());
        }
        if(e.getSource() == delBtn) {
            String title = list.getSelectedValue().toString();
            int index = list.getSelectedIndex();
            removeItem(title, index);
        }
        if(e.getSource() == userManageBTN){
            new UserManageWindow(new DefaultListModel(), "", loginedMember);
            setVisible(false);
        }
    }

    public void removeItem(String title, int index) {
        if(index<0) {
            if(model.size()==0) return;	//아무것도 저장되어 있지 않으면 return
            index=0;	//그 이상이면 가장 상위 list index
        }
        bookService.deleteBook(title);
        model.remove(index);
    }

    public int addItem() {
        int emptyTextFieldIdx = needMoreInformation();
        if(emptyTextFieldIdx > 0) return emptyTextFieldIdx;

        Optional<Library> handongLibrary = libraryService.findOne(1L);
        Book book = new Book.BookBuilder(titleInputField.getText(), Long.parseLong(isbnInputField.getText()), positionInputField.getText(), publisherInputField.getText(), handongLibrary.get())
                .build();

        System.out.println("book title: " + book.getTitle());
        System.out.println("book isbn: " + book.getIsbn());
        System.out.println("book position: " + book.getPosition());
        System.out.println("book publisher: " + book.getPublisher());

        bookService.saveBook(book);
        return emptyTextFieldIdx;
    }

    private void inputFieldSetting(JTextField textField){
        model.addElement(textField);
        textField.setText("");
    }

    private int needMoreInformation(){
        if(titleInputField.getText()==null||titleInputField.getText().length()==0) return 1;
        else if(isbnInputField.getText()==null||isbnInputField.getText().length()==0) return 2;
        else if(positionInputField.getText()==null||positionInputField.getText().length()==0) return 3;
        else if(publisherInputField.getText()==null||publisherInputField.getText().length()==0) return 4;
        else return 0;
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
            System.out.println("selected index : " + list.getSelectedIndex());
        }
    }

        public static void main(String[] ar) {
//        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
//        bookRepository.getBookList().add(book);
//        AdminManagement frame = new AdminManagement();
        }
}
