package me.srgantmoomoo.postman.backend.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cape {

	List<UUID> uuids = new ArrayList<>();

	public Cape() {
		try {
			URL capesList = new URL("https://pastebin.com/raw/2K1zmXZc");
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
