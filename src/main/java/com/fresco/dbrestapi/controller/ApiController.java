package com.fresco.dbrestapi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.Userposts;
import com.fresco.dbrestapi.repo.UserpostsRepository;

@RestController
public class ApiController {
	@Autowired	
	UserpostsRepository postsRepo;
	@CrossOrigin
	@PostMapping("/addpost")
	public String post(String postBody, String user) {
		LocalDate localDate = LocalDate.now();
			Userposts up= new Userposts();
			ArrayList<Post> postlist=new ArrayList<Post>();
				Post pt=new Post();
				pt.setPostBody(postBody);
				pt.setPostId(1);
				pt.setPostDate(localDate.toString());
				postlist.add(pt);
			up.setPosts(postlist);	
			up.set_id(user);
		postsRepo.save(up);
		return "OK 200";
	}

	@CrossOrigin
	@RequestMapping("/getposts")
	public Object[] getPosts(String user) {
		List<Userposts> userpostlist = postsRepo.findAll();
		Object o=new Object();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				o= userpostlist.get(i).getPosts();
			}
		}
		return (Object[]) o;
	}
	
	@CrossOrigin
	@RequestMapping("/delpost")
	public String delPosts(String user, int postId) {
		List<Userposts> userpostlist = postsRepo.findAll();
		Userposts up1=new Userposts();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				ArrayList<Post> pt= new ArrayList<Post>();
				ArrayList<Post> newpt= new ArrayList<Post>();
				pt= userpostlist.get(i).getPosts();
				for(int j=0;j<pt.size();j++) {
					if(pt.get(j).getPostId()!= postId) {
						newpt.add(pt.get(j));
					}
				}				
				up1.setPosts(newpt);				
			}
			
		}
		postsRepo.save(up1);
		return "OK 200";
	}
	@CrossOrigin
	@RequestMapping("/searchuser")
	public HashMap<String, Boolean> searchUser(String user, String searchText) {
	
		HashMap<String, Boolean> returnMap= new HashMap<String, Boolean>();
		List<Userposts> userpostlist = postsRepo.findAll();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				ArrayList<String> subusr= new ArrayList<String>();
				subusr= userpostlist.get(i).getSubscribed();
				for(int k=0;k<subusr.size();k++) {
					if(subusr.get(i).contains("searchText")) {
						returnMap.put(subusr.get(i), true);
					}
				}
			}
		}
		return returnMap;
	}
	
	@CrossOrigin
	@RequestMapping("/subscriber")
	public String subscriber(String user, String subuser) {
		List<Userposts> userpostlist = postsRepo.findAll();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				ArrayList<String> subslist= new ArrayList<String>();
				subslist.add(subuser);
				userpostlist.get(i).setSubscribed(subslist);
			}
		}
		
		return "OK 200";
	}
}
