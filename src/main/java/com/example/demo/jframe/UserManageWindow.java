package com.example.demo.jframe;
import com.example.demo.domain.Member;
import com.example.demo.domain.Student;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class UserManageWindow extends JFrame {

    private JList list;				//리스트
    private JScrollPane scrolled;
    private JPanel northPanel;
    private JPanel buttonPanel;
    private Button findStudentsBTN;   // 전체 학생 조회
    private Button findAdminsBTN;   // 전체 관리자 조회
    private Button findOverduedStudentsBTN;   // 전체 관리자 조회
    private Button findPermittedStudentsBTN;
    private Button findNotPermittedStudentsBTN;
    private Button externalPermitBTN;
    private Button backBTN;
    private JList<Student> studentList;
    private Member loginedMember;
    private Integer selectedIdx;
    private Member selectedMember;
    private String description;
    Container container;
    private DefaultListModel model = new DefaultListModel<>();

}
