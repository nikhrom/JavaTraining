package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.User;

import javax.persistence.EntityManager;

public class UserRepository extends AbstractRepository<Long, User>{

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }



}
