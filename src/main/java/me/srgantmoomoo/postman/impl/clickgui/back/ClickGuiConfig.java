package me.srgantmoomoo.postman.impl.clickgui.back;

import com.google.gson.*;
import com.lukflug.panelstudio.ConfigList;
import com.lukflug.panelstudio.PanelConfig;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClickGuiConfig implements ConfigList {
    private final String fileLocation;
    private JsonObject panelObject;

    public ClickGuiConfig(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public void begin(boolean loading) {
        if (loading) {
            Path path = Paths.get(fileLocation + "ClickGUI" + ".json");

            if (!Files.exists(path)) {
                return;
            }
            try {
                InputStream inputStream;
                inputStream = Files.newInputStream(path);
                JsonObject mainObject = new JsonParser().parse(new InputStreamReader(inputStream)).getAsJsonObject();
                if (mainObject.get("Panels") == null) {
                    return;
                }
                panelObject = mainObject.get("Panels").getAsJsonObject();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            panelObject = new JsonObject();
        }
    }

    @Override
    public void end(boolean loading) {
        if (panelObject == null) return;
        if (!loading) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(fileLocation + "ClickGUI" + ".json")), StandardCharsets.UTF_8);
                JsonObject mainObject = new JsonObject();
                mainObject.add("Panels", panelObject);
                String jsonString = gson.toJson(new JsonParser().parse(mainObject.toString()));
                fileOutputStreamWriter.write(jsonString);
                fileOutputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        panelObject = null;
    }

    @Override
    public PanelConfig addPanel(String title) {
        if (panelObject == null) return null;
        JsonObject valueObject = new JsonObject();
        panelObject.add(title, valueObject);
        return new JPanelConfig(valueObject);
    }

    @Override
    public PanelConfig getPanel(String title) {
        if (panelObject == null) return null;
        JsonElement configObject = panelObject.get(title);
        if (configObject != null && configObject.isJsonObject())
            return new JPanelConfig(configObject.getAsJsonObject());
        return null;
    }


    private static class JPanelConfig implements PanelConfig {
        private final JsonObject configObject;

        public JPanelConfig(JsonObject configObject) {
            this.configObject = configObject;
        }

        @Override
        public void savePositon(Point position) {
            configObject.add("PosX", new JsonPrimitive(position.x));
            configObject.add("PosY", new JsonPrimitive(position.y));
        }

        @Override
        public Point loadPosition() {
            Point point = new Point();
            JsonElement panelPosXObject = configObject.get("PosX");
            if (panelPosXObject != null && panelPosXObject.isJsonPrimitive()) {
                point.x = panelPosXObject.getAsInt();
            } else return null;
            JsonElement panelPosYObject = configObject.get("PosY");
            if (panelPosYObject != null && panelPosYObject.isJsonPrimitive()) {
                point.y = panelPosYObject.getAsInt();
            } else return null;
            return point;
        }

        @Override
        public void saveState(boolean state) {
            configObject.add("State", new JsonPrimitive(state));
        }

        @Override
        public boolean loadState() {
            JsonElement panelOpenObject = configObject.get("State");
            if (panelOpenObject != null && panelOpenObject.isJsonPrimitive()) {
                return panelOpenObject.getAsBoolean();
            }
            return false;
        }
    }
}