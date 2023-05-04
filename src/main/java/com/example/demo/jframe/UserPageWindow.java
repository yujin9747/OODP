package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.domain.Book;
import com.example.demo.domain.RentalInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPageWindow extends JFrame {
    private final MemberService memberService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private Button returnBTN;
    private Button renewBTN;
//    private JList bookList;
    private JTable bookTable;
    private DefaultListModel<String> model;
    private List<RentalInfo> rentalInfoList;

    private final RentalInfoService rentalInfoService;

    private Member loginedMember;

    public UserPageWindow (Member loginedMember) {
        this.memberService = BeanUtil.get(MemberService.class);
        this.libraryService = BeanUtil.get(LibraryService.class);
        this.bookService = BeanUtil.get(BookService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
        this.loginedMember = loginedMember;
        setTitle("User Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

//        bookList = new JList<>();
//        model = new DefaultListModel<>();

        returnBTN = new Button("return");
        renewBTN = new Button("renew");
        renewBTN.addActionListener(new renewActionListener());

        JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        topPanel.add(returnBTN);
        topPanel.add(renewBTN);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.add(topPanel,"North");

        String[] columnNames = {"title", "Estimated return date"};

        rentalInfoList = rentalInfoService.findRentalInfosByMemberId(loginedMember.getId());

        Object[][] data = new Object[rentalInfoList.size()][];
        for (int i = 0; i < rentalInfoList.size(); i++) {
            RentalInfo rentalInfo = rentalInfoList.get(i);

            Book book = rentalInfo.getBook();
            String returnDueDate = rentalInfo.getReturnDueDate().toString();

            data[i] = new Object[]{book.getTitle(), returnDueDate};
        }

        bookTable = new JTable(data, columnNames);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나만 선택 될 수 있도록
        JScrollPane scrollPane = new JScrollPane(bookTable);
        c.add(scrollPane);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기
    }

    private class renewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected=bookTable.getSelectedRow();

            RentalInfo rentalInfo = rentalInfoList.get(selected);

            boolean isExtensionAllowed = rentalInfo.isExtensionAllowed();

            if (isExtensionAllowed) {
                //modify due date
                int updatedCount = rentalInfoService.updateRentalInfoDueDate(rentalInfo.getId());
                System.out.println("성공 - 연장");
                setVisible(false);
                new UserPageWindow(loginedMember);
            } else {
                JOptionPane.showMessageDialog(null, "연장이 불가능합니다.");
                System.out.println("실패 - 연장불가");
            }

        }
    }


}
