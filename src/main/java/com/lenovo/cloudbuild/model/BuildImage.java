package com.lenovo.cloudbuild.model;

import java.util.Date;

public class BuildImage {
	
	private Long id;
	private String name;
	private Date modifiedTime;
	private Long size;
	private String description;
	
	public BuildImage() {
	}

	public BuildImage(String name, Date modifiedTime, Long size, String description) {
		this.name = name;
		this.modifiedTime = modifiedTime;
		this.size = size;
		this.description = description;
	}

	public BuildImage(Long id, String name, Date modifiedTime, Long size, String description) {
		this.id = id;
		this.name = name;
		this.modifiedTime = modifiedTime;
		this.size = size;
		this.description = description;
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

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BuildImage [id=" + id + ", name=" + name + ", modifiedTime=" + modifiedTime + ", size=" + size
				+ ", description=" + description + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BuildImage) {
			BuildImage buildImage = (BuildImage) obj;
//			 System.out.println("equal"+ buildImage.name);
            return (name.equals(buildImage.name));
        }
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
//		BuildImage buildImage = (BuildImage) this;
//		System.out.println("Hash" + buildImage.name);
        return name.hashCode();
	}
	
	
	
}
