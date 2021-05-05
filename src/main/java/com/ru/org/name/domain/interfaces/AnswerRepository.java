package com.ru.org.name.domain.interfaces;

import com.ru.org.name.data.database.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {


}
