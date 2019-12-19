package com.zking.ssm.tag;

import com.zking.ssm.util.PageBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




public class PageTag extends BodyTagSupport {
	
	private PageBean pageBean;

	public PageTag() {
		super();
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			pageContext.getOut().print(toHtml());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return SKIP_BODY;
	}
	
	public String toHtml() {
		StringBuffer bf = new StringBuffer();
	    bf.append("<div align='center'>");
	    bf.append("<form id='formpage' action="+this.pageBean.getUrl()+" method='post'>");
	    bf.append("<input id='page' type='hidden' name='page' />");
	    
	    Map<String, String[]> parmeterMap = pageBean.getParameterMap();
	    Set<Entry<String, String[]>> entrySet = parmeterMap.entrySet();
	    
	    for (Entry<String, String[]> entry : entrySet) {
			String name = entry.getKey();
			String values[] = entry.getValue();
			System.out.println(name);
			if(name.equals("page")) {
				continue;
			}
			for (String value : values) {
				bf.append("<input type='hidden' name='"+name+"' value='"+value+"' />");
			}
		}
	    
	    
	    bf.append("</form>");
	    bf.append("<a href='javascript:toFoward(1)'>首页</a>");
	    bf.append("<a href='javascript:toFoward("+this.pageBean.getFrontPage()+")'>上一页</a>");
	    bf.append("<a>当前页["+this.pageBean.getPage()+"/"+this.pageBean.getMaxPage()+"]</a>");
	    bf.append("<a href='javascript:toFoward("+this.pageBean.getNextPage()+")'>下一页</a>");
	    bf.append("<a href='javascript:toFoward("+this.pageBean.getMaxPage()+")'>末页</a>");
	    bf.append("<input id='goto' type='text' /><a href='javascript:gotoPage()'>Go...</a>");
	    bf.append("总条数"+this.pageBean.getTotal());
	    bf.append("<script type='text/javascript'>");
	    bf.append("function toFoward(page){");
	    bf.append("document.getElementById('page').value=page;");
	    bf.append("document.getElementById('formpage').submit()}");
				
	    bf.append("function gotoPage(){");
	    bf.append("var page = document.getElementById('goto').value;");
	    bf.append("if(isNaN(page)){");
	    bf.append("	alert('请输入数字');");
	    bf.append("}else if(page<1 || page>"+this.pageBean.getMaxPage()+"){");
	    bf.append("alert('请输入1-"+this.pageBean.getMaxPage()+"');");
	    bf.append("}else{");
	    bf.append("toFoward(page);}}");
					
				
	    bf.append("</script>");
	    bf.append("</div>");
		return bf.toString();
	}
}
