package com.lenovo.cloudbuild.model;

public class Directory {

	private Long id;
	private String name;
	private Long parentId;

	public Directory() {
	}

	public Directory(String name, Long parentId) {
		this.name = name;
		this.parentId = parentId;
	}

	public Directory(Long id, String name, Long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Directory [id=" + id + ", name=" + name + ", parentId=" + parentId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
