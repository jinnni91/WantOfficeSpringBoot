package com.project.office.position.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.position.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	Optional<Position> findByPositionNo(Long positionNo);

}
