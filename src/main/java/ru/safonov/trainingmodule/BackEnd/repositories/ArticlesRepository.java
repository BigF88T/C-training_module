package ru.safonov.trainingmodule.BackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safonov.trainingmodule.BackEnd.models.Article;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {

}
