package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.*;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class UserPageWindow extends JFrame {
    private final MemberService memberService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private Button returnBTN;
    private Button renewBTN;
    private Button backBTN;
//    private JList bookList;
    private JTable bookTable;
    private DefaultListModel<String> model;
    private List<RentalInfo> rentalInfoList;

    private final RentalInfoService rentalInfoService;

    private Member loginedMember;
    private Book searchedBook;
    private Object[][] data;

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
            if(!rentalInfo.isReturned()) {
                Book book = rentalInfo.getBook();
                String returnDueDate = rentalInfo.getReturnDueDate().toString();

                data[i] = new Object[]{book.getTitle(), returnDueDate};
                i += 1;
            }
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
            new MainWindow(loginedMember);
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

                    new MainWindow(loginedMember);
                    setVisible(false);
                }
                else if(loginedMember.getRole() == Role.PROFESSOR){

                }

            }
        }

    }

}
