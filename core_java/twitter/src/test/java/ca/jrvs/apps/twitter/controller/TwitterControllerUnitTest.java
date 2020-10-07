package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet() {
    when(service.postTweet(any())).thenReturn(new Tweet());
    String[] args = {"post","I'd take some fried eggs and toast over pancakes any day of the week.","45.12:-5.12"};
    controller.postTweet(args);
  }

  @Test
  public void showTweet() {
    when(service.showTweet(any(),any())).thenReturn(new Tweet());
    String[] args = {"show","1234","[fake,news,is,the,best,news,jk,lol]"};
    controller.showTweet(args);
  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
    String[] args = {"delete","[1234,420691337,23,5318008]"};
    controller.deleteTweet(args);
  }
}