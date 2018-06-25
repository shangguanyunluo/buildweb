package com.lenovo.cloudbuild.model;

public class Directory {

	private Long id;
	private String name;
	private String absolutePath;
	private Long parentId;

	public Directory() {
	}

	public Directory(String name, String absolutePath, Long parentId) {
		this.name = name;
		this.absolutePath = absolutePath;
		this.parentId = parentId;
	}

	public Directory(Long id, String name, String absolutePath, Long parentId) {
		this.id = id;
		this.name = name;
		this.absolutePath = absolutePath;
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Directory [id=" + id + ", name=" + name + ", absolutePath=" + absolutePath + ", parentId=" + parentId
				+ "]";
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

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	public int hashCode() {
		return absolutePath.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Directory) {
			Directory directory = (Directory) obj;
			// System.out.println("equal"+ buildImage.name);
			return (absolutePath.equals(directory.getAbsolutePath()));
		}
		return super.equals(obj);
	}

}
