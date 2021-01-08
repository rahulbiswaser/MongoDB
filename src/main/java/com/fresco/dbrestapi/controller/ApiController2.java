package com.fresco.dbrestapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.Userposts;
import com.fresco.dbrestapi.repo.UserpostsRepository;
import java.time.LocalDate;

@RestController
public class ApiController2 {
	@Autowired	
	UserpostsRepository postsRepo;
	@CrossOrigin
	@PostMapping("/addpost")
	public String post(String postBody, String user) {
		LocalDate localDate = LocalDate.now();		
			Userposts up= new Userposts();
			ArrayList<String> subscribed=new ArrayList<String>();
			ArrayList<Post> postlist=new ArrayList<Post>();
				Post pt=new Post();
				pt.setPostBody(postBody);
				pt.setPostId(1);
				pt.setPostDate(localDate.toString());
				postlist.add(pt);
			up.setPosts(postlist);
			up.set_id(user);
			up.setSubscribed(subscribed);
    postsRepo.save(up);
    System.out.println("/addpost api result:"+ up);
		return "OK 200";
	}

	@CrossOrigin
	@RequestMapping("/getposts")
	public Object[] getPosts(String user) {
    List<Userposts> userpostlist = postsRepo.findAll();
		ArrayList<Post> postlist=new ArrayList<Post>();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				postlist=userpostlist.get(i).getPosts();
			}
    }
    System.out.println("/getposts api result:"+ postlist);
    Object[] objArray = postlist.toArray();
	  return objArray;
		//return new Object[0];
	}
	
	@CrossOrigin
	@RequestMapping("/delpost")
	public String delPosts(String user, String postId) {
		List<Userposts> userpostlist = postsRepo.findAll();
		Userposts up1=new Userposts();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				ArrayList<Post> pt= new ArrayList<Post>();
				pt= userpostlist.get(i).getPosts();
				for(int j=0;j<pt.size();j++) {
					if(pt.get(j).getPostId()== Integer.parseInt(postId)) {
						pt.remove(j);
					}
				}
				up1.setPosts(pt);
				up1.set_id(user);
				postsRepo.save(up1);
			}
    }
    System.out.println("/delpost api result:"+ up1);
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
		          ArrayList<String> subusrlist= new ArrayList<String>();
		          if(userpostlist.get(i).getSubscribed() !=null){
		        	  subusrlist= userpostlist.get(i).getSubscribed();
					for(int k=0;k<subusrlist.size();k++) {
						if(subusrlist.get(k).contains(searchText)) {
							returnMap.put(subusrlist.get(k), true);
						}
						}
		          }
		          else{
		        	  returnMap.put(user, true);
		          }
				}
			}
	    System.out.println("/searchuser api result:"+ returnMap);
		 return returnMap;
		//return new HashMap<String, Boolean>();
	}
	
	@CrossOrigin
	@RequestMapping("/subscriber")
	public String subscriber(String user, String subuser) {
		List<Userposts> userpostlist = postsRepo.findAll();		
		ArrayList<String> subusrlist= new ArrayList<String>();
		for(int i=0;i<userpostlist.size();i++)
		{
			if(userpostlist.get(i).get_id().equalsIgnoreCase(user)) {
				Userposts up=new Userposts();
				up= userpostlist.get(i);
				if(userpostlist.get(i).getSubscribed() !=null){
				subusrlist=userpostlist.get(i).getSubscribed();
				int listsize=  subusrlist.size();
				for(int k=0;k<listsize;k++) {
					if(subusrlist.get(k).equalsIgnoreCase(subuser)) {						
						subusrlist.remove(subuser);
					}else {						
						subusrlist.add(subuser);
					}
				}
		      }else{
		        subusrlist.add(subuser);
		      }				
				up.setSubscribed(subusrlist);
				up.set_id(user);
				postsRepo.save(up);
			}
		}
    List<Userposts> new_userpostlist = postsRepo.findAll();
    System.out.println("/subscriber api result:"+ new_userpostlist);

		return "OK 200";
	}
}
