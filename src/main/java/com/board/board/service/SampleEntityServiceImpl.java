package com.board.board.service;

import com.board.board.entity.SampleEntity;
import com.board.board.repository.SampleEntityRepository;
import com.board.board.dto.SampleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SampleEntityServiceImpl implements SampleEntityService{
    @Autowired
    private SampleEntityRepository sampleEntityRepository;

    @Override
    public Long save(SampleDTO dto) {
        return sampleEntityRepository.save(dtoToEntity(dto)).getId();
    }

    @Override
    public SampleDTO findById(Long id) {
        Optional<SampleEntity> result =sampleEntityRepository.findById(id);

        if(result.isPresent()==false){
            System.out.println("not exist");
            return null;
        }

        SampleDTO dto = entityToDTO(result.get());
        return dto;
    }
}
