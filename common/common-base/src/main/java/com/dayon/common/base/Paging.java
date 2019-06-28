package com.dayon.common.base;

import java.io.Serializable;

public class Paging implements Serializable {
	private static final long serialVersionUID = -8563366579600546440L;
	
	private int limit;// 每页条数
	private long count;// 总条数
	private int pageCount;// 总页数

	private long first;// 开始索引 从0开始
	private long last;// 结束索引 不包含该索引数据

	private int afterPage;// 下一页
	private int page;// 当前页
	private int beforePage;// 上一页

	private int[] pageBar; // 分页栏

	public Paging(int page, int limit, long count) {
		this.page = page < 1 ? 1 : page;
		this.limit = limit < 0 ? 0 : limit;
		this.count = count < 0 ? 0 : count;

		this.first = this.page * this.limit - this.limit;
		this.last = this.page * this.limit;

		long pageCount = this.count / this.limit;
		if (this.count % this.limit != 0) {
			pageCount++;
		}
		this.pageCount = (int) pageCount;

		this.beforePage = this.page > 1 ? this.page - 1 : 1;

		this.afterPage = this.page < this.pageCount ? this.page + 1 : this.pageCount;

		int maxConunt = 10; // 最高10页
		int beginPage;
		int endPage;
		if (this.pageCount <= count) {
			beginPage = 1;
			endPage = this.pageCount;
		} else {
			beginPage = this.page - maxConunt / 2;
			endPage = this.page + maxConunt / 2 - 1;

			if (beginPage <= 0) {
				beginPage = 1;
				endPage = beginPage + maxConunt - 1;
			}
			if (endPage >= this.pageCount) {
				endPage = this.pageCount;
				beginPage = this.pageCount - maxConunt + 1;
			}
		}
		this.pageBar = new int[endPage - beginPage + 1];
		for (int i = 0; i < pageBar.length; i++) {
			pageBar[i] = beginPage + i;
		}
	}

	public long getFirst() {
		return this.first;
	}

	public long getLast() {
		return this.last;
	}

	public int getLimit() {
		return this.limit;
	}

	// 前一页
	public int getBeforePage() {

		return this.beforePage;
	}

	// 当前页
	public int getPage() {
		return this.page;
	}

	// 后一页
	public int getAfterPage() {
		return this.afterPage;
	}

	// 总页数
	public long getPageCount() {
		return this.pageCount;
	}

	// 分页栏
	public int[] getPageBar() {

		return this.pageBar;
	}

}
