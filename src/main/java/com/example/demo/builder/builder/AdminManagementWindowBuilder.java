package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.actionListener.AdminSearchActionListener;
import com.example.demo.actionListener.BackActionListener;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementSelectedBookBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.command.BackCommand;
import com.example.demo.command.ButtonWithCommand;
import com.example.demo.command.Command;
import com.example.demo.command.InitCommand;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class AdminManagementWindowBuilder {

    protected AdminManagement adminManagementWindow;

    public AdminManagement getAdminManagementWindow() {
        return adminManagementWindow;
    }

    public void createNewSearchWindowProduct(){
        adminManagementWindow = new AdminManagement();
    }

    public void buildDependencyInjection() {
        adminManagementWindow.setMemberService(BeanUtil.get(MemberService.class));
        adminManagementWindow.setBookService(BeanUtil.get(BookService.class));
        adminManagementWindow.setLibraryService(BeanUtil.get(LibraryService.class));
    }

    public void buildLoginedMember(Member loginedMember){
        adminManagementWindow.setLoginedMember(loginedMember);
    }

    public void buildSelectedBook(Book selectedBook){
        adminManagementWindow.setSelectedBook(selectedBook);
    }

    public void buildSelectedIdx(Integer selectedIdx){
        adminManagementWindow.setSelectedIdx(selectedIdx);
    }

    public void buildWindowTitle(){
        adminManagementWindow.setTitle("AdminManagement");
    }

    public void buildJList(){
        adminManagementWindow.setModel(new DefaultListModel());
        adminManagementWindow.setList(new JList(adminManagementWindow.getModel()));


        JList list = adminManagementWindow.getList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록
        list.setCellRenderer(new ColoredListCellRenderer());

        list.addListSelectionListener(e -> {
            String selectedTitle = list.getSelectedValue().toString();
            adminManagementWindow.setSelectedBook(adminManagementWindow.getBookService().findBookByTitle(selectedTitle).get());

            AdminManagementSelectedBookBuilder builder = new AdminManagementSelectedBookBuilder();
            AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, adminManagementWindow.getLoginedMember(), adminManagementWindow.getSelectedBook(), list.getSelectedIndex());
            adminManagementWindow.setVisible(false);
            director.constructAdminManagementWindow();
        });

        adminManagementWindow.setScrolled(new JScrollPane(list));
        adminManagementWindow.getScrolled().setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
    }

    public void buildInputField(){
        adminManagementWindow.setTitleInputField(new JTextField(20));
        adminManagementWindow.setIsbnInputField(new JTextField(20));
        adminManagementWindow.setPositionInputField(new JTextField(20));
        adminManagementWindow.setPublisherInputField(new JTextField(20));
    }

    public void buildButton(){
        adminManagementWindow.setAddBtn(new JButton("추가"));
        adminManagementWindow.setDelBtn(new JButton("삭제"));
        adminManagementWindow.setUserManageBTN(new Button("학생 정보 관리"));
        adminManagementWindow.setBookManageBTN(new Button("책 정보 관리"));
        adminManagementWindow.setBackBTN(new Button("<")); //뒤로가기 버튼
        ButtonWithCommand buttonWithCommand = new ButtonWithCommand(new InitCommand());
        Command backCommand = new BackCommand(adminManagementWindow.getBeforePage(), adminManagementWindow.getLoginedMember(), adminManagementWindow);
        buttonWithCommand.setCommand(backCommand);
        adminManagementWindow.getBackBTN().addActionListener(new AdminSearchActionListener(buttonWithCommand));
    }

    public void buildListener(){
        adminManagementWindow.getTitleInputField().addKeyListener(adminManagementWindow);
        adminManagementWindow.getIsbnInputField().addKeyListener(adminManagementWindow);
        adminManagementWindow.getPositionInputField().addKeyListener(adminManagementWindow);
        adminManagementWindow.getPublisherInputField().addKeyListener(adminManagementWindow);
        adminManagementWindow.getAddBtn().addMouseListener(adminManagementWindow);		//아이템 추가
        adminManagementWindow.getDelBtn().addMouseListener(adminManagementWindow);		//아이템 삭제
        adminManagementWindow.getUserManageBTN().addMouseListener(adminManagementWindow);
        adminManagementWindow.getBookManageBTN().addMouseListener(adminManagementWindow);
    }

    public void buildLayout(){
        adminManagementWindow.setLayout(new BorderLayout());
        buildTopPanel();
        buildInputPanel();
    }

    private void buildTopPanel(){
        adminManagementWindow.setTopPanel(new JPanel(new FlowLayout(10,10,FlowLayout.LEFT)));
        JPanel topPanel = adminManagementWindow.getTopPanel();
        topPanel.add(adminManagementWindow.getBackBTN());
        topPanel.add(adminManagementWindow.getTitleInputField());
        topPanel.add(adminManagementWindow.getAddBtn());
        topPanel.add(adminManagementWindow.getDelBtn());		//위쪽 패널 [textfield]  [add] [del]
        topPanel.add(adminManagementWindow.getUserManageBTN());
        topPanel.add(adminManagementWindow.getBookManageBTN());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//상, 좌, 하, 우 공백(Padding)
    }

    private void buildInputPanel(){
        adminManagementWindow.setInputPanel(new JPanel(new GridLayout(9, 2)));
        JPanel inputPanel = adminManagementWindow.getInputPanel();
        inputPanel.add(new JLabel("Title : "));
        inputPanel.add(adminManagementWindow.getTitleInputField());
        inputPanel.add(new JLabel("ISBN: "));
        inputPanel.add(adminManagementWindow.getIsbnInputField());		//위쪽 패널 [textfield]  [add] [del]
        inputPanel.add(new JLabel("Position : "));
        inputPanel.add(adminManagementWindow.getPositionInputField());
        inputPanel.add(new JLabel("Publisher : "));
        inputPanel.add(adminManagementWindow.getPublisherInputField());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//상, 좌, 하, 우 공백(Padding)
    }

    public void buildDetailPanel(){
        JPanel inputPanel = adminManagementWindow.getInputPanel();
        inputPanel.add(new JLabel("< 책 상세정보 >"));
        inputPanel.add(new JLabel(" "));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    class ColoredListCellRenderer extends DefaultListCellRenderer{
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // 기본 스타일을 사용하기 위해 상위 클래스의 구현을 호출
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // 특정 인덱스에 따라 색상을 설정
            if (adminManagementWindow.getSelectedIdx() != null && index == adminManagementWindow.getSelectedIdx()) { // 인덱스 2에 색상을 적용
                renderer.setBackground(Color.BLUE);
                renderer.setForeground(Color.WHITE);
            } else {
                renderer.setBackground(list.getBackground());
                renderer.setForeground(list.getForeground());
            }

            return renderer;
        }
    }

    public void buildFinished() {
        adminManagementWindow.add(adminManagementWindow.getTopPanel(),"North");
        adminManagementWindow.add(adminManagementWindow.getInputPanel(), "Center");
        adminManagementWindow.add(adminManagementWindow.getScrolled(),"South");	//가운데 list

        adminManagementWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminManagementWindow.setSize(600,600);
        adminManagementWindow.setLocationRelativeTo(null);	//창 가운데 위치
        adminManagementWindow.setVisible(true);
    }

    public void fillJList(){
        List<Book> bookList = adminManagementWindow.getBookService().findBooks();
        for(int i=0; i<bookList.size(); i++){
            Book book=bookList.get(i);
            adminManagementWindow.getModel().addElement(book.getTitle());
            adminManagementWindow.getTitleInputField().setText("");		//내용 지우기
            adminManagementWindow.getTitleInputField().requestFocus();	//다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
        }
    }

    public void buildLabel(JPanel inputPanel, String labelName){
        inputPanel.add(new JLabel(labelName));
    }
    public abstract void buildDetailInfo();

}
