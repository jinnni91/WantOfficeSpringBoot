package com.project.office.common.paging;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PagingButton {

	private int currentPage;
	private int startPage;
	private int endPage;
	private int maxPage;
	
}
