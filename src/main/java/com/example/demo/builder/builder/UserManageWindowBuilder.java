package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.actionListener.UserManageActionListener;
import com.example.demo.builder.concreteUserManageBuilder.UserManageWindowPermitBuilder;
import com.example.demo.builder.director.UserManageWindowDirector;
import com.example.demo.domain.Member;
import com.example.demo.domain.Student;
import com.example.demo.jframe.UserManageWindow;
import com.example.demo.service.MemberService;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class UserManageWindowBuilder {

    protected UserManageWindow userManageWindow;

    public UserManageWindow getUserManageWindow() {
        return userManageWindow;
    }

    public void createNewSearchWindowProduct(){
        userManageWindow = new UserManageWindow();
    }

    public void buildLoginedMember(Member loginedMember){
        userManageWindow.setLoginedMember(loginedMember);
    }

    public void buildSelectedIdx(Integer selectedIdx){
        userManageWindow.setSelectedIdx(selectedIdx);
    }

    public void buildListModel(DefaultListModel model) {
        if(model != null) userManageWindow.setModel(model);
        else userManageWindow.setModel(new DefaultListModel());


        class ColoredListCellRenderer extends DefaultListCellRenderer{
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // 기본 스타일을 사용하기 위해 상위 클래스의 구현을 호출
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // 특정 인덱스에 따라 색상을 설정
                if (userManageWindow.getSelectedIdx() != null && index == userManageWindow.getSelectedIdx()) { // 인덱스 2에 색상을 적용
                    renderer.setBackground(Color.BLUE);
                    renderer.setForeground(Color.WHITE);
                } else {
                    renderer.setBackground(list.getBackground());
                    renderer.setForeground(list.getForeground());
                }

                return renderer;
            }
        }

        userManageWindow.setList(new JList<>(userManageWindow.getModel()));
        JList list = userManageWindow.getList();
        list.setCellRenderer(new ColoredListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        list.addListSelectionListener(e -> {
            String selectedUserName = list.getSelectedValue().toString();
            Student selectedMember1 = BeanUtil.get(MemberService.class).findByStudentId(selectedUserName).get(0);
            userManageWindow.setSelectedMember(selectedMember1);

            UserManageWindowPermitBuilder builder = new UserManageWindowPermitBuilder();
            UserManageWindowDirector director = new UserManageWindowDirector(userManageWindow.getLoginedMember(), list.getSelectedIndex(), selectedMember1);
            director.setDescription(userManageWindow.getDescription());
            director.setBuilder(builder);
            director.setModel(userManageWindow.getModel());
            director.constructUserManageWindow();
            userManageWindow.setVisible(false);
        });

        buildScrolledList();
    }

    private void buildScrolledList(){
        userManageWindow.setScrolled(new JScrollPane(userManageWindow.getList()));
        userManageWindow.getScrolled().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        userManageWindow.getScrolled().setSize(600, 500);
    }

    public void buildLayoutSetting(){
        userManageWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        userManageWindow.setLayout(new BorderLayout());
        userManageWindow.setContainer(userManageWindow.getContentPane());
        userManageWindow.getContainer().setLayout(new BorderLayout());
    }

    public void buildDefaultButton(){
        userManageWindow.setNorthPanel(new JPanel());
        userManageWindow.getNorthPanel().setLayout(new GridLayout(2, 1));

        userManageWindow.setButtonPanel(new JPanel());
        userManageWindow.getButtonPanel().setLayout(new FlowLayout());

        buildButtons();
    }

    private void buildButtons(){
        userManageWindow.setBackBTN(new Button("<"));
        userManageWindow.setFindStudentsBTN(new Button("학생 조회"));
        userManageWindow.setFindAdminsBTN(new Button("관리자 조회"));
        userManageWindow.setFindOverduedStudentsBTN(new Button("연기된 학생 조회"));
        userManageWindow.setFindPermittedStudentsBTN(new Button("외부 도서관 허가 학생 조회"));
        userManageWindow.setFindNotPermittedStudentsBTN(new Button("외부 도서관 이용 불가 학생 조회"));

        setButtonBounds();
    }

    private void setButtonBounds(){
        userManageWindow.getBackBTN().setBounds(20, 5, 70, 30);
        userManageWindow.getFindStudentsBTN().setBounds(20, 5, 70, 30);
        userManageWindow.getFindAdminsBTN().setBounds(20, 5, 70, 30);
        userManageWindow.getFindOverduedStudentsBTN().setBounds(20, 5, 70, 30);
        userManageWindow.getFindPermittedStudentsBTN().setBounds(20, 5, 70, 30);
        userManageWindow.getFindNotPermittedStudentsBTN().setBounds(20, 5, 70, 30);

        setActionListeners();
    }

    private void setActionListeners(){
        userManageWindow.getBackBTN().addActionListener(new UserManageActionListener(userManageWindow));
        userManageWindow.getFindStudentsBTN().addActionListener(new UserManageActionListener(userManageWindow));
        userManageWindow.getFindAdminsBTN().addActionListener(new UserManageActionListener(userManageWindow));
        userManageWindow.getFindOverduedStudentsBTN().addActionListener(new UserManageActionListener(userManageWindow));
        userManageWindow.getFindPermittedStudentsBTN().addActionListener(new UserManageActionListener(userManageWindow));
        userManageWindow.getFindNotPermittedStudentsBTN().addActionListener(new UserManageActionListener(userManageWindow));

        addButtonsIntoButtonPanel(userManageWindow.getButtonPanel());
    }

    private void addButtonsIntoButtonPanel(JPanel buttonPanel){
        buttonPanel.add(userManageWindow.getBackBTN());
        buttonPanel.add(userManageWindow.getFindStudentsBTN());
        buttonPanel.add(userManageWindow.getFindAdminsBTN());
        buttonPanel.add(userManageWindow.getFindOverduedStudentsBTN());
        buttonPanel.add(userManageWindow.getFindPermittedStudentsBTN());
        buttonPanel.add(userManageWindow.getFindNotPermittedStudentsBTN());
    }

    public void buildNorthPanelWithDesc(String description){
        JLabel desc = new JLabel(description);
        JPanel northPanel = userManageWindow.getNorthPanel();
        northPanel.add(userManageWindow.getButtonPanel());
        northPanel.add(desc);

        userManageWindow.add(northPanel, "North");
        userManageWindow.add(userManageWindow.getScrolled(), "Center");
    }

    public void buildFinished(){
        userManageWindow.setSize(1000, 600); //창 사이즈
        userManageWindow.setVisible(true); //보이기
    }

    public abstract void buildPermitOrBanButton(String description);

    public void buildDescription(String description) {
        userManageWindow.setDescription(description);
    }

    public void buildSelectedMember(Member selectedMember) {
        userManageWindow.setSelectedMember(selectedMember);
    }
}
