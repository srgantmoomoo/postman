package me.srgantmoomoo.postman.framework.friend;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.Main;

public class FriendManager {
	public final List<Friend> friends = new ArrayList<>();

	public List<String> getFriendsByName() {
		ArrayList<String> friendsName = new ArrayList<>();
		friends.forEach(friend -> friendsName.add(friend.getName()));

		return friendsName;
	}

	public boolean isFriend(String name) {
		for (Friend friend : friends) {
			if (friend.getName().toLowerCase().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public Friend getFriendByName(String name) {
		for (Friend friend : friends) {
			if (friend.getName().equalsIgnoreCase(name)) {
				return friend
			}
		}

		return null;
	}

	public void addFriend(String name) {
		friends.add(new Friend(name));
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}

	public void removeFriend(String name) {
		friends.remove(getFriendByName(name));
	}
	
	public void clearFriends() {
		friends.clear();
	}
}
