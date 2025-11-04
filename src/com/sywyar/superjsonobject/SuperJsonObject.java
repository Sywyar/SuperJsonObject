package com.sywyar.superjsonobject;

import com.google.gson.*;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SuperJsonObject {
    private final JsonObject jsonObject;
    private File file;

    public void setFile(@NotNull File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public SuperJsonObject() {
        jsonObject = new JsonObject();
    }

    public SuperJsonObject(@NotNull JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public SuperJsonObject(@NotNull String json) {
        jsonObject = (JsonObject) JsonParser.parseString(json);
    }

    public SuperJsonObject(@NotNull File file) throws FileNotFoundException {
        jsonObject = (JsonObject) JsonParser.parseReader(new FileReader(file));
        this.file = file;
    }

    public SuperJsonObject(@NotNull String json, @NotNull File file) {
        jsonObject = (JsonObject) JsonParser.parseString(json);
        this.file = file;
    }

    public SuperJsonObject(@NotNull SuperJsonObject jsonObject, @NotNull File file) {
        this.jsonObject = jsonObject.jsonObject;
        this.file = file;
    }

    public SuperJsonObject(@NotNull JsonObject jsonObject, @NotNull File file) {
        this.jsonObject = jsonObject;
        this.file = file;
    }

    public void addProperty(@NotNull String key, @NotNull String value) {
        jsonObject.addProperty(key, value);
    }

    public void addProperty(@NotNull String key, @NotNull boolean value) {
        jsonObject.addProperty(key, value);
    }

    public void addProperty(@NotNull String key, @NotNull Number value) {
        jsonObject.addProperty(key, value);
    }

    public void addProperty(@NotNull String key, @NotNull Character value) {
        jsonObject.addProperty(key, value);
    }

    public void add(@NotNull String key, @NotNull JsonElement value) {
        jsonObject.add(key, value);
    }

    public void add(@NotNull String key, @NotNull SuperJsonObject value) {
        jsonObject.add(key, value.jsonObject);
    }

    public String getAsString(@NotNull String key) {
        return jsonObject.get(key).getAsString();
    }

    public JsonPrimitive getAsJsonPrimitive(@NotNull String key) {
        return jsonObject.getAsJsonPrimitive(key);
    }

    public SuperJsonObject getAsSuperJsonObject(@NotNull String key) {
        return new SuperJsonObject(jsonObject.getAsJsonObject(key));
    }

    public SuperJsonArray getAsSuperJsonArray(@NotNull String key) {
        return new SuperJsonArray(jsonObject.getAsJsonArray(key));
    }

    public JsonArray getAsJsonArray(@NotNull String key) {
        return jsonObject.getAsJsonArray(key);
    }

    public float getAsFloat(@NotNull String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsFloat();
    }

    public double getAsDouble(@NotNull String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsDouble();
    }

    public boolean getAsBoolean(@NotNull String key) {
        return jsonObject.get(key).getAsBoolean();
    }

    public BigDecimal getAsBigDecimal(@NotNull String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsBigDecimal();
    }

    public BigInteger getAsBigInteger(@NotNull String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsBigInteger();
    }

    public int getAsInt(@NotNull String key) {
        return jsonObject.get(key).getAsInt();
    }

    public Number getAsNumber(@NotNull String key) {
        return jsonObject.get(key).getAsNumber();
    }

    public JsonObject getAsJsonObject(@NotNull String key) {
        return jsonObject.getAsJsonObject(key);
    }

    public JsonElement getAsJsonElement(@NotNull String key) {
        return jsonObject.get(key);
    }

    public ArrayList<String> valueToKey(@NotNull String value) {
        ArrayList<String> keys = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            if (jsonObject.get(key).getAsString().equals(value)) {
                keys.add(key);
            }
        }
        return keys;
    }

    public ArrayList<String> valueToKey(@NotNull JsonElement value) {
        ArrayList<String> keys = new ArrayList<>();
        for (Map.Entry<String, JsonElement> map : jsonObject.entrySet()) {
            if (map.getValue().equals(value)) {
                keys.add(map.getKey());
            }
        }
        return keys;
    }

    public JsonElement makeJsonElement(@NotNull String value) {
        JsonObject json = new JsonObject();
        json.addProperty("key", value);
        return json.get("key");
    }

    public JsonElement makeJsonElement(@NotNull boolean value) {
        JsonObject json = new JsonObject();
        json.addProperty("key", value);
        return json.get("key");
    }

    public JsonElement makeJsonElement(@NotNull Number value) {
        JsonObject json = new JsonObject();
        json.addProperty("key", value);
        return json.get("key");
    }

    public JsonElement makeJsonElement(@NotNull Character value) {
        JsonObject json = new JsonObject();
        json.addProperty("key", value);
        return json.get("key");
    }

    public boolean has(@NotNull String key) {
        return jsonObject.has(key);
    }

    public JsonElement remove(@NotNull String key) {
        return jsonObject.remove(key);
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return jsonObject.entrySet();
    }

    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    public String toString() {
        return jsonObject.toString();
    }

    public SuperJsonObject deepCopy() {
        return new SuperJsonObject(jsonObject.deepCopy());
    }

    public int size() {
        return jsonObject.size();
    }

    public boolean equals(@NotNull Object o) {
        return o == this || o instanceof SuperJsonObject && ((SuperJsonObject) o).jsonObject.equals(this.jsonObject);
    }

    public Map<String, JsonElement> asMap() {
        return jsonObject.asMap();
    }

    public boolean isEmpty() {
        return jsonObject.isEmpty();
    }

    public static boolean isJsonObject(@NotNull String str) {
        try {
            JsonObject temp = (JsonObject) JsonParser.parseString(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save() throws IOException {
        if (file == null) {
            return false;
        } else {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return file.createNewFile() && Writer(jsonObject.toString(), file.getPath());
            }
            return Writer(jsonObject.toString(), file.getPath());
        }
    }

    public static boolean Writer(String str, String path) {
        try {
            BufferedWriter osw;
            FileOutputStream fos;
            fos = new FileOutputStream(path);
            osw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            osw.write(str);
            osw.flush();
            osw.close();
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return jsonObject.hashCode();
    }
}
