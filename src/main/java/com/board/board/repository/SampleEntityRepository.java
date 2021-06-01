package com.board.board.repository;

import com.board.board.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleEntityRepository extends JpaRepository<SampleEntity,Long> {
}
