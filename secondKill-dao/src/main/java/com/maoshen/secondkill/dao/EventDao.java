package com.maoshen.secondkill.dao;

import org.springframework.stereotype.Repository;

import com.maoshen.secondkill.domain.Event;

@Repository
public interface EventDao {
	public Event selectById(long id);
}
