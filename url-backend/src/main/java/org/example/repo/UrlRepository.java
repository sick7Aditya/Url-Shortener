package org.example.repo;


import org.example.models.UrlModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {

    //    Optional<UrlModel> findBySmallHashCode(String SmallHashCode);
    UrlModel findByUrl(String Url);
    UrlModel findBySmallHashCode(String code);
    boolean existsBySmallHashCode(String code);
    boolean existsByUrl(String url);
}
