package com.bcrec.alumni.service;

import java.util.List;

import com.bcrec.alumni.model.Alumni;

public interface ReadAlumniDetailsFromExcel {
	
	public List<Alumni> getAlumniListFromExcel(String FILE_PATH);

}
