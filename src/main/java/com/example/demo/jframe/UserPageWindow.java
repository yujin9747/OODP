package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.builder.concreteMainBuilder.MainWindowUserBuilder;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.*;
import com.example.demo.iterator.Iterator;
import com.example.demo.iterator.RentalInfoContainer;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPageWindow extends JFrame {
    private final BookService bookService;
    private Button returnBTN;
    private Button renewBTN;
    private Button backBTN;
    private JTable bookTable;
    private List<RentalInfo> rentalInfoList;

    private final RentalInfoService rentalInfoService;

    private Member loginedMember;
    private Book searchedBook;
    private Object[][] data;

    public UserPageWindow (Member loginedMember) {
        this.bookService = BeanUtil.get(BookService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);

        this.loginedMember = loginedMember;

        setTitle("User Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        backBTN = new Button("<");
        returnBTN = new Button("return");
        renewBTN = new Button("renew");
        backBTN.addActionListener(new backActionListener());
        renewBTN.addActionListener(new renewActionListener());
        returnBTN.addActionListener(new returnActionListener());

        JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        topPanel.add(backBTN);
        topPanel.add(returnBTN);
        topPanel.add(renewBTN);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.add(topPanel,"North");

        String[] columnNames = {"title", "Estimated return date"};

        rentalInfoList = rentalInfoService.findRentalInfosByMemberId(loginedMember.getId());

        //iterator pattern 적용
        RentalInfoContainer rentalInfoContainer = new RentalInfoContainer(rentalInfoList);

        data = new Object[rentalInfoList.size()][];
        int i = 0;
        for (Iterator it = rentalInfoContainer.getIterator(); it.hasNext();) {
            RentalInfo rentalInfo = (RentalInfo) it.next();
            String returnDueDate = rentalInfo.getReturnDueDate().toString();

            data[i] = new Object[]{rentalInfo.getBook().getTitle(), returnDueDate};
            i += 1;
        }
        for (; i < rentalInfoList.size(); i++) {
            data[i] = new Object[]{"", ""};
        }

        bookTable = new JTable(data, columnNames);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록
        JScrollPane scrollPane = new JScrollPane(bookTable);
        c.add(scrollPane);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기
    }
    private class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindowUserBuilder builder = new MainWindowUserBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();
            setVisible(false);
        }
    }
    private class renewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected=bookTable.getSelectedRow();

            RentalInfo rentalInfo = rentalInfoList.get(selected);

            boolean isExtensionAllowed = rentalInfo.isExtensionAllowed();

            if (isExtensionAllowed) {
                //modify due date
                rentalInfoService.updateRentalInfoDueDate(rentalInfo.getId());
                System.out.println("성공 - 연장");
                setVisible(false);
                new UserPageWindow(loginedMember);
            } else {
                JOptionPane.showMessageDialog(null, "연장이 불가능합니다.");
            }

        }
    }

    private class returnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            Object o = data[bookTable.getSelectedRow()][0];
            System.out.println("ReturnBook title  : " + o);
            searchedBook = bookService.findBookByTitle(o.toString()).get();

            if (command.equals("return")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    rentalInfoService.returnBook(loginedMember.getId(), searchedBook.getId());
                    JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");

                    MainWindowUserBuilder builder = new MainWindowUserBuilder();
                    MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
                    director.constructMainWindow();
                    setVisible(false);
                }
            }
        }
    }
}
