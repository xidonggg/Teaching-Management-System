package com.ssh.answer;

import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 */
public class Page<T> {

	private static int DEFAULT_PAGE_SIZE = 10; // 每页默认记录数

	private static int DEFAULT_START_NUM = 0; // 默认记录开始

	private static int DEFAULT_TOTAL_NUM = 0; // 默认记录总数

	private int size = Page.DEFAULT_PAGE_SIZE; // 每页的记录数

	private int start = Page.DEFAULT_START_NUM; // 数据在数据库中的位置,从0开始

	private int total = Page.DEFAULT_TOTAL_NUM; // 总记录数

	private int current; // 当前页号

	private List<T> list; // 当前页中存放的记录,类型一般为List

	/**
	 * 构造方法，只构造空页.
	 */
	public Page() {
		this(Page.DEFAULT_START_NUM, Page.DEFAULT_TOTAL_NUM, Page.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start 本页数据在数据库中的起始位置
	 * @param total 数据库中总记录条数
	 * @param size 本页容量
	 */
	public Page(final int start, final int total, final int size) {
		this.start = start;
		this.total = total;
		this.size = size;
		this.current = getCurrentPageNo();
	}

	/**
	 * 数据层使用的构造方法.
	 * 
	 * @param start 本页数据在数据库中的起始位置
	 * @param total 数据库中总记录条数
	 * @param size 本页容量
	 * @param result 本页包含的数据
	 */
	public Page(final int start, final int total, final int size, List<T> list) {
		this.start = start;
		this.total = total;
		this.size = size;
		this.current = getCurrentPageNo();
		this.list = list;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public int getCurrentPageNo() {
		return this.start / this.size + 1;
	}

	/**
	 * 获取总页数
	 * 
	 * @author caoyi 2012-9-20 下午02:07:57
	 * @return
	 */
	public int getPages() {
		if (this.total % this.size == 0)
			return this.total / this.size;
		else
			return this.total / this.size + 1;
	}

	/**
	 * 获取页号，从1开始.
	 * 
	 * @param startIndex 开始索引
	 * @param size 每页记录条数
	 * @return 从1开始的页号
	 */
	public static int getPageNo(final int startIndex, final int size) {
		return startIndex % size == 0 ? startIndex / size : startIndex / size + 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值
	 * 
	 * @author caoyi 2012-9-20 下午02:08:18
	 * @param pageNo
	 * @return
	 */
	protected static int getStartOfPage(final int pageNo) {
		return Page.getStartOfPage(pageNo, Page.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo 从1开始的页号
	 * @param size 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(final int pageNo, final int size) {
		return (pageNo - 1) * size;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return getCurrentPageNo() < getPages() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return getCurrentPageNo() > 1;
	}

	public int getSize() {
		return this.size;
	}

	public int getStart() {
		return this.start;
	}

	public int getTotal() {
		return this.total;
	}

	public void setSize(final int size) {
		this.size = size;
	}

	public void setStart(final int start) {
		this.start = start;
	}

	public void setTotal(final int total) {
		this.total = total;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
