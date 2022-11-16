package com.project.office.common.paging;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PagingButton {

	private int currentPage;
	private int firstPage;
	private int endPage;
	private int maxPage;
	
}
