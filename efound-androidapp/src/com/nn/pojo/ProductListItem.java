package com.nn.pojo;

public class ProductListItem {
	public String TagNo, TagName;
	private Boolean isStolen;

	public ProductListItem(String TagName, String TagNo, Boolean isStolen) {
		this.TagName = TagName;
		this.TagNo = TagNo;
		this.isStolen = isStolen;
	}

	public String getTagNo() {
		return TagNo;
	}

	public void setTagNo(String tagNo) {
		TagNo = tagNo;
	}

	public String getTagName() {
		return TagName;
	}

	public void setTagName(String tagName) {
		TagName = tagName;
	}

	public Boolean getIsStolen() {
		return isStolen;
	}

	public void setIsStolen(Boolean isStolen) {
		this.isStolen = isStolen;
	}

}
