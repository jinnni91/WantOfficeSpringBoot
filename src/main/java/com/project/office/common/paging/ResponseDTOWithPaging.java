package com.project.office.common.paging;

import lombok.Data;

@Data
public class ResponseDTOWithPaging {

	private Object data;
	private PagingButton pageBtn;
	
}
