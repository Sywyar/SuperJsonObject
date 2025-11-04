package com.sywyar.superjsonobject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SuperJsonArray {
    private JsonArray jsonArray;

    public SuperJsonArray(JsonArray jsonArray){
        this.jsonArray = jsonArray;
    }

    public JsonElement get(@NotNull int index){
        return jsonArray.get(index);
    }

    public SuperJsonObject getAsSuperJsonObject(@NotNull int index){
        return new SuperJsonObject(jsonArray.get(index).getAsJsonObject());
    }

    public JsonObject getAsJsonObject(@NotNull int index){
        return jsonArray.get(index).getAsJsonObject();
    }

    public String getAsString(@NotNull int index){
        return jsonArray.get(index).getAsString();
    }

    public int getAsInt(@NotNull int index){
        return jsonArray.get(index).getAsInt();
    }

    public long getAsLong(@NotNull int index){
        return jsonArray.get(index).getAsLong();
    }

    public float getAsFloat(@NotNull int index){
        return jsonArray.get(index).getAsFloat();
    }

    public double getAsDouble(@NotNull int index){
        return jsonArray.get(index).getAsDouble();
    }

    public boolean getAsBoolean(@NotNull int index){
        return jsonArray.get(index).getAsBoolean();
    }

    public BigDecimal getAsBigDecimal(@NotNull int index){
        return jsonArray.get(index).getAsBigDecimal();
    }

    public BigInteger getAsBigInteger(@NotNull int index){
        return jsonArray.get(index).getAsBigInteger();
    }

    public SuperJsonArray getAsSuperJsonArray(@NotNull int index){
        return new SuperJsonArray(jsonArray.get(index).getAsJsonArray());
    }

    public JsonPrimitive getAsJsonPrimitive(@NotNull int index){
        return jsonArray.get(index).getAsJsonPrimitive();
    }

    public JsonArray getAsJsonArray(@NotNull int index){
        return jsonArray.get(index).getAsJsonArray();
    }

    public JsonArray getJsonArray(){
        return jsonArray;
    }
}
