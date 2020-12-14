package com.graphql.header;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphql.entity.Person;

@Repository
public interface HeaderRepository extends JpaRepository<Person, Integer>{

}
