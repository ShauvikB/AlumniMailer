package com.bcrec.alumni.service;

import java.io.IOException;

public interface UpdateAlumniDetailsInExcel {
	
	public void updateAlumni(int rowCount, int col, String FILE_PATH) throws IOException;

}
