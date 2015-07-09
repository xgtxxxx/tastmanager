package task.model;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("rawtypes")
public class Pager implements Serializable{

	 

    private static final long serialVersionUID = 1566826618769972857L;

    public static final int DEFAULT_PAGE_SIZE = 20;


    private int pageSize = DEFAULT_PAGE_SIZE;


    private int pageNo = 1;


    private int rowCount;


    private int pageCount = 1;


	private List resultList;

    

    public Pager() {


    }

    

    public Pager(int pageNo) {

       this.pageNo = pageNo;

    }

 

    public Pager(int pageSize, int pageNo) {

       this.pageSize = pageSize;

       this.pageNo = pageNo;

    }

 

    public int getPageSize() {

       return pageSize;

    }

 

    public void setPageSize(int pageSize) {

       this.pageSize = pageSize;

    }

 

    public int getPageNo() {

       return pageNo;

    }

 

    public void setPageNo(int pageNo) {

       this.pageNo = pageNo;

    }

 

    public int getRowCount() {

       return rowCount;

    }

 

    public void setRowCount(int rowCount) {

       this.rowCount = rowCount;

       if(rowCount % pageSize == 0) {

           this.pageCount = rowCount / pageSize;

       } else {

           this.pageCount = rowCount / pageSize + 1;

       }

    }

    

    public int getPageCount() {

       return pageCount;

    }

    

    public void setPageCount(int pageCount) {

       this.pageCount = pageCount;

    }

 

    public List getResultList() {

       return resultList;

    }

 

    public void setResultList(List resultList) {

       this.resultList = resultList;

    }



	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", rowCount=" + rowCount + ", pageCount=" + pageCount
				+ ", resultList=" + resultList + "]";
	}


}


