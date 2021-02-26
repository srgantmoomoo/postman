package me.srgantmoomoo.postman.api.cape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Capes {

	List<UUID> uuids = new ArrayList<>();

	public Capes() {
		try {
			URL capesList = new URL("https://raw.githubusercontent.com/IUDevman/gamesense-assets/main/files/capeslist.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				uuids.add(UUID.fromString(inputLine));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean hasCape(UUID id) {
		return uuids.contains(id);
	}
}