package com.board.board.service;

import com.board.board.entity.SampleEntity;
import com.board.board.web.SampleDTO;

public interface SampleEntityService {

    Long save(SampleDTO dto);

    SampleDTO findById(Long id);

    default SampleDTO entityToDTO(SampleEntity entity){
        SampleDTO dto = SampleDTO.builder()
                                .id(entity.getId())
                                .data(entity.getData())
                                .build();
        return dto;
    }

    default SampleEntity dtoToEntity(SampleDTO dto){
        SampleEntity entity
                = SampleEntity.builder()
                                .id(dto.getId())
                                .data(dto.getData())
                                .build();

        return entity;
    }

}
