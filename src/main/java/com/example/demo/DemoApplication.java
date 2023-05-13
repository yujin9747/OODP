package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.jframe.MainWindow;
import com.example.demo.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication {
    
    private MemberService memberService;
    private BookService bookService;
    private LibraryService libraryService;
//    private RentalInfoService rentalInfoService;
//    private ReservationInfoService reservationInfoService;

    private final ApplicationContext context;	// 2. 실제 applicationContext 를 주입받는다.


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.setProperty("java.awt.headless", "false"); //Disables headless

        SwingUtilities.invokeLater(() -> {
            new MainWindow(null);
        });
    }

    @PostConstruct
    public void init(){
        BeanUtil.init(context); // 3. 객체의 생명주기를 이용해 스태틱 참조변수에 주입한다.

// 붙여넣기 시작
        // default value 넣어 놓는 코드
//        this.libraryService = BeanUtil.get(LibraryService.class);
//        Library handongLibrary = new Library("HGU Library");
//        libraryService.saveLibrary(handongLibrary);
//
//        this.bookService = BeanUtil.get(BookService.class);
//        Book book1 = new Book("Introduction to Metaverse", 135929384L, "510.32 지 474", "교보문고", handongLibrary);
//        bookService.saveBook(book1);
//        Book book2 = new Book("객체지향 설계패턴", 23412534L, "530.32 지 474", "글송이", handongLibrary);
//        bookService.saveBook(book2);
//        Book book3 = new Book("기독교 세계관", 2342343L, "510.32 가 474", "넥서스", handongLibrary);
//        bookService.saveBook(book3);
//        Book book4 = new Book("Operating System", 3453423L, "123.32 지 474", "나무생각", handongLibrary);
//        bookService.saveBook(book4);
//        Book book5 = new Book("JPA 정복하기", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book5);
//
//        this.memberService = BeanUtil.get(MemberService.class);
//        Member member1 = new Student("yujin", Role.STUDENT, "slsddbwls4421", handongLibrary,"22000630");
//        memberService.saveMember(member1);
//        Member admin = new Admin("adminName", Role.ADMIN, "admin1234", handongLibrary, "1130403", 19);
//        memberService.saveMember(admin);






        // default value 넣어 놓는 코드
//        this.libraryService = BeanUtil.get(LibraryService.class);
////        Library handongLibrary = new Library("HGU Library");
//        Library handongLibrary = libraryService.findOne(1L).get();
//
//        this.bookService = BeanUtil.get(BookService.class);
//        Book book1 = new Book("A", 135929384L, "510.32 지 474", "교보문고", handongLibrary);
////        Book book1 = bookService.findOne(1L).get();
//        Book book2 = new Book("B", 23412534L, "530.32 지 474", "글송이", handongLibrary);
//        bookService.saveBook(book2);
//        Book book3 = new Book("C", 2342343L, "510.32 가 474", "넥서스", handongLibrary);
//        bookService.saveBook(book3);
//        Book book4 = new Book("D", 3453423L, "123.32 지 474", "나무생각", handongLibrary);
//        bookService.saveBook(book4);
//        Book book5 = new Book("E", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book5);
//        Book book6 = new Book("F", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book6);
//        Book book7 = new Book("G", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book7);
//        Book book8 = new Book("H", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book8);
//        Book book9 = new Book("I", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book9);
//        Book book10 = new Book("J", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book10);
//        Book book11 = new Book("K", 2343553L, "453.32 바 474", "나남출판", handongLibrary);
//        bookService.saveBook(book11);

//        this.memberService = BeanUtil.get(MemberService.class);
//        Member member1 = new Student("yujin", Role.STUDENT, "slsddbwls4421", handongLibrary,"22000630");
//        memberService.saveMember(member1);

//        Member overdueMember = new Student("overdueMember", Role.STUDENT, "123", handongLibrary,"22000111");
//        memberService.saveMember(overdueMember);
//
//        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
//        rentalInfoService.saveRentalInfo(overdueMember.getId(), book1.getId());

//        Member admin = new Admin("adminName", Role.ADMIN, "admin1234", handongLibrary, "1130403", 19);
//        memberService.saveMember(admin);
    }

}
