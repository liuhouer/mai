package com.mai.controller.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 分页显示
 * */
public class PaginationTag extends BodyTagSupport{
	private static final long serialVersionUID = -1833531866598715938L;
	private final Log log = LogFactory.getLog(PaginationTag.class);
	private String param;//查询参数

	@Override
	public int doStartTag() {
		StringBuffer html=new StringBuffer();
		String[] params = param.split("&");
		String[] pa = null;
		Map<String,String> map = new HashMap<String,String>();
		for(String p : params){
			pa = p.split("=");
			if(pa != null && pa.length == 2){
				map.put(pa[0], pa[1]);
			}
		}
		int total = map.get("total") != null ? Integer.parseInt(map.get("total")) : 0;
		int page = map.get("page")!=null ?Integer.parseInt(map.get("page")):1;
		int maxsteps = map.get("maxsteps")!=null ?Integer.parseInt(map.get("maxsteps")): 10;
		int max = map.get("max")!=null ?Integer.parseInt(map.get("max")): 20;
		boolean steps = maxsteps > 0;
		int currentstep = page;
		int firststep = 1;
		int laststep = (total  +  max  - 1) / max;


		html.append("<nav class=\"text-center\"><ul class=\"pagination pagination-lg\">");

		if (currentstep > firststep) {
			page = currentstep - 1;
			html.append("<li>");
			html.append("<a href=\"JavaScript:void(0);\" onclick=\"initPagination("+page+");\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a>");
		}else{
			html.append("<li class=\"disabled\">");
			html.append("<a href=\"JavaScript:void(0);\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a>");
		}

		html.append("</span></li>");

		if (steps && laststep > firststep) {
			int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2);
			int endstep = currentstep + Math.round(maxsteps / 2) - 1;

			if (beginstep < firststep) {
				beginstep = firststep;
				endstep = maxsteps;
			}
			if (endstep > laststep) {
				beginstep = laststep - maxsteps + 1;
				if (beginstep < firststep) {
					beginstep = firststep;
				}
				endstep = laststep;
			}

			if (beginstep > firststep) {
				page = 1;
				html.append("<li>");
				html.append("<a href=\"JavaScript:void(0);\" onClick=\"initPagination("+page+")\">"+firststep+"</a>");
				html.append("</li><li><span class=\"step\">..</span></li>");
			}

			for(int kk = beginstep;kk<=endstep;kk++){
				if (currentstep == kk) {
					html.append("<li class=\"active\"><a href=\"JavaScript:void(0);\" onClick=\"initPagination("+currentstep+")\">"+kk+" <span class=\"sr-only\">(current)</span></a></li>");
				}
				else {
					html.append("<li>");
					html.append("<a href=\"JavaScript:void(0);\" onClick=\"initPagination("+kk+")\">"+kk+"</a>");
					html.append("</li>");
				}
			}

			if (endstep < laststep) {
				html.append("<li><span class=\"step\">..</span></li>");
				html.append("<li>");
				html.append("<a href=\"JavaScript:void(0);\" onClick=\"initPagination("+laststep+")\">"+laststep+"</a>");
				html.append("</li>");
			}
		}
		else{
			html.append("<li class=\"active\"><a href=\"JavaScript:void(0);\" onClick=\"initPagination("+currentstep+")\">"+currentstep+" <span class=\"sr-only\">(current)</span></a></li>");
		}

		if (currentstep < laststep) {
			html.append("<li>");
			page = currentstep + 1;
			html.append("<a aria-label=\"Next\" href=\"JavaScript:void(0);\" onClick=\"initPagination("+page+")\"><span aria-hidden=\"true\">&raquo;</span></a>");
		}else{
			html.append("<li class=\"disabled\">");
			html.append("<a aria-label=\"Next\" href=\"JavaScript:void(0);\"><span aria-hidden=\"true\">&raquo;</span></a>");
		}

		html.append("</li>");

		html.append("<li class=\"disabled\" ><a>共"+total+"条</a></li>");

		html.append("</ul></nav>");
		try {
			pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return SKIP_PAGE;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
}
