package com.fresco.dbrestapi.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "#{'FrescoTweet'}")
public class Userposts {
	@Id
	private String _id;
	private ArrayList<Post> posts;
	private ArrayList<String> subscribed;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public ArrayList<Post> getPosts() {
		return posts;
	}
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	public ArrayList<String> getSubscribed() {
		return subscribed;
	}
	public void setSubscribed(ArrayList<String> subscribed) {
		this.subscribed = subscribed;
	}
	@Override
	public String toString() {
		return "User [_id=" + _id + ", posts=" + posts + ", subscribed=" + subscribed + "]";
	}
}
