package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.RentalInfoService;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;


@Getter
@Setter
public class AdminManagement extends JFrame implements MouseListener,KeyListener,ListSelectionListener{
    private JList list;				//리스트
    private JTextField titleInputField;
    private JTextField isbnInputField;
    private JTextField positionInputField;
    private JTextField publisherInputField;
    private JPanel topPanel;
    private JPanel inputPanel;
    private JButton addBtn;		//추가 버튼
    private JButton delBtn;		//삭제 버튼
    private Button userManageBTN;
    private Button bookManageBTN;
    private Button backBTN;

    private DefaultListModel model;	//JList에 보이는 실제 데이터
    private JScrollPane scrolled;
    private Member loginedMember;
    private Book selectedBook;
    private Integer selectedIdx;
    private Integer beforePage;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == addBtn) {
            int idx = addItem();
            if(idx == 0){
                model.addElement(titleInputField.getText());
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
        else if(e.getSource() == delBtn) {
            removeItem(selectedBook.getTitle(), selectedIdx);
            setVisible(false);
            AdminManagementDefaultBuilder builder = new AdminManagementDefaultBuilder();
            AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, loginedMember, null, null);
            director.constructAdminManagementWindow();
            JOptionPane.showMessageDialog(null, selectedBook.getTitle() + "책 삭제가 완료되었습니다.");
        }
        else if(e.getSource() == userManageBTN){
            new UserManageWindow(new DefaultListModel(), "", loginedMember, null, null);
            setVisible(false);
        }
        else if(e.getSource() == bookManageBTN && selectedIdx != null){
            SearchWindowBuilder searchWindowBuilder = new SearchWindowAdminBuilder();
            SearchWindowDirector searchWindowDirector = new SearchWindowDirector(searchWindowBuilder, loginedMember, selectedBook, 1);
            searchWindowDirector.constructSearchWindow();
            setVisible(false);
        }
    }

    public void removeItem(String title, int index) {
        if(index<0) {
            if(model.size()==0) return;	//아무것도 저장되어 있지 않으면 return
            index=0;	//그 이상이면 가장 상위 list index
        }

        BeanUtil.get(BookService.class).deleteBook(title);
        model.remove(index);
    }

    public int addItem() {
        int emptyTextFieldIdx = needMoreInformation();
        if(emptyTextFieldIdx > 0) return emptyTextFieldIdx;

        Optional<Library> handongLibrary = BeanUtil.get(LibraryService.class).findOne(1L);
        Book book = new Book.BookBuilder()
                .title(titleInputField.getText())
                .isbn(Long.parseLong(isbnInputField.getText()))
                .position(positionInputField.getText())
                .publisher(publisherInputField.getText())
                .library(handongLibrary.get())
                .build();

        System.out.println("book title: " + book.getTitle());
        System.out.println("book isbn: " + book.getIsbn());
        System.out.println("book position: " + book.getPosition());
        System.out.println("book publisher: " + book.getPublisher());

        BeanUtil.get(BookService.class).saveBook(book);
        return emptyTextFieldIdx;
    }

    private void inputFieldSetting(JTextField textField){
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
}
