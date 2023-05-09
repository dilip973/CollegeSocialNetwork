package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.NewsFeed;



@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {

}
//package com.cg.boot.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.cg.boot.entity.NewsFeed;
//
//public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {
//
//}
