
package com.dishminer.facebook;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Facebook{
   	private List<Data> data;
   	private Paging paging;

 	public List<Data> getData(){
		return this.data;
	}
	public void setData(List<Data> data){
		this.data = data;
	}
 	public Paging getPaging(){
		return this.paging;
	}
	public void setPaging(Paging paging){
		this.paging = paging;
	}
}
