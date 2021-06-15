package com.board.board.service;

import com.board.board.dto.SampleDTO;
import com.board.board.entity.Board;
import com.board.board.entity.SampleEntity;

import java.util.List;

public interface BoardService {
    
    Long save(SampleDTO dto);
    
    List<Board> getBoards ();
    
    default SampleDTO entityToDTO (SampleEntity entity) {
        SampleDTO dto = SampleDTO.builder()
                                 .id(entity.getId())
                                 .data(entity.getData())
                                 .build();
        return dto;
    }
    
    default SampleEntity dtoToEntity (SampleDTO dto) {
        SampleEntity entity
                = SampleEntity.builder()
                              .id(dto.getId())
                              .data(dto.getData())
                              .build();
        
        return entity;
    }
    
}
