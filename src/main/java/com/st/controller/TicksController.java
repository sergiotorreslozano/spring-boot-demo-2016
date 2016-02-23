package com.st.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.st.domain.Ticks;
import com.st.repository.TicksRepository;

@RestController
public class TicksController {

	@Autowired
	private TicksRepository ticksRepository;

	@RequestMapping(value = "/api/ticks", method = RequestMethod.GET)
	public List<Ticks> findAllTicks() {
		return ticksRepository.findAll();
	}

	@RequestMapping(value = "/api/ticks", method = RequestMethod.POST)
	public Ticks addTick() {
		Ticks tick = new Ticks();
		tick.setTimeStamp(new Date());
		return ticksRepository.save(tick);
	}

}
