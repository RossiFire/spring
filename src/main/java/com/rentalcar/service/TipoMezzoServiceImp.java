package com.rentalcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentalcar.dao.TipoMezzoDao;
import com.rentalcar.entities.TipoMezzo;

@Service
@Transactional
public class TipoMezzoServiceImp implements TipoMezzoService{

	@Autowired
	private TipoMezzoDao tipoMezzoDao;
	
	@Override
	public List<TipoMezzo> selTutti() {
		return tipoMezzoDao.selTutti();
	}

}
