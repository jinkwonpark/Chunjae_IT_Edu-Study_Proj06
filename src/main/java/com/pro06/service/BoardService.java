package com.pro06.service;

import com.pro06.dto.BoardDTO;
import com.pro06.dto.PageRequestDTO;
import com.pro06.dto.PageResponseDTO;

public interface BoardService {

    // 등록 작업 처리
    Long register(BoardDTO boardDTO);

    // 조회 작업 처리
    BoardDTO readOne(Long bno);

    // 수정 작업 처리
    void modify(BoardDTO boardDTO);

    // 삭제 작업 처리
    void remove(Long bno);

    // 목록/검색 기능
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
