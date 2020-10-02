package com.synclab.ecommerce.utility.pages;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class PageUtils {

	public static <T> ResponseEntity<Page<T>> listToPageResponseEntity(List<T> list, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);

		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());

		if (list.size() == 0 || start >= end)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(new PageImpl<T>(list.subList(start, end), pageable, list.size()));
	}
	
	public static <T>  Page<T> listToPage(List<T> list, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);

		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());

		return new PageImpl<T>(list.subList(start, end), pageable, list.size());
	}
	
}
