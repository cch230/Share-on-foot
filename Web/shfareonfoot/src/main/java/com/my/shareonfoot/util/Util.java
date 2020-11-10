package com.my.shareonfoot.util;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.DetailFeedVO;
import com.my.shareonfoot.social.vo.DetailFeedVO_Extended;
import com.my.shareonfoot.social.vo.ExpandedFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;

public class Util {
	

	public static BoardVO setPageFilter(BoardVO boardFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		return boardFilter;
	}
	
	public static FollowVO setPageFilter(FollowVO followFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			followFilter.setPageStart(pageInt * pageSizeInt);
			followFilter.setPageSize(pageSizeInt);
		}
		return followFilter;
	}
	
	public static FeedVO setPageFilter(FeedVO feedFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			feedFilter.setPageStart(pageInt * pageSizeInt);
			feedFilter.setPageSize(pageSizeInt);
		}
		return feedFilter;
	}
	
	public static DetailFeedVO setPageFilter(DetailFeedVO feedFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			feedFilter.setPageStart(pageInt * pageSizeInt);
			feedFilter.setPageSize(pageSizeInt);
		}
		return feedFilter;
	}
	
	public static DetailFeedVO_Extended setPageFilter(DetailFeedVO_Extended feedFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			feedFilter.setPageStart(pageInt * pageSizeInt);
			feedFilter.setPageSize(pageSizeInt);
		}
		return feedFilter;
	}
	
	
	public static ExpandedFeedVO setPageFilter(ExpandedFeedVO feedFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			feedFilter.setPageStart(pageInt * pageSizeInt);
			feedFilter.setPageSize(pageSizeInt);
		}
		return feedFilter;
	}
	
}
