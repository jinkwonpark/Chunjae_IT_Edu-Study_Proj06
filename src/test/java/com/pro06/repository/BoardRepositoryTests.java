package com.pro06.repository;

import com.pro06.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    // insert 기능 테스트
    @Test
    public void testInsert() {
         IntStream.rangeClosed(1,100).forEach(i -> {
             Board board = Board.builder()
                     .title("title..." + i)
                     .content("content..." + i)
                     .writer("user"+ (i % 10))
                     .build();

             Board result = boardRepository.save(board);
             log.info("BNO: " + result.getBno());
         });
    }

    // select 기능 테스트
    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    // update 기능 테스트
    @Test
    public void testUpdate() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.change("update...title 100", "update content 100");

        boardRepository.save(board);
    }

    // delete 기능 테스트
    @Test
    public void testDelete() {
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }

    // Pageable과 Page<E> 타입
    @Test
    public void testPaging() {

        // 1 page order by bno desc
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: "+result.getTotalElements());
        log.info("total pages: "+result.getTotalPages());
        log.info("page number: "+result.getNumber());
        log.info("page size: "+result.getSize());

        List<Board> todoList = result.getContent();

        todoList.forEach(board -> log.info(board));
    }

    // Q도메인을 이용한 쿼리 작성 및 테스트
    @Test
    public void testSearch1() {

        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll() {

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

    }
}