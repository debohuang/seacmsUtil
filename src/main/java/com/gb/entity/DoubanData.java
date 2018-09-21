package com.gb.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DoubanData implements Serializable {

	private int count;
	private int start;
	private int total;

	private String title;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	private DoubanDataSubjects subjects;
	
	public DoubanDataSubjects getSubjects() {
		return subjects;
	}

	public void setSubjects(DoubanDataSubjects subjects) {
		this.subjects = subjects;
	}

	public class DoubanDataSubjects implements Serializable {
		
		private List<DoubanDataSubject> subjects;

		
		public List<DoubanDataSubject> getSubjects() {
			return subjects;
		}

		public void setSubjects(List<DoubanDataSubject> subjects) {
			this.subjects = subjects;
		}
		
	}
	
	
	public class DoubanDataSubject implements Serializable {

		private Map<String, Object> rating;

		private String genres;

		private String title;

		private Map<String, Object> casts;

		private String collect_count;

		private String original_title;

		private String subtype;

		private Map<String, Object> directors;

		private String year;

		private Map<String, Object> images;

		private String alt;

		private String id;

		public Map<String, Object> getRating() {
			return rating;
		}

		public void setRating(Map<String, Object> rating) {
			this.rating = rating;
		}

		public String getGenres() {
			return genres;
		}

		public void setGenres(String genres) {
			this.genres = genres;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Map<String, Object> getCasts() {
			return casts;
		}

		public void setCasts(Map<String, Object> casts) {
			this.casts = casts;
		}

		public String getCollect_count() {
			return collect_count;
		}

		public void setCollect_count(String collect_count) {
			this.collect_count = collect_count;
		}

		public String getOriginal_title() {
			return original_title;
		}

		public void setOriginal_title(String original_title) {
			this.original_title = original_title;
		}

		public String getSubtype() {
			return subtype;
		}

		public void setSubtype(String subtype) {
			this.subtype = subtype;
		}

		public Map<String, Object> getDirectors() {
			return directors;
		}

		public void setDirectors(Map<String, Object> directors) {
			this.directors = directors;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public Map<String, Object> getImages() {
			return images;
		}

		public void setImages(Map<String, Object> images) {
			this.images = images;
		}

		public String getAlt() {
			return alt;
		}

		public void setAlt(String alt) {
			this.alt = alt;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

}
